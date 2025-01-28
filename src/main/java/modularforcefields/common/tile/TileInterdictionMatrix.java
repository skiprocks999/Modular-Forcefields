package modularforcefields.common.tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

import com.google.common.collect.Sets;

import electrodynamics.prefab.properties.Property;
import electrodynamics.prefab.properties.PropertyTypes;
import electrodynamics.prefab.tile.components.IComponentType;
import electrodynamics.prefab.tile.components.type.ComponentContainerProvider;
import electrodynamics.prefab.tile.components.type.ComponentInventory;
import electrodynamics.prefab.tile.components.type.ComponentInventory.InventoryBuilder;
import electrodynamics.prefab.tile.components.type.ComponentPacketHandler;
import electrodynamics.prefab.tile.components.type.ComponentTickable;
import modularforcefields.References;
import modularforcefields.common.inventory.container.ContainerInterdictionMatrix;
import modularforcefields.common.item.ItemModule;
import modularforcefields.common.item.subtype.SubtypeModule;
import modularforcefields.registers.ModularForcefieldsDataComponentTypes;
import modularforcefields.registers.ModularForcefieldsTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.event.entity.living.MobSpawnEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.items.IItemHandler;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.GAME, modid = References.ID)
public class TileInterdictionMatrix extends TileFortronConnective {
	public static HashMap<TileInterdictionMatrix, AABB> matrices = new HashMap<>();
	public static final int BASEENERGY = 100;
	public static final HashSet<SubtypeModule> VALIDMODULES = Sets.newHashSet(SubtypeModule.values());
	public Property<Integer> fortron = property(new Property<>(PropertyTypes.INTEGER, "fortron", 0));
	public Property<Integer> fortronCapacity = property(new Property<>(PropertyTypes.INTEGER, "fortronCapacity", 0));
	public int radius;
	public boolean running;
	public boolean antispawn;
	public boolean blockaccess;
	public boolean blockalter;
	private int scaleEnergy;
	private int strength;

	public TileInterdictionMatrix(BlockPos pos, BlockState state) {
		super(ModularForcefieldsTiles.TILE_INTERDICTIONMATRIX.get(), pos, state);
		addComponent(new ComponentPacketHandler(this));
		addComponent(new ComponentInventory(this, InventoryBuilder.newInv().forceSize(18)).valid((index, stack, inv) -> true).onChanged(this::onChanged));
		addComponent(new ComponentContainerProvider("container.interdictionmatrix", this).createMenu((id, player) -> new ContainerInterdictionMatrix(id, player, getComponent(IComponentType.Inventory), getCoordsArray())));
	}

	@Override
	protected void tickCommon(ComponentTickable tickable) {
		super.tickCommon(tickable);
		if (tickable.getTicks() % 20 == 0) {
			fortronCapacity.set(getMaxFortron());
			fortron.set(Mth.clamp(fortron.get(), 0, fortronCapacity.get()));
		}
	}

	private final HashSet<UUID> validPlayers = new HashSet<>();

	@Override
	protected void tickServer(ComponentTickable tickable) {
		super.tickServer(tickable);
		if (tickable.getTicks() % 1000 == 1) {
			onChanged(getComponent(IComponentType.Inventory), -1);
		}
		int use = getFortronUse();
		running = false;
		if (fortron.get() >= use) {
			fortron.set(fortron.get() - use);
			running = true;
		}
		validPlayers.clear();
		if (tickable.getTicks() % 10 == 0) {
			if (running) {
				for (Direction direction : Direction.values()) {
					BlockEntity entity = level.getBlockEntity(worldPosition.offset(direction.getNormal()));
					if (entity instanceof TileBiometricIdentifier identifier) {
						for (ItemStack stack : identifier.<ComponentInventory>getComponent(IComponentType.Inventory).getItems()) {
							if (stack.has(ModularForcefieldsDataComponentTypes.UUID)) {
								validPlayers.add(stack.get(ModularForcefieldsDataComponentTypes.UUID));
							}
						}
					}
				}
				AABB aabb = new AABB(worldPosition).inflate(radius);
				List<LivingEntity> entities = level.getEntities(EntityTypeTest.forClass(LivingEntity.class), aabb, LivingEntity::isAlive);
				matrices.put(this, aabb);
				List<SubtypeModule> list = new ArrayList<>();
				for (ItemStack stack : this.<ComponentInventory>getComponent(IComponentType.Inventory).getItems()) {
					if(stack.getItem() instanceof ItemModule module) {
						list.add(module.subtype);
					}
				}
				applyModules(list, entities);
			}
		}
	}

