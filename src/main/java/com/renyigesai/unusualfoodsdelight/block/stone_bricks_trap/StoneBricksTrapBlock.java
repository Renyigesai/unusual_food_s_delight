package com.renyigesai.unusualfoodsdelight.block.stone_bricks_trap;

import com.renyigesai.unusualfoodsdelight.block.UdEnumProperty;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class StoneBricksTrapBlock extends Block {
    public static final EnumProperty<UdEnumProperty> TYPE =  EnumProperty.create("type", UdEnumProperty.class);
    public StoneBricksTrapBlock(Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState(defaultBlockState().setValue(TYPE,UdEnumProperty.NONE));
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof LivingEntity livingEntity){
            this.getAttackType(level,pos,state,livingEntity);
        }
        super.stepOn(level, pos, state, entity);

    }

    private void getAttackType(Level level, BlockPos pos, BlockState state,LivingEntity entity){
        if (state.getValue(TYPE) == UdEnumProperty.NONE){
            return;
        }
        if (state.getValue(TYPE) == UdEnumProperty.FIRE){
            this.fireAttack(level,pos,entity);
            return;
        }
        if (state.getValue(TYPE) == UdEnumProperty.POISON){
            this.poisonAttack(level,pos,entity);
            return;
        }
        if (state.getValue(TYPE) == UdEnumProperty.MAGIC){
            this.magicAttack(level,pos,entity);
        }
    }

    private void fireAttack(Level level, BlockPos pos, LivingEntity entity){
        if (!entity.fireImmune()) {
            entity.setRemainingFireTicks(entity.getRemainingFireTicks() + 1);
            if (entity.getRemainingFireTicks() == 0) {
                entity.setSecondsOnFire(8);
            }
        }
        entity.hurt(level.damageSources().inFire(), 3.0F);
        level.addParticle(ParticleTypes.FLAME,pos.getX()+0.5,pos.getY()+1,pos.getZ()+0.5,0.0,0.15,0.0);
        level.addParticle(ParticleTypes.LARGE_SMOKE,pos.getX()+0.5,pos.getY()+1,pos.getZ()+0.5,0.0,0.0,0.0);
    }


    private void poisonAttack(Level level, BlockPos pos, LivingEntity entity){
        entity.hurt(level.damageSources().generic(), 2.0F);
        entity.addEffect(new MobEffectInstance(MobEffects.POISON,200,2));
        level.addParticle(ParticleTypes.CLOUD,pos.getX()+0.5,pos.getY()+1,pos.getZ()+0.5,0.0,0.25,0.0);
    }

    private void magicAttack(Level level, BlockPos pos, LivingEntity entity){
        entity.hurt(level.damageSources().magic(), 4.0F);
        level.addParticle(ParticleTypes.DRAGON_BREATH,pos.getX()+0.5,pos.getY()+1,pos.getZ()+0.5,0.0,0.15,0.0);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_49915_) {
        p_49915_.add(TYPE);
    }
}
