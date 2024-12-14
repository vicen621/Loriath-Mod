package io.github.vicen621.loriath.init;

import io.github.vicen621.loriath.Loriath;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModSoundEvents {

    public static final SoundEvent BOX_OPEN = register("item.mystery_box.open");
    public static final SoundEvent LEGENDARY_BOX_OPEN = register("item.mystery_box.open_legendary");
    public static final SoundEvent BOX_SWITCH = register("item.mystery_box.switch");
    public static final SoundEvent BOX_SWITCH_PLUS = register("item.mystery_box.switch_plus");
    public static final SoundEvent BOX_THEME = register("item.mystery_box.theme");
    public static final SoundEvent RARE_BOX_THEME = register("item.mystery_box.theme_rare");
    public static final SoundEvent EPIC_BOX_THEME = register("item.mystery_box.theme_epic");
    public static final SoundEvent LEGENDARY_BOX_THEME = register("item.mystery_box.theme_legendary");
    public static final SoundEvent COSMETIC_BOX_THEME = register("item.mystery_box.theme_cosmetic");
    public static final SoundEvent BOX_FINISH = register("item.mystery_box.finish");
    public static final SoundEvent LEGENDARY_BOX_FINISH = register("item.mystery_box.finish_legendary");

    private static SoundEvent register(String name) {
        Identifier id = Loriath.id(name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerModSounds() {
        Loriath.LOGGER.info("Registering Sounds for " + Loriath.MODID);
    }
}
