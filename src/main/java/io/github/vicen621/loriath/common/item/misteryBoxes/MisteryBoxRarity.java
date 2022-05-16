package io.github.vicen621.loriath.common.item.misteryBoxes;

import io.github.vicen621.loriath.common.item.accessories.AccessoryRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

// TODO Hacer las listas de items
public enum MisteryBoxRarity {
    COMMON(new ArrayList<>() {
        {
            add(MisteryBoxRarity.getFromRarity(AccessoryRarity.COMMON.getItems()));
            add(MisteryBoxRarity.getFromRarity(AccessoryRarity.COMMON.getItems()));
            add(MisteryBoxRarity.getFromRarity(AccessoryRarity.COMMON.getItems()));
            add(MisteryBoxRarity.getFromRarity(AccessoryRarity.COMMON.getItems()));
            add(MisteryBoxRarity.getFromRarity(AccessoryRarity.COMMON.getItems()));
            add(MisteryBoxRarity.getFromRarity(AccessoryRarity.COMMON.getItems()));
            add(MisteryBoxRarity.getFromRarity(AccessoryRarity.COMMON.getItems()));
            add(MisteryBoxRarity.getFromRarity(AccessoryRarity.COMMON.getItems()));
            add(MisteryBoxRarity.getFromRarity(AccessoryRarity.UNCOMMON.getItems()));
            add(MisteryBoxRarity.getFromRarity(AccessoryRarity.UNCOMMON.getItems()));
            add(MisteryBoxRarity.getFromRarity(AccessoryRarity.RARE.getItems()));
            Collections.shuffle(this);
        }
    }),
    UNCOMMON(new ArrayList<>() {
        {
            add(new ItemStack(Items.STICK, 1));
            add(new ItemStack(Items.STICK, 2));
            add(new ItemStack(Items.STICK, 3));
            add(new ItemStack(Items.STICK, 4));
            add(new ItemStack(Items.STICK, 5));
            Collections.shuffle(this);
        }
    }),
    RARE(new ArrayList<>() {
        {
            add(new ItemStack(Items.STICK, 1));
            add(new ItemStack(Items.STICK, 2));
            add(new ItemStack(Items.STICK, 3));
            add(new ItemStack(Items.STICK, 4));
            add(new ItemStack(Items.STICK, 5));
            Collections.shuffle(this);
        }
    }),
    EPIC(new ArrayList<>() {
        {
            add(new ItemStack(Items.STICK, 1));
            add(new ItemStack(Items.STICK, 2));
            add(new ItemStack(Items.STICK, 3));
            add(new ItemStack(Items.STICK, 4));
            add(new ItemStack(Items.STICK, 5));
            Collections.shuffle(this);
        }
    }),
    LEGENDARY(new ArrayList<>() {
        {
            add(new ItemStack(Items.STICK, 1));
            add(new ItemStack(Items.STICK, 2));
            add(new ItemStack(Items.STICK, 3));
            add(new ItemStack(Items.STICK, 4));
            add(new ItemStack(Items.STICK, 5));
            Collections.shuffle(this);
        }
    });

    private final ArrayList<ItemStack> items;

    MisteryBoxRarity(ArrayList<ItemStack> items) {
        this.items = items;
    }

    public static ItemStack getFromRarity(List<Item> list) {
        return list.get(new Random().nextInt(list.size())).getDefaultStack();
    }

    public ArrayList<ItemStack> getItems() {
        return items;
    }
}
