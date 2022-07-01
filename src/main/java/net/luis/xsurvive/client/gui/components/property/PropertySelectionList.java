package net.luis.xsurvive.client.gui.components.property;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

import net.luis.xsurvive.client.gui.screens.CreateServerScreen;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Difficulty;
import net.minecraft.world.level.GameType;

public class PropertySelectionList extends ContainerObjectSelectionList<PropertyEntry> {
	
	protected static final List<Component> WORLD_TYPES = Lists.newArrayList(Component.literal("Default"), Component.literal("Superflat"), Component.literal("Large Biomes"), Component.literal("Amplified"));
	protected static final List<Component> GAMEMODES = Lists.newArrayList(GameType.values()).stream().map(GameType::getShortDisplayName).collect(Collectors.toList());
	protected static final List<Component> DIFFICULTIES = Lists.newArrayList(Difficulty.values()).stream().map(Difficulty::getDisplayName).collect(Collectors.toList());
	
	protected final CreateServerScreen screen;
	
	public PropertySelectionList(CreateServerScreen screen) {
		super(screen.getMinecraft(), screen.width, screen.height, 43, screen.height - 32, 24);
		this.screen = screen;
	}
	
	public void initNewServer() {
		this.addStringProperty("level-name", Component.translatable("screen.create_server.world_name"), "New World", PropertyValidors::worldNotExists);
		this.addIntegerProperty("level-seed", Component.translatable("screen.create_server.world_seed"), null, IntegerPropertyEntry.TRUE_VALIDOR);
		this.addCycleProperty("level-type", Component.translatable("screen.create_server.world_type"), Component.literal("Default"), WORLD_TYPES);
		this.addCycleProperty("gamemode", Component.translatable("screen.create_server.gamemode"), GameType.SURVIVAL.getShortDisplayName(), GAMEMODES);
		this.addCycleProperty("difficulty", Component.translatable("screen.create_server.difficulty"), Difficulty.EASY.getDisplayName(), DIFFICULTIES);
		this.addBooleanProperty("hardcore", Component.translatable("screen.create_server.hardcore"), false);
		this.addBooleanProperty("pvp", Component.translatable("screen.create_server.pvp"), true);
		this.addBooleanProperty("spawn-animals", Component.translatable("screen.create_server.spawn_animals"), true);
		this.addBooleanProperty("spawn-npcs", Component.translatable("screen.create_server.spawn_npcs"), true);
		this.addBooleanProperty("spawn-monsters", Component.translatable("screen.create_server.spawn_monsters"), true);
		this.addBooleanProperty("generate-structures", Component.translatable("screen.create_server.generate_structures"), true);
		this.addBooleanProperty("allow-nether", Component.translatable("screen.create_server.allow_nether"), true);
		this.addIntegerProperty("view-distance", Component.translatable("screen.create_server.view_distance"), 10, PropertyValidors::viewDistanceBounds);
		this.addIntegerProperty("simulation-distance", Component.translatable("screen.create_server.simulation_distance"), 10, PropertyValidors::simulationDistanceBounds);
		this.addIntegerProperty("max-players", Component.translatable("screen.create_server.max_players"), 20, PropertyValidors::maxPlayersBounds);
		this.addBooleanProperty("white-list", Component.translatable("screen.create_server.white_list"), false);
		this.addBooleanProperty("enable-command-block", Component.translatable("screen.create_server.enable_command_block"), false);
	}
	
	public void initExistingServer() {
		this.addCycleProperty("difficulty", Component.translatable("screen.create_server.difficulty"), Difficulty.EASY.getDisplayName(), DIFFICULTIES);
		this.addBooleanProperty("pvp", Component.translatable("screen.create_server.pvp"), true);
		this.addBooleanProperty("spawn-animals", Component.translatable("screen.create_server.spawn_animals"), true);
		this.addBooleanProperty("spawn-npcs", Component.translatable("screen.create_server.spawn_npcs"), true);
		this.addBooleanProperty("spawn-monsters", Component.translatable("screen.create_server.spawn_monsters"), true);
		this.addIntegerProperty("view-distance", Component.translatable("screen.create_server.view_distance"), 10, PropertyValidors::viewDistanceBounds);
		this.addIntegerProperty("simulation-distance", Component.translatable("screen.create_server.simulation_distance"), 10, PropertyValidors::simulationDistanceBounds);
		this.addIntegerProperty("max-players", Component.translatable("screen.create_server.max_players"), 20, PropertyValidors::maxPlayersBounds);
		this.addBooleanProperty("white-list", Component.translatable("screen.create_server.white_list"), false);
		this.addBooleanProperty("enable-command-block", Component.translatable("screen.create_server.enable_command_block"), false);
	}
	
	protected void addBooleanProperty(String key, Component label, boolean defaultValue) {
		this.addEntry(new BooleanPropertyEntry(this.screen, key, label, defaultValue));
	}
	
	protected void addCycleProperty(String key, Component label, Component defaultValue, List<Component> values) {
		this.addEntry(new CyclePropertyEntry(this.screen, key, label, defaultValue, values));
	}
	
	protected void addIntegerProperty(String key, Component label, Integer defaultValue, Function<Integer, Boolean> validor) {
		this.addEntry(new IntegerPropertyEntry(this.screen, key, label, defaultValue, validor));
	}
	
	protected void addStringProperty(String key, Component label, String defaultValue, Function<String, Boolean> validor) {
		this.addEntry(new StringPropertyEntry(this.screen, key, label, defaultValue, validor));
	}
	
}
