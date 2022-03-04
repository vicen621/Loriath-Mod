package io.github.vicen621.loriath.mixin;

import io.github.vicen621.loriath.events.PlayerDamageCallback;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"), method = "damage", cancellable = true)
    public void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (((ServerPlayerEntity) (Object) this).getAbilities().creativeMode)
            return;

        ActionResult result = PlayerDamageCallback.EVENT.invoker().damage((ServerPlayerEntity) (Object) this, source, amount);

        if (result == ActionResult.FAIL)
            cir.cancel();
    }
}
