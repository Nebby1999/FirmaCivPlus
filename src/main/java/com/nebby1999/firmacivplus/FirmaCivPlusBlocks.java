package com.nebby1999.firmacivplus;

import com.alekiponi.alekiroofs.SquaredAngleBlock;
import com.alekiponi.firmaciv.common.block.CanoeComponentBlock;
import com.alekiponi.firmaciv.common.block.FirmacivAngledWoodenBoatFrameBlock;
import com.alekiponi.firmaciv.common.block.FirmacivBlocks;
import com.alekiponi.firmaciv.common.block.FirmacivFlatWoodenBoatFrameBlock;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.util.registry.RegistryWood;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.*;
import java.util.function.Supplier;

public class FirmaCivPlusBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, FirmaCivPlus.MOD_ID);

    private static final Map<WatercraftMaterial, RegistryObject<CanoeComponentBlock>> _CANOE_COMPONENT_BLOCKS = new HashMap<>();
    public static Map<WatercraftMaterial, RegistryObject<CanoeComponentBlock>> getCanoeComponentBlocks()
    {
        return Collections.unmodifiableMap(_CANOE_COMPONENT_BLOCKS);
    }

    private static final Map<WatercraftMaterial, RegistryObject<FirmacivAngledWoodenBoatFrameBlock>> _WOODEN_BOAT_FRAME_ANGLED = new HashMap<>();
    public static Map<WatercraftMaterial, RegistryObject<FirmacivAngledWoodenBoatFrameBlock>> getWoodenBoatFrameAngledBlocks()
    {
        return Collections.unmodifiableMap(_WOODEN_BOAT_FRAME_ANGLED);
    }

    private static final Map<WatercraftMaterial, RegistryObject<FirmacivFlatWoodenBoatFrameBlock>> _WOODEN_BOAT_FRAME_FLAT = new HashMap<>();
    public static Map<WatercraftMaterial, RegistryObject<FirmacivFlatWoodenBoatFrameBlock>> getWoodenBoatFrameFlatBlocks()
    {
        return Collections.unmodifiableMap(_WOODEN_BOAT_FRAME_FLAT);
    }

    private static final Map<WatercraftMaterial, RegistryObject<SquaredAngleBlock>> _WOOD_ROOFING = new HashMap<>();
    public static Map<WatercraftMaterial, RegistryObject<SquaredAngleBlock>> getWoodRoofings()
    {
        return Collections.unmodifiableMap(_WOOD_ROOFING);
    }


    static void init(IEventBus eventBus)
    {
        for(var woodEntry : WatercraftMaterial._ALL_WATERCRAFT_MATERIALS)
        {
            putWoodRoofing(woodEntry);
            if(woodEntry.isSoftwood())
            {
                putCanoeComponentBlock(woodEntry);
            }
            else
            {
                putAngledBoatFrameBlock(woodEntry);
                putFlatBoatFrameBlock(woodEntry);
            }
        }

        if(!BLOCKS.getEntries().isEmpty())
            BLOCKS.register(eventBus);
    }

    private static void putWoodRoofing(WatercraftMaterial watercraftMaterial)
    {
        String name = "wood/" + watercraftMaterial.getSerializedName() + "_roofing";
        Supplier<SquaredAngleBlock> supplier = () ->
        {
            var stairs = watercraftMaterial.getWood().getBlock(Wood.BlockType.STAIRS).get();
            var blockState = stairs.defaultBlockState();
            var blockProperties = BlockBehaviour.Properties.copy(stairs)
                    .mapColor(watercraftMaterial.getWood().woodColor()).noOcclusion();
            return new SquaredAngleBlock(blockState, blockProperties);
        };

        var entry = BLOCKS.register(name, supplier);
        _WOOD_ROOFING.put(watercraftMaterial, entry);
        FirmaCivPlusItems.ITEMS.register(name, () -> new BlockItem(entry.get(), new Item.Properties()));
    }

    private static void putCanoeComponentBlock(WatercraftMaterial watercraftMaterial)
    {
        String name = "wood/canoe_component_block/" + watercraftMaterial.getSerializedName();
        Supplier<CanoeComponentBlock> supplier = () ->
        {
            var wood = watercraftMaterial.getWood();
            var strippedLog = wood.getBlock(Wood.BlockType.STRIPPED_LOG).get();

            BlockBehaviour.Properties properties = BlockBehaviour.Properties.copy(strippedLog).mapColor(wood.woodColor())
                    .noOcclusion();
            return new CanoeComponentBlock(properties, watercraftMaterial);
        };

        var entry = BLOCKS.register(name, supplier);
        _CANOE_COMPONENT_BLOCKS.put(watercraftMaterial, entry);
    }

    private static void putAngledBoatFrameBlock(WatercraftMaterial watercraftMaterial)
    {
        String name = "wood/watercraft_frame/angled/" + watercraftMaterial.getSerializedName();
        Supplier<FirmacivAngledWoodenBoatFrameBlock> supplier = () ->
                new FirmacivAngledWoodenBoatFrameBlock(watercraftMaterial, BlockBehaviour.Properties.copy(FirmacivBlocks.BOAT_FRAME_ANGLED.get()));
        var entry = BLOCKS.register(name, supplier);
        _WOODEN_BOAT_FRAME_ANGLED.put(watercraftMaterial, entry);
    }

    private static void putFlatBoatFrameBlock(WatercraftMaterial watercraftMaterial)
    {
        String name = "wood/watercraft_frame/flat/" + watercraftMaterial.getSerializedName();
        Supplier<FirmacivFlatWoodenBoatFrameBlock> supplier = () ->
                new FirmacivFlatWoodenBoatFrameBlock(watercraftMaterial, BlockBehaviour.Properties.copy(FirmacivBlocks.BOAT_FRAME_FLAT.get()));
        var entry = BLOCKS.register(name, supplier);
        _WOODEN_BOAT_FRAME_FLAT.put(watercraftMaterial, entry);
    }
}
