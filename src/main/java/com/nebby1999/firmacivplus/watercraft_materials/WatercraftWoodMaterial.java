/*package com.nebby1999.firmacivplus.watercraft_materials;

import com.alekiponi.alekiships.common.entity.vehicle.AbstractVehicle;
import com.alekiponi.alekiships.util.BoatMaterial;
import com.alekiponi.firmaciv.common.block.CanoeComponentBlock;
import com.alekiponi.firmaciv.common.block.FirmacivBlocks;
import com.alekiponi.firmaciv.common.entity.vehicle.CanoeEntity;
import com.alekiponi.firmaciv.util.CanoeMaterial;
import com.eerussianguy.beneath.common.blocks.Stem;
import com.eerussianguy.beneath.common.items.BeneathItems;
import com.nebby1999.firmacivplus.FirmaCivPlusBlocks;
import com.therighthon.afc.common.blocks.AFCWood;
import com.therighthon.afc.common.items.AFCItems;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.util.registry.RegistryWood;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.RegistryObject;

import java.util.Map;
import java.util.Optional;

/// An enum similar to the TFCWood enum found inside FirmaCiv that allows for registration of new boats. This however, lets you use any kind of TFC wood. Since most of them inherit from the RegistryWood interface.
public enum WatercraftWoodMaterial implements BoatMaterial, CanoeMaterial
{
    WARPED(Stem.WARPED, true, BeneathItems.LUMBER),
    CRIMSON(Stem.CRIMSON, true, BeneathItems.LUMBER),
    BAOBAB(AFCWood.BAOBAB, false, AFCItems.LUMBER),
    EUCALYPTUS(AFCWood.EUCALYPTUS, false, AFCItems.LUMBER),
    MAHOGANY(AFCWood.MAHOGANY, false, AFCItems.LUMBER),
    HEVEA(AFCWood.HEVEA, false, AFCItems.LUMBER),
    TUALANG(AFCWood.TUALANG, false, AFCItems.LUMBER),
    TEAK(AFCWood.TEAK, false, AFCItems.LUMBER),
    CYPRESS(AFCWood.CYPRESS, false, AFCItems.LUMBER),
    FIG(AFCWood.FIG, false, AFCItems.LUMBER),
    IRONWOOD(AFCWood.IRONWOOD, false, AFCItems.LUMBER),
    IPE(AFCWood.IPE, false, AFCItems.LUMBER)
    ;

    public final RegistryWood wood;
    public final boolean doesWithstandsLava;
    public final Map<? extends RegistryWood, RegistryObject<Item>> lumberRegistry;

    WatercraftWoodMaterial(RegistryWood wood, boolean withstandsLava, Map<? extends RegistryWood, RegistryObject<Item>> lumberRegistry)
    {
        this.wood = wood;
        doesWithstandsLava = withstandsLava;
        this.lumberRegistry = lumberRegistry;
    }

    public static void registerFrames()
    {
        for(WatercraftWoodMaterial entry : values())
        {
            FirmacivBlocks.BOAT_FRAME_ANGLED.get()
                    .registerFrame(entry.wood.getBlock(Wood.BlockType.PLANKS).get().asItem(),
                            FirmaCivPlusBlocks.WOODEN_BOAT_FRAME_ANGLED.get(entry).get());
            FirmacivBlocks.BOAT_FRAME_FLAT.get()
                    .registerFrame(entry.wood.getBlock(Wood.BlockType.PLANKS).get().asItem(),
                            FirmaCivPlusBlocks.WOODEN_BOAT_FRAME_FLAT.get(entry).get());
            CanoeComponentBlock.registerCanoeComponent(
                    (RotatedPillarBlock) entry.wood.getBlock(Wood.BlockType.STRIPPED_LOG).get(),
                    FirmaCivPlusBlocks.CANOE_COMPONENT_BLOCKS.get(entry).get());
        }
    }
    @Override
    public Item getRailing()
    {
        return lumberRegistry.get(wood).get();
    }

    @Override
    public Item getStrippedLog()
    {
        return wood.getBlock(Wood.BlockType.STRIPPED_LOG).get().asItem();
    }

    @Override
    public boolean withstandsLava()
    {
        return doesWithstandsLava;
    }

    @Override
    public BlockState getDeckBlock()
    {
        return wood.getBlock(Wood.BlockType.PLANKS).get().defaultBlockState();
    }

    @Override
    public Optional<EntityType<? extends AbstractVehicle>> getEntityType(BoatType boatType)
    {
        return switch (boatType) {
            case ROWBOAT -> Optional.of(FirmaCivCrossCompatEntities.CROSS_COMPAT_ROWBOATS.get(this).get());
            case SLOOP -> Optional.of(FirmaCivCrossCompatEntities.CROSS_COMPAT_SLOOPS.get(this).get());
            case CONSTRUCTION_SLOOP -> Optional.of(FirmaCivCrossCompatEntities.CROSS_COMPAT_SLOOPS_UNDER_CONSTRUCTION.get(this).get());
        };
    }

    @Override
    public Optional<EntityType<? extends CanoeEntity>> getCanoeType()
    {
        return Optional.of(FirmaCivCrossCompatEntities.CROSS_COMPAT_CANOES.get(this).get());
    }

    @Override
    public String getSerializedName()
    {
        return wood.getSerializedName();
    }
}*/
