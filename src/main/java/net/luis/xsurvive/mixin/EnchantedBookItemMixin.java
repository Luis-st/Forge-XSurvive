package net.luis.xsurvive.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.luis.xsurvive.XSurvive;
import net.luis.xsurvive.common.extension.IEnchantment;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;

@Mixin(EnchantedBookItem.class)
public abstract class EnchantedBookItemMixin {
	
	@Overwrite
	@SuppressWarnings("deprecation")
	public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> stacks) {
		if (tab == CreativeModeTab.TAB_SEARCH) {
			for (Enchantment enchantment : Registry.ENCHANTMENT) {
				if (enchantment.category != null) {
					if (enchantment instanceof IEnchantment ench) {
						if (!ench.isUpgrade()) {
							for (int i = enchantment.getMinLevel(); i <= enchantment.getMaxLevel(); ++i) {
								stacks.add(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantment, i)));
							}
						}
					} else {
						XSurvive.LOGGER.error("Enchantment {} is not a instance of IEnchantment", enchantment.getRegistryName());
						XSurvive.LOGGER.info("A deprecate vanilla logic is called");
						for (int i = enchantment.getMinLevel(); i <= enchantment.getMaxLevel(); ++i) {
							stacks.add(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantment, i)));
						}
					}

				}
			}
		} else if (tab.getEnchantmentCategories().length != 0) {
			for (Enchantment enchantment : Registry.ENCHANTMENT) {
				if (enchantment instanceof IEnchantment ench) {
					if (tab.hasEnchantmentCategory(enchantment.category) && !ench.isUpgrade()) {
						stacks.add(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantment, enchantment.getMaxLevel())));
					}
				} else {
					XSurvive.LOGGER.error("Enchantment {} is not a instance of IEnchantment", enchantment.getRegistryName());
					XSurvive.LOGGER.info("A deprecate vanilla logic is called");
					if (tab.hasEnchantmentCategory(enchantment.category)) {
						stacks.add(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantment, enchantment.getMaxLevel())));
					}
				}
			}
		}

	}
	
}
