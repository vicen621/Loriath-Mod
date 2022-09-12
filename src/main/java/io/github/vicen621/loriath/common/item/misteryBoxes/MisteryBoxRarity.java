package io.github.vicen621.loriath.common.item.misteryBoxes;

import io.github.vicen621.loriath.LoriathMod;
import net.minecraft.util.Identifier;

public enum MisteryBoxRarity {

    COMMON, UNCOMMON, RARE, EPIC, LEGENDARY;

    Identifier lootTable;
    MisteryBoxRarity() {
        this.lootTable = LoriathMod.id("mistery_boxes/" + this.toString().toLowerCase());
    }

    public Identifier getLootTable() {
        return this.lootTable;
    }
}
