package com.renyigesai.unusualfoodsdelight.init;

import com.renyigesai.unusualfoodsdelight.UnusualFoodDelight;
import com.renyigesai.unusualfoodsdelight.potion.BulgingWithAngerMobEffect;
import com.renyigesai.unusualfoodsdelight.potion.CollocationMobEffect;
import com.renyigesai.unusualfoodsdelight.potion.SalinityMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class UdMobEffects {
    public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, UnusualFoodDelight.MODID);

    public static final RegistryObject<MobEffect> SALINITY = REGISTRY.register("salinity", SalinityMobEffect::new);
    public static final RegistryObject<MobEffect> COLLOCATION = REGISTRY.register("collocation", CollocationMobEffect::new);
    public static final RegistryObject<MobEffect> BULGING_WITH_ANGER = REGISTRY.register("bulging_with_anger", /*()->
            new BulgingWithAngerMobEffect().addAttributeModifier(Attributes.JUMP_STRENGTH,"2be41020-5971-43c3-bcb2-cd0b59060403",)*/BulgingWithAngerMobEffect::new);
}
