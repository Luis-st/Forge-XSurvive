package net.luis.xsurvive.world.level.storage.loot;

import net.luis.xsurvive.XSurvive;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class XSurviveGlobalLootModifiers {
	
	public static final DeferredRegister<GlobalLootModifierSerializer<?>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, XSurvive.MOD_ID);
	
	
	public static final RegistryObject<MultiDropModifier.Serializer> MULTI_DROP_MODIFIER = LOOT_MODIFIERS.register("multi_drop_modifier", MultiDropModifier.Serializer::new);
	
}
