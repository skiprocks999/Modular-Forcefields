package modularforcefields.datagen.server.recipe.vanilla;

import electrodynamics.common.block.subtype.SubtypeWire;
import electrodynamics.common.tags.ElectrodynamicsTags;
import electrodynamics.datagen.utils.recipe.AbstractRecipeGenerator;
import electrodynamics.datagen.utils.recipe.ShapedCraftingRecipeBuilder;
import electrodynamics.datagen.utils.recipe.ShapelessCraftingRecipeBuilder;
import electrodynamics.registers.ElectrodynamicsItems;
import modularforcefields.References;
import modularforcefields.common.block.SubtypeMFFSMachine;
import modularforcefields.common.item.subtype.SubtypeModule;
import modularforcefields.registers.ModularForcefieldsItems;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;

public class MFFSCraftingTableRecipes extends AbstractRecipeGenerator {

	@Override
	public void addRecipes(RecipeOutput output) {

		ShapedCraftingRecipeBuilder.start(ModularForcefieldsItems.ITEM_FOCUSMATRIX.get(), 8)
				//
				.addPattern("R#R")
				//
				.addPattern("#D#")
				//
				.addPattern("R#R")
				//
				.addKey('R', Tags.Items.DUSTS_REDSTONE)
				//
				.addKey('#', ElectrodynamicsTags.Items.PLATE_STEEL)
				//
				.addKey('D', Tags.Items.GEMS_DIAMOND)
				//
				.complete(References.ID, "focus_matrix", output);

		ShapelessCraftingRecipeBuilder.start(ModularForcefieldsItems.ITEM_FREQUENCYCARD.get(), 1)
				//
				.addIngredient(ElectrodynamicsItems.ITEMS_WIRE.getValue(SubtypeWire.copper))
				//
				.addIngredient(Items.PAPER)
				//
				.complete(References.ID, "frequency_card", output);

		ShapelessCraftingRecipeBuilder.start(ModularForcefieldsItems.ITEM_IDENTIFICATIONCARD.get(), 1)
				//
				.addIngredient(Tags.Items.DUSTS_REDSTONE)
				//
				.addIngredient(Items.PAPER)
				//
				.complete(References.ID, "identification_card", output);

		ShapedCraftingRecipeBuilder.start(ModularForcefieldsItems.ITEMS_MODULE.getValue(SubtypeModule.manipulationscale), 2)
				//
				.addPattern("F F")
				//
				.addKey('F', ModularForcefieldsItems.ITEM_FOCUSMATRIX.get())
				//
				.complete(References.ID, "module_manipulation_scale", output);

		ShapedCraftingRecipeBuilder.start(ModularForcefieldsItems.ITEMS_MODULE.getValue(SubtypeModule.manipulationtranslate), 2)
				//
				.addPattern("FSF")
				//
				.addKey('F', ModularForcefieldsItems.ITEM_FOCUSMATRIX.get())
				//
				.addKey('S', ModularForcefieldsItems.ITEMS_MODULE.getValue(SubtypeModule.manipulationscale))
				//
				.complete(References.ID, "module_manipulation_translate", output);

		ShapedCraftingRecipeBuilder.start(ModularForcefieldsItems.ITEMS_MODULE.getValue(SubtypeModule.shapecube), 1)
				//
				.addPattern("FFF")
				//
				.addPattern("FFF")
				//
				.addPattern("FFF")
				//
				.addKey('F', ModularForcefieldsItems.ITEM_FOCUSMATRIX.get())
				//
				.complete(References.ID, "module_shape_cube", output);

		ShapedCraftingRecipeBuilder.start(ModularForcefieldsItems.ITEMS_MODULE.getValue(SubtypeModule.shapehemisphere), 1)
				//
				.addPattern("FFF")
				//
				.addPattern("F F")
				//
				.addKey('F', ModularForcefieldsItems.ITEM_FOCUSMATRIX.get())
				//
				.complete(References.ID, "module_shape_hemisphere", output);

		ShapedCraftingRecipeBuilder.start(ModularForcefieldsItems.ITEMS_MODULE.getValue(SubtypeModule.shapepyramid), 1)
				//
				.addPattern("F  ")
				//
				.addPattern("FF ")
				//
				.addPattern("FFF")
				//
				.addKey('F', ModularForcefieldsItems.ITEM_FOCUSMATRIX.get())
				//
				.complete(References.ID, "module_shape_pyramid", output);

		ShapedCraftingRecipeBuilder.start(ModularForcefieldsItems.ITEMS_MODULE.getValue(SubtypeModule.shapesphere), 1)
				//
				.addPattern(" F ")
				//
				.addPattern("FFF")
				//
				.addPattern(" F ")
				//
				.addKey('F', ModularForcefieldsItems.ITEM_FOCUSMATRIX.get())
				//
				.complete(References.ID, "module_shape_sphere", output);

		ShapedCraftingRecipeBuilder.start(ModularForcefieldsItems.ITEMS_MODULE.getValue(SubtypeModule.upgradeantifriendly), 1)
				//
				.addPattern(" W ")
				//
				.addPattern("PFL")
				//
				.addPattern(" S ")
				//
				.addKey('F', ModularForcefieldsItems.ITEM_FOCUSMATRIX.get())
				//
				.addKey('W', ItemTags.WOOL)
				//
				.addKey('P', Items.COOKED_PORKCHOP)
				//
				.addKey('L', Tags.Items.LEATHERS)
				//
				.addKey('S', Tags.Items.SLIME_BALLS)
				//
				.complete(References.ID, "module_upgrade_antifriendly", output);

		ShapedCraftingRecipeBuilder.start(ModularForcefieldsItems.ITEMS_MODULE.getValue(SubtypeModule.upgradeantihostile), 1)
				//
				.addPattern(" R ")
				//
				.addPattern("PFB")
				//
				.addPattern(" G ")
				//
				.addKey('F', ModularForcefieldsItems.ITEM_FOCUSMATRIX.get())
				//
				.addKey('R', Items.ROTTEN_FLESH)
				//
				.addKey('P', Tags.Items.GUNPOWDERS)
				//
				.addKey('B', Tags.Items.BONES)
				//
				.addKey('G', Items.GHAST_TEAR)
				//
				.complete(References.ID, "module_upgrade_antihostile", output);

		ShapedCraftingRecipeBuilder.start(ModularForcefieldsItems.ITEMS_MODULE.getValue(SubtypeModule.upgradeantipersonnel), 1)
				//
				.addPattern("HFA")
				//
				.addKey('F', ModularForcefieldsItems.ITEM_FOCUSMATRIX.get())
				//
				.addKey('H', ModularForcefieldsItems.ITEMS_MODULE.getValue(SubtypeModule.upgradeantihostile))
				//
				.addKey('A', ModularForcefieldsItems.ITEMS_MODULE.getValue(SubtypeModule.upgradeantifriendly))
				//
				.complete(References.ID, "module_upgrade_antipersonnel", output);

		ShapedCraftingRecipeBuilder.start(ModularForcefieldsItems.ITEMS_MODULE.getValue(SubtypeModule.upgradeantispawn), 1)
				//
				.addPattern(" H ")
				//
				.addPattern("H A")
				//
				.addPattern(" A ")
				//
				.addKey('H', ModularForcefieldsItems.ITEMS_MODULE.getValue(SubtypeModule.upgradeantihostile))
				//
				.addKey('A', ModularForcefieldsItems.ITEMS_MODULE.getValue(SubtypeModule.upgradeantifriendly))
				//
				.complete(References.ID, "module_upgrade_antispawn", output);

		ShapedCraftingRecipeBuilder.start(ModularForcefieldsItems.ITEMS_MODULE.getValue(SubtypeModule.upgradeblockaccess), 1)
				//
				.addPattern(" C ")
				//
				.addPattern("BFB")
				//
				.addPattern(" C ")
				//
				.addKey('F', ModularForcefieldsItems.ITEM_FOCUSMATRIX.get())
				//
				.addKey('C', Tags.Items.CHESTS)
				//
				.addKey('B', Tags.Items.STORAGE_BLOCKS_IRON)
				//
				.complete(References.ID, "module_upgrade_blockaccess", output);

		ShapedCraftingRecipeBuilder.start(ModularForcefieldsItems.ITEMS_MODULE.getValue(SubtypeModule.upgradeblockalter), 1)
				//
				.addPattern(" B ")
				//
				.addPattern("BFB")
				//
				.addPattern(" B ")
				//
				.addKey('F', ModularForcefieldsItems.ITEM_FOCUSMATRIX.get())
				//
				.addKey('B', Tags.Items.STORAGE_BLOCKS_GOLD)
				//
				.complete(References.ID, "module_upgrade_blockalter", output);

		ShapedCraftingRecipeBuilder.start(ModularForcefieldsItems.ITEMS_MODULE.getValue(SubtypeModule.upgradecapacity), 1)
				//
				.addPattern("RFR")
				//
				.addKey('F', ModularForcefieldsItems.ITEM_FOCUSMATRIX.get())
				//
				.addKey('R', Items.REPEATER)
				//
				.complete(References.ID, "module_upgrade_capacity", output);

		ShapedCraftingRecipeBuilder.start(ModularForcefieldsItems.ITEMS_MODULE.getValue(SubtypeModule.upgradecollection), 1)
				//
				.addPattern("H H")
				//
				.addPattern(" F ")
				//
				.addPattern("H H")
				//
				.addKey('F', ModularForcefieldsItems.ITEM_FOCUSMATRIX.get())
				//
				.addKey('H', Items.HOPPER)
				//
				.complete(References.ID, "module_upgrade_collection", output);

		ShapedCraftingRecipeBuilder.start(ModularForcefieldsItems.ITEMS_MODULE.getValue(SubtypeModule.upgradecolorchange), 1)
				//
				.addPattern("DDD")
				//
				.addPattern("DFD")
				//
				.addPattern("DDD")
				//
				.addKey('F', ModularForcefieldsItems.ITEM_FOCUSMATRIX.get())
				//
				.addKey('D', Tags.Items.DUSTS_GLOWSTONE)
				//
				.complete(References.ID, "module_upgrade_colorchange", output);

		ShapedCraftingRecipeBuilder.start(ModularForcefieldsItems.ITEMS_MODULE.getValue(SubtypeModule.upgradeconfiscate), 1)
				//
				.addPattern("EYE")
				//
				.addPattern("YFY")
				//
				.addPattern("EYE")
				//
				.addKey('F', ModularForcefieldsItems.ITEM_FOCUSMATRIX.get())
				//
				.addKey('E', Tags.Items.ENDER_PEARLS)
				//
				.addKey('Y', Items.ENDER_EYE)
				//
				.complete(References.ID, "module_upgrade_confiscate", output);

		ShapedCraftingRecipeBuilder.start(ModularForcefieldsItems.ITEMS_MODULE.getValue(SubtypeModule.upgradedisintegration), 1)
				//
				.addPattern(" D ")
				//
				.addPattern("RFR")
				//
				.addPattern(" D ")
				//
				.addKey('F', ModularForcefieldsItems.ITEM_FOCUSMATRIX.get())
				//
				.addKey('D', Tags.Items.DUSTS_REDSTONE)
				//
				.addKey('R', Items.REPEATER)
				//
				.complete(References.ID, "module_upgrade_disintegration", output);

		ShapedCraftingRecipeBuilder.start(ModularForcefieldsItems.ITEMS_MODULE.getValue(SubtypeModule.upgradeinterior), 1)
				//
				.addPattern("B")
				//
				.addPattern("F")
				//
				.addPattern("B")
				//
				.addKey('F', ModularForcefieldsItems.ITEM_FOCUSMATRIX.get())
				//
				.addKey('B', Tags.Items.STORAGE_BLOCKS_LAPIS)
				//
				.complete(References.ID, "module_upgrade_interior", output);

		ShapedCraftingRecipeBuilder.start(ModularForcefieldsItems.ITEMS_MODULE.getValue(SubtypeModule.upgradeshock), 1)
				//
				.addPattern("DFD")
				//
				.addKey('F', ModularForcefieldsItems.ITEM_FOCUSMATRIX.get())
				//
				.addKey('D', Tags.Items.DUSTS_REDSTONE)
				//
				.complete(References.ID, "module_upgrade_shock", output);

		ShapedCraftingRecipeBuilder.start(ModularForcefieldsItems.ITEMS_MODULE.getValue(SubtypeModule.upgradespeed), 1)
				//
				.addPattern("DDD")
				//
				.addPattern("DFD")
				//
				.addPattern("DDD")
				//
				.addKey('F', ModularForcefieldsItems.ITEM_FOCUSMATRIX.get())
				//
				.addKey('D', Tags.Items.DUSTS_REDSTONE)
				//
				.complete(References.ID, "module_upgrade_speed", output);

		ShapedCraftingRecipeBuilder.start(ModularForcefieldsItems.ITEMS_MODULE.getValue(SubtypeModule.upgradesponge), 1)
				//
				.addPattern("BBB")
				//
				.addPattern("BFB")
				//
				.addPattern("BBB")
				//
				.addKey('F', ModularForcefieldsItems.ITEM_FOCUSMATRIX.get())
				//
				.addKey('B', Tags.Items.BUCKETS_EMPTY)
				//
				.complete(References.ID, "module_upgrade_sponge", output);

		ShapedCraftingRecipeBuilder.start(ModularForcefieldsItems.ITEMS_MODULE.getValue(SubtypeModule.upgradestabilize), 1)
				//
				.addPattern("FGF")
				//
				.addPattern("PSA")
				//
				.addPattern("FGF")
				//
				.addKey('F', ModularForcefieldsItems.ITEM_FOCUSMATRIX.get())
				//
				.addKey('G', Tags.Items.GEMS_DIAMOND)
				//
				.addKey('P', Items.DIAMOND_PICKAXE)
				//
				.addKey('S', Items.DIAMOND_SHOVEL)
				//
				.addKey('A', Items.DIAMOND_AXE)
				//
				.complete(References.ID, "module_upgrade_stabilize", output);

		ShapedCraftingRecipeBuilder.start(ModularForcefieldsItems.ITEMS_MODULE.getValue(SubtypeModule.upgradestrength), 1)
				//
				.addPattern("SFS")
				//
				.addKey('F', ModularForcefieldsItems.ITEM_FOCUSMATRIX.get())
				//
				.addKey('S', ModularForcefieldsItems.ITEMS_MODULE.getValue(SubtypeModule.upgradeshock))
				//
				.complete(References.ID, "module_upgrade_strength", output);

		addMachines(output);

	}

