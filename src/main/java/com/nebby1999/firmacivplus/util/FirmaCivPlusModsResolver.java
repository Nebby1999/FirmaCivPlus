package com.nebby1999.firmacivplus.util;

import net.minecraftforge.fml.ModList;

public enum FirmaCivPlusModsResolver
{
    ARBOR_FIRMA_CRAFT("afc"),
    BENEATH("beneath");

    private final String name;
    private Boolean modLoaded;

    FirmaCivPlusModsResolver(String name) {this.name = name;}

    public boolean isLoaded()
    {
        if(modLoaded == null)
        {
            modLoaded = ModList.get().isLoaded(name);
        }
        return modLoaded;
    }
}
