package io.github.vicen621.loriath.common.init;

import io.github.vicen621.loriath.LoriathMod;
import io.github.vicen621.loriath.common.item.mysteryBoxes.MysteryBoxItem;
import io.github.vicen621.loriath.common.item.mysteryBoxes.MysteryBoxRarity;
import io.github.vicen621.loriath.common.item.trinkets.accessories.AccessoryItem;
import io.github.vicen621.loriath.common.item.trinkets.accessories.items.cape.InvisibilityCloakItem;
import io.github.vicen621.loriath.common.item.trinkets.accessories.items.extra.*;
import io.github.vicen621.loriath.common.item.trinkets.accessories.items.feet.FrogLegItem;
import io.github.vicen621.loriath.common.item.trinkets.accessories.items.feet.HermesBootsItem;
import io.github.vicen621.loriath.common.item.trinkets.accessories.items.hand.DiggingClawsItem;
import io.github.vicen621.loriath.common.item.trinkets.accessories.items.hand.TitanGloveItem;
import io.github.vicen621.loriath.common.item.trinkets.accessories.items.head.DivingGearItem;
import io.github.vicen621.loriath.common.item.trinkets.accessories.items.head.FrogFlipperItem;
import io.github.vicen621.loriath.common.item.trinkets.accessories.items.necklace.PanicNecklaceItem;
import io.github.vicen621.loriath.common.item.tools.CustomAxeItem;
import io.github.vicen621.loriath.common.item.tools.LastHopeTool;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.registry.Registry;


//TODO Fijarse sailor hat
public class ModItems {
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(LoriathMod.id("loriath"))
            .icon(() -> new ItemStack(ModItems.MARICOIN))
            .build();

    public static final Item ADHESIVE_BANDAGE = register("adhesive_bandage", new AdhesiveBandageItem());
    public static final Item BEZOAR = register("bezoar", new BezoarItem());
    //TODO
    public static final Item DESTROYER_EMBLEM = register("destroyer_emblem", new AccessoryItem());
    public static final Item CLOAK_OF_INVISIBILITY = register("cloak_of_invisibility", new InvisibilityCloakItem());
    public static final Item FAST_CLOCK = register("fast_clock", new FastClockItem());
    public static final Item LAVA_CHARM = register("lava_charm", new LavaCharmItem());
    public static final Item MEDICATED_BANDAGE = register("medicated_bandage", new MedicatedBandageItem());
    public static final Item SHACKLE = register("shackle", new ShackleItem());
    public static final Item SHINY_STONE = register("shiny_stone", new ShinyStoneItem());
    public static final Item FROG_LEG = register("frog_leg", new FrogLegItem());
    public static final Item HERMES_BOOTS = register("hermes_boots", new HermesBootsItem());
    public static final Item DIGGING_CLAWS = register("digging_claws", new DiggingClawsItem());
    public static final Item TITAN_GLOVE = register("titan_glove", new TitanGloveItem());
    public static final Item DIVING_GEAR = register("diving_gear", new DivingGearItem());
    public static final Item FROG_FLIPPER = register("frog_flipper", new FrogFlipperItem());
    public static final Item PANIC_NECKLACE = register("panic_necklace", new PanicNecklaceItem());

    // public static final Item LAST_HOPE = register("last_hope", new CustomAxeItem(LastHopeTool.INSTANCE, 11, -3f, new FabricItemSettings().group(ITEM_GROUP)));

    public static final Item MARICOIN = register("maricoin", new Item(new FabricItemSettings().group(ITEM_GROUP).maxCount(16)));
    // public static final Item INFINITE_TORCH = register("infinite_torch", new InfiniteTorchItem(new FabricItemSettings().group(ITEM_GROUP).maxCount(1)));

    public static final Item LEGENDARY_MYSTERY_BOX = new MysteryBoxItem(MysteryBoxRarity.LEGENDARY, new FabricItemSettings().group(ITEM_GROUP).maxCount(1));
    public static final Item EPIC_MYSTERY_BOX = new MysteryBoxItem(MysteryBoxRarity.EPIC, new FabricItemSettings().group(ITEM_GROUP).maxCount(1));
    public static final Item RARE_MYSTERY_BOX = new MysteryBoxItem(MysteryBoxRarity.RARE, new FabricItemSettings().group(ITEM_GROUP).maxCount(1));
    public static final Item UNCOMMON_MYSTERY_BOX = new MysteryBoxItem(MysteryBoxRarity.UNCOMMON, new FabricItemSettings().group(ITEM_GROUP).maxCount(1));
    public static final Item COMMON_MYSTERY_BOX = new MysteryBoxItem(MysteryBoxRarity.COMMON, new FabricItemSettings().group(ITEM_GROUP).maxCount(1));
    public static final Item COMESTIC_MYSTERY_BOX = new MysteryBoxItem(MysteryBoxRarity.COSMETIC, new FabricItemSettings().group(ITEM_GROUP).maxCount(8));

    private static Item register(String name, Item item) {
        return Registry.register(Registry.ITEM, LoriathMod.id(name), item);
    }

    public static void registerModItems() {
        LoriathMod.LOGGER.info("Registering Items for " + LoriathMod.MODID);
    }
}
