package net.luis.xsurvive.client.gui.components.property;

import java.util.List;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.vertex.PoseStack;

import net.luis.xsurvive.client.gui.screens.CreateServerScreen;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;

public abstract class PropertyEntry extends ContainerObjectSelectionList.Entry<PropertyEntry> {
	
	protected final CreateServerScreen screen;
	protected final String key;
	protected final List<FormattedCharSequence> label;
	protected final List<AbstractWidget> children;
	
	@SuppressWarnings("resource")
	public PropertyEntry(CreateServerScreen screen, String key, Component label) {
		this.screen = screen;
		this.key = key;
		this.label = screen.getMinecraft().font.split(label, 175);
		this.children = Lists.newArrayList();
	}
	
	public String getKey() {
		return this.key;
	}
	
	public abstract String getValue();
	
	@Override
	public List<? extends GuiEventListener> children() {
		return this.children;
	}

	@Override
	public List<? extends NarratableEntry> narratables() {
		return this.children;
	}
	
	@Override
	public abstract void render(PoseStack stack, int itemCount, int rowTop, int rowLeft, int rowWidth, int itemHeight, int x, int y, boolean hovered, float partialTicks);
	
	@SuppressWarnings("resource")
	protected void renderLabel(PoseStack stack, int rowTop, int rowLeft) {
		if (this.label.size() == 1) {
			screen.getMinecraft().font.draw(stack, this.label.get(0), rowLeft, rowTop + 5, 16777215);
		} else if (this.label.size() >= 2) {
			screen.getMinecraft().font.draw(stack, this.label.get(0), rowLeft, rowTop, 16777215);
			screen.getMinecraft().font.draw(stack, this.label.get(1), rowLeft, rowTop + 10, 16777215);
		}
	}
	
}
