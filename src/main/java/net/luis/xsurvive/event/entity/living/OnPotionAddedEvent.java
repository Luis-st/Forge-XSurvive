package net.luis.xsurvive.event.entity.living;

import net.luis.xsurvive.XSurvive;
import net.luis.xsurvive.init.XSurviveCapabilities;
import net.luis.xsurvive.init.XSurviveMobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.PotionEvent.PotionAddedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = XSurvive.MOD_ID)
public class OnPotionAddedEvent {
	
	@SubscribeEvent
	public static void potionAdded(PotionAddedEvent event) {
		MobEffectInstance instance = event.getPotionEffect();
		if (event.getEntityLiving() instanceof Player player && instance.getEffect() == XSurviveMobEffects.FROST.get()) {
			player.getCapability(XSurviveCapabilities.PLAYER, null).ifPresent((handler) -> {
				XSurvive.LOGGER.info("Working");
			});
		}
	}
	
}
