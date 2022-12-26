package io.github.vicen621.loriath.utils;

import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import io.github.vicen621.loriath.common.item.trinkets.accessories.AccessoryItem;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Pair;
import net.minecraft.util.math.Vec3f;

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

    public static void translateToFace(MatrixStack matrices, EntityModel<? extends LivingEntity> model,
                                       LivingEntity entity, float headYaw, float headPitch) {

        if (entity.isInSwimmingPose() || entity.isFallFlying()) {
            if(model instanceof PlayerEntityModel)
            {
                PlayerEntityModel<AbstractClientPlayerEntity> ctx = (PlayerEntityModel<AbstractClientPlayerEntity>) model;
                matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(ctx.head.roll));
            }
            matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(headYaw));
            matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(-45.0F));
        } else {

            if (entity.isInSneakingPose() && !model.riding) {
                matrices.translate(0.0F, 0.25F, 0.0F);
            }
            matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(headYaw));
            matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(headPitch));
        }
        matrices.translate(0.0F, -0.25F, -0.3F);
    }
}
