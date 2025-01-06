package modularforcefields.client.guidebook.chapters;

import electrodynamics.client.guidebook.utils.components.Chapter;
import electrodynamics.client.guidebook.utils.components.Module;
import electrodynamics.client.guidebook.utils.pagedata.graphics.AbstractGraphicWrapper;
import electrodynamics.client.guidebook.utils.pagedata.graphics.ItemWrapperObject;
import modularforcefields.common.item.subtype.SubtypeModule;
import modularforcefields.prefab.utils.MFFSTextUtils;
import modularforcefields.registers.ModularForcefieldsItems;
import net.minecraft.network.chat.MutableComponent;

public class ChapterModules extends Chapter {

    private static final ItemWrapperObject LOGO = new ItemWrapperObject(7, 10, 32, 32, 32, 2.0F, ModularForcefieldsItems.ITEMS_MODULE.getValue(SubtypeModule.shapecube));

    public ChapterModules(Module module) {
        super(module);
    }

    @Override
    public AbstractGraphicWrapper<?> getLogo() {
        return LOGO;
    }

    @Override
    public MutableComponent getTitle() {
        return MFFSTextUtils.guidebook("chapter.modules");
    }

    @Override
    public void addData() {

    }
}
