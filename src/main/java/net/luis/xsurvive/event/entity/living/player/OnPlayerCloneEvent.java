package net.luis.xsurvive.event.entity.living.player;

import net.luis.xsurvive.XSurvive;
import net.luis.xsurvive.common.capability.CapabilityUtil;
import net.luis.xsurvive.common.capability.IPlayerCapability;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = XSurvive.MOD_ID)
public class OnPlayerCloneEvent {
	
	@SubscribeEvent
	public static void playerClone(PlayerEvent.Clone event) {
		if (event.getOriginal() instanceof ServerPlayer oldPlayer && event.getPlayer() instanceof ServerPlayer newPlayer) {
			IPlayerCapability oldHandler = CapabilityUtil.getPlayer(oldPlayer);
			IPlayerCapability newHandler = CapabilityUtil.getPlayer(newPlayer);
			if (event.isWasDeath()) {
				newHandler.deserializePersistent(oldHandler.serializePersistent());
			} else {
				newHandler.deserialize(oldHandler.serialize());
			}
		}
	}
	
}
