package io.github.vicen621.loriath.item.trinkets.accessories.items.hand;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import io.github.vicen621.loriath.item.trinkets.accessories.AccessoryItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;

import java.util.UUID;

public class TitanGloveItem extends AccessoryItem {
    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack,
                                                                           SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = super.getModifiers(stack, slot, entity, uuid);
        modifiers.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(uuid,
                "loriath:attack_speed", 0.05, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
        modifiers.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(uuid,
                "loriath:attack_damage", 0.3, EntityAttributeModifier.Operation.ADDITION));
        return modifiers;
    }
}
