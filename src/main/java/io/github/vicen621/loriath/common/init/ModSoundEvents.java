package io.github.vicen621.loriath.common.init;

import io.github.vicen621.loriath.LoriathMod;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModSoundEvents {

    public static final SoundEvent MISTERY_BOX_OPEN = register("item.mistery_box.open");
    public static final SoundEvent MISTERY_BOX_PICKUP = register("item.mistery_box.pickup");
    public static final SoundEvent WATER_STEP = register("block.water.step");

    private static SoundEvent register(String name) {
        return Registry.register(Registry.SOUND_EVENT, LoriathMod.id(name), new SoundEvent(LoriathMod.id(name)));
    }

    public static void registerModSounds() {
        LoriathMod.LOGGER.info("Registering Sounds for " + LoriathMod.MODID);
    }
}
