package com.renyigesai.unusualfoodsdelight.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.renyigesai.unusualfoodsdelight.block.clay_stove.ClayStoveBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec2;
import net.minecraftforge.items.ItemStackHandler;
import vectorwing.farmersdelight.common.block.StoveBlock;

public class ClayStoveRenderer implements BlockEntityRenderer<ClayStoveBlockEntity> {
    public ClayStoveRenderer(BlockEntityRendererProvider.Context context){

    }
    @Override
    public void render(ClayStoveBlockEntity ClayStoveBlockEntity, float v, PoseStack poseStack, MultiBufferSource buffer, int combinedOverlayIn, int i1) {
        Direction direction = ((Direction)ClayStoveBlockEntity.getBlockState().getValue(StoveBlock.FACING)).getOpposite();
        ItemStackHandler inventory = ClayStoveBlockEntity.getInventory();
        int posLong = (int)ClayStoveBlockEntity.getBlockPos().asLong();

        for(int i = 0; i < inventory.getSlots(); ++i) {
            ItemStack stoveStack = inventory.getStackInSlot(i);
            if (!stoveStack.isEmpty()) {
                poseStack.pushPose();
                poseStack.translate(0.5, 1.02, 0.5);
                float f = -direction.toYRot();
                poseStack.mulPose(Axis.YP.rotationDegrees(f));
                poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
                Vec2 itemOffset = ClayStoveBlockEntity.getStoveItemOffset(i);
                poseStack.translate((double)itemOffset.x, (double)itemOffset.y, 0.0);
                poseStack.scale(0.375F, 0.375F, 0.375F);
                if (ClayStoveBlockEntity.getLevel() != null) {
                    Minecraft.getInstance().getItemRenderer().renderStatic(stoveStack, ItemDisplayContext.FIXED, LevelRenderer.getLightColor(ClayStoveBlockEntity.getLevel(), ClayStoveBlockEntity.getBlockPos().above()), combinedOverlayIn, poseStack, buffer, ClayStoveBlockEntity.getLevel(), posLong + i);
                }

                poseStack.popPose();
            }
        }
    }
}
