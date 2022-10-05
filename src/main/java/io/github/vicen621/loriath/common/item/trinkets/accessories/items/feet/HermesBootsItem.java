package io.github.vicen621.loriath.common.item.trinkets.accessories.items.feet;

import dev.emi.trinkets.api.SlotReference;
import io.github.vicen621.loriath.common.item.trinkets.accessories.AccessoryItem;
import io.github.vicen621.loriath.common.particle.HermesBootsParticles;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class HermesBootsItem extends AccessoryItem {

    final double DefaultValue = 1.0;
    double value = 1.0;
    int a = 0;

    void resetTimer() {
        a = 0;
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        World world = entity.world;
        boolean isSprinting = entity.isSprinting();
        boolean isGrounded = entity.isOnGround();
        PlayerEntity player = (PlayerEntity) entity;
        Vec3d vec = player.getVelocity();

        if (entity.isSprinting()) {
            a++;
        } else if (!entity.isSprinting()) {
            resetTimer();
        }

        if (a >= 50 && a < 100) {
            value = 1.04;
        } else if (a >= 100 && a < 150) {
            value = 1.08;
        } else if (a >= 150 && a < 200) {
            value = 1.12;
        } else if (a >= 200 && a < 250) {
            value = 1.14;
        } else if (a >= 250 && a < 300) {
            value = 1.16;
        } else if (a >= 300 && a < 350) {
            value = 1.18;
        } else if (a >= 350) {
            value = 1.20;
        } else {
            value = DefaultValue;
        }
        if (isSprinting && isGrounded) {
            player.setVelocity(vec.getX() * value, vec.getY(), vec.getZ() * value);
            HermesBootsParticles.spawnRocketParticles(entity, world);
        }

        super.tick(stack, slot, entity);
    }
}
