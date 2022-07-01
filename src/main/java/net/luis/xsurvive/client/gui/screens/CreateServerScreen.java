package net.luis.xsurvive.client.gui.screens;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Sets;
import com.mojang.blaze3d.vertex.PoseStack;

import net.luis.xsurvive.XSurvive;
import net.luis.xsurvive.client.gui.components.property.PropertyEntry;
import net.luis.xsurvive.client.gui.components.property.PropertySelectionList;
import net.luis.xsurvive.server.ServerIntegrationHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.dedicated.DedicatedServerProperties;

public class CreateServerScreen extends Screen {
	
	protected final String world;
	protected final Properties properties;
	protected final Set<PropertyEntry> invalidEntries = Sets.newHashSet();
	protected PropertySelectionList propertyList;
	protected Button doneButton;
	
	public CreateServerScreen(String world) {
		super(Component.literal("Server properties"));
		this.world = StringUtils.trimToNull(world);
		this.properties = ServerIntegrationHelper.loadServerProperties(this.world);
	}
	
	public static void open(Minecraft minecraft, @Nullable String world) {
		Path serverJarPath = ServerIntegrationHelper.getServerJarDirectory();
		if (!Files.exists(serverJarPath)) {
			XSurvive.LOGGER.warn("Fail to find forge server jar file, it should be at {}", serverJarPath);
			XSurvive.LOGGER.error("Fail to start server with version {}, since {} does not contains a valid server installation", ServerIntegrationHelper.getVersion(), ServerIntegrationHelper.getServerDirectory());
			minecraft.setScreen(new ErrorScreen(Component.translatable("screen.create_server.invalid_server", ServerIntegrationHelper.getVersion())));
		} else {
			minecraft.setScreen(new CreateServerScreen(world));
		}
	}
	
	protected void initDefaultProperties() {
		this.properties.setProperty("allow-flight", "true");
		this.properties.setProperty("motd", "Minecraft Server created by " + XSurvive.MOD_NAME);
		this.properties.setProperty("spawn-protection", "0");
		this.properties.setProperty("online-mode", "true");
		this.properties.setProperty("generator-settings", "{}");
	}
	
	@Override
	protected void init() {
		this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
		this.propertyList = new PropertySelectionList(this);
		this.addWidget(this.propertyList);
		if (this.world == null) {
			this.propertyList.initNewServer();
		} else {
			this.propertyList.initExistingServer();
		}
		this.doneButton = this.addRenderableWidget(new Button(this.width / 2 - 155, this.height - 29, 150, 20, Component.translatable("screen.select_world.start"), (button) -> {
			this.initDefaultProperties();
			for (PropertyEntry entry : this.propertyList.children()) {
				this.properties.setProperty(entry.getKey(), entry.getValue());
			}
			DedicatedServerProperties serverProperties = new DedicatedServerProperties(this.properties);
			serverProperties.store(ServerIntegrationHelper.getServerDirectory().resolve("server.properties"));
			XSurvive.LOGGER.info("Start"); // -> start server, gen server.properties file, open at the End EditServerScreen -> with localhost and default port
			this.minecraft.setScreen(new TitleScreen());
		}));
		this.addRenderableWidget(new Button(this.width / 2 - 155 + 160, this.height - 29, 150, 20, CommonComponents.GUI_CANCEL, (button) -> {
			this.minecraft.setScreen(new SelectServerWorldScreen(ServerIntegrationHelper.getWorlds()));
		}));
		this.updateDoneButton();
	}
	
	@Override
	public void render(PoseStack stack, int x, int y, float partialTicks) {
		this.propertyList.render(stack, x, y, partialTicks);
		drawCenteredString(stack, this.font, this.title, this.width / 2, 20, 16777215);
		super.render(stack, x, y, partialTicks);
	}
	
	public void markInvalid(PropertyEntry entry) {
		this.invalidEntries.add(entry);
		this.updateDoneButton();
	}

	public void clearInvalid(PropertyEntry entry) {
		this.invalidEntries.remove(entry);
		this.updateDoneButton();
	}
	
	protected void updateDoneButton() {
		this.doneButton.active = this.invalidEntries.isEmpty();
	}

	public void removed() {
		this.minecraft.keyboardHandler.setSendRepeatsToGui(false);
	}
	
}
