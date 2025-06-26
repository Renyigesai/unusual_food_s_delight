package com.renyigesai.unusualfoodsdelight.init;

import com.renyigesai.unusualfoodsdelight.UnusualFoodDelight;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class UdParticleTypes {
    public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, UnusualFoodDelight.MODID);
    public static final RegistryObject<SimpleParticleType> SMOKED_MEAT_CAMPFIRE = REGISTRY.register("smoked_meat_campfire", () -> new SimpleParticleType(true));

}
