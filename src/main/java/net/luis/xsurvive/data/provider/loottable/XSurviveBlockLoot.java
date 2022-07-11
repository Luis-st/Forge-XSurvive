package net.luis.xsurvive.data.provider.loottable;

import java.util.stream.Collectors;

import net.luis.xsurvive.world.level.block.XSurviveBlocks;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class XSurviveBlockLoot extends BlockLoot {
	
	XSurviveBlockLoot() {
		
	}
	
	@Override
	protected void addTables() {
		this.dropSelf(XSurviveBlocks.SMELTING_FURNACE.get());
	}
	
	@Override
	protected Iterable<Block> getKnownBlocks() {
		return XSurviveBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).collect(Collectors.toList());
	}

}