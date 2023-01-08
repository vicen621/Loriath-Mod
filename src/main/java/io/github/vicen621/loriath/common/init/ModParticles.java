package io.github.vicen621.loriath.common.init;

import io.github.vicen621.loriath.LoriathMod;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.registry.Registry;

public class ModParticles {

    public static DefaultParticleType PHOENIX = register("phoenix");
    public static DefaultParticleType OVERSPEED = register("overspeed");
    public static DefaultParticleType SECOND_OVERSPEED = register("second_overspeed");
    public static DefaultParticleType MYSTERY_BOX = register("mystery_box");

    private static DefaultParticleType register(String name) {
        return Registry.register(Registry.PARTICLE_TYPE, LoriathMod.id(name), FabricParticleTypes.simple());
    }

    public static void registerModParticles() {
        LoriathMod.LOGGER.info("Registering particles for " + LoriathMod.MODID);
    }
}
