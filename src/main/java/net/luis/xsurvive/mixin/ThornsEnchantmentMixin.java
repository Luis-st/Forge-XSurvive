package net.luis.xsurvive.mixin;

import java.util.Map.Entry;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ThornsEnchantment;

@Mixin(ThornsEnchantment.class)
public abstract class ThornsEnchantmentMixin {
	
	@Shadow
	public static boolean shouldHit(int level, RandomSource rng) {
		return false;
	}
	
	@Inject(method = "getMaxLevel", at = @At("HEAD"), cancellable = true)
	public void getMaxLevel(CallbackInfoReturnable<Integer> callback) {
		callback.setReturnValue(4);
	}
	
	@Inject(method = "doPostHurt", at = @At("HEAD"), cancellable = true)
	public void doPostHurt(LivingEntity target, Entity attacker, int level, CallbackInfo callback) {
		RandomSource rng = target.getRandom();
		Entry<EquipmentSlot, ItemStack> entry = EnchantmentHelper.getRandomItemWith(Enchantments.THORNS, target);
		if (shouldHit(level, rng)) {
			if (attacker != null) {
				attacker.hurt(DamageSource.thorns(target), rng.nextInt(this.getThornsLevel(target)));
			}
			if (entry != null) {
				entry.getValue().hurtAndBreak(1, target, (entity) -> {
					entity.broadcastBreakEvent(entry.getKey());
				});
			}
		}
		callback.cancel();
	}

	private int getThornsLevel(LivingEntity entity) {
		return this.getThornsLevel(entity, EquipmentSlot.HEAD) + this.getThornsLevel(entity, EquipmentSlot.CHEST) + this.getThornsLevel(entity, EquipmentSlot.LEGS) + this.getThornsLevel(entity, EquipmentSlot.FEET);
	}
	
	private int getThornsLevel(LivingEntity entity, EquipmentSlot slot) {
		return entity.getItemBySlot(slot).getEnchantmentLevel((ThornsEnchantment) (Object) this);
	}
	
}
