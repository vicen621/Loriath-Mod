package io.github.vicen621.loriath.common.item.mysteryBoxes;

import eu.pb4.sgui.api.elements.GuiElementBuilder;
import eu.pb4.sgui.api.elements.GuiElementInterface;
import eu.pb4.sgui.api.gui.SimpleGui;
import io.github.vicen621.loriath.LoriathMod;
import io.github.vicen621.loriath.common.gui.LoriathAnimatedGuiElement;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.network.packet.s2c.play.StopSoundS2CPacket;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.List;

public class MisteryBoxItem extends Item {

    private final ItemStack[] PANES = new ItemStack[]{
            Items.WHITE_STAINED_GLASS_PANE.getDefaultStack(),
            Items.MAGENTA_STAINED_GLASS_PANE.getDefaultStack(),
            Items.LIGHT_BLUE_STAINED_GLASS_PANE.getDefaultStack(),
            Items.LIME_STAINED_GLASS_PANE.getDefaultStack(),
            Items.PINK_STAINED_GLASS_PANE.getDefaultStack(),
            Items.LIGHT_GRAY_STAINED_GLASS_PANE.getDefaultStack(),
            Items.CYAN_STAINED_GLASS_PANE.getDefaultStack(),
            Items.PURPLE_STAINED_GLASS_PANE.getDefaultStack(),
            Items.BLUE_STAINED_GLASS_PANE.getDefaultStack(),
            Items.GREEN_STAINED_GLASS_PANE.getDefaultStack(),
            Items.RED_STAINED_GLASS_PANE.getDefaultStack(),
    };

    private final Random RANDOM = Random.create();

    private final MysteryBoxRarity rarity;

    public MisteryBoxItem(MysteryBoxRarity rarity, Settings settings) {
        super(settings);
        this.rarity = rarity;
        Registry.register(Registry.ITEM, LoriathMod.id(rarity.toString().toLowerCase() + "_mistery_box"), this);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient) return super.use(world, user, hand);
        if (!(user instanceof ServerPlayerEntity player)) return super.use(world, user, hand);

        ItemStack stack = player.getStackInHand(hand);

        try {
            LootContext.Builder builder = new LootContext.Builder(player.getWorld()).parameter(LootContextParameters.ORIGIN, player.getPos()).random(RANDOM);
            builder.parameter(LootContextParameters.THIS_ENTITY, player);
            List<ItemStack> items = player.getWorld().getServer().getLootManager().getTable(rarity.getLootTable()).generateLoot(builder.build(LootContextTypes.CHEST));
            Collections.shuffle(items);

            SimpleGui gui = new SimpleGui(ScreenHandlerType.GENERIC_9X1, player, false) {
                int tick;
                boolean spinning = true;

                @Override
                public void onClose() {
                    if (!spinning) {
                        if (!player.getInventory().insertStack(getSlot(4).getItemStack().copy()))
                            player.getWorld().spawnEntity(new ItemEntity(player.getWorld(), player.getX(), player.getY(), player.getZ(), getSlot(4).getItemStack()));
                    } else {
                        ItemStack stack = items.get(RANDOM.nextInt(items.size())).copy();
                        if (!player.getInventory().insertStack(stack))
                            player.getWorld().spawnEntity(new ItemEntity(player.getWorld(), player.getX(), player.getY(), player.getZ(), stack));
                    }

                    StopSoundS2CPacket stopSoundS2CPacket = new StopSoundS2CPacket(rarity.getThemeSound().getId(), SoundCategory.NEUTRAL);
                    world.getServer().getPlayerManager().sendToAround(null, player.getBlockX(), player.getBlockY(), player.getBlockZ(),
                            rarity.getThemeSound().getDistanceToTravel(1), world.getRegistryKey(), stopSoundS2CPacket);

                    super.onClose();
                }

                @Override
                public void onTick() {
                    if (tick == 40) {
                        for (int i = 2; i < 7; i++)
                            ((LoriathAnimatedGuiElement) getSlot(i)).setInterval(4);
                    } else if (tick == 88) {
                        for (int i = 2; i < 7; i++)
                            ((LoriathAnimatedGuiElement) getSlot(i)).setInterval(8);
                    } else if (tick == 120) {
                        for (int i = 2; i < 7; i++)
                            ((LoriathAnimatedGuiElement) getSlot(i)).setInterval(15);
                    } else if (tick == 150) {
                        for (int i = 2; i < 7; i++)
                            ((LoriathAnimatedGuiElement) getSlot(i)).setInterval(20);
                    } else if (tick == 190) {
                        for (int i = 2; i < 7; i++)
                            ((LoriathAnimatedGuiElement) getSlot(i)).setInterval(25);
                    } else if (tick == 215) {
                        for (int i = 2; i < 7; i++)
                            ((LoriathAnimatedGuiElement) getSlot(i)).setInterval(30);
                    }
                    if (tick == 245) {
                        ItemStack item = getSlot(4).getItemStack();

                        this.setSlot(0, new LoriathAnimatedGuiElement(PANES, 3, true, GuiElementInterface.EMPTY_CALLBACK));
                        this.setSlot(1, new LoriathAnimatedGuiElement(PANES, 3, true, GuiElementInterface.EMPTY_CALLBACK));
                        this.setSlot(2, new GuiElementBuilder(Items.SOUL_CAMPFIRE).setName(Text.literal(" ")));
                        this.clearSlot(3);
                        this.setSlot(4, item);
                        this.clearSlot(5);
                        this.setSlot(6, new GuiElementBuilder(Items.SOUL_CAMPFIRE).setName(Text.literal(" ")));
                        this.setSlot(7, new LoriathAnimatedGuiElement(PANES, 3, true, GuiElementInterface.EMPTY_CALLBACK));
                        this.setSlot(8, new LoriathAnimatedGuiElement(PANES, 3, true, GuiElementInterface.EMPTY_CALLBACK));

                        if (rarity.getFinishSound() != null)
                            world.playSound(null, player.getBlockPos(), rarity.getFinishSound(), SoundCategory.NEUTRAL, 1, 1);
                        spinning = false;
                    }

                    tick++;
                    super.onTick();
                }
            };


            gui.setSlot(0, new GuiElementBuilder(Items.LIGHT_BLUE_STAINED_GLASS_PANE).setName(Text.literal(" ")));
            gui.setSlot(1, new GuiElementBuilder(Items.LIME_STAINED_GLASS_PANE).setName(Text.literal(" ")));
            gui.setSlot(7, new GuiElementBuilder(Items.LIME_STAINED_GLASS_PANE).setName(Text.literal(" ")));
            gui.setSlot(8, new GuiElementBuilder(Items.LIGHT_BLUE_STAINED_GLASS_PANE).setName(Text.literal(" ")));


            gui.setSlot(2, new LoriathAnimatedGuiElement(items.toArray(ItemStack[]::new), 2, false, rarity.getSwitchSound(), GuiElementInterface.EMPTY_CALLBACK));
            Collections.rotate(items, 1);

            for (int i = 3; i < 7; i++) {
                gui.setSlot(i, new LoriathAnimatedGuiElement(items.toArray(ItemStack[]::new), 2, false, GuiElementInterface.EMPTY_CALLBACK));
                Collections.rotate(items, 1);
            }

            gui.open();
            world.playSound(null, player.getBlockPos(), rarity.getOpenSound(), SoundCategory.NEUTRAL, 1, 1);
            world.playSound(null, player.getBlockPos(), rarity.getThemeSound(), SoundCategory.NEUTRAL, 1, 1);
            stack.decrement(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.use(world, user, hand);
    }
}