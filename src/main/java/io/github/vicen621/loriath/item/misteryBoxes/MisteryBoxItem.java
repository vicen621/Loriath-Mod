package io.github.vicen621.loriath.item.misteryBoxes;

import eu.pb4.sgui.api.elements.*;
import eu.pb4.sgui.api.gui.*;
import io.github.vicen621.loriath.LoriathMod;
import io.github.vicen621.loriath.gui.LoriathAnimatedGuiElement;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.*;

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

    private final MisteryBoxRarity rarity;

    public MisteryBoxItem(MisteryBoxRarity rarity, Settings settings) {
        super(settings);
        this.rarity = rarity;
        Registry.register(Registry.ITEM, new Identifier(LoriathMod.MOD_ID, rarity.toString().toLowerCase() + "_mistery_box"), this);
    }

    //FIXME Ver porque se borran los items cuando se abre el mistery box, capaz es por el rarity
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient) return super.use(world, user, hand);
        if (!(user instanceof ServerPlayerEntity player)) return super.use(world, user, hand);

        ItemStack stack = player.getStackInHand(hand);

        try {
            ArrayList<ItemStack> items = new ArrayList<>(rarity.getItems());
            Collections.shuffle(items);

            SimpleGui gui = new SimpleGui(ScreenHandlerType.GENERIC_9X1, player, false) {
                int tick;
                boolean spinning = true;

                @Override
                public void onClose() {
                    if (!spinning) {
                        if (!player.getInventory().insertStack(getSlot(4).getItemStack()))
                            player.getWorld().spawnEntity(new ItemEntity(player.getWorld(), player.getX(), player.getY(), player.getZ(), getSlot(4).getItemStack()));
                    } else {
                        ItemStack stack = items.get(new Random().nextInt(items.size()));
                        if (!player.getInventory().insertStack(stack))
                            player.getWorld().spawnEntity(new ItemEntity(player.getWorld(), player.getX(), player.getY(), player.getZ(), stack));
                    }
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
                    } if (tick == 245) {
                        ItemStack item = getSlot(4).getItemStack();

                        this.setSlot(0, new LoriathAnimatedGuiElement(PANES, 3, true, GuiElementInterface.EMPTY_CALLBACK));
                        this.setSlot(1, new LoriathAnimatedGuiElement(PANES, 3, true, GuiElementInterface.EMPTY_CALLBACK));
                        this.setSlot(2, new GuiElementBuilder(Items.SOUL_CAMPFIRE).setName(new LiteralText(" ")));
                        this.clearSlot(3);
                        this.setSlot(4, item);
                        this.clearSlot(5);
                        this.setSlot(6, new GuiElementBuilder(Items.SOUL_CAMPFIRE).setName(new LiteralText(" ")));
                        this.setSlot(7, new LoriathAnimatedGuiElement(PANES, 3, true, GuiElementInterface.EMPTY_CALLBACK));
                        this.setSlot(8, new LoriathAnimatedGuiElement(PANES, 3, true, GuiElementInterface.EMPTY_CALLBACK));

                        player.playSound(SoundEvents.BLOCK_BEACON_POWER_SELECT, SoundCategory.MASTER, 1, 1);
                        spinning = false;
                    }

                    tick++;
                    super.onTick();
                }
            };


            gui.setSlot(0, new GuiElementBuilder(Items.LIGHT_BLUE_STAINED_GLASS_PANE).setName(new LiteralText(" ")));
            gui.setSlot(1, new GuiElementBuilder(Items.LIME_STAINED_GLASS_PANE).setName(new LiteralText(" ")));
            gui.setSlot(7, new GuiElementBuilder(Items.LIGHT_BLUE_STAINED_GLASS_PANE).setName(new LiteralText(" ")));
            gui.setSlot(8, new GuiElementBuilder(Items.LIME_STAINED_GLASS_PANE).setName(new LiteralText(" ")));

            for (int i = 2; i < 7; i++) {
                gui.setSlot(i, new LoriathAnimatedGuiElement(items.toArray(ItemStack[]::new), 2, false, GuiElementInterface.EMPTY_CALLBACK));
                Collections.rotate(items, 1);
            }

            gui.open();
            stack.decrement(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.use(world, user, hand);
    }
}