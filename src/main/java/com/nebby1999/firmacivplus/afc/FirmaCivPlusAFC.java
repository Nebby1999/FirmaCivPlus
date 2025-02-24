package com.nebby1999.firmacivplus.afc;

import com.nebby1999.firmacivplus.FirmaCivPlusBlocks;
import com.nebby1999.firmacivplus.util.CommonHelper;

public final class FirmaCivPlusAFC
{
    public static void init()
    {
        FirmaCivPlusBlocks.addConsumerForRegistration(FirmaCivPlusAFC::addBlocks);
    }

    private static void addBlocks(FirmaCivPlusBlocks.WriteOnlyRegistration registration)
    {
        for(var woodEntry : AFCWatercraftMaterial.values())
        {
            String woodName = woodEntry.getSerializedName();

            registration.putCanoe(woodEntry, "wood/canoe_component_block/" + woodName, CommonHelper.createCanoeComponentBlock(woodEntry));
            registration.putAngledBoatFrame(woodEntry, "wood/watercraft_frame/angled/" + woodName, CommonHelper.createAngledWoodenBoatFrameBlock(woodEntry));
            registration.putFlatBoatFrame(woodEntry, "wood/watercraft_frame/flat/" + woodName, CommonHelper.createFlatWoodenBoatFrameBlock(woodEntry));
        }
    }
}
