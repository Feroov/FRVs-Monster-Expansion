package com.feroov.frv.events;

import com.feroov.frv.Frv;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Frv.MOD_ID, bus = Bus.MOD)
public class ModEvents
{
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event)
    {
        /** Hostile **/
       // event.put(ModEntityTypes.PIRATE_CAPTAIN.get(), PirateCaptain.createAttributes().build());
    }
}