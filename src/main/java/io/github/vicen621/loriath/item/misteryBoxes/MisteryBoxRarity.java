package io.github.vicen621.loriath.item.misteryBoxes;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.ArrayList;

// TODO Hacer las listas de items
public enum MisteryBoxRarity {
    COMMON(new ArrayList<>() {
        {
            /*add(Items.IRON_SWORD.getDefaultStack());
            add(Items.IRON_AXE.getDefaultStack());
            add(Items.DIAMOND_SWORD.getDefaultStack());
            add(Items.NETHERITE_SWORD.getDefaultStack());
            add(Items.DIAMOND_AXE.getDefaultStack());
            add(Items.NETHERITE_AXE.getDefaultStack());
            add(new ItemStack(Items.GOLDEN_APPLE, new Random().nextInt(5) + 1));*/
            add(new ItemStack(Items.STICK, 1));
            add(new ItemStack(Items.STICK, 2));
            add(new ItemStack(Items.STICK, 3));
            add(new ItemStack(Items.STICK, 4));
            add(new ItemStack(Items.STICK, 5));
        }
    }),
    UNCOMMON(new ArrayList<>() {
        {
            add(new ItemStack(Items.STICK, 1));
            add(new ItemStack(Items.STICK, 2));
            add(new ItemStack(Items.STICK, 3));
            add(new ItemStack(Items.STICK, 4));
            add(new ItemStack(Items.STICK, 5));
        }
    }),
    RARE(new ArrayList<>() {
        {
            add(new ItemStack(Items.STICK, 1));
            add(new ItemStack(Items.STICK, 2));
            add(new ItemStack(Items.STICK, 3));
            add(new ItemStack(Items.STICK, 4));
            add(new ItemStack(Items.STICK, 5));
        }
    }),
    EPIC(new ArrayList<>() {
        {
            add(new ItemStack(Items.STICK, 1));
            add(new ItemStack(Items.STICK, 2));
            add(new ItemStack(Items.STICK, 3));
            add(new ItemStack(Items.STICK, 4));
            add(new ItemStack(Items.STICK, 5));
        }
    }),
    LEGENDARY(new ArrayList<>() {
        {
            add(new ItemStack(Items.STICK, 1));
            add(new ItemStack(Items.STICK, 2));
            add(new ItemStack(Items.STICK, 3));
            add(new ItemStack(Items.STICK, 4));
            add(new ItemStack(Items.STICK, 5));
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
