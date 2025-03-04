package com.nebby1999.firmacivplus;

import com.alekiponi.firmaciv.common.item.FirmacivTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class FirmaCivPlusItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FirmaCivPlus.MOD_ID);

    public static void init(IEventBus bus) {
        ITEMS.register(bus);

        bus.addListener(FirmaCivPlusItems::BuildCreativeModeTabContentsEvent);
    }

    private static void BuildCreativeModeTabContentsEvent(BuildCreativeModeTabContentsEvent evt)
    {
        if(evt.getTabKey() == FirmacivTabs.FIRMACIV_TAB.getKey())
        {
            for(var item : ITEMS.getEntries())
            {
                evt.accept(item);
            }
        }
    }
}
