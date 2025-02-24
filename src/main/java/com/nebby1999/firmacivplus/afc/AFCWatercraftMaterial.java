package com.nebby1999.firmacivplus.afc;

import com.alekiponi.alekiships.common.entity.vehicle.AbstractVehicle;
import com.alekiponi.firmaciv.common.entity.vehicle.CanoeEntity;
import com.nebby1999.firmacivplus.WatercraftMaterial;
import com.therighthon.afc.common.blocks.AFCWood;
import com.therighthon.afc.common.items.AFCItems;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.util.registry.RegistryWood;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public enum AFCWatercraftMaterial implements WatercraftMaterial
{
    BAOBAB(AFCWood.BAOBAB),
    EUCALYPTUS(AFCWood.EUCALYPTUS),
    MAHOGANY(AFCWood.MAHOGANY),
    HEVEA(AFCWood.HEVEA),
    TUALANG(AFCWood.TUALANG),
    TEAK(AFCWood.TEAK),
    CYPRESS(AFCWood.CYPRESS),
    FIG(AFCWood.FIG),
    IRONWOOD(AFCWood.IRONWOOD),
    IPE(AFCWood.IPE)
    ;

    public final AFCWood wood;

    AFCWatercraftMaterial(AFCWood wood) { this.wood = wood; }
    @Override
    public Item getRailing()
    {
        return AFCItems.LUMBER.get(wood).get();
    }

    @Override
    public Item getStrippedLog()
    {
        return wood.getBlock(Wood.BlockType.STRIPPED_LOG).get().asItem();
    }

    @Override
    public boolean withstandsLava()
    {
        return false;
    }

    @Override
    public BlockState getDeckBlock()
    {
        return wood.getBlock(Wood.BlockType.PLANKS).get().defaultBlockState();
    }

    @Override
    public Optional<EntityType<? extends AbstractVehicle>> getEntityType(BoatType boatType)
    {
        return Optional.empty();
        /*return switch (boatType) {
            case ROWBOAT -> Optional.of(FirmaCivPlusEntities.CROSS_COMPAT_ROWBOATS.get(this).get());
            case SLOOP -> Optional.of(FirmaCivPlusEntities.CROSS_COMPAT_SLOOPS.get(this).get());
            case CONSTRUCTION_SLOOP -> Optional.of(FirmaCivPlusEntities.CROSS_COMPAT_SLOOPS_UNDER_CONSTRUCTION.get(this).get());
        };*/
    }

    @Override
    public Optional<EntityType<? extends CanoeEntity>> getCanoeType()
    {
        return Optional.empty();
        //return Optional.of(FirmaCivPlusEntities.CROSS_COMPAT_CANOES.get(this).get());
    }

    @Override
    public String getSerializedName()
    {
        return wood.getSerializedName();
    }

    @Override
    public RegistryWood getWood()
    {
        return wood;
    }
}
