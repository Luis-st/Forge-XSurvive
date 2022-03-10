package net.luis.xsurvive.common;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class XSurviveCreativeModeTab extends CreativeModeTab {

	public XSurviveCreativeModeTab(String label) {
		super(label);
	}

	@Override
	public ItemStack makeIcon() {
		return ItemStack.EMPTY;
	}

}
