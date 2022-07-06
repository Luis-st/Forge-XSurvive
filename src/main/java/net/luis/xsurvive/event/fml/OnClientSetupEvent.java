package net.luis.xsurvive.event.fml;

import net.luis.xsurvive.XSurvive;
import net.luis.xsurvive.client.DoubleRangeOption;
import net.luis.xsurvive.client.renderer.gui.overlay.FrostMobEffectOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = XSurvive.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class OnClientSetupEvent {

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		Minecraft minecraft = Minecraft.getInstance();
		OverlayRegistry.registerOverlayAbove(ForgeIngameGui.FROSTBITE_ELEMENT, "Frost Mob Effect Overlay", new FrostMobEffectOverlay(minecraft));
		replaceGammaOption(minecraft);
		XSurvive.LOGGER.info("Replace gamma option and reload options");
		minecraft.options.load();
		XSurvive.LOGGER.info("Gamma value is now {}", minecraft.options.gamma.get().doubleValue());
	}

	private static void replaceGammaOption(Minecraft minecraft) {
		minecraft.options.gamma = new OptionInstance<Double>("options.gamma", OptionInstance.noTooltip(), (p_231913_, p_231914_) -> {
			int i = (int) (p_231914_ * 100.0D);
			if (i == 0) {
				return Options.genericValueLabel(p_231913_, Component.translatable("options.gamma.min"));
			} else if (i == 50) {
				return Options.genericValueLabel(p_231913_, Component.translatable("options.gamma.default"));
			} else {
				return i == 100 ? Options.genericValueLabel(p_231913_, Component.translatable("options.gamma.max")) : Options.genericValueLabel(p_231913_, i);
			}
		}, new DoubleRangeOption(0.0, 100.0), 0.5, (value) -> {
			
		});
	}

}
