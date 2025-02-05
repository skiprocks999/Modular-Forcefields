package modularforcefields.common.tile;

import electrodynamics.prefab.properties.Property;
import electrodynamics.prefab.properties.PropertyTypes;
import electrodynamics.prefab.tile.GenericTile;
import electrodynamics.prefab.tile.components.type.ComponentPacketHandler;
import electrodynamics.prefab.utilities.BlockEntityUtils;
import electrodynamics.prefab.utilities.Scheduler;
import modularforcefields.common.block.FortronFieldColor;
import modularforcefields.registers.ModularForcefieldsTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TileFortronField extends GenericTile {

    public final Property<Integer> fieldColorOrdinal = property(new Property<>(PropertyTypes.INTEGER, "fieldColor", FortronFieldColor.LIGHT_BLUE.ordinal()));
    private final Property<BlockPos> projectorPos = property(new Property<>(PropertyTypes.BLOCK_POS, "projectorPos", BlockEntityUtils.OUT_OF_REACH).onChange(this::onPropertyChange).onTileLoaded(prop -> onPropertyChange(prop, prop.get())));

    private void onPropertyChange(Property<BlockPos> t, BlockPos pos) {

        if (level == null || pos == null) {
            return;
        }

        Scheduler.schedule(3, () -> {

            if (level.getBlockEntity(pos) instanceof TileFortronFieldProjector proj) {
                if (!level.isClientSide()) {
                    fieldColorOrdinal.set(proj.getFieldColor().ordinal());
                    proj.activeFields.add(this);
                }
            }

        });
    }

    public TileFortronField(BlockPos pos, BlockState state) {
        super(ModularForcefieldsTiles.TILE_FORTRONFIELD.get(), pos, state);
        addComponent(new ComponentPacketHandler(this));
    }

    public void setConstructor(TileFortronFieldProjector projector) {
        if (!level.isClientSide()) {
            if (projector != null) {
                projectorPos.set(projector.getBlockPos());
            }
        }
    }

    public FortronFieldColor getFieldColor() {
        return FortronFieldColor.values()[fieldColorOrdinal.get()];
    }

    public BlockPos getProjectorPos() {
        return projectorPos.get();
    }
}