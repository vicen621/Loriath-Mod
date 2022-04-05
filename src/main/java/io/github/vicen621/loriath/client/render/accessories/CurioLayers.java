package io.github.vicen621.loriath.client.render.accessories;

import io.github.vicen621.loriath.LoriathMod;
import net.minecraft.client.render.entity.model.EntityModelLayer;

public class CurioLayers {

    public static final EntityModelLayer PANIC_NECKLACE = createLocation("panic_necklace");
    public static final EntityModelLayer CLAWS = createLocation("claws");
    public static final EntityModelLayer SLIM_CLAWS = createLocation("slim_claws");
    public static final EntityModelLayer GLOVE = createLocation("gloves");
    public static final EntityModelLayer SLIM_GLOVE = createLocation("slim_gloves");

    private static EntityModelLayer createLocation(String model) {
        return createLocation(model, "main");
    }

    @SuppressWarnings("SameParameterValue")
    private static EntityModelLayer createLocation(String model, String layer) {
        return new EntityModelLayer(LoriathMod.id(model), layer);
    }

    public static EntityModelLayer claws(boolean smallArms) {
        return smallArms ? SLIM_CLAWS : CLAWS;
    }

    public static EntityModelLayer glove(boolean smallArms) {
        return smallArms ? SLIM_GLOVE : GLOVE;
    }
}
