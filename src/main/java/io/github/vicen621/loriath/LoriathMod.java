package io.github.vicen621.loriath;

import io.github.vicen621.loriath.accessories.Dash;
import io.github.vicen621.loriath.item.CustomItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoriathMod implements ModInitializer {
    public static final String MOD_ID = "loriath";
    public static final Logger LOGGER = LoggerFactory.getLogger("Loriath");

    @Override
    public void onInitialize() {
        CustomItems.registerModItems();

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
}
