package com.renyigesai.unusualfoodsdelight.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.utility.ItemUtils;

public class BambooCookedRiceBlock extends HorizontalDirectionalBlock {
    public static final IntegerProperty BITES = IntegerProperty.create("bites",0,3);

    public BambooCookedRiceBlock(Properties p_54120_) {
        super(p_54120_);
        this.registerDefaultState(defaultBlockState().setValue(BITES,0).setValue(FACING, Direction.NORTH));

    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            default -> box(3, 0, 5, 14, 3, 10);
            case NORTH -> box(2, 0, 6, 13, 3, 11);
            case EAST -> box(5, 0, 2, 10, 3, 13);
            case WEST -> box(6, 0, 3, 11, 3, 14);
        };
    }



    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult p_60508_) {
        return level.isClientSide && this.eat(state, level, pos, player).consumesAction() ? InteractionResult.SUCCESS : this.eat(state, level, pos, player);
    }

    private InteractionResult eat(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer){
        int bites = pState.getValue(BITES);
        if (!pPlayer.canEat(false)){
            return InteractionResult.PASS;
        }
        if (bites == 3){
            ItemUtils.spawnItemEntity(pLevel,new ItemStack(Items.BAMBOO),(double)pPos.getX() + 0.5, (double)pPos.getY() + 0.3, (double)pPos.getZ() + 0.5, 0.0,0.0,0.0);
            pLevel.destroyBlock(pPos,false);
            return InteractionResult.SUCCESS;
        }
        pPlayer.getFoodData().eat(5,5f);
        pLevel.setBlock(pPos,pState.setValue(BITES,bites+1),3);
        pLevel.playSound(null,pPos, SoundEvents.GENERIC_EAT, SoundSource.PLAYERS, 0.8F, 0.8F);
        return InteractionResult.SUCCESS;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection());
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        return facing == Direction.DOWN && !stateIn.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).isSolid();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(BITES,FACING);
    }
}
