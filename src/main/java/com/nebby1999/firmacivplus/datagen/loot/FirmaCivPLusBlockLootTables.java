package com.nebby1999.firmacivplus.datagen.loot;

import com.alekiponi.alekiships.common.block.FlatBoatFrameBlock;
import com.alekiponi.firmaciv.common.block.FirmacivAngledBoatFrameBlock;
import com.alekiponi.firmaciv.common.block.FirmacivBlocks;
import com.alekiponi.firmaciv.common.block.FirmacivFlatBoatFrameBlock;
import com.alekiponi.firmaciv.common.block.FirmacivFlatWoodenBoatFrameBlock;
import com.alekiponi.firmaciv.common.item.FirmacivItems;
import com.nebby1999.firmacivplus.FirmaCivPlusBlocks;
import com.nebby1999.firmacivplus.WatercraftMaterial;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.*;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraftforge.registries.RegistryObject;
import org.antlr.v4.tool.Alternative;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Set;

public class FirmaCivPlusBlockLootTables extends BlockLootSubProvider
{
    public FirmaCivPlusBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate()
    {
        getKnownBlocks().forEach(this::dropSelf);
        FirmaCivPlusBlocks.getCanoeComponentBlocks().forEach((watercraftMaterial, canoeComponentBlockRegistryObject) ->
        {
            add(canoeComponentBlockRegistryObject.get(), block ->
                createSingleItemTable(watercraftMaterial.getRailing()));
        });

        FirmaCivPlusBlocks.getWoodenBoatFrameFlatBlocks().forEach((watercraftMaterial, firmacivFlatWoodenBoatFrameBlockRegistryObject) ->
        {
            add(firmacivFlatWoodenBoatFrameBlockRegistryObject.get(), block ->
                    createBoatFrameFlatTable(block, watercraftMaterial));
        });
    }

    protected <T extends Comparable<T> & StringRepresentable> LootTable.Builder createBoatFrameFlatTable(Block pBlock, WatercraftMaterial material) {
        var lootTable = LootTable.lootTable();

        //base frame drop
        lootTable
                .withPool(applyExplosionCondition(pBlock, LootPool.lootPool().setRolls(ConstantValue.exactly(1F)))
                                .add(LootItem.lootTableItem(FirmacivBlocks.BOAT_FRAME_FLAT.get())));

        //handle plank drop
        ArrayList<LootPoolEntryContainer.Builder<?>> alternatives = new ArrayList<>();
        for(int frameProcessed : FirmacivFlatWoodenBoatFrameBlock.FRAME_PROCESSED.getPossibleValues())
        {
            int count = Math.min(frameProcessed + 1, 4);
            alternatives.add(LootItem.lootTableItem(material.getDeckItem())
                    .when(LootItemBlockStatePropertyCondition
                            .hasBlockStateProperties(pBlock)
                            .setProperties(StatePropertiesPredicate.Builder.properties()
                                    .hasProperty(FirmacivFlatWoodenBoatFrameBlock.FRAME_PROCESSED, frameProcessed)))
                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(count))));
        }
        lootTable.withPool(applyExplosionCondition(pBlock, LootPool.lootPool().setRolls(ConstantValue.exactly(1f))
                        .add(AlternativesEntry.alternatives(alternatives, func -> func))));

        alternatives.clear();
        for(int frameProcessed : FirmacivFlatWoodenBoatFrameBlock.FRAME_PROCESSED.getPossibleValues())
        {
            if(frameProcessed + 1 <= 4)
            {
                continue;
            }

            int count = frameProcessed % 4;
            alternatives.add(LootItem.lootTableItem(FirmacivItems.COPPER_BOLT.get())
                    .when(LootItemBlockStatePropertyCondition
                            .hasBlockStateProperties(pBlock)
                            .setProperties(StatePropertiesPredicate.Builder.properties()
                                    .hasProperty(FirmacivFlatWoodenBoatFrameBlock.FRAME_PROCESSED, frameProcessed)))
                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(count + 1))));
        }
        lootTable.withPool(applyExplosionCondition(pBlock, LootPool.lootPool().setRolls(ConstantValue.exactly(1f))
                .add(AlternativesEntry.alternatives(alternatives, func -> func))));

        return lootTable;
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return FirmaCivPlusBlocks.BLOCKS.getEntries().stream().filter(b ->
        {
            var flag1 = FirmaCivPlusBlocks.getCanoeComponentBlocks().containsValue(b);
            var flag2 = FirmaCivPlusBlocks.getWoodenBoatFrameFlatBlocks().containsValue(b);

            return flag1 || flag2;
        }).map(RegistryObject::get)::iterator;
    }
}
