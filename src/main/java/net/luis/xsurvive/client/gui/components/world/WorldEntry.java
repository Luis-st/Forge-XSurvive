package net.luis.xsurvive.client.gui.components.world;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.network.chat.Component;

public class WorldEntry extends ObjectSelectionList.Entry<WorldEntry> {
	
	protected final WorldSelectionList worldList;
	protected final String world;
	
	public WorldEntry(WorldSelectionList worldList, String world) {
		this.worldList = worldList;
		this.world = world;
	}
	
	public String getWorld() {
		return this.world;
	}
	
	@Override
	public Component getNarration() {
		return Component.literal(this.world);
	}

	@Override
	@SuppressWarnings("resource")
	public void render(PoseStack stack, int itemCount, int rowTop, int rowLeft, int rowWidth, int p_93528_, int x, int y, boolean hovered, float partialTicks) {
		GuiComponent.drawString(stack, this.worldList.screen.getMinecraft().font, this.world, rowLeft + 5, rowTop + 6, 16777215);
	}
	
	@Override
	public boolean mouseClicked(double x, double y, int index) {
		if (index == 0) {
			this.worldList.setSelected(this);
			return true;
		} else {
			return false;
		}
	}
	
}
