package io.github.vicen621.loriath;

import io.github.vicen621.loriath.item.trinkets.accessories.ExtendableTooltipProvider;
import io.github.vicen621.loriath.particle.PhoenixParticle;
import io.github.vicen621.loriath.render.AccessoryRender;
import io.github.vicen621.loriath.render.accessory.AccessoryRenderers;
import io.github.vicen621.loriath.init.ModHats;
import io.github.vicen621.loriath.init.ModParticles;
import io.github.vicen621.loriath.item.trinkets.hats.HatItem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.resource.ResourceType;
import net.minecraft.text.Text;

public class LoriathClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		// Dash.DASH_KEYBIND = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.loriath.dash", GLFW.GLFW_KEY_Z, "key.categories.movement"));

		//phoenix particle
		ParticleFactoryRegistry.getInstance().register(ModParticles.PHOENIX, PhoenixParticle.Factory::new);

		//overspeed particle
		registerParticle(ModParticles.OVERSPEED, FlameParticle.Factory::new);

		//second overspeed particle
		registerParticle(ModParticles.SECOND_OVERSPEED, FlameParticle.Factory::new);

		//mystery box particle
		registerParticle(ModParticles.MYSTERY_BOX, FlameParticle.Factory::new);

		LoriathLayerDefinitions.registerAll();
		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new AccessoryRenderers());

		// This event controls the extendable tooltip (shift tooltip function)
		ItemTooltipCallback.EVENT.register(((itemStack, tooltipContext, list) -> {
			Text TOOLTIP_HINT = Text.translatable("item.loriath.tooltip_hint");

			if (itemStack.getItem() instanceof ExtendableTooltipProvider extendableTooltip) {
				if (Screen.hasShiftDown()) {
					list.addAll(extendableTooltip.getExtendedTooltip());
				} else {
					list.add(TOOLTIP_HINT);
				}
			}
		}));

		// Register renderer for Hats only on the client
		for (Item item : ModHats.HATS) {
			if (item instanceof HatItem hat)
				AccessoryRender.registerRender(hat);
		}
	}

	public <T extends ParticleEffect> void registerParticle(ParticleType<T> type, ParticleFactoryRegistry.PendingParticleFactory<T> factory) {
		ParticleFactoryRegistry.getInstance().register(type, factory);
	}
}
