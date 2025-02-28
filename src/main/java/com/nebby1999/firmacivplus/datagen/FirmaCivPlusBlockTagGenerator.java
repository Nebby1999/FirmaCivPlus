package com.nebby1999.firmacivplus.datagen;

import com.alekiponi.firmaciv.util.FirmacivTags;
import com.nebby1999.firmacivplus.FirmaCivPlus;
import com.nebby1999.firmacivplus.FirmaCivPlusBlocks;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class FirmaCivPlusBlockTagGenerator extends BlockTagsProvider
{
    public FirmaCivPlusBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(output, lookupProvider, FirmaCivPlus.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider)
    {
        FirmaCivPlusBlocks.getCanoeComponentBlocks().forEach((watercraftMaterial, canoeComponentBlockRegistryObject) ->
        {
            tag(FirmacivTags.Blocks.CANOE_COMPONENT_BLOCKS)
                    .add(canoeComponentBlockRegistryObject.get());

            tag(FirmacivTags.Blocks.CAN_MAKE_CANOE)
                    .add(watercraftMaterial.getWood().getBlock(Wood.BlockType.STRIPPED_LOG).get());
        });
    }
}
