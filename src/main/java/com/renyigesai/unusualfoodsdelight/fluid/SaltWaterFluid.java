
package com.renyigesai.unusualfoodsdelight.fluid;

import com.renyigesai.unusualfoodsdelight.init.UdBlocks;
import com.renyigesai.unusualfoodsdelight.init.UdItems;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import org.jetbrains.annotations.NotNull;

public abstract class SaltWaterFluid extends ForgeFlowingFluid {
    public static final Properties PROPERTIES = new Properties(UdFluidTypes.SALT_WATER_TYPE, UdFluids.SALT_WATER,
            UdFluids.FLOWING_SALT_WATER).explosionResistance(100f).bucket(UdItems.WELL_SALT_WATER_BUCKET)
            .block(UdBlocks.SALT_WATER_BLOCK);

    private SaltWaterFluid() {
        super(PROPERTIES);
    }

    public static class Source extends SaltWaterFluid {
        public int getAmount(@NotNull FluidState state) {
            return 8;
        }

        public boolean isSource(@NotNull FluidState state) {
            return true;
        }
    }

    public static class Flowing extends SaltWaterFluid {
        protected void createFluidStateDefinition(StateDefinition.@NotNull Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        public int getAmount(FluidState state) {
            return state.getValue(LEVEL);
        }

        public boolean isSource(@NotNull FluidState state) {
            return false;
        }
    }

}
