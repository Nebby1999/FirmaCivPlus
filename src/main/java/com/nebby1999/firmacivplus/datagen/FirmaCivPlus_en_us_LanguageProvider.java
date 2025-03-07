package com.nebby1999.firmacivplus.datagen;

import com.nebby1999.firmacivplus.FirmaCivPlus;
import com.nebby1999.firmacivplus.FirmaCivPlusBlocks;
import com.nebby1999.firmacivplus.FirmaCivPlusEntities;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.Arrays;
import java.util.stream.Collectors;

public class FirmaCivPlus_en_us_LanguageProvider extends LanguageProvider
{
    public FirmaCivPlus_en_us_LanguageProvider(PackOutput output) {
        super(output, FirmaCivPlus.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addBlockTranslations();
        addEntityTranslations();
    }

    private void addBlockTranslations()
    {
        FirmaCivPlusBlocks.getWoodRoofings().forEach((material, registryObject) ->
        {
            addBlock(registryObject, langify(material.getSerializedName()) + " Roofing");
        });

        FirmaCivPlusBlocks.getCanoeComponentBlocks().forEach(((watercraftMaterial, canoeComponentBlockRegistryObject) ->
        {
            addBlock(canoeComponentBlockRegistryObject, langify(watercraftMaterial.getSerializedName()) + " Canoe Component");
        }));

        FirmaCivPlusBlocks.getWoodenBoatFrameFlatBlocks().forEach((watercraftMaterial, firmacivFlatWoodenBoatFrameBlockRegistryObject) ->
        {
            addBlock(firmacivFlatWoodenBoatFrameBlockRegistryObject, langify(watercraftMaterial.getSerializedName()) + " Flat Shipwright's Scaffolding");
        });

        FirmaCivPlusBlocks.getWoodenBoatFrameAngledBlocks().forEach((watercraftMaterial, firmacivAngledWoodenBoatFrameBlockRegistryObject) ->
        {
            addBlock(firmacivAngledWoodenBoatFrameBlockRegistryObject, langify(watercraftMaterial.getSerializedName()) + " Sloped Shipwright's Scaffolding");
        });
    }

    private void addEntityTranslations()
    {
        FirmaCivPlusEntities.getCanoes().forEach((watercraftMaterial, entityTypeRegistryObject) ->
        {
            addEntityType(entityTypeRegistryObject, langify(watercraftMaterial.getSerializedName()) + " Dugout Canoe");
        });

        FirmaCivPlusEntities.getSloops().forEach((watercraftMaterial, entityTypeRegistryObject) ->
        {
            addEntityType(entityTypeRegistryObject, langify(watercraftMaterial.getSerializedName()) + " Sloop");
        });

        FirmaCivPlusEntities.getSloopsUnderConstruction().forEach((watercraftMaterial, entityTypeRegistryObject) ->
        {
            addEntityType(entityTypeRegistryObject, langify(watercraftMaterial.getSerializedName()) + " Sloop");
        });

        FirmaCivPlusEntities.getRowboats().forEach((watercraftMaterial, entityTypeRegistryObject) ->
        {
            addEntityType(entityTypeRegistryObject, langify(watercraftMaterial.getSerializedName()) + " Rowboat");
        });
    }

    public String langify(final String serializedName) {
        return Arrays.stream(serializedName.split("_"))
                .map(s -> Character.toUpperCase(s.charAt(0)) + s.substring(1))
                .collect(Collectors.joining(" "));
    }
}
