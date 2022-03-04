package io.github.vicen621.loriath.accessories;

import io.github.vicen621.loriath.LoriathMod;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

import java.util.Random;

//TODO Testear en server
public class Dash {

    public static KeyBinding DASH_KEYBIND;

    public Dash() {
        ServerPlayNetworking.registerGlobalReceiver(new Identifier(LoriathMod.MOD_ID, "dash"),
                (server, player, handler, buf, sender) -> {
                    World world = player.world;
                    Vec3d target = raytraceForDash(player);

                    if (target != null) {
                        Random rand = world.random;
                        for (int i = 0; i < 3; i++) {
                            double x = (target.x - player.getX()) * rand.nextDouble() + player.getX() - 0.5;
                            double y = (target.y - player.getY()) * rand.nextDouble() + player.getY() + 1;
                            double z = (target.z - player.getZ()) * rand.nextDouble() + player.getZ() - 0.5;
                            if (world instanceof ServerWorld sv)
                                sv.spawnParticles(DustParticleEffect.DEFAULT, x, y, z, 10,
                                        0.5, 1.0, 0.5, 0.0);
                        }
                        world.playSound(null, target.x, target.y, target.z,
                                SoundEvents.ENTITY_GHAST_SHOOT, SoundCategory.PLAYERS, 1f, 1.5f);


                        Vec3d rotation = player.getRotationVector();
                        player.sendMessage(new LiteralText(rotation.toString()), false);
                        double d = 1D;
                        double e = 0.5D;
                        Vec3d velocity = player.getVelocity();
                        player.setVelocity(velocity.add(rotation.x * e + (rotation.x * d - velocity.x) * e, rotation.y * e + (rotation.y * d - velocity.y) * e, rotation.z * e + (rotation.z * d - velocity.z) * e));
                        player.velocityModified = true;
                    }
                });
    }

    public static Vec3d raytraceForDash(PlayerEntity p) {
        World world = p.world;

        Vec3d eyeVec = p.getCameraPosVec(0f);
        Vec3d dir = p.getRotationVec(0f);
        Vec3d rayEnd = eyeVec.add(dir.x * 16, dir.y * 16, dir.z * 16);
        BlockHitResult result = world.raycast(new RaycastContext(eyeVec, rayEnd, RaycastContext.ShapeType.COLLIDER,
                RaycastContext.FluidHandling.NONE, p));

        BlockPos dashPos = result.getSide() == Direction.DOWN ? result.getBlockPos().down(2) :
                result.getBlockPos().offset(result.getSide());

        boolean posIsFree = posIsClear(world, dashPos);
        while (!posIsFree) {
            dashPos = dashPos.down();
            posIsFree = posIsClear(world, dashPos) && world.raycast(new RaycastContext(eyeVec,
                    Vec3d.ofCenter(dashPos.up()), RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, p))
                    .getType() == HitResult.Type.MISS;
            if (dashPos.getY() <= 0)
                break;
        }
        return posIsFree ? Vec3d.ofCenter(dashPos) : null;
    }

    private static boolean posIsClear(World world, BlockPos pos) {
        return (world.isAir(pos) || world.getBlockState(pos).getCollisionShape(world, pos).isEmpty())
                && (world.isAir(pos.up()) || world.getBlockState(pos.up()).getCollisionShape(world, pos.up()).isEmpty());
    }
}
