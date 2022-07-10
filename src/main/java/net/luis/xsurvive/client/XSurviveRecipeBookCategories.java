package net.luis.xsurvive.client;

import net.luis.xsurvive.XSurvive;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

public class XSurviveRecipeBookCategories {
	
	public static final RecipeBookCategories SMELTING_FURNACE_SEARCH = RecipeBookCategories.create(XSurvive.MOD_ID + "_smelting_furnace_search", new ItemStack(Items.COMPASS));
	public static final RecipeBookCategories SMELTING_FURNACE_BLOCKS = RecipeBookCategories.create(XSurvive.MOD_ID + "_smelting_furnace_blocks", new ItemStack(Blocks.AIR));
	public static final RecipeBookCategories SMELTING_FURNACE_MISC = RecipeBookCategories.create(XSurvive.MOD_ID + "_smelting_furnace_misc", new ItemStack(Blocks.AIR));
	
}
