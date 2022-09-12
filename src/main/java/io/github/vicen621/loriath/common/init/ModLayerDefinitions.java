package io.github.vicen621.loriath.common.init;

import io.github.vicen621.loriath.LoriathMod;
import io.github.vicen621.loriath.client.render.accessory.AccessoryLayers;
import io.github.vicen621.loriath.client.render.accessory.model.ArmsModel;
import io.github.vicen621.loriath.client.render.accessory.model.HeadModel;
import io.github.vicen621.loriath.client.render.accessory.model.NecklaceModel;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModelLayer;

public class ModLayerDefinitions {

    public static void registerAll() {
        register(AccessoryLayers.PANIC_NECKLACE, layer(NecklaceModel.createPanicNecklace(), 64, 48));
        register(AccessoryLayers.LAVA_CHARM, layer(NecklaceModel.createLavaCharm(), 64, 48));
        register(AccessoryLayers.DIVING_GEAR, layer(HeadModel.createDivingGear(), 64, 32));

        register(AccessoryLayers.CLAWS, layer(ArmsModel.createClaws(false), 32, 16));
        register(AccessoryLayers.SLIM_CLAWS, layer(ArmsModel.createClaws(true), 32, 16));
    }

    private static EntityModelLayerRegistry.TexturedModelDataProvider layer(ModelData mesh, int textureWidth, int textureHeight) {
        return () -> TexturedModelData.of(mesh, textureWidth, textureHeight);
    }

    private static void register(EntityModelLayer location, EntityModelLayerRegistry.TexturedModelDataProvider provider) {
        EntityModelLayerRegistry.registerModelLayer(location, provider);
    }


    public static void registerModLayerDefinitions() {
        LoriathMod.LOGGER.info("Registering Layer Definitions for " + LoriathMod.MODID);
    }
}