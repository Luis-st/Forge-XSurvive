package net.luis.xsurvive.common.loot;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.luis.xsurvive.XSurvive;
import net.luis.xsurvive.init.XSurviveEnchantments;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
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
	protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
		ObjectArrayList<ItemStack> loot = new ObjectArrayList<>();
		generatedLoot.forEach((stack) -> {
			if (context.hasParam(LootContextParams.TOOL)) {
				loot.addAll(MultiDropModifier.this.multiplyItem(stack, context.getParam(LootContextParams.TOOL).getEnchantmentLevel(XSurviveEnchantments.MULTI_DROP.get())));
			} else {
				XSurvive.LOGGER.error("Could not apply the MultiDrop logic, since there is no Tool in the LootContext present");
			}
		});
		return null;
	}
	
	protected List<ItemStack> multiplyItem(ItemStack stack, int level) {
		List<ItemStack> loot = Lists.newArrayList();
		if (level == 0) {
			loot.add(stack);
		} else if (!stack.isStackable()) {
			for (int i = 0; i <= level; i++) {
				loot.add(stack);
			}
		} else {
			int count = stack.getCount() * (level + 1);
			if (count > 64) {
				while (count > 0) {
					if (count > 64) {
						loot.add(this.copy(stack, 64));
						count -= 64;
					} else {
						loot.add(this.copy(stack, count));
						count = 0;
					}
				}
			} else {
				loot.add(this.copy(stack, count));
			}
		}
		return loot;
	}
	
	protected ItemStack copy(ItemStack stack, int count) {
		ItemStack lootStack = stack.copy();
		lootStack.setCount(count);
		return lootStack;
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
