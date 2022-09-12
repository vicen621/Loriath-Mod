package io.github.vicen621.loriath.common.init;

import io.github.vicen621.loriath.LoriathMod;
import io.github.vicen621.loriath.common.loot.function.EnchantRandomlyWithLevelLootFunction;
import io.github.vicen621.loriath.common.loot.function.SetRandomPotionLootFunction;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.registry.Registry;

public class ModLootFunctions {

    public static final LootFunctionType ENCHANT_RANDOMLY_WITH_LEVEL = register("enchant_randomly_with_levels", new EnchantRandomlyWithLevelLootFunction.Serializer());
    public static final LootFunctionType SET_RANDOM_POTION = register("set_random_potion", new SetRandomPotionLootFunction.Serializer());

    private static LootFunctionType register(String id, JsonSerializer<? extends LootFunction> jsonSerializer) {
        return Registry.register(Registry.LOOT_FUNCTION_TYPE, LoriathMod.id(id), new LootFunctionType(jsonSerializer));
    }

    public static void registerLootFunctions() {
        LoriathMod.LOGGER.info("Registering Loot Functions for " + LoriathMod.MODID);
    }
}
