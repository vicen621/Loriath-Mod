package io.github.vicen621.loriath;

import io.github.vicen621.loriath.common.init.*;
import io.github.vicen621.loriath.common.init.loottables.ModLootFunctions;
import io.github.vicen621.loriath.common.init.loottables.ModLootTables;
import io.github.vicen621.loriath.common.item.trinkets.accessories.items.extra.Dash;
import io.github.vicen621.loriath.utils.TimeHelper;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//TODO Arreglar Infinite torch que rompe los bloques transparentes
//TODO https://discord.com/channels/@me/1045529136657072138/1057856591199748196 
public class LoriathMod implements ModInitializer {
    public static final String MODID = "loriath";
    public static final Logger LOGGER = LoggerFactory.getLogger("Loriath");

    public static final boolean DEBUG = true;

    public static Identifier id(String path) {
        return new Identifier(MODID, path);
    }

    @Override
    public void onInitialize() {
        ModLootFunctions.registerLootFunctions();
        ModSoundEvents.registerModSounds();
        ModEnchantments.registerModEnchantments();
        ModParticles.registerModParticles();
        ModBlocks.registerModBlocks();
        ModItems.registerModItems();
        ModHats.registerModHats();
        ModSoundEvents.registerModSounds();

        new TimeHelper();
        events();
        LOGGER.info("Finished loading LoriathMod");
    }

    private void events() {

        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            ModLootTables.onLootTableLoad(id, tableBuilder);
        });
        new Dash();
    }
}
