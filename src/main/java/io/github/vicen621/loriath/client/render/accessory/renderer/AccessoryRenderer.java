package io.github.vicen621.loriath.client.render.accessory.renderer;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.client.TrinketRenderer;
import io.github.vicen621.loriath.LoriathMod;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class AccessoryRenderer implements TrinketRenderer {

    private final Identifier texture;
    private final BipedEntityModel<LivingEntity> model;

    public AccessoryRenderer(String texturePath, BipedEntityModel<LivingEntity> model) {
        this(LoriathMod.id(String.format("textures/entity/accessory/%s.png", texturePath)), model);
    }

    public AccessoryRenderer(Identifier texture, BipedEntityModel<LivingEntity> model) {
        this.texture = texture;
        this.model = model;
    }

    protected Identifier getTexture() {
        return texture;
    }

    protected BipedEntityModel<LivingEntity> getModel() {
        return model;
    }

    @Override
    public final void render(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, MatrixStack MatrixStack, VertexConsumerProvider VertexConsumerProvider, int light, LivingEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        BipedEntityModel<LivingEntity> model = getModel();

        model.setAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        model.animateModel(entity, limbSwing, limbSwingAmount, partialTicks);
        TrinketRenderer.followBodyRotations(entity, model);
        render(MatrixStack, VertexConsumerProvider, light, stack.hasGlint());
    }

    protected void render(MatrixStack matrixStack, VertexConsumerProvider buffer, int light, boolean hasFoil) {
        RenderLayer renderType = model.getLayer(getTexture());
        VertexConsumer vertexBuilder = ItemRenderer.getItemGlintConsumer(buffer, renderType, false, hasFoil);
        model.render(matrixStack, vertexBuilder, light, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
    }
}
