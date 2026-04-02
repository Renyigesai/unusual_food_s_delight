package com.renyigesai.unusualfoodsdelight.init;

import com.renyigesai.unusualfoodsdelight.UnusualFoodDelight;
import com.renyigesai.unusualfoodsdelight.potion.BulgingWithAngerMobEffect;
import com.renyigesai.unusualfoodsdelight.potion.CollocationMobEffect;
import com.renyigesai.unusualfoodsdelight.potion.InstantShadowMobEffect;
import com.renyigesai.unusualfoodsdelight.potion.SalinityMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class UdMobEffects {
    public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, UnusualFoodDelight.MODID);

    public static final RegistryObject<MobEffect> SALINITY = REGISTRY.register("salinity", SalinityMobEffect::new);
    public static final RegistryObject<MobEffect> COLLOCATION = REGISTRY.register("collocation", CollocationMobEffect::new);
    public static final RegistryObject<MobEffect> BULGING_WITH_ANGER = REGISTRY.register("bulging_with_anger", BulgingWithAngerMobEffect::new);
    public static final RegistryObject<MobEffect> INSTANT_SHADOW = REGISTRY.register("instant_shadow", InstantShadowMobEffect::new);
}
