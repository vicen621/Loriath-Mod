package io.github.vicen621.loriath.client.render.accessory.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;

public class NecklaceModel extends BipedEntityModel<LivingEntity> {

    public NecklaceModel(ModelPart part) {
        super(part, RenderLayer::getEntityTranslucent);
    }

    public static ModelData createNecklace(ModelPartBuilder body) {
        ModelData mesh = getModelData(Dilation.NONE, 0);

        mesh.getRoot().addChild(
                "body",
                body.uv(0, 0)
                        .cuboid(-(2 * 8) / 2F, -1 / 2F, -(2 * 4 + 1) / 2F, 2 * 8, 2 * 12 + 1, 2 * 4 + 1),
                ModelTransform.NONE
        );

        return mesh;
    }

    public static ModelData createCenteredNecklace(ModelPartBuilder body) {
        ModelData mesh = getModelData(Dilation.NONE, 0);

        mesh.getRoot().addChild(
                "body",
                body.uv(0, 0)
                        .cuboid(-(2 * 8 + 1) / 2F, -1 / 2F, -(2 * 4 + 1) / 2F, 2 * 8 + 1, 2 * 12 + 1, 2 * 4 + 1),
                ModelTransform.NONE
        );

        return mesh;
    }

    public static ModelData createPanicNecklace() {
        ModelPartBuilder body = ModelPartBuilder.create();

        // gem top
        body.uv(52, 0);
        body.cuboid(-2.5F, 5.5F, -5, 2, 2, 1);
        body.uv(58, 0);
        body.cuboid(0.5F, 5.5F, -5, 2, 2, 1);

        // gem middle
        body.uv(52, 3);
        body.cuboid(-1.5F, 6.5F, -5, 3, 2, 1);

        // gem bottom
        body.uv(60, 4);
        body.cuboid(-0.5F, 8.5F, -5, 1, 1, 1);

        return createCenteredNecklace(body);
    }

    public static ModelData createLavaCharm() {
        ModelPartBuilder body = ModelPartBuilder.create();

        // gem
        body.uv(50, 0);
        body.cuboid(-1, 4.5F, -5, 2, 2, 1);

        return createNecklace(body);
    }

    @Override
    protected Iterable<ModelPart> getHeadParts() {
        return ImmutableList.of();
    }

    @Override
    protected Iterable<ModelPart> getBodyParts() {
        return ImmutableList.of(body);
    }

    @Override
    public void render(MatrixStack MatrixStack, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
        MatrixStack.push();
        MatrixStack.scale(0.5F, 0.5F, 0.5F);
        body.render(MatrixStack, buffer, light, overlay, red, green, blue, alpha);
        MatrixStack.pop();
    }
}
