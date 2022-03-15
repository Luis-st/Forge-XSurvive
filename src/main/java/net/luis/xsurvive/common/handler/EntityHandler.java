package net.luis.xsurvive.common.handler;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.monster.MagmaCube;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.monster.Strider;

public class EntityHandler {
	
	public static boolean isAffectedByEnderSlayer(Entity entity) {
		return entity instanceof EnderMan || entity instanceof Endermite || entity instanceof Shulker || entity instanceof EnderDragon;
	}
	
	public static boolean isAffectedByImpaling(Entity entity) {
		if (entity instanceof MagmaCube || entity instanceof Ghast || entity instanceof Blaze || entity instanceof Strider) {
			return true;
		}
		return entity instanceof AbstractFish || entity instanceof Dolphin || entity instanceof Squid || entity instanceof Guardian || entity instanceof ElderGuardian || entity instanceof Drowned || entity instanceof Turtle;
	}

}
