package modularforcefields.registers;

import electrodynamics.api.creativetab.CreativeTabSupplier;
import electrodynamics.api.registration.BulkDeferredHolder;
import electrodynamics.common.blockitem.types.BlockItemDescriptable;
import electrodynamics.common.item.ItemElectrodynamics;
import modularforcefields.References;
import modularforcefields.common.block.SubtypeMFFSMachine;
import modularforcefields.common.item.ItemFortronFrequencyCard;
import modularforcefields.common.item.ItemIdentificationCard;
import modularforcefields.common.item.ItemModule;
import modularforcefields.common.item.subtype.SubtypeModule;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;

public class ModularForcefieldsItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, References.ID);

    public static final BulkDeferredHolder<Item, BlockItemDescriptable, SubtypeMFFSMachine> ITEMS_MFFSMACHINE = new BulkDeferredHolder<>(SubtypeMFFSMachine.values(), subtype -> ITEMS.register(subtype.tag(), () -> new BlockItemDescriptable(ModularForcefieldsBlocks.BLOCKS_MFFSMACHINE.getValue(subtype), new Item.Properties(), ModularForcefieldsCreativeTabs.MAIN)));
	public static final BulkDeferredHolder<Item, ItemModule, SubtypeModule> ITEMS_MODULE = new BulkDeferredHolder<>(SubtypeModule.values(), subtype -> ITEMS.register(subtype.tag(), () -> new ItemModule(subtype, new Item.Properties(), ModularForcefieldsCreativeTabs.MAIN)));
    public static final DeferredHolder<Item, Item> ITEM_FOCUSMATRIX = ITEMS.register("focusmatrix", () -> new ItemElectrodynamics(new Item.Properties(), ModularForcefieldsCreativeTabs.MAIN));
    public static final DeferredHolder<Item, Item> ITEM_IDENTIFICATIONCARD = ITEMS.register("identificationcard", () -> new ItemIdentificationCard(new Item.Properties().stacksTo(1)));
    public static final DeferredHolder<Item, Item> ITEM_FREQUENCYCARD = ITEMS.register("frequencycard", () -> new ItemFortronFrequencyCard(new Item.Properties().stacksTo(1)));
	
    @EventBusSubscriber(value = Dist.CLIENT, modid = References.ID, bus = EventBusSubscriber.Bus.MOD)
    private static class MFFSCreativeRegistry {

        @SubscribeEvent
        public static void registerItems(BuildCreativeModeTabContentsEvent event) {

			ITEMS.getEntries().forEach(reg -> {

				CreativeTabSupplier supplier = (CreativeTabSupplier) reg.get();

				if (supplier.hasCreativeTab() && supplier.isAllowedInCreativeTab(event.getTab())) {
					List<ItemStack> toAdd = new ArrayList<>();
					supplier.addCreativeModeItems(event.getTab(), toAdd);
					event.acceptAll(toAdd);
				}

			});

        }

    }
}
