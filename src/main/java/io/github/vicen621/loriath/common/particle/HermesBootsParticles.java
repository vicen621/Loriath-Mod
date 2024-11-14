package io.github.vicen621.loriath.common.particle;

import io.github.vicen621.loriath.common.init.ModParticles;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Vector3f;
import org.joml.Vector3fc;

public class HermesBootsParticles {

    public static void spawnRocketParticles(LivingEntity entity, World world) {
        float yBodyRot = entity.getYaw();

        Vec3d playerPos = entity.getPos();
        float random = (world.random.nextFloat() - 0.05F) * 0.01F;

        Vec3d vLeft = new Vec3d(-0.2, 0, 0).rotateX(0).rotateY((yBodyRot / -57.295f));
        Vec3d vRight = new Vec3d(0.2, 0, 0).rotateX(0).rotateY((yBodyRot / -57.295f));

        Vector3f velocity = new Vector3f((float) entity.getVelocity().x, (float) entity.getVelocity().y, (float) entity.getVelocity().z);
        velocity.mul(0.01f);
        Vec3d velocityVec = new Vec3d(velocity.x, velocity.y, velocity.z);

        Vec3d right = playerPos.add(vRight).add(velocityVec);
        Vec3d left = playerPos.add(vLeft).add(velocityVec);

        spawnParticles(entity, random, world, left, right);
    }

    private static void spawnParticles(LivingEntity entity, float random, World world, Vec3d left, Vec3d right) {
        boolean isSneaking = entity.isSneaking();
        if (!entity.isSubmergedInWater() && !isSneaking) {
            for (int i = 0; i < 10; i++) {
                world.addParticle(ModParticles.OVERSPEED, left.x, left.y + 0.1D, left.z, random, 0.0D, random);
                world.addParticle(ModParticles.SECOND_OVERSPEED, left.x, left.y + 0.1D, left.z, random, 0.0D, random);
                world.addParticle(ModParticles.OVERSPEED, right.x, right.y, right.z + 0.1D, random, 0.0D, random);
                world.addParticle(ModParticles.SECOND_OVERSPEED, right.x, right.y + 0.1D, right.z, random, 0.0D, random);
            }
        } else {
            world.addParticle(ParticleTypes.BUBBLE, left.x, left.y, left.z, random, 0.0D, random);
            world.addParticle(ParticleTypes.BUBBLE, right.x, right.y, right.z, random, 0.0D, random);
        }
    }

}