package com.renyigesai.unusualfoodsdelight.block.smoked_meat_hook;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class SmokedMeatHookBlockEntityRender implements BlockEntityRenderer<SmokedMeatHookBlockEntity> {


    public SmokedMeatHookBlockEntityRender(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(SmokedMeatHookBlockEntity entity, float v, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        Direction direction = entity.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING).getOpposite();
        NonNullList<ItemStack> inventory = entity.getItems();
        ItemStack stack = inventory.get(0);
        if (!stack.isEmpty()){
            poseStack.pushPose();
            poseStack.translate(0.5,0.5,0.5);
            poseStack.mulPose(Axis.YP.rotationDegrees(-direction.toYRot()-180f));
            poseStack.translate(0.0f,-0.5f,0.0f);
            poseStack.scale(0.4f,0.4f,0.4f);
            if (entity.getLevel() != null){
                Minecraft.getInstance().getItemRenderer().renderStatic(
                        stack, ItemDisplayContext.FIXED, LevelRenderer.getLightColor(entity.getLevel(),entity.getBlockPos()),packedOverlay,poseStack,buffer,entity.getLevel(),1);
            }
            poseStack.popPose();
        }
    }
}
