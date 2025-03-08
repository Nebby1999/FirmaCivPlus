package com.nebby1999.firmacivplus;

import com.alekiponi.alekiships.common.entity.vehicle.AbstractVehicle;
import com.alekiponi.alekiships.util.BoatMaterial;
import com.alekiponi.firmaciv.common.entity.vehicle.CanoeEntity;
import com.eerussianguy.beneath.common.blocks.Stem;
import com.eerussianguy.beneath.common.items.BeneathItems;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.util.registry.RegistryWood;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public enum BeneathWatercraftMaterial implements WatercraftMaterial
{
    //softwoods, makes canoes
    CRIMSON(Stem.CRIMSON, true),
    WARPED(Stem.WARPED, false)
    ;

    public final boolean isSoftwood;
    public final Stem wood;

    BeneathWatercraftMaterial(Stem wood, boolean isSoftwood) { this.wood = wood; this.isSoftwood = isSoftwood; }
    @Override
    public Item getRailing()
    {
        return BeneathItems.LUMBER.get(wood).get();
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
    public Optional<EntityType<? extends AbstractVehicle>> getEntityType(BoatMaterial.BoatType boatType)
    {
        return switch (boatType) {
            case ROWBOAT -> Optional.of(FirmaCivPlusEntities.getRowboats().get(this).get());
            case SLOOP -> Optional.of(FirmaCivPlusEntities.getSloops().get(this).get());
            case CONSTRUCTION_SLOOP -> Optional.of(FirmaCivPlusEntities.getSloopsUnderConstruction().get(this).get());
        };
    }

    @Override
    public Optional<EntityType<? extends CanoeEntity>> getCanoeType()
    {
        return Optional.of(FirmaCivPlusEntities.getCanoes().get(this).get());
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

    @Override
    public boolean isSoftwood()
    {
        return isSoftwood;
    }
}
