package net.luis.xsurvive.common.enchantment;

import net.luis.xsurvive.common.extension.IEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class BreakingCurseEnchantment extends Enchantment implements IEnchantment {

	public BreakingCurseEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot... slots) {
		super(rarity, category, slots);
	}
	
	@Override
	public int getMaxLevel() {
		return 1;
	}
	
	@Override
	public int getMinCost(int level) {
		return 25;
	}
	
	@Override
	public int getMaxCost(int level) {
		return 50;
	}
	
	@Override
	public boolean isTreasureOnly() {
		return true;
	}
	
	@Override
	public boolean isCurse() {
		return true;
	}
	
	@Override
	public boolean isAllowedOnGoldenBooks() {
		return false;
	}
	
}
