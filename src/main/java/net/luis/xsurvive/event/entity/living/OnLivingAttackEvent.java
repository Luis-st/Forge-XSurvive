package net.luis.xsurvive.event.entity.living;

import net.luis.xsurvive.XSurvive;
import net.luis.xsurvive.common.handler.EnchantmentHandler;
import net.luis.xsurvive.init.XSurviveEnchantments;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = XSurvive.MOD_ID)
public class OnLivingAttackEvent {
	
	@SubscribeEvent
	public static void livingAttack(LivingAttackEvent event) {
		Entity target = event.getEntityLiving();
		DamageSource source = event.getSource();
		float amount = event.getAmount();
		if (source instanceof EntityDamageSource entitySource && entitySource.getEntity() instanceof Player player) {
			if (target instanceof LivingEntity livingTarget && amount > 0.0F) {
				int harmingCurse = EnchantmentHandler.getEnchantmentLevel(XSurviveEnchantments.CURSE_OF_HARMING.get(), player);
				if (harmingCurse > 0) {
					float damage = (amount / 2.0F) * (float) harmingCurse;
					if (player.hurt(new EntityDamageSource("curse_of_harming", livingTarget), damage)) {
						event.setCanceled(true);
					}
				}
			}
		}
	}
	
}
