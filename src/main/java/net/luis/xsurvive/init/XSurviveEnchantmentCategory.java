package net.luis.xsurvive.init;

import net.luis.xsurvive.XSurvive;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public enum XSurviveEnchantmentCategory {
	
	TOOLS(new ResourceLocation(XSurvive.MOD_ID, "tools")) {
		@Override
		protected boolean canEnchant(Item item) {
			return item instanceof DiggerItem || item instanceof SwordItem || item instanceof BowItem || item instanceof CrossbowItem;
		}
	};
	
	private final ResourceLocation location;
	
	private XSurviveEnchantmentCategory(ResourceLocation location) {
		this.location = location;
	}
	
	protected abstract boolean canEnchant(Item item);
	
	public ResourceLocation getLocation() {
		return this.location;
	}
	
	public EnchantmentCategory toCategory() {
		return EnchantmentCategory.create(this.location.toString(), this::canEnchant);
	}
	
}
