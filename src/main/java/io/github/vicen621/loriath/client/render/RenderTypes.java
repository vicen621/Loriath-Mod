package io.github.vicen621.loriath.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.function.Function;

@Environment(EnvType.CLIENT)
public abstract class RenderTypes extends RenderLayer {

	public RenderTypes(String string, VertexFormat vertexFormat, VertexFormat.DrawMode mode, int i, boolean bl, boolean bl2, Runnable runnable, Runnable runnable2) {
		super(string, vertexFormat, mode, i, bl, bl2, runnable, runnable2);
		throw new IllegalStateException("This class must not be instantiated");
	}

	// See ForgeRenderTypes#getUnlitTranslucent
	private static final Function<Identifier, RenderLayer> UNLIT_TRANSLUCENT = Util.memoize(Util.memoize(textureLocation -> {
		MultiPhaseParameters renderState = MultiPhaseParameters.builder()
				// TODO (from forge): .setShaderState(RENDERTYPE_ENTITY_TRANSLUCENT_UNLIT_SHADER)
				.shader(ENTITY_CUTOUT_NONULL_SHADER)
				.texture(new Texture(textureLocation, false, false))
				.transparency(TRANSLUCENT_TRANSPARENCY)
				.cull(DISABLE_CULLING)
				.lightmap(ENABLE_LIGHTMAP)
				.overlay(ENABLE_OVERLAY_COLOR)
				.build(true);
		return of("artifacts_entity_unlit", VertexFormats.POSITION_COLOR_TEXTURE_OVERLAY_LIGHT_NORMAL, VertexFormat.DrawMode.QUADS, 256, true, true, renderState);
	}));

	public static RenderLayer unlit(Identifier textureLocation) {
		return UNLIT_TRANSLUCENT.apply(textureLocation);
	}
}