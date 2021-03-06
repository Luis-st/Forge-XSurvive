package net.luis.xsurvive.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.Level;

@Mixin(WitherBoss.class)
public abstract class WitherBossMixin extends Monster {
	
	@Shadow
	@Final
	private int[] idleHeadUpdates;
	@Shadow
	private int destroyBlocksTick;
	@Shadow
	@Final
	private ServerBossEvent bossEvent;
	
	private WitherBossMixin(EntityType<? extends Monster> entityType, Level level) {
		super(entityType, level);
	}
	
	@Shadow
	public abstract int getInvulnerableTicks();
	
	@Shadow
	public abstract void setInvulnerableTicks(int invulnerableTicks);
	
	@Inject(method = "createAttributes", at = @At("HEAD"), cancellable = true)
	private static void createAttributes(CallbackInfoReturnable<AttributeSupplier.Builder> callback) {
		callback.setReturnValue(Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 400.0).add(Attributes.MOVEMENT_SPEED, 0.75).add(Attributes.FLYING_SPEED, 0.75).add(Attributes.FOLLOW_RANGE, 128.0).add(Attributes.ARMOR, 12.0));
	}
	
	@Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
	public void hurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> callback) {
		if (this.isInvulnerableTo(source)) {
			callback.setReturnValue(false);
		} else if (source != DamageSource.DROWN && !(source.getEntity() instanceof WitherBoss)) {
			if (this.getInvulnerableTicks() > 0 && source != DamageSource.OUT_OF_WORLD) {
				callback.setReturnValue(false);
			} else {
				if (source.getDirectEntity() instanceof AbstractArrow arrow && 4 > arrow.getPierceLevel()) {
					callback.setReturnValue(false);
				} else {
					Entity entity = source.getEntity();
					if (entity != null && entity instanceof LivingEntity livingEntity && !(livingEntity instanceof Player) && livingEntity.getMobType() == this.getMobType()) {
						callback.setReturnValue(false);
					} else {
						if (this.destroyBlocksTick <= 0) {
							this.destroyBlocksTick = 20;
						}
						for (int i = 0; i < this.idleHeadUpdates.length; ++i) {
							this.idleHeadUpdates[i] += 3;
						}
						callback.setReturnValue(super.hurt(source, amount));
					}
				}
			}
		} else {
			callback.setReturnValue(false);
		}
	}
	
	@Inject(method = "makeInvulnerable", at = @At("HEAD"), cancellable = true)
	public void makeInvulnerable(CallbackInfo callback) {
		this.setInvulnerableTicks(80);
		this.bossEvent.setProgress(0.0F);
		callback.cancel();
	}
	
	@Inject(method = "isPowered", at = @At("HEAD"), cancellable = true)
	public void isPowered(CallbackInfoReturnable<Boolean> callback) {
		callback.setReturnValue(true);
	}
	
}
