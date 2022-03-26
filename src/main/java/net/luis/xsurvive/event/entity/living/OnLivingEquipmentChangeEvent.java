package net.luis.xsurvive.event.entity.living;

import net.luis.xsurvive.XSurvive;
import net.luis.xsurvive.init.XSurviveEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = XSurvive.MOD_ID)
public class OnLivingEquipmentChangeEvent {
	
	public static final AttributeModifier MODIFIER = new AttributeModifier("715AF01B-DED3-45ED-8812-C8878C7F98CC", -1.0, AttributeModifier.Operation.MULTIPLY_TOTAL);
	
	@SubscribeEvent
	public static void livingEquipmentChange(LivingEquipmentChangeEvent event) {
		if (event.getEntityLiving() instanceof Player player && event.getSlot() == EquipmentSlot.FEET) {
			ItemStack fromStack = event.getFrom();
			ItemStack toStack = event.getTo();
			int voidWalkerTo = EnchantmentHelper.getItemEnchantmentLevel(XSurviveEnchantments.VOID_WALKER.get(), toStack);
			int voidWalkerFrom = EnchantmentHelper.getItemEnchantmentLevel(XSurviveEnchantments.VOID_WALKER.get(), fromStack);
			AttributeInstance instance = player.getAttribute(ForgeMod.ENTITY_GRAVITY.get());
			if (voidWalkerTo > 0 && voidWalkerFrom > 0 && !instance.hasModifier(MODIFIER)) {
				instance.addTransientModifier(MODIFIER);
			} else if (0 >= voidWalkerTo && voidWalkerFrom > 0 && instance.hasModifier(MODIFIER)) {
				instance.removeModifier(MODIFIER);
			} else if (voidWalkerTo > 0 && 0 >= voidWalkerFrom && !instance.hasModifier(MODIFIER)) {
				instance.addTransientModifier(MODIFIER);
			} else {
				if (instance.hasModifier(MODIFIER)) {
					instance.removeModifier(MODIFIER);
				}
			}
		}
	}
	
}
