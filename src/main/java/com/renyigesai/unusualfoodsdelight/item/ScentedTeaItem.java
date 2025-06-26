package com.renyigesai.unusualfoodsdelight.item;

import com.renyigesai.unusualfoodsdelight.init.UdFood;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.item.DrinkableItem;

public class ScentedTeaItem extends DrinkableItem {
    public ScentedTeaItem() {
        super(new Item.Properties().stacksTo(16).food(UdFood.SCENTED_TEA).craftRemainder(Items.GLASS_BOTTLE),true);
    }


    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity consumer) {
        if (consumer.hasEffect(MobEffects.DIG_SPEED)){
            int lv = consumer.getEffect(MobEffects.DIG_SPEED).getAmplifier();
            int time = consumer.getEffect(MobEffects.DIG_SPEED).getDuration();
            if (lv < 2){
                consumer.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,time + 6000,lv + 1));
            }
        }
        return super.finishUsingItem(stack, level, consumer);
    }
}
