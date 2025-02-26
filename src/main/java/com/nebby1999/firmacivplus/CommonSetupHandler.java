package com.nebby1999.firmacivplus;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.ArrayList;
import java.util.List;

public final class CommonSetupHandler
{
    private static List<Action> setups = new ArrayList<Action>();
    public static void addCommonSetup(Action setup)
    {
        setups.add(setup);
    }

    static void onCommonSetup(FMLCommonSetupEvent evt)
    {
        evt.enqueueWork(() ->
        {
           FirmaCivPlusBlockEntities.registerCanoesToFirmaCivRegistry();
           for(var otherSetup : setups)
           {
                otherSetup.execute();
           }
        });
    }
}
