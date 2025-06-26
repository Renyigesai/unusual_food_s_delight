//package com.renyigesai.unusualfoodsdelight.event;
//
//import com.renyigesai.unusualfoodsdelight.init.UdItems;
//import net.minecraft.core.BlockPos;
//import net.minecraft.core.particles.ParticleTypes;
//import net.minecraft.server.level.ServerLevel;
//import net.minecraft.server.level.ServerPlayer;
//import net.minecraft.sounds.SoundEvents;
//import net.minecraft.sounds.SoundSource;
//import net.minecraft.world.InteractionHand;
//import net.minecraft.world.InteractionResult;
//import net.minecraft.world.entity.Entity;
//import net.minecraft.world.entity.animal.goat.Goat;
//import net.minecraft.world.entity.monster.Blaze;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.Items;
//import net.minecraft.world.level.GameType;
//import net.minecraft.world.level.Level;
//import net.minecraftforge.event.entity.player.PlayerInteractEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod;
//
//@Mod.EventBusSubscriber
//public class ClickEntityEvent {
//    @SubscribeEvent
//    public static void onClickEntityEvent(PlayerInteractEvent.EntityInteract event){
//        Player player = event.getEntity();
//        Entity entity = event.getTarget();
//        Level level = event.getLevel();
//        if (player ==  null || entity == null || level == null){
//            return;
//        }
//        ItemStack hand = player.getItemInHand(InteractionHand.MAIN_HAND);
//        if (hand.is(Items.BUCKET) && entity instanceof Goat){
//            event.setCanceled(true);
//            event.setCancellationResult(InteractionResult.SUCCESS);
//            clickGoat(event,level,player,hand,entity);
//            return;
//        }
//        if (hand.is(UdItems.RAW_BARBECUE.get()) && entity instanceof Blaze) {
//            event.setCanceled(true);
//            event.setCancellationResult(InteractionResult.SUCCESS);
//            clickBlaze(level,player,hand,entity);
//        }
//    }
//
//
//    private static void clickGoat(PlayerInteractEvent.EntityInteract event,Level level, Player entity,ItemStack hand, Entity sEntity){
//        if (!level.isClientSide){
//            if (!entity.getAbilities().instabuild){
//                    hand.shrink(1);
//            }
//            entity.getInventory().placeItemBackInInventory(new ItemStack(UdItems.GOAT_MILK_BUCKET.get()));
//            level.playSound(null, BlockPos.containing(sEntity.getX(),sEntity.getY(),sEntity.getZ()), SoundEvents.GOAT_SCREAMING_MILK, SoundSource.PLAYERS);
//        }
//    }
//
//    private static void clickBlaze(Level level,Player entity,ItemStack hand,Entity sEntity){
//        if (!level.isClientSide) {
//            if (!entity.getAbilities().instabuild) {
//                hand.shrink(1);
//            }
//            entity.getInventory().placeItemBackInInventory(new ItemStack(UdItems.BLAZE_BARBECUE.get()));
//            if (level instanceof ServerLevel serverLevel) {
//                double x = sEntity.getX();
//                double y = sEntity.getY() + sEntity.getBbHeight() / 2;
//                double z = sEntity.getZ();
//                serverLevel.sendParticles(ParticleTypes.FLAME, x, y, z, 32, 0.25, 0.25, 0.25, 0.0);
//                serverLevel.playSound(null, BlockPos.containing(x, y, z), SoundEvents.BLAZE_SHOOT, SoundSource.PLAYERS);
//            }
//        }
//    }
//}
