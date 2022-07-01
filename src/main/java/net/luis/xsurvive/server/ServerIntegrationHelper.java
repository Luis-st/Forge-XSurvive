package net.luis.xsurvive.server;

import java.io.File;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

import net.luis.xsurvive.XSurvive;
import net.minecraft.server.dedicated.DedicatedServerProperties;
import net.minecraft.server.dedicated.DedicatedServerSettings;
import net.minecraft.server.dedicated.Settings;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.versions.forge.ForgeVersion;
import net.minecraftforge.versions.mcp.MCPVersion;

public class ServerIntegrationHelper {
	
	public static String getVersion() {
		return MCPVersion.getMCVersion() + "-" + ForgeVersion.getVersion();
	}
	
	public static Path getServerDirectory() {
		return FMLPaths.GAMEDIR.get().resolve("forge-servers").resolve(MCPVersion.getMCVersion()).resolve("forge-" + ForgeVersion.getVersion());
	}
	
	public static Path getServerJarDirectory() {
		return getServerDirectory().resolve("libraries/net/minecraftforge/forge").resolve(getVersion()).resolve("forge-" + getVersion() + "-server.jar");
	}
	
	public static boolean isServerInstalled() {
		if (Files.exists(getServerJarDirectory())) {
			return true;
		}
		return false;
	}
	
	public static void createEulaFile(Path path) {
		try {
			if (ServerIntegrationHelper.isServerInstalled()) {
				XSurvive.LOGGER.info("Create agreed eula");
				OutputStream outputStream = Files.newOutputStream(path.resolve("eula.txt"));
				Properties properties = new Properties();
				properties.setProperty("eula", "true");
				properties.store(outputStream, "This EULA was create by " + XSurvive.MOD_NAME + " to skip the EULA acception at server start");
				outputStream.flush();
				outputStream.close();
			}
		} catch (Exception e) {
			XSurvive.LOGGER.error("Fail to create agreed eula", e);
		}
	}
	
	public static Properties loadServerProperties(String world) {
		Path path;
		if (world != null && getWorlds().contains(world)) {
			path = getServerDirectory().resolve(world).resolve("server.properties");
		} else {
			path = getServerDirectory().resolve("server.properties");
		}
		DedicatedServerSettings settings = new DedicatedServerSettings(path);
		settings.forceSave();
		return ObfuscationReflectionHelper.getPrivateValue(Settings.class, settings.getProperties(), "f_139798_");
	}
	
	public static List<String> getWorlds() {
		List<String> worlds = Lists.newArrayList();
		for (File file : Lists.newArrayList(getServerDirectory().toFile().listFiles())) {
			if (isWorldDirectory(file)) {
				worlds.add(file.getName());
			}
		}
		return worlds;
	}
	
	public static boolean isWorldDirectory(File file) {
		if (!file.exists()) {
			return false;
		} else if (!file.isDirectory()) {
			return false;
		}
		List<String> folders = Lists.newArrayList(file.listFiles()).stream().filter(File::isDirectory).map(File::getName).collect(Collectors.toList());
		List<String> files = Lists.newArrayList(file.listFiles()).stream().filter(File::isFile).map(File::getName).collect(Collectors.toList());
		return folders.contains("DIM1") && folders.contains("DIM-1") && folders.contains("entities") && folders.contains("playerdata") && folders.contains("poi") && folders.contains("region") && files.contains("level.dat");
	}
	
	public static void saveServerProperties() {
		Properties properties = loadServerProperties(null);
		String world = properties.getProperty("level-name");
		if (getWorlds().contains(world)) {
			DedicatedServerProperties serverProperties = new DedicatedServerProperties(properties);
			serverProperties.store(getServerDirectory().resolve(world).resolve("server.properties"));
		} else {
			XSurvive.LOGGER.warn("Fail to save server.properties of world {}, since the world does not exists", world);
		}
	}
	
}
