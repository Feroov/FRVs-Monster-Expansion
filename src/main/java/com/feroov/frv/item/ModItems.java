package com.feroov.frv.item;

import com.feroov.frv.Frv;
import com.feroov.frv.init.ModEntityTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems
{

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Frv.MOD_ID);
    /********************************** (Misc/Unobtainable) *******************************************/
    public static final RegistryObject<Item> ADMIN_SWORD = ITEMS.register("admin_sword",
            () -> new SwordItem(ModTiers.ADMIN, 0, 9996f,
                    new Item.Properties().tab(ModItemGroup.FRV_TAB_MISC)));

    /* ***** (Tab pics) *****/
    //public static final RegistryObject<Item> MOB_PIC = ITEMS.register("mob_pic",
      //      () -> new Item(new Item.Properties()));
    /* ********************* */
    /***********************************************************************************************/



    /**********************************  (MOBS EGGS) **********************************************/
    public static final RegistryObject<ModSpawnEggItem> MUTATED_COW_SPAWN_EGG = ITEMS.register("mutated_cow_spawn_egg", () -> new ModSpawnEggItem(ModEntityTypes.MUTATED_COW, 0X087D62, 0X087A62, new Item.Properties().tab(ModItemGroup.FRV_TAB_EGGS)));
    /***********************************************************************************************/



/**
    @Override
    public boolean hurtEnemy(ItemStack p_41395_, LivingEntity p_41396_, LivingEntity p_41397_)
    {
        if(!p_41397_.level.isClientSide())
        {
            p_41396_.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 200, 1));
        }
        return super.hurtEnemy(p_41395_, p_41396_, p_41397_);
    }**/


    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
