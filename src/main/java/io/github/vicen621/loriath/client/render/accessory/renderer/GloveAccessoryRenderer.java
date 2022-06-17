package io.github.vicen621.loriath.client.render.accessory.renderer;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.client.TrinketRenderer;
import io.github.vicen621.loriath.LoriathMod;
import io.github.vicen621.loriath.client.render.accessory.model.ArmsModel;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;


public class GloveAccessoryRenderer implements TrinketRenderer {

    private final Identifier defaultTexture;
    private final Identifier slimTexture;
    private final ArmsModel defaultModel;
    private final ArmsModel slimModel;

    public GloveAccessoryRenderer(String name, ArmsModel defaultModel, ArmsModel slimModel) {
        this(String.format("glove/%s/%s_default", name, name), String.format("glove/%s/%s_slim", name, name), defaultModel, slimModel);
    }

    public GloveAccessoryRenderer(String defaultTexturePath, String slimTexturePath, ArmsModel defaultModel, ArmsModel slimModel) {
        this.defaultTexture = LoriathMod.id(String.format("textures/entity/accessory/%s.png", defaultTexturePath));
        this.slimTexture = LoriathMod.id(String.format("textures/entity/accessory/%s.png", slimTexturePath));
        this.defaultModel = defaultModel;
        this.slimModel = slimModel;
    }

    protected static boolean hasSlimArms(Entity entity) {
        return entity instanceof AbstractClientPlayerEntity player && player.getModel().equals("slim");
    }

    protected Identifier getTexture(boolean hasSlimArms) {
        return hasSlimArms ? slimTexture : defaultTexture;
    }

    protected ArmsModel getModel(boolean hasSlimArms) {
        return hasSlimArms ? slimModel : defaultModel;
    }

    @Override
    public final void render(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, MatrixStack MatrixStack, VertexConsumerProvider VertexConsumerProvider, int light, LivingEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        boolean hasSlimArms = hasSlimArms(entity);
        ArmsModel model = getModel(hasSlimArms);

        model.setAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        model.animateModel(entity, limbSwing, limbSwingAmount, partialTicks);
        TrinketRenderer.followBodyRotations(entity, model);

        renderArm(model, MatrixStack, VertexConsumerProvider, entity.getMainArm(), light, hasSlimArms, stack.hasGlint());
    }

    protected void renderArm(ArmsModel model, MatrixStack matrixStack, VertexConsumerProvider buffer, Arm handSide, int light, boolean hasSlimArms, boolean hasFoil) {
        RenderLayer renderType = model.getLayer(getTexture(hasSlimArms));
        VertexConsumer vertexBuilder = ItemRenderer.getItemGlintConsumer(buffer, renderType, false, hasFoil);
        model.renderArm(handSide, matrixStack, vertexBuilder, light, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
    }

    public final void renderFirstPersonArm(MatrixStack matrixStack, VertexConsumerProvider buffer, int light, AbstractClientPlayerEntity player, Arm side, boolean hasFoil) {
        if (!player.isSpectator()) {
            boolean hasSlimArms = hasSlimArms(player);
            ArmsModel model = getModel(hasSlimArms);

            ModelPart arm = side == Arm.LEFT ? model.leftArm : model.rightArm;

            model.setVisible(false);
            arm.visible = true;

            model.sneaking = false;
            model.handSwingProgress = model.leaningPitch = 0;
            model.setAngles(player, 0, 0, 0, 0, 0);
            arm.pitch = 0;

            renderFirstPersonArm(model, arm, matrixStack, buffer, light, hasSlimArms, hasFoil);
        }
    }

    protected void renderFirstPersonArm(ArmsModel model, ModelPart arm, MatrixStack matrixStack, VertexConsumerProvider buffer, int light, boolean hasSlimArms, boolean hasFoil) {
        RenderLayer renderType = model.getLayer(getTexture(hasSlimArms));
        VertexConsumer builder = ItemRenderer.getItemGlintConsumer(buffer, renderType, false, hasFoil);
        arm.render(matrixStack, builder, light, OverlayTexture.DEFAULT_UV);
    }
}
