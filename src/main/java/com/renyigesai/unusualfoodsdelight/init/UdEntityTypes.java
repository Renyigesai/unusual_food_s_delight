package com.renyigesai.unusualfoodsdelight.init;

import com.renyigesai.unusualfoodsdelight.UnusualFoodDelight;
import com.renyigesai.unusualfoodsdelight.entity.DragonPreservedEggEntity;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.FallingBlockRenderer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class UdEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY = DeferredRegister.create(Registries.ENTITY_TYPE, UnusualFoodDelight.MODID);

    public static final RegistryObject<EntityType<DragonPreservedEggEntity>> DRAGON_PRESERVED_EGG = ENTITY.register("dragon_preserved_egg",()->
            EntityType.Builder.of(DragonPreservedEggEntity::new, MobCategory.MISC).sized(0.25f, 0.35f).build(new ResourceLocation(UnusualFoodDelight.MODID,"dragon_preserved_egg").toString()));

    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> EntityRenderers.register(DRAGON_PRESERVED_EGG.get(), FallingBlockRenderer::new));
    }
}
