package net.luis.xsurvive.client.capability;

import net.luis.xsurvive.common.capability.IPlayerCapability;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;

public class LocalPlayerCapabilityHandler implements IPlayerCapability {
	
	protected final LocalPlayer player;
	protected ClientLevel level;
	protected int tick;
	protected int frostTime;
	protected int startFrostTime;
	
	public LocalPlayerCapabilityHandler(LocalPlayer player) {
		this.player = player;
		this.level = (ClientLevel) player.getLevel();
	}
	
	@Override
	public LocalPlayer getPlayer() {
		return this.player;
	}
	
	@Override
	public ClientLevel getLevel() {
		return this.level;
	}
	
	@Override
	public void updateLevel() {
		this.level = (ClientLevel) this.player.getLevel();
	}
	
	@Override
	public void tick() {
		this.tick++;
	}
	
	@Override
	public int getFrostTime() {
		return this.frostTime;
	}
	
	@Override
	public void setFrostTime(int frostTime) {
		this.frostTime = frostTime;
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
	public CompoundTag serialize() {
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
	
	public void deserializeFromServer(CompoundTag tag) {
		this.deserialize(tag);
	}

}
