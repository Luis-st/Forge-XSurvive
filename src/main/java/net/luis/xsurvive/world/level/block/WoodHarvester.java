package net.luis.xsurvive.world.level.block;

import java.util.List;
import java.util.stream.Collectors;

import net.luis.xsurvive.world.item.enchantment.XSurviveEnchantments;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class WoodHarvester {
	
	private final Level level;
	private final BlockPos startPos;
	private final BlockState state;
	private final int harvesting;
	private final Player player;
	
	public WoodHarvester(Level level, BlockPos startPos, BlockState state, int harvesting, Player player) {
		this.level = level;
		this.startPos = startPos;
		this.state = state;
		this.harvesting = harvesting;
		this.player = player;
	}
	
	public void harvest() {
		List<BlockPos> positions = BlockPos.betweenClosedStream(this.startPos.north().east(), this.startPos.south().west()).map(this::immutable).filter(this::isTreePosition).collect(Collectors.toList());
		int height = this.getTreeHeight(positions);
		if (height != 0) {
			AABB aabb = this.getHarvestingArea(positions, height);
			List<BlockPos> harvestingPositions = BlockPos.betweenClosedStream(aabb).map(this::immutable).filter(this::isTreePosition).collect(Collectors.toList());
			for (BlockPos pos : harvestingPositions) {
				BlockState state = this.level.getBlockState(pos);
				this.level.destroyBlock(pos, false);
				Block.dropResources(state, this.level, pos, state.hasBlockEntity() ? this.level.getBlockEntity(pos) : null, this.player, this.getUsedStack());
			}
		}
	}
	
	private int getTreeHeight(List<BlockPos> positions) {
		int height = 0;
		for (BlockPos pos : positions) {
			height = Math.max(this.getTreePositionHeight(pos), height);
		}
		return Math.min(this.harvesting * 4 + (this.harvesting / 2), height);
	}
	
	private int getTreePositionHeight(BlockPos pos) {
		int height = 0;
		for (int y = 0; y <= 256; y++) {
			if (this.isTreePosition(pos.above(y))) {
				++height;
			} else {
				break;
			}
		}
		return height;
	}
	
	private AABB getHarvestingArea(List<BlockPos> positions, int height) {
		AABB aabb;
		if (positions.size() == 1) {
			aabb = new AABB(this.startPos.north().east(), this.startPos.south().west());
		} else if (positions.size() == 4) {
			aabb = new AABB(this.startPos.north(1).east(1), this.startPos.south(1).west(1));
		} else {
			aabb = new AABB(this.startPos.north(2).east(2), this.startPos.south(2).west(2));
		}
		int y = this.startPos.getY();
		return aabb.setMinY(y - (height / 2)).setMaxY(y + height);
	}
	
	private ItemStack getUsedStack() {
		ItemStack mainStack = this.player.getMainHandItem();
		ItemStack offStack = this.player.getOffhandItem();
		if (mainStack.getEnchantmentLevel(XSurviveEnchantments.HARVESTING.get()) > 0) {
			return mainStack;
		} else if (offStack.getEnchantmentLevel(XSurviveEnchantments.HARVESTING.get()) > 0) {
			return offStack;
		} else if (!mainStack.isEmpty()) {
			return mainStack;
		} else if (!offStack.isEmpty()) {
			return offStack;
		}
		return ItemStack.EMPTY;
	}
	
	private boolean isTreePosition(BlockPos pos) {
		BlockState state = this.level.getBlockState(pos);
		return state.is(BlockTags.LOGS) || state.is(this.state.getBlock());
	}
	
	private BlockPos immutable(BlockPos pos) {
		if (pos instanceof MutableBlockPos mutablePos) {
			return mutablePos.immutable();
		}
		return pos;
	}
	
}
