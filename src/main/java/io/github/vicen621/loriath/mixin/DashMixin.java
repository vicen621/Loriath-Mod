package io.github.vicen621.loriath.mixin;

import com.mojang.authlib.GameProfile;
import io.github.vicen621.loriath.LoriathMod;
import io.github.vicen621.loriath.common.item.trinkets.accessories.items.extra.Dash;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.encryption.PlayerPublicKey;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public abstract class DashMixin extends AbstractClientPlayerEntity {

    private int pressedTicks = 0;
    private long lastDashed = -24000;

    public DashMixin(ClientWorld world, GameProfile profile, @Nullable PlayerPublicKey publicKey) {
        super(world, profile, publicKey);
    }


    @Shadow
    public abstract Hand getActiveHand();

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;tick()V"))
    void dash(CallbackInfo ci) {
        /*if (pressedTicks > 0 && !Dash.DASH_KEYBIND.isPressed() && canDash()) {
            PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
            ClientPlayNetworking.send(LoriathMod.id("dash"), buf);
            this.getItemCooldownManager().set(this.getStackInHand(this.getActiveHand()).getItem(), 50);
            lastDashed = world.getTime();
        }
        pressedTicks = Dash.DASH_KEYBIND.isPressed() ? pressedTicks + 1 : 0;*/
    }

    private boolean canDash() {
        return !this.isFallFlying() && world.getTime() > lastDashed + 50 /*&&
                this.getStackInHand(this.getActiveHand()).getItem() instanceof FabricShieldItem*/;
    }
}
