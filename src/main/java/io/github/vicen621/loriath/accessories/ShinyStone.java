package io.github.vicen621.loriath.accessories;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import io.github.vicen621.loriath.item.CustomItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class ShinyStone extends TrinketItem {

    public ShinyStone() {
        super(new FabricItemSettings().group(CustomItems.ITEM_GROUP).maxCount(1));
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity.getEntityWorld().isClient) return;


    }
}
