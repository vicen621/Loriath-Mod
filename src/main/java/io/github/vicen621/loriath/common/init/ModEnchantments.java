package io.github.vicen621.loriath.common.init;

import io.github.vicen621.loriath.LoriathMod;
import io.github.vicen621.loriath.common.enchantment.ExtendedEnchantment;
import io.github.vicen621.loriath.common.enchantment.types.DeathWishEnchantment;
import io.github.vicen621.loriath.common.enchantment.types.DodgeEnchantment;
import io.github.vicen621.loriath.common.enchantment.types.ElderGuardianFavorEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.registry.Registry;

public class ModEnchantments {

    public static Enchantment DEATH_WISH = register(new DeathWishEnchantment());
    public static Enchantment DODGE = register(new DodgeEnchantment());
    public static Enchantment ELDER_GUARDIAN_FAVOR = register(new ElderGuardianFavorEnchantment());

    private static Enchantment register(ExtendedEnchantment ench) {
        return Registry.register(Registry.ENCHANTMENT, LoriathMod.id(ench.getRegisterName()), ench);
    }

    public static void registerModSounds() {
        LoriathMod.LOGGER.info("Registering Sounds for " + LoriathMod.MODID);
    }
}
