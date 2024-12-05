package io.github.vicen621.loriath.render.accessory;

import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import io.github.vicen621.loriath.Loriath;
import io.github.vicen621.loriath.common.init.ModItems;
import io.github.vicen621.loriath.render.accessory.model.ArmsModel;
import io.github.vicen621.loriath.render.accessory.model.HeadModel;
import io.github.vicen621.loriath.render.accessory.model.NecklaceModel;
import io.github.vicen621.loriath.render.accessory.renderer.AccessoryRenderer;
import io.github.vicen621.loriath.render.accessory.renderer.GloveAccessoryRenderer;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

public class AccessoryRenderers implements SimpleSynchronousResourceReloadListener {

    public static ModelPart getModelPart(EntityModelLayer layerLocation) {
        return MinecraftClient.getInstance().getEntityModelLoader().getModelPart(layerLocation);
    }

    @Override
    public void reload(ResourceManager resourceManager) {
        TrinketRendererRegistry.registerRenderer(ModItems.DIGGING_CLAWS, new GloveAccessoryRenderer("digging_claws", "digging_claws", ArmsModel.createClawsModel(false), ArmsModel.createClawsModel(true)));
        TrinketRendererRegistry.registerRenderer(ModItems.PANIC_NECKLACE, new AccessoryRenderer("panic_necklace", new NecklaceModel(getModelPart(AccessoryLayers.PANIC_NECKLACE))));
        TrinketRendererRegistry.registerRenderer(ModItems.LAVA_CHARM, new AccessoryRenderer("lava_charm", new NecklaceModel(getModelPart(AccessoryLayers.LAVA_CHARM))));
        TrinketRendererRegistry.registerRenderer(ModItems.DIVING_GEAR, new AccessoryRenderer("diving_gear", new HeadModel(getModelPart(AccessoryLayers.DIVING_GEAR), RenderLayer::getEntityTranslucent)));
    }

    @Override
    public Identifier getFabricId() {
        return Loriath.id("trinket_renderers");
    }
}
