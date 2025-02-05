package modularforcefields.datagen.server;

import java.util.List;

import electrodynamics.datagen.server.ElectrodynamicsLootTablesProvider;
import modularforcefields.References;
import modularforcefields.common.block.SubtypeMFFSMachine;
import modularforcefields.registers.ModularForcefieldsBlocks;
import modularforcefields.registers.ModularForcefieldsTiles;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Block;

public class MFFSLootTablesProvider extends ElectrodynamicsLootTablesProvider {

	public MFFSLootTablesProvider(HolderLookup.Provider provider) {
		super(References.ID, provider);
	}

	@Override
	protected void generate() {

		addMachineTable(ModularForcefieldsBlocks.BLOCKS_MFFSMACHINE.getValue(SubtypeMFFSMachine.biometricidentifier), ModularForcefieldsTiles.TILE_BIOMETRICIDENTIFIER, true, false, false, true, true);
		addMachineTable(ModularForcefieldsBlocks.BLOCKS_MFFSMACHINE.getValue(SubtypeMFFSMachine.coercionderiver), ModularForcefieldsTiles.TILE_COERCIONDERIVER, true, false, false, true, true);
		addMachineTable(ModularForcefieldsBlocks.BLOCKS_MFFSMACHINE.getValue(SubtypeMFFSMachine.fortroncapacitor), ModularForcefieldsTiles.TILE_FORTRONCAPACITOR, true, false, false, true, true);
		addMachineTable(ModularForcefieldsBlocks.BLOCKS_MFFSMACHINE.getValue(SubtypeMFFSMachine.fortronfieldprojector), ModularForcefieldsTiles.TILE_FORTRONFIELDPROJECTOR, true, false, false, true, true);
		addMachineTable(ModularForcefieldsBlocks.BLOCKS_MFFSMACHINE.getValue(SubtypeMFFSMachine.interdictionmatrix), ModularForcefieldsTiles.TILE_INTERDICTIONMATRIX, true, false, false, true, true);

	}

	@Override
	public List<Block> getExcludedBlocks() {
		return List.of(ModularForcefieldsBlocks.BLOCK_FORTRONFIELD.get());
	}

}
