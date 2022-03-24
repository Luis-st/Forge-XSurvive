package net.luis.xsurvive.event.entity.living.player;

import net.luis.xsurvive.XSurvive;
import net.luis.xsurvive.init.XSurviveCapabilities;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = XSurvive.MOD_ID)
public class OnPlayerTickEvent {
	
	@SubscribeEvent
	public static void playerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.START) {
			Player player = event.player;
			if (player instanceof ServerPlayer serverPlayer) {
				serverPlayer.getCapability(XSurviveCapabilities.PLAYER, null).ifPresent((handler) -> {
					handler.tick();
				});
			} else if (player instanceof LocalPlayer localPlayer) {
				localPlayer.getCapability(XSurviveCapabilities.PLAYER, null).ifPresent((handler) -> {
					handler.tick();
				});
			}
		}
	}
	
}
