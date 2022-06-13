package io.github.vicen621.loriath.common.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;

public class TickEvents {

    public interface PlayerTickCallback {
        Event<PlayerTickCallback> EVENT = EventFactory.createArrayBacked(PlayerTickCallback.class,
                (listeners) -> (player) -> {
                    for (PlayerTickCallback listener : listeners) {
                        listener.tick(player);
                    }
                });


        void tick(PlayerEntity player);
    }

    public interface ServerTickCallback {
        Event<ServerTickCallback> EVENT = EventFactory.createArrayBacked(ServerTickCallback.class,
                (listeners) -> () -> {
                    for (ServerTickCallback listener : listeners) {
                        listener.tick();
                    }
                });


        void tick();
    }
}
