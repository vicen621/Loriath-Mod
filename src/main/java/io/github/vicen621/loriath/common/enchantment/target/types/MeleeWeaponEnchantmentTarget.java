package io.github.vicen621.loriath.common.enchantment.target.types;

import io.github.vicen621.loriath.mixin.enchantments.EnchantmentTargetMixin;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.TridentItem;

public class MeleeWeaponEnchantmentTarget extends EnchantmentTargetMixin {
    @Override
    public boolean isAcceptableItem(Item item) {
        return item instanceof SwordItem || item instanceof AxeItem || item instanceof TridentItem;
    }
}