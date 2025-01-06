package modularforcefields.client.guidebook;

import electrodynamics.client.guidebook.utils.components.Module;
import electrodynamics.client.guidebook.utils.pagedata.graphics.AbstractGraphicWrapper;
import electrodynamics.client.guidebook.utils.pagedata.graphics.ImageWrapperObject;
import modularforcefields.References;
import modularforcefields.client.guidebook.chapters.ChapterBlocks;
import modularforcefields.client.guidebook.chapters.ChapterItems;
import modularforcefields.client.guidebook.chapters.ChapterModules;
import modularforcefields.prefab.utils.MFFSTextUtils;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;

public class ModuleMFFS extends Module {

    private static final ImageWrapperObject LOGO = new ImageWrapperObject(0, 0, 0, 0, 32, 32, 32, 32, ResourceLocation.fromNamespaceAndPath(References.ID, "textures/screen/guidebook/modularforcefieldslogo.png"));

    @Override
    public AbstractGraphicWrapper<?> getLogo() {
        return LOGO;
    }

    @Override
    public MutableComponent getTitle() {
        return MFFSTextUtils.guidebook(References.ID);
    }

    @Override
    public void addChapters() {

        chapters.add(new ChapterBlocks(this));
        chapters.add(new ChapterModules(this));
        chapters.add(new ChapterItems(this));

    }

}
