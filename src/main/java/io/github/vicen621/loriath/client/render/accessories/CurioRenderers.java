package io.github.vicen621.loriath.client.render.accessories;

import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import io.github.vicen621.loriath.LoriathMod;
import io.github.vicen621.loriath.client.render.accessories.model.ArmsModel;
import io.github.vicen621.loriath.client.render.accessories.model.NecklaceModel;
import io.github.vicen621.loriath.client.render.accessories.renderer.CurioRenderer;
import io.github.vicen621.loriath.client.render.accessories.renderer.GloveCurioRenderer;
import io.github.vicen621.loriath.common.init.ModItems;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

//TODO: Terminar de pasar las clases
public class CurioRenderers implements SimpleSynchronousResourceReloadListener {

    @Override
    public void reload(ResourceManager resourceManager) {
        // head
        /*TrinketRendererRegistry.registerRenderer(ModItems.PLASTIC_DRINKING_HAT, new CurioRenderer("drinking_hat/plastic_drinking_hat", new HeadModel(bakeLayer(CurioLayers.DRINKING_HAT))));
        TrinketRendererRegistry.registerRenderer(ModItems.NOVELTY_DRINKING_HAT, new CurioRenderer("drinking_hat/novelty_drinking_hat", new HeadModel(bakeLayer(CurioLayers.DRINKING_HAT))));
        TrinketRendererRegistry.registerRenderer(ModItems.SNORKEL, new CurioRenderer("snorkel", new HeadModel(bakeLayer(CurioLayers.SNORKEL), RenderType::entityTranslucent)));
        TrinketRendererRegistry.registerRenderer(ModItems.NIGHT_VISION_GOGGLES, new GlowingCurioRenderer("night_vision_goggles", new HeadModel(bakeLayer(CurioLayers.NIGHT_VISION_GOGGLES))));
        TrinketRendererRegistry.registerRenderer(ModItems.SUPERSTITIOUS_HAT, new CurioRenderer("superstitious_hat", new HeadModel(bakeLayer(CurioLayers.SUPERSTITIOUS_HAT), RenderType::entityCutoutNoCull)));
        TrinketRendererRegistry.registerRenderer(ModItems.VILLAGER_HAT, new CurioRenderer("villager_hat", new HeadModel(bakeLayer(CurioLayers.VILLAGER_HAT))));*/

        // necklace
        TrinketRendererRegistry.registerRenderer(ModItems.PANIC_NECKLACE, new CurioRenderer("panic_necklace", new NecklaceModel(bakeLayer(CurioLayers.PANIC_NECKLACE))));

        // belt
        /*TrinketRendererRegistry.registerRenderer(ModItems.CLOUD_IN_A_BOTTLE, new BeltCurioRenderer("cloud_in_a_bottle", BeltModel.createCloudInABottleModel()));
        TrinketRendererRegistry.registerRenderer(ModItems.OBSIDIAN_SKULL, new BeltCurioRenderer("obsidian_skull", BeltModel.createObsidianSkullModel()));
        TrinketRendererRegistry.registerRenderer(ModItems.ANTIDOTE_VESSEL, new BeltCurioRenderer("antidote_vessel", BeltModel.createAntidoteVesselModel()));
        TrinketRendererRegistry.registerRenderer(ModItems.UNIVERSAL_ATTRACTOR, new BeltCurioRenderer("universal_attractor", BeltModel.createUniversalAttractorModel()));
        TrinketRendererRegistry.registerRenderer(ModItems.CRYSTAL_HEART, new BeltCurioRenderer("crystal_heart", BeltModel.createCrystalHeartModel()));
        TrinketRendererRegistry.registerRenderer(ModItems.HELIUM_FLAMINGO, new CurioRenderer("helium_flamingo", BeltModel.createHeliumFlamingoModel()));*/

        // hands
        TrinketRendererRegistry.registerRenderer(ModItems.DIGGING_CLAWS, new GloveCurioRenderer("claws/digging_claws", "claws/digging_claws", ArmsModel.createClawsModel(false), ArmsModel.createClawsModel(true)));
        TrinketRendererRegistry.registerRenderer(ModItems.POCKET_PISTON, new GloveCurioRenderer("pocket_piston", ArmsModel.createGloveModel(false), ArmsModel.createGloveModel(true)));

        // feet
        /*TrinketRendererRegistry.registerRenderer(ModItems.AQUA_DASHERS, new CurioRenderer("aqua_dashers", new LegsModel(bakeLayer(CurioLayers.AQUA_DASHERS))));
        TrinketRendererRegistry.registerRenderer(ModItems.BUNNY_HOPPERS, new CurioRenderer("bunny_hoppers", new LegsModel(bakeLayer(CurioLayers.BUNNY_HOPPERS))));
        TrinketRendererRegistry.registerRenderer(ModItems.KITTY_SLIPPERS, new CurioRenderer("kitty_slippers", new LegsModel(bakeLayer(CurioLayers.KITTY_SLIPPERS))));
        TrinketRendererRegistry.registerRenderer(ModItems.RUNNING_SHOES, new CurioRenderer("running_shoes", new LegsModel(bakeLayer(CurioLayers.RUNNING_SHOES))));
        TrinketRendererRegistry.registerRenderer(ModItems.STEADFAST_SPIKES, new CurioRenderer("steadfast_spikes", new LegsModel(bakeLayer(CurioLayers.STEADFAST_SPIKES))));
        TrinketRendererRegistry.registerRenderer(ModItems.FLIPPERS, new CurioRenderer("flippers", new LegsModel(bakeLayer(CurioLayers.FLIPPERS))));

        // curio
        TrinketRendererRegistry.registerRenderer(ModItems.WHOOPEE_CUSHION, new CurioRenderer("whoopee_cushion", new HeadModel(bakeLayer(CurioLayers.WHOOPEE_CUSHION))));*/
    }

    public static ModelPart bakeLayer(EntityModelLayer layerLocation) {
        return MinecraftClient.getInstance().getEntityModelLoader().getModelPart(layerLocation);
    }

    @Override
    public Identifier getFabricId() {
        return LoriathMod.id("trinket_renderers");
    }
}
