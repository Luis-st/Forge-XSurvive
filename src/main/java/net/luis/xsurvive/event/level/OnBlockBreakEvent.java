package net.luis.xsurvive.event.level;

import net.luis.xsurvive.XSurvive;
import net.luis.xsurvive.common.handler.EnchantmentHandler;
import net.luis.xsurvive.init.XSurviveEnchantments;
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
		int xp = event.getExpToDrop();
		int experience = EnchantmentHandler.getEnchantmentLevel(XSurviveEnchantments.EXPERIENCE.get(), player);
		int multiDrop = EnchantmentHandler.getEnchantmentLevel(XSurviveEnchantments.MULTI_DROP.get(), player);
		int fortune = EnchantmentHandler.getEnchantmentLevel(Enchantments.BLOCK_FORTUNE, player);
		int blasting = EnchantmentHandler.getEnchantmentLevel(XSurviveEnchantments.BLASTING.get(), player);
		if (xp > 0 && experience > 0) {
			event.setExpToDrop((xp * ((experience + 1) * ((experience * 2) + fortune))) * (multiDrop + 1));
		}
		if (blasting > 0) {
			player.level.explode(player, event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), 2.0f * (blasting + 1), Explosion.BlockInteraction.BREAK);
		}
	}
	
}
