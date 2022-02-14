package com.feroov.frv.init;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.hostile.MutatedCow;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes
{
    private ModEntityTypes(){}

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES,
            Frv.MOD_ID);


    /** Hostile **/
    public static final RegistryObject<EntityType<MutatedCow>> MUTATED_COW = ENTITIES.register("mutated_cow",
            () -> EntityType.Builder.of(MutatedCow::new, MobCategory.CREATURE).canSpawnFarFromPlayer()
                    .sized(1.4f,2.8f).build("mutated_cow"));

}

