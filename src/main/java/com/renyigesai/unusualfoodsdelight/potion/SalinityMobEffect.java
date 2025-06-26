package com.renyigesai.unusualfoodsdelight.potion;

import com.renyigesai.unusualfoodsdelight.init.UdMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

public class SalinityMobEffect extends MobEffect {
    public SalinityMobEffect() {
        super(MobEffectCategory.BENEFICIAL, -992314);
    }
    @Mod.EventBusSubscriber
    public static class SalinityPotionEffect{
        @SubscribeEvent
        public static void onEntityDeath(LivingDeathEvent event){
            Level world = event.getEntity().level();
            LivingEntity entity = event.getEntity();
            if (entity == null){
                return;
            }
            if (entity.hasEffect(UdMobEffects.SALINITY.get()) && entity.isOnFire()){
                if (!world.isClientSide() && world.getServer() != null) {
                    BlockPos pos = BlockPos.containing(entity.getX(),entity.getY(),entity.getZ());
                    LootTable lootTable = world.getServer().getLootData().getLootTable(new ResourceLocation("unusualfoodsdelight:entities/salt"));
                    List<ItemStack> stacks = new ArrayList<>(lootTable.getRandomItems(new LootParams.Builder((ServerLevel) world).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos)).withParameter(LootContextParams.BLOCK_STATE, world.getBlockState(pos)).withOptionalParameter(LootContextParams.BLOCK_ENTITY, world.getBlockEntity(pos)).create(LootContextParamSets.EMPTY)));
                    for (ItemStack stack:stacks) {
                        ItemEntity itemEntity = new ItemEntity(world,entity.getX(),entity.getY(),entity.getZ(),stack);
                        world.addFreshEntity(itemEntity);
                    }
                }
            }
        }
    }
}
