package io.github.vicen621.loriath.common.loot.function;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.gson.*;
import com.mojang.logging.LogUtils;
import io.github.vicen621.loriath.common.init.ModLootFunctions;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.function.ConditionalLootFunction;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;

import java.util.Collection;
import java.util.List;

public class EnchantRandomlyWithLevelLootFunction extends ConditionalLootFunction {
    private static final Logger LOGGER = LogUtils.getLogger();
    final List<Enchantment> enchantments;
    final int level;
    final boolean randomSelection;

    EnchantRandomlyWithLevelLootFunction(LootCondition[] conditions, Collection<Enchantment> enchantments, int level, boolean randomSelection) {
        super(conditions);
        this.enchantments = ImmutableList.copyOf(enchantments);
        this.level = level;
        this.randomSelection = randomSelection;
    }

    private static ItemStack addEnchantmentToStack(ItemStack stack, Enchantment enchantment, int level) {
        int i = level == -1 ? enchantment.getMaxLevel() : level;
        if (stack.isOf(Items.BOOK)) {
            stack = new ItemStack(Items.ENCHANTED_BOOK);
            EnchantedBookItem.addEnchantment(stack, new EnchantmentLevelEntry(enchantment, i));
        } else {
            stack.addEnchantment(enchantment, i);
        }

        return stack;
    }

    public LootFunctionType getType() {
        return ModLootFunctions.ENCHANT_RANDOMLY_WITH_LEVEL;
    }

    public ItemStack process(ItemStack stack, LootContext context) {
        Random random = context.getRandom();
        Enchantment enchantment;
        if (this.enchantments.isEmpty()) {
            boolean bl = stack.isOf(Items.BOOK);
            List<Enchantment> list = Registry.ENCHANTMENT.stream()
                    .filter(ench -> randomSelection == ench.isAvailableForRandomSelection())
                    .filter(ench -> bl || ench.isAcceptableItem(stack))
                    .filter(ench -> this.level != -1 && ench.getMaxLevel() >= this.level).toList();

            if (list.isEmpty()) {
                LOGGER.warn("Couldn't find a compatible enchantment for {}", stack);
                return stack;
            }

            enchantment = list.get(random.nextInt(list.size()));
        } else {
            enchantment = this.enchantments.get(random.nextInt(this.enchantments.size()));
        }

        return addEnchantmentToStack(stack, enchantment, this.level);
    }

    public static class Serializer extends ConditionalLootFunction.Serializer<EnchantRandomlyWithLevelLootFunction> {

        public void toJson(JsonObject jsonObject, EnchantRandomlyWithLevelLootFunction enchantRandomlyLootFunction, JsonSerializationContext jsonSerializationContext) {
            super.toJson(jsonObject, enchantRandomlyLootFunction, jsonSerializationContext);
            if (!enchantRandomlyLootFunction.enchantments.isEmpty()) {
                JsonArray jsonArray = new JsonArray();

                for (Enchantment enchantment : enchantRandomlyLootFunction.enchantments) {
                    Identifier identifier = Registry.ENCHANTMENT.getId(enchantment);
                    if (identifier == null) {
                        throw new IllegalArgumentException("Don't know how to serialize enchantment " + enchantment);
                    }

                    jsonArray.add(new JsonPrimitive(identifier.toString()));
                }

                jsonObject.add("enchantments", jsonArray);
            }
            jsonObject.addProperty("level", enchantRandomlyLootFunction.level);
            jsonObject.addProperty("selection", enchantRandomlyLootFunction.randomSelection);
        }

        public EnchantRandomlyWithLevelLootFunction fromJson(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext, LootCondition[] lootConditions) {
            List<Enchantment> list = Lists.newArrayList();
            int level = JsonHelper.getInt(jsonObject, "level", 1);
            boolean randomSelection = JsonHelper.getBoolean(jsonObject, "selection", true);
            if (jsonObject.has("enchantments")) {
                JsonArray jsonArray = JsonHelper.getArray(jsonObject, "enchantments");

                for (JsonElement jsonElement : jsonArray) {
                    String string = JsonHelper.asString(jsonElement, "enchantment");
                    Enchantment enchantment = Registry.ENCHANTMENT.getOrEmpty(new Identifier(string)).orElseThrow(() -> new JsonSyntaxException("Unknown enchantment '" + string + "'"));
                    list.add(enchantment);
                }
            }

            return new EnchantRandomlyWithLevelLootFunction(lootConditions, list, level, randomSelection);
        }
    }
}