package io.github.vicen621.loriath.accessories;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import io.github.vicen621.loriath.item.ModItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

import java.util.List;

public class AccessoryItem extends TrinketItem {

    public AccessoryItem() {
        super(new FabricItemSettings().group(ModItems.ITEM_GROUP).maxCount(1).rarity(Rarity.RARE));
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText(this.getTranslationKey() + ".tooltip"));
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity.getEntityWorld().isClient) return;
        this.tick(stack, entity);
    }

    public void tick(ItemStack stack, LivingEntity entity) {

    }

    /**
     * Used to give a Trinket a permanent status effect while wearing it.
     * The StatusEffectInstance is applied every 15 ticks so a duration greater than that is required.
     *
     * @return The {@link StatusEffectInstance} to be applied while wearing this artifact
     */
    public StatusEffectInstance getPermanentEffect() {
        return null;
    }
}
