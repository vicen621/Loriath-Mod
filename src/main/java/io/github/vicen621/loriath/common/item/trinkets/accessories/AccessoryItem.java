package io.github.vicen621.loriath.common.item.trinkets.accessories;

import dev.emi.trinkets.api.SlotReference;
import io.github.vicen621.loriath.common.item.trinkets.TrinketItem;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Language;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AccessoryItem extends TrinketItem implements ExtendableTooltipProvider {

    /**
     * Used to give a Trinket a permanent status effect while wearing it.
     * The StatusEffectInstance have to be permanent or it will be removed once the time is over
     *
     * @return The {@link StatusEffectInstance} to be applied while wearing this artifact
     */
    public StatusEffectInstance getPermanentEffect() {
        return null;
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (hasPermanentEffect())
            entity.addStatusEffect(getPermanentEffect());
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (hasPermanentEffect())
            entity.removeStatusEffect(getPermanentEffect().getEffectType());
    }

    @Override
    public List<Text> getExtendedTooltip() {
        List<Text> tooltip = new ArrayList<>();
        String[] lines = String.format(Language.getInstance().get(this.getTranslationKey() + ".tooltip"), getTooltipDescriptionArguments().toArray()).split("\n");

        for (String line : lines)
            tooltip.add(Text.literal(line).formatted(Formatting.GRAY));

        return tooltip;
    }

    public boolean hasPermanentEffect() {
        return this.getPermanentEffect() != null;
    }

    protected List<String> getTooltipDescriptionArguments() {
        return Collections.emptyList();
    }
}
