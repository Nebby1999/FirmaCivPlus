package com.nebby1999.firmacivplus;

import com.alekiponi.alekiships.util.BoatMaterial;
import com.alekiponi.firmaciv.common.block.CanoeComponentBlock;
import com.alekiponi.firmaciv.common.block.FirmacivBlocks;
import com.alekiponi.firmaciv.util.CanoeMaterial;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.util.registry.RegistryWood;
import net.minecraft.world.level.block.RotatedPillarBlock;

public interface WatercraftMaterial extends BoatMaterial, CanoeMaterial
{
    public RegistryWood getWood();

    public boolean isSoftwood();
    public static void registerFrames(WatercraftMaterial[] materials)
    {
        for(var material : materials)
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
