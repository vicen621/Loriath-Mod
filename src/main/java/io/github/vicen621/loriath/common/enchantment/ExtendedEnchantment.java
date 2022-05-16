package io.github.vicen621.loriath.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class ExtendedEnchantment extends Enchantment {

    protected ExtendedEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
    }

    protected ExtendedEnchantment(Rarity rarity, EnchantmentTarget enchantmentCategory, EquipmentSlot equipmentSlot) {
        this(rarity, enchantmentCategory, new EquipmentSlot[]{equipmentSlot});
    }

    public int getEnchantmentLevel(ItemStack itemStack) {
        return EnchantmentHelper.getLevel(this, itemStack);
    }

    public int getEnchantmentLevel(LivingEntity entity) {
        return EnchantmentHelper.getEquipmentLevel(this, entity);
    }

    public boolean hasEnchantment(ItemStack itemStack) {
        return this.getEnchantmentLevel(itemStack) > 0;
    }

    public boolean hasEnchantment(LivingEntity entity) {
        return this.getEnchantmentLevel(entity) > 0;
    }
}
