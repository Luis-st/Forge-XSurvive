package net.luis.xsurvive;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.luis.xsurvive.common.XSurviveCreativeModeTab;
import net.luis.xsurvive.init.XSurviveEnchantments;
import net.luis.xsurvive.init.XSurviveGlobalLootModifiers;
import net.luis.xsurvive.init.XSurviveItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(XSurvive.MOD_ID)
public class XSurvive {
	
	public static final String MOD_ID = "xsurvive";
	public static final String MOD_NAME = "XSurvive";
	public static final Logger LOGGER = LogManager.getLogger(XSurvive.class);
	public static final CreativeModeTab TAB = new XSurviveCreativeModeTab(XSurvive.MOD_ID);
	
	public XSurvive() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		XSurviveEnchantments.ENCHANTMENTS.register(modEventBus);
		XSurviveGlobalLootModifiers.LOOT_MODIFIERS.register(modEventBus);
		XSurviveItems.ITEMS.register(modEventBus);
	}
	
}
