package io.github.vicen621.loriath.datagen.providers;

import io.github.vicen621.loriath.LoriathMod;
import io.github.vicen621.loriath.common.item.accessories.AccessoryRarity;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;

public class LoriathItemProvider extends FabricTagProvider<Item> {
    TagKey<Item> COMMON_ACCESSORIES = TagKey.of(Registry.ITEM_KEY, LoriathMod.id("common_accessories"));
    TagKey<Item> UNCOMMON_ACCESSORIES = TagKey.of(Registry.ITEM_KEY, LoriathMod.id("uncommon_accessories"));
    TagKey<Item> RARE_ACCESSORIES = TagKey.of(Registry.ITEM_KEY, LoriathMod.id("rare_accessories"));
    TagKey<Item> EPIC_ACCESSORIES = TagKey.of(Registry.ITEM_KEY, LoriathMod.id("epic_accessories"));
    TagKey<Item> LEGENDARY_ACCESSORIES = TagKey.of(Registry.ITEM_KEY, LoriathMod.id("legendary_accessories"));

    /**
     * Construct a new {@link FabricTagProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided. For example @see BlockTagProvider
     *
     * @param dataGenerator The data generator instance
     */
    public LoriathItemProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator, Registry.ITEM);
    }

    @Override
    protected void generateTags() {
        getOrCreateTagBuilder(COMMON_ACCESSORIES).add(AccessoryRarity.COMMON.getItems().toArray(Item[]::new));
        getOrCreateTagBuilder(UNCOMMON_ACCESSORIES).add(AccessoryRarity.UNCOMMON.getItems().toArray(Item[]::new));
        getOrCreateTagBuilder(RARE_ACCESSORIES).add(AccessoryRarity.RARE.getItems().toArray(Item[]::new));
        getOrCreateTagBuilder(EPIC_ACCESSORIES).add(AccessoryRarity.EPIC.getItems().toArray(Item[]::new));
        getOrCreateTagBuilder(LEGENDARY_ACCESSORIES).add(AccessoryRarity.LEGENDARY.getItems().toArray(Item[]::new));
    }
}
