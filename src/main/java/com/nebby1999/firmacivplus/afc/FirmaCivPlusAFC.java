package com.nebby1999.firmacivplus.afc;

import com.nebby1999.firmacivplus.*;

public final class FirmaCivPlusAFC
{
    public static void init()
    {
        FirmaCivPlusBlocks.addConsumerForRegistration(FirmaCivPlusAFC::addBlocks);
        FirmaCivPlusEntities.addConsumerForRegistration(FirmaCivPlusAFC::addEntities);

        //We add these to the lists, but if we're not a client the classes' init wont even call.
        FirmaCivPlusClientEvents.addWatercraftMaterials(AFCWatercraftMaterial.values());
        RenderEventHandler.addWatercraftMaterials(AFCWatercraftMaterial.values());

        CommonSetupHandler.addCommonSetup(() -> WatercraftMaterial.registerFrames(AFCWatercraftMaterial.values()));
    }

    private static void addBlocks(FirmaCivPlusBlocks.WriteOnlyRegistration registration)
    {
        registration.registerFromWatercraftMaterials(AFCWatercraftMaterial.values());
    }

    private static void addEntities(FirmaCivPlusEntities.WriteOnlyRegistration registration)
    {
        registration.registerFromWatercraftMaterials(AFCWatercraftMaterial.values());
    }
}
