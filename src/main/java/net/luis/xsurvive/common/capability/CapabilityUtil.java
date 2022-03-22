package net.luis.xsurvive.common.capability;

public class CapabilityUtil {
	
//	public static IPlayerCapability getPlayer(Player player) {
//		return player.getCapability(XSurviveCapabilities.PLAYER, null).orElseThrow(NullPointerException::new);
//	}
//	
//	@Nullable
//	public static LocalPlayerCapabilityHandler getLocalPlayer(Player player) {
//		try {
//			IPlayerCapability capability = player.getCapability(XSurviveCapabilities.PLAYER, null).orElseThrow(NullPointerException::new);
//			if (capability instanceof LocalPlayerCapabilityHandler handler) {
//				return handler;
//			} else if (capability instanceof ServerPlayerCapabilityHandler handler) {
//				throw new IllegalStateException("Fail to get LocalPlayerCapabilityHandler from Server");
//			}
//		} catch (Exception e) {
//			XSurvive.LOGGER.info("{}", XSurviveCapabilities.PLAYER.getName());
//		}
//		return null;
//	}
//	
//	@Nullable
//	public static ServerPlayerCapabilityHandler getServerPlayer(Player player) {
//		try {
//			IPlayerCapability capability = player.getCapability(XSurviveCapabilities.PLAYER, null).orElseThrow(NullPointerException::new);
//			if (capability instanceof LocalPlayerCapabilityHandler handler) {
//				throw new IllegalStateException("Fail to get ServerPlayerCapabilityHandler from Client");
//			} else if (capability instanceof ServerPlayerCapabilityHandler handler) {
//				return handler;
//			}
//		} catch (Exception e) {
//			XSurvive.LOGGER.info("{}", XSurviveCapabilities.PLAYER.getName());
//		}
//		return null;
//	}
	
}
