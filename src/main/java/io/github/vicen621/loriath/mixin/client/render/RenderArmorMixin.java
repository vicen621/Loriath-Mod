package io.github.vicen621.loriath.mixin.client.render;

import io.github.vicen621.loriath.datagen.providers.LoriathItemProvider;
import io.github.vicen621.loriath.utils.TrinketsHelper;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorFeatureRenderer.class)
public abstract class RenderArmorMixin<T extends LivingEntity, M extends BipedEntityModel<T>, A extends BipedEntityModel<T>> {

	@Inject(method = "renderArmor", at = @At("HEAD"), cancellable = true)
	private void onRenderArmor(MatrixStack matrices, VertexConsumerProvider vertexConsumers, T entity,
							   EquipmentSlot armorSlot, int light, A model, CallbackInfo ci) {
		// if rendering player's head armor
		if (entity instanceof PlayerEntity && armorSlot == EquipmentSlot.HEAD) {
			// cancel if player has an item in hats slot
			if (TrinketsHelper.isEquipped(item -> item.isIn(LoriathItemProvider.HATS), entity))
				ci.cancel();
		}
	}

}