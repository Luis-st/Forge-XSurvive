package net.luis.xsurvive.common.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.FireAspectEnchantment;

public class FrostAspectEnchantment extends FireAspectEnchantment {
	
	public FrostAspectEnchantment(Rarity rarity, EquipmentSlot... slots) {
		super(rarity, slots);
	}
	
	@Override
	protected boolean checkCompatibility(Enchantment enchantment) {
		if (enchantment instanceof FireAspectEnchantment) {
			return enchantment != Enchantments.FIRE_ASPECT;
		}
		return super.checkCompatibility(enchantment);
	}
	
}
