package io.github.vicen621.loriath.datagen.providers;

import io.github.vicen621.loriath.Loriath;
import io.github.vicen621.loriath.common.init.ModHats;
import io.github.vicen621.loriath.common.item.trinkets.accessories.AccessoryRarity;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class LoriathItemProvider extends FabricTagProvider<Item> {
    private static final TagKey<Item> COMMON_ACCESSORY = TagKey.of(Registries.ITEM.getKey(), Loriath.id("common_accessory"));
    private static final TagKey<Item> UNCOMMON_ACCESSORY = TagKey.of(Registries.ITEM.getKey(), Loriath.id("uncommon_accessory"));
    private static final TagKey<Item> RARE_ACCESSORY = TagKey.of(Registries.ITEM.getKey(), Loriath.id("rare_accessory"));
    private static final TagKey<Item> EPIC_ACCESSORY = TagKey.of(Registries.ITEM.getKey(), Loriath.id("epic_accessory"));
    private static final TagKey<Item> LEGENDARY_ACCESSORY = TagKey.of(Registries.ITEM.getKey(), Loriath.id("legendary_accessory"));
    public static final TagKey<Item> HATS = TagKey.of(Registries.ITEM.getKey(), Loriath.id("hats"));
    private static final TagKey<Item> CONCRETE_POWDER = TagKey.of(Registries.ITEM.getKey(), new Identifier("concrete_powder"));
    private static final TagKey<Item> GLAZED_TERRACOTTA = TagKey.of(Registries.ITEM.getKey(), new Identifier("glazed_terracotta"));

    public LoriathItemProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> future) {
        super(dataOutput, RegistryKey.ofRegistry(new Identifier("item")), future);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup lookup) {
        getOrCreateTagBuilder(COMMON_ACCESSORY).add(AccessoryRarity.COMMON.getItems().toArray(Item[]::new));
        getOrCreateTagBuilder(UNCOMMON_ACCESSORY).add(AccessoryRarity.UNCOMMON.getItems().toArray(Item[]::new));
        getOrCreateTagBuilder(RARE_ACCESSORY).add(AccessoryRarity.RARE.getItems().toArray(Item[]::new));
        getOrCreateTagBuilder(EPIC_ACCESSORY).add(AccessoryRarity.EPIC.getItems().toArray(Item[]::new));
        getOrCreateTagBuilder(LEGENDARY_ACCESSORY).add(AccessoryRarity.LEGENDARY.getItems().toArray(Item[]::new));
        getOrCreateTagBuilder(CONCRETE_POWDER)
                .add(Items.BLACK_CONCRETE_POWDER)
                .add(Items.BLUE_CONCRETE_POWDER)
                .add(Items.BROWN_CONCRETE_POWDER)
                .add(Items.CYAN_CONCRETE_POWDER)
                .add(Items.GRAY_CONCRETE_POWDER)
                .add(Items.GREEN_CONCRETE_POWDER)
                .add(Items.LIGHT_BLUE_CONCRETE_POWDER)
                .add(Items.LIGHT_GRAY_CONCRETE_POWDER)
                .add(Items.LIME_CONCRETE_POWDER)
                .add(Items.MAGENTA_CONCRETE_POWDER)
                .add(Items.ORANGE_CONCRETE_POWDER)
                .add(Items.PINK_CONCRETE_POWDER)
                .add(Items.PURPLE_CONCRETE_POWDER)
                .add(Items.RED_CONCRETE_POWDER)
                .add(Items.WHITE_CONCRETE_POWDER)
                .add(Items.YELLOW_CONCRETE_POWDER);


        getOrCreateTagBuilder(GLAZED_TERRACOTTA)
                .add(Items.BLACK_GLAZED_TERRACOTTA)
                .add(Items.BLUE_GLAZED_TERRACOTTA)
                .add(Items.BROWN_GLAZED_TERRACOTTA)
                .add(Items.CYAN_GLAZED_TERRACOTTA)
                .add(Items.GRAY_GLAZED_TERRACOTTA)
                .add(Items.GREEN_GLAZED_TERRACOTTA)
                .add(Items.LIGHT_BLUE_GLAZED_TERRACOTTA)
                .add(Items.LIGHT_GRAY_GLAZED_TERRACOTTA)
                .add(Items.LIME_GLAZED_TERRACOTTA)
                .add(Items.MAGENTA_GLAZED_TERRACOTTA)
                .add(Items.ORANGE_GLAZED_TERRACOTTA)
                .add(Items.PINK_GLAZED_TERRACOTTA)
                .add(Items.PURPLE_GLAZED_TERRACOTTA)
                .add(Items.RED_GLAZED_TERRACOTTA)
                .add(Items.WHITE_GLAZED_TERRACOTTA)
                .add(Items.YELLOW_GLAZED_TERRACOTTA)
                .addOptionalTag(ItemTags.DIRT);

        getOrCreateTagBuilder(HATS)
                .add(ModHats.HATS);
    }
}
