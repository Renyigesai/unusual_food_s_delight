package com.renyigesai.unusualfoodsdelight.event;

import com.renyigesai.unusualfoodsdelight.Config;
import com.renyigesai.unusualfoodsdelight.block.smoked_meat_campfire.SmokedMeatCampfireBlock;
import com.renyigesai.unusualfoodsdelight.init.UdBlocks;
import com.renyigesai.unusualfoodsdelight.init.UdItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class UdEvents {
    @SubscribeEvent
    public static void onClickEntityEvent(PlayerInteractEvent.EntityInteract event){
        Player player = event.getEntity();
        Entity entity = event.getTarget();
        Level level = event.getLevel();
        if (player ==  null || entity == null || level == null){
            return;
        }
        ItemStack hand = player.getItemInHand(InteractionHand.MAIN_HAND);
        if (hand.is(Items.BUCKET) && entity instanceof Goat){
            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.SUCCESS);
            clickGoat(event,level,player,hand,entity);
            return;
        }
        if (hand.is(UdItems.RAW_BARBECUE.get()) && entity instanceof Blaze) {
            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.SUCCESS);
            clickBlaze(level,player,hand,entity);
            return;
        }
        if (hand.is(Items.GLASS_BOTTLE) && entity instanceof Creeper) {
            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.SUCCESS);
            clickCreeper(level,player,hand,entity);
        }
    }


    private static void clickGoat(PlayerInteractEvent.EntityInteract event,Level level, Player entity,ItemStack hand, Entity sEntity){
        if (!level.isClientSide){
            if (!entity.getAbilities().instabuild){
                hand.shrink(1);
            }
            entity.getInventory().placeItemBackInInventory(new ItemStack(UdItems.GOAT_MILK_BUCKET.get()));
            level.playSound(null, BlockPos.containing(sEntity.getX(),sEntity.getY(),sEntity.getZ()), SoundEvents.GOAT_SCREAMING_MILK, SoundSource.PLAYERS);
        }
    }

    private static void clickBlaze(Level level,Player entity,ItemStack hand,Entity sEntity){
        if (!level.isClientSide) {
            if (!entity.getAbilities().instabuild) {
                hand.shrink(1);
            }
            entity.getInventory().placeItemBackInInventory(new ItemStack(UdItems.BLAZE_BARBECUE.get()));
            if (level instanceof ServerLevel serverLevel) {
                double x = sEntity.getX();
                double y = sEntity.getY() + sEntity.getBbHeight() / 2;
                double z = sEntity.getZ();
                serverLevel.sendParticles(ParticleTypes.FLAME, x, y, z, 32, 0.25, 0.25, 0.25, 0.0);
                serverLevel.playSound(null, BlockPos.containing(x, y, z), SoundEvents.BLAZE_SHOOT, SoundSource.PLAYERS);
            }
        }
    }

    private static void clickCreeper(Level level, Player entity,ItemStack hand, Entity sEntity){
        if (!level.isClientSide){
            if (!entity.getAbilities().instabuild){
                hand.shrink(1);
            }
            entity.getInventory().placeItemBackInInventory(new ItemStack(UdItems.CREEPER_GAS.get()));
            level.playSound(null, BlockPos.containing(sEntity.getX(),sEntity.getY(),sEntity.getZ()), SoundEvents.BOTTLE_FILL, SoundSource.PLAYERS);
        }
    }

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        InteractionHand hand = event.getHand();
        Player entity = event.getEntity();
        if (hand != entity.getUsedItemHand())
            return;
        BlockPos pos = event.getPos();
        BlockState blockState = event.getLevel().getBlockState(pos);
        ItemStack hand1 = entity.getItemInHand(hand);
        if (hand1.is(UdItems.CREEPER_GAS.get()) && blockState.is(Blocks.CAMPFIRE)){
            event.setCancellationResult(InteractionResult.CONSUME);
            event.getLevel().setBlock(pos, UdBlocks.SMOKED_MEAT_CAMPFIRE.get().defaultBlockState().setValue(SmokedMeatCampfireBlock.FACING,blockState.getValue(CampfireBlock.FACING)),3);
            hand1.shrink(1);
            entity.getInventory().placeItemBackInInventory(new ItemStack(Items.GLASS_BOTTLE));
            for (int i = 0; i < 16; i++) {
                double d1 = Mth.nextDouble(RandomSource.create(),-0.25,-0.25);
                double d2 = Mth.nextDouble(RandomSource.create(),-0.25,-0.25);
                double d3 = Mth.nextDouble(RandomSource.create(),-0.25,-0.25);
                event.getLevel().addParticle(ParticleTypes.LAVA,pos.getX()+0.5,pos.getY()+0.5,pos.getZ()+0.5,0+d1,0+d2,0+d3);
            }
            event.getLevel().playLocalSound(pos.getX(),pos.getY(),pos.getZ(),SoundEvents.BOTTLE_EMPTY,SoundSource.BLOCKS,1f,1f,false);
        }
    }

    @SubscribeEvent
    public static void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
        ItemStack itemstack = event.getItemStack();
        if (itemstack.getItem() == UdItems.ZOMBIE_OIL_BLOCK.get())
            event.setBurnTime(1200);
    }

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event){
        if (!Config.dispel_the_darkness)
            return;
        Level level = event.getEntity().level();
        LivingEntity entity = event.getEntity();
        if (!(entity instanceof Player))
            return;
        if (!level.isClientSide) {
            if (entity.hasEffect(MobEffects.DARKNESS) && entity.hasEffect(MobEffects.GLOWING)) {
                entity.removeEffect(MobEffects.DARKNESS);
            }
        }
    }

}
