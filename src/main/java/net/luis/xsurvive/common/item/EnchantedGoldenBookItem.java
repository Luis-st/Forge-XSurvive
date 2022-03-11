package net.luis.xsurvive.common.item;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import net.luis.xsurvive.XSurvive;
import net.luis.xsurvive.common.extension.IEnchantment;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

public class EnchantedGoldenBookItem extends Item {

	public EnchantedGoldenBookItem(Properties properties) {
		super(properties);
	}
	
	@Override
	public boolean isFoil(ItemStack stack) {
		return true;
	}
	
	@Override
	public boolean isEnchantable(ItemStack stack) {
		return true;
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Level level, List<Component> components, TooltipFlag tooltip) {
		super.appendHoverText(stack, level, components, tooltip);
		Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
		if (!enchantments.isEmpty()) {
			Enchantment enchantment = this.getEnchantment(stack);
			if (enchantment != null) {
				TranslatableComponent component = new TranslatableComponent(enchantment.getDescriptionId());
				component.withStyle(ChatFormatting.DARK_PURPLE);
				components.add(component);
			}
		}
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> stacks) {
		if (tab == XSurvive.TAB || tab == CreativeModeTab.TAB_SEARCH) {
			for (Enchantment enchantment : Registry.ENCHANTMENT.stream().toList()) {
				if (enchantment instanceof IEnchantment ench) {
					if (ench.isAllowedOnGoldenBooks()) {
						ItemStack stack = new ItemStack(this);
						this.setEnchantment(stack, enchantment);
						stacks.add(stack);
					}
				} else {
					XSurvive.LOGGER.error("Enchantment {} is not a instance of IEnchantment", enchantment.getRegistryName());
				}
			}
		}
	}
	
	@Nullable
	public Enchantment getEnchantment(ItemStack stack) {
		if (stack.getItem() instanceof EnchantedGoldenBookItem) {
			Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
			if (!enchantments.isEmpty() && enchantments.size() == 1) {
				return enchantments.keySet().stream().toList().get(0);
			}
		}
		return null;
	}
	
	public void setEnchantment(ItemStack stack, Enchantment enchantment) {
		if (stack.getItem() instanceof EnchantedGoldenBookItem) {
			EnchantmentHelper.setEnchantments(Map.of(enchantment, 1), stack);
		}
	}

}
