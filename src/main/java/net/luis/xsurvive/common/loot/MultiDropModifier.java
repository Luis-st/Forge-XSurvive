package net.luis.xsurvive.common.loot;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;

import net.luis.xsurvive.XSurvive;
import net.luis.xsurvive.init.XSurviveEnchantments;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

public class MultiDropModifier extends LootModifier {

	public MultiDropModifier(LootItemCondition[] conditionsIn) {
		super(conditionsIn);
	}

	@Override
	protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		List<ItemStack> loot = Lists.newArrayList();
		generatedLoot.forEach((stack) -> {
			int level = 0;
			if (context.hasParam(LootContextParams.TOOL)) { // TODO: fix error & test count
				level = EnchantmentHelper.getItemEnchantmentLevel(XSurviveEnchantments.MULTI_DROP.get(), context.getParam(LootContextParams.TOOL));
			} else {
				XSurvive.LOGGER.error("Could not apply the MultiDrop logic, since there is no Tool or LivingEntity in the LootContext present");
			}
			if (MultiDropModifier.this.canItemMultiDrop(stack)) { // report this bug
				if (level == 0) {
					loot.add(stack);
				} else if (level == 1) {
					loot.add(stack);
					loot.add(stack);
				} else if (level == 2) {
					loot.add(stack);
					loot.add(stack);
					loot.add(stack);
				} else if (level == 3) {
					loot.add(stack);
					loot.add(stack);
					loot.add(stack);
					loot.add(stack);
				} else {
					XSurvive.LOGGER.error("Could not apply the MultiDrop logic, since the Enchantment level cannot be larger than 3 but it is {}", level);
				}
//				for (int i = 0; i <= level; i++) {
//					loot.add(stack);
//				}
			}
		});
		return loot;
	}
	
	protected boolean canItemMultiDrop(ItemStack stack) { // TODO: test for nature blocks
		return true;
	}
	
	public static class Serializer extends GlobalLootModifierSerializer<MultiDropModifier> {

		@Override
		public MultiDropModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] lootcondition) {
			return new MultiDropModifier(lootcondition);
		}

		@Override
		public JsonObject write(MultiDropModifier instance) {
			return makeConditions(instance.conditions);
		}
		
	}
	
}
