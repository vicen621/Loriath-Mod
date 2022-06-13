package io.github.vicen621.loriath.client;

import io.github.vicen621.loriath.LoriathMod;
import io.github.vicen621.loriath.common.init.ModParticles;
import io.github.vicen621.loriath.common.item.accessories.items.extra.Dash;
import io.github.vicen621.loriath.common.particle.PhoenixParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.screen.PlayerScreenHandler;
import org.lwjgl.glfw.GLFW;

public class LoriathModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        Dash.DASH_KEYBIND = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.loriath.dash", GLFW.GLFW_KEY_Z, "key.categories.movement"));

        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register(((atlasTexture, registry) -> {
            registry.register(LoriathMod.id("particle/phoenix"));
        }));

        ParticleFactoryRegistry.getInstance().register(ModParticles.PHOENIX, PhoenixParticle.Factory::new);
    }
}
