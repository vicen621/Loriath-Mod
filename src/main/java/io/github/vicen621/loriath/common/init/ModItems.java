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
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;


//TODO Fijarse sailor hat
public class ModItems {
    public static final ItemGroup ITEM_GROUP = Registry.register(Registries.ITEM_GROUP, LoriathMod.id("loriath"),
            FabricItemGroup.builder().displayName(Text.translatable("itemGroup.loriath.loriath"))
            .icon(() -> new ItemStack(ModItems.MARICOIN)).entries(((displayContext, entries) -> {
                for (Item item : ModItems.ITEMS) {
                    entries.add(() -> new ItemStack(item).getItem());
                }
            }))
            .build());

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

    public static final Item MARICOIN = register("maricoin", new Item(new FabricItemSettings().maxCount(16)));
    // public static final Item INFINITE_TORCH = register("infinite_torch", new InfiniteTorchItem(new FabricItemSettings().group(ITEM_GROUP).maxCount(1)));

    public static final Item LEGENDARY_MYSTERY_BOX = new MysteryBoxItem(MysteryBoxRarity.LEGENDARY, new FabricItemSettings().maxCount(1));
    public static final Item EPIC_MYSTERY_BOX = new MysteryBoxItem(MysteryBoxRarity.EPIC, new FabricItemSettings().maxCount(1));
    public static final Item RARE_MYSTERY_BOX = new MysteryBoxItem(MysteryBoxRarity.RARE, new FabricItemSettings().maxCount(1));
    public static final Item UNCOMMON_MYSTERY_BOX = new MysteryBoxItem(MysteryBoxRarity.UNCOMMON, new FabricItemSettings().maxCount(1));
    public static final Item COMMON_MYSTERY_BOX = new MysteryBoxItem(MysteryBoxRarity.COMMON, new FabricItemSettings().maxCount(1));
    public static final Item COMESTIC_MYSTERY_BOX = new MysteryBoxItem(MysteryBoxRarity.COSMETIC, new FabricItemSettings().maxCount(8));

    public static final Item[] ITEMS = new Item[]{
            ADHESIVE_BANDAGE,
            BEZOAR,
            DESTROYER_EMBLEM,
            CLOAK_OF_INVISIBILITY,
            FAST_CLOCK,
            LAVA_CHARM,
            MEDICATED_BANDAGE,
            SHACKLE,
            SHINY_STONE,
            FROG_LEG,
            HERMES_BOOTS,
            DIGGING_CLAWS,
            TITAN_GLOVE,
            DIVING_GEAR,
            FROG_FLIPPER,
            PANIC_NECKLACE,
            MARICOIN,
            LEGENDARY_MYSTERY_BOX,
            EPIC_MYSTERY_BOX,
            RARE_MYSTERY_BOX,
            UNCOMMON_MYSTERY_BOX,
            COMMON_MYSTERY_BOX,
            COMESTIC_MYSTERY_BOX
    };

    private static Item register(String name, Item item) {
        return Registry.register(Registries.ITEM, LoriathMod.id(name), item);
    }

    public static void registerModItems() {
        LoriathMod.LOGGER.info("Registering Items for " + LoriathMod.MODID);
    }
}
