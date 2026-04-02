package com.renyigesai.unusualfoodsdelight.events;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.Event;

public class ItemHurtEnemyEvent extends Event {
    private final LivingEntity sourceEntity;
    private final LivingEntity entity;
    private final ItemStack item;

    public ItemHurtEnemyEvent(LivingEntity sourceEntity, LivingEntity entity, ItemStack item) {
        this.sourceEntity = sourceEntity;
        this.entity = entity;
        this.item = item;
    }

    public ItemStack getItem() {
        return item;
    }

    public LivingEntity getEntity() {
        return entity;
    }

    public LivingEntity getSourceEntity() {
        return sourceEntity;
    }
}
