package com.nebby1999.firmacivplus.datagen;

import com.nebby1999.firmacivplus.FirmaCivPlus;
import com.nebby1999.firmacivplus.FirmaCivPlusBlocks;
import com.nebby1999.firmacivplus.datagen.recipe_builders.AdvancedCraftingRecipeBuilder;
import com.nebby1999.firmacivplus.datagen.recipe_builders.DisabledRecipe;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.crafting.*;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.FalseCondition;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

public class FirmaCivPlusRecipeProvider extends RecipeProvider
{
    public FirmaCivPlusRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {

        var sawTag = TagKey.create(Registries.ITEM, new ResourceLocation("tfc", "saws"));
        FirmaCivPlusBlocks.getWoodRoofings().forEach((watercraftMaterial, squaredAngleBlockRegistryObject) ->
        {
            var planks = watercraftMaterial.getWood().getBlock(Wood.BlockType.PLANKS).get();

            //crafting roofing
            AdvancedCraftingRecipeBuilder.shaped("crafting", squaredAngleBlockRegistryObject.get(), 6, CraftingBookCategory.BUILDING)
                    .pattern("  a")
                    .pattern(" a ")
                    .pattern("a  ")
                    .define('a', planks)
                    .unlockedBy("has_plank", has(planks))
                    .showNotification(true)
                    .save(consumer);

            var lumber = watercraftMaterial.getRailing();
            var lumberRegistry = ForgeRegistries.ITEMS.getKey(lumber);
            //uncrafting roofing
            AdvancedCraftingRecipeBuilder.shapeless("crafting", watercraftMaterial.getRailing(), 2)
                    .requires(squaredAngleBlockRegistryObject.get())
                    .requires(sawTag)
                    .primaryIngredient(Ingredient.of(sawTag))
                    .damageInputs()
                    .unlockedBy("has_saw", has(sawTag))
                    .unlockedBy("has_roofing", has(squaredAngleBlockRegistryObject.get()))
                    .save(consumer, new ResourceLocation(FirmaCivPlus.MOD_ID, "crafting/" + lumberRegistry.getPath()));

            //kill off the boat
            var dummy = new DisabledRecipe(new ResourceLocation(lumberRegistry.getNamespace(), "crafting/wood/" + watercraftMaterial.getSerializedName() + "_boat"));
            consumer.accept(dummy);
        });
    }
}
