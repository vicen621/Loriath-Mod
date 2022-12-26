package io.github.vicen621.loriath.mixin.effects;

import io.github.vicen621.loriath.extensions.StatusEffectInstanceExtensions;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(StatusEffectInstance.class)
public abstract class StatusEffectInstanceMixin implements StatusEffectInstanceExtensions {

    @Shadow
    int duration;

    @Shadow
    private StatusEffectInstance hiddenEffect;

    @Unique
    @Override
    public void loriath$setDuration(int duration) {
        // Recursively set duration for hidden effects
        if (this.hiddenEffect != null) {
            ((StatusEffectInstanceExtensions) this.hiddenEffect).loriath$setDuration(duration);
        }

        this.duration = duration;
    }
}