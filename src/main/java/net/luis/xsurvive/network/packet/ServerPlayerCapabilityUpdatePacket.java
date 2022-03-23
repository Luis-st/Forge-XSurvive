package net.luis.xsurvive.network.packet;

import java.util.function.Supplier;

import net.luis.xsurvive.common.capability.CapabilityUtil;
import net.luis.xsurvive.server.capability.ServerPlayerCapabilityHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent.Context;

public class ServerPlayerCapabilityUpdatePacket {
	
	protected final CompoundTag tag;
	
	public ServerPlayerCapabilityUpdatePacket(CompoundTag tag) {
		this.tag = tag;
	}
	
	public static void encode(ServerPlayerCapabilityUpdatePacket packet, FriendlyByteBuf byteBuf) {
		byteBuf.writeNbt(packet.tag);
	}
	
	public static ServerPlayerCapabilityUpdatePacket decode(FriendlyByteBuf byteBuf) {
		return new ServerPlayerCapabilityUpdatePacket(byteBuf.readNbt());
	}
	
	public static class Handler {
		
		public static void handle(ServerPlayerCapabilityUpdatePacket packet, Supplier<Context> context) {
			ServerPlayer player = context.get().getSender();
			context.get().enqueueWork(() -> {
				ServerPlayerCapabilityHandler handler = CapabilityUtil.getServerPlayer(player);
				handler.deserializeFromClient(packet.tag);
			});
		}
		
	}
	
}
