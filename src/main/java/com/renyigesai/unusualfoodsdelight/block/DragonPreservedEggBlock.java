package com.renyigesai.unusualfoodsdelight.block;

import com.renyigesai.unusualfoodsdelight.init.UdMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DragonPreservedEggBlock extends FallingBlock {
    protected static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
    public static final BooleanProperty KNOCK = BooleanProperty.create("knock");
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public DragonPreservedEggBlock(BlockBehaviour.Properties p_52911_) {
        super(p_52911_);
        this.registerDefaultState(defaultBlockState().setValue(KNOCK,false).setValue(FACING, Direction.NORTH));
    }

    public VoxelShape getShape(BlockState p_52930_, BlockGetter p_52931_, BlockPos p_52932_, CollisionContext p_52933_) {
        return SHAPE;
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.isClientSide){
            return InteractionResult.SUCCESS;
        }
        return knockOrEat(state, level, pos, player, hand);
    }

    private InteractionResult knockOrEat(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand){
        boolean knock = state.getValue(KNOCK);
        ItemStack itemInHand = player.getItemInHand(hand);
        boolean flag = itemInHand.is(ItemTags.PICKAXES);
        if (!knock){
            if (flag){
                level.setBlock(pos,state.setValue(KNOCK,true),2);
                level.playSound(null,pos, SoundEvents.SNIFFER_EGG_CRACK, SoundSource.BLOCKS);
            }else {
                player.displayClientMessage(Component.translatable("tooltip.unusualfoodsdelight.dragon_preserved_egg"),true);
            }
            return flag ? InteractionResult.SUCCESS : InteractionResult.FAIL;
        }else {
            player.getFoodData().eat(20,0.5f );
            player.addEffect(new MobEffectInstance(UdMobEffects.INSTANT_SHADOW.get(),6000));
            level.removeBlock(pos,flag);
            level.gameEvent(player, GameEvent.EAT, pos);
            level.gameEvent(player, GameEvent.BLOCK_DESTROY, pos);
            level.playSound(null,pos, SoundEvents.GENERIC_EAT, SoundSource.PLAYERS, 0.8F, 0.8F);
            return InteractionResult.SUCCESS;
        }
    }

    public void tick(BlockState p_277544_, ServerLevel p_277779_, BlockPos p_278019_, RandomSource p_277471_) {
        if (FallingBlock.isFree(p_277779_.getBlockState(p_278019_.below())) && p_278019_.getY() >= p_277779_.getMinBuildHeight()) {
            FallingBlockEntity fallingblockentity = FallingBlockEntity.fall(p_277779_, p_278019_, p_277544_);
            fallingblockentity.disableDrop();
        }
    }

    @Override
    public void onBrokenAfterFall(Level level, BlockPos pos, FallingBlockEntity fallingBlock) {
        Vec3 vec3 = fallingBlock.getBoundingBox().getCenter();
        level.levelEvent(2001, BlockPos.containing(vec3), Block.getId(fallingBlock.getBlockState()));
        level.gameEvent(fallingBlock, GameEvent.BLOCK_DESTROY, vec3);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection());
    }

    public BlockState rotate(BlockState p_54125_, Rotation p_54126_) {
        return p_54125_.setValue(FACING, p_54126_.rotate(p_54125_.getValue(FACING)));
    }

    public BlockState mirror(BlockState p_54122_, Mirror p_54123_) {
        return p_54122_.rotate(p_54123_.getRotation(p_54122_.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(KNOCK,FACING);
    }

    public boolean isPathfindable(BlockState p_52913_, BlockGetter p_52914_, BlockPos p_52915_, PathComputationType p_52916_) {
        return false;
    }
}