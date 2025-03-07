package com.nebby1999.firmacivplus.datagen;

import com.nebby1999.firmacivplus.FirmaCivPlus;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = FirmaCivPlus.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DataGenerators
{
    @SubscribeEvent
    public static void gatherData(GatherDataEvent evt)
    {
        DataGenerator generator = evt.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper helper = evt.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = evt.getLookupProvider();

        generator.addProvider(evt.includeClient(), new FirmaCivPlusBlockStateProvider(packOutput, helper));
        generator.addProvider(evt.includeClient(), new FirmaCivPlus_en_us_LanguageProvider(packOutput));

        var blockTagGenerator = generator.addProvider(evt.includeServer(), new FirmaCivPlusBlockTagGenerator(packOutput, evt.getLookupProvider(), helper));
        generator.addProvider(evt.includeServer(), new FirmaCivPlusItemTagGenerator(packOutput, lookupProvider, blockTagGenerator.contentsGetter(), helper));
        generator.addProvider(evt.includeServer(), new FirmaCivPlusEntityTypeTagsGenerator(packOutput, lookupProvider, helper));
        generator.addProvider(evt.includeServer(), FirmaCivPlusLootTableProvider.create(packOutput));
        generator.addProvider(evt.includeServer(), new FirmaCivPlusRecipeProvider(packOutput));
    }
}
