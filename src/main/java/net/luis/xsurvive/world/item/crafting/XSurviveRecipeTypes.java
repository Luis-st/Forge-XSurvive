package net.luis.xsurvive.world.item.crafting;

import net.luis.xsurvive.XSurvive;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class XSurviveRecipeTypes {
	
	public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, XSurvive.MOD_ID);
	
	
	public static final RegistryObject<RecipeType<SmeltingRecipe>> SMELTING = RECIPE_TYPES.register("smelting", () -> {
		return RecipeType.simple(new ResourceLocation(XSurvive.MOD_ID, "smelting"));
	});
	
}
