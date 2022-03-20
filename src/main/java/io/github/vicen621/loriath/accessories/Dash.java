package io.github.vicen621.loriath.accessories;

import io.github.vicen621.loriath.LoriathMod;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Random;

//TODO Fijarse porque no funciona
public class Dash {

    public static KeyBinding DASH_KEYBIND;

    public Dash() {
        ServerPlayNetworking.registerGlobalReceiver(new Identifier(LoriathMod.MOD_ID, "dash"),
                (server, player, handler, buf, sender) -> {
                    World world = player.world;

                    Random rand = world.random;
                    for (int i = 0; i < 3; i++) {
                        double d0 = rand.nextGaussian() * 0.02D;
                        double d1 = rand.nextGaussian() * 0.02D;
                        double d2 = rand.nextGaussian() * 0.02D;
                        double x = player.getX() + (double) (rand.nextFloat() * 2.0F) - d0 * 10.0D;
                        double y = player.getY() + (double) (rand.nextFloat() * 2.0F) - d1 * 10.0D;
                        double z = player.getZ() + (double) (rand.nextFloat() * 2.0F) - d2 * 10.0D;
                        if (world instanceof ServerWorld sv)
                            sv.spawnParticles(ParticleTypes.POOF, x, y, z, 10,
                                    d0, d1, d2, 0.0);
                    }
                    world.playSound(null, player.getX(), player.getY(), player.getZ(),
                            SoundEvents.ENTITY_GHAST_SHOOT, SoundCategory.PLAYERS, 0.5f, 1.5f);

                    player.setVelocity(player.getVelocity().add(calculateVector(player)));
                    player.velocityModified = true;
                });
    }

    private Vec3d calculateVector(ServerPlayerEntity p) {
        double radians = Math.toRadians(p.getYaw());
        double x = -Math.sin(radians) * 2;
        double y = 0.7;
        double z = Math.cos(radians) * 2;
        return new Vec3d(x, y, z);
    }
}
