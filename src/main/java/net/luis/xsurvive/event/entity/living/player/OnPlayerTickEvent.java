package net.luis.xsurvive.event.entity.living.player;

import net.luis.xsurvive.XSurvive;
import net.luis.xsurvive.common.capability.CapabilityUtil;
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
				CapabilityUtil.getServerPlayer(serverPlayer).tick();
			} else if (player instanceof LocalPlayer localPlayer) {
				CapabilityUtil.getLocalPlayer(localPlayer).tick();
			}
		}
	}
	
}
