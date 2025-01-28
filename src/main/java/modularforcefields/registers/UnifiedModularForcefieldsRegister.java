package modularforcefields.registers;

import net.neoforged.bus.api.IEventBus;

public class UnifiedModularForcefieldsRegister {

	public static void register(IEventBus bus) {
		ModularForcefieldsBlocks.BLOCKS.register(bus);
		ModularForcefieldsItems.ITEMS.register(bus);
		ModularForcefieldsDataComponentTypes.DATA_COMPONENT_TYPES.register(bus);
		ModularForcefieldsTiles.BLOCK_ENTITY_TYPES.register(bus);
		ModularForcefieldsMenuTypes.MENU_TYPES.register(bus);
		ModularForcefieldsFluids.FLUIDS.register(bus);
		ModularForcefieldsFluidTypes.FLUID_TYPES.register(bus);
		ModularForcefieldsSounds.SOUNDS.register(bus);
		ModularForcefieldsCreativeTabs.CREATIVE_TABS.register(bus);
	}

}
