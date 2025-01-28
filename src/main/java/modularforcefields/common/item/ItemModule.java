package modularforcefields.common.item;

import electrodynamics.common.item.ItemElectrodynamics;
import modularforcefields.common.item.subtype.SubtypeModule;
import net.minecraft.core.Holder;
import net.minecraft.world.item.CreativeModeTab;

public class ItemModule extends ItemElectrodynamics {

    public final SubtypeModule subtype;

    public ItemModule(SubtypeModule subtype, Properties properties, Holder<CreativeModeTab> creativeTab) {
        super(properties, creativeTab);
        this.subtype = subtype;
    }
}
