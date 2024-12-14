package io.github.vicen621.loriath.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class LivingEvent {
    public interface LivingEntityFallCallback {

        Event<LivingEntityFallCallback> EVENT = EventFactory.createArrayBacked(LivingEntityFallCallback.class,
                (listeners) -> (user, distance, damageMultiplier) -> {
                    for (LivingEntityFallCallback listener : listeners) {
                        boolean list = listener.fall(user, distance, damageMultiplier);

                        if (!list)
                            return false;
                    }

                    return true;
                });

        boolean fall(LivingEntity user, float distance, float damageMultiplier);
    }

    public interface LivingEntityHurtCallback {

        Event<LivingEntityHurtCallback> EVENT = EventFactory.createArrayBacked(LivingEntityHurtCallback.class,
                (listeners) -> (user, source, amount) -> {
                    for (LivingEntityHurtCallback listener : listeners) {
                        float list = listener.hurt(user, source, amount);

                        if (list != amount)
                            return list;
                    }

                    return amount;
                });

        float hurt(LivingEntity user, DamageSource source, float amount);
    }

    public interface LivingEntityDamageCallback {
        Event<LivingEntityDamageCallback> EVENT = EventFactory.createArrayBacked(LivingEntityDamageCallback.class,
                (listeners) -> (user, source, amount) -> {
                    for (LivingEntityDamageCallback listener : listeners) {
                        float list = listener.damage(user, source, amount);

                        if (list != amount)
                            return list;
                    }

                    return amount;
                });

        float damage(LivingEntity user, DamageSource source, float amount);
    }

    public interface LivingEntityUpdateCallback {
        Event<LivingEntityUpdateCallback> EVENT = EventFactory.createArrayBacked(LivingEntityUpdateCallback.class,
                (listeners) -> (entity) -> {
                    for (LivingEntityUpdateCallback listener : listeners) {
                        listener.update(entity);
                    }
                });

        void update(LivingEntity entity);
    }

    public interface LivingEntityJumpCallback {
        Event<LivingEntityJumpCallback> EVENT = EventFactory.createArrayBacked(LivingEntityJumpCallback.class,
                (listeners) -> (entity) -> {
                    for (LivingEntityJumpCallback listener : listeners) {
                        listener.jump(entity);
                    }
                });

        void jump(LivingEntity entity);
    }

    public interface LivingEntityEquipmentChangeCallback {
        Event<LivingEntityEquipmentChangeCallback> EVENT = EventFactory.createArrayBacked(LivingEntityEquipmentChangeCallback.class,
                (listeners) -> (entity, slot, from, to) -> {
                    for (LivingEntityEquipmentChangeCallback listener : listeners) {
                        listener.changeEquipment(entity, slot, from, to);
                    }
                });

        void changeEquipment(LivingEntity entity, EquipmentSlot slot, ItemStack from, ItemStack to);
    }
}
