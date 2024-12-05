package io.github.vicen621.loriath.render.accessory;

import io.github.vicen621.loriath.Loriath;
import net.minecraft.client.render.entity.model.EntityModelLayer;

public class AccessoryLayers {
    public static final EntityModelLayer PANIC_NECKLACE = createLocation("panic_necklace");
    public static final EntityModelLayer LAVA_CHARM = createLocation("lava_charm");
    public static final EntityModelLayer CLAWS = createLocation("claws");
    public static final EntityModelLayer SLIM_CLAWS = createLocation("slim_claws");
    public static final EntityModelLayer DIVING_GEAR = createLocation("diving_gear");

    private static EntityModelLayer createLocation(String model) {
        return createLocation(model, "main");
    }

    @SuppressWarnings("SameParameterValue")
    private static EntityModelLayer createLocation(String model, String layer) {
        return new EntityModelLayer(Loriath.id(model), layer);
    }

    public static EntityModelLayer claws(boolean smallArms) {
        return smallArms ? SLIM_CLAWS : CLAWS;
    }
}
