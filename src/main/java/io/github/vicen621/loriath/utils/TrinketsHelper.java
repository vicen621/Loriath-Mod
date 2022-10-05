package io.github.vicen621.loriath.utils;

import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import io.github.vicen621.loriath.common.item.trinkets.accessories.AccessoryItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Pair;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TrinketsHelper {

    public static List<ItemStack> getEquippedAccessories(LivingEntity entity) {
        return TrinketsApi.getTrinketComponent(entity).stream()
                .flatMap(comp -> comp.getAllEquipped().stream())
                .map(Pair::getRight)
                .filter(stack -> !stack.isEmpty() && stack.getItem() instanceof AccessoryItem)
                .collect(Collectors.toList());
    }

    public static boolean isEquipped(Item item, LivingEntity entity) {
        return isEquipped(stack -> stack.getItem().equals(item), entity);
    }

    public static boolean isEquipped(Predicate<ItemStack> filter, LivingEntity entity) {
        return TrinketsApi.getTrinketComponent(entity)
                .map(comp -> comp.isEquipped(filter))
                .orElse(false);
    }

    public static List<ItemStack> getAllEquippedForSlot(LivingEntity entity, String groupId, String slotId) {
        return TrinketsApi.getTrinketComponent(entity)
                .map(TrinketComponent::getInventory)
                .flatMap(invByGroup -> Optional.ofNullable(invByGroup.get(groupId)))
                .flatMap(invBySlot -> Optional.ofNullable(invBySlot.get(slotId)))
                .stream()
                .flatMap(inv -> IntStream.range(0, inv.size()).mapToObj(inv::getStack))
                .filter(stack -> stack.getItem() instanceof AccessoryItem)
                .collect(Collectors.toList());
    }
}
