package net.luis.xsurvive.event.fml;

import java.nio.file.Files;
import java.nio.file.Path;

import net.luis.xsurvive.XSurvive;
import net.luis.xsurvive.server.ServerIntegrationHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

@EventBusSubscriber(modid = XSurvive.MOD_ID, bus = Bus.MOD)
public class OnLoadCompleteEvent {
	
	@SubscribeEvent
	public static void loadComplete(FMLLoadCompleteEvent event) {
		try {
			Path serverPath = ServerIntegrationHelper.getServerDirectory();
			if (!Files.exists(serverPath)) {
				Files.createDirectories(serverPath);
				XSurvive.LOGGER.info("Create server directory");
			}
			ServerIntegrationHelper.createEulaFile(serverPath);
		} catch (Exception e) {
			XSurvive.LOGGER.error("Fail to create servers directory and eula");
		}
	}
	
}
