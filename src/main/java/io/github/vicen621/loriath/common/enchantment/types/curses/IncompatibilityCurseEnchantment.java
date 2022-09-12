package io.github.vicen621.loriath.common.enchantment.types.curses;

import io.github.vicen621.loriath.common.enchantment.ExtendedCurse;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class IncompatibilityCurseEnchantment extends ExtendedCurse {
    public IncompatibilityCurseEnchantment() {
        super("incompatibility_curse", Rarity.RARE, EnchantmentTarget.BREAKABLE,
                new EquipmentSlot[]{
                        EquipmentSlot.HEAD,
                        EquipmentSlot.CHEST,
                        EquipmentSlot.LEGS,
                        EquipmentSlot.FEET,
                        EquipmentSlot.MAINHAND,
                        EquipmentSlot.OFFHAND});

        setMaximumEnchantmentLevel(1);
        setDifferenceBetweenMinimumAndMaximum(40);
        setMinimumEnchantabilityCalculator(level -> 10);
    }

    @Override
    public boolean canAccept(Enchantment other) {
        return false;
    }
}