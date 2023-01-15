package net.fabricmc.example.fluid;

import net.fabricmc.example.MoreFluidsMod;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class PetroleumFluid extends AbstractFluid {

    @Override
    public Fluid getFlowing() {
        return MoreFluidsMod.FLOWING_PETROLEUM;
    }

    @Override
    public Fluid getStill() {
        return MoreFluidsMod.STILL_PETROLEUM;
    }

    @Override
    protected int getFlowSpeed(WorldView world) {
        return 4;
    }

    @Override
    public int getLevel(FluidState state) {
        return 8;
    }

    @Override
    public int getTickRate(WorldView world) {
        return 4;
    }

    @Override
    public Item getBucketItem() {
        return MoreFluidsMod.PETROLEUM_BUCKET;
    }

    @Override
    protected BlockState toBlockState(FluidState state) {
        return MoreFluidsMod.PETROLEUM.getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(state));
    }

    @Override
    public boolean isStill(FluidState state) {
        return false;
    }

    @Override
    protected boolean isInfinite(World world) {
        return true;
    }

    public static class Flowing extends PetroleumFluid {
        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getLevel(FluidState fluidState) {
            return fluidState.get(LEVEL);
        }

        @Override
        public boolean isStill(FluidState fluidState) {
            return false;
        }
    }

    public static class Still extends PetroleumFluid {
        @Override
        public int getLevel(FluidState fluidState) {
            return 8;
        }

        @Override
        public boolean isStill(FluidState fluidState) {
            return true;
        }
    }

}
