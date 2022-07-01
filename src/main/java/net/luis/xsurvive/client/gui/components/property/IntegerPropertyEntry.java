package net.luis.xsurvive.client.gui.components.property;

import java.util.function.Function;

import com.mojang.blaze3d.vertex.PoseStack;

import net.luis.xsurvive.client.gui.screens.CreateServerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;

public class IntegerPropertyEntry extends PropertyEntry {
	
	public static final Function<Integer, Boolean> TRUE_VALIDOR = (i) -> {
		return true;
	};
	public static final Function<Integer, Boolean> FALSE_VALIDOR = (i) -> {
		return false;
	};
	
	protected final EditBox input;
	
	public IntegerPropertyEntry(CreateServerScreen screen, String key, Component label, int defaultValue) {
		this(screen, key, label, defaultValue, TRUE_VALIDOR);
	}
	
	@SuppressWarnings("resource")
	public IntegerPropertyEntry(CreateServerScreen screen, String key, Component label, Integer defaultValue, Function<Integer, Boolean> validor) {
		super(screen, key, label);
		this.input = new EditBox(screen.getMinecraft().font, 0, 0, 84, 20, Component.empty());
		this.input.setValue(defaultValue == null ? "" : defaultValue.toString());
		this.input.setResponder((value) -> {
			Integer integer = this.tryParse(value);
			if (integer != null && validor.apply(integer)) {
				this.input.setTextColor(14737632);
				screen.clearInvalid(this);
			} else if (integer == null && defaultValue == null && validor.apply(0)) {
				this.input.setTextColor(14737632);
				screen.clearInvalid(this);
			} else {
				this.input.setTextColor(16711680);
				screen.markInvalid(this);
			}
		});
		this.children.add(this.input);
	}
	
	protected Integer tryParse(String value) {
		try {
			return Integer.valueOf(value);
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public String getValue() {
		return this.input.getValue().toLowerCase().trim();
	}

	@Override
	public void render(PoseStack stack, int itemCount, int rowTop, int rowLeft, int rowWidth, int itemHeight, int x, int y, boolean hovered, float partialTicks) {
		this.renderLabel(stack, rowTop, rowLeft);
		this.input.x = rowLeft + rowWidth - 80;
		this.input.y = rowTop;
		this.input.render(stack, x, y, partialTicks);
	}
	
}
