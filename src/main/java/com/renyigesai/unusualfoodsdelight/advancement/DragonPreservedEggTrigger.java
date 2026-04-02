package com.renyigesai.unusualfoodsdelight.advancement;

import com.google.gson.JsonObject;
import com.renyigesai.unusualfoodsdelight.UnusualFoodDelight;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class DragonPreservedEggTrigger extends SimpleCriterionTrigger<DragonPreservedEggTrigger.Instance> {

    private static final ResourceLocation ID = new ResourceLocation(UnusualFoodDelight.MODID, "dragon_preserved_egg");

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    protected DragonPreservedEggTrigger.Instance createInstance(JsonObject json, ContextAwarePredicate playerPredicate, DeserializationContext context) {
        return new DragonPreservedEggTrigger.Instance(playerPredicate);
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, instance -> true);
    }

    public static class Instance extends AbstractCriterionTriggerInstance {
        public Instance(ContextAwarePredicate playerPredicate) {
            super(ID, playerPredicate);
        }
    }
}
