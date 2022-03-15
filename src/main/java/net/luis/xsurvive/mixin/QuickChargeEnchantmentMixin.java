package net.luis.xsurvive.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.world.item.enchantment.QuickChargeEnchantment;

@Mixin(QuickChargeEnchantment.class)
public abstract class QuickChargeEnchantmentMixin {
	
	@Overwrite
	public int getMaxLevel() {
		return 4;
	}
	
}
