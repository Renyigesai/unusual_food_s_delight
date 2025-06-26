package com.renyigesai.unusualfoodsdelight.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
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
import vectorwing.farmersdelight.common.registry.ModEffects;

public class ShulkerBoxLunchBlock extends HorizontalDirectionalBlock {
    public static final VoxelShape  BOX = box(1.0,0.0,1.0,15.0,8.0,15.0);
    public static final IntegerProperty BITES = IntegerProperty.create("bites",0,4);

    public ShulkerBoxLunchBlock(Properties p_54120_) {
        super(p_54120_);
        this.registerDefaultState(defaultBlockState().setValue(BITES,0).setValue(FACING, Direction.NORTH));

    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return BOX;
    }


    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult p_60508_) {
        return level.isClientSide && this.eat(state, level, pos, player).consumesAction() ? InteractionResult.SUCCESS : this.eat(state, level, pos, player);
    }

    private InteractionResult eat(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer){
        int bites = pState.getValue(BITES);
        if (bites == 4){
            pLevel.destroyBlock(pPos,false);
            return InteractionResult.SUCCESS;
        }
        if (pPlayer.canEat(false)) {
            pPlayer.getFoodData().eat(5, 6.5f);
            pPlayer.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 40));
            pPlayer.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 3));
            pPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 4));
            pPlayer.addEffect(new MobEffectInstance(ModEffects.COMFORT.get(), 1200));
            pLevel.setBlock(pPos, pState.setValue(BITES, bites + 1), 3);
            pLevel.playSound(null, pPos, SoundEvents.GENERIC_EAT, SoundSource.PLAYERS, 0.8F, 0.8F);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
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
