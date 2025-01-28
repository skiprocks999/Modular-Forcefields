package modularforcefields.client;

import electrodynamics.client.guidebook.ScreenGuidebook;
import electrodynamics.client.misc.SWBFClientExtensions;
import electrodynamics.common.fluid.SimpleWaterBasedFluidType;
import modularforcefields.References;
import modularforcefields.client.guidebook.ModuleMFFS;
import modularforcefields.client.screen.ScreenBiometricIdentifier;
import modularforcefields.client.screen.ScreenCoercionDeriver;
import modularforcefields.client.screen.ScreenFortronCapacitor;
import modularforcefields.client.screen.ScreenFortronFieldProjector;
import modularforcefields.client.screen.ScreenInterdictionMatrix;
import modularforcefields.registers.ModularForcefieldsFluids;
import modularforcefields.registers.ModularForcefieldsMenuTypes;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = References.ID, bus = EventBusSubscriber.Bus.MOD, value = { Dist.CLIENT })
public class ClientRegister {

	public static void setup() {

		ScreenGuidebook.addGuidebookModule(new ModuleMFFS());
		
	}

	@SubscribeEvent
	public static void registerScreens(RegisterMenuScreensEvent event) {
		event.register(ModularForcefieldsMenuTypes.CONTAINER_COERCIONDERIVER.get(), ScreenCoercionDeriver::new);
		event.register(ModularForcefieldsMenuTypes.CONTAINER_FORTRONCAPACITOR.get(), ScreenFortronCapacitor::new);
		event.register(ModularForcefieldsMenuTypes.CONTAINER_FORTRONFIELDPROJECTOR.get(), ScreenFortronFieldProjector::new);
		event.register(ModularForcefieldsMenuTypes.CONTAINER_INTERDICTIONMATRIX.get(), ScreenInterdictionMatrix::new);
		event.register(ModularForcefieldsMenuTypes.CONTAINER_BIOMETRICIDENTIFIER.get(), ScreenBiometricIdentifier::new);
	}

	@SubscribeEvent
	public static void registerClientExtensions(RegisterClientExtensionsEvent event) {

		ModularForcefieldsFluids.FLUIDS.getEntries().forEach((fluid) -> {
			event.registerFluidType(new SWBFClientExtensions((SimpleWaterBasedFluidType) fluid.get().getFluidType()), fluid.get().getFluidType());
		});

	}

}
