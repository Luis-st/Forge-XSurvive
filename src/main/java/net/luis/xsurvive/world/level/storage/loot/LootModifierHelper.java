package net.luis.xsurvive.world.level.storage.loot;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import net.luis.xsurvive.world.item.enchantment.XSurviveEnchantments;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

public class LootModifierHelper {
	
	public static List<Enchantment> getTrashEnchantments() {
		return ImmutableList.of(Enchantments.FIRE_PROTECTION, Enchantments.BLAST_PROTECTION, Enchantments.PROJECTILE_PROTECTION, Enchantments.KNOCKBACK);
	}
	
	public static List<Enchantment> getEnchantments() {
		return ImmutableList.of(Enchantments.SHARPNESS, Enchantments.ALL_DAMAGE_PROTECTION, Enchantments.BLOCK_EFFICIENCY, Enchantments.UNBREAKING, Enchantments.PIERCING, Enchantments.SMITE, Enchantments.PUNCH_ARROWS, Enchantments.POWER_ARROWS,
			Enchantments.FISHING_SPEED, Enchantments.BANE_OF_ARTHROPODS, XSurviveEnchantments.BLASTING.get(), XSurviveEnchantments.GROWTH.get(), XSurviveEnchantments.ENDER_SLAYER.get());
	}
	
	public static List<Enchantment> getRareEnchantments() {
		return ImmutableList.of(Enchantments.FALL_PROTECTION, Enchantments.RESPIRATION, Enchantments.DEPTH_STRIDER, Enchantments.SWEEPING_EDGE, Enchantments.FISHING_LUCK, Enchantments.FIRE_ASPECT, Enchantments.QUICK_CHARGE, 
			XSurviveEnchantments.FROST_ASPECT.get(), XSurviveEnchantments.POISON_ASPECT.get());
	}
	
	public static List<Enchantment> getVeryRareEnchantments() {
		return ImmutableList.of(Enchantments.BLOCK_FORTUNE, Enchantments.MOB_LOOTING, Enchantments.LOYALTY, Enchantments.RIPTIDE);
	}
	
	public static List<Enchantment> getEndVeryRareEnchantments() {
		return ImmutableList.<Enchantment>builder().addAll(getVeryRareEnchantments()).add(XSurviveEnchantments.VOID_PROTECTION.get()).build();
	}
	
	public static List<Enchantment> getTreasureEnchantments() {
		return Lists.newArrayList(Enchantments.SWIFT_SNEAK, XSurviveEnchantments.MULTI_DROP.get());
	}
	
	public static List<Enchantment> getNetherTreasureEnchantments() {
		return ImmutableList.<Enchantment>builder().addAll(getTreasureEnchantments()).add(Enchantments.SOUL_SPEED).build();
	}
	
}
