package net.luis.xsurvive.event.entity.living;

import java.util.UUID;

import net.luis.xsurvive.XSurvive;
import net.luis.xsurvive.common.handler.EntityHandler;
import net.luis.xsurvive.init.XSurviveEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = XSurvive.MOD_ID)
public class OnLivingEquipmentChangeEvent {
	
	public static final AttributeModifier GRAVITY_MODIFIER = new AttributeModifier(UUID.fromString("715AF01B-DED3-45ED-8812-C8878C7F98CC"), XSurvive.MOD_NAME + "EntityGravity", -1.0, AttributeModifier.Operation.MULTIPLY_TOTAL);
	public static final UUID HEALTH_MODIFIER_UUID = UUID.fromString("CD14D16D-82ED-474E-97E9-403DE2439D01");
	
	@SubscribeEvent
	public static void livingEquipmentChange(LivingEquipmentChangeEvent event) {
		if (event.getEntityLiving() instanceof Player player && event.getSlot().getType() != EquipmentSlot.Type.HAND) {
			ItemStack toStack = event.getTo();
			ItemStack fromStack = event.getFrom();
			if (event.getSlot() == EquipmentSlot.FEET) {
				int voidWalkerTo = toStack.getEnchantmentLevel(XSurviveEnchantments.VOID_WALKER.get());
				int voidWalkerFrom = fromStack.getEnchantmentLevel(XSurviveEnchantments.VOID_WALKER.get());
				AttributeInstance gravityInstance = player.getAttribute(ForgeMod.ENTITY_GRAVITY.get());
				if (voidWalkerTo > 0 && voidWalkerFrom > 0 && !gravityInstance.hasModifier(GRAVITY_MODIFIER)) {
					gravityInstance.addTransientModifier(GRAVITY_MODIFIER);
				} else if (0 >= voidWalkerTo && voidWalkerFrom > 0 && gravityInstance.hasModifier(GRAVITY_MODIFIER)) {
					gravityInstance.removeModifier(GRAVITY_MODIFIER);
				} else if (voidWalkerTo > 0 && 0 >= voidWalkerFrom && !gravityInstance.hasModifier(GRAVITY_MODIFIER)) {
					gravityInstance.addTransientModifier(GRAVITY_MODIFIER);
				} else {
					if (gravityInstance.hasModifier(GRAVITY_MODIFIER)) {
						gravityInstance.removeModifier(GRAVITY_MODIFIER);
					}
				}
			}
			int growthTo = EntityHandler.getGrowthLevel(player, event.getSlot(), toStack);
			int growthFrom = EntityHandler.getGrowthLevel(player, event.getSlot(), fromStack);
			AttributeInstance healthInstance = player.getAttribute(Attributes.MAX_HEALTH);
			AttributeModifier healthModifier = new AttributeModifier(HEALTH_MODIFIER_UUID, XSurvive.MOD_NAME + "MaxHealth", (double) growthTo, AttributeModifier.Operation.ADDITION);
			boolean hasModifier = healthInstance.getModifier(HEALTH_MODIFIER_UUID) != null;
			if (growthTo == growthFrom && !hasModifier) {
				healthInstance.addTransientModifier(healthModifier);
			} else if (growthTo != growthFrom) {
				if (hasModifier) {
					healthInstance.removeModifier(HEALTH_MODIFIER_UUID);
					healthInstance.addTransientModifier(healthModifier);
				} else {
					healthInstance.addTransientModifier(healthModifier);
				}
				player.setHealth(Math.min(player.getHealth(), (float) healthInstance.getValue()));
			}
		}
	}
	
}