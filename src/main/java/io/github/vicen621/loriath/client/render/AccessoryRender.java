package io.github.vicen621.loriath.client.render;

import dev.emi.trinkets.api.client.TrinketRenderer;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import com.mojang.blaze3d.platform.GlStateManager;
import dev.emi.trinkets.api.SlotReference;
import io.github.vicen621.loriath.common.item.trinkets.hats.HatItem;
import dev.emi.trinkets.api.client.TrinketRenderer;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import io.github.vicen621.loriath.common.init.ModHats;
import io.github.vicen621.loriath.common.init.ModItems;
import io.github.vicen621.loriath.common.item.trinkets.TrinketItem;
import io.github.vicen621.loriath.utils.TrinketsHelper;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;

public class AccessoryRender implements TrinketRenderer {

    @Override
    public void render(ItemStack itemStack, SlotReference slotReference, EntityModel<? extends LivingEntity> entityModel, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (entity instanceof AbstractClientPlayerEntity player) {
            TrinketsHelper.translateToFace(matrixStack, entityModel, player, headYaw, headPitch);
            matrixStack.scale(-1f,-1f,1f);
            matrixStack.translate(0,0.7,0.3f);
            MinecraftClient.getInstance().getItemRenderer().renderItem(itemStack, ModelTransformation.Mode.FIXED, light, OverlayTexture.DEFAULT_UV, matrixStack, vertexConsumerProvider, 0);
        }
    }

    public static void registerRender(HatItem item) {
        TrinketRendererRegistry.registerRenderer(item, new AccessoryRender());
    }
}