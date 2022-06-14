package io.github.vicen621.loriath.common.enchantment.types;

import io.github.vicen621.loriath.common.enchantment.ExtendedEnchantment;
import io.github.vicen621.loriath.common.events.LivingEvent;
import io.github.vicen621.loriath.utils.AttributeHandler;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;

/**
 * Enchantment that increases the health of the entity.
 */
public class VitalityEnchantment extends ExtendedEnchantment {

    private static final AttributeHandler ATTRIBUTE_HANDLER = new AttributeHandler("575cb29a-1ee4-11eb-adc1-0242ac120002", "VitalityBonus",
            EntityAttributes.GENERIC_MAX_HEALTH, EntityAttributeModifier.Operation.ADDITION
    );

    public VitalityEnchantment() {
        super("vitality", Rarity.UNCOMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});

        setMaximumEnchantmentLevel(3);
        setDifferenceBetweenMinimumAndMaximum(10);
        setMinimumEnchantabilityCalculator(level -> (5 + 8 * level));

        LivingEvent.LivingEntityEquipmentChangeCallback.EVENT.register((entity, slot, from, to) -> {
            ATTRIBUTE_HANDLER.setValueAndApply(entity, getHealthBonus(entity));
        });
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof ShieldItem;
    }

    private int getHealthBonus(LivingEntity entity) {
        int sum = getEnchLevelOfItemClass(this, entity,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND}, ShieldItem.class);

        return sum * 2;
    }

    private <InstanceType> int getEnchLevelOfItemClass(Enchantment enchantment, LivingEntity livingEntity, EquipmentSlot[] slotTypes, Class<InstanceType> type) {
        int sum = 0;

        for (EquipmentSlot slotType : slotTypes) {
            ItemStack itemStack = livingEntity.getEquippedStack(slotType);
            if (type.isInstance(itemStack.getItem()))
                sum += EnchantmentHelper.getLevel(enchantment, itemStack);
        }

        return sum;
    }
}