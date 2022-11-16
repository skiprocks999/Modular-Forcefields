package modularforcefields.common.tile;

import java.util.HashSet;
import java.util.Map.Entry;
import java.util.function.Predicate;

import com.google.common.collect.Sets;

import electrodynamics.api.ISubtype;
import electrodynamics.prefab.properties.Property;
import electrodynamics.prefab.properties.PropertyType;
import electrodynamics.prefab.tile.components.ComponentType;
import electrodynamics.prefab.tile.components.type.ComponentContainerProvider;
import electrodynamics.prefab.tile.components.type.ComponentDirection;
import electrodynamics.prefab.tile.components.type.ComponentElectrodynamic;
import electrodynamics.prefab.tile.components.type.ComponentInventory;
import electrodynamics.prefab.tile.components.type.ComponentPacketHandler;
import electrodynamics.prefab.tile.components.type.ComponentTickable;
import electrodynamics.prefab.utilities.object.TransferPack;
import modularforcefields.common.inventory.container.ContainerCoercionDeriver;
import modularforcefields.common.item.subtype.SubtypeModule;
import modularforcefields.common.settings.Constants;
import modularforcefields.registers.ModularForcefieldsBlockTypes;
import modularforcefields.registers.ModularForcefieldsItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;

public class TileCoercionDeriver extends TileFortronConnective {
	public static final HashSet<SubtypeModule> VALIDMODULES = Sets.newHashSet(SubtypeModule.upgradespeed, SubtypeModule.upgradecapacity);
	public static final int BASEENERGY = 50;
	public Property<Integer> fortron = property(new Property<Integer>(PropertyType.Integer, "fortron")).set(0).save();
	public Property<Integer> fortronCapacity = property(new Property<Integer>(PropertyType.Integer, "fortronCapacity")).set(0).save();

	public TileCoercionDeriver(BlockPos pos, BlockState state) {
		super(ModularForcefieldsBlockTypes.TILE_COERCIONDERIVER.get(), pos, state);
		addComponent(new ComponentDirection());
		addComponent(new ComponentPacketHandler());
		addComponent(new ComponentElectrodynamic(this).voltage(Constants.COERCIONDERIVER_VOLTAGE).input(Direction.DOWN).output(Direction.DOWN));
		addComponent(new ComponentInventory(this).size(4).shouldSendInfo().valid((index, stack, inv) -> {
			for (Entry<ISubtype, RegistryObject<Item>> en : ModularForcefieldsItems.SUBTYPEITEMREGISTER_MAPPINGS.entrySet()) {
				if (VALIDMODULES.contains(en.getKey())) {
					if (en.getValue().get() == stack.getItem()) {
						return true;
					}
				}
			}
			return false;

		}));
		addComponent(new ComponentContainerProvider("container.coercionderiver").createMenu((id, player) -> new ContainerCoercionDeriver(id, player, getComponent(ComponentType.Inventory), getCoordsArray())));
	}

	@Override
	protected void tickServer(ComponentTickable tickable) {
		super.tickServer(tickable);
		ComponentElectrodynamic electro = getComponent(ComponentType.Electrodynamic);
		ComponentPacketHandler packets = getComponent(ComponentType.PacketHandler);
		if (tickable.getTicks() % 20 == 0) {
			int max = getMaxStored();
			electro.maxJoules(max);
			fortron.set(Mth.clamp(fortron.get(), 0, max));
			fortronCapacity.set(max);
			packets.sendGuiPacketToTracking();
		}
		fortron.set((int) (fortron.get() + electro.extractPower(TransferPack.joulesVoltage(Math.min(getTransfer(), fortronCapacity.get() - fortron.get()), electro.getVoltage()), false).getJoules()));
		fortron.set(fortron.get() - sendFortronTo(Math.min(fortron.get(), getTransfer()), getConnectionTest()));

	}

	private int getMaxStored() {
		return (int) (getTransfer() + BASEENERGY * countModules(SubtypeModule.upgradecapacity) * 2.0);
	}

	public int getTransfer() {
		return BASEENERGY * 30 + BASEENERGY * countModules(SubtypeModule.upgradespeed);
	}

	@Override
	protected Predicate<BlockEntity> getConnectionTest() {
		return b -> b.getType() == ModularForcefieldsBlockTypes.TILE_FORTRONCAPACITOR.get();
	}
}
