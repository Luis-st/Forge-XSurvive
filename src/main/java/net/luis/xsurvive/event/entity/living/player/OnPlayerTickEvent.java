package net.luis.xsurvive.event.entity.living.player;

import net.luis.xsurvive.XSurvive;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = XSurvive.MOD_ID)
public class OnPlayerTickEvent {
	
	@SubscribeEvent
	public static void playerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.START) {
//			Player player = event.player;
//			if (player instanceof ServerPlayer serverPlayer) {
//				CapabilityUtil.getServerPlayer(serverPlayer).tick();
//			} else if (player instanceof LocalPlayer localPlayer) {
//				CapabilityUtil.getLocalPlayer(localPlayer).tick();
//			}
		}
	}
	
}
