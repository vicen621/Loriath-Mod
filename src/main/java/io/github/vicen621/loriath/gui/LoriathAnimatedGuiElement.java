package io.github.vicen621.loriath.gui;

import eu.pb4.sgui.api.elements.GuiElementInterface;
import eu.pb4.sgui.api.gui.GuiInterface;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;

import java.util.WeakHashMap;

/**
 * Animated Gui Element
 * <br>
 * Animated gui elements are a GuiElement constructed of
 * multiple different {@link ItemStack} frames, which cycle
 * (optionally randomly) on a set cycle time.
 * <p>
 * Gui elements are typically constructed via their respective builder.
 *
 * @see eu.pb4.sgui.api.elements.AnimatedGuiElementBuilder
 * @see GuiElementInterface
 */
public class LoriathAnimatedGuiElement implements GuiElementInterface {
    protected final ClickCallback callback;
    protected final boolean random;
    protected ItemStack[] items;
    protected int frame = 0;
    protected int tick = 0;
    protected int changeEvery;
    protected WeakHashMap<GuiInterface, TickAndFrame> ticks = new WeakHashMap<>();
    protected SoundEvent switchSound;

    /**
     * Constructs an AnimatedGuiElement using the supplied options.
     *
     * @param items    an array of ItemStack frames
     * @param interval the interval each frame should remain active for
     * @param random   <code>true</code> is the frames should be randomly chosen
     * @param callback the callback to execute when the element is selected
     * @see eu.pb4.sgui.api.elements.AnimatedGuiElementBuilder
     */
    public LoriathAnimatedGuiElement(ItemStack[] items, int interval, boolean random, SoundEvent switchSound, ClickCallback callback) {
        this.items = items;
        this.callback = callback;
        this.changeEvery = interval;
        this.random = random;
        this.switchSound = switchSound;
    }

    /**
     * Constructs an AnimatedGuiElement using the supplied options.
     *
     * @param items    an array of ItemStack frames
     * @param interval the interval each frame should remain active for
     * @param random   <code>true</code> is the frames should be randomly chosen
     * @param callback the callback to execute when the element is selected
     * @see eu.pb4.sgui.api.elements.AnimatedGuiElementBuilder
     */
    public LoriathAnimatedGuiElement(ItemStack[] items, int interval, boolean random, SoundEvent switchSound, ItemClickCallback callback) {
        this.items = items;
        this.callback = callback;
        this.changeEvery = interval;
        this.random = random;
    }

    public LoriathAnimatedGuiElement(ItemStack[] items, int interval, boolean random, ItemClickCallback callback) {
        this(items, interval, random, null, callback);
    }

    public LoriathAnimatedGuiElement(ItemStack[] items, int interval, boolean random, ClickCallback callback) {
        this(items, interval, random, null, callback);
    }

    /**
     * Sets the elements animation frames.
     *
     * @param itemStacks the new animation frames
     */
    public void setItemStacks(ItemStack[] itemStacks) {
        this.items = itemStacks;
    }

    @Override
    public ItemStack getItemStack() {
        return this.items[frame];
    }

    @Override
    public ClickCallback getGuiCallback() {
        return this.callback;
    }

    @Override
    public ItemStack getItemStackForDisplay(GuiInterface gui) {
        int cFrame = this.frame;

        this.tick += 1;
        if (this.tick >= this.changeEvery) {
            this.tick = 0;
            this.frame += 1;
            if (this.frame >= this.items.length) {
                this.frame = 0;
            }

            if (this.random) {
                this.frame = (int) (Math.random() * this.items.length);
            }

            if (switchSound != null) {
                ServerPlayerEntity player = gui.getPlayer();
                player.getWorld().playSound(null, player.getBlockPos(), this.switchSound, SoundCategory.NEUTRAL, 1, 1);
            }
        }
        return this.items[cFrame].copy();
    }

    public ItemStack[] getItemsStacks() {
        return items;
    }

    public void setInterval(int i) {
        changeEvery = i;
    }


    protected static class TickAndFrame {
        public int tick;
        public int frame;
    }
}