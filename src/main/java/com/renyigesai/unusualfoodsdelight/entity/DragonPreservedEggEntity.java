package com.renyigesai.unusualfoodsdelight.entity;

import com.renyigesai.unusualfoodsdelight.UnusualFoodDelight;
import com.renyigesai.unusualfoodsdelight.init.UdEntityTypes;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class DragonPreservedEggEntity extends FallingBlockEntity {


    public DragonPreservedEggEntity(EntityType<? extends FallingBlockEntity> p_31950_, Level p_31951_) {
        super(p_31950_, p_31951_);
    }

    private DragonPreservedEggEntity(Level level, double x, double y, double z, BlockState state) {
        this(UdEntityTypes.DRAGON_PRESERVED_EGG.get(), level);
        this.blockState = state;
        this.blocksBuilding = true;
        this.setPos(x, y, z);
        this.setDeltaMovement(Vec3.ZERO);
        this.xo = x;
        this.yo = y;
        this.zo = z;
        this.setStartPos(this.blockPosition());
    }

    @Override
    public void tick() {
        if (this.time < 100){
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, +0.0025, 0.0D));
            if (level() instanceof ServerLevel serverLevel){
                serverLevel.sendParticles(ParticleTypes.DRAGON_BREATH,this.getX(),this.getY(0.5),this.getZ(),2,0.5,0.5,0.5,0.0025);
            }
        }else {
            setNoGravity(false);
        }
        super.tick();
    }

    public static DragonPreservedEggEntity up(Level p_201972_, BlockPos p_201973_, BlockState p_201974_) {
        DragonPreservedEggEntity fallingblockentity = new DragonPreservedEggEntity(p_201972_, (double)p_201973_.getX() + 0.5, (double)p_201973_.getY(), (double)p_201973_.getZ() + 0.5, p_201974_.hasProperty(BlockStateProperties.WATERLOGGED) ? (BlockState)p_201974_.setValue(BlockStateProperties.WATERLOGGED, false) : p_201974_);
        p_201972_.setBlock(p_201973_, p_201974_.getFluidState().createLegacyBlock(), 3);
        fallingblockentity.setNoGravity(true);
        p_201972_.addFreshEntity(fallingblockentity);
        return fallingblockentity;
    }
}
