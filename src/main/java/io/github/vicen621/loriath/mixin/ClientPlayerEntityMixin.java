package io.github.vicen621.loriath.mixin;

import com.mojang.authlib.GameProfile;
import io.github.vicen621.loriath.LoriathMod;
import io.github.vicen621.loriath.accessories.Dash;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin extends AbstractClientPlayerEntity {

    private int pressedTicks = 0;
    private long lastDashed = -24000;
    public ClientPlayerEntityMixin(ClientWorld world, GameProfile profile) {
        super(world, profile);
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;tick()V"))
    void dash(CallbackInfo ci) {
        if (pressedTicks > 0 && !Dash.DASH_KEYBIND.isPressed() && canDash()) {
            PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
            ClientPlayNetworking.send(new Identifier(LoriathMod.MOD_ID, "dash"), buf);
            lastDashed = world.getTime();
        } else if (Dash.DASH_KEYBIND.isPressed() && canDash()) {
            Vec3d target = Dash.raytraceForDash(this);
            if (target != null) for (int i = 0; i < 10; i++) {
                world.addParticle(new DustParticleEffect(new Vec3f(0, 0, 0), 1), target.x - 0.5 + random.nextDouble(), target.y + random.nextDouble() * 2, target.z - 0.5 + random.nextDouble(), 0.0, 0.5, 0.0);
            }
        }
        pressedTicks = Dash.DASH_KEYBIND.isPressed() ? pressedTicks + 1 : 0;
    }

    private boolean canDash() {
        return world.getTime() > lastDashed + 50;
    }
}
