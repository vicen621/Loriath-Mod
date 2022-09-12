package io.github.vicen621.loriath.common.enchantment;

import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public abstract class ExtendedCurse extends ExtendedEnchantment {

    public ExtendedCurse(String registerName, Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(registerName, weight, type, slotTypes);
    }

    protected ExtendedCurse(String registerName, Rarity rarity, EnchantmentTarget enchantmentCategory, EquipmentSlot equipmentSlot) {
        this(registerName, rarity, enchantmentCategory, new EquipmentSlot[]{equipmentSlot});
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    public boolean isCursed() {
        return true;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return false;
    }
}
