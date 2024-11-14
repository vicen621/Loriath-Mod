package io.github.vicen621.loriath.common.item.trinkets.accessories.items.feet;

import com.mojang.brigadier.LiteralMessage;
import dev.emi.trinkets.api.SlotReference;
import io.github.vicen621.loriath.common.item.trinkets.accessories.AccessoryItem;
import io.github.vicen621.loriath.common.particle.HermesBootsParticles;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralTextContent;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class HermesBootsItem extends AccessoryItem {

    final double DefaultValue = 1.0;
    double value = 1.0;
    float timer = 0;

    void resetTimer() {
        timer = 0;
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        World world = entity.getWorld();
        boolean isSprinting = entity.isSprinting();
        boolean isGrounded = entity.isOnGround();
        PlayerEntity player = (PlayerEntity) entity;
        Vec3d vec = player.getVelocity();

        if (entity.isSprinting()) {
            timer++;
        } else if (!entity.isSprinting()) {
            resetTimer();
        }

/*      if (timer >= 50 && timer < 100) {
            value = 1.04;
        } else if (timer >= 100 && timer < 150) {
            value = 1.08;
        } else if (timer >= 150 && timer < 200) {
            value = 1.12;
        } else if (timer >= 200 && timer < 250) {
            value = 1.14;
        } else if (timer >= 250 && timer < 300) {
            value = 1.16;
        } else if (timer >= 300 && timer < 350) {
            value = 1.18;
        } else if (timer >= 350) {
            value = 1.20;
        } else {
            value = DefaultValue;
        }*/

        if (timer % 20 == 0) {
            value = (((timer-14)/((timer+217)*1.4))+0.9);
            if (value < 1) {
                value = 1;
            }
            else if (value > 1.25) {
                value = 1.25;
            }
        }

        //player.sendMessage(Text.literal(String.valueOf(value))); //TODO: Debug

        if (isSprinting && isGrounded) {
            player.setVelocity(vec.getX() * value, vec.getY(), vec.getZ() * value);
/*            if (timer % 20 == 0) {
                if (!world.isClient) {
                    world.playSound(null, player.getBlockPos(), SoundEvents.UI_TOAST_IN, SoundCategory.PLAYERS, 50f, 1.9f);
                }
            }*/ //TODO: Boots sound
            HermesBootsParticles.spawnRocketParticles(entity, world);
        }

        super.tick(stack, slot, entity);
    }
}
