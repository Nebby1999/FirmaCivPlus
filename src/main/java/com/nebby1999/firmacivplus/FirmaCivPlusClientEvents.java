package com.nebby1999.firmacivplus;

import com.alekiponi.alekiships.AlekiShips;
import com.alekiponi.alekiships.client.resources.PaintedTextureGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.eventbus.api.IEventBus;

import java.util.ArrayList;
import java.util.List;

public class FirmaCivPlusClientEvents
{
    private static List<WatercraftMaterial> _watercraftMaterials = new ArrayList<>();
    public static void addWatercraftMaterials(WatercraftMaterial[] watercraftMaterials)
    {
        _watercraftMaterials.addAll(List.of(watercraftMaterials));
    }

    static void init(IEventBus evtBus)
    {
        evtBus.addListener(FirmaCivPlusClientEvents::onRegisterReloadListeners);
    }

    private static void onRegisterReloadListeners(final RegisterClientReloadListenersEvent evt)
    {
        final ResourceLocation rowboatPaint = new ResourceLocation(AlekiShips.MOD_ID, "entity/watercraft/rowboat/paint");
        final ResourceLocation sloopPaint = new ResourceLocation(AlekiShips.MOD_ID, "entity/watercraft/sloop/paint");

        for(final var woodEntry : _watercraftMaterials)
        {
            evt.registerReloadListener(new PaintedTextureGenerator(
                    new ResourceLocation(FirmaCivPlus.MOD_ID, "entity/watercraft/rowboat/" + woodEntry.getSerializedName()), rowboatPaint
            ));

            evt.registerReloadListener(new PaintedTextureGenerator(
                    new ResourceLocation(FirmaCivPlus.MOD_ID, "entity/watercraft/sloop" + woodEntry.getSerializedName()), sloopPaint
            ));
        }
    }
}
