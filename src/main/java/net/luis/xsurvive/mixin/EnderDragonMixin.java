package net.luis.xsurvive.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.end.EndDragonFight;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;

@Mixin(EnderDragon.class)
public abstract class EnderDragonMixin extends Mob {
	
	@Shadow
	public EndCrystal nearestCrystal;
	@Shadow
	@Final
	private EndDragonFight dragonFight;
	@Shadow
	public int dragonDeathTime;
	@Shadow
	private Player unlimitedLastHurtByPlayer = null;
	
	private EnderDragonMixin(EntityType<? extends Mob> entityType, Level level) {
		super(entityType, level);
	}
	
	@Inject(method = "createAttributes", at = @At("HEAD"), cancellable = true)
	private static void createAttributes(CallbackInfoReturnable<AttributeSupplier.Builder> callback) {
		callback.setReturnValue(Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 400.0));
	}
	
	@Inject(method = "tickDeath", at = @At("HEAD"), cancellable = true)
	protected void tickDeath(CallbackInfo callback) {
		if (this.dragonFight != null) {
			this.dragonFight.updateDragon((EnderDragon) (Object) this);
		}
		++this.dragonDeathTime;
		if (this.dragonDeathTime >= 180 && this.dragonDeathTime <= 200) {
			float f = (this.random.nextFloat() - 0.5F) * 8.0F;
			float f1 = (this.random.nextFloat() - 0.5F) * 4.0F;
			float f2 = (this.random.nextFloat() - 0.5F) * 8.0F;
			this.level.addParticle(ParticleTypes.EXPLOSION_EMITTER, this.getX() + (double) f, this.getY() + 2.0D + (double) f1, this.getZ() + (double) f2, 0.0D, 0.0D, 0.0D);
		}
		boolean flag = this.level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT);
		int i = 12000;
		if (this.level instanceof ServerLevel level) {
			if (this.dragonDeathTime > 150 && this.dragonDeathTime % 5 == 0 && flag) {
				int award = ForgeEventFactory.getExperienceDrop(this, this.unlimitedLastHurtByPlayer, Mth.floor((float) i * 0.08F));
				ExperienceOrb.award(level, this.position(), award);
			}
			if (this.dragonDeathTime == 1 && !this.isSilent()) {
				this.level.globalLevelEvent(1028, this.blockPosition(), 0);
			}
		}
		this.move(MoverType.SELF, new Vec3(0.0D, (double) 0.1F, 0.0D));
		this.setYRot(this.getYRot() + 20.0F);
		this.yBodyRot = this.getYRot();
		if (this.dragonDeathTime == 200 && this.level instanceof ServerLevel level) {
			if (flag) {
				int award = ForgeEventFactory.getExperienceDrop(this, this.unlimitedLastHurtByPlayer, Mth.floor((float) i * 0.2F));
				ExperienceOrb.award(level, this.position(), award);
			}
			if (this.dragonFight != null) {
				this.dragonFight.setDragonKilled((EnderDragon) (Object) this);
			}
			this.remove(Entity.RemovalReason.KILLED);
			this.gameEvent(GameEvent.ENTITY_DIE);
		}
		callback.cancel();
	}
	
	@Inject(method = "checkCrystals", at = @At("HEAD"), cancellable = true)
	private void checkCrystals(CallbackInfo callback) {
		if (this.nearestCrystal != null) {
			if (this.nearestCrystal.isRemoved()) {
				this.nearestCrystal = null;
			} else if (this.getHealth() < this.getMaxHealth()) {
				this.heal(1.0F);
			}
		}
		EndCrystal nearestCrystal = null;
		double distance = Double.MAX_VALUE;
		for (EndCrystal endCrystal : this.level.getEntitiesOfClass(EndCrystal.class, this.getBoundingBox().inflate(32.0D))) {
			double distanceTo = endCrystal.distanceToSqr(this);
			if (distanceTo < distance) {
				distance = distanceTo;
				nearestCrystal = endCrystal;
			}
		}
		this.nearestCrystal = nearestCrystal;
		callback.cancel();
	}
	
}
