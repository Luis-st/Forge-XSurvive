package net.luis.xsurvive.world.inventory;

import net.luis.xsurvive.world.item.crafting.XSurviveRecipeTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;

public class SmeltingFurnaceMenu extends AbstractFurnaceMenu {

	public SmeltingFurnaceMenu(int id, Inventory inventory, FriendlyByteBuf data) {
		super(XSurviveMenuTypes.SMELTING_FURNACE.get(), XSurviveRecipeTypes.SMELTING.get(), XSurviveRecipeBookTypes.SMELTING, id, inventory);
	}
	
	public SmeltingFurnaceMenu(int id, Inventory inventory, Container container, ContainerData data) {
		super(XSurviveMenuTypes.SMELTING_FURNACE.get(), XSurviveRecipeTypes.SMELTING.get(), XSurviveRecipeBookTypes.SMELTING, id, inventory, container, data);
	}

}
