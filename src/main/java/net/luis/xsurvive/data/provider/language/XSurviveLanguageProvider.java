package net.luis.xsurvive.data.provider.language;

import net.luis.xsurvive.XSurvive;
import net.luis.xsurvive.world.effect.XSurviveMobEffects;
import net.luis.xsurvive.world.item.XSurviveItems;
import net.luis.xsurvive.world.item.alchemy.XSurvivePotions;
import net.luis.xsurvive.world.item.enchantment.XSurviveEnchantments;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class XSurviveLanguageProvider extends LanguageProvider {

	public XSurviveLanguageProvider(DataGenerator generator) {
		super(generator, XSurvive.MOD_ID, "en_us");
	}

	@Override
	protected void addTranslations() {
		for (Enchantment enchantment : XSurviveEnchantments.ENCHANTMENTS.getEntries().stream().map(RegistryObject::get).toList()) {
			this.add(enchantment, this.getEnchantmentName(ForgeRegistries.ENCHANTMENTS.getKey(enchantment)));
		}
		for (Item item : XSurviveItems.ITEMS.getEntries().stream().map(RegistryObject::get).toList()) {
			this.add(item, this.getName(ForgeRegistries.ITEMS.getKey(item)));
		}
		for (MobEffect mobEffect : XSurviveMobEffects.MOB_EFFECTS.getEntries().stream().map(RegistryObject::get).toList()) {
			this.add(mobEffect, this.getName(ForgeRegistries.MOB_EFFECTS.getKey(mobEffect)));
		}
		for (Potion potion : XSurvivePotions.POTIONS.getEntries().stream().map(RegistryObject::get).toList()) {
			this.add(potion);
		}
		this.add(XSurvive.TAB.getDisplayName().getString(), XSurvive.MOD_NAME);
		this.add("death.attack.curse_of_harming", "%1$s die by his own weapon");
		this.add("death.attack.curse_of_harming.player", "%1$s die by his own weapon whilst fighting %2$s");
		this.add("screen.select_world.create", "New World");
		this.add("screen.select_world.start", "Start Server");
		this.add("screen.create_server.invalid_server", "There is no valid server installation for version %1$s");
		this.add("screen.select_server_world.create", "New Server World");
		this.add("screen.select_server_world.continue", "Continue");
		this.add("screen.create_server.invalid_selection", "No world has been selected");
		
		this.add("screen.create_server.world_name", "World Name");
		this.add("screen.create_server.world_seed", "Seed");
		this.add("screen.create_server.world_type", "World Type");
		this.add("screen.create_server.gamemode", "Gamemode");
		this.add("screen.create_server.difficulty", "Difficulty");
		this.add("screen.create_server.hardcore", "Hardcore");
		this.add("screen.create_server.pvp", "Enable PvP");
		this.add("screen.create_server.spawn_animals", "Spawn Animals");		
		this.add("screen.create_server.spawn_npcs", "Spawn NPCS");
		this.add("screen.create_server.spawn_monsters", "Spawn Monsters");
		this.add("screen.create_server.generate_structures", "Generate Structures");
		this.add("screen.create_server.allow_nether", "Allow Nether");
		this.add("screen.create_server.view_distance", "View Distance");
		this.add("screen.create_server.simulation_distance", "Simulation Distance");
		this.add("screen.create_server.max_players", "Max Players");		
		this.add("screen.create_server.white_list", "Enable Whitelist");
		this.add("screen.create_server.enable_command_block", "Enable Command Block");
	}
	
	public void add(Potion potion) {
		ResourceLocation location = ForgeRegistries.POTIONS.getKey(potion);
		String potionName = location.getPath();
		this.add("item.minecraft.potion.effect." + potionName, this.getPotionName(location));
		this.add("item.minecraft.splash_potion.effect." + potionName, this.getPotionName(location));
		this.add("item.minecraft.lingering_potion.effect." + potionName, this.getPotionName(location));
		this.add("item.minecraft.tipped_arrow.effect." + potionName, "Arrow of " + this.getPotionName(location));
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
	
	protected String getEnchantmentName(ResourceLocation location) {
		String name = this.getName(location);
		if (name.contains(" Of ")) {
			return name.replace(" Of ", " of ");
		}
		return name;
	}
	
	protected String getPotionName(ResourceLocation location) {
		if (location.getPath().startsWith("long_")) {
			String path = location.getPath();
			return this.getName(new ResourceLocation(location.getNamespace(), path.replace("long_", "")));
		} else if (location.getPath().startsWith("strong_")) {
			String path = location.getPath();
			return this.getName(new ResourceLocation(location.getNamespace(), path.replace("strong_", "")));
		}
		return this.getName(location);
	}
	
	@Override
	public String getName() {
		return "XSurvive Languages";
	}

}

