package modularforcefields.datagen.server.tags.types;

import electrodynamics.common.block.BlockMachine;
import modularforcefields.References;
import modularforcefields.registers.ModularForcefieldsBlocks;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class MFFSBlockTagsProvider extends BlockTagsProvider {

    public MFFSBlockTagsProvider(PackOutput output, CompletableFuture<Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, References.ID, existingFileHelper);
    }

    @Override
    protected void addTags(Provider provider) {

        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModularForcefieldsBlocks.BLOCKS_MFFSMACHINE.getAllValuesArray(new BlockMachine[0]));

        tag(BlockTags.NEEDS_STONE_TOOL).add(ModularForcefieldsBlocks.BLOCKS_MFFSMACHINE.getAllValuesArray(new BlockMachine[0]));

    }

}
