package com.renyigesai.unusualfoodsdelight.block.smoked_meat_hook;

import com.renyigesai.unusualfoodsdelight.block.smoked_meat_campfire.SmokedMeatCampfireBlockEntity;
import com.renyigesai.unusualfoodsdelight.init.UdBlocks;
import com.renyigesai.unusualfoodsdelight.recipes.SmokedMeatRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class SmokedMeatHookBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final VoxelShape BOX = box(6.5, 0.0, 6.5, 9.5, 16.0, 9.5);
    public SmokedMeatHookBlock(Properties p_49224_) {
        super(p_49224_);
        this.registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return BOX;
    }

    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new SmokedMeatHookBlockEntity(blockPos,blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level   , BlockState p_152756_, BlockEntityType<T> p_152757_) {
        return level.isClientSide ? null : createTickerHelper(p_152757_, UdBlocks.SMOKED_MEAT_HOOK_ENTITY.get(),
                SmokedMeatHookBlockEntity::tick);
    }

    public InteractionResult use(BlockState p_51274_, Level level, BlockPos p_51276_, Player player, InteractionHand hand, BlockHitResult p_51279_) {
        BlockEntity blockentity = level.getBlockEntity(p_51276_);
        if (blockentity instanceof SmokedMeatHookBlockEntity blockEntity) {
            ItemStack itemstack = player.getItemInHand(hand);
                if (!level.isClientSide) {
                    blockEntity.placeOrTakeFood(player,itemstack);
                    player.awardStat(Stats.INTERACT_WITH_CAMPFIRE);
                    return InteractionResult.SUCCESS;
                }
                return InteractionResult.CONSUME;
        }

        return InteractionResult.PASS;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection());
    }


    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    public BlockState rotate(BlockState state, LevelAccessor level, BlockPos pos, Rotation direction) {
        return state.setValue(FACING,direction.rotate(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
