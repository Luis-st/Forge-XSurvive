package net.luis.xsurvive.common.handler;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

import net.luis.xsurvive.XSurvive;
import net.luis.xsurvive.common.extension.IEnchantment;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;

public class EnchantmentHandler {
	
	public static boolean hasEnchantment(Enchantment enchantment, ItemStack stack) {
		List<Enchantment> enchantments = EnchantmentHelper.getEnchantments(stack).keySet().stream().toList();
		return enchantments.contains(enchantment);
	}
	
	public static boolean hasGoldenEnchantment(ItemStack stack) {
		return !getGoldenEnchantments(stack).isEmpty();
	}
	
	public static List<Enchantment> getGoldenEnchantments(ItemStack stack) {
		List<Enchantment> enchantments = Lists.newArrayList();;
		for (Enchantment enchantment : EnchantmentHelper.getEnchantments(stack).keySet().stream().toList()) {
			if (enchantment instanceof IEnchantment ench) {
				if (ench.isAllowedOnGoldenBooks() && Math.max(0, EnchantmentHelper.getItemEnchantmentLevel(enchantment, stack) - enchantment.getMaxLevel()) > 0) {
					enchantments.add(enchantment);
				}
			} else {
				XSurvive.LOGGER.error("Enchantment {} is not a instance of IEnchantment", enchantment.getRegistryName());
			}
		}
		return enchantments;
	}
	
	public static boolean isMinEnchantment(Enchantment enchantment, ItemStack stack) {
		if (hasEnchantment(enchantment, stack)) {
			return EnchantmentHelper.getItemEnchantmentLevel(enchantment, stack) == enchantment.getMinLevel();
		}
		return false;
	}
	
	public static boolean isMaxEnchantment(Enchantment enchantment, ItemStack stack) {
		if (hasEnchantment(enchantment, stack)) {
			return EnchantmentHelper.getItemEnchantmentLevel(enchantment, stack) == enchantment.getMaxLevel();
		}
		return false;
	}
	
	public static void addEnchantment(EnchantmentInstance instance, ItemStack stack, boolean present) {
		Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
		if ((!hasEnchantment(instance.enchantment, stack) || present) && EnchantmentHelper.isEnchantmentCompatible(enchantments.keySet(), instance.enchantment)) {
			enchantments.put(instance.enchantment, instance.level);
		}
		EnchantmentHelper.setEnchantments(enchantments, stack);
	}
	
	public static void removeEnchantment(Enchantment enchantment, ItemStack stack) {
		Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
		if (hasEnchantment(enchantment, stack)) {
			enchantments.remove(enchantment);
		}
		EnchantmentHelper.setEnchantments(enchantments, stack);
	}
	
	public static void replaceEnchantment(EnchantmentInstance instance, ItemStack stack) {
		if (hasEnchantment(instance.enchantment, stack)) {
			Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
			enchantments.replace(instance.enchantment, instance.level);
			EnchantmentHelper.setEnchantments(enchantments, stack);
		}
	}
	
	public static void increaseEnchantment(Enchantment enchantment, int count, ItemStack stack, boolean golden) {
		for (int i = 0; i < count; i++) {
			increaseEnchantment(enchantment, stack, golden);
		}
	}
	
	public static void increaseEnchantment(Enchantment enchantment, ItemStack stack, boolean golden) {
		if (hasEnchantment(enchantment, stack)) {
			if (!isMaxEnchantment(enchantment, stack) || golden) {
				replaceEnchantment(new EnchantmentInstance(enchantment, EnchantmentHelper.getItemEnchantmentLevel(enchantment, stack) + 1), stack);
			}
		} else {
			addEnchantment(new EnchantmentInstance(enchantment, 1), stack, false);
		}
	}
	
	public static void decreaseEnchantment(Enchantment enchantment, int count, ItemStack stack) {
		for (int i = 0; i < count; i++) {
			decreaseEnchantment(enchantment, stack);
		}
	}
	
	public static void decreaseEnchantment(Enchantment enchantment, ItemStack stack) {
		if (hasEnchantment(enchantment, stack)) {
			if (isMinEnchantment(enchantment, stack)) {
				removeEnchantment(enchantment, stack);
			} else {
				replaceEnchantment(new EnchantmentInstance(enchantment, EnchantmentHelper.getItemEnchantmentLevel(enchantment, stack) - 1), stack);
			}
		}
	}
	
}
