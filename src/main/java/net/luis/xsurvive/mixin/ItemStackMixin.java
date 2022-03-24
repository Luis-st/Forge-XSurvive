package net.luis.xsurvive.mixin;

import java.util.function.Consumer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.luis.xsurvive.init.XSurviveEnchantments;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
	
	@Shadow
	public abstract <T extends LivingEntity> void hurtAndBreak(int damage, T livingEntity, Consumer<T> consumer);
	
	@Inject(method = "inventoryTick", at = @At("TAIL"))
	public void inventoryTick(Level level, Entity entity, int slot, boolean selected, CallbackInfo callback) {
		if (entity instanceof LivingEntity livingEntity) {
			int breakingCurse = EnchantmentHelper.getItemEnchantmentLevel(XSurviveEnchantments.CURSE_OF_BREAKING.get(), (ItemStack) (Object) this);
			if (breakingCurse > 0 && level.getGameTime() % 100 == 0) {
				this.hurtAndBreak(breakingCurse * 2, livingEntity, (e) -> {
					
				});
			}
		}
	}
	
}
