package net.luis.xsurvive.mixin.client;

import java.util.List;
import java.util.function.Supplier;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import net.luis.xsurvive.client.gui.screens.CreateServerScreen;
import net.luis.xsurvive.client.gui.screens.SelectServerWorldScreen;
import net.luis.xsurvive.server.ServerIntegrationHelper;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.client.gui.screens.worldselection.WorldSelectionList;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

@Mixin(SelectWorldScreen.class)
public abstract class SelectWorldScreenMixin extends Screen {
	
	@Shadow
	@Final
	protected Screen lastScreen;
	@Shadow
	protected EditBox searchBox;
	@Shadow
	private WorldSelectionList list;
	@Shadow
	private Button selectButton;
	@Shadow
	private Button renameButton;
	@Shadow
	private Button deleteButton;
	@Shadow
	private Button copyButton;
	
	private SelectWorldScreenMixin(Component component) {
		super(component);
	}
	
	@Shadow
	public abstract void updateButtonStatus(boolean active);
	
	@Shadow
	public abstract Supplier<String> getFilterSupplier();
	
	@Overwrite
	protected void init() {
		this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
		this.searchBox = new EditBox(this.font, this.width / 2 - 100, 22, 200, 20, this.searchBox, Component.translatable("selectWorld.search"));
		this.searchBox.setResponder((p_232980_) -> {
			this.list.refreshList(p_232980_);
		});
		this.list = new WorldSelectionList((SelectWorldScreen) (Object) this, this.minecraft, this.width, this.height, 48, this.height - 64, 36, this.getFilterSupplier(), this.list);
		this.addWidget(this.searchBox);
		this.addWidget(this.list);
		this.selectButton = this.addRenderableWidget(new Button(this.width / 2 - 154, this.height - 52, 150, 20, Component.translatable("selectWorld.select"), (button) -> {
			this.list.getSelectedOpt().ifPresent(WorldSelectionList.WorldListEntry::joinWorld);
		}));
		this.addRenderableWidget(new Button(this.width / 2 + 4, this.height - 52, 72, 20, Component.translatable("screen.select_world.create"), (button) -> {
			CreateWorldScreen.openFresh(this.minecraft, this);
		}));
		this.addRenderableWidget(new Button(this.width / 2 + 82, this.height - 52, 72, 20, Component.translatable("screen.select_world.start"), (button) -> {
			List<String> worlds = ServerIntegrationHelper.getWorlds();
			if (worlds.isEmpty()) {
				CreateServerScreen.open(this.minecraft, null);
			} else {
				this.minecraft.setScreen(new SelectServerWorldScreen(worlds));
			}
		}));
		this.renameButton = this.addRenderableWidget(new Button(this.width / 2 - 154, this.height - 28, 72, 20, Component.translatable("selectWorld.edit"), (button) -> {
			this.list.getSelectedOpt().ifPresent(WorldSelectionList.WorldListEntry::editWorld);
		}));
		this.deleteButton = this.addRenderableWidget(new Button(this.width / 2 - 76, this.height - 28, 72, 20, Component.translatable("selectWorld.delete"), (button) -> {
			this.list.getSelectedOpt().ifPresent(WorldSelectionList.WorldListEntry::deleteWorld);
		}));
		this.copyButton = this.addRenderableWidget(new Button(this.width / 2 + 4, this.height - 28, 72, 20, Component.translatable("selectWorld.recreate"), (button) -> {
			this.list.getSelectedOpt().ifPresent(WorldSelectionList.WorldListEntry::recreateWorld);
		}));
		this.addRenderableWidget(new Button(this.width / 2 + 82, this.height - 28, 72, 20, CommonComponents.GUI_CANCEL, (button) -> {
			this.minecraft.setScreen(this.lastScreen);
		}));
		this.updateButtonStatus(false);
		this.setInitialFocus(this.searchBox);
	}
	
}