	private void applyModules(List<SubtypeModule> list, List<LivingEntity> entities) {
		for (LivingEntity entity : entities) {
			if (entity instanceof Player player) {
				if (validPlayers.contains(player.getUUID()) || player.isCreative()) {
					continue;
				}
			}
			if (list.contains(SubtypeModule.upgradeantifriendly)) {
				if (entity instanceof Animal animal) {
					animal.hurt(animal.damageSources().magic(), 5 + strength);
				}
			}
			if (list.contains(SubtypeModule.upgradeantihostile)) {
				if (entity instanceof Monster monster) {
					monster.hurt(monster.damageSources().magic(), 5 + strength);
				}
			}
			if (list.contains(SubtypeModule.upgradeconfiscate)) {
				if (entity instanceof Player player) {
					confiscateItems(player);
				}
			}
			if (list.contains(SubtypeModule.upgradeantipersonnel)) {
				if (entity instanceof Player player) {
					player.hurt(player.damageSources().magic(), 5 + strength);
				}
			}
			antispawn = list.contains(SubtypeModule.upgradeantispawn);
			blockaccess = list.contains(SubtypeModule.upgradeblockaccess);
			blockalter = list.contains(SubtypeModule.upgradeblockalter);
		}
	}

	private void confiscateItems(Player player) {
		BlockEntity above = level.getBlockEntity(worldPosition.above());

		if(above == null) {
			return;
		}

		IItemHandler handler = level.getCapability(Capabilities.ItemHandler.BLOCK, above.getBlockPos(), above.getBlockState(), above, Direction.DOWN);

		if(handler == null) {
			return;
		}

		List<ItemStack> stacks = player.getInventory().items;

		for (int index = 0; index < stacks.size(); index++) {

			player.getInventory().setItem(index, addItemToItemHandler(stacks.get(index), handler).copy());
		}

		stacks = player.getInventory().armor;

		for (int index = 0; index < stacks.size(); index++) {

			player.getInventory().setItem(index, addItemToItemHandler(stacks.get(index), handler).copy());
		}

		stacks = player.getInventory().offhand;

		for (int index = 0; index < stacks.size(); index++) {

			player.getInventory().setItem(index, addItemToItemHandler(stacks.get(index), handler).copy());

		}
	}

	private ItemStack addItemToItemHandler(ItemStack item, IItemHandler handler) {

		for (int targetIndex = 0; targetIndex < handler.getSlots(); targetIndex++) {

			ItemStack remainder = handler.insertItem(targetIndex, item, false);

			int taken = item.getCount() - remainder.getCount();

			if (taken <= 0) {

				continue;

			}

			item.shrink(taken);

			if(item.isEmpty()) {
				break;
			}

		}

		return item;

	}

	@SubscribeEvent
	public static void spawnLiving(MobSpawnEvent.SpawnPlacementCheck event) {
		for (Entry<TileInterdictionMatrix, AABB> en : matrices.entrySet()) {
			if (en.getKey().running && !en.getKey().isRemoved() && en.getKey().antispawn) {
				if (en.getValue().intersects(event.getEntityType().getSpawnAABB(event.getPos().getX(), event.getPos().getY(), event.getPos().getZ()))) {
					event.setResult(MobSpawnEvent.SpawnPlacementCheck.Result.FAIL);
					return;
				}
			}
		}
	}

	@SubscribeEvent
	public static void antiAccessBlockRight(PlayerInteractEvent.RightClickBlock event) {
		for (Entry<TileInterdictionMatrix, AABB> en : matrices.entrySet()) {
			if (en.getKey().running && !en.getKey().isRemoved() && en.getKey().blockaccess) {
				if (en.getValue().contains(event.getPos().getX(), event.getPos().getY(), event.getPos().getZ())) {
					Player player = event.getEntity();
					if (en.getKey().validPlayers.contains(player.getUUID()) || player.isCreative()) {
						continue;
					}
					event.setCanceled(true);
					event.setCancellationResult(InteractionResult.FAIL);
					return;
				}
			}
		}
	}

