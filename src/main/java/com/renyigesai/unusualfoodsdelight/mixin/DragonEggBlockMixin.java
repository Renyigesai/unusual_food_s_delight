package com.renyigesai.unusualfoodsdelight.mixin;

import com.renyigesai.unusualfoodsdelight.Config;
import com.renyigesai.unusualfoodsdelight.UnusualFoodDelight;
import com.renyigesai.unusualfoodsdelight.entity.DragonPreservedEggEntity;
import com.renyigesai.unusualfoodsdelight.init.UdBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.DragonEggBlock;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(DragonEggBlock.class)
public abstract class DragonEggBlockMixin extends FallingBlock {
    public DragonEggBlockMixin(Properties p_53205_) {
        super(p_53205_);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource source) {
        if (source.nextInt(5) == 0) {
            boolean flag = true;
            for (Direction direction : Direction.values()) {
                BlockPos relative = pos.relative(direction);
                BlockState block = level.getBlockState(relative);
                if (direction != Direction.DOWN && (!Config.DRAGON_EGG_CONVERTING_BLOCKS.contains(block.getBlock()) || block.isAir())) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                DragonPreservedEggEntity fallingBlockEntity = DragonPreservedEggEntity.up(level, pos, UdBlocks.DRAGON_PRESERVED_EGG.get().defaultBlockState());
                level.addFreshEntity(fallingBlockEntity);
                for (Direction direction : Direction.values()) {
                    if (direction != Direction.DOWN) {
                        BlockPos relative = pos.relative(direction);
                        level.destroyBlock(relative, false);
                    }
                }
                Vec3 vec3 = new Vec3(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                for (ServerPlayer player : level.getEntitiesOfClass(ServerPlayer.class, AABB.ofSize(vec3, 17.0, 17.0, 17.0))) {
                     UnusualFoodDelight.DRAGON_PRESERVED_EGG_TRIGGER.trigger(player);
                }
            }
        }
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }
}
