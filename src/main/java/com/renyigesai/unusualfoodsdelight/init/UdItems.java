package com.renyigesai.unusualfoodsdelight.init;

import com.renyigesai.unusualfoodsdelight.UnusualFoodDelight;
import com.renyigesai.unusualfoodsdelight.fluid.UdFluidTypes;
import com.renyigesai.unusualfoodsdelight.fluid.UdFluids;
import com.renyigesai.unusualfoodsdelight.item.*;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.item.DrinkableItem;

import java.util.function.Supplier;

public class UdItems {
    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, UnusualFoodDelight.MODID);
    /*方块物品*/
    public static final RegistryObject<Item> HANG_LANTERN;
    public static final RegistryObject<Item> CLAY_STOVE;
    public static final RegistryObject<Item> SOFT_CHEESE;
    public static final RegistryObject<Item> WELL_SALT_WATER_BUCKET;
    public static final RegistryObject<Item> MOSS_PLANKS;
    public static final RegistryObject<Item> MOSS_PLANKS_SLAB;
    public static final RegistryObject<Item> MOSS_PLANKS_STAIRS;
    public static final RegistryObject<Item> LOOSE_SALT_BLOCK;
    public static final RegistryObject<Item> STONE_BRICKS_TRAP;
    public static final RegistryObject<Item> SMOKED_MEAT_HOOK;

    /*食材*/
    public static final RegistryObject<Item> SPIDER_LEGS;
    public static final RegistryObject<Item> SHREDDED_LEAVES;
    public static final RegistryObject<Item> SALT;
    public static final RegistryObject<Item> SALTED_FISH;
    public static final RegistryObject<Item> DOUGH_SLICE;
    public static final RegistryObject<Item> HOGLIN_RIB;
    public static final RegistryObject<Item> BAD_FLESH;
    public static final RegistryObject<Item> BAM_BAM_SMOKED_MEAT;
    public static final RegistryObject<Item> MINCED_BAD_FLESH;
    public static final RegistryObject<Item> BAD_SAUSAGE;
    public static final RegistryObject<Item> SILVERFISH_MEATBALL;
    public static final RegistryObject<Item> GOAT_MILK_BUCKET;
    public static final RegistryObject<Item> CREEPER_GAS;

    /*食物*/
    public static final RegistryObject<Item> WEED_SALAD;
    public static final RegistryObject<Item> MOSS_ROLLS_ROTTING_FLESH;
    public static final RegistryObject<Item> MIXED_FRUIT_PIE;
    public static final RegistryObject<Item> MIXED_FRUIT_PIE_SLICE;
    public static final RegistryObject<Item> SUGARCANE_JUICE;
    public static final RegistryObject<Item> SPIDER_LEGS_WITH_VEGETABLES;
    public static final RegistryObject<Item> BAKE_SPIDER_LEGS;
    public static final RegistryObject<Item> BAKE_HOGLIN_RIB;
    public static final RegistryObject<Item> BAMBOO_COOKED_RICE;
    public static final RegistryObject<Item> SLIME_SAUCE;
    public static final RegistryObject<Item> SCENTED_TEA;
    public static final RegistryObject<Item> MUSHROOM_BEEF_STEW;
    public static final RegistryObject<Item> ENERGY_BAR;
    public static final RegistryObject<Item> SILVERFISH_MEATBALL_SOUP;
    public static final RegistryObject<Item> GOLD_PLATING_APPLE;
    public static final RegistryObject<Item> SALTED_FISH_SANDWICH;
    public static final RegistryObject<Item> ZOMBIE_OIL_BLOCK;
    public static final RegistryObject<Item> SOFT_CHEESE_CUBE;
    public static final RegistryObject<Item> TOMATO_IN_OIL;
    public static final RegistryObject<Item> CHEESE_LASAGNA;
    public static final RegistryObject<Item> ROAST_CREAM_MIXED_MONSTER;
    public static final RegistryObject<Item> HUMAN_SAUCE_BREAD;
    public static final RegistryObject<Item> END_SOFT_SWEETS;
    public static final RegistryObject<Item> BAD_HOT_DOG;
    public static final RegistryObject<Item> SHULKER_BOX_LUNCH;
    public static final RegistryObject<Item> RAW_BARBECUE;
    public static final RegistryObject<Item> BLAZE_BARBECUE;
    public static final RegistryObject<Item> SPRING_DRINKS;
    public static final RegistryObject<Item> SPIDER_LEG_TEMPURA;
    public static final RegistryObject<Item> SMOKED_ROUGAMO;
    public static final RegistryObject<Item> PASTA_WITH_SMOKED_MEAT;
    public static final RegistryObject<Item> SHROOMLIGHT_BREAD;
    public static final RegistryObject<Item> SHROOMLIGHT_HAMBURGER;
    public static final RegistryObject<Item> ZOMBIE_OIL_NIAN_GAO;


    static {
        /*方块物品*/
        HANG_LANTERN = block(UdBlocks.HANG_LANTERN);
        CLAY_STOVE = block(UdBlocks.CLAY_STOVE);
        SOFT_CHEESE = block(UdBlocks.SOFT_CHEESE);
        WELL_SALT_WATER_BUCKET = REGISTER.register("well_salt_water_bucket",()->new BucketItem(UdFluids.SALT_WATER,new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
        MOSS_PLANKS = block(UdBlocks.MOSS_PLANKS);
        MOSS_PLANKS_SLAB = block(UdBlocks.MOSS_PLANKS_SLAB);
        MOSS_PLANKS_STAIRS = block(UdBlocks.MOSS_PLANKS_STAIRS);
        LOOSE_SALT_BLOCK = block(UdBlocks.LOOSE_SALT_BLOCK);
        STONE_BRICKS_TRAP = block(UdBlocks.STONE_BRICKS_TRAP);
        SMOKED_MEAT_HOOK = block(UdBlocks.SMOKED_MEAT_HOOK);

        /*食材*/
        SPIDER_LEGS = food("spider_legs",UdFood.SPIDER_LEGS);
        SHREDDED_LEAVES = item("shredded_leaves");
        SALT = item("salt");
        SALTED_FISH = food("salted_fish",UdFood.SALTED_FISH);
        DOUGH_SLICE = item("dough_slice");
        HOGLIN_RIB = food("hoglin_rib",UdFood.HOGLIN_RIB);
        BAD_FLESH = badFood("bad_flesh",UdFood.BAD_FLESH);
        BAM_BAM_SMOKED_MEAT = food("bam_bam_smoked_meat",UdFood.BAM_BAM_SMOKED_MEAT,true);
        MINCED_BAD_FLESH = badFood("minced_bad_flesh",UdFood.MINCED_BAD_FLESH);
        BAD_SAUSAGE = badFood("bad_sausage",UdFood.BAD_SAUSAGE);
        GOAT_MILK_BUCKET = REGISTER.register("goat_milk_bucket",()->
                new MilkBucketItem(new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET)));
        CREEPER_GAS = REGISTER.register("creeper_gas",()->
                new Item(new Item.Properties().stacksTo(16).rarity(Rarity.RARE).craftRemainder(Items.GLASS_BOTTLE)));
        /*食物*/
        WEED_SALAD = REGISTER.register("weed_salad",()->
                new BowlFoodItem(new Item.Properties().food(UdFood.WEED_SALAD).stacksTo(16)));

        MOSS_ROLLS_ROTTING_FLESH = food("moss_rolls_rotting_flesh",UdFood.MOSS_ROLLS_ROTTING_FLESH);

        MIXED_FRUIT_PIE = block(UdBlocks.MIXED_FRUIT_PIE);

        MIXED_FRUIT_PIE_SLICE = food("mixed_fruit_pie_slice",UdFood.MIXED_FRUIT_PIE_SLICE);

        SUGARCANE_JUICE = REGISTER.register("sugarcane_juice",()->
                new SugarcaneJuiceItem(new Item.Properties().stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));

        SPIDER_LEGS_WITH_VEGETABLES = REGISTER.register("spider_legs_with_vegetables",()->
                new BowlFoodItem(new Item.Properties().food(UdFood.SPIDER_LEGS_WITH_VEGETABLES).stacksTo(16)));

        BAKE_SPIDER_LEGS = food("bake_spider_legs",UdFood.BAKE_SPIDER_LEGS);

        BAKE_HOGLIN_RIB = food("bake_hoglin_rib",UdFood.BAKE_HOGLIN_RIB);

        BAMBOO_COOKED_RICE = block(UdBlocks.BAMBOO_COOKED_RICE);

        SLIME_SAUCE = food("slime_sauce",UdFood.SLIME_SAUCE);

        SCENTED_TEA = REGISTER.register("scented_tea", ScentedTeaItem::new);

        MUSHROOM_BEEF_STEW = bowlFoodItem("mushroom_beef_stew",UdFood.MUSHROOM_BEEF_STEW,true);

        ENERGY_BAR = food("energy_bar",UdFood.ENERGY_BAR,true);

        SILVERFISH_MEATBALL = food("silverfish_meatball",UdFood.SILVERFISH_MEATBALL);

        SILVERFISH_MEATBALL_SOUP = bowlFoodItem("silverfish_meatball_soup",UdFood.SILVERFISH_MEATBALL_SOUP,true);

        GOLD_PLATING_APPLE = food("gold_plating_apple",UdFood.GOLD_PLATING_APPLE);

        SALTED_FISH_SANDWICH = food("salted_fish_sandwich",UdFood.SALTED_FISH_SANDWICH);

        ZOMBIE_OIL_BLOCK = item("zombie_oil_block");

        SOFT_CHEESE_CUBE = food("soft_cheese_cube",UdFood.SOFT_CHEESE_CUBE,true);

        TOMATO_IN_OIL = food("tomato_in_oil",UdFood.TOMATO_IN_OIL,true);

        CHEESE_LASAGNA = foodBlock(UdBlocks.CHEESE_LASAGNA);

        ROAST_CREAM_MIXED_MONSTER = foodBlock(UdBlocks.ROAST_CREAM_MIXED_MONSTER);

        HUMAN_SAUCE_BREAD = REGISTER.register("human_sauce_bread", HumanSauceBreadItem::new);

        END_SOFT_SWEETS = food("end_soft_sweets", UdFood.END_SOFT_SWEETS,true);

        BAD_HOT_DOG = food("bad_hot_dog",UdFood.BAD_HOT_DOG,true);

        SHULKER_BOX_LUNCH = REGISTER.register("shulker_box_lunch",()->
                new BlockItem(UdBlocks.SHULKER_BOX_LUNCH.get(), new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)));

        RAW_BARBECUE = bowlFoodItem("raw_barbecue",UdFood.RAW_BARBECUE);

        BLAZE_BARBECUE = bowlFoodItem("blaze_barbecue",UdFood.BLAZE_BARBECUE,true);

        SPRING_DRINKS = REGISTER.register("spring_drinks",()->
                new DrinkableItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(16).food(UdFood.SPRING_DRINKS),true));

        SPIDER_LEG_TEMPURA = food("spider_leg_tempura",UdFood.SPIDER_LEG_TEMPURA);

        SMOKED_ROUGAMO = food("smoked_rougamo",UdFood.SMOKED_ROUGAMO,true);

        SHROOMLIGHT_BREAD = food("shroomlight_bread",UdFood.SHROOMLIGHT_BREAD,true);

        SHROOMLIGHT_HAMBURGER = food("shroomlight_hamburger",UdFood.SHROOMLIGHT_HAMBURGER,true);

        ZOMBIE_OIL_NIAN_GAO = food("zombie_oil_nian_gao",UdFood.ZOMBIE_OIL_NIAN_GAO,true);

        PASTA_WITH_SMOKED_MEAT = bowlFoodItem("pasta_with_smoked_meat",UdFood.PASTA_WITH_SMOKED_MEAT,true);
    }


    private static RegistryObject<Item> item(String pName) {
        return REGISTER.register(pName, () -> new Item(new Item.Properties()));
    }

    private static RegistryObject<Item> block(RegistryObject<Block> block) {
        return REGISTER.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static RegistryObject<Item> foodBlock(RegistryObject<Block> block) {
        return REGISTER.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().stacksTo(1)));
    }

    private static RegistryObject<Item> food(String pName, FoodProperties foodProperties) {
        return REGISTER.register(pName, () -> new Item(new Item.Properties().food(foodProperties)));
    }

    private static RegistryObject<Item> food(String pName, FoodProperties foodProperties,boolean tips) {
        return REGISTER.register(pName, () -> new ConsumableItem(new Item.Properties().food(foodProperties),tips));
    }

    private static RegistryObject<Item> bowlFoodItem(String pName,FoodProperties foodProperties) {
        return REGISTER.register(pName, () -> new BowlFoodItem(new Item.Properties().food(foodProperties).stacksTo(16)));
    }

    private static RegistryObject<Item> bowlFoodItem(String pName,FoodProperties foodProperties,boolean tips) {
        return REGISTER.register(pName, () -> new ConsumableItem(new Item.Properties().food(foodProperties).stacksTo(16).craftRemainder(Items.BOWL),tips));
    }

    private static RegistryObject<Item> badFood(String pName, FoodProperties foodProperties) {
        return REGISTER.register(pName, () -> new BadMeatItem(new Item.Properties().food(foodProperties)));
    }

    public static RegistryObject<Item> register(String name, Supplier<Item> supplier) {
        return REGISTER.register(name, supplier);
    }
}
