package com.renyigesai.unusualfoodsdelight;

import com.mojang.logging.LogUtils;
import com.renyigesai.unusualfoodsdelight.advancement.DragonPreservedEggTrigger;
import com.renyigesai.unusualfoodsdelight.fluid.UdFluidTypes;
import com.renyigesai.unusualfoodsdelight.fluid.UdFluids;
import com.renyigesai.unusualfoodsdelight.init.*;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(UnusualFoodDelight.MODID)
public class UnusualFoodDelight {

    public static final String MODID = "unusualfoodsdelight";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final DragonPreservedEggTrigger DRAGON_PRESERVED_EGG_TRIGGER = new DragonPreservedEggTrigger();

    public UnusualFoodDelight() {
        MinecraftForge.EVENT_BUS.register(this);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        UdItems.REGISTER.register(bus);
        UdBlocks.BLOCKS.register(bus);
        UdBlocks.BLOCK_ENTITY_REGISTRY.register(bus);
        UdFluids.REGISTRY.register(bus);
        UdFluidTypes.REGISTRY.register(bus);
        UdMobEffects.REGISTRY.register(bus);
        UdParticleTypes.REGISTRY.register(bus);
        UdEnchantments.ENCHANTMENTS.register(bus);
        UdGroup.CREATIVE_TABS.register(bus);
        UdEntityTypes.ENTITY.register(bus);
        registerTrigger();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    @SubscribeEvent
    public void onTooltipColor(RenderTooltipEvent.Color event) {
    }

    public void registerTrigger(){
        CriteriaTriggers.register(DRAGON_PRESERVED_EGG_TRIGGER);
    }
}
