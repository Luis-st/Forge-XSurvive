package net.luis.xsurvive.client.render.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;

import net.luis.xsurvive.client.capability.LocalPlayerCapabilityHandler;
import net.luis.xsurvive.common.capability.CapabilityUtil;
import net.luis.xsurvive.init.XSurviveMobEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.IIngameOverlay;

public class FrostMobEffectOverlay implements IIngameOverlay {
	
	protected static final ResourceLocation FROST_EFFECT = new ResourceLocation("textures/misc/powder_snow_outline.png");
	
	protected final Minecraft minecraft;
	
	public FrostMobEffectOverlay(Minecraft minecraft) {
		this.minecraft = minecraft;
	}
	
	@Override
	public void render(ForgeIngameGui gui, PoseStack poseStack, float partialTick, int width, int height) {
		Player player = this.minecraft.player;
		if (!this.minecraft.options.hideGui && player.hasEffect(XSurviveMobEffects.FROST.get()) && 0 >= player.getPercentFrozen()) {
			gui.setupOverlayRenderState(true, false);
//			LocalPlayerCapabilityHandler handler = CapabilityUtil.getLocalPlayer(player);
//			if (handler != null) {
//				this.renderFrostMobEffectOverlay(width, height, handler.getFrostPercent());
//			} else {
//				this.renderFrostMobEffectOverlay(width, height, 1.0F);
//			}
			// TODO: cap which sets the frostPercent
		}
	}
	
	protected void renderFrostMobEffectOverlay(int width, int height, float frostPercent) {
		RenderSystem.disableDepthTest();
		RenderSystem.depthMask(false);
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F >= frostPercent && frostPercent >= 0.0F ? frostPercent : 0.0F);
		RenderSystem.setShaderTexture(0, FROST_EFFECT);
		Tesselator tesselator = Tesselator.getInstance();
		BufferBuilder bufferbuilder = tesselator.getBuilder();
		bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
		bufferbuilder.vertex(0.0D, height, -90.0D).uv(0.0F, 1.0F).endVertex();
		bufferbuilder.vertex(width, height, -90.0D).uv(1.0F, 1.0F).endVertex();
		bufferbuilder.vertex(width, 0.0D, -90.0D).uv(1.0F, 0.0F).endVertex();
		bufferbuilder.vertex(0.0D, 0.0D, -90.0D).uv(0.0F, 0.0F).endVertex();
		tesselator.end();
		RenderSystem.depthMask(true);
		RenderSystem.enableDepthTest();
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
	}

}
