package io.github.vicen621.loriath;

import io.github.vicen621.loriath.init.*;
import io.github.vicen621.loriath.init.loottables.ModLootFunctions;
import io.github.vicen621.loriath.init.loottables.ModLootTables;
import io.github.vicen621.loriath.item.trinkets.accessories.items.extra.Dash;
import io.github.vicen621.loriath.utils.TimeHelper;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//TODO Arreglar Infinite torch que rompe los bloques transparentes
// https://discord.com/channels/@me/1045529136657072138/1057856591199748196
public class Loriath implements ModInitializer {
	public static final String MODID = "loriath";
	public static final Logger LOGGER = LoggerFactory.getLogger("Loriath");

	public static final boolean DEBUG = true;

	public static Identifier id(String path) {
		return Identifier.of(MODID, path);
	}

	@Override
	public void onInitialize() {
		ModLootFunctions.registerLootFunctions();
		ModSoundEvents.registerModSounds();
		ModEnchantments.registerModEnchantments();
		ModParticles.registerModParticles();
		// ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		ModHats.registerModHats();
		ModSoundEvents.registerModSounds();

		new TimeHelper();
		events();
		LOGGER.info("Finished loading Loriath");
	}

	private void events() {

		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
			ModLootTables.onLootTableLoad(id, tableBuilder);
		});
		new Dash();
	}
}
