package io.github.vicen621.loriath.common.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

public class LivingEvent {
    public interface LivingEntityFallCallback {

        Event<LivingEntityFallCallback> EVENT = EventFactory.createArrayBacked(LivingEntityFallCallback.class,
                (listeners) -> (user, distance, damageMultiplier) -> {
                    for (LivingEntityFallCallback listener : listeners) {
                        boolean list = listener.fall(user, distance, damageMultiplier);

                        if (!list)
                            return false;
                    }

                    return true;
                });

        boolean fall(LivingEntity user, float distance, float damageMultiplier);
    }

    public interface LivingEntityHurtCallback {

        Event<LivingEntityHurtCallback> EVENT = EventFactory.createArrayBacked(LivingEntityHurtCallback.class,
                (listeners) -> (user, source, amount) -> {
                    for (LivingEntityHurtCallback listener : listeners) {
                        float list = listener.hurt(user, source, amount);

                        if (list != amount)
                            return list;
                    }

                    return amount;
                });

        float hurt(LivingEntity user, DamageSource source, float amount);
    }

    public interface LivingEntityUpdateCallback {
        Event<LivingEntityUpdateCallback> EVENT = EventFactory.createArrayBacked(LivingEntityUpdateCallback.class,
                (listeners) -> (entity) -> {
                    for (LivingEntityUpdateCallback listener : listeners) {
                        listener.update(entity);
                    }
                });

        void update(LivingEntity entity);
    }

    public interface LivingEntityJumpCallback {
        Event<LivingEntityJumpCallback> EVENT = EventFactory.createArrayBacked(LivingEntityJumpCallback.class,
                (listeners) -> (entity) -> {
                    for (LivingEntityJumpCallback listener : listeners) {
                        listener.jump(entity);
                    }
                });

        void jump(LivingEntity entity);
    }
}
