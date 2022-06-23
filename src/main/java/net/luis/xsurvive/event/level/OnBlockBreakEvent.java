package net.luis.xsurvive.event.level;

import net.luis.xsurvive.XSurvive;
import net.luis.xsurvive.common.block.BlockDestroyer;
import net.luis.xsurvive.common.handler.EnchantmentHandler;
import net.luis.xsurvive.init.XSurviveEnchantments;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Explosion;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = XSurvive.MOD_ID)
public class OnBlockBreakEvent {
	
	@SubscribeEvent
	public static void blockBreak(BlockEvent.BreakEvent event) {
		Player player = event.getPlayer();
		BlockPos pos = new BlockPos(event.getPos().getX(), event.getPos().getY(), event.getPos().getZ());
		int xp = event.getExpToDrop();
		int experience = EnchantmentHandler.getEnchantmentLevel(XSurviveEnchantments.EXPERIENCE.get(), player);
		int multiDrop = EnchantmentHandler.getEnchantmentLevel(XSurviveEnchantments.MULTI_DROP.get(), player);
		int fortune = EnchantmentHandler.getEnchantmentLevel(Enchantments.BLOCK_FORTUNE, player);
		int blasting = EnchantmentHandler.getEnchantmentLevel(XSurviveEnchantments.BLASTING.get(), player);
		int harvesting = EnchantmentHandler.getEnchantmentLevel(XSurviveEnchantments.HARVESTING.get(), player);
		if (xp > 0 && experience > 0) {
			event.setExpToDrop((xp * ((experience + 1) * ((experience * 2) + fortune))) * (multiDrop + 1));
		}
		if (blasting > 0) {
			player.level.explode(player, pos.getX(), pos.getY(), pos.getZ(), 2.0f * (blasting + 1), Explosion.BlockInteraction.BREAK);
		}
		if (harvesting > 0 && player.level instanceof ServerLevel serverLevel) {
			BlockDestroyer destroyer = new BlockDestroyer(player, serverLevel, event.getPos(), harvesting * 6);
			destroyer.destroy();
			destroyer.dropLoot(pos);
		}
	}
	
}
