package com.nebby1999.firmacivplus;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.ArrayList;
import java.util.List;

public final class CommonSetupHandler
{
    static void onCommonSetup(FMLCommonSetupEvent evt)
    {
        evt.enqueueWork(() ->
        {
           FirmaCivPlusBlockEntities.registerCanoesToFirmaCivRegistry();
            WatercraftMaterial.registerFrames();
        });
    }
}
