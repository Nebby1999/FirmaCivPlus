package com.nebby1999.firmacivplus.util;

import com.alekiponi.firmaciv.common.block.CanoeComponentBlock;
import com.alekiponi.firmaciv.common.block.FirmacivAngledWoodenBoatFrameBlock;
import com.alekiponi.firmaciv.common.block.FirmacivBlocks;
import com.alekiponi.firmaciv.common.block.FirmacivFlatWoodenBoatFrameBlock;
import com.nebby1999.firmacivplus.WatercraftMaterial;
import com.nebby1999.firmacivplus.afc.AFCWatercraftMaterial;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.util.registry.RegistryWood;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class CommonHelper
{
    public static Supplier<CanoeComponentBlock> createCanoeComponentBlock(WatercraftMaterial material)
    {
        var wood = material.getWood();

        return () ->new CanoeComponentBlock(
                BlockBehaviour.Properties.copy(wood.getBlock(Wood.BlockType.STRIPPED_LOG).get()).mapColor(wood.woodColor()).noOcclusion(),
                material);
    }

    public static Supplier<FirmacivAngledWoodenBoatFrameBlock> createAngledWoodenBoatFrameBlock(WatercraftMaterial material)
    {
        return () -> new FirmacivAngledWoodenBoatFrameBlock(material, BlockBehaviour.Properties.copy(FirmacivBlocks.BOAT_FRAME_ANGLED.get()));
    }

    public static Supplier<FirmacivFlatWoodenBoatFrameBlock> createFlatWoodenBoatFrameBlock(WatercraftMaterial material)
    {
        return () -> new FirmacivFlatWoodenBoatFrameBlock(material, BlockBehaviour.Properties.copy(FirmacivBlocks.BOAT_FRAME_FLAT.get()));
    }
}