	@SubscribeEvent
	public static void antiAccessBlockLeft(PlayerInteractEvent.LeftClickBlock event) {
		for (Entry<TileInterdictionMatrix, AABB> en : matrices.entrySet()) {
			if (en.getKey().running && !en.getKey().isRemoved() && en.getKey().blockaccess) {
				if (en.getValue().contains(event.getPos().getX(), event.getPos().getY(), event.getPos().getZ())) {
					Player player = event.getEntity();
					if (en.getKey().validPlayers.contains(player.getUUID()) || player.isCreative()) {
						continue;
					}
					event.setCanceled(true);
					return;
				}
			}
		}
	}

	@SubscribeEvent
	public static void antiAccessItemRight(PlayerInteractEvent.RightClickBlock event) {
		for (Entry<TileInterdictionMatrix, AABB> en : matrices.entrySet()) {
			if (en.getKey().running && !en.getKey().isRemoved() && en.getKey().blockaccess) {
				if (en.getValue().contains(event.getPos().getX(), event.getPos().getY(), event.getPos().getZ())) {
					Player player = event.getEntity();
					if (en.getKey().validPlayers.contains(player.getUUID()) || player.isCreative()) {
						continue;
					}
					event.setCanceled(true);
					event.setCancellationResult(InteractionResult.FAIL);
					return;
				}
			}
		}
	}

	@SubscribeEvent
	public static void antiAccessItemLeft(PlayerInteractEvent.LeftClickBlock event) {
		for (Entry<TileInterdictionMatrix, AABB> en : matrices.entrySet()) {
			if (en.getKey().running && !en.getKey().isRemoved() && en.getKey().blockaccess) {
				if (en.getValue().contains(event.getPos().getX(), event.getPos().getY(), event.getPos().getZ())) {
					Player player = event.getEntity();
					if (en.getKey().validPlayers.contains(player.getUUID()) || player.isCreative()) {
						continue;
					}
					event.setCanceled(true);
					return;
				}
			}
		}
	}

	@SubscribeEvent
	public static void antiAccess(BlockEvent.BreakEvent event) {
		for (Entry<TileInterdictionMatrix, AABB> en : matrices.entrySet()) {
			if (en.getKey().running && !en.getKey().isRemoved() && en.getKey().blockalter) {
				if (en.getValue().contains(event.getPos().getX(), event.getPos().getY(), event.getPos().getZ())) {
					Player player = event.getPlayer();
					if (en.getKey().validPlayers.contains(player.getUUID()) || player.isCreative()) {
						continue;
					}
					event.setCanceled(true);
					return;
				}
			}
		}
	}

	@SubscribeEvent
	public static void antiAccess(BlockEvent.EntityPlaceEvent event) {
		for (Entry<TileInterdictionMatrix, AABB> en : matrices.entrySet()) {
			if (en.getKey().running && !en.getKey().isRemoved() && en.getKey().blockalter) {
				if (en.getValue().contains(event.getPos().getX(), event.getPos().getY(), event.getPos().getZ())) {
					if (event.getEntity() instanceof Player player) {
						if (en.getKey().validPlayers.contains(player.getUUID()) || player.isCreative()) {
							continue;
						}
					}
					event.setCanceled(true);
					return;
				}
			}

		}
	}

	public int getMaxFortron() {
		return getFortronUse() * 40 + BASEENERGY;
	}

	public int getFortronUse() {
		return scaleEnergy;
	}

	@Override
	protected boolean canRecieveFortron(TileFortronConnective tile) {
		return tile instanceof TileFortronCapacitor;
	}

	private void onChanged(ComponentInventory inv, int index) {
		radius = countModules(SubtypeModule.manipulationscale);
		strength = countModules(SubtypeModule.upgradestrength);
		scaleEnergy = (BASEENERGY + strength) * radius * radius * radius;
	}

	@Override
	public void onChunkUnloaded() {
		super.onChunkUnloaded();
		matrices.remove(this);
	}
}
