package io.github.vicen621.loriath.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

import java.util.Collection;

// TODO Finish AddText
/*
https://github.com/williambl/haema/blob/7f595887862f0033576d1211f9274beaf1174daa/src/main/kotlin/com/williambl/haema/client/gui/VampireHud.kt
https://github.com/williambl/haema/blob/7f595887862f0033576d1211f9274beaf1174daa/src/main/kotlin/com/williambl/haema/api/client/VampireHudAddTextEvent.kt
https://github.com/williambl/haema/blob/7f595887862f0033576d1211f9274beaf1174daa/src/main/java/com/williambl/haema/mixin/InGameHudMixin.java
 */
public interface HudAddTextEvent {
    public static Event<HudAddTextEvent> EVENT = EventFactory.createArrayBacked(HudAddTextEvent.class, (listeners) -> (player, createText) -> {
        for (HudAddTextEvent event : listeners) {
            MutableText
        }
    });

    Collection<Text> addText(PlayerEntity player);
}
