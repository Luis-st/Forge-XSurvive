package net.luis.xsurvive.client.render.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat.Mode;

import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import net.luis.xsurvive.XSurvive;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;

public abstract class XSurviveRenderType extends RenderType {

	protected XSurviveRenderType(String name, VertexFormat format, Mode mode, int bufferSize, boolean affectsCrumbling, boolean sortOnUpload, Runnable setupState, Runnable clearState) {
		super(name, format, mode, bufferSize, affectsCrumbling, sortOnUpload, setupState, clearState);
	}

	public static List<RenderType> glint = newRenderList(XSurviveRenderType::glintRenderType);
	public static List<RenderType> glintTranslucent = newRenderList(XSurviveRenderType::glintTranslucentRenderType);
	public static List<RenderType> entityGlint = newRenderList(XSurviveRenderType::entityGlintRenderType);
	public static List<RenderType> glintDirect = newRenderList(XSurviveRenderType::glintDirectRenderType);
	public static List<RenderType> entityGlintDirect = newRenderList(XSurviveRenderType::entityGlintDriectRenderType);
	public static List<RenderType> armorGlint = newRenderList(XSurviveRenderType::armorGlintRenderType);
	public static List<RenderType> armorEntityGlint = newRenderList(XSurviveRenderType::armorEntityGlintRenderType);

	public static void addGlintTypes(Object2ObjectLinkedOpenHashMap<RenderType, BufferBuilder> map) {
		addGlintTypes(map, glint);
		addGlintTypes(map, glintTranslucent);
		addGlintTypes(map, entityGlint);
		addGlintTypes(map, glintDirect);
		addGlintTypes(map, entityGlintDirect);
		addGlintTypes(map, armorGlint);
		addGlintTypes(map, armorEntityGlint);
	}

	protected static List<RenderType> newRenderList(Function<String, RenderType> function) {
		ArrayList<RenderType> list = Lists.newArrayList();
		for (DyeColor color : DyeColor.values()) {
			list.add(function.apply(color.getName()));
		}
		list.add(function.apply("rainbow"));
		list.add(function.apply("blank"));
		return list;
	}

	protected static void addGlintTypes(Object2ObjectLinkedOpenHashMap<RenderType, BufferBuilder> map, List<RenderType> renderTypes) {
		for (RenderType renderType : renderTypes) {
			if (!map.containsKey(renderType)) {
				map.put(renderType, new BufferBuilder(renderType.bufferSize()));
			}
		}
	}

	protected static RenderType glintRenderType(String name) {
		return RenderType.create("glint_" + name, DefaultVertexFormat.POSITION_TEX, VertexFormat.Mode.QUADS, 256, false, false,
			CompositeState.builder().setShaderState(RenderStateShard.RENDERTYPE_GLINT_SHADER).setTextureState(new TextureStateShard(texture(name), true, false)).setWriteMaskState(RenderStateShard.COLOR_WRITE).setCullState(RenderStateShard.NO_CULL)
				.setDepthTestState(RenderStateShard.EQUAL_DEPTH_TEST).setTransparencyState(RenderStateShard.GLINT_TRANSPARENCY).setOutputState(RenderStateShard.ITEM_ENTITY_TARGET).setTexturingState(RenderStateShard.GLINT_TEXTURING)
				.createCompositeState(false));
	}

	protected static RenderType glintTranslucentRenderType(String name) {
		return RenderType.create("glint_translucent_" + name, DefaultVertexFormat.POSITION_TEX, VertexFormat.Mode.QUADS, 256, false, false,
			CompositeState.builder().setShaderState(RenderStateShard.RENDERTYPE_GLINT_TRANSLUCENT_SHADER).setTextureState(new TextureStateShard(texture(name), true, false)).setWriteMaskState(RenderStateShard.COLOR_WRITE)
				.setCullState(RenderStateShard.NO_CULL).setDepthTestState(RenderStateShard.EQUAL_DEPTH_TEST).setTransparencyState(RenderStateShard.GLINT_TRANSPARENCY).setOutputState(RenderStateShard.ITEM_ENTITY_TARGET)
				.setTexturingState(RenderStateShard.GLINT_TEXTURING).createCompositeState(false));
	}

