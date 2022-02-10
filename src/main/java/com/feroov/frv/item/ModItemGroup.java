package com.feroov.frv.item;


import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ModItemGroup
{

    public static final CreativeModeTab FRV_TAB_MISC = new CreativeModeTab("frvMisc")
    {
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(Items.NETHERITE_SWORD);
        }
    };


    public static final CreativeModeTab FRV_TAB_EGGS = new CreativeModeTab("frvEgg")
    {
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(Items.EGG);
        }
    };

}
