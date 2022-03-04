package io.github.vicen621.loriath.accessories;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import io.github.vicen621.loriath.events.PlayerDamageCallback;
import io.github.vicen621.loriath.item.CustomItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;

public class LavaCharm extends TrinketItem {

    int timer = 0;
    int cooldown = 0;

    public LavaCharm() {
        super(new FabricItemSettings().group(CustomItems.ITEM_GROUP).maxCount(1));

        PlayerDamageCallback.EVENT.register((player, source, amount) -> {
            if (source == DamageSource.LAVA && timer > 0) {
                player.setFireTicks(0);
                return ActionResult.FAIL;
            }

            return ActionResult.PASS;
        });
    }

    @Override
    public void onUnequip(ItemStack item, SlotReference reference, LivingEntity entity) {
        timer = 0;
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity.getEntityWorld().isClient) return;
        if (entity.isInLava()) {
            if (cooldown <= 0)
                cooldown = 100; // 5 secs

            if (timer > 0)
                timer--;
        } else {
            if (cooldown > 0)
                cooldown--;
            else if (timer != 140)
                timer = 140; // 7 secs
        }
    }
}
