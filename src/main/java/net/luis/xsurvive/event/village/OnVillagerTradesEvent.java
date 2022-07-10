package net.luis.xsurvive.event.village;

import net.luis.xsurvive.XSurvive;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = XSurvive.MOD_ID)
public class OnVillagerTradesEvent {
	
	@SubscribeEvent
	public static void villagerTrades(VillagerTradesEvent event) {
		
	}
	
}
