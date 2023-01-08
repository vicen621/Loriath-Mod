package io.github.vicen621.loriath.common.item.mysteryBoxes;

import io.github.vicen621.loriath.LoriathMod;
import io.github.vicen621.loriath.common.init.ModSoundEvents;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public enum MysteryBoxRarity {

    COMMON,
    UNCOMMON,
    RARE(ModSoundEvents.BOX_SWITCH_PLUS, ModSoundEvents.RARE_BOX_THEME), //TODO: Cambiar por el tema del final de la común
    EPIC(ModSoundEvents.BOX_SWITCH_PLUS, ModSoundEvents.EPIC_BOX_THEME),
    LEGENDARY(ModSoundEvents.LEGENDARY_BOX_OPEN, null, ModSoundEvents.BOX_SWITCH_PLUS, ModSoundEvents.LEGENDARY_BOX_THEME),
    COSMETIC(ModSoundEvents.BOX_SWITCH, ModSoundEvents.COSMETIC_BOX_THEME);

    final Identifier lootTable;
    final SoundEvent openSound, finishSound, switchSound, themeSound;

    MysteryBoxRarity() {
        this(ModSoundEvents.BOX_SWITCH, ModSoundEvents.BOX_THEME); //Cambiar por el tema del final de la comun
    }

    MysteryBoxRarity(SoundEvent switchSound, SoundEvent themeSound) {
        this(ModSoundEvents.BOX_OPEN, ModSoundEvents.BOX_FINISH, switchSound, themeSound);
    }

    MysteryBoxRarity(SoundEvent openSound, SoundEvent finishSound, SoundEvent switchSound, SoundEvent themeSound) {
        this.lootTable = LoriathMod.id("mystery_boxes/" + this.toString().toLowerCase());
        this.openSound = openSound;
        this.switchSound = switchSound;
        this.themeSound = themeSound;
        this.finishSound = finishSound;
    }

    public Identifier getLootTable() {
        return this.lootTable;
    }

    public SoundEvent getOpenSound() {
        return this.openSound;
    }

    public SoundEvent getFinishSound() {
        return this.finishSound;
    }

    public SoundEvent getSwitchSound() {
        return this.switchSound;
    }

    public SoundEvent getThemeSound() {
        return this.themeSound;
    }
}
