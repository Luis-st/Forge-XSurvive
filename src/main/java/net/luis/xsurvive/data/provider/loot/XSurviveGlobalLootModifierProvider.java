package net.luis.xsurvive.data.provider.loot;

import net.luis.xsurvive.XSurvive;
import net.luis.xsurvive.common.loot.MultiDropModifier;
import net.luis.xsurvive.init.XSurviveGlobalLootModifiers;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

public class XSurviveGlobalLootModifierProvider extends GlobalLootModifierProvider {

	public XSurviveGlobalLootModifierProvider(DataGenerator generator) {
		super(generator, XSurvive.MOD_ID);
	}

	@Override
	protected void start() {
		this.add("multi_drop", XSurviveGlobalLootModifiers.MULTI_DROP_MODIFIER.get(), new MultiDropModifier(new LootItemCondition[] {
				new MatchTool(ItemPredicate.ANY)
		}));
	}
	
	@Override
	public String getName() {
		return "XSurvive Global Loot Modifiers";
	}

}