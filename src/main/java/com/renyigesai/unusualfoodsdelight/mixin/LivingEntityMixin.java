package com.renyigesai.unusualfoodsdelight.mixin;

import com.renyigesai.unusualfoodsdelight.init.UdMobEffects;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Attackable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable, net.minecraftforge.common.extensions.IForgeLivingEntity{
    @Shadow public abstract float getHealth();

    @Shadow public abstract boolean hasEffect(MobEffect p_21024_);

    public LivingEntityMixin(EntityType<?> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
    }

    @Inject(method = "setHealth",at = @At("HEAD"), cancellable = true)
    public void defense(float amount, CallbackInfo ci) {
        if (this.hasEffect(UdMobEffects.INSTANT_SHADOW.get()) && amount < this.getHealth()){
            ci.cancel();
        }
    }
}
