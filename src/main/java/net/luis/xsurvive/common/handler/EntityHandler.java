package net.luis.xsurvive.common.handler;

import net.luis.xsurvive.init.XSurviveEnchantments;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.monster.MagmaCube;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.monster.Strider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class EntityHandler {
	
	public static boolean isAffectedByEnderSlayer(Entity entity) {
		return entity instanceof EnderMan || entity instanceof Endermite || entity instanceof Shulker || entity instanceof EnderDragon;
	}
	
	public static boolean isAffectedByImpaling(Entity entity) {
		if (entity instanceof MagmaCube || entity instanceof Ghast || entity instanceof Blaze || entity instanceof Strider) {
			return true;
		}
		return entity instanceof AbstractFish || entity instanceof Dolphin || entity instanceof Squid || entity instanceof Guardian || entity instanceof ElderGuardian || entity instanceof Drowned || entity instanceof Turtle;
	}
	
	public static int getGrowthLevel(LivingEntity entity, EquipmentSlot slot, ItemStack stack) {
		int growth = 0;
		for (EquipmentSlot equipmentSlot : EquipmentSlot.values()) {
			if (equipmentSlot.getType() == EquipmentSlot.Type.ARMOR) {
				if (equipmentSlot == slot) {
					growth += EnchantmentHelper.getItemEnchantmentLevel(XSurviveEnchantments.GROWTH.get(), stack);
				} else {
					growth += EnchantmentHelper.getItemEnchantmentLevel(XSurviveEnchantments.GROWTH.get(), entity.getItemBySlot(equipmentSlot));
				}
			}
		}
		return growth;
	}
	

	
}
