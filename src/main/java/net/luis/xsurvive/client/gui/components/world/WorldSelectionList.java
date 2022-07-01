package net.luis.xsurvive.client.gui.components.world;

import net.luis.xsurvive.client.gui.screens.SelectServerWorldScreen;
import net.minecraft.client.gui.components.ObjectSelectionList;

public class WorldSelectionList extends ObjectSelectionList<WorldEntry> {
	
	protected final SelectServerWorldScreen screen;
	
	public WorldSelectionList(SelectServerWorldScreen screen) {
		super(screen.getMinecraft(), screen.width, screen.height, 43, screen.height - 32, 24);
		this.screen = screen;
		this.init();
	}
	
	protected void init() {
		for (String world : this.screen.worlds) {
			this.addEntry(new WorldEntry(this, world));
		}
	}
	
	@Override
	protected boolean isFocused() {
		return this.screen.getFocused() == this;
	}
	
	@Override
	public void setSelected(WorldEntry entry) {
		super.setSelected(entry);
		if (entry != null) {
			this.screen.world = entry.getWorld();
		}
		this.screen.updateButtonValidity();
	}
	
}
