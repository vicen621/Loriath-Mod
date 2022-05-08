package io.github.vicen621.loriath.common.item.accessories;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

import java.util.List;

public interface ExtendableTooltipProvider {

    Text TOOLTIP_HINT = new TranslatableText("item.loriath.tooltip_hint");

    List<Text> getExtendedTooltip();

    default void append(List<Text> tooltip) {
        if (Screen.hasShiftDown()) tooltip.addAll(getExtendedTooltip());
        else tooltip.add(TOOLTIP_HINT);
    }

}