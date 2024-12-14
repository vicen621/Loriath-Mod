package io.github.vicen621.loriath.mixin.events;

import io.github.vicen621.loriath.events.TickEvents;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BooleanSupplier;

@Mixin(MinecraftServer.class)
public abstract class ServerMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    private void serverTick(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
        TickEvents.ServerTickCallback.EVENT.invoker().tick();
    }
}
