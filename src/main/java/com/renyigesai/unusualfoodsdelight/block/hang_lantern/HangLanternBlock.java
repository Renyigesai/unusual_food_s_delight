package com.renyigesai.unusualfoodsdelight.block.hang_lantern;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.Nullable;

public class HangLanternBlock extends BaseEntityBlock implements Equipable {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty ROD = BooleanProperty.create("rod");
    public HangLanternBlock(Properties p_54120_) {
        super(p_54120_);
        this.registerDefaultState(defaultBlockState().setValue(ROD,false).setValue(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        if (!state.getValue(ROD)) {
            return switch (state.getValue(FACING)) {
                default ->
                        Shapes.or(box(5, 0, 5, 11, 7, 11), box(6, 7, 6, 10, 9, 10), box(7, 13, 0, 9, 15, 9), box(7.5, 9, 7.5, 8.5, 15.5, 8.5));
                case NORTH ->
                        Shapes.or(box(5, 0, 5, 11, 7, 11), box(6, 7, 6, 10, 9, 10), box(7, 13, 7, 9, 15, 16), box(7.5, 9, 7.5, 8.5, 15.5, 8.5));
                case EAST ->
                        Shapes.or(box(5, 0, 5, 11, 7, 11), box(6, 7, 6, 10, 9, 10), box(0, 13, 7, 9, 15, 9), box(7.5, 9, 7.5, 8.5, 15.5, 8.5));
                case WEST ->
                        Shapes.or(box(5, 0, 5, 11, 7, 11), box(6, 7, 6, 10, 9, 10), box(7, 13, 7, 16, 15, 9), box(7.5, 9, 7.5, 8.5, 15.5, 8.5));
            };
        }
        return switch (state.getValue(FACING)) {
            default -> Shapes.or(box(0, -5, 5, 6, 2, 11), box(1, 2, 6, 5, 4, 10));
            case NORTH -> Shapes.or(box(10, -5, 5, 16, 2, 11), box(11, 2, 6, 15, 4, 10));
            case EAST -> Shapes.or(box(5, -5, 10, 11, 2, 16), box(6, 2, 11, 10, 4, 15));
            case WEST -> Shapes.or(box(5, -5, 0, 11, 2, 6), box(6, 2, 1, 10, 4, 5));
        };


    }

    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof HangLanternBlockEntity hangLanternBlockEntity) {
                hangLanternBlockEntity.drops(hangLanternBlockEntity);
                world.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state, world, pos, newState, isMoving);
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack itemStack) {
        super.setPlacedBy(level, pos, state, entity, itemStack);
        BlockState blockState = level.getBlockState(pos.below());
        if (blockState.is(Tags.Blocks.FENCES)){
            level.setBlock(pos,state.setValue(ROD,true),3);
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof HangLanternBlockEntity hangLanternBlockEntity){
                Item item = blockState.getBlock().asItem();
                hangLanternBlockEntity.inventory.setStackInSlot(0,new ItemStack(item));
            }
            level.removeBlock(pos.below(),false);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(ROD,FACING);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new HangLanternBlockEntity(blockPos,blockState);
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.HEAD;
    }
}
