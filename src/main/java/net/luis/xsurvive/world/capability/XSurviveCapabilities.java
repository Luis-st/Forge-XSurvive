package net.luis.xsurvive.world.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class XSurviveCapabilities {
	
	public static final Capability<IPlayerCapability> PLAYER = CapabilityManager.get(new CapabilityToken<IPlayerCapability>() {});
	
}
