package net.luis.xsurvive.event.entity.living;

import net.luis.xsurvive.XSurvive;
import net.luis.xsurvive.common.handler.EnchantmentHandler;
import net.luis.xsurvive.common.handler.EntityHandler;
import net.luis.xsurvive.init.XSurviveEnchantments;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = XSurvive.MOD_ID)
public class OnLivingDamageEvent {
	
	@SubscribeEvent
	public static void livingDamage(LivingDamageEvent event) {
		Entity target = event.getEntity();
		DamageSource source = event.getSource();
		float amount = event.getAmount();
		float newAmount = amount;
		if (source instanceof EntityDamageSource entitySource && entitySource.getEntity() instanceof Player player) {
			int enderSlayer = EnchantmentHandler.getEnchantmentLevel(XSurviveEnchantments.ENDER_SLAYER.get(), player);
			int impaling = EnchantmentHandler.getEnchantmentLevel(Enchantments.IMPALING, player);
			if (enderSlayer > 0 && EntityHandler.isAffectedByEnderSlayer(target)) {
				newAmount = (float) (amount * 2.5);
			} else if (impaling > 0 && EntityHandler.isAffectedByImpaling(target)) {
				newAmount = (float) (amount * 2.5);
			}
		}
		event.setAmount(newAmount);
	}
	
}
