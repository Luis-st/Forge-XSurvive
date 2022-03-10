package net.luis.xsurvive.init;

import net.luis.xsurvive.XSurvive;
import net.luis.xsurvive.common.item.EnchantedGoldenBookItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class XSurviveItems {
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, XSurvive.MOD_ID);
	
	
	public static final RegistryObject<EnchantedGoldenBookItem> ENCHANTED_GOLDEN_BOOK = ITEMS.register("enchanted_golden_book", () -> {
		return new EnchantedGoldenBookItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).fireResistant());
	});
	
}
