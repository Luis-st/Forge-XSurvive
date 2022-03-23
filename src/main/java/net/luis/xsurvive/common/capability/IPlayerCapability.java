package net.luis.xsurvive.common.capability;

import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public interface IPlayerCapability {
	
	@Nullable
	Player getPlayer();
	
	@Nullable
	Level getLevel();
	
	void tick();
	
	int getFrostTime();
	
	void setFrostTime(int frostTime);
	
	float getFrostPercent();
	
	default void setChanged() {
		
	}
	
	default void broadcastChanges() {
		
	}
	
	CompoundTag serialize();
	
	CompoundTag serializePersistent();
    
    void deserialize(CompoundTag tag);
    
    void deserializePersistent(CompoundTag tag);
	
}
