package net.luis.xsurvive.common.enchantment;

import net.luis.xsurvive.common.extension.IEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;

public class GoldenEnchantmentInstance extends EnchantmentInstance {

	public GoldenEnchantmentInstance(Enchantment enchantment, int level) {
		super(enchantment, level);
	}
	
	public boolean isGolden() {
		return this.enchantment instanceof IEnchantment ench && ench.isAllowedOnGoldenBooks() && ench.isGolden(this.level);
	}

}
