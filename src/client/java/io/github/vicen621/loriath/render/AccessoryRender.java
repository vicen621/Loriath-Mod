package io.github.vicen621.loriath.render;

import dev.emi.trinkets.api.client.TrinketRenderer;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import dev.emi.trinkets.api.SlotReference;
import io.github.vicen621.loriath.item.trinkets.hats.HatItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class AccessoryRender implements TrinketRenderer {

    @Override
    public void render(ItemStack itemStack, SlotReference slotReference, EntityModel<? extends LivingEntity> entityModel, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (entity instanceof AbstractClientPlayerEntity player) {
            TrinketRenderer.translateToFace(matrixStack, (PlayerEntityModel<AbstractClientPlayerEntity>) entityModel, player, headYaw, headPitch);
            matrixStack.scale(-1f,-1f,1f);
            matrixStack.translate(0,0.7,0.3f);
            MinecraftClient.getInstance().getItemRenderer().renderItem(itemStack, ModelTransformationMode.FIXED, false, matrixStack, vertexConsumerProvider, light, OverlayTexture.DEFAULT_UV, MinecraftClient.getInstance().getItemRenderer().getModel(itemStack, entity.getWorld(), entity, 0));
        }
    }

    public static void registerRender(HatItem item) {
        TrinketRendererRegistry.registerRenderer(item, new AccessoryRender());
    }
}