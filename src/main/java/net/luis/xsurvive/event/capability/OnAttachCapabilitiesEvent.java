package net.luis.xsurvive.event.capability;

import net.luis.xsurvive.XSurvive;
import net.luis.xsurvive.client.capability.LocalPlayerCapabilityHandler;
import net.luis.xsurvive.server.capability.ServerPlayerCapabilityHandler;
import net.luis.xsurvive.world.capability.PlayerCapabilityProvider;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = XSurvive.MOD_ID)
public class OnAttachCapabilitiesEvent {
	
	@SubscribeEvent
	public static void AttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
		Entity entity = event.getObject();
		if (entity instanceof LocalPlayer player) {
			event.addCapability(new ResourceLocation(XSurvive.MOD_ID, "local_player_capability"), new PlayerCapabilityProvider(new LocalPlayerCapabilityHandler(player)));
		} else if (entity instanceof ServerPlayer player) {
			event.addCapability(new ResourceLocation(XSurvive.MOD_ID, "server_player_capability"), new PlayerCapabilityProvider(new ServerPlayerCapabilityHandler(player)));
		}
	}
	
}
