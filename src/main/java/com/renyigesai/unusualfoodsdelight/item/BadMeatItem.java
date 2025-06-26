package com.renyigesai.unusualfoodsdelight.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.item.ConsumableItem;

import java.util.List;

public class BadMeatItem extends Item {


    public BadMeatItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity consumer) {
        if (consumer.hasEffect(MobEffects.BAD_OMEN)){
            int lv = consumer.getEffect(MobEffects.BAD_OMEN).getAmplifier();
            int time = consumer.getEffect(MobEffects.BAD_OMEN).getDuration();
            if (lv < 4){
                consumer.addEffect(new MobEffectInstance(MobEffects.BAD_OMEN,time,lv + 1));
            }
        }
        return super.finishUsingItem(stack, level, consumer);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag isAdvanced) {
        tooltip.add(Component.translatable("item.unusualfoodsdelight.bad_meat.tips"));
    }
}
