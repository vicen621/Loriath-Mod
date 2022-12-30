package io.github.vicen621.loriath.common.init;

import io.github.vicen621.loriath.LoriathMod;
import io.github.vicen621.loriath.common.item.trinkets.hats.HatItem;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.registry.Registry;

public class ModHats {
    public static final ItemGroup HATS_ITEM_GROUP = FabricItemGroupBuilder.create(LoriathMod.id("hats"))
            .icon(() -> new ItemStack(ModHats.BANDANA))
            .build();

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
    public static final Item BAT_GLASSES = register("bat_glasses", new HatItem());
    public static final Item BAT_MASK = register("bat_mask", new HatItem());
    public static final Item BEACH_VISOR = register("beach_visor", new HatItem());
    public static final Item BEAR_HAT = register("bear_hat", new HatItem());
    public static final Item BETA_BLUE = register("beta_blue", new HatItem());
    public static final Item BETA_LIGHT_GREEN = register("beta_light_green", new HatItem());
    public static final Item BETA_MAGENTA = register("beta_magenta", new HatItem());
    public static final Item BETA_ORANGE = register("beta_orange", new HatItem());
    public static final Item BETA_RAINBOW = register("beta_rainbow", new HatItem());
    public static final Item BETA_RED = register("beta_red", new HatItem());
    public static final Item BETA_WHITE = register("beta_white", new HatItem());
    public static final Item BOATER_HAT = register("boater_hat", new HatItem());
    public static final Item BOX_HAT = register("box_hat", new HatItem());
    public static final Item BUCKET_HAT = register("bucket_hat", new HatItem());
    public static final Item BUNNY_EARS = register("bunny_ears", new HatItem());
    public static final Item CACTUS_CAP = register("cactus_cap", new HatItem());
    public static final Item CAKE_HAT = register("cake_hat", new HatItem());
    public static final Item CAT_EARS = register("cat_ears", new HatItem());
    public static final Item CONSTRUCTION_HELMET = register("construction_helmet", new HatItem());
    public static final Item COOKIE_HAT = register("cookie_hat", new HatItem());
    public static final Item COW_HAT = register("cow_hat", new HatItem());
    public static final Item COWBOY_HAT = register("cowboy_hat", new HatItem());
    public static final Item CROWN = register("crown", new HatItem());
    public static final Item DAISY_CHAIN = register("daisy_chain", new HatItem());
    public static final Item DEVIL_HORNS = register("devil_horns", new HatItem());
    public static final Item DINOSAUR_HAT = register("dinosaur_hat", new HatItem());
    public static final Item DISGUISE = register("disguise", new HatItem());
    public static final Item DRAGON_HAT_BLACK = register("dragon_hat_black", new HatItem());
    public static final Item DUCK_HAT = register("duck_hat", new HatItem());
    public static final Item EARMUFFS = register("earmuffs", new HatItem());
    public static final Item EASTER_BEANIE = register("easter_beanie", new HatItem());
    public static final Item ELF = register("elf", new HatItem());
    public static final Item FALL_CROWN = register("fall_crown", new HatItem());
    public static final Item FEATHER_CROWN = register("feather_crown", new HatItem());
    public static final Item FEDORA = register("fedora", new HatItem());
    public static final Item FEDORA2 = register("fedora2", new HatItem());
    public static final Item FIREFIGHTER_HELMET = register("firefighter_helmet", new HatItem());
    public static final Item FROG_BUCKET_HAT = register("frog_bucket_hat", new HatItem());
    public static final Item GASMASK = register("gasmask", new HatItem());
    public static final Item GHOST_HAT = register("ghost_hat", new HatItem());
    public static final Item GRADUATION_CAP = register("graduation_cap", new HatItem());
    public static final Item GUARD_HAT = register("guard_hat", new HatItem());
    public static final Item HALO = register("halo", new HatItem());
    public static final Item HAZMAT_MASK = register("hazmat_mask", new HatItem());
    public static final Item HEADPHONES = register("headphones", new HatItem());
    public static final Item HOCKEY_MASK = register("hockey_mask", new HatItem());
    public static final Item HYPNO_GOGGLES = register("hypno_goggles", new HatItem());
    public static final Item JELLYFISH_HAT = register("jellyfish_hat", new HatItem());
    public static final Item JOKER_HAT = register("joker_hat", new HatItem());
    public static final Item LION_HAT = register("lion_hat", new HatItem());
    public static final Item MASQUERADE_MASK = register("masquerade_mask", new HatItem());
    public static final Item MINER_HELMET = register("miner_helmet", new HatItem());
    public static final Item MONOCLE = register("monocle", new HatItem());
    public static final Item MOTORCYCLE_HAT = register("motorcycle_hat", new HatItem());
    public static final Item MUSHROOM_CAP = register("mushroom_cap", new HatItem());
    public static final Item NATIVE_HEADBAND = register("native_headband", new HatItem());
    public static final Item NIGHTVISION_GOGGLES = register("nightvision_goggles", new HatItem());
    public static final Item NINJA_HEADBAND = register("ninja_headband", new HatItem());
    public static final Item PAINTBALL_MASK = register("paintball_mask", new HatItem());
    public static final Item PHARAOH_HEADDRESS = register("pharaoh_headdress", new HatItem());
    public static final Item PILGRIM_HAT = register("pilgrim_hat", new HatItem());
    public static final Item PINK_BOW = register("pink_bow", new HatItem());
    public static final Item PIRATE_HAT = register("pirate_hat", new HatItem());
    public static final Item POLICE_HAT = register("police_hat", new HatItem());
    public static final Item POTTER_GLASSES = register("potter_glasses", new HatItem());
    public static final Item RAINBOW_HEADBAND = register("rainbow_headband", new HatItem());
    public static final Item PUMPKIN_BEANIE = register("pumpkin_beanie", new HatItem());
    public static final Item RAINBOW_SHADES = register("rainbow_shades", new HatItem());
    public static final Item RAM_HAT = register("ram_hat", new HatItem());
    public static final Item RHINO_HAT = register("rhino_hat", new HatItem());
    public static final Item ROBOCOP_HELMET = register("robocop_helmet", new HatItem());
    public static final Item SAILOR = register("sailor", new HatItem());
    public static final Item SAMURAI_HELMET = register("samurai_helmet", new HatItem());
    public static final Item SANTA_HAT = register("santa_hat", new HatItem());
    public static final Item SCIENCE_GOGGLES = register("science_goggles", new HatItem());
    public static final Item SCUBA = register("scuba", new HatItem());
    public static final Item SEUSS = register("seuss", new HatItem());
    public static final Item SHARK = register("shark", new HatItem());
    public static final Item SHREK_BEANIE = register("shrek_beanie", new HatItem());
    public static final Item SKATEBOARD_HELMET = register("skateboard_helmet", new HatItem());
    public static final Item SKULL_MASK = register("skull_mask", new HatItem());
    public static final Item SODA_HAT = register("soda_hat", new HatItem());
    public static final Item SOMBRERO = register("sombrero", new HatItem());
    public static final Item SPIDER_HAT = register("spider_hat", new HatItem());
    public static final Item SPRING_FLOWER_CROWN = register("spring_flower_crown", new HatItem());
    public static final Item SPROUT_HAT = register("sprout_hat", new HatItem());
    public static final Item STEAM_GOGGLES = register("steam_goggles", new HatItem());
    public static final Item STITCH_HAT = register("stitch_hat", new HatItem());
    public static final Item STRAW_HAT = register("straw_hat", new HatItem());
    public static final Item TAX_HAT = register("summer_hat", new HatItem());
    public static final Item TELEVISION_HEAD = register("television_head", new HatItem());
    public static final Item TIARA = register("tiara", new HatItem());
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
    public static final Item[] HATS = {ANGRY_HAT, ANTLERS, ARCHER_HAT, ARTIST_BERET, ASH_HAT, ASTRO, AVIATOR_HAT,
            AVIATORS, BANDANA, BETA_BLUE, BETA_LIGHT_GREEN, BETA_MAGENTA, BETA_ORANGE, BETA_RAINBOW, BETA_RED, BETA_WHITE,
            BOATER_HAT, BOX_HAT, BUCKET_HAT, BUNNY_EARS, CACTUS_CAP, CAKE_HAT, CAT_EARS, CONSTRUCTION_HELMET, COOKIE_HAT,
            COW_HAT, COWBOY_HAT, CROWN, DAISY_CHAIN, DEVIL_HORNS, DINOSAUR_HAT, DISGUISE, DRAGON_HAT_BLACK, DUCK_HAT,
            EARMUFFS, EASTER_BEANIE, ELF, FALL_CROWN, FEATHER_CROWN, FEDORA, FEDORA2, FIREFIGHTER_HELMET, FROG_BUCKET_HAT,
            GLASSES_3D, BAT_GLASSES, BAT_MASK, BEACH_VISOR, BEAR_HAT, GASMASK, GHOST_HAT, GUARD_HAT, GRADUATION_CAP,
            HALO, HAZMAT_MASK, HEADPHONES, HOCKEY_MASK, HYPNO_GOGGLES, JELLYFISH_HAT, JOKER_HAT, LION_HAT,
            MASQUERADE_MASK, MOTORCYCLE_HAT, MONOCLE, MUSHROOM_CAP, MINER_HELMET, NATIVE_HEADBAND, NIGHTVISION_GOGGLES,
            NINJA_HEADBAND, PILGRIM_HAT, PAINTBALL_MASK, PIRATE_HAT, POLICE_HAT, POTTER_GLASSES, PHARAOH_HEADDRESS, PINK_BOW,
            PUMPKIN_BEANIE, RAM_HAT, RHINO_HAT, RAINBOW_HEADBAND, RAINBOW_SHADES, ROBOCOP_HELMET, SANTA_HAT, SAILOR, SAMURAI_HELMET, SODA_HAT,
            SPIDER_HAT, SCIENCE_GOGGLES, SCUBA, SEUSS, SHARK, SHREK_BEANIE, SKATEBOARD_HELMET, SKULL_MASK, SOMBRERO,
            SPRING_FLOWER_CROWN, SPROUT_HAT, STEAM_GOGGLES, STITCH_HAT, STRAW_HAT, TAX_HAT, TIARA, TOP_HAT, TURTLE_HAT,
            TELEVISION_HEAD, TIKI_MASK, TOMATO_CAP, TOP_HAT, TURTLE_HAT, UFO_HAT, UNICORN_HAT, USHANKA, VALKYRIE_HELMET,
            VIKING_HELMET, VR, VR_GREEN, VR_ORANGE, VR_PURPLE, VR_RED, WINGED};

    private static Item register(String name, Item item) {
        return Registry.register(Registry.ITEM, LoriathMod.id(name), item);
    }

    public static void registerModHats() {
        LoriathMod.LOGGER.info("Registering Hats for " + LoriathMod.MODID);
    }
}
