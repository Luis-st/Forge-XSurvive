package net.luis.xsurvive.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.TridentImpalerEnchantment;

@Mixin(TridentImpalerEnchantment.class)
public abstract class TridentImpalerEnchantmentMixin extends Enchantment {

	private TridentImpalerEnchantmentMixin(Rarity rarity, EnchantmentCategory category, EquipmentSlot... slots) {
		super(rarity, category, slots);
	}
	
	@Override
	protected boolean checkCompatibility(Enchantment enchantment) {
		if (enchantment instanceof DamageEnchantment) {
			return false;
		}
		return super.checkCompatibility(enchantment);
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		if (stack.getItem() instanceof SwordItem) {
			return true;
		} else if (stack.getItem() instanceof AxeItem) {
			return true;
		} else if (stack.getItem() instanceof TridentItem) {
			return true;
		}
		return super.canApplyAtEnchantingTable(stack);
	}
	
	@Overwrite
	public float getDamageBonus(int level, MobType mobType) {
		return 0.0F;
	}
	
}
