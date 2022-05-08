package io.github.vicen621.loriath.common.item.accessories.items.hand;

import io.github.vicen621.loriath.common.item.accessories.AccessoryItem;
import net.minecraft.sound.SoundEvents;

public class DiggingClawsItem extends AccessoryItem {

    public static final int NEW_BASE_MINING_LEVEL = 1;
    public static final float MINING_SPEED_INCREASE = 3.2f;

    @Override
    protected AccessoryItem.SoundInfo getEquipSoundInfo() {
        return new AccessoryItem.SoundInfo(SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE);
    }
}
