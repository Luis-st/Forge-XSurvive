package net.luis.xsurvive.world.level.block.entity;

import net.luis.xsurvive.XSurvive;
import net.luis.xsurvive.world.level.block.XSurviveBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class XSurviveBlockEntityTypes {
	
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, XSurvive.MOD_ID);
	
	
	public static final RegistryObject<BlockEntityType<SmeltingFurnaceBlockEntity>> SMELTING_FURNACE = BLOCK_ENTITIES.register("smelting_furnace", () -> {
		return BlockEntityType.Builder.of(SmeltingFurnaceBlockEntity::new, XSurviveBlocks.SMELTING_FURNACE.get()).build(null);
	});
	
}
