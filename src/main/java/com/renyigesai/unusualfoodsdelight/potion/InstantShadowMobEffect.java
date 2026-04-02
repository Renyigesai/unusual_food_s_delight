package com.renyigesai.unusualfoodsdelight.potion;

import com.renyigesai.unusualfoodsdelight.init.UdMobEffects;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class InstantShadowMobEffect extends MobEffect {
    public InstantShadowMobEffect() {
        super(MobEffectCategory.BENEFICIAL, 17416137);
    }

    @Mod.EventBusSubscriber
    public static class InstantShadowPotionEffect{
        @SubscribeEvent
        public static void onLivingAttack(LivingAttackEvent event){
            Level world = event.getEntity().level();
            Entity sEntity = event.getSource().getEntity();
            Entity entity = event.getEntity();
            if (sEntity == null || entity == null){
                return;
            }
            if (world.isClientSide){
                return;
            }
            if (entity instanceof LivingEntity livingEntity && livingEntity.hasEffect(UdMobEffects.INSTANT_SHADOW.get())) {
                event.setCanceled(true);
                if (world instanceof ServerLevel serverLevel){
                    if (livingEntity instanceof ServerPlayer serverPlayer){
                        serverPlayer.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY,5));
                        boolean isLeft = livingEntity.getRandom().nextBoolean();
                        applyLeftRightKnockback(serverPlayer, 2, isLeft);
                        MobEffectInstance effect = livingEntity.getEffect(UdMobEffects.INSTANT_SHADOW.get());
                        /*每触发一次减少10秒药水效果时间*/
                        if (effect != null){
                            int oleDuration = effect.getDuration();
                            serverPlayer.removeEffect(UdMobEffects.INSTANT_SHADOW.get());
                            serverPlayer.addEffect(new MobEffectInstance(UdMobEffects.INSTANT_SHADOW.get(), oleDuration - 200));
                        }
                        /*受击时有十分之一的概率对攻击者造成10+最大生命值百分之10的伤害*/
                        if (serverPlayer.getRandom().nextDouble() < 0.1){
                            if (sEntity instanceof LivingEntity living){
                                float maxHealth = living.getMaxHealth();
                                living.hurt(serverPlayer.damageSources().magic(),10f + maxHealth / 10);
                                serverLevel.sendParticles(ParticleTypes.DRAGON_BREATH,serverPlayer.getX(),serverPlayer.getY(0.5),serverPlayer.getZ(),32,0,0.5,0,0.15);
                            }
                        }
                        serverLevel.sendParticles(ParticleTypes.DRAGON_BREATH,serverPlayer.getX(),serverPlayer.getY(0.5),serverPlayer.getZ(),32,0.5,0.5,0.5,0.025);
                        serverLevel.playSound(null,serverPlayer.getX(),serverPlayer.getY(),serverPlayer.getZ(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.BLOCKS,1f,1f);
                    }
                }
            }
        }

        public static void applyLeftRightKnockback(LivingEntity entity, double speed, boolean isLeft) {
            float yaw = entity.getYRot();
            double angle = Math.toRadians(yaw);
            double rightX = Math.cos(angle);
            double rightZ = Math.sin(angle);
            double dx = (isLeft ? -rightX : rightX) * speed;
            double dz = (isLeft ? -rightZ : rightZ) * speed;
            entity.setDeltaMovement(dx, entity.getDeltaMovement().y + 0.3, dz);
            entity.hurtMarked = true;
        }
    }
}
