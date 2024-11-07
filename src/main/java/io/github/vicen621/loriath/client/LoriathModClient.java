package io.github.vicen621.loriath.client;

import io.github.vicen621.loriath.LoriathMod;
import io.github.vicen621.loriath.client.render.AccessoryRender;
import io.github.vicen621.loriath.client.render.accessory.AccessoryRenderers;
import io.github.vicen621.loriath.common.init.ModBlocks;
import io.github.vicen621.loriath.common.init.ModHats;
import io.github.vicen621.loriath.common.init.ModLayerDefinitions;
import io.github.vicen621.loriath.common.init.ModParticles;
import io.github.vicen621.loriath.common.item.trinkets.accessories.items.extra.Dash;
import io.github.vicen621.loriath.common.item.trinkets.hats.HatItem;
import io.github.vicen621.loriath.common.particle.PhoenixParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.ResourceType;
import net.minecraft.screen.PlayerScreenHandler;
import org.lwjgl.glfw.GLFW;

public class LoriathModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // Dash.DASH_KEYBIND = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.loriath.dash", GLFW.GLFW_KEY_Z, "key.categories.movement"));

        //phoenix particle
        registerParticle(ModParticles.PHOENIX, PhoenixParticle.Factory::new, "phoenix");

        //overspeed particle
        registerParticle(ModParticles.OVERSPEED, FlameParticle.Factory::new, "overspeed");

        //second overspeed particle
        registerParticle(ModParticles.SECOND_OVERSPEED, FlameParticle.Factory::new, "second");

        //mystery box particle
        registerParticle(ModParticles.MYSTERY_BOX, FlameParticle.Factory::new, "mystery_box");

        // BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.INFINITE_TORCH, RenderLayer.getCutout());
        // BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.INFINITE_WALL_TORCH, RenderLayer.getCutout());

        ModLayerDefinitions.registerAll();
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new AccessoryRenderers());

        // Register renderer for Hats only on the client
        for (Item item : ModHats.HATS) {
            if (item instanceof HatItem hat)
                AccessoryRender.registerRender(hat);
        }
    }

    public <T extends ParticleEffect> void registerParticle(ParticleType<T> type, ParticleFactoryRegistry.PendingParticleFactory<T> factory, String particle) {
        ParticleFactoryRegistry.getInstance().register(type, factory);
    }
}
