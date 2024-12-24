package io.github.vicen621.loriath;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.BoolArgumentType;
import io.github.vicen621.loriath.init.*;
import io.github.vicen621.loriath.init.loottables.ModLootFunctions;
import io.github.vicen621.loriath.init.loottables.ModLootTables;
import io.github.vicen621.loriath.item.trinkets.accessories.items.extra.Dash;
import io.github.vicen621.loriath.utils.TimeHelper;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.HitResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.mojang.brigadier.builder.LiteralArgumentBuilder.literal;

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

		// Cancel the use of Anvils and Enchanting Tables unless you have the canUseEnchants enabled
		UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
			if (hitResult.getType() != HitResult.Type.BLOCK || player.isSpectator() || world.isClient)
				return ActionResult.PASS;

			BlockState block = world.getBlockState(hitResult.getBlockPos());

			if (block.isOf(Blocks.ENCHANTING_TABLE) || block.isIn(BlockTags.ANVIL)) {
				if (player.loriath$canUseEnchants()) {
					return ActionResult.PASS;
				}

				player.sendMessage(Text.literal("Necesitas el favor de los Dioses").formatted(Formatting.RED), true);
				return ActionResult.FAIL;
			}

			return ActionResult.PASS;
		});

		// Set the canUseEnchants for a player
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(CommandManager.literal("loriath")
							.then(CommandManager.literal("enchants")
							.then(CommandManager.argument("player", EntityArgumentType.players())
							.then(CommandManager.argument("value", BoolArgumentType.bool())
					.requires(source -> source.hasPermissionLevel(4))
					.executes(context -> {
						PlayerEntity player = EntityArgumentType.getPlayer(context, "player");
						boolean value = BoolArgumentType.getBool(context, "value");

						player.loriath$setCanUseEnchants(value);
						context.getSource().sendMessage(player.getName().copyContentOnly().append(" now can use the enchanting table and anvils!"));
						return Command.SINGLE_SUCCESS;
					})))));
		});
		new Dash();
	}
}
