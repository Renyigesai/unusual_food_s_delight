package com.renyigesai.unusualfoodsdelight.block;

import com.renyigesai.unusualfoodsdelight.init.UdBlocks;
import com.renyigesai.unusualfoodsdelight.init.UdItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.common.utility.ItemUtils;

public class InfestedCheeseBlock extends InfestedBlock {
    public static final VoxelShape BOX = box(3.0,0.0,3.0,13.0,8.0,13.0);
    public static final BooleanProperty CUT = BooleanProperty.create("cut");
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public InfestedCheeseBlock(Properties p_54179_) {
        super(UdBlocks.SOFT_CHEESE.get(), p_54179_);
        this.registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH).setValue(CUT,false));
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        ItemStack itemInHand = player.getItemInHand(hand);
        if (isKnifeItem(itemInHand)){
            Direction direction = player.getDirection().getOpposite();
            if (!state.getValue(CUT)){
                level.setBlock(pos,state.setValue(CUT,true),3);
                spawnInfestation(level,pos,direction);
            }else {
                level.removeBlock(pos,false);
                ItemUtils.spawnItemEntity(level, new ItemStack(UdItems.INFESTED_CHEESE_CUBE.get(),4), (double)pos.getX() + 0.5, (double)pos.getY() + 0.3, (double)pos.getZ() + 0.5, (double)direction.getStepX() * 0.15, 0.05, (double)direction.getStepZ() * 0.15);
            }
            level.playSound(null,pos, SoundEvents.HONEY_BLOCK_PLACE, SoundSource.BLOCKS);
            return InteractionResult.SUCCESS;
        }
        return super.use(state, level, pos, player, hand, result);
    }

    @Override
    public void spawnAfterBreak(BlockState p_221360_, ServerLevel p_221361_, BlockPos p_221362_, ItemStack p_221363_, boolean p_221364_) {

    }

    private void spawnInfestation(Level level, BlockPos pos,Direction direction) {
        if (level instanceof ServerLevel serverLevel) {
            Silverfish silverfish = EntityType.SILVERFISH.create(serverLevel);
            if (silverfish != null) {
                silverfish.moveTo(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, 0.0F, 0.0F);
                silverfish.setDeltaMovement(direction.getStepX() * 0.35, 0.35, direction.getStepZ() * 0.35);
                serverLevel.addFreshEntity(silverfish);
                silverfish.spawnAnim();
            }
        }
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return BOX;
    }

    public BlockState rotate(BlockState p_54125_, Rotation p_54126_) {
        return p_54125_.setValue(FACING, p_54126_.rotate(p_54125_.getValue(FACING)));
    }

    public BlockState mirror(BlockState p_54122_, Mirror p_54123_) {
        return p_54122_.rotate(p_54123_.getRotation(p_54122_.getValue(FACING)));
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
        stateBuilder.add(FACING,CUT);
    }
    @SuppressWarnings("all")
    public boolean isKnifeItem(ItemStack itemStack) {
        return itemStack.is(ModTags.KNIVES) || itemStack.is(ItemTags.create(new ResourceLocation("forge:tools/knives")));
    }
}
