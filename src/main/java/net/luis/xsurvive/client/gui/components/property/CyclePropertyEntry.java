package net.luis.xsurvive.client.gui.components.property;

import java.util.List;

import com.mojang.blaze3d.vertex.PoseStack;

import net.luis.xsurvive.client.gui.screens.CreateServerScreen;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.network.chat.Component;

public class CyclePropertyEntry extends PropertyEntry {
	
	protected final CycleButton<Component> input;
	
	public CyclePropertyEntry(CreateServerScreen screen, String key, Component label, Component defaultValue, List<Component> values) {
		super(screen, key, label);
		this.input = CycleButton.<Component>builder((value) -> {
			return value;
		}).withValues(values).withInitialValue(defaultValue).displayOnlyValue().create(0, 0, 86, 20, Component.empty());
		this.children.add(this.input);
	}
	
	@Override
	public String getValue() {
		return this.input.getValue().getString().toLowerCase().replace(" ", "_");
	}
	
	@Override
	public void render(PoseStack stack, int itemCount, int rowTop, int rowLeft, int rowWidth, int itemHeight, int x, int y, boolean hovered, float partialTicks) {
		this.renderLabel(stack, rowTop, rowLeft);
		this.input.x = rowLeft + rowWidth - 81;
		this.input.y = rowTop;
		this.input.render(stack, x, y, partialTicks);
	}
	
}
