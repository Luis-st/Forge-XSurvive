package net.luis.xsurvive.mixin;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.google.common.collect.Maps;

import net.luis.xsurvive.XSurvive;
import net.luis.xsurvive.world.item.EnchantedGoldenBookItem;
import net.luis.xsurvive.world.item.enchantment.IEnchantment;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.registries.ForgeRegistries;

@Mixin(EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin {
	
	@SuppressWarnings("deprecation")
	@Inject(method = "getEnchantments", at = @At("HEAD"), cancellable = true)
	private static void getEnchantments(ItemStack stack, CallbackInfoReturnable<Map<Enchantment, Integer>> callback) {
		if (stack.getItem() instanceof EnchantedGoldenBookItem) {
			Map<Enchantment, Integer> enchantments = Maps.newLinkedHashMap();
			CompoundTag tag = stack.getTag() != null ? stack.getTag().getCompound(XSurvive.MOD_NAME + "GoldenEnchantments") : null;
			if (tag != null) {
				enchantments.put(Registry.ENCHANTMENT.get(ResourceLocation.tryParse(tag.getString("enchantment"))), 1);
			}
			callback.setReturnValue(enchantments);
		}
	}
	
	@Inject(method = "setEnchantments", at = @At("TAIL"))
	private static void setEnchantments(Map<Enchantment, Integer> enchantments, ItemStack stack, CallbackInfo callback) {
		if (stack.getItem() instanceof EnchantedGoldenBookItem) {
			if (enchantments.size() == 1) {
				Enchantment enchantment = enchantments.keySet().stream().toList().get(0);
				if (enchantment instanceof IEnchantment ench) {
					if (ench.isAllowedOnGoldenBooks()) {
						CompoundTag tag = new CompoundTag();
						tag.putString("enchantment", ForgeRegistries.ENCHANTMENTS.getKey(enchantment).toString());
						stack.getOrCreateTag().put(XSurvive.MOD_NAME + "GoldenEnchantments", tag);
					} else {
						XSurvive.LOGGER.info("The Enchantment {} which should be set is no allowed on EnchantedGoldenBookItems", ForgeRegistries.ENCHANTMENTS.getKey(enchantment));
					}
				} else {
					XSurvive.LOGGER.error("Enchantment {} is not a instance of IEnchantment", ForgeRegistries.ENCHANTMENTS.getKey(enchantment));
				}
				
			} else {
				XSurvive.LOGGER.error("Fail to set the Enchantment (" + enchantments +") for a EnchantedGoldenBookItem, since the given Map size must be 1");
			}
			stack.removeTagKey("Enchantments");
		}
	}
	
	@SuppressWarnings("deprecation")
	@Inject(method = "enchantItem", at = @At("HEAD"), cancellable = true)
	private static void enchantItem(RandomSource rng, ItemStack stack, int cost, boolean treasure, CallbackInfoReturnable<ItemStack> callback) {
		if (stack.getItem() instanceof EnchantedGoldenBookItem) {
			List<Enchantment> enchantments = Registry.ENCHANTMENT.stream().filter((enchantment) -> {
				if (enchantment instanceof IEnchantment ench) {
					return ench.isAllowedOnGoldenBooks();
				}
				XSurvive.LOGGER.error("Enchantment {} is not a instance of IEnchantment", ForgeRegistries.ENCHANTMENTS.getKey(enchantment));
				return false;
			}).collect(Collectors.toList());
			EnchantmentHelper.setEnchantments(Map.of(enchantments.get(rng.nextInt(enchantments.size())), 1), stack);
			callback.setReturnValue(stack);
		}
	}
	
}
