package net.luis.xsurvive.client.gui.screens;

import java.util.List;
import java.util.Objects;

import com.mojang.blaze3d.vertex.PoseStack;

import net.luis.xsurvive.client.gui.components.world.WorldSelectionList;
import net.luis.xsurvive.server.ServerIntegrationHelper;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

public class SelectServerWorldScreen extends Screen {
	
	public final List<String> worlds;
	protected WorldSelectionList worldList;
	protected Button playButton;
	public String world;
	
	public SelectServerWorldScreen(List<String> worlds) {
		super(Component.translatable("selectWorld.title"));
		this.worlds = worlds;
	}
	
	@Override
	protected void init() {
		super.init();
		this.worldList = new WorldSelectionList(this);
		this.addWidget(this.worldList);
		this.playButton = this.addRenderableWidget(new Button(this.width / 2 - 195 + 6, this.height - 28, 120, 20, Component.translatable("screen.select_server_world.continue"), (button) -> {
			if (this.world == null) {
				this.minecraft.setScreen(new ErrorScreen(Component.translatable("screen.create_server.invalid_selection"), new SelectServerWorldScreen(ServerIntegrationHelper.getWorlds())));
			} else {
				CreateServerScreen.open(this.minecraft, Objects.requireNonNull(this.world));
			}
		}));
		this.addRenderableWidget(new Button(this.width / 2 - 65 + 6, this.height - 28, 120, 20, Component.translatable("screen.select_server_world.create"), (button) -> {
			CreateServerScreen.open(this.minecraft, null);
		}));
		this.addRenderableWidget(new Button(this.width / 2 + 65 + 6, this.height - 28, 120, 20, CommonComponents.GUI_CANCEL, (button) -> {
			this.minecraft.setScreen(new SelectWorldScreen(new TitleScreen()));
		}));
		this.worldList.setSelected(this.worldList.children().stream().filter((entry) -> {
			return Objects.equals(this.world, entry.getWorld());
		}).findFirst().orElse(null));
	}
	
	public void updateButtonValidity() {
		this.playButton.active = this.world != null;
	}
	
	@Override
	public void render(PoseStack stack, int x, int y, float partialTicks) {
		this.renderDirtBackground(0);
		this.worldList.render(stack, x, y, partialTicks);
		drawCenteredString(stack, this.font, this.title, this.width / 2, 20, 16777215);
		super.render(stack, x, y, partialTicks);
	}
	
}
