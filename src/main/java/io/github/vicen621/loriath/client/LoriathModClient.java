package io.github.vicen621.loriath.client;

import io.github.vicen621.loriath.common.item.accessories.items.extra.Dash;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class LoriathModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        Dash.DASH_KEYBIND = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.loriath.dash", GLFW.GLFW_KEY_Z, "key.categories.movement"));
    }
}
