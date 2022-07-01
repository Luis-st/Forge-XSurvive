package net.luis.xsurvive.client.gui.components.property;

import com.mojang.blaze3d.vertex.PoseStack;

import net.luis.xsurvive.client.gui.screens.CreateServerScreen;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.network.chat.Component;

public class BooleanPropertyEntry extends PropertyEntry {
	
	protected final CycleButton<Boolean> input;
	
	public BooleanPropertyEntry(CreateServerScreen screen, String key, Component label, boolean defaultValue) {
		super(screen, key, label);
		this.input = CycleButton.onOffBuilder(true).withInitialValue(defaultValue).displayOnlyValue().create(0, 0, 86, 20, Component.empty());
		this.children.add(this.input);
	}
	
	@Override
	public String getValue() {
		return input.getValue().toString().toLowerCase();
	}
	
	@Override
	public void render(PoseStack stack, int itemCount, int rowTop, int rowLeft, int rowWidth, int itemHeight, int x, int y, boolean hovered, float partialTicks) {
		this.renderLabel(stack, rowTop, rowLeft);
		this.input.x = rowLeft + rowWidth - 81;
		this.input.y = rowTop;
		this.input.render(stack, x, y, partialTicks);
	}
	
}