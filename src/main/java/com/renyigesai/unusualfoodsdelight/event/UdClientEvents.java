package com.renyigesai.unusualfoodsdelight.event;

import com.google.common.collect.ImmutableList;
import com.renyigesai.unusualfoodsdelight.client.FullbrightBakedModel;
import com.renyigesai.unusualfoodsdelight.init.UdBlocks;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class UdClientEvents {

    @SubscribeEvent
    public static void onModel(ModelEvent.ModifyBakingResult event){
        ImmutableList<BlockState> possibleStates = UdBlocks.DRAGON_PRESERVED_EGG.get().getStateDefinition().getPossibleStates();
        for (BlockState state : possibleStates) {
            ModelResourceLocation modelResourceLocation = BlockModelShaper.stateToModelLocation(state);
            BakedModel bakedModel = event.getModels().get(modelResourceLocation);
            FullbrightBakedModel fullbrightBakedModel = new FullbrightBakedModel(bakedModel);
            event.getModels().put(modelResourceLocation,fullbrightBakedModel);
        }
    }
}
