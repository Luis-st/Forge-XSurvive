package net.luis.xsurvive.client.gui.screens;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

public class ErrorScreen extends Screen {
	
	protected final Screen exitScreen;
	
	public ErrorScreen(Component component) {
		this(component, new TitleScreen());
	}
	
	public ErrorScreen(Component component, Screen exitScreen) {
		super(component);
		this.exitScreen = exitScreen;
	}
	
	@Override
	protected void init() {
		super.init();
		this.addRenderableWidget(new Button(this.width / 2 - 100, 140, 200, 20, CommonComponents.GUI_CANCEL, (button) -> {
			this.minecraft.setScreen(this.exitScreen);
		}));
	}
	
	@Override
	public void render(PoseStack stack, int x, int y, float partialTicks) {
		this.renderDirtBackground(0);
		drawCenteredString(stack, this.font, this.title, this.width / 2, 70, 16777215);
		super.render(stack, x, y, partialTicks);
	}

}
