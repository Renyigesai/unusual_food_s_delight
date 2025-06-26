package com.renyigesai.unusualfoodsdelight.block.smoked_meat_campfire;

import com.renyigesai.unusualfoodsdelight.init.UdBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SmokedMeatCampfireBlockEntity extends BlockEntity {

    private int conversionTime;

    public SmokedMeatCampfireBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(UdBlocks.SMOKED_MEAT_CAMPFIRE_ENTITY.get(), pPos, pBlockState);
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, SmokedMeatCampfireBlockEntity pBlockEntity) {
        pBlockEntity.conversionTime ++;
        if (pBlockEntity.conversionTime >= 3600){
            pLevel.setBlock(pPos, Blocks.CAMPFIRE.defaultBlockState(),3);
        }
        setChanged(pLevel, pPos, pState);
    }

    public static void particleTick(Level pLevel, BlockPos pPos, BlockState pState, SmokedMeatCampfireBlockEntity pBlockEntity) {
        RandomSource randomsource = pLevel.random;
        if (randomsource.nextFloat() < 0.11F) {
            for(int i = 0; i < randomsource.nextInt(2) + 2; ++i) {
                SmokedMeatCampfireBlock.makeParticles(pLevel, pPos, false);
            }
        }
    }

    public void load(CompoundTag pTag) {
        super.load(pTag);
        if (pTag.contains("ConversionTime")) {
            this.conversionTime = pTag.getInt("ConversionTime");
        }
    }

    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putInt("ConversionTime",this.conversionTime);
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }


    private void markUpdated() {
        this.setChanged();
        this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
    }

    public void dowse() {
        if (this.level != null) {
            this.markUpdated();
        }

    }
}
