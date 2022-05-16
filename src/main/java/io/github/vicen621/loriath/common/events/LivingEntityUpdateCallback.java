package io.github.vicen621.loriath.common.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;

public interface LivingEntityUpdateCallback {
    Event<LivingEntityUpdateCallback> EVENT = EventFactory.createArrayBacked(LivingEntityUpdateCallback.class,
            (listeners) -> (entity) -> {
                for (LivingEntityUpdateCallback listener : listeners) {
                    listener.update(entity);
                }
            });

    void update(LivingEntity entity);
}
