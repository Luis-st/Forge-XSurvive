package net.luis.xsurvive.data;

import net.luis.xsurvive.XSurvive;
import net.luis.xsurvive.data.provider.block.XSurviveBlockStateProvider;
import net.luis.xsurvive.data.provider.item.XSurviveItemModelProvider;
import net.luis.xsurvive.data.provider.language.XSurviveLanguageProvider;
import net.luis.xsurvive.data.provider.loot.XSurviveGlobalLootModifierProvider;
import net.luis.xsurvive.data.provider.loottable.XSurviveLootTableProvider;
import net.luis.xsurvive.data.provider.recipe.XSurviveRecipeProvider;
import net.luis.xsurvive.data.provider.tag.XSurviveBlockTagsProvider;
import net.luis.xsurvive.data.provider.tag.XSurviveItemTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@EventBusSubscriber(modid = XSurvive.MOD_ID, bus = Bus.MOD)
public class OnGatherDataEvent {
	
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		if (event.includeDev()) {
			if (event.includeClient()) {
			generator.addProvider(new XSurviveBlockStateProvider(generator, event.getExistingFileHelper()));
			generator.addProvider(new XSurviveItemModelProvider(generator, event.getExistingFileHelper()));
			generator.addProvider(new XSurviveLanguageProvider(generator));
		}
		if (event.includeServer()) {
			generator.addProvider(new XSurviveLootTableProvider(generator));
			generator.addProvider(new XSurviveRecipeProvider(generator));
			XSurviveBlockTagsProvider blockTagsProvider = new XSurviveBlockTagsProvider(generator, event.getExistingFileHelper());
			generator.addProvider(blockTagsProvider);
			generator.addProvider(new XSurviveItemTagsProvider(generator, blockTagsProvider, event.getExistingFileHelper()));
			generator.addProvider(new XSurviveGlobalLootModifierProvider(generator));
		}
		}
	}
	
}
