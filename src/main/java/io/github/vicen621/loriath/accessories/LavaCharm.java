package io.github.vicen621.loriath.accessories;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import io.github.vicen621.loriath.events.PlayerDamageCallback;
import io.github.vicen621.loriath.item.CustomItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;

public class LavaCharm extends AccesoryItem {

    public LavaCharm() {
        PlayerDamageCallback.EVENT.register((player, source, amount) -> {
            if (source == DamageSource.LAVA && this.getDefaultStack().getOrCreateNbt().getInt("lavaCharmTimer") > 0) {
                player.setFireTicks(0);
                return ActionResult.FAIL;
            }

            return ActionResult.PASS;
        });
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        stack.getOrCreateNbt().putInt("lavaCharmTimer", 0);
    }

    @Override
    public void tick(ItemStack stack, LivingEntity entity) {
        NbtCompound tag = stack.getOrCreateNbt();

        int cooldown = tag.getInt("lavaCharmCooldown");
        int timer = tag.getInt("lavaCharmTimer");

        if (entity.isInLava()) {
            if (cooldown <= 0)
                tag.putInt("lavaCharmCooldown", 100); // 5 secs

            if (timer > 0) {
                timer--;
                tag.putInt("lavaCharmTimer", timer);
            }
        } else {
            if (cooldown > 0) {
                cooldown--;
                tag.putInt("lavaCharmCooldown", cooldown);
            } else if (timer != 140)
                tag.putInt("lavaCharmTimer", 140); // 7 secs
        }
    }
}