	public void addMachines(RecipeOutput output) {

		ShapedCraftingRecipeBuilder.start(ModularForcefieldsItems.ITEMS_MFFSMACHINE.getValue(SubtypeMFFSMachine.biometricidentifier), 1)
				//
				.addPattern("FPF")
				//
				.addPattern("P#P")
				//
				.addPattern("FPF")
				//
				.addKey('F', ModularForcefieldsItems.ITEM_FOCUSMATRIX.get())
				//
				.addKey('P', ElectrodynamicsTags.Items.PLATE_STEEL)
				//
				.addKey('#', Items.PAPER)
				//
				.complete(References.ID, "machine_biometricidentifier", output);

		ShapedCraftingRecipeBuilder.start(ModularForcefieldsItems.ITEMS_MFFSMACHINE.getValue(SubtypeMFFSMachine.coercionderiver), 1)
				//
				.addPattern("FPF")
				//
				.addPattern("FRF")
				//
				.addPattern("FPF")
				//
				.addKey('F', ModularForcefieldsItems.ITEM_FOCUSMATRIX.get())
				//
				.addKey('P', ElectrodynamicsTags.Items.PLATE_STEEL)
				//
				.addKey('R', Items.REPEATER)
				//
				.complete(References.ID, "machine_coercionderiver", output);

		ShapedCraftingRecipeBuilder.start(ModularForcefieldsItems.ITEMS_MFFSMACHINE.getValue(SubtypeMFFSMachine.fortroncapacitor), 1)
				//
				.addPattern("PFP")
				//
				.addPattern("PRP")
				//
				.addPattern("PFP")
				//
				.addKey('F', ModularForcefieldsItems.ITEM_FOCUSMATRIX.get())
				//
				.addKey('P', ElectrodynamicsTags.Items.PLATE_STEEL)
				//
				.addKey('R', Items.REPEATER)
				//
				.complete(References.ID, "machine_fortroncapacitor", output);

		ShapedCraftingRecipeBuilder.start(ModularForcefieldsItems.ITEMS_MFFSMACHINE.getValue(SubtypeMFFSMachine.fortronfieldprojector), 1)
				//
				.addPattern(" D ")
				//
				.addPattern("FFF")
				//
				.addPattern("PRP")
				//
				.addKey('D', Tags.Items.GEMS_DIAMOND)
				//
				.addKey('F', ModularForcefieldsItems.ITEM_FOCUSMATRIX.get())
				//
				.addKey('P', ElectrodynamicsTags.Items.PLATE_STEEL)
				//
				.addKey('R', Items.REPEATER)
				//
				.complete(References.ID, "machine_fortronfieldprojector", output);

		ShapedCraftingRecipeBuilder.start(ModularForcefieldsItems.ITEMS_MFFSMACHINE.getValue(SubtypeMFFSMachine.interdictionmatrix), 1)
				//
				.addPattern("###")
				//
				.addPattern("FFF")
				//
				.addPattern("FCF")
				//
				.addKey('F', ModularForcefieldsItems.ITEM_FOCUSMATRIX.get())
				//
				.addKey('#', ModularForcefieldsItems.ITEMS_MODULE.getValue(SubtypeModule.upgradeshock))
				//
				.addKey('C', Tags.Items.CHESTS_ENDER)
				//
				.complete(References.ID, "machine_interdictionmatrix", output);


	}

}
