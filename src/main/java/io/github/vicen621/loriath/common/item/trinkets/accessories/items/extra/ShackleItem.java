package io.github.vicen621.loriath.common.item.trinkets.accessories.items.extra;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import io.github.vicen621.loriath.common.item.trinkets.accessories.AccessoryItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;

import java.util.UUID;

public class ShackleItem extends AccessoryItem {

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack,
                                                                           SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = super.getModifiers(stack, slot, entity, uuid);
        modifiers.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(uuid,
                "loriath:armor", 2, EntityAttributeModifier.Operation.ADDITION));
        return modifiers;
    }
}
