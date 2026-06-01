package com.nebby1999.firmacivplus.watercraftmaterials;

import com.alekiponi.alekiships.common.entity.vehicle.AbstractVehicle;
import com.alekiponi.firmaciv.common.entity.vehicle.CanoeEntity;
import com.nebby1999.firmacivplus.FirmaCivPlusEntities;
import com.nebby1999.firmacivplus.WatercraftMaterial;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.util.registry.RegistryWood;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import su.terrafirmagreg.core.common.data.TFGWood;

import java.util.Optional;

public enum TFGWatercraftMaterial implements WatercraftMaterial {

    ARAUCARIA(TFGWood.ARAUCARIA, true),
    AERONOS(TFGWood.AERONOS, true),
    BEECH(TFGWood.BEECH, false),
    MAHOE(TFGWood.MAHOE, false),
    GLACIAN(TFGWood.GLACIAN, false),
    STROPHAR(TFGWood.STROPHAR, true),
    GINKGO(TFGWood.GINKGO, true)
    ;

    public final boolean isSoftwood;
    public final TFGWood wood;

    TFGWatercraftMaterial(TFGWood wood, boolean isSoftwood) { this.wood = wood; this.isSoftwood = isSoftwood;}

    @Override
    public Item getRailing() {

        String resourceLocation = String.format("tfg:wood/lumber/%s", getSerializedName());
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation(resourceLocation));
    }

    @Override
    public Item getStrippedLog() {
        return wood.getBlock(Wood.BlockType.STRIPPED_LOG).get().asItem();
    }

    @Override
    public boolean withstandsLava() {
        return false;
    }

    @Override
    public BlockState getDeckBlock() {
        return wood.getBlock(Wood.BlockType.PLANKS).get().defaultBlockState();
    }

    @Override
    public Optional<EntityType<? extends AbstractVehicle>> getEntityType(BoatType boatType) {
        return switch(boatType)
        {
            case ROWBOAT -> Optional.of(FirmaCivPlusEntities.getRowboats().get(this).get());
            case SLOOP -> Optional.of(FirmaCivPlusEntities.getSloops().get(this).get());
            case CONSTRUCTION_SLOOP -> Optional.of(FirmaCivPlusEntities.getSloopsUnderConstruction().get(this).get());
        };
    }

    @Override
    public Optional<EntityType<? extends CanoeEntity>> getCanoeType() {
        return Optional.of(FirmaCivPlusEntities.getCanoes().get(this).get());
    }

    @Override
    public String getSerializedName() {
        return wood.getSerializedName();
    }

    @Override
    public RegistryWood getWood() {
        return wood;
    }

    @Override
    public boolean isSoftwood() {
        return isSoftwood;
    }
}
