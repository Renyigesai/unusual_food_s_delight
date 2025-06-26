package com.renyigesai.unusualfoodsdelight.potion;

import com.renyigesai.unusualfoodsdelight.init.UdMobEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class BulgingWithAngerMobEffect extends MobEffect {

    public BulgingWithAngerMobEffect() {
        super(MobEffectCategory.BENEFICIAL,  -10053376);
    }

    @Mod.EventBusSubscriber
    public static class BulgingWithAngerPotionEffect{
        @SubscribeEvent
        public static void onLivingAttack(LivingAttackEvent event){
            Level world = event.getEntity().level();
            Entity sEntity = event.getSource().getEntity();
            Entity entity = event.getEntity();
            if (sEntity == null || entity == null){
                return;
            }
            if (entity instanceof LivingEntity livingEntity && livingEntity.hasEffect(UdMobEffects.BULGING_WITH_ANGER.get())) {
                if (!world.isClientSide()){
                    world.explode(entity, entity.getX(), entity.getY(), entity.getZ(), 3F, Level.ExplosionInteraction.NONE);
                }
                livingEntity.removeEffect(UdMobEffects.BULGING_WITH_ANGER.get());
            }
        }
    }
}
