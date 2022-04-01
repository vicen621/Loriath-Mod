package io.github.vicen621.loriath.item.misteryBoxes;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.ArrayList;
import java.util.Random;

public enum MisteryBoxRarity {
    COMMON(new ArrayList<>() {
        {
            add(Items.IRON_SWORD.getDefaultStack());
            add(Items.IRON_AXE.getDefaultStack());
            add(Items.DIAMOND_SWORD.getDefaultStack());
            add(Items.NETHERITE_SWORD.getDefaultStack());
            add(Items.DIAMOND_AXE.getDefaultStack());
            add(Items.NETHERITE_AXE.getDefaultStack());
            add(new ItemStack(Items.GOLDEN_APPLE, new Random().nextInt(5) + 1));
        }
    });

    private final ArrayList<ItemStack> items;

    MisteryBoxRarity(ArrayList<ItemStack> items) {
        this.items = items;
    }

    public ArrayList<ItemStack> getItems() {
        return items;
    }
}
