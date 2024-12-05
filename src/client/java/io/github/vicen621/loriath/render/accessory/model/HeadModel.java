package io.github.vicen621.loriath.render.accessory.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class HeadModel extends BipedEntityModel<LivingEntity> {

    public HeadModel(ModelPart part, Function<Identifier, RenderLayer> renderType) {
        super(part, renderType);
    }

    public HeadModel(ModelPart part) {
        this(part, RenderLayer::getEntityCutoutNoCull);
    }

    public static ModelData createEmptyHat(ModelPartBuilder head) {
        ModelData mesh = getModelData(Dilation.NONE, 0);

        mesh.getRoot().addChild(
                "head",
                head,
                ModelTransform.NONE
        );

        return mesh;
    }

    public static ModelData createHat(ModelPartBuilder head) {
        Dilation deformation = new Dilation(0.5F);

        head.uv(0, 0);
        head.cuboid(-4, -8, -4, 8, 8, 8, deformation);

        return createEmptyHat(head);
    }

    public static ModelData createDiagonalHat(ModelPartBuilder head, ModelPartBuilder diagonalParts, String partName) {
        ModelData mesh = createHat(head);

        mesh.getRoot().getChild("head").addChild(
                partName,
                diagonalParts,
                ModelTransform.rotation(45 * (float) Math.PI / 180, 0, 0)
        );

        return mesh;
    }

    public static ModelData createDivingGear() {
        ModelPartBuilder head = ModelPartBuilder.create();
        ModelPartBuilder tube = ModelPartBuilder.create();

        // mouth thingy
        head.uv(32, 0);
        head.cuboid(-2, -1.5F, -6, 8, 2, 2);

        // tube
        tube.uv(0, 16);
        tube.cuboid(4.01F, -5, -3, 2, 2, 12);

        return createDiagonalHat(head, tube, "tube");
    }

    @Override
    protected Iterable<ModelPart> getHeadParts() {
        return ImmutableList.of(head);
    }

    @Override
    protected Iterable<ModelPart> getBodyParts() {
        return ImmutableList.of();
    }
}
