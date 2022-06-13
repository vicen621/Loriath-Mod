package io.github.vicen621.loriath.common.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class PhoenixParticle extends SpriteBillboardParticle {

    public PhoenixParticle(ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super(world, x, y, z, xSpeed, ySpeed, zSpeed);
        this.velocityX = this.velocityX * 0.01D + xSpeed;
        this.velocityY = this.velocityY * 0.01D + ySpeed * 0.0D;
        this.velocityZ = this.velocityZ * 0.01D + zSpeed;

        this.maxAge = (20 + world.getRandom().nextInt(10));
    }

    @Override
    public float getSize(float tickDelta) {
        float factor = ((float) this.age + tickDelta) / (float) this.maxAge;
        return this.scale * (1.0F - factor * 0.5F);
    }

    @Override
    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;

        if (this.age++ >= this.maxAge)
            this.markDead();

        else {
            this.move(this.velocityX, this.velocityY, this.velocityZ);
            this.velocityX *= 0.75D;
            this.velocityY *= 0.75D;
            this.velocityZ *= 0.75D;
            if (this.onGround) {
                this.velocityX *= 0.5D;
                this.velocityZ *= 0.5D;
            }
        }
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider sprite) {
            this.spriteProvider = sprite;
        }

        @Nullable
        @Override
        public Particle createParticle(DefaultParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            PhoenixParticle particle = new PhoenixParticle(world, x, y, z, velocityX, velocityY, velocityZ);
            particle.setSprite(this.spriteProvider);

            return particle;
        }
    }
}
