package io.github.vicen621.loriath.init;

import io.github.vicen621.loriath.Loriath;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModParticles {

    public static DefaultParticleType PHOENIX = register("phoenix");
    public static DefaultParticleType OVERSPEED = register("overspeed");
    public static DefaultParticleType SECOND_OVERSPEED = register("second_overspeed");
    public static DefaultParticleType MYSTERY_BOX = register("mystery_box");

    private static DefaultParticleType register(String name) {
        return Registry.register(Registries.PARTICLE_TYPE, Loriath.id(name), FabricParticleTypes.simple());
    }

    public static void registerModParticles() {
        Loriath.LOGGER.info("Registering particles for " + Loriath.MODID);
    }
}
