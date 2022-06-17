package io.github.vicen621.loriath.client;

import com.mojang.bridge.game.PackType;
import io.github.vicen621.loriath.LoriathMod;
import io.github.vicen621.loriath.client.render.accessory.AccessoryRenderers;
import io.github.vicen621.loriath.common.init.ModLayerDefinitions;
import io.github.vicen621.loriath.common.init.ModParticles;
import io.github.vicen621.loriath.common.item.accessories.items.extra.Dash;
import io.github.vicen621.loriath.common.particle.PhoenixParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.resource.ResourceType;
import net.minecraft.screen.PlayerScreenHandler;
import org.lwjgl.glfw.GLFW;

public class LoriathModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        Dash.DASH_KEYBIND = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.loriath.dash", GLFW.GLFW_KEY_Z, "key.categories.movement"));

        //phoenix particle
        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register(((atlasTexture, registry) -> {
            registry.register(LoriathMod.id("particle/phoenix"));
        }));
        ParticleFactoryRegistry.getInstance().register(ModParticles.PHOENIX, PhoenixParticle.Factory::new);

        //overspeed particle
        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register(((atlasTexture, registry) -> {
            registry.register(LoriathMod.id("particle/overspeed"));
        }));
        ParticleFactoryRegistry.getInstance().register(ModParticles.OVERSPEED, FlameParticle.Factory::new);

        //second overspeed particle
        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register(((atlasTexture, registry) -> {
            registry.register(LoriathMod.id("particle/second_overspeed"));
        }));
        ParticleFactoryRegistry.getInstance().register(ModParticles.SECOND_OVERSPEED, FlameParticle.Factory::new);

        //mistery box particle
        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register(((atlasTexture, registry) -> {
            registry.register(LoriathMod.id("particle/mistery_box"));
        }));
        ParticleFactoryRegistry.getInstance().register(ModParticles.MISTERY_BOX, FlameParticle.Factory::new);

        ModLayerDefinitions.registerAll();
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new AccessoryRenderers());
    }
}
