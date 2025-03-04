package com.nebby1999.firmacivplus.datagen;

import com.nebby1999.firmacivplus.FirmaCivPlus;
import com.nebby1999.firmacivplus.FirmaCivPlusBlocks;
import com.nebby1999.firmacivplus.FirmaCivPlusItems;
import com.nebby1999.firmacivplus.datagen.recipe_builders.AdvancedCraftingRecipeBuilder;
import com.therighthon.afc.common.blocks.AFCWood;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.common.recipes.DamageInputsCraftingRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Blocks;
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

        });
    }
}
