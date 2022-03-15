package net.luis.xsurvive.mixin;

import java.util.Random;
import java.util.Map.Entry;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

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
	public static boolean shouldHit(int level, Random rng) {
		return false;
	}
	
	@Overwrite
	public int getMaxLevel() {
		return 3;
	}
	
	@Overwrite
	public void doPostHurt(LivingEntity target, Entity attacker, int level) {
		Random rng = target.getRandom();
		Entry<EquipmentSlot, ItemStack> entry = EnchantmentHelper.getRandomItemWith(Enchantments.THORNS, target);
		if (shouldHit(level, rng)) {
			if (attacker != null) {
				attacker.hurt(DamageSource.thorns(target), getDamage(this.getThornsLevel(target), rng));
			}
			if (entry != null) {
				entry.getValue().hurtAndBreak(1, target, (entity) -> {
					entity.broadcastBreakEvent(entry.getKey());
				});
			}
		}
	}
	
	@Overwrite
	public static int getDamage(int level, Random rng) {
		return rng.nextInt(level);
	}

	private int getThornsLevel(LivingEntity entity) {
		return this.getThornsLevel(entity, EquipmentSlot.HEAD) + this.getThornsLevel(entity, EquipmentSlot.CHEST) + this.getThornsLevel(entity, EquipmentSlot.LEGS) + this.getThornsLevel(entity, EquipmentSlot.FEET);
	}
	
	private int getThornsLevel(LivingEntity entity, EquipmentSlot slot) {
		return EnchantmentHelper.getItemEnchantmentLevel((ThornsEnchantment) (Object) this, entity.getItemBySlot(slot));
	}
	
}
