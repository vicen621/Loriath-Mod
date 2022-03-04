package io.github.vicen621.loriath.client;

import io.github.vicen621.loriath.LoriathMod;
import io.github.vicen621.loriath.accessories.Dash;
import io.netty.buffer.Unpooled;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import org.lwjgl.glfw.GLFW;

public class LoriathModCLient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        Dash.DASH_KEYBIND = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.loriath.dash", GLFW.GLFW_KEY_Z, "key.categories.movement"));
    }
}
