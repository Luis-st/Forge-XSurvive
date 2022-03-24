package net.luis.xsurvive.init;

import net.luis.xsurvive.XSurvive;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class XSurviveEnchantmentCategory {
	
	public static final EnchantmentCategory TOOLS = EnchantmentCategory.create(new ResourceLocation(XSurvive.MOD_ID, "tools").toString(), (item) -> {
		return item instanceof DiggerItem || item instanceof SwordItem || item instanceof BowItem || item instanceof CrossbowItem;
	});
	
}
