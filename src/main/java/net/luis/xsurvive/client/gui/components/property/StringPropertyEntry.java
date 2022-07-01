package net.luis.xsurvive.client.gui.components.property;

import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;

import com.mojang.blaze3d.vertex.PoseStack;

import net.luis.xsurvive.client.gui.screens.CreateServerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;

public class StringPropertyEntry extends PropertyEntry {
	
	public static final Function<String, Boolean> TRUE_VALIDOR = (i) -> {
		return true;
	};
	public static final Function<String, Boolean> FALSE_VALIDOR = (i) -> {
		return false;
	};
	
	protected final EditBox input;
	
	public StringPropertyEntry(CreateServerScreen screen, String key, Component label, String defaultValue) {
		this(screen, key, label, defaultValue, TRUE_VALIDOR);
	}
	
	@SuppressWarnings("resource")
	public StringPropertyEntry(CreateServerScreen screen, String key, Component label, String defaultValue, Function<String, Boolean> validor) {
		super(screen, key, label);
		this.input = new EditBox(screen.getMinecraft().font, 0, 0, 84, 20, Component.empty());
		this.input.setValue(StringUtils.trimToEmpty(defaultValue));
		this.input.setResponder((value) -> {
			if (validor.apply(value)) {
				this.input.setTextColor(14737632);
				screen.clearInvalid(this);
			} else {
				this.input.setTextColor(16711680);
				screen.markInvalid(this);
			}
		});
		this.children.add(this.input);
	}
	
	@Override
	public String getValue() {
		return this.input.getValue().trim();
	}
	
	@Override
	public void render(PoseStack stack, int itemCount, int rowTop, int rowLeft, int rowWidth, int itemHeight, int x, int y, boolean hovered, float partialTicks) {
		this.renderLabel(stack, rowTop, rowLeft);
		this.input.x = rowLeft + rowWidth - 80;
		this.input.y = rowTop;
		this.input.render(stack, x, y, partialTicks);
	}
	
}
