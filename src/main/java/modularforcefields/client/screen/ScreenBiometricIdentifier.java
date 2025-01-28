package modularforcefields.client.screen;

import electrodynamics.prefab.screen.GenericScreen;
import modularforcefields.common.inventory.container.ContainerBiometricIdentifier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ScreenBiometricIdentifier extends GenericScreen<ContainerBiometricIdentifier> {
	public ScreenBiometricIdentifier(ContainerBiometricIdentifier container, Inventory playerInventory, Component title) {
		super(container, playerInventory, title);
	}
}