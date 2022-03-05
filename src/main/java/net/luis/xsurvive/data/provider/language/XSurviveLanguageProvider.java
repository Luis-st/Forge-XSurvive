package net.luis.xsurvive.data.provider.language;

import net.luis.xsurvive.XSurvive;
import net.luis.xsurvive.init.XSurviveEnchantments;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;

public class XSurviveLanguageProvider extends LanguageProvider {

	public XSurviveLanguageProvider(DataGenerator generator) {
		super(generator, XSurvive.MOD_ID, "en_us");
	}

	@Override
	protected void addTranslations() {
		for (Enchantment enchantment : XSurviveEnchantments.ENCHANTMENTS.getEntries().stream().map(RegistryObject::get).toList()) {
			this.add(enchantment, getName(enchantment.getRegistryName()));
		}
	}
	
	protected String getName(ResourceLocation location) { 
		String[] nameParts = location.getPath().split("_");
		String name = "";
		for (String namePart : nameParts) {
			String startChar = namePart.substring(0, 1).toUpperCase();
			name += startChar + namePart.substring(1, namePart.length()) + " ";
		}
		return name.trim();
	}
	
	@Override
	public String getName() {
		return "XSurvive Languages";
	}

}

