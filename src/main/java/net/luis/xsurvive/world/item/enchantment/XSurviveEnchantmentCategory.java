package net.luis.xsurvive.world.item.enchantment;

import net.luis.xores.world.item.ElytraChestplateItem;
import net.luis.xsurvive.XSurvive;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class XSurviveEnchantmentCategory {
	
	public static final EnchantmentCategory TOOLS = EnchantmentCategory.create(new ResourceLocation(XSurvive.MOD_ID, "tools").toString(), (item) -> {
		return item instanceof DiggerItem || item instanceof SwordItem || item instanceof BowItem || item instanceof CrossbowItem;
	});
	public static final EnchantmentCategory WEAPONS = EnchantmentCategory.create(new ResourceLocation(XSurvive.MOD_ID, "weapons").toString(), (item) -> {
		return item instanceof SwordItem || item instanceof BowItem || item instanceof CrossbowItem;
	});
	public static final EnchantmentCategory ELYTRA = EnchantmentCategory.create(new ResourceLocation(XSurvive.MOD_ID, "elytra").toString(), (item) -> {
		return item instanceof ElytraItem || item instanceof ElytraChestplateItem;
	});
	
}
