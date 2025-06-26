package com.renyigesai.unusualfoodsdelight.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.renyigesai.unusualfoodsdelight.UnusualFoodDelight;
import com.renyigesai.unusualfoodsdelight.init.UdBlocks;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SmokedMeatRecipes implements Recipe<SimpleContainer> {

    private final NonNullList<Ingredient> inputItems;
    private final ItemStack output;
    private final ResourceLocation id;

    public SmokedMeatRecipes(NonNullList<Ingredient> ingredient, ItemStack output, ResourceLocation id) {
        this.inputItems = ingredient;
        this.output = output;
        this.id = id;
    }


    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        return this.inputItems.get(0).test(pContainer.getItem(0));
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        return inputItems;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SmokedMeatRecipes.Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return SmokedMeatRecipes.Type.INSTANCE;
    }

    public static class Type implements RecipeType<SmokedMeatRecipes> {
        public static final SmokedMeatRecipes.Type INSTANCE = new SmokedMeatRecipes.Type();
        public static final String ID = "smoked_meat";
    }

    public static class Serializer implements RecipeSerializer<SmokedMeatRecipes> {
        public static final SmokedMeatRecipes.Serializer INSTANCE = new SmokedMeatRecipes.Serializer();
        public static final ResourceLocation ID = new ResourceLocation(UnusualFoodDelight.MODID, "smoked_meat");

        @Override
        public SmokedMeatRecipes fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));

            // 动态获取原料数量
            JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.create();

//            for (int i = 0; i < ingredients.size(); i++) {
            inputs.add(Ingredient.fromJson(ingredients.get(0)));
//            }

            return new SmokedMeatRecipes(inputs,output,pRecipeId);
        }

        @Override
        public @Nullable SmokedMeatRecipes fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            int ingredientCount = pBuffer.readInt();
            NonNullList<Ingredient> inputs = NonNullList.withSize(ingredientCount, Ingredient.EMPTY);

//            for (int i = 0; i < ingredientCount; i++) {
            inputs.set(0, Ingredient.fromNetwork(pBuffer));
//            }
            ItemStack output = pBuffer.readItem();
            return new SmokedMeatRecipes(inputs, output,pRecipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, SmokedMeatRecipes pRecipe) {
            pBuffer.writeInt(pRecipe.inputItems.size());

            for (Ingredient ingredient : pRecipe.getIngredients()) {
                ingredient.toNetwork(pBuffer);
            }
            pBuffer.writeItemStack(pRecipe.getResultItem(null), false);
        }
    }

}
