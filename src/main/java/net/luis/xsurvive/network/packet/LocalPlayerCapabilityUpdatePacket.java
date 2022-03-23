package net.luis.xsurvive.network.packet;

import java.util.function.Supplier;

import net.luis.xsurvive.client.capability.LocalPlayerCapabilityHandler;
import net.luis.xsurvive.common.capability.CapabilityUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent.Context;

public class LocalPlayerCapabilityUpdatePacket {
	
	protected final CompoundTag tag;
	
	public LocalPlayerCapabilityUpdatePacket(CompoundTag tag) {
		this.tag = tag;
	}
	
	public static void encode(LocalPlayerCapabilityUpdatePacket packet, FriendlyByteBuf byteBuf) {
		byteBuf.writeNbt(packet.tag);
	}
	
	public static LocalPlayerCapabilityUpdatePacket decode(FriendlyByteBuf byteBuf) {
		return new LocalPlayerCapabilityUpdatePacket(byteBuf.readNbt());
	}
	
	public static class Handler {
		
		@SuppressWarnings("resource")
		public static void handle(LocalPlayerCapabilityUpdatePacket packet, Supplier<Context> context) {
			LocalPlayer player = Minecraft.getInstance().player;
			context.get().enqueueWork(() -> {
				LocalPlayerCapabilityHandler handler = CapabilityUtil.getLocalPlayer(player);
				handler.deserializeFromServer(packet.tag);
			});
		}
		
	}
	
}
