package io.github.vicen621.loriath.common.item;

import io.github.vicen621.loriath.common.item.accessories.AbstractAccessoryItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EverlastingFoodItem extends AbstractAccessoryItem {

    public EverlastingFoodItem(FoodComponent food) {
        super(new Item.Settings().food(food));
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity entity) {
        if (!world.isClient && entity instanceof PlayerEntity player) {
            player.getItemCooldownManager().set(this, 300); // 5 seconds
        }

        stack.damage(1, entity, damager -> {});

        // Stack decrement is cancelled in LivingEntity.eatFood() mixin
        return super.finishUsing(stack, world, entity);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 32;
    }
}
