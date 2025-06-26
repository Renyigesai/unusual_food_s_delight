package com.renyigesai.unusualfoodsdelight.block;

import com.renyigesai.unusualfoodsdelight.init.UdItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.common.utility.ItemUtils;

public class SoftCheeseBlock extends HorizontalDirectionalBlock {
    public static final VoxelShape BOX = box(4.0,0.0,4.0,12.0,3.0,12.0);
    public static final BooleanProperty CUT = BooleanProperty.create("cut");
    public SoftCheeseBlock(Properties p_54120_) {
        super(p_54120_);
        this.registerDefaultState(defaultBlockState().setValue(CUT,false).setValue(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return BOX;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        ItemStack hand1 = player.getItemInHand(hand);
        if (hand1.is(ModTags.KNIVES)){
            return cut(state, level, pos,player);
        }
        return super.use(state, level, pos, player, hand, hitResult);
    }

    public InteractionResult cut(BlockState state, Level level, BlockPos pos,Player player){
        if (!state.getValue(CUT)){
            level.setBlock(pos,state.setValue(CUT,true),3);
        }else {
            Direction direction = player.getDirection().getOpposite();
            ItemUtils.spawnItemEntity(level, new ItemStack(UdItems.SOFT_CHEESE_CUBE.get(),4), (double)pos.getX() + 0.5, (double)pos.getY() + 0.3, (double)pos.getZ() + 0.5, (double)direction.getStepX() * 0.15, 0.05, (double)direction.getStepZ() * 0.15);
            level.removeBlock(pos,false);
        }
        level.playSound(null,pos, SoundEvents.HONEY_BLOCK_PLACE, SoundSource.BLOCKS);
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
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(CUT,FACING);
    }
}
