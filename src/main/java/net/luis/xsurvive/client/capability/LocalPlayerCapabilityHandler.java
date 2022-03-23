package net.luis.xsurvive.client.capability;

import net.luis.xsurvive.common.capability.IPlayerCapability;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;

public class LocalPlayerCapabilityHandler implements IPlayerCapability {
	
	protected final LocalPlayer player;
	protected int tick;
	protected int frostTime;
	protected int startFrostTime;
	
	public LocalPlayerCapabilityHandler(LocalPlayer player) {
		this.player = player;
	}
	
	@Override
	public LocalPlayer getPlayer() {
		return this.player;
	}
	
	@Override
	public ClientLevel getLevel() {
		return (ClientLevel) this.player.getLevel();
	}
	
	@Override
	public void tick() {
		this.tick++;
		if (this.frostTime > 0) {
			this.frostTime--;
		}
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
		double showStartTime = ((double) this.startFrostTime - (double) this.frostTime) / 20.0;
		double showEndTime = ((double) this.frostTime) / 20.0;
		if (showStartTime > 5.0 && showEndTime > 5.0) {
			return 1.0F;
		} else if (5.0 >= showStartTime) {
			return (float) showStartTime / 5.0F;
		} else if (5.0 >= showEndTime) {
			return (float) showEndTime / 5.0F;
		}
		return 0.0F;
	}
	
	@Override
	public CompoundTag serialize() {
		CompoundTag tag = new CompoundTag();
		tag.putInt("tick", this.tick);
		tag.putInt("frost_time", this.frostTime);
		tag.putInt("start_frost_time", this.startFrostTime);
		return tag;
	}
	
	@Override
	public CompoundTag serializePersistent() {
		CompoundTag tag = new CompoundTag();
		tag.putInt("tick", this.tick);
		tag.putInt("frost_time", this.frostTime);
		tag.putInt("start_frost_time", this.startFrostTime);
		return tag;
	}

	@Override
	public void deserialize(CompoundTag tag) {
		this.tick = tag.getInt("tick");
		this.frostTime = tag.getInt("frost_time");
		this.startFrostTime = tag.getInt("start_frost_time");
	}
	
	@Override
	public void deserializePersistent(CompoundTag tag) {
		this.tick = tag.getInt("tick");
		this.frostTime = tag.getInt("frost_time");
		this.startFrostTime = tag.getInt("start_frost_time");
	}
	
	public void deserializeFromServer(CompoundTag tag) {
		this.deserialize(tag);
	}

}
