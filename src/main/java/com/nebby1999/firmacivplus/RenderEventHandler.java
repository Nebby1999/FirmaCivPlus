package com.nebby1999.firmacivplus;

import com.alekiponi.alekiships.client.render.entity.vehicle.RowboatRenderer;
import com.alekiponi.alekiships.client.render.entity.vehicle.SloopConstructionRenderer;
import com.alekiponi.alekiships.client.render.entity.vehicle.SloopRenderer;
import com.alekiponi.alekiships.util.CommonHelper;
import com.alekiponi.firmaciv.client.render.entity.vehicle.CanoeRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;

import java.util.ArrayList;
import java.util.List;

public final class RenderEventHandler
{
    static void init(IEventBus evtBus)
    {
        evtBus.addListener(RenderEventHandler::registerRenderers);
    }

    private static void registerRenderers(final EntityRenderersEvent.RegisterRenderers evt)
    {
        for(final WatercraftMaterial woodEntry : WatercraftMaterial._ALL_WATERCRAFT_MATERIALS)
        {
            if(woodEntry.isSoftwood())
            {
                // Canoes
                evt.registerEntityRenderer(FirmaCivPlusEntities.getCanoes().get(woodEntry).get(),
                        context -> new CanoeRenderer(context, new ResourceLocation(FirmaCivPlus.MOD_ID,
                                "textures/entity/watercraft/dugout_canoe/" + woodEntry.getSerializedName() + ".png")));
            }
            else
            {
                // Rowboat
                evt.registerEntityRenderer(FirmaCivPlusEntities.getRowboats().get(woodEntry).get(),
                        context -> new RowboatRenderer(context, new ResourceLocation(FirmaCivPlus.MOD_ID,
                                "textures/entity/watercraft/rowboat/" + woodEntry.getSerializedName()),
                                CommonHelper.mapOfKeys(DyeColor.class, dyeColor -> new ResourceLocation(FirmaCivPlus.MOD_ID,
                                        "textures/entity/watercraft/rowboat/" + woodEntry.getSerializedName() + "/" + dyeColor.getSerializedName()))));
                // Sloops
                evt.registerEntityRenderer(FirmaCivPlusEntities.getSloops().get(woodEntry).get(),
                        context -> new SloopRenderer(context, new ResourceLocation(FirmaCivPlus.MOD_ID,
                                "textures/entity/watercraft/sloop/" + woodEntry.getSerializedName()),
                                CommonHelper.mapOfKeys(DyeColor.class, dyeColor -> new ResourceLocation(FirmaCivPlus.MOD_ID,
                                        "textures/entity/watercraft/sloop/" + woodEntry.getSerializedName() + "/" + dyeColor.getSerializedName()))));
                // Construction sloops
                evt.registerEntityRenderer(FirmaCivPlusEntities.getSloopsUnderConstruction().get(woodEntry).get(),
                        context -> new SloopConstructionRenderer(context, new ResourceLocation(FirmaCivPlus.MOD_ID,
                                "textures/entity/watercraft/sloop_construction/" + woodEntry.getSerializedName() + ".png")));
            }
        }
    }
}
