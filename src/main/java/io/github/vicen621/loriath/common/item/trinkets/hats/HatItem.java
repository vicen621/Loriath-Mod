package io.github.vicen621.loriath.common.item.trinkets.hats;

import io.github.vicen621.loriath.common.item.trinkets.TrinketItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.util.Rarity;

public class HatItem extends TrinketItem {

    public HatItem() {
        super(new FabricItemSettings().maxCount(1).rarity(Rarity.RARE));
    }
}
