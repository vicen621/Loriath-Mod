package io.github.vicen621.loriath.extensions;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public interface AnimalExtensions {

    boolean loriath$isBreedingItemWithCooldown(ItemStack stack, PlayerEntity player);
}
