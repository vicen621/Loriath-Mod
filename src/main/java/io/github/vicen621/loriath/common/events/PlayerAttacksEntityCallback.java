package io.github.vicen621.loriath.common.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.apache.logging.log4j.core.jmx.Server;

import java.util.Arrays;

public interface PlayerAttacksEntityCallback {

    Event<PlayerAttacksEntityCallback> EVENT = EventFactory.createArrayBacked(PlayerAttacksEntityCallback.class,
            (listeners) -> (user, target, amount) -> {
                for (PlayerAttacksEntityCallback listener : listeners) {
                    listener.damage(user, target, amount);
                }
            });

    void damage(ServerPlayerEntity user, Entity target, float amount);
}
