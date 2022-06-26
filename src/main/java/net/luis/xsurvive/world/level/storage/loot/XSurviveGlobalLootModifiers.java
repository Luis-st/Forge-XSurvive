package net.luis.xsurvive.world.level.storage.loot;

import net.luis.xsurvive.XSurvive;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class XSurviveGlobalLootModifiers {
	
	public static final DeferredRegister<GlobalLootModifierSerializer<?>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, XSurvive.MOD_ID);
	
	
	public static final RegistryObject<MultiDropModifier.Serializer> MULTI_DROP_MODIFIER = LOOT_MODIFIERS.register("multi_drop_modifier", () -> {
		return new MultiDropModifier.Serializer();
	});
	public static final RegistryObject<RuneItemModifier.Serializer> RUNE_ITEM_MODIFIER = LOOT_MODIFIERS.register("rune_item_modifier", () -> {
		return new RuneItemModifier.Serializer();
	});
	public static final RegistryObject<GoldenBookModifier.Serializer> GOLDEN_BOOK_MODIFIER = LOOT_MODIFIERS.register("golden_book_modifier", () -> {
		return new GoldenBookModifier.Serializer();
	});
	
}
