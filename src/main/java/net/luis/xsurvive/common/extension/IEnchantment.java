package net.luis.xsurvive.common.extension;

import net.luis.xsurvive.XSurvive;
import net.luis.xsurvive.common.enchantment.EnchantedItem;
import net.luis.xsurvive.common.enchantment.GoldenEnchantmentInstance;
import net.luis.xsurvive.common.item.EnchantedGoldenBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;

public interface IEnchantment {
	
	private Enchantment self() {
		return (Enchantment) this;
	}
	
	boolean isAllowedOnGoldenBooks();
	
	int getMinGoldenBookLevel();
	
	int getMaxGoldenBookLevel();
	
	default boolean isGolden(int level) {
		return this.getMaxGoldenBookLevel() >= level && level >= this.getMinGoldenBookLevel();
	}
	
	default GoldenEnchantmentInstance createGoldenInstance(int level) {
		if (this.getMinGoldenBookLevel() > level) {
			return new GoldenEnchantmentInstance(this.self(), this.getMinGoldenBookLevel());
		} else if (level > this.getMaxGoldenBookLevel()) {
			return new GoldenEnchantmentInstance(this.self(), this.getMaxGoldenBookLevel());
		}
		return new GoldenEnchantmentInstance(this.self(), level);
	}
	
	static EnchantedItem merge(ItemStack left, ItemStack right) {
		ItemStack result = left.copy();
		if (right.getItem() instanceof EnchantedGoldenBookItem goldenBook) {
			Enchantment enchantment = goldenBook.getEnchantment(right);
			if (enchantment instanceof IEnchantment ench) {
				int level = EnchantmentHelper.getItemEnchantmentLevel(enchantment, result);
				GoldenEnchantmentInstance instance = ench.createGoldenInstance(level + 1);
				if (!IEnchantmentHelper.hasEnchantment(enchantment, result)) {
					IEnchantmentHelper.addEnchantment(new EnchantmentInstance(enchantment, 1), result, false);
				} else if (instance.isGolden() && instance.level > enchantment.getMaxLevel() && level >= enchantment.getMaxLevel()) {
					if (ench.getMaxGoldenBookLevel() > level) {
						IEnchantmentHelper.replaceEnchantment(instance, result);
						return new EnchantedItem(result, Math.max(0, level - enchantment.getMaxLevel()) * 10 + 30);
					}
					return new EnchantedItem(ItemStack.EMPTY, 0);
				} else {
					IEnchantmentHelper.increaseEnchantment(enchantment, result, false);
				}
			}
			return new EnchantedItem(result, 10);
		}
		XSurvive.LOGGER.error("Can not merge a Item with a other Item, since the right Item must be a EnchantedGoldenBookItem");
		return new EnchantedItem(ItemStack.EMPTY, 0);
	}
	
}
