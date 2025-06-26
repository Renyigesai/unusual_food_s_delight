package com.renyigesai.unusualfoodsdelight.item;

import com.renyigesai.unusualfoodsdelight.init.UdFood;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.item.ConsumableItem;

public class HumanSauceBreadItem extends ConsumableItem {
    public HumanSauceBreadItem() {
        super(new Item.Properties().food(UdFood.HUMAN_SAUCE_BREAD), true);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity consumer) {
        if (consumer instanceof Player player){
            int c = Mth.nextInt(RandomSource.create(),1,2);
            for (int i = 0; i < c; i++) {
                player.drop(new ItemStack(Items.SLIME_BALL),false);
            }
        }
        return super.finishUsingItem(stack, level, consumer);
    }
}
