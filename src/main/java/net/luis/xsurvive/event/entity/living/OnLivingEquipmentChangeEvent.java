package net.luis.xsurvive.event.entity.living;

import net.luis.xsurvive.XSurvive;
import net.luis.xsurvive.init.XSurviveEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = XSurvive.MOD_ID)
public class OnLivingEquipmentChangeEvent {
	
	public static final AttributeModifier MODIFIER = new AttributeModifier("715AF01B-DED3-45ED-8812-C8878C7F98CC", 0.0, AttributeModifier.Operation.MULTIPLY_TOTAL);
	
	@SubscribeEvent
	public static void livingEquipmentChange(LivingEquipmentChangeEvent event) {
		if (event.getEntityLiving() instanceof Player player && event.getSlot() == EquipmentSlot.FEET) {
			ItemStack fromStack = event.getFrom();
			ItemStack toStack = event.getTo();
			if (fromStack.isEmpty() && toStack.getItem() instanceof ArmorItem) {
				int voidWalker = EnchantmentHelper.getItemEnchantmentLevel(XSurviveEnchantments.VOID_WALKER.get(), toStack);
				if (voidWalker > 0) {
					player.getAttribute(ForgeMod.ENTITY_GRAVITY.get()).addPermanentModifier(MODIFIER);
					XSurvive.LOGGER.info("Add AttributeModifier");
					XSurvive.LOGGER.info("Entity Gravity is now {}", player.getAttributeValue(ForgeMod.ENTITY_GRAVITY.get()));
				}
			} else if (fromStack.getItem() instanceof ArmorItem && toStack.isEmpty()) {
				int voidWalker = EnchantmentHelper.getItemEnchantmentLevel(XSurviveEnchantments.VOID_WALKER.get(), fromStack);
				if (voidWalker > 0) {
					player.getAttribute(ForgeMod.ENTITY_GRAVITY.get()).removeModifier(MODIFIER);
					XSurvive.LOGGER.info("Remove AttributeModifier");
					XSurvive.LOGGER.info("Entity Gravity is now {}", player.getAttributeValue(ForgeMod.ENTITY_GRAVITY.get()));
				}
			} else if (fromStack.getItem() instanceof ArmorItem && toStack.getItem() instanceof ArmorItem) {
				// TODO
			}
		}
	}
	
}
