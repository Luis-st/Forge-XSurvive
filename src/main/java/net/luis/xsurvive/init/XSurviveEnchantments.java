package net.luis.xsurvive.init;

import net.luis.xsurvive.XSurvive;
import net.luis.xsurvive.common.enchantment.BreakingCurseEnchantment;
import net.luis.xsurvive.common.enchantment.EnderSlayerEnchantment;
import net.luis.xsurvive.common.enchantment.ExperienceEnchantment;
import net.luis.xsurvive.common.enchantment.FrostAspectEnchantment;
import net.luis.xsurvive.common.enchantment.HarmingCurseEnchantment;
import net.luis.xsurvive.common.enchantment.MultiDropEnchantment;
import net.luis.xsurvive.common.enchantment.PoisonAspectEnchantment;
import net.luis.xsurvive.common.enchantment.SmeltingEnchantment;
import net.luis.xsurvive.common.enchantment.VoidWalkerEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantment.Rarity;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class XSurviveEnchantments {
	
	public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, XSurvive.MOD_ID);
	
	
	public static final RegistryObject<MultiDropEnchantment> MULTI_DROP = ENCHANTMENTS.register("multi_drop", () -> {
		return new MultiDropEnchantment(Rarity.VERY_RARE, EnchantmentCategory.DIGGER, EquipmentSlot.MAINHAND);
	});
	public static final RegistryObject<EnderSlayerEnchantment> ENDER_SLAYER = ENCHANTMENTS.register("ender_slayer", () -> {
		return new EnderSlayerEnchantment(Rarity.UNCOMMON, EquipmentSlot.MAINHAND);
	});
	public static final RegistryObject<FrostAspectEnchantment> FROST_ASPECT = ENCHANTMENTS.register("frost_aspect", () -> {
		return new FrostAspectEnchantment(Rarity.RARE, EquipmentSlot.MAINHAND);
	});
	public static final RegistryObject<PoisonAspectEnchantment> POISON_ASPECT = ENCHANTMENTS.register("poison_aspect", () -> {
		return new PoisonAspectEnchantment(Rarity.RARE, EquipmentSlot.MAINHAND);
	});
	public static final RegistryObject<ExperienceEnchantment> EXPERIENCE = ENCHANTMENTS.register("experience", () -> {
		return new ExperienceEnchantment(Rarity.UNCOMMON, XSurviveEnchantmentCategory.TOOLS, EquipmentSlot.MAINHAND);
	});
	public static final RegistryObject<SmeltingEnchantment> SMELTING = ENCHANTMENTS.register("smelting", () -> {
		return new SmeltingEnchantment(Rarity.VERY_RARE, EnchantmentCategory.DIGGER, EquipmentSlot.MAINHAND);
	});
	public static final RegistryObject<BreakingCurseEnchantment> CURSE_OF_BREAKING = ENCHANTMENTS.register("curse_of_breaking", () -> {
		return new BreakingCurseEnchantment(Rarity.VERY_RARE, EnchantmentCategory.BREAKABLE, EquipmentSlot.values());
	});
	public static final RegistryObject<HarmingCurseEnchantment> CURSE_OF_HARMING = ENCHANTMENTS.register("curse_of_harming", () -> {
		return new HarmingCurseEnchantment(Rarity.VERY_RARE, XSurviveEnchantmentCategory.WEAPONS, EquipmentSlot.MAINHAND);
	});
	public static final RegistryObject<VoidWalkerEnchantment> VOID_WALKER = ENCHANTMENTS.register("void_walker", () -> {
		return new VoidWalkerEnchantment(Rarity.VERY_RARE, EnchantmentCategory.ARMOR_FEET, EquipmentSlot.FEET);
	});
	
}
