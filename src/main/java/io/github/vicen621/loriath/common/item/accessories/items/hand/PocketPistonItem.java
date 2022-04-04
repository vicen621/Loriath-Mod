package io.github.vicen621.loriath.common.item.accessories.items.hand;

import io.github.vicen621.loriath.common.item.accessories.AccessoryItem;
import net.minecraft.sound.SoundEvents;

public class PocketPistonItem extends AccessoryItem {

	@Override
	protected AccessoryItem.SoundInfo getEquipSoundInfo() {
		return new AccessoryItem.SoundInfo(SoundEvents.BLOCK_PISTON_EXTEND);
	}
}
