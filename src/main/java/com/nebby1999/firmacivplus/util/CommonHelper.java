package com.nebby1999.firmacivplus.util;

import com.alekiponi.firmaciv.common.block.CanoeComponentBlock;
import com.alekiponi.firmaciv.common.block.FirmacivAngledWoodenBoatFrameBlock;
import com.alekiponi.firmaciv.common.block.FirmacivBlocks;
import com.alekiponi.firmaciv.common.block.FirmacivFlatWoodenBoatFrameBlock;
import com.alekiponi.firmaciv.util.CanoeMaterial;
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

        return () ->
        {
            var strippedLogSupplier = wood.getBlock(Wood.BlockType.STRIPPED_LOG);
            var strippedLog = strippedLogSupplier.get();

            BlockBehaviour.Properties properties = BlockBehaviour.Properties.copy(strippedLog).mapColor(wood.woodColor()).noOcclusion();
            CanoeMaterial canoeMaterial = material;
            return new CanoeComponentBlock(properties, canoeMaterial);
        };
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
