package net.luis.xsurvive.common.enchantment;

import net.luis.xsurvive.common.extension.IEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;

public class VoidWalkerEnchantment extends Enchantment implements IEnchantment {

	public VoidWalkerEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot... slots) {
		super(rarity, category, slots);
	}
	
	@Override
	public int getMaxLevel() {
		return 1;
	}
	
	@Override
	public int getMinCost(int level) {
		return 20;
	}
	
	@Override
	public int getMaxCost(int level) {
		return 50;
	}
	
	@Override
	protected boolean checkCompatibility(Enchantment enchantment) {
		if (enchantment == Enchantments.DEPTH_STRIDER) {
			return false;
		} else if (enchantment == Enchantments.FROST_WALKER) {
			return false;
		}	
		return super.checkCompatibility(enchantment);
	}
	
	@Override
	public boolean isTreasureOnly() {
		return true;
	}

	@Override
	public boolean isAllowedOnGoldenBooks() {
		return false;
	}
	
}
