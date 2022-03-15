package net.luis.xsurvive.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.world.item.enchantment.ArrowKnockbackEnchantment;

@Mixin(ArrowKnockbackEnchantment.class)
public abstract class ArrowKnockbackEnchantmentMixin {
	
	@Overwrite
	public int getMaxLevel() {
		return 3;
	}
	
}
