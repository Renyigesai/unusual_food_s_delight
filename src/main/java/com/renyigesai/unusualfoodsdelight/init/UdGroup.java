package com.renyigesai.unusualfoodsdelight.init;

import com.renyigesai.unusualfoodsdelight.UnusualFoodDelight;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class UdGroup {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, UnusualFoodDelight.MODID);

    public static final RegistryObject<CreativeModeTab> TAB_FARMERS_DELIGHT = CREATIVE_TABS.register(UnusualFoodDelight.MODID,
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("advancements.unusual_food_s_delight_root.title"))
                    .icon(() -> new ItemStack(UdItems.CLAY_STOVE.get()))
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(UdItems.CLAY_STOVE.get());
                        output.accept(UdItems.SMOKED_MEAT_HOOK.get());
                        output.accept(UdItems.HANG_LANTERN.get());
                        output.accept(UdItems.MOSS_PLANKS.get());
                        output.accept(UdItems.MOSS_PLANKS_SLAB.get());
                        output.accept(UdItems.MOSS_PLANKS_STAIRS.get());

                        output.accept(UdItems.SALT.get());
                        output.accept(UdItems.LOOSE_SALT_BLOCK.get());
                        output.accept(UdItems.WELL_SALT_WATER_BUCKET.get());

                        output.accept(UdItems.GOAT_MILK_BUCKET.get());
                        output.accept(UdItems.SOFT_CHEESE.get());
                        output.accept(UdItems.SOFT_CHEESE_CUBE.get());
                        output.accept(UdItems.SHREDDED_LEAVES.get());
                        output.accept(UdItems.SPIDER_LEGS.get());
                        output.accept(UdItems.BAKE_SPIDER_LEGS.get());
                        output.accept(UdItems.SPIDER_LEG_TEMPURA.get());
                        output.accept(UdItems.SILVERFISH_MEATBALL.get());
                        output.accept(UdItems.BAD_FLESH.get());
                        output.accept(UdItems.BAM_BAM_SMOKED_MEAT.get());
                        output.accept(UdItems.MINCED_BAD_FLESH.get());
                        output.accept(UdItems.BAD_SAUSAGE.get());
                        output.accept(UdItems.HOGLIN_RIB.get());
                        output.accept(UdItems.BAKE_HOGLIN_RIB.get());
                        output.accept(UdItems.SALTED_FISH.get());
                        output.accept(UdItems.ZOMBIE_OIL_BLOCK.get());
                        output.accept(UdItems.CREEPER_GAS.get());
                        output.accept(UdItems.DOUGH_SLICE.get());
                        output.accept(UdItems.GOLD_PLATING_APPLE.get());
                        output.accept(UdItems.SUGARCANE_JUICE.get());
                        output.accept(UdItems.SCENTED_TEA.get());
                        output.accept(UdItems.SPRING_DRINKS.get());
                        output.accept(UdItems.MIXED_FRUIT_PIE.get());
                        output.accept(UdItems.MIXED_FRUIT_PIE_SLICE.get());
                        output.accept(UdItems.SLIME_SAUCE.get());
                        output.accept(UdItems.WEED_SALAD.get());
                        output.accept(UdItems.SPIDER_LEGS_WITH_VEGETABLES.get());
                        output.accept(UdItems.MOSS_ROLLS_ROTTING_FLESH.get());
                        output.accept(UdItems.ZOMBIE_OIL_NIAN_GAO.get());
                        output.accept(UdItems.SHROOMLIGHT_BREAD.get());
                        output.accept(UdItems.SHROOMLIGHT_HAMBURGER.get());
                        output.accept(UdItems.BAD_HOT_DOG.get());
                        output.accept(UdItems.SALTED_FISH_SANDWICH.get());
                        output.accept(UdItems.HUMAN_SAUCE_BREAD.get());
                        output.accept(UdItems.ENERGY_BAR.get());
                        output.accept(UdItems.END_SOFT_SWEETS.get());
                        output.accept(UdItems.TOMATO_IN_OIL.get());
                        output.accept(UdItems.BAMBOO_COOKED_RICE.get());
                        output.accept(UdItems.SHULKER_BOX_LUNCH.get());
                        output.accept(UdItems.SMOKED_ROUGAMO.get());
                        output.accept(UdItems.PASTA_WITH_SMOKED_MEAT.get());
                        output.accept(UdItems.CHEESE_LASAGNA.get());
                        output.accept(UdItems.ROAST_CREAM_MIXED_MONSTER.get());
                        output.accept(UdItems.SILVERFISH_MEATBALL_SOUP.get());
                        output.accept(UdItems.MUSHROOM_BEEF_STEW.get());
                        output.accept(UdItems.RAW_BARBECUE.get());
                        output.accept(UdItems.BLAZE_BARBECUE.get());
                        output.accept(UdItems.STONE_BRICKS_TRAP.get());

                    }))
                    .build());
}
