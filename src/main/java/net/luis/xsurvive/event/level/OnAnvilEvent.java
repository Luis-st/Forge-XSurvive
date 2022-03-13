package net.luis.xsurvive.event.level;

import net.luis.xsurvive.XSurvive;
import net.luis.xsurvive.common.enchantment.EnchantedItem;
import net.luis.xsurvive.common.extension.IEnchantment;
import net.luis.xsurvive.common.item.EnchantedGoldenBookItem;
import net.luis.xsurvive.common.item.RuneItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.player.AnvilRepairEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = XSurvive.MOD_ID)
public class OnAnvilEvent {
	
	@SubscribeEvent
	public static void anvilUpdate(AnvilUpdateEvent event) {
		ItemStack left = event.getLeft();
		ItemStack right = event.getRight();
		XSurvive.LOGGER.info("isEnchanted {}", left.isEnchanted());
		XSurvive.LOGGER.info("instanceof {}", right.getItem() instanceof RuneItem);
		XSurvive.LOGGER.info("all {}", left.isEnchantable() && right.getItem() instanceof RuneItem rune);
		if ((left.isEnchantable() || left.isEnchanted()) && right.getItem() instanceof EnchantedGoldenBookItem goldenBook) {
			Enchantment enchantment = goldenBook.getEnchantment(right);
			if (enchantment != null && enchantment instanceof IEnchantment ench) {
				event.setCanceled(false);
				EnchantedItem enchantedItem = IEnchantment.merge(left, right);
				event.setOutput(enchantedItem.stack());
				event.setCost(enchantedItem.cost());
			} else {
				XSurvive.LOGGER.error("Enchantment {} is not a instance of IEnchantment", enchantment.getRegistryName());
			}
		} else if (left.isEnchanted() && right.getItem() instanceof RuneItem rune) {
			event.setCanceled(false);
			ItemStack result = left.copy();
			int color = rune.getRuneColor(right);
			if (17 >= color && color >= 0) {
				CompoundTag tag = result.getOrCreateTag();
				if (tag.contains(XSurvive.MOD_NAME)) {
					CompoundTag modTag = tag.getCompound(XSurvive.MOD_NAME);
					tag.remove(XSurvive.MOD_NAME);
					modTag.putInt(XSurvive.MOD_NAME + "ItemColor", color);
					tag.put(XSurvive.MOD_NAME, modTag);
				} else {
					CompoundTag modTag = new CompoundTag();
					modTag.putInt(XSurvive.MOD_NAME + "ItemColor", color);
					tag.put(XSurvive.MOD_NAME, modTag);
				}
				result.setTag(tag);
				event.setOutput(result);
				event.setCost(10);
			}
		}
	}
	
	@SubscribeEvent
	public static void anvilRepair(AnvilRepairEvent event) {
		if (event.getIngredientInput().getItem() instanceof EnchantedGoldenBookItem) {
			event.setBreakChance(0.0F);
		} else {
			event.setBreakChance(0.08F);
		}
	}
	
}
