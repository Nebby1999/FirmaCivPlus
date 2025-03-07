package com.nebby1999.firmacivplus.datagen.recipe_builders;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.FalseCondition;
import net.minecraftforge.registries.ObjectHolder;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public final class DisabledRecipe implements FinishedRecipe
{
    ResourceLocation recipeID;
    public DisabledRecipe(ResourceLocation id)
    {
        recipeID = id;
    }

    @Override
    public JsonObject serializeRecipe() {
        JsonObject jsonobject = new JsonObject();
        this.serializeRecipeData(jsonobject);
        return jsonobject;
    }

    @Override
    public void serializeRecipeData(JsonObject pJson) {
        JsonArray conditionsArray = new JsonArray();
        conditionsArray.add(CraftingHelper.serialize(FalseCondition.INSTANCE));

        pJson.add("conditions", conditionsArray);
    }

    @Override
    public ResourceLocation getId() {
        return recipeID;
    }

    @Override
    public RecipeSerializer<?> getType() {
        return null;
    }

    @Override
    public @Nullable JsonObject serializeAdvancement() {
        return null;
    }

    @Override
    public @Nullable ResourceLocation getAdvancementId() {
        return null;
    }
}
