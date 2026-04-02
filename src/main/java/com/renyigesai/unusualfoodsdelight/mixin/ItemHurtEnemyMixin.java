package com.renyigesai.unusualfoodsdelight.mixin;

import com.renyigesai.unusualfoodsdelight.events.ItemHurtEnemyEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemHurtEnemyMixin {
    @Inject(method = "hurtEnemy",at = @At("HEAD"))
    private void hurtEnemy(ItemStack itemStack, LivingEntity living, LivingEntity sourceentity, CallbackInfoReturnable<Boolean> cir){
        System.out.println("Mixin");
        ItemHurtEnemyEvent itemHurtEnemyEvent = new ItemHurtEnemyEvent(sourceentity, living, itemStack);
        MinecraftForge.EVENT_BUS.post(itemHurtEnemyEvent);
    }
}
