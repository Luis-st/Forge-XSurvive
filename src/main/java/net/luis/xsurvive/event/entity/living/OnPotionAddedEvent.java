package net.luis.xsurvive.event.entity.living;

import net.luis.xsurvive.XSurvive;
import net.luis.xsurvive.common.capability.CapabilityUtil;
import net.luis.xsurvive.init.XSurviveMobEffects;
import net.luis.xsurvive.server.capability.ServerPlayerCapabilityHandler;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.event.entity.living.PotionEvent.PotionAddedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = XSurvive.MOD_ID)
public class OnPotionAddedEvent {
	
	@SubscribeEvent
	public static void potionAdded(PotionAddedEvent event) {
		MobEffectInstance instance = event.getPotionEffect();
		if (event.getEntityLiving() instanceof ServerPlayer player && instance.getEffect() == XSurviveMobEffects.FROST.get()) {
			ServerPlayerCapabilityHandler handler = CapabilityUtil.getServerPlayer(player);
			handler.setFrostTime(instance.getDuration());
		}
	}
	
}
