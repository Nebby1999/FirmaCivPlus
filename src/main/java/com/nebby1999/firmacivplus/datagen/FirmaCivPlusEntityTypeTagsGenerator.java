package com.nebby1999.firmacivplus.datagen;

import com.alekiponi.alekiships.util.AlekiShipsTags;
import com.nebby1999.firmacivplus.FirmaCivPlus;
import com.nebby1999.firmacivplus.FirmaCivPlusEntities;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class FirmaCivPlusEntityTypeTagsGenerator extends EntityTypeTagsProvider
{

    public FirmaCivPlusEntityTypeTagsGenerator(PackOutput p_256095_, CompletableFuture<HolderLookup.Provider> p_256572_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_256095_, p_256572_, FirmaCivPlus.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        FirmaCivPlusEntities.getRowboats().forEach((watercraftMaterial, entityTypeRegistryObject) ->
        {
            tag(AlekiShipsTags.Entities.ROWBOATS)
                    .add(entityTypeRegistryObject.get());

        });

        FirmaCivPlusEntities.getSloops().forEach((watercraftMaterial, entityTypeRegistryObject) ->
        {
            tag(AlekiShipsTags.Entities.SLOOPS)
                    .add(entityTypeRegistryObject.get());

            tag(TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("carryon", "entity_blacklist")))
                    .add(entityTypeRegistryObject.get());
        });
    }
}
