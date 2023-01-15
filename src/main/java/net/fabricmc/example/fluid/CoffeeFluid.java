package net.fabricmc.example.fluid;

import net.fabricmc.example.MoreFluidsMod;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.world.WorldView;

public class CoffeeFluid extends AbstractFluid {
    @Override
    public Fluid getFlowing() {
        return MoreFluidsMod.FLOWING_COFFEE;
    }

    @Override
    public Fluid getStill() {
        return MoreFluidsMod.STILL_COFFEE;
    }

    @Override
    protected int getFlowSpeed(WorldView world) {
        return 8;
    }

    @Override
    public Item getBucketItem() {
        return MoreFluidsMod.COFFEE_BUCKET;
    }

    @Override
    protected BlockState toBlockState(FluidState state) {
        return MoreFluidsMod.COFFEE.getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(state));
    }

    @Override
    public boolean isStill(FluidState state) {
        return false;
    }

    @Override
    public int getLevel(FluidState state) {
        return 0;
    }


    public static class Flowing extends CoffeeFluid {
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

    public static class Still extends CoffeeFluid {
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
