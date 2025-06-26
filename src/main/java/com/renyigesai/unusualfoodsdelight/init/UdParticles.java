package com.renyigesai.unusualfoodsdelight.init;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class UdParticles {
    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
//        event.registerSpriteSet(UdParticleTypes.KWAT.get(), SmokedMeatParticle.CosyProvider);
    }

}
