package io.github.vicen621.loriath.render.accessory.model;

import com.google.common.collect.ImmutableList;
import io.github.vicen621.loriath.render.accessory.AccessoryLayers;
import io.github.vicen621.loriath.render.accessory.AccessoryRenderers;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ArmsModel extends BipedEntityModel<LivingEntity> {

    public ArmsModel(ModelPart part, Function<Identifier, RenderLayer> renderType) {
        super(part, renderType);
    }

    public ArmsModel(ModelPart part) {
        this(part, RenderLayer::getEntityCutoutNoCull);
    }

    public static ArmsModel createClawsModel(boolean smallArms) {
        return new ArmsModel(AccessoryRenderers.getModelPart(AccessoryLayers.claws(smallArms)));
    }

    public static ModelData createEmptyArms(ModelPartBuilder leftArm, ModelPartBuilder rightArm, boolean smallArms) {
        ModelData mesh = getModelData(Dilation.NONE, 0);

        mesh.getRoot().addChild(
                "left_arm",
                leftArm,
                ModelTransform.pivot(5, smallArms ? 2.5F : 2, 0)
        );
        mesh.getRoot().addChild(
                "right_arm",
                rightArm,
                ModelTransform.pivot(-5, smallArms ? 2.5F : 2, 0)
        );

        return mesh;
    }

    public static ModelData createArms(ModelPartBuilder leftArm, ModelPartBuilder rightArm, boolean smallArms) {
        leftArm.uv(0, 0);
        rightArm.uv(16, 0);
        addArms(leftArm, rightArm, new Dilation(0.5F), smallArms);

        return createEmptyArms(leftArm, rightArm, smallArms);
    }

    public static ModelData createSleevedArms(ModelPartBuilder leftArm, ModelPartBuilder rightArm, boolean smallArms) {
        leftArm.uv(0, 16);
        rightArm.uv(16, 16);
        addArms(leftArm, rightArm, new Dilation(0.75F), smallArms);

        return createArms(leftArm, rightArm, smallArms);
    }

    public static ModelData createSleevedArms(boolean smallArms) {
        return createSleevedArms(ModelPartBuilder.create(), ModelPartBuilder.create(), smallArms);
    }

    private static void addArms(ModelPartBuilder leftArm, ModelPartBuilder rightArm, Dilation deformation, boolean smallArms) {
        leftArm.cuboid(-1, -2, -2, smallArms ? 3 : 4, 12, 4, deformation);
        rightArm.cuboid(smallArms ? -2 : -3, -2, -2, smallArms ? 3 : 4, 12, 4, deformation);
    }

    public static ModelData createClaws(boolean smallArms) {
        ModelPartBuilder leftArm = ModelPartBuilder.create();
        ModelPartBuilder rightArm = ModelPartBuilder.create();

        int smallArmsOffset = smallArms ? 1 : 0;

        // claw 1 lower
        leftArm.uv(0, 0);
        leftArm.cuboid(-smallArmsOffset, 10, -1.5F, 3, 5, 1);
        rightArm.uv(8, 0);
        rightArm.cuboid(-3 + smallArmsOffset, 10, -1.5F, 3, 5, 1);

        // claw 2 lower
        leftArm.uv(0, 6);
        leftArm.cuboid(-smallArmsOffset, 10, 0.5F, 3, 5, 1);
        rightArm.uv(8, 6);
        rightArm.cuboid(-3 + smallArmsOffset, 10, 0.5F, 3, 5, 1);

        // claw 1 upper
        leftArm.uv(16, 0);
        leftArm.cuboid(3 - smallArmsOffset, 10, -1.5F, 1, 4, 1);
        rightArm.uv(20, 0);
        rightArm.cuboid(-4 + smallArmsOffset, 10, -1.5F, 1, 4, 1);

        // claw 2 upper
        leftArm.uv(16, 6);
        leftArm.cuboid(3 - smallArmsOffset, 10, 0.5F, 1, 4, 1);
        rightArm.uv(20, 6);
        rightArm.cuboid(-4 + smallArmsOffset, 10, 0.5F, 1, 4, 1);

        return createEmptyArms(leftArm, rightArm, smallArms);
    }

    @Override
    protected Iterable<ModelPart> getHeadParts() {
        return ImmutableList.of();
    }

    @Override
    protected Iterable<ModelPart> getBodyParts() {
        return ImmutableList.of(leftArm, rightArm);
    }

    public void renderArm(Arm handSide, MatrixStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        getArm(handSide).visible = true;
        getArm(handSide.getOpposite()).visible = false;
        render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
