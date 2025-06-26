package com.renyigesai.unusualfoodsdelight.fluid;

import com.renyigesai.unusualfoodsdelight.UnusualFoodDelight;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class UdFluidTypes {
    public static final DeferredRegister<FluidType> REGISTRY = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, UnusualFoodDelight.MODID);
    public static final RegistryObject<FluidType> SALT_WATER_TYPE = REGISTRY.register("salt_water", () -> new SaltWaterFluidType());
}
