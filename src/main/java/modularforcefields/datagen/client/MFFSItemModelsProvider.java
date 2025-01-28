package modularforcefields.datagen.client;

import electrodynamics.datagen.client.ElectrodynamicsItemModelsProvider;
import modularforcefields.References;
import modularforcefields.registers.ModularForcefieldsItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
public class MFFSItemModelsProvider extends ElectrodynamicsItemModelsProvider {

	public MFFSItemModelsProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, existingFileHelper, References.ID);
	}

	@Override
	protected void registerModels() {

		layeredItem(ModularForcefieldsItems.ITEM_IDENTIFICATIONCARD, Parent.GENERATED, itemLoc("identificationcard"));
		layeredItem(ModularForcefieldsItems.ITEM_FREQUENCYCARD, Parent.GENERATED, itemLoc("frequencycard"));
		layeredItem(ModularForcefieldsItems.ITEM_FOCUSMATRIX, Parent.GENERATED, itemLoc("focusmatrix"));

		for(Item item : ModularForcefieldsItems.ITEMS_MODULE.getAllValues()) {
			layeredItem(item, Parent.GENERATED, itemLoc("module/" + name(item)));
		}

	}

}
