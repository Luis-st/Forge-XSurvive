package net.luis.xsurvive.data.provider.loottable;

import com.google.common.collect.Lists;

import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;

public class XSurviveBlockLoot extends BlockLoot {
	
	XSurviveBlockLoot() {
		
	}
	
	@Override
	protected void addTables() {
		
	}
	
	@Override
	protected Iterable<Block> getKnownBlocks() {
		return Lists.newArrayList();
	}

}