package io.github.vicen621.loriath.common.item.trinkets.accessories;

import io.github.vicen621.loriath.common.item.trinkets.TrinketItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Language;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AccessoryItem extends TrinketItem implements ExtendableTooltipProvider{

    /**
     * Used to give a Trinket a permanent status effect while wearing it.
     * The StatusEffectInstance is applied every 15 ticks so a duration greater than that is required.
     *
     * @return The {@link StatusEffectInstance} to be applied while wearing this artifact
     */
    public StatusEffectInstance getPermanentEffect() {
        return null;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext flags) {
        append(tooltip);
    }

    @Override
    public List<Text> getExtendedTooltip() {
        List<Text> tooltip = new ArrayList<>();
        String[] lines = String.format(Language.getInstance().get(this.getTranslationKey() + ".tooltip"), getTooltipDescriptionArguments().toArray()).split("\n");

        for (String line : lines)
            tooltip.add(Text.literal(line).formatted(Formatting.GRAY));

        return tooltip;
    }

    protected List<String> getTooltipDescriptionArguments() {
        return Collections.emptyList();
    }
}
