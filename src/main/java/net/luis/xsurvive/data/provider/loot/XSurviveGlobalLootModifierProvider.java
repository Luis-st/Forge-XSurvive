package net.luis.xsurvive.data.provider.loot;

import net.luis.xsurvive.XSurvive;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

public class XSurviveGlobalLootModifierProvider extends GlobalLootModifierProvider {

	public XSurviveGlobalLootModifierProvider(DataGenerator generator) {
		super(generator, XSurvive.MOD_ID);
	}

	@Override
	protected void start() {
		
	}
	
	@Override
	public String getName() {
		return "XSurvive Global Loot Modifiers";
	}

}