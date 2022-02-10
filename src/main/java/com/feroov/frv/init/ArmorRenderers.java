package com.feroov.frv.init;

import com.feroov.frv.Frv;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = Frv.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ArmorRenderers
{
    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.AddLayers event)
    {
        //GeoArmorRenderer.registerArmorRenderer(AmethystArmor.class, new AmethystArmorRenderer());

    }
}
