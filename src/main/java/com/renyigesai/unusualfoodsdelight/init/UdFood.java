package com.renyigesai.unusualfoodsdelight.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import vectorwing.farmersdelight.common.registry.ModEffects;

public class UdFood {

    public static final FoodProperties SPIDER_LEGS = new FoodProperties.Builder().nutrition(1).saturationMod(0.8f).build();

    public static final FoodProperties WEED_SALAD = new FoodProperties.Builder().nutrition(4).saturationMod(0.2f).build();

    public static final FoodProperties MOSS_ROLLS_ROTTING_FLESH = new FoodProperties.Builder().nutrition(5).saturationMod(0.06f).fast().build();

    public static final FoodProperties MIXED_FRUIT_PIE_SLICE = new FoodProperties.Builder().nutrition(3).saturationMod(0.4f).build();

    public static final FoodProperties SUGARCANE_JUICE = new FoodProperties.Builder().alwaysEat().build();

    public static final FoodProperties SPIDER_LEGS_WITH_VEGETABLES = new FoodProperties.Builder().nutrition(7).saturationMod(0.8f).build();

    public static final FoodProperties BAKE_SPIDER_LEGS = new FoodProperties.Builder().nutrition(3).saturationMod(0.6f).build();

    public static final FoodProperties SALTED_FISH = new FoodProperties.Builder().nutrition(1).saturationMod(0.8f).build();

    public static final FoodProperties HOGLIN_RIB = new FoodProperties.Builder().nutrition(3).saturationMod(0.3f).build();

    public static final FoodProperties BAD_FLESH = new FoodProperties.Builder().nutrition(2).saturationMod(0.6f).effect(new MobEffectInstance(MobEffects.BAD_OMEN,120000),1F).alwaysEat().build();

    public static final FoodProperties MINCED_BAD_FLESH = new FoodProperties.Builder().nutrition(1).saturationMod(0.5f).effect(new MobEffectInstance(MobEffects.BAD_OMEN,120000),1F).alwaysEat().build();

    public static final FoodProperties BAD_SAUSAGE = new FoodProperties.Builder().nutrition(1).saturationMod(0.6f).effect(new MobEffectInstance(MobEffects.BAD_OMEN,120000),1F).alwaysEat().build();

    public static final FoodProperties BAKE_HOGLIN_RIB = new FoodProperties.Builder().nutrition(9).saturationMod(1.4f).build();

    public static final FoodProperties SLIME_SAUCE = new FoodProperties.Builder().nutrition(6).saturationMod(0.65f).build();

    public static final FoodProperties SCENTED_TEA = new FoodProperties.Builder().effect(new MobEffectInstance(MobEffects.DIG_SPEED,6000),1f).alwaysEat().build();

    public static final FoodProperties MUSHROOM_BEEF_STEW = new FoodProperties.Builder().nutrition(10).saturationMod(1.92F)
            .effect(()->new MobEffectInstance(ModEffects.NOURISHMENT.get(),6000),1f).build();

    public static final FoodProperties ENERGY_BAR = new FoodProperties.Builder().nutrition(10).saturationMod(2F)
            .effect(new MobEffectInstance(MobEffects.DIG_SPEED,1200),1f)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,1200),1f)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,1200),1f).build();

    public static final FoodProperties SILVERFISH_MEATBALL = new FoodProperties.Builder().nutrition(1).saturationMod(0.8F).build();

    public static final FoodProperties SILVERFISH_MEATBALL_SOUP = new FoodProperties.Builder().nutrition(14).saturationMod(0.785F)
            .effect(()->new MobEffectInstance(ModEffects.COMFORT.get(),6000),1f).build();

    public static final FoodProperties GOLD_PLATING_APPLE = new FoodProperties.Builder().nutrition(4).saturationMod(1.2F)
            .effect(new MobEffectInstance(MobEffects.REGENERATION,100),1f).build();

    public static final FoodProperties SALTED_FISH_SANDWICH = new FoodProperties.Builder().nutrition(8).saturationMod(0.8F).build();

    public static final FoodProperties SOFT_CHEESE_CUBE = new FoodProperties.Builder().nutrition(2).saturationMod(2.5F).effect(()->new MobEffectInstance(UdMobEffects.COLLOCATION.get(),200),1F).alwaysEat().build();

    public static final FoodProperties TOMATO_IN_OIL = new FoodProperties.Builder().nutrition(2).saturationMod(2.5F).effect(()->new MobEffectInstance(UdMobEffects.COLLOCATION.get(),200),1F).alwaysEat().build();

    public static final FoodProperties CHEESE_LASAGNA = new FoodProperties.Builder().nutrition(10).saturationMod(1.2F).build();

    public static final FoodProperties HUMAN_SAUCE_BREAD = new FoodProperties.Builder().nutrition(10).saturationMod(1.2F)
            .effect(new MobEffectInstance(MobEffects.CONFUSION,200),1F)
            .effect(new MobEffectInstance(MobEffects.WEAKNESS,600),1F)
            .effect(new MobEffectInstance(MobEffects.HUNGER,600),1F).build();

    public static final FoodProperties END_SOFT_SWEETS = new FoodProperties.Builder().nutrition(2).saturationMod(0.2F).alwaysEat()
            .effect(new MobEffectInstance(MobEffects.SLOW_FALLING,600),1F)
            .effect(new MobEffectInstance(MobEffects.JUMP,600,2),1F)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,600,2),1F).build();

    public static final FoodProperties BAD_HOT_DOG = new FoodProperties.Builder().nutrition(10).saturationMod(1.6F)
            .effect(new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE,6000),1F).build();

    public static final FoodProperties RAW_BARBECUE = new FoodProperties.Builder().nutrition(5).saturationMod(0.1F).build();

    public static final FoodProperties BLAZE_BARBECUE = new FoodProperties.Builder().nutrition(20).saturationMod(1F)
            .effect(()->new MobEffectInstance(ModEffects.NOURISHMENT.get(),6000),1F).build();

    public static final FoodProperties SPRING_DRINKS = new FoodProperties.Builder().effect(new MobEffectInstance(MobEffects.ABSORPTION,2400),1F).alwaysEat().build();

    public static final FoodProperties CREEPER_SANDWICH_CAKE = new FoodProperties.Builder().effect(()-> new MobEffectInstance(UdMobEffects.BULGING_WITH_ANGER.get(),1200),1F).build();

    public static final FoodProperties BAM_BAM_SMOKED_MEAT = new FoodProperties.Builder().effect(()-> new MobEffectInstance(UdMobEffects.BULGING_WITH_ANGER.get(),1200),1F).nutrition(6).saturationMod(0.575F).build();

    public static final FoodProperties SPIDER_LEG_TEMPURA = new FoodProperties.Builder().nutrition(6).saturationMod(0.85F).build();

    public static final FoodProperties PASTA_WITH_SMOKED_MEAT = new FoodProperties.Builder().nutrition(12).saturationMod(0.875F).effect(()->new MobEffectInstance(ModEffects.NOURISHMENT.get(),6000),1F).effect(()->new MobEffectInstance(UdMobEffects.BULGING_WITH_ANGER.get(),3600),1F).build();

}
