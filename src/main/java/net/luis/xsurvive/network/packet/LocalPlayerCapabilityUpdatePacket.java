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
	
	public LocalPlayerCapabilityUpdatePacket(FriendlyByteBuf byteBuf) {
		this.tag = byteBuf.readNbt();
	}
	
	public void encode(FriendlyByteBuf byteBuf) {
		byteBuf.writeNbt(this.tag);
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
