package io.github.vicen621.loriath.client;

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
import net.minecraft.block.TorchBlock;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.resource.ResourceType;
import net.minecraft.screen.PlayerScreenHandler;
import org.lwjgl.glfw.GLFW;

public class LoriathModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        Dash.DASH_KEYBIND = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.loriath.dash", GLFW.GLFW_KEY_Z, "key.categories.movement"));

        //phoenix particle
        registerParticle(ModParticles.PHOENIX, PhoenixParticle.Factory::new, "phoenix");

        //overspeed particle
        registerParticle(ModParticles.OVERSPEED, FlameParticle.Factory::new, "overspeed");

        //second overspeed particle
        registerParticle(ModParticles.SECOND_OVERSPEED, FlameParticle.Factory::new, "second_overspeed");

        //mistery box particle
        registerParticle(ModParticles.MISTERY_BOX, FlameParticle.Factory::new, "mistery_box");

        ModLayerDefinitions.registerAll();
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new AccessoryRenderers());
    }

    public <T extends ParticleEffect> void registerParticle(ParticleType<T> type, ParticleFactoryRegistry.PendingParticleFactory<T> factory, String particle) {
        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register(((atlasTexture, registry) -> {
            registry.register(LoriathMod.id("particle/" + particle));
        }));
        ParticleFactoryRegistry.getInstance().register(type, factory);
    }
}
