package net.luis.xsurvive.world.item.alchemy;

import net.luis.xsurvive.XSurvive;
import net.luis.xsurvive.world.effect.XSurviveMobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class XSurvivePotions {
	
	public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, XSurvive.MOD_ID);
	
	
	public static final RegistryObject<Potion> FORST = POTIONS.register("frost", () -> {
		return new Potion(new MobEffectInstance(XSurviveMobEffects.FROST.get(), 3600));
	});
	public static final RegistryObject<Potion> LONG_FORST = POTIONS.register("long_frost", () -> {
		return new Potion(new MobEffectInstance(XSurviveMobEffects.FROST.get(), 9600, 1));
	});
	
}
