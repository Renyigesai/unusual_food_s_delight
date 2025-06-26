package com.renyigesai.unusualfoodsdelight.enchantment;

import com.renyigesai.unusualfoodsdelight.init.UdEnchantments;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WhackEnchantment extends Enchantment {
    public WhackEnchantment(Rarity rarity, EquipmentSlot... applicableSlots) {
        super(rarity, EnchantmentCategory.BREAKABLE, applicableSlots);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public int getMinCost(int pEnchantmentLevel) {
        return 1 + (pEnchantmentLevel - 1) * 10;
    }
    @Override
    public int getMaxCost(int pEnchantmentLevel) {
        return this.getMinCost(pEnchantmentLevel) + 15;
    }

    @Override
    public boolean isDiscoverable() {
        return false;
    }

    @Override
    protected boolean checkCompatibility(Enchantment ench) {
        return this != ench && !List.of(Enchantments.SWEEPING_EDGE).contains(ench);
    }

    @Mod.EventBusSubscriber
    public static class WhackEnchantmentEvent {
        @SubscribeEvent
        @SuppressWarnings("unused")
        public static void onWhackElimination(LivingHurtEvent event){
            Level world = event.getEntity().level();
            Entity sEntity = event.getSource().getEntity();
            Entity entity = event.getEntity();
            if (sEntity == null || entity == null){
                return;
            }
            if (sEntity instanceof Player player) {
                ItemStack mainHandItem = player.getMainHandItem();
                int enchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(UdEnchantments.WHACK.get(), mainHandItem);
                if (enchantmentLevel > 0) {
                    double x = player.getX();
                    double y = player.getY();
                    double z = player.getZ();
                    double ex = player.getLookAngle().x;
                    double ez = player.getLookAngle().z;
                    List<Entity> entityList = new ArrayList<>();
                    Vec3 vec3 = new Vec3(x, y, z);
                    double damage = mainHandItem.is(ModItems.SKILLET.get())?0.5:0.3;
                    List<Entity> tempEntityList = world.getEntitiesOfClass(Entity.class, new AABB(vec3, vec3).inflate(3*enchantmentLevel / 2d), e
                            -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(vec3))).toList();
                    if (!tempEntityList.isEmpty()) {
                        entityList.addAll(tempEntityList);
                    }
                    for (Entity e : entityList) {
                        if (e instanceof LivingEntity livingEntity && livingEntity != player) {
                            livingEntity.setDeltaMovement(new Vec3(ex, 0.3 * enchantmentLevel, ez));
                            livingEntity.hurt(event.getSource(), (float) (event.getAmount()*damage));
                        }
                    }
                }
            }
        }
    }
}
