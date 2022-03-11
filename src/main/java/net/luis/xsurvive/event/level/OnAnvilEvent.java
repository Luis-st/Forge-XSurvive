package net.luis.xsurvive.event.level;

import net.luis.xsurvive.XSurvive;
import net.luis.xsurvive.common.enchantment.EnchantedItem;
import net.luis.xsurvive.common.extension.IEnchantment;
import net.luis.xsurvive.common.item.EnchantedGoldenBookItem;
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
		if (left.isEnchantable() || left.isEnchanted()) {
			if (right.getItem() instanceof EnchantedGoldenBookItem goldenBook) {
				Enchantment enchantment = goldenBook.getEnchantment(right);
				if (enchantment != null && enchantment instanceof IEnchantment ench) {
					event.setCanceled(false);
					EnchantedItem enchantedItem = IEnchantment.merge(left, right);
					event.setOutput(enchantedItem.stack());
					event.setCost(enchantedItem.cost());
				} else {
					XSurvive.LOGGER.error("Enchantment {} is not a instance of IEnchantment", enchantment.getRegistryName());
				}
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
