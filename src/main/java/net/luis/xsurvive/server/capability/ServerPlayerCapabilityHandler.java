package net.luis.xsurvive.server.capability;

import net.luis.xsurvive.common.capability.IPlayerCapability;
import net.luis.xsurvive.network.XSurviveNetworkHandler;
import net.luis.xsurvive.network.packet.LocalPlayerCapabilityUpdatePacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class ServerPlayerCapabilityHandler implements IPlayerCapability {
	
	protected final ServerPlayer player;
	protected ServerLevel level;
	protected int tick;
	protected int frostTime;
	protected int startFrostTime;
	protected int lastSync;
	protected boolean changed = false;
	
	public ServerPlayerCapabilityHandler(ServerPlayer player) {
		this.player = player;
		this.level = player.getLevel();
	}
	
	@Override
	public ServerPlayer getPlayer() {
		return this.player;
	}
	
	@Override
	public ServerLevel getLevel() {
		return this.getPlayer().getLevel();
	}
	
	@Override
	public void updateLevel() {
		this.level = this.player.getLevel();
	}
	
	@Override
	public void tick() {
		this.tick++;
		if (this.changed) {
			this.broadcastChanges();
			this.lastSync = 0;
			this.changed = false;
		}
		this.lastSync++;
	}
	
	@Override
	public int getFrostTime() {
		return this.frostTime;
	}
	
	@Override
	public void setFrostTime(int frostTime) {
		this.frostTime = frostTime;
		this.startFrostTime = frostTime;
	}
	
	@Override
	public float getFrostPercent() {
		double time = ((double) this.startFrostTime - (double) this.frostTime) / 20.0;
		if (time > 10.0) {
			return 1.0F;
		}
		return (float) time / 10.0F;
	}
	
	@Override
	public void setChanged() {
		this.changed = true;
	}

	@Override
	public void broadcastChanges() {
		XSurviveNetworkHandler.sendToPlayer(this.player, new LocalPlayerCapabilityUpdatePacket(this.serialize()));
	}
	
	@Override
	public CompoundTag serialize() {
		CompoundTag tag = new CompoundTag();
		tag.putInt("tick", this.tick);
		tag.putInt("frost_time", this.frostTime);
		tag.putInt("start_frost_time", this.startFrostTime);
		tag.putInt("last_sync", this.lastSync);
		tag.putBoolean("changed", this.changed);
		return tag;
	}

	@Override
	public void deserialize(CompoundTag tag) {
		this.tick = tag.getInt("tick");
		this.frostTime = tag.getInt("frost_time");
		this.startFrostTime = tag.getInt("start_frost_time");
		this.lastSync = tag.getInt("last_sync");
		this.changed = tag.getBoolean("changed");
	}
	
	public void deserializeFromClient(CompoundTag tag) {
		
	}
	
}
