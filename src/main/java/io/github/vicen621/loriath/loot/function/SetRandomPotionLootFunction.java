package io.github.vicen621.loriath.loot.function;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.gson.*;
import io.github.vicen621.loriath.init.loottables.ModLootFunctions;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.function.ConditionalLootFunction;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.registry.Registries;

import java.util.Collection;
import java.util.List;

//strong: -1 = No hay strong potions | 0 = Todas las pociones | 1 = Solo Strong potions
public class SetRandomPotionLootFunction extends ConditionalLootFunction {
    final List<Potion> potions;
    final int strong;

    SetRandomPotionLootFunction(LootCondition[] conditions, Collection<Potion> potions, int strong) {
        super(conditions);
        this.potions = ImmutableList.copyOf(potions);
        this.strong = strong;
    }

    public LootFunctionType getType() {
        return ModLootFunctions.SET_RANDOM_POTION;
    }

    public ItemStack process(ItemStack stack, LootContext context) {
        Random random = context.getRandom();
        Potion potion;

        if (this.potions.isEmpty()) {
            List<Potion> list = Registries.POTION.stream().toList();

            if (strong == -1)
                list = list.stream().filter(pot -> !Registries.POTION.getId(pot).getPath().contains("strong")).toList();
            else if (strong == 1)
                list = list.stream().filter(pot -> Registries.POTION.getId(pot).getPath().contains("strong")).toList();

            potion = list.get(random.nextInt(list.size()));
        } else {
            potion = this.potions.get(random.nextInt(this.potions.size()));
        }

        PotionUtil.setCustomPotionEffects(stack, potion.getEffects());
        return stack;
    }

    public static class Serializer extends ConditionalLootFunction.Serializer<SetRandomPotionLootFunction> {

        public void toJson(JsonObject jsonObject, SetRandomPotionLootFunction setRandomPotionLootFunction, JsonSerializationContext jsonSerializationContext) {
            super.toJson(jsonObject, setRandomPotionLootFunction, jsonSerializationContext);
            if (!setRandomPotionLootFunction.potions.isEmpty()) {
                JsonArray jsonArray = new JsonArray();

                for (Potion potion : setRandomPotionLootFunction.potions) {
                    Identifier identifier = Registries.POTION.getId(potion);
                    jsonArray.add(new JsonPrimitive(identifier.toString()));
                }

                jsonObject.add("potions", jsonArray);
            }
            jsonObject.addProperty("strong", setRandomPotionLootFunction.strong);
        }

        public SetRandomPotionLootFunction fromJson(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext, LootCondition[] lootConditions) {
            List<Potion> list = Lists.newArrayList();
            int strong = JsonHelper.getInt(jsonObject, "strong", 0);
            if (jsonObject.has("potions")) {
                JsonArray jsonArray = JsonHelper.getArray(jsonObject, "potions");

                for (JsonElement jsonElement : jsonArray) {
                    String string = JsonHelper.asString(jsonElement, "potion");
                    Potion potion = Registries.POTION.getOrEmpty(new Identifier(string)).orElseThrow(() -> new JsonSyntaxException("Unknown potion '" + string + "'"));
                    list.add(potion);
                }
            }
            return new SetRandomPotionLootFunction(lootConditions, list, strong);
        }
    }
}