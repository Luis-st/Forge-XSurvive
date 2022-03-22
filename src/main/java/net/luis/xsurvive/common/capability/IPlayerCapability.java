package net.luis.xsurvive.common.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public interface IPlayerCapability {
	
	Player getPlayer();
	
	Level getLevel();
	
	void updateLevel();
	
	void tick();
	
	int getFrostTime();
	
	void setFrostTime(int frostTime);
	
	float getFrostPercent();
	
	default void setChanged() {
		
	}
	
	default void broadcastChanges() {
		
	}
	
	CompoundTag serialize();
    
    void deserialize(CompoundTag tag);
	
}
