package com.renyigesai.unusualfoodsdelight;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;
import java.util.stream.Collectors;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = UnusualFoodDelight.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.BooleanValue DISPEL_THE_DARKNESS = BUILDER.comment("When the Glowing effect is enabled, players will be immune to the Darkness effect").define("Dispel the darkness", true);

    public static List<Block> DRAGON_EGG_CONVERTING_BLOCKS;
    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> DRAGON_EGG_CONVERTING_BLOCK = BUILDER
            .comment("Can be used for converting the blocks of dragon eggs.")
            .defineListAllowEmpty("dragonEggConvertingBlocks",getModBlocks()
                   , Config::validateBlockName);;

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static boolean dispel_the_darkness;

    private static List<String> getModBlocks(){
        List<List<String>> blocks = List.of(
                List.of("unusualfoodsdelight","unusualfoodsdelight:loose_salt_block"),
                List.of("bakeries","bakeries:raw_salt_block"),
                List.of("mekanism","mekanism:saltblock"),
                List.of("vintagedeligh","vintagedelight:salt_block"),
                List.of("salt","salt:salt_block"),
                List.of("salt","salt:raw_rock_salt_block"));
        List<String> list = new ArrayList<>();
        for (List<String> stringStringMap : blocks) {
            if (ModList.get().isLoaded(stringStringMap.get(0))){
                list.add(stringStringMap.get(1));
            }
        }
        return list;
    }

    private static boolean validateBlockName(final Object obj)
    {
        return obj instanceof final String name && BuiltInRegistries.BLOCK.containsKey(new ResourceLocation(name));
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        dispel_the_darkness = DISPEL_THE_DARKNESS.get();
        DRAGON_EGG_CONVERTING_BLOCKS = DRAGON_EGG_CONVERTING_BLOCK.get().stream()
                .map(name -> BuiltInRegistries.BLOCK.get(new ResourceLocation(name))).toList();
    }
}
