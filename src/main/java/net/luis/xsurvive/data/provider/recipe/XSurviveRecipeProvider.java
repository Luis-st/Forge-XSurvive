package net.luis.xsurvive.data.provider.recipe;

import java.util.function.Consumer;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

public class XSurviveRecipeProvider extends RecipeProvider {
	
	public XSurviveRecipeProvider(DataGenerator generator) {
		super(generator);
	}
	
	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
		
	}
	
	@Override
	public String getName() {
		return "XSurviv Recipes";
	}
	
}