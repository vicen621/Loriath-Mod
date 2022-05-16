package io.github.vicen621.loriath.common.enchantment.targets;

import com.github.crimsondawn45.fabricshieldlib.mixin.EnchantmentTargetMixin;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.TridentItem;

public class MeleeWeaponTarget extends EnchantmentTargetMixin {

    @Override
    public boolean method_8177(Item item) {
        return item instanceof SwordItem || item instanceof AxeItem || item instanceof TridentItem;
    }
}
