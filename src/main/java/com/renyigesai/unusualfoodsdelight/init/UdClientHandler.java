package com.renyigesai.unusualfoodsdelight.init;

import com.renyigesai.unusualfoodsdelight.block.smoked_meat_hook.SmokedMeatHookBlockEntityRender;
import com.renyigesai.unusualfoodsdelight.client.particle.SmokedMeatCampfireParticle;
import com.renyigesai.unusualfoodsdelight.client.renderer.ClayStoveRenderer;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class UdClientHandler {
    @SubscribeEvent
    public static void onClientEvent(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {

        });
    }
    @SubscribeEvent
    public static void onRenders(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(UdBlocks.CLAY_STOVE_ENTITY.get(), ClayStoveRenderer::new);
        event.registerBlockEntityRenderer(UdBlocks.SMOKED_MEAT_HOOK_ENTITY.get(), SmokedMeatHookBlockEntityRender::new);
    }
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(UdParticleTypes.SMOKED_MEAT_CAMPFIRE.get(), SmokedMeatCampfireParticle.Factory::new);
    }
}
