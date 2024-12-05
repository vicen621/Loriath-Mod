package io.github.vicen621.loriath.render;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

import java.util.function.Function;

@FunctionalInterface
public interface RenderLayerFactory extends Function<Identifier, RenderLayer> {
}
