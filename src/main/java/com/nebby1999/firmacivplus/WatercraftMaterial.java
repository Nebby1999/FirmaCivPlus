package com.nebby1999.firmacivplus;

import com.alekiponi.alekiships.util.BoatMaterial;
import com.alekiponi.firmaciv.common.block.CanoeComponentBlock;
import com.alekiponi.firmaciv.common.block.FirmacivBlocks;
import com.alekiponi.firmaciv.util.CanoeMaterial;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.util.registry.RegistryWood;
import net.minecraft.world.level.block.RotatedPillarBlock;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface WatercraftMaterial extends BoatMaterial, CanoeMaterial
{
    RegistryWood getWood();

    boolean isSoftwood();

    HashSet<WatercraftMaterial> _ALL_WATERCRAFT_MATERIALS = new HashSet<>();

    static void addMaterials(WatercraftMaterial[] materials)
    {
        _ALL_WATERCRAFT_MATERIALS.addAll(List.of(materials));
    }

    static void registerFrames()
    {
        for(var material : _ALL_WATERCRAFT_MATERIALS)
        {
            if(material.isSoftwood())
            {
                CanoeComponentBlock.registerCanoeComponent
                        ((RotatedPillarBlock) material.getWood().getBlock(Wood.BlockType.STRIPPED_LOG).get(),
                                FirmaCivPlusBlocks.getCanoeComponentBlocks().get(material).get());
            }
            else
            {
                FirmacivBlocks.BOAT_FRAME_ANGLED.get()
                        .registerFrame(material.getWood().getBlock(Wood.BlockType.PLANKS).get().asItem(),
                                FirmaCivPlusBlocks.getWoodenBoatFrameAngledBlocks().get(material).get());
                FirmacivBlocks.BOAT_FRAME_FLAT.get()
                        .registerFrame(material.getWood().getBlock(Wood.BlockType.PLANKS).get().asItem(),
                                FirmaCivPlusBlocks.getWoodenBoatFrameFlatBlocks().get(material).get());
            }
        }
    }
}
