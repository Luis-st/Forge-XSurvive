package net.luis.xsurvive.world.item.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class HarvestingEnchantment extends Enchantment {

	public HarvestingEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot... slots) {
		super(rarity, category, slots);
	}
	
	@Override
	public int getMaxLevel() {
		return 5;
	}
	
	@Override
	public int getMinCost(int level) {
		return 5 + (level - 1) * 5;
	}
	
	@Override
	public int getMaxCost(int level) {
		return this.getMinCost(level) + 20;
	}
	
	@Override
	protected boolean checkCompatibility(Enchantment enchantment) {
		if (enchantment == XSurviveEnchantments.HARVESTING.get()) {
			return false;
		}
		return super.checkCompatibility(enchantment);
	}

}
