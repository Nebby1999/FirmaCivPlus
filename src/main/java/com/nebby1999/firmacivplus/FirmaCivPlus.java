package com.nebby1999.firmacivplus;

import com.mojang.logging.LogUtils;
import com.nebby1999.firmacivplus.afc.FirmaCivPlusAFC;
import com.nebby1999.firmacivplus.util.FirmaCivPlusModsResolver;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FirmaCivPlus.MOD_ID)
public class FirmaCivPlus
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "firmacivplus";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public FirmaCivPlus()
    {

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        if(FirmaCivPlusModsResolver.ARBOR_FIRMA_CRAFT.isLoaded())
        {
            LOGGER.info("Enabling FirmaCiv+ArborFirmaCraft cross compat");
            FirmaCivPlusAFC.init();
        }
        /*if(FirmaCivPlusModsResolver.BENEATH.isLoaded())
        {
            LOGGER.info("Enabling FirmaCiv+Beneath cross compat");
            //FirmaCivPlusBeneath.init();
        }*/

        FirmaCivPlusBlocks.init(bus);

        // Register the commonSetup method for modloading
        bus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        if(FirmaCivPlusModsResolver.ARBOR_FIRMA_CRAFT.isLoaded())
        {
            FirmaCivPlusAFC.commonSetup();
        }
        if(FirmaCivPlusModsResolver.BENEATH.isLoaded())
        {
            //FirmaCivPlusBeneath.commonSetup();
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
    }
}
