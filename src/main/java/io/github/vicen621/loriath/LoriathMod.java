package io.github.vicen621.loriath;

import io.github.vicen621.loriath.common.init.ModEnchantments;
import io.github.vicen621.loriath.common.init.ModItems;
import io.github.vicen621.loriath.common.init.ModParticles;
import io.github.vicen621.loriath.common.init.ModSoundEvents;
import io.github.vicen621.loriath.common.item.accessories.items.extra.Dash;
import io.github.vicen621.loriath.utils.TimeHelper;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoriathMod implements ModInitializer {
    public static final String MODID = "loriath";
    public static final Logger LOGGER = LoggerFactory.getLogger("Loriath");

    public static final boolean DEBUG = true;

    public static Identifier id(String path) {
        return new Identifier(MODID, path);
    }

    @Override
    public void onInitialize() {
        ModItems.registerModItems();
        ModSoundEvents.registerModSounds();
        ModEnchantments.registerModSounds();
        ModParticles.registerModParticles();

        new TimeHelper();
        events();
        LOGGER.info("Finished loading LoriathMod");
    }

    private void events() {
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (id.getPath().contains("chest")) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .withCondition(RandomChanceLootCondition.builder(0.07F).build())
                        .rolls(UniformLootNumberProvider.create(1, 3))
                        .with(ItemEntry.builder(ModItems.MARICOIN));

                table.pool(poolBuilder);
            }
        });
        new Dash();
    }
}
