package modularforcefields;

import electrodynamics.prefab.configuration.ConfigurationHandler;
import modularforcefields.client.ClientRegister;
import modularforcefields.common.block.BlockColorFortronField;
import modularforcefields.common.packet.NetworkHandler;
import modularforcefields.common.settings.Constants;
import modularforcefields.common.tags.MFFTags;
import modularforcefields.registers.ModularForcefieldsBlocks;
import modularforcefields.registers.UnifiedModularForcefieldsRegister;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@Mod(References.ID)
@EventBusSubscriber(modid = References.ID, bus = EventBusSubscriber.Bus.MOD)
public class ModularForcefields {

	public ModularForcefields(IEventBus bus) {
		ConfigurationHandler.registerConfig(Constants.class);
		UnifiedModularForcefieldsRegister.register(bus);
		MFFTags.init();
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void onClientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			ClientRegister.setup();
		});
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void onColorEvent(RegisterColorHandlersEvent.Block event) {
		event.register(new BlockColorFortronField(), ModularForcefieldsBlocks.BLOCK_FORTRONFIELD.get());
	}

	@SubscribeEvent
	public static void onCommonSetup(FMLCommonSetupEvent event) {
		NetworkHandler.init();
	}
}
