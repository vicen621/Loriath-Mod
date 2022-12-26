package io.github.vicen621.loriath.common.item.trinkets.hats;

import com.mojang.blaze3d.platform.GlStateManager;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.client.TrinketRenderer;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import io.github.vicen621.loriath.common.item.trinkets.TrinketItem;
import io.github.vicen621.loriath.utils.TrinketsHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class HatItem extends TrinketItem implements TrinketRenderer {

    public HatItem() {
        TrinketRendererRegistry.registerRenderer(this, this);
    }

    @Override
    public void render(ItemStack itemStack, SlotReference slotReference, EntityModel<? extends LivingEntity> entityModel, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (entity instanceof AbstractClientPlayerEntity player) {
            //TODO: Ver si hay alguna manera de auto-arreglar los hats
            TrinketsHelper.translateToFace(matrixStack, entityModel, player, headYaw, headPitch);
            matrixStack.scale(-1f,-1f,1f);
            matrixStack.translate(0,0.7,0.3f);
            MinecraftClient.getInstance().getItemRenderer().renderItem(itemStack, ModelTransformation.Mode.FIXED, light, OverlayTexture.DEFAULT_UV, matrixStack, vertexConsumerProvider, 0);
        }
    }
}
