package com.nebby1999.firmacivplus;

import com.alekiponi.firmaciv.common.block.CanoeComponentBlock;
import com.alekiponi.firmaciv.common.block.FirmacivAngledWoodenBoatFrameBlock;
import com.alekiponi.firmaciv.common.block.FirmacivFlatWoodenBoatFrameBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class FirmaCivPlusBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, FirmaCivPlus.MOD_ID);


    private static final Map<WatercraftMaterial, RegistryObject<CanoeComponentBlock>> _CANOE_COMPONENT_BLOCKS = new HashMap<>();
    public static RegistryObject<CanoeComponentBlock> getCanoeComponentBlock(WatercraftMaterial material)
    {
        return _CANOE_COMPONENT_BLOCKS.get(material);
    }

    private static final Map<WatercraftMaterial, RegistryObject<FirmacivAngledWoodenBoatFrameBlock>> _WOODEN_BOAT_FRAME_ANGLED = new HashMap<>();
    public static RegistryObject<FirmacivAngledWoodenBoatFrameBlock> getAngledWoodenBoatFrameBlock(WatercraftMaterial material)
    {
        return _WOODEN_BOAT_FRAME_ANGLED.get(material);
    }

    private static final Map<WatercraftMaterial, RegistryObject<FirmacivFlatWoodenBoatFrameBlock>> _WOODEN_BOAT_FRAME_FLAT = new HashMap<>();
    public static RegistryObject<FirmacivFlatWoodenBoatFrameBlock> getFlatWoodenBoatFrameBlock(WatercraftMaterial material)
    {
        return _WOODEN_BOAT_FRAME_FLAT.get(material);
    }

    private static final ArrayList<Consumer<WriteOnlyRegistration>> _consumers = new ArrayList<>();
    public static void addConsumerForRegistration(Consumer<WriteOnlyRegistration> consumer)
    {
        _consumers.add(consumer);
    }

    public static void init(IEventBus eventBus)
    {
        var registrationClass = new WriteOnlyRegistration();
        for(var consumer : _consumers)
        {
            consumer.accept(registrationClass);
        }

        if(!BLOCKS.getEntries().isEmpty())
            BLOCKS.register(eventBus);
    }

    public static final class WriteOnlyRegistration
    {
        public void putCanoe(WatercraftMaterial watercraftMaterial, String blockName, Supplier<CanoeComponentBlock> canoeSupplier)
        {
            var entry = BLOCKS.register(blockName, canoeSupplier);
            _CANOE_COMPONENT_BLOCKS.put(watercraftMaterial, entry);
        }

        public void putAngledBoatFrame(WatercraftMaterial watercraftMaterial, String blockName, Supplier<FirmacivAngledWoodenBoatFrameBlock> angledBoatFrameSupplier)
        {
            var entry = BLOCKS.register(blockName, angledBoatFrameSupplier);
            _WOODEN_BOAT_FRAME_ANGLED.put(watercraftMaterial, entry);
        }

        public void putFlatBoatFrame(WatercraftMaterial watercraftMaterial, String blockName, Supplier<FirmacivFlatWoodenBoatFrameBlock> flatBoatFrameSupplier)
        {
            var entry = BLOCKS.register(blockName, flatBoatFrameSupplier);
            _WOODEN_BOAT_FRAME_FLAT.put(watercraftMaterial, entry);
        }
    }
}
