package com.nebby1999.firmacivplus;

import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
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
            WatercraftMaterial.addMaterials(AFCWatercraftMaterial.values());
        }
        /*if(FirmaCivPlusModsResolver.BENEATH.isLoaded())
        {
            LOGGER.info("Enabling FirmaCiv+Beneath cross compat");
            //FirmaCivPlusBeneath.init();
        }*/

        FirmaCivPlusBlocks.init(bus);
        FirmaCivPlusItems.init(bus);
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
