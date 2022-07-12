package net.luis.xsurvive.world.level.entity.npc;

import com.google.common.collect.ImmutableSet;

import net.luis.xsurvive.XSurvive;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class XSurviveVillagerProfessions {
	
	public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, XSurvive.MOD_ID);
	
	
	public static final RegistryObject<VillagerProfession> BEEKEEPER = VILLAGER_PROFESSIONS.register("beekeeper", () -> {
		return register("beekeeper");
	});
	public static final RegistryObject<VillagerProfession> ENCHANTER = VILLAGER_PROFESSIONS.register("enchanter", () -> {
		return register("enchanter");
	});
	public static final RegistryObject<VillagerProfession> END_TRADER = VILLAGER_PROFESSIONS.register("end_trader", () -> {
		return register("end_trader");
	});
	public static final RegistryObject<VillagerProfession> LUMBERJACK = VILLAGER_PROFESSIONS.register("lumberjack", () -> {
		return register("lumberjack");
	});
	public static final RegistryObject<VillagerProfession> MINER = VILLAGER_PROFESSIONS.register("miner", () -> {
		return register("miner");
	});
	public static final RegistryObject<VillagerProfession> MOB_HUNTER = VILLAGER_PROFESSIONS.register("mob_hunter", () -> {
		return register("mob_hunter");
	});
	public static final RegistryObject<VillagerProfession> NETHER_TRADER = VILLAGER_PROFESSIONS.register("nether_trader", () -> {
		return register("nether_trader");
	});
	
	private static VillagerProfession register(String name) {
		ResourceLocation location = new ResourceLocation(XSurvive.MOD_ID, name);
		return new VillagerProfession(location.toString(), (holder) -> {
			return holder.is(location);
		}, (holder) -> {
			return holder.is(location);
		}, ImmutableSet.of(), ImmutableSet.of(), null);
	}
	
}
