package net.luis.xsurvive;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.luis.xsurvive.init.XSurviveEnchantments;
import net.luis.xsurvive.init.XSurviveGlobalLootModifiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(XSurvive.MOD_ID)
public class XSurvive {
	
	public static final String MOD_ID = "xsurvive";
	public static final Logger LOGGER = LogManager.getLogger(XSurvive.class);
	
	public XSurvive() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		XSurviveEnchantments.ENCHANTMENTS.register(modEventBus);
		XSurviveGlobalLootModifiers.LOOT_MODIFIERS.register(modEventBus);
	}
	
}
