package io.github.vicen621.loriath.common.item.accessories;

import io.github.vicen621.loriath.common.init.ModItems;
import net.minecraft.item.Item;

import java.util.ArrayList;

public enum AccessoryRarity {

    COMMON(new ArrayList<>() {
        {
            add(ModItems.ADHESIVE_BANDAGE);
            add(ModItems.BEZOAR);
            add(ModItems.FAST_CLOCK);
        }
    }),

    UNCOMMON(new ArrayList<>() {
        {
            add(ModItems.CLOAK_OF_INVISIBILITY);
            add(ModItems.FROG_LEG);
            add(ModItems.DIVING_GEAR);
        }
    }),

    RARE(new ArrayList<>() {
        {
            add(ModItems.MEDICATED_BANDAGE);
            add(ModItems.SHACKLE);
            add(ModItems.SHINY_STONE);
            add(ModItems.PANIC_NECKLACE);
        }
    }),

    EPIC(new ArrayList<>() {
        {
            add(ModItems.LAVA_CHARM);
            add(ModItems.DIGGING_CLAWS);
            add(ModItems.FROG_FLIPPER);
        }
    }),

    LEGENDARY(new ArrayList<>() {
        {
            add(ModItems.DESTROYER_EMBLEM);
            add(ModItems.HERMES_BOOTS);
            add(ModItems.TITAN_GLOVE);
        }
    });

    private final ArrayList<Item> items;

    AccessoryRarity(ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}
