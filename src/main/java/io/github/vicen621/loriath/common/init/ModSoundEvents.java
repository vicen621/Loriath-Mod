package io.github.vicen621.loriath.common.init;

import io.github.vicen621.loriath.LoriathMod;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

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
        Identifier id = LoriathMod.id(name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }

    public static void registerModSounds() {
        LoriathMod.LOGGER.info("Registering Sounds for " + LoriathMod.MODID);
    }
}
