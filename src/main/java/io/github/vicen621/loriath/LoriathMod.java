package io.github.vicen621.loriath;

import io.github.vicen621.loriath.common.init.ModSoundEvents;
import io.github.vicen621.loriath.common.item.accessories.items.extra.Dash;
import io.github.vicen621.loriath.common.init.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.EntityLootTableGenerator;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.condition.RandomChanceWithLootingLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.FurnaceSmeltLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.entity.EntityFlagsPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoriathMod implements ModInitializer {
    public static final String MODID = "loriath";
    public static final Logger LOGGER = LoggerFactory.getLogger("Loriath");

    @Override
    public void onInitialize() {
        ModItems.registerModItems();
        ModSoundEvents.registerModSounds();

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

            if (id.getPath().equals("entities/cow")) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .withCondition(KilledByPlayerLootCondition.builder().build())
                        .with(ItemEntry.builder(ModItems.EVERLASTING_BEEF).apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, EntityPredicate.Builder.create().flags(EntityFlagsPredicate.Builder.create().onFire(true).build())))))
                        .withCondition(RandomChanceLootCondition.builder(0.002f).build());
                table.pool(poolBuilder);
            }
        });
        new Dash();
    }

    public static Identifier id(String path) {
        return new Identifier(MODID, path);
    }
}
