package com.renyigesai.unusualfoodsdelight.potion;

import com.mojang.datafixers.util.Pair;
import com.renyigesai.unusualfoodsdelight.init.UdMobEffects;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import vectorwing.farmersdelight.common.registry.ModEffects;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CollocationMobEffect extends MobEffect {
    public CollocationMobEffect() {
        super(MobEffectCategory.BENEFICIAL, -1817);
    }
    @Mod.EventBusSubscriber
    public static class CollocationPotionEffect{
        @SubscribeEvent
        public static void onUseItemFinish(LivingEntityUseItemEvent.Finish event) {
            if (event != null && event.getEntity() != null) {
                ItemStack resultStack = event.getResultStack();
                LivingEntity entity = event.getEntity();
                if (entity.hasEffect(UdMobEffects.COLLOCATION.get()) && resultStack.isEdible() && isCollocationFood(resultStack,entity)) {
                    if (!event.getEntity().level().isClientSide) {
                        List<MobEffectInstance> list = getEffect();
                        int i = Mth.nextInt(RandomSource.create(), 0, 4);
                        entity.addEffect(new MobEffectInstance(list.get(i)));
                        entity.removeEffect(UdMobEffects.COLLOCATION.get());
                    }
                }
            }
        }
    }

    public static boolean isCollocationFood(ItemStack stack, LivingEntity entity){
        List<Pair<MobEffectInstance, Float>> effects = Objects.requireNonNull(stack.getFoodProperties(entity)).getEffects();
        for (Pair<MobEffectInstance, Float> effect : effects) {
            if (effect.getFirst().getEffect() == UdMobEffects.COLLOCATION.get()) {
                return false;
            }
        }
        return true;
    }

    public static List<MobEffectInstance> getEffect(){
        List<MobEffectInstance> list = new ArrayList<>();
        list.add(new MobEffectInstance(MobEffects.DAMAGE_BOOST,1200));
        list.add(new MobEffectInstance(MobEffects.REGENERATION,1200));
        list.add(new MobEffectInstance(MobEffects.DIG_SPEED,1200));
        list.add(new MobEffectInstance(ModEffects.NOURISHMENT.get(),1200));
        list.add(new MobEffectInstance(ModEffects.COMFORT.get(),1200));
        return list;

    }

}