	protected static RenderType entityGlintRenderType(String name) {
		return RenderType.create("entity_glint_" + name, DefaultVertexFormat.POSITION_TEX, VertexFormat.Mode.QUADS, 256, false, false,
			CompositeState.builder().setShaderState(RenderStateShard.RENDERTYPE_ENTITY_GLINT_SHADER).setTextureState(new TextureStateShard(texture(name), true, false)).setWriteMaskState(RenderStateShard.COLOR_WRITE)
				.setCullState(RenderStateShard.NO_CULL).setDepthTestState(RenderStateShard.EQUAL_DEPTH_TEST).setTransparencyState(RenderStateShard.GLINT_TRANSPARENCY).setOutputState(RenderStateShard.ITEM_ENTITY_TARGET)
				.setTexturingState(RenderStateShard.ENTITY_GLINT_TEXTURING).createCompositeState(false));
	}

	protected static RenderType glintDirectRenderType(String name) {
		return RenderType.create("glint_direct_" + name, DefaultVertexFormat.POSITION_TEX, VertexFormat.Mode.QUADS, 256, false, false,
			CompositeState.builder().setShaderState(RenderStateShard.RENDERTYPE_GLINT_DIRECT_SHADER).setTextureState(new TextureStateShard(texture(name), true, false)).setWriteMaskState(RenderStateShard.COLOR_WRITE)
				.setCullState(RenderStateShard.NO_CULL).setDepthTestState(RenderStateShard.EQUAL_DEPTH_TEST).setTransparencyState(RenderStateShard.GLINT_TRANSPARENCY).setTexturingState(RenderStateShard.GLINT_TEXTURING).createCompositeState(false));
	}

	protected static RenderType entityGlintDriectRenderType(String name) {
		return RenderType.create("entity_glint_direct_" + name, DefaultVertexFormat.POSITION_TEX, VertexFormat.Mode.QUADS, 256, false, false,
			CompositeState.builder().setShaderState(RenderStateShard.RENDERTYPE_ENTITY_GLINT_DIRECT_SHADER).setTextureState(new TextureStateShard(texture(name), true, false)).setWriteMaskState(RenderStateShard.COLOR_WRITE)
				.setCullState(RenderStateShard.NO_CULL).setDepthTestState(RenderStateShard.EQUAL_DEPTH_TEST).setTransparencyState(RenderStateShard.GLINT_TRANSPARENCY).setTexturingState(RenderStateShard.ENTITY_GLINT_TEXTURING)
				.createCompositeState(false));
	}

	protected static RenderType armorGlintRenderType(String name) {
		return RenderType.create("armor_glint_" + name, DefaultVertexFormat.POSITION_TEX, VertexFormat.Mode.QUADS, 256, false, false,
			CompositeState.builder().setShaderState(RenderStateShard.RENDERTYPE_ARMOR_GLINT_SHADER).setTextureState(new TextureStateShard(texture(name), true, false)).setWriteMaskState(RenderStateShard.COLOR_WRITE)
				.setCullState(RenderStateShard.NO_CULL).setDepthTestState(RenderStateShard.EQUAL_DEPTH_TEST).setTransparencyState(RenderStateShard.GLINT_TRANSPARENCY).setTexturingState(RenderStateShard.ENTITY_GLINT_TEXTURING)
				.setLayeringState(RenderStateShard.VIEW_OFFSET_Z_LAYERING).createCompositeState(false));
	}

	protected static RenderType armorEntityGlintRenderType(String name) {
		return RenderType.create("armor_entity_glint_" + name, DefaultVertexFormat.POSITION_TEX, VertexFormat.Mode.QUADS, 256, false, false,
			CompositeState.builder().setShaderState(RenderStateShard.RENDERTYPE_ARMOR_ENTITY_GLINT_SHADER).setTextureState(new TextureStateShard(texture(name), true, false)).setWriteMaskState(RenderStateShard.COLOR_WRITE)
				.setCullState(RenderStateShard.NO_CULL).setDepthTestState(RenderStateShard.EQUAL_DEPTH_TEST).setTransparencyState(RenderStateShard.GLINT_TRANSPARENCY).setTexturingState(RenderStateShard.ENTITY_GLINT_TEXTURING)
				.setLayeringState(RenderStateShard.VIEW_OFFSET_Z_LAYERING).createCompositeState(false));
	}

	protected static ResourceLocation texture(String name) {
		return new ResourceLocation(XSurvive.MOD_ID, "textures/glint/enchanted_item_glint_" + name + ".png");
	}

}
