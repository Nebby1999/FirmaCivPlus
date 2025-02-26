package com.nebby1999.firmacivplus;

import com.mojang.logging.LogUtils;
import com.nebby1999.firmacivplus.afc.FirmaCivPlusAFC;
import com.nebby1999.firmacivplus.util.FirmaCivPlusModsResolver;
import net.minecraft.world.level.block.entity.BlockEntityType;
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
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
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
        FirmaCivPlusBlockEntities.init(bus);
        FirmaCivPlusEntities.init(bus);

        if(FMLEnvironment.dist == Dist.CLIENT)
        {
            FirmaCivPlusClientEvents.init(bus);
            RenderEventHandler.init(bus);
        }

        bus.addListener(CommonSetupHandler::onCommonSetup);
    }
}
