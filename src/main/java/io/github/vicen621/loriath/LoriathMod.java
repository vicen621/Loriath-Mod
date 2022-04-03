package io.github.vicen621.loriath;

import dev.emi.trinkets.api.TrinketsApi;
import io.github.vicen621.loriath.accessories.AccessoryItem;
import io.github.vicen621.loriath.accessories.Dash;
import io.github.vicen621.loriath.item.CustomItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class LoriathMod implements ModInitializer {
    public static final String MOD_ID = "loriath";
    public static final Logger LOGGER = LoggerFactory.getLogger("Loriath");

    @Override
    public void onInitialize() {
        CustomItems.registerModItems();
        //CustomItems.DASH_SHIELD.getDefaultStack().getOrCreateNbt().putInt("dashShield", 1);

        events();
        LOGGER.info("Finished loading LoriathMod");
    }

    private void events() {
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (id.getPath().contains("chest")) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .withCondition(RandomChanceLootCondition.builder(0.07F).build())
                        .rolls(UniformLootNumberProvider.create(1, 3))
                        .with(ItemEntry.builder(CustomItems.MARICOIN));

                table.pool(poolBuilder);
            }
        });
        new Dash();
    }

    public static List<ItemStack> getEquippedAccessories(LivingEntity entity) {
        return TrinketsApi.getTrinketComponent(entity).stream()
                .flatMap(comp -> comp.getAllEquipped().stream())
                .map(Pair::getRight)
                .filter(stack -> !stack.isEmpty() && stack.getItem() instanceof AccessoryItem)
                .collect(Collectors.toList());
    }
}
