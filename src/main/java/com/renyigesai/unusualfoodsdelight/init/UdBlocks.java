package com.renyigesai.unusualfoodsdelight.init;

import com.renyigesai.unusualfoodsdelight.UnusualFoodDelight;
import com.renyigesai.unusualfoodsdelight.block.*;
import com.renyigesai.unusualfoodsdelight.block.clay_stove.ClayStoveBlock;
import com.renyigesai.unusualfoodsdelight.block.clay_stove.ClayStoveBlockEntity;
import com.renyigesai.unusualfoodsdelight.block.hang_lantern.HangLanternBlock;
import com.renyigesai.unusualfoodsdelight.block.hang_lantern.HangLanternBlockEntity;
import com.renyigesai.unusualfoodsdelight.block.smoked_meat_campfire.SmokedMeatCampfireBlock;
import com.renyigesai.unusualfoodsdelight.block.smoked_meat_campfire.SmokedMeatCampfireBlockEntity;
import com.renyigesai.unusualfoodsdelight.block.smoked_meat_hook.SmokedMeatHookBlock;
import com.renyigesai.unusualfoodsdelight.block.smoked_meat_hook.SmokedMeatHookBlockEntity;
import com.renyigesai.unusualfoodsdelight.block.stone_bricks_trap.StoneBricksTrapBlock;
import com.renyigesai.unusualfoodsdelight.fluid.SaltWaterFluidsBlock;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.block.PieBlock;
import vectorwing.farmersdelight.common.registry.ModBlocks;

import java.util.function.ToIntFunction;

public class UdBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, UnusualFoodDelight.MODID);

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, UnusualFoodDelight.MODID);

    private static ToIntFunction<BlockState> litBlockEmission(int lightValue) {
        return (state) -> state.getValue(BlockStateProperties.LIT) ? lightValue : 0;
    }

    public static final RegistryObject<Block> HANG_LANTERN;
    public static final RegistryObject<BlockEntityType<HangLanternBlockEntity>> HANG_LANTERN_ENTITY;
    public static final RegistryObject<Block> CLAY_STOVE;
    public static final RegistryObject<BlockEntityType<ClayStoveBlockEntity>> CLAY_STOVE_ENTITY;
    public static final RegistryObject<Block> SMOKED_MEAT_CAMPFIRE;
    public static final RegistryObject<BlockEntityType<SmokedMeatCampfireBlockEntity>> SMOKED_MEAT_CAMPFIRE_ENTITY;
    public static final RegistryObject<Block> SMOKED_MEAT_HOOK;
    public static final RegistryObject<BlockEntityType<SmokedMeatHookBlockEntity>> SMOKED_MEAT_HOOK_ENTITY;

    static {
        HANG_LANTERN = BLOCKS.register("hang_lantern",()->
                new HangLanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN)));

        HANG_LANTERN_ENTITY = BLOCK_ENTITY_REGISTRY.register("hang_lantern_entity",
                ()-> BlockEntityType.Builder.of(HangLanternBlockEntity::new, HANG_LANTERN.get()).build(null));

        CLAY_STOVE = BLOCKS.register("clay_stove",()->
                new ClayStoveBlock(BlockBehaviour.Properties.copy(Blocks.BRICKS).lightLevel(litBlockEmission(13))));

        CLAY_STOVE_ENTITY = BLOCK_ENTITY_REGISTRY.register("clay_stove_entity",
                ()-> BlockEntityType.Builder.of(ClayStoveBlockEntity::new, CLAY_STOVE.get()).build(null));

        SMOKED_MEAT_CAMPFIRE = BLOCKS.register("smoked_meat_campfire",()->
                new SmokedMeatCampfireBlock(true, 1, BlockBehaviour.Properties.of().mapColor(MapColor.PODZOL).instrument(NoteBlockInstrument.BASS).strength(2.0F).lightLevel((state) -> 15).sound(SoundType.WOOD).noOcclusion().ignitedByLava()));

        SMOKED_MEAT_CAMPFIRE_ENTITY = BLOCK_ENTITY_REGISTRY.register("smoked_meat_campfire_entity",
                ()-> BlockEntityType.Builder.of(SmokedMeatCampfireBlockEntity::new, SMOKED_MEAT_CAMPFIRE.get()).build(null));

        SMOKED_MEAT_HOOK = BLOCKS.register("smoked_meat_hook",()->
                new SmokedMeatHookBlock(BlockBehaviour.Properties.of().forceSolidOn().requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.CHAIN).noOcclusion()));

        SMOKED_MEAT_HOOK_ENTITY = BLOCK_ENTITY_REGISTRY.register("smoked_meat_hook_entity",
                ()-> BlockEntityType.Builder.of(SmokedMeatHookBlockEntity::new, SMOKED_MEAT_HOOK.get()).build(null));
    }

    public static final RegistryObject<Block> BAMBOO_COOKED_RICE = BLOCKS.register("bamboo_cooked_rice",()->
            new BambooCookedRiceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(1f).sound(SoundType.BAMBOO)));

    public static final RegistryObject<Block> MIXED_FRUIT_PIE = BLOCKS.register("mixed_fruit_pie",()->
            new PieBlock(BlockBehaviour.Properties.copy(Blocks.CAKE),UdItems.MIXED_FRUIT_PIE_SLICE));

    public static final RegistryObject<Block> SHULKER_BOX_LUNCH = BLOCKS.register("shulker_box_lunch",()->
            new ShulkerBoxLunchBlock(BlockBehaviour.Properties.of().strength(2f).mapColor(MapColor.COLOR_PURPLE)));

    public static final RegistryObject<Block> CHEESE_LASAGNA = BLOCKS.register("cheese_lasagna",()->
            new CheeseLasagnaBlock(BlockBehaviour.Properties.copy(Blocks.CAKE)));

    public static final RegistryObject<Block> ROAST_CREAM_MIXED_MONSTER = BLOCKS.register("roast_cream_mixed_monster",()->
            new RoastCreamMixedMonsterBlock(BlockBehaviour.Properties.copy(Blocks.CAKE)));

    public static final RegistryObject<Block> SOFT_CHEESE = BLOCKS.register("soft_cheese",()->
            new SoftCheeseBlock(BlockBehaviour.Properties.of().strength(0.5f).sound(SoundType.HONEY_BLOCK)));

    public static final RegistryObject<LiquidBlock> SALT_WATER_BLOCK = BLOCKS.register("salt_water_block", SaltWaterFluidsBlock::new);

    public static final RegistryObject<Block> MOSS_PLANKS = BLOCKS.register("moss_planks", ()->
            new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistryObject<Block> MOSS_PLANKS_SLAB = BLOCKS.register("moss_planks_slab", ()->
            new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)));

    public static final RegistryObject<Block> MOSS_PLANKS_STAIRS = BLOCKS.register("moss_planks_stairs", ()->
            new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistryObject<Block> LOOSE_SALT_BLOCK = BLOCKS.register("loose_salt_block", ()->
            new Block(BlockBehaviour.Properties.copy(Blocks.SAND).speedFactor(0.4f)));

    public static final RegistryObject<Block> STONE_BRICKS_TRAP = BLOCKS.register("stone_bricks_trap", ()->
            new StoneBricksTrapBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
