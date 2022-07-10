package net.luis.xsurvive.world.item.crafting;

import net.luis.xsurvive.XSurvive;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class XSurviveRecipeSerializers {
	
	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, XSurvive.MOD_ID);
	
	
	public static final RegistryObject<RecipeSerializer<SmeltingRecipe>> SMELTING_RECIPE = RECIPE_SERIALIZERS.register("smelting", () -> {
		return new SimpleCookingSerializer<SmeltingRecipe>(SmeltingRecipe::new, 100);
	});
	
}
