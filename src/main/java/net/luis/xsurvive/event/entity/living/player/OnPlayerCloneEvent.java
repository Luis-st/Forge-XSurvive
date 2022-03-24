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
		if (event.getOriginal() instanceof ServerPlayer original && event.getPlayer() instanceof ServerPlayer player) {
			original.reviveCaps();
			IPlayerCapability originalHandler = CapabilityUtil.getPlayer(original);
			IPlayerCapability handler = CapabilityUtil.getPlayer(player);
			if (event.isWasDeath()) {
				handler.deserializePersistent(originalHandler.serializePersistent());
			} else {
				handler.deserialize(originalHandler.serialize());
			}
			original.invalidateCaps();
		}
	}
	
}
