package io.github.vicen621.loriath.init;

import io.github.vicen621.loriath.Loriath;
import io.github.vicen621.loriath.enchantment.ExtendedEnchantment;
import io.github.vicen621.loriath.enchantment.types.*;
import io.github.vicen621.loriath.enchantment.types.curses.IncompatibilityCurseEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModEnchantments {

    public static Enchantment DEATH_WISH = register(new DeathWishEnchantment());
    public static Enchantment DODGE = register(new DodgeEnchantment());
    public static Enchantment ELDER_GUARDIAN_FAVOR = register(new ElderGuardianFavorEnchantment());
    public static Enchantment PHOENIX_DIVE = register(new PhoenixDiveEnchantment());
    public static Enchantment SMELTER = register(new SmelterEnchantment());
    public static Enchantment VITALITY = register(new VitalityEnchantment());
    public static Enchantment IMMORTALITY = register(new ImmortalityEnchantment());
    public static Enchantment INCOMPATIBILITY_CURSE = register(new IncompatibilityCurseEnchantment());

    private static Enchantment register(ExtendedEnchantment ench) {
        return Registry.register(Registries.ENCHANTMENT, Loriath.id(ench.getRegisterName()), ench);
    }

    public static void registerModEnchantments() {
        Loriath.LOGGER.info("Registering Enchantments for " + Loriath.MODID);
    }
}
