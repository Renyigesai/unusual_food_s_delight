package com.renyigesai.unusualfoodsdelight.item;

import com.renyigesai.unusualfoodsdelight.init.UdItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class MincedBadFleshItem extends BadMeatItem {


    public MincedBadFleshItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack offhandItem = player.getOffhandItem();
        ItemStack mainHandItem = player.getMainHandItem();
        if (offhandItem.is(Items.PHANTOM_MEMBRANE) && mainHandItem.is(UdItems.MINCED_BAD_FLESH.get())){
            mainHandItem.shrink(1);
            offhandItem.shrink(1);
        }
        player.getInventory().placeItemBackInInventory(new ItemStack(UdItems.BAD_SAUSAGE.get()));
        level.playSound(null, BlockPos.containing(player.getX(),player.getY(),player.getZ()), SoundEvents.SLIME_JUMP, SoundSource.PLAYERS);
        return super.use(level, player, hand);
    }
}
