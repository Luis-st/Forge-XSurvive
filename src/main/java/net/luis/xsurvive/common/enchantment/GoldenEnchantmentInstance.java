package net.luis.xsurvive.common.enchantment;

import net.luis.xsurvive.XSurvive;
import net.luis.xsurvive.common.extension.IEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;

public class GoldenEnchantmentInstance extends EnchantmentInstance {

	public GoldenEnchantmentInstance(Enchantment enchantment, int level) {
		super(enchantment, level);
	}
	
	public boolean isGolden() {
		if (this.enchantment instanceof IEnchantment ench) {
			return ench.isAllowedOnGoldenBooks() && ench.isGolden(this.level);
		}
		XSurvive.LOGGER.error("Enchantment {} is not a instance of IEnchantment", this.enchantment.getRegistryName());
		return false;
	}

}
