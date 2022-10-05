package io.github.vicen621.loriath.common.item.trinkets;

import io.github.vicen621.loriath.common.init.ModItems;
import io.github.vicen621.loriath.common.item.trinkets.accessories.ExtendableTooltipProvider;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Language;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractTrinketItem extends Item implements ExtendableTooltipProvider {

    public AbstractTrinketItem() {
        this(new FabricItemSettings().group(ModItems.ITEM_GROUP).maxCount(1).rarity(Rarity.RARE));
    }

    public AbstractTrinketItem(Settings settings) {
        super(settings.group(ModItems.ITEM_GROUP).maxCount(1).rarity(Rarity.RARE));
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
