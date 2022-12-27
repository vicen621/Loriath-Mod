package io.github.vicen621.loriath.common.init;

import io.github.vicen621.loriath.LoriathMod;
import io.github.vicen621.loriath.common.item.InfiniteTorchItem;
import io.github.vicen621.loriath.common.item.misteryBoxes.MisteryBoxItem;
import io.github.vicen621.loriath.common.item.misteryBoxes.MisteryBoxRarity;
import io.github.vicen621.loriath.common.item.trinkets.accessories.AccessoryItem;
import io.github.vicen621.loriath.common.item.trinkets.accessories.items.cape.InvisibilityCloakItem;
import io.github.vicen621.loriath.common.item.trinkets.accessories.items.extra.*;
import io.github.vicen621.loriath.common.item.trinkets.accessories.items.feet.FrogLegItem;
import io.github.vicen621.loriath.common.item.trinkets.accessories.items.feet.HermesBootsItem;
import io.github.vicen621.loriath.common.item.trinkets.accessories.items.hand.DiggingClawsItem;
import io.github.vicen621.loriath.common.item.trinkets.accessories.items.hand.TitanGLoveItem;
import io.github.vicen621.loriath.common.item.trinkets.accessories.items.head.DivingGearItem;
import io.github.vicen621.loriath.common.item.trinkets.accessories.items.head.FrogFlipperItem;
import io.github.vicen621.loriath.common.item.trinkets.accessories.items.necklace.PanicNecklaceItem;
import io.github.vicen621.loriath.common.item.trinkets.hats.HatItem;
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

    public static final ItemGroup HATS_ITEM_GROUP = FabricItemGroupBuilder.create(LoriathMod.id("hats"))
            .icon(() -> new ItemStack(ModItems.BANDANA))
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
    public static final Item MARICOIN = register("maricoin", new Item(new FabricItemSettings().group(ITEM_GROUP).maxCount(16)));
    public static final Item SHINY_STONE = register("shiny_stone", new ShinyStoneItem());
    public static final Item FROG_LEG = register("frog_leg", new FrogLegItem());
    public static final Item HERMES_BOOTS = register("hermes_boots", new HermesBootsItem());
    public static final Item DIGGING_CLAWS = register("digging_claws", new DiggingClawsItem());
    public static final Item TITAN_GLOVE = register("titan_glove", new TitanGLoveItem());
    public static final Item DIVING_GEAR = register("diving_gear", new DivingGearItem());
    public static final Item FROG_FLIPPER = register("frog_flipper", new FrogFlipperItem());
    public static final Item PANIC_NECKLACE = register("panic_necklace", new PanicNecklaceItem());

    public static final Item GLASSES_3D = register("3d_glasses", new HatItem());
    public static final Item ANGRY_HAT = register("angry_hat", new HatItem());
    public static final Item ANTLERS = register("antlers", new HatItem());
    public static final Item ARCHER_HAT = register("archer_hat", new HatItem());
    public static final Item ARTIST_BERET = register("artist_beret", new HatItem());
    public static final Item ASH_HAT = register("ash_hat", new HatItem());
    public static final Item ASTRO = register("astro", new HatItem());
    public static final Item AVIATOR_HAT = register("aviator_hat", new HatItem());
    public static final Item AVIATORS = register("aviators", new HatItem());
    public static final Item BANDANA = register("bandana", new HatItem());
    public static final Item BETA_BLUE = register("beta_blue", new HatItem());
    public static final Item BETA_LIGHT_GREEN = register("beta_light_green", new HatItem());
    public static final Item BETA_MAGENTA = register("beta_magenta", new HatItem());
    public static final Item BETA_ORANGE = register("beta_orange", new HatItem());
    public static final Item BETA_RAINBOW = register("beta_rainbow", new HatItem());
    public static final Item BETA_RED = register("beta_red", new HatItem());
    public static final Item BETA_WHITE = register("beta_white", new HatItem());
    public static final Item UFO_HAT = register("ufo_hat", new HatItem());
    public static final Item TIKI_MASK = register("tiki_mask", new HatItem());
    public static final Item TOMATO_CAP = register("tomato_cap", new HatItem());
    public static final Item TOP_HAT = register("top_hat", new HatItem());
    public static final Item TURTLE_HAT = register("turtle_hat", new HatItem());
    public static final Item UNICORN_HAT = register("unicorn_hat", new HatItem());
    public static final Item USHANKA = register("ushanka", new HatItem());
    public static final Item VALKYRIE_HELMET = register("valkyrie_helmet", new HatItem());
    public static final Item VIKING_HELMET = register("viking_helmet", new HatItem());
    public static final Item VR = register("vr", new HatItem());
    public static final Item VR_GREEN = register("vr_green", new HatItem());
    public static final Item VR_ORANGE = register("vr_orange", new HatItem());
    public static final Item VR_PURPLE = register("vr_purple", new HatItem());
    public static final Item VR_RED = register("vr_red", new HatItem());
    public static final Item WINGED = register("winged", new HatItem());

    public static final Item INFINITE_TORCH = register("infinite_torch", new InfiniteTorchItem(new FabricItemSettings().group(ITEM_GROUP).maxCount(1)));

    public static final Item[] HATS = {ANGRY_HAT, ANTLERS, ARCHER_HAT, ARTIST_BERET, ASH_HAT, ASTRO, AVIATOR_HAT,
            AVIATORS, BANDANA, BETA_BLUE, BETA_LIGHT_GREEN, BETA_MAGENTA, BETA_ORANGE, BETA_RAINBOW, BETA_RED, BETA_WHITE,
            GLASSES_3D, UFO_HAT, TIKI_MASK, TOMATO_CAP, TOP_HAT, TURTLE_HAT, UNICORN_HAT, USHANKA, VALKYRIE_HELMET, VIKING_HELMET,
            VR, VR_GREEN, VR_ORANGE, VR_PURPLE, VR_RED, WINGED};

    private static Item register(String name, Item item) {
        return Registry.register(Registry.ITEM, LoriathMod.id(name), item);
    }

    public static void registerModItems() {
        LoriathMod.LOGGER.info("Registering Items for " + LoriathMod.MODID);
    }

    public static final Item LEGENDARY_MISTERY_BOX = new MisteryBoxItem(MisteryBoxRarity.LEGENDARY, new FabricItemSettings().group(ITEM_GROUP).maxCount(1));
    public static final Item EPIC_MISTERY_BOX = new MisteryBoxItem(MisteryBoxRarity.EPIC, new FabricItemSettings().group(ITEM_GROUP).maxCount(1));
    public static final Item RARE_MISTERY_BOX = new MisteryBoxItem(MisteryBoxRarity.RARE, new FabricItemSettings().group(ITEM_GROUP).maxCount(1));
    public static final Item UNCOMMON_MISTERY_BOX = new MisteryBoxItem(MisteryBoxRarity.UNCOMMON, new FabricItemSettings().group(ITEM_GROUP).maxCount(1));
    public static final Item COMMON_MISTERY_BOX = new MisteryBoxItem(MisteryBoxRarity.COMMON, new FabricItemSettings().group(ITEM_GROUP).maxCount(1));
    public static final Item COMESTIC_MISTERY_BOX = new MisteryBoxItem(MisteryBoxRarity.COSMETIC, new FabricItemSettings().group(ITEM_GROUP).maxCount(8));

}
