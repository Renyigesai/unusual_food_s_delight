package com.renyigesai.unusualfoodsdelight.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import vectorwing.farmersdelight.common.item.DrinkableItem;

import java.util.Comparator;
import java.util.List;

public class ReversalChorusFruitDrinkableItem extends DrinkableItem {
    public ReversalChorusFruitDrinkableItem() {
        super(new Properties().food(Foods.CHORUS_FRUIT).rarity(Rarity.UNCOMMON).stacksTo(16));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity consumer) {
        transferEntitys(level,consumer);
        return super.finishUsingItem(stack, level, consumer);
    }

        public void transferEntitys(Level p_40713_, LivingEntity p_40714_) {
        if (!p_40713_.isClientSide) {
            List<LivingEntity> entitys = getEntitys(p_40714_, p_40713_);
            if (entitys.isEmpty()) {
                return;
            }
            for (LivingEntity l : entitys) {
                if (l != p_40714_){
                    double d0 = l.getX();
                    double d1 = l.getY();
                    double d2 = l.getZ();

                    for(int i = 0; i < 16; ++i) {
                        double d3 = l.getX() + (l.getRandom().nextDouble() - 0.5D) * 16.0D;
                        double d4 = Mth.clamp(l.getY() + (double)(l.getRandom().nextInt(16) - 8), (double)p_40713_.getMinBuildHeight(), (double)(p_40713_.getMinBuildHeight() + ((ServerLevel)p_40713_).getLogicalHeight() - 1));
                        double d5 = l.getZ() + (l.getRandom().nextDouble() - 0.5D) * 16.0D;
                        if (l.isPassenger()) {
                            l.stopRiding();
                        }

                        Vec3 vec3 = l.position();
                        p_40713_.gameEvent(GameEvent.TELEPORT, vec3, GameEvent.Context.of(l));
                        net.minecraftforge.event.entity.EntityTeleportEvent.ChorusFruit event = net.minecraftforge.event.ForgeEventFactory.onChorusFruitTeleport(l, d3, d4, d5);
                        if (event.isCanceled()) return;
                        if (l.randomTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true)) {
                            SoundEvent soundevent = l instanceof Fox ? SoundEvents.FOX_TELEPORT : SoundEvents.CHORUS_FRUIT_TELEPORT;
                            p_40713_.playSound((Player)null, d0, d1, d2, soundevent, SoundSource.PLAYERS, 1.0F, 1.0F);
                            l.playSound(soundevent, 1.0F, 1.0F);
                            break;
                        }
                    }

                    if (p_40714_ instanceof Player) {
                        ((Player)p_40714_).getCooldowns().addCooldown(this, 20);
                    }
                }
            }
        }
    }

    public static List<LivingEntity> getEntitys(Entity entity,Level level){
        if (entity == null){
            return null;
        }
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        final Vec3 _center = new Vec3(x, y, z);
        return level.getEntitiesOfClass(LivingEntity.class, new AABB(_center, _center).inflate(16 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
    }

}
