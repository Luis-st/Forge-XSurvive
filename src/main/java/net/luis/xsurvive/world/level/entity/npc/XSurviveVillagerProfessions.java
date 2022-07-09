package net.luis.xsurvive.world.level.entity.npc;

import com.google.common.collect.ImmutableSet;

import net.luis.xsurvive.XSurvive;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class XSurviveVillagerProfessions {
	
	public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(ForgeRegistries.PROFESSIONS, XSurvive.MOD_ID);
	
	
	public static final RegistryObject<VillagerProfession> BEEKEEPER = PROFESSIONS.register("beekeeper", () -> {
		return register("xsurvive:beekeeper", new ResourceLocation(XSurvive.MOD_ID, "beekeeper"));
	});
	public static final RegistryObject<VillagerProfession> ENCHANTER = PROFESSIONS.register("enchanter", () -> {
		return register("xsurvive:enchanter", new ResourceLocation(XSurvive.MOD_ID, "enchanter"));
	});
	public static final RegistryObject<VillagerProfession> END_TRADER = PROFESSIONS.register("end_trader", () -> {
		return register("xsurvive:end_trader", new ResourceLocation(XSurvive.MOD_ID, "end_trader"));
	});
	public static final RegistryObject<VillagerProfession> LUMBERJACK = PROFESSIONS.register("lumberjack", () -> {
		return register("xsurvive:lumberjack", new ResourceLocation(XSurvive.MOD_ID, "lumberjack"));
	});
	public static final RegistryObject<VillagerProfession> MINER = PROFESSIONS.register("miner", () -> {
		return register("xsurvive:miner", new ResourceLocation(XSurvive.MOD_ID, "miner"));
	});
	public static final RegistryObject<VillagerProfession> MOB_HUNTER = PROFESSIONS.register("mob_hunter", () -> {
		return register("xsurvive:mob_hunter", new ResourceLocation(XSurvive.MOD_ID, "mob_hunter"));
	});
	public static final RegistryObject<VillagerProfession> NETHER_TRADER = PROFESSIONS.register("nether_trader", () -> {
		return register("xsurvive:nether_trader", new ResourceLocation(XSurvive.MOD_ID, "nether_trader"));
	});
	
	private static VillagerProfession register(String name, ResourceLocation location) {
		return new VillagerProfession(name, (holder) -> {
			return holder.is(location);
		}, (holder) -> {
			return holder.is(location);
		}, ImmutableSet.of(), ImmutableSet.of(), null);
	}
	
}
