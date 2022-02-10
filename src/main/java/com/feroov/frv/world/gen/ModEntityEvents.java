package com.feroov.frv.world.gen;

import com.feroov.frv.Frv;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


@Mod.EventBusSubscriber(modid = Frv.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEntityEvents
{
    public static final DeferredRegister<EntityType<?>> ENTITIES
            = DeferredRegister.create(ForgeRegistries.ENTITIES, Frv.MOD_ID);

    /***************** Spawning Entities etc *********************************/

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onBiomeLoad(final BiomeLoadingEvent event) {
        Biome biome = ForgeRegistries.BIOMES.getValue(event.getName());
        if (biome == null)
            return;
        /*if (biome.getBiomeCategory() == Biome.BiomeCategory.SWAMP)
            event.getSpawns().getSpawner(MobCategory.CREATURE)
                    .add(new MobSpawnSettings.SpawnerData(ModEntityTypes.CROAKER.get(), 200, 1, 3));

         */

    }
    /***************************************************************************/

    public static void register(IEventBus eventBus)
    {
        ENTITIES.register(eventBus);
    }
}
