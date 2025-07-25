package com.renyigesai.unusualfoodsdelight;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = UnusualFoodDelight.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.BooleanValue DISPEL_THE_DARKNESS = BUILDER.comment("When the Glowing effect is enabled, players will be immune to the Darkness effect").define("Dispel the darkness", true);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static boolean dispel_the_darkness;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        dispel_the_darkness = DISPEL_THE_DARKNESS.get();
    }
}
