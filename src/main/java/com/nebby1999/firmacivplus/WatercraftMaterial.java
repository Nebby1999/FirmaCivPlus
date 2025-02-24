package com.nebby1999.firmacivplus;

import com.alekiponi.alekiships.util.BoatMaterial;
import com.alekiponi.firmaciv.util.CanoeMaterial;
import net.dries007.tfc.util.registry.RegistryWood;

public interface WatercraftMaterial extends BoatMaterial, CanoeMaterial
{
    public RegistryWood getWood();
}
