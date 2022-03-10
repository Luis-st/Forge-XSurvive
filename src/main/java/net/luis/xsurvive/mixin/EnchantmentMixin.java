package net.luis.xsurvive.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.luis.xsurvive.common.extension.IEnchantment;
import net.luis.xsurvive.init.XSurviveEnchantments;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

@Mixin(Enchantment.class)
public abstract class EnchantmentMixin implements IEnchantment {
	
	@Shadow
	public abstract int getMaxLevel();
	
	@Shadow
	public abstract String getDescriptionId();
	
	@Shadow
	public abstract boolean isCurse();
	
	@Shadow
	protected abstract boolean checkCompatibility(Enchantment enchantment);
	
	@Inject(method = "isCompatibleWith", at = @At("HEAD"), cancellable = true)
	public void isCompatibleWith(Enchantment enchantment, CallbackInfoReturnable<Boolean> callback) {
		Enchantment ench = (Enchantment) (Object) this;
		if ((ench == Enchantments.INFINITY_ARROWS && enchantment == Enchantments.MENDING) || (ench == Enchantments.MENDING && enchantment == Enchantments.INFINITY_ARROWS)) {
			callback.setReturnValue(true);
			callback.cancel();
		} else if ((ench == Enchantments.PIERCING && enchantment == Enchantments.MULTISHOT) || (ench == Enchantments.MULTISHOT && enchantment == Enchantments.PIERCING)) {
			callback.setReturnValue(true);
			callback.cancel();
		} else if ((ench == Enchantments.LOYALTY && enchantment == Enchantments.CHANNELING) || (ench == Enchantments.CHANNELING && enchantment == Enchantments.LOYALTY)) {
			callback.setReturnValue(true);
			callback.cancel();
		}
	}
	
	@Inject(method = "getFullname", at = @At("RETURN"), cancellable = true)
	public void getFullname(int level, CallbackInfoReturnable<Component> callback) {
		if (this.isAllowedOnGoldenBooks() && this.getMaxGoldenBookLevel() >= level && level >= this.getMinGoldenBookLevel()) {
			MutableComponent component = new TranslatableComponent(this.getDescriptionId());
			component.append(" ").append(new TranslatableComponent("enchantment.level." + level));
			callback.setReturnValue(component.withStyle(ChatFormatting.DARK_PURPLE));
			callback.cancel();
		}
	}
	
	@Override
	public boolean isAllowedOnGoldenBooks() {
		Enchantment enchantment = (Enchantment) (Object) this;
		if (this.isCurse()) {
			return false;
		} else if (enchantment == Enchantments.AQUA_AFFINITY) {
			return false;
		} else if (enchantment == Enchantments.FIRE_ASPECT) {
			return false;
		} else if (enchantment == Enchantments.SILK_TOUCH) {
			return false;
		} else if (enchantment == Enchantments.FLAMING_ARROWS) {
			return false;
		} else if (enchantment == Enchantments.INFINITY_ARROWS) {
			return false;
		} else if (enchantment == Enchantments.CHANNELING) {
			return false;
		} else if (enchantment == Enchantments.MULTISHOT) {
			return false;
		}
		return enchantment != XSurviveEnchantments.MULTI_DROP.get();
	}
	
	@Override
	public int getMinGoldenBookLevel() {
		return this.getMaxLevel() + 1;
	}
	
	@Override
	public int getMaxGoldenBookLevel() {
		return this.getMaxLevel() + 5;
	}
	
}
