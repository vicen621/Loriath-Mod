package io.github.vicen621.loriath.common.item.accessories.items.feet;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import io.github.vicen621.loriath.common.item.accessories.AccessoryItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;

import java.util.UUID;

public class HermesBootsItem extends AccessoryItem {

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack,
                                                                           SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = super.getModifiers(stack, slot, entity, uuid);
        modifiers.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(uuid,
                "loriath:movement_speed", 0.04, EntityAttributeModifier.Operation.ADDITION));
        return modifiers;
    }
}
