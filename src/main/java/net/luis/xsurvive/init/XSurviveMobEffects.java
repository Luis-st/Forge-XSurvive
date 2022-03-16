package net.luis.xsurvive.init;

import net.luis.xsurvive.XSurvive;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class XSurviveMobEffects {
	
	public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, XSurvive.MOD_ID);
	
	
	public static final RegistryObject<MobEffect> FROST = MOB_EFFECTS.register("frost", () -> {
		return new MobEffect(MobEffectCategory.HARMFUL, 13172735); 
	});
	
}
