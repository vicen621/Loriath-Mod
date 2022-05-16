package io.github.vicen621.loriath.common.init;

import io.github.vicen621.loriath.LoriathMod;
import io.github.vicen621.loriath.common.enchantment.types.DeathWishEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.registry.Registry;

public class ModEnchantments {

    public static Enchantment DEATH_WISH = register("death_wish", new DeathWishEnchantment());

    private static Enchantment register(String name, Enchantment ench) {
        return Registry.register(Registry.ENCHANTMENT, LoriathMod.id(name), ench);
    }

    public static void registerModSounds() {
        LoriathMod.LOGGER.info("Registering Sounds for " + LoriathMod.MODID);
    }
}
