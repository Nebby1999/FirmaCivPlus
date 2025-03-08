package com.nebby1999.firmacivplus.datagen;

import com.nebby1999.firmacivplus.datagen.loot.FirmaCivPlusBlockLootTables;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

public class FirmaCivPlusLootTableProvider
{
    public static LootTableProvider create(PackOutput output)
    {
        return new LootTableProvider(output, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(FirmaCivPlusBlockLootTables::new, LootContextParamSets.BLOCK)
        ));
    }
}
