package net.luis.xsurvive.init;

import net.luis.xsurvive.common.capability.IPlayerCapability;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class XSurviveCapabilities {
	
	public static final Capability<IPlayerCapability> PLAYER = CapabilityManager.get(new CapabilityToken<IPlayerCapability>() {});
	
}
