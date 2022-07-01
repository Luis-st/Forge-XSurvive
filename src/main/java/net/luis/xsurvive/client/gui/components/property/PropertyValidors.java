package net.luis.xsurvive.client.gui.components.property;

import net.luis.xsurvive.server.ServerIntegrationHelper;

public class PropertyValidors {
	
	public static boolean worldNotExists(String world) {
		return !ServerIntegrationHelper.getWorlds().contains(world);
	}
	
	public static boolean viewDistanceBounds(int value) {
		return 32 >= value && value > 1;
	}
	
	public static boolean simulationDistanceBounds(int value) {
		return 32 >= value && value > 4;
	}
	
	public static boolean maxPlayersBounds(int value) {
		return value > 0;
	}
	
}
