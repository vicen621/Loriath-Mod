package io.github.vicen621.loriath.init.loottables;

import io.github.vicen621.loriath.Loriath;
import io.github.vicen621.loriath.loot.function.EnchantRandomlyWithLevelLootFunction;
import io.github.vicen621.loriath.loot.function.SetRandomPotionLootFunction;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.util.JsonSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModLootFunctions {

    public static final LootFunctionType ENCHANT_RANDOMLY_WITH_LEVEL = register("enchant_randomly_with_levels", new EnchantRandomlyWithLevelLootFunction.Serializer());
    public static final LootFunctionType SET_RANDOM_POTION = register("set_random_potion", new SetRandomPotionLootFunction.Serializer());

    private static LootFunctionType register(String id, JsonSerializer<? extends LootFunction> jsonSerializer) {
        return Registry.register(Registries.LOOT_FUNCTION_TYPE, Loriath.id(id), new LootFunctionType(jsonSerializer));
    }

    public static void registerLootFunctions() {
        Loriath.LOGGER.info("Registering Loot Functions for " + Loriath.MODID);
    }
}
