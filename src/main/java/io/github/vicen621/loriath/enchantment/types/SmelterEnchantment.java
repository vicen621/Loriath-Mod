package io.github.vicen621.loriath.enchantment.types;

import io.github.vicen621.loriath.enchantment.ExtendedEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;

public class SmelterEnchantment extends ExtendedEnchantment {
    public SmelterEnchantment() {
        super("smelter", Rarity.RARE, EnchantmentTarget.DIGGER, EquipmentSlot.MAINHAND);

        setDifferenceBetweenMinimumAndMaximum(50);
        setMinimumEnchantabilityCalculator(level -> 20);
    }

    public boolean canAccept(Enchantment other) {
        return super.canAccept(other) && other != Enchantments.SILK_TOUCH;
    }
}
