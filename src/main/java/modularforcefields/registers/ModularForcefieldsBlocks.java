package modularforcefields.registers;

import electrodynamics.api.registration.BulkDeferredHolder;
import electrodynamics.common.block.BlockMachine;
import modularforcefields.References;
import modularforcefields.common.block.BlockFortronField;
import modularforcefields.common.block.SubtypeMFFSMachine;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModularForcefieldsBlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, References.ID);

	public static final BulkDeferredHolder<Block, BlockMachine, SubtypeMFFSMachine> BLOCKS_MFFSMACHINE = new BulkDeferredHolder<>(SubtypeMFFSMachine.values(), subtype -> BLOCKS.register(subtype.tag(), () -> new BlockMachine(subtype)));
	public static final DeferredHolder<Block, BlockFortronField> BLOCK_FORTRONFIELD = BLOCKS.register("fortronfield", BlockFortronField::new);

}
