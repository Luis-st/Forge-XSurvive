package net.luis.xsurvive;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.luis.xsurvive.world.effect.XSurviveMobEffects;
import net.luis.xsurvive.world.item.XSurviveCreativeModeTab;
import net.luis.xsurvive.world.item.XSurviveItems;
import net.luis.xsurvive.world.item.alchemy.XSurvivePotions;
import net.luis.xsurvive.world.item.enchantment.XSurviveEnchantments;
import net.luis.xsurvive.world.level.entity.ai.village.poi.XSurvivePoiTypes;
import net.luis.xsurvive.world.level.entity.npc.XSurviveVillagerProfessions;
import net.luis.xsurvive.world.level.storage.loot.XSurviveGlobalLootModifiers;
import net.luis.xsurvive.world.level.storage.loot.predicates.XSurviveLootItemConditions;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(XSurvive.MOD_ID)
public class XSurvive {
	
	public static final String MOD_ID = "xsurvive";
	public static final String MOD_NAME = "XSurvive";
	public static final Logger LOGGER = LogUtils.getLogger();
	public static final CreativeModeTab TAB = new XSurviveCreativeModeTab(XSurvive.MOD_ID);
	
	public XSurvive() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		XSurviveEnchantments.ENCHANTMENTS.register(modEventBus);
		XSurviveLootItemConditions.LOOT_ITEM_CONDITIONS.register(modEventBus);
		XSurviveGlobalLootModifiers.LOOT_MODIFIERS.register(modEventBus);
		XSurviveItems.ITEMS.register(modEventBus);
		XSurviveMobEffects.MOB_EFFECTS.register(modEventBus);
		XSurvivePotions.POTIONS.register(modEventBus);
		XSurvivePoiTypes.POI_TYPES.register(modEventBus);
		XSurviveVillagerProfessions.PROFESSIONS.register(modEventBus);
	}
	
}
