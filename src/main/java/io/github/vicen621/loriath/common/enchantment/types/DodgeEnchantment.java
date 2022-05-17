package io.github.vicen621.loriath.common.enchantment.types;

import io.github.vicen621.loriath.LoriathMod;
import io.github.vicen621.loriath.common.enchantment.ExtendedEnchantment;
import io.github.vicen621.loriath.common.events.LivingEntityHurtCallback;
import io.github.vicen621.loriath.common.events.LivingEntityUpdateCallback;
import io.github.vicen621.loriath.extensions.LivingEntityExtensions;
import io.github.vicen621.loriath.utils.AttributeHandler;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;

public class DodgeEnchantment extends ExtendedEnchantment {
    private static final AttributeHandler ATTRIBUTE_HANDLER = new AttributeHandler("ad3e064e-e9f6-4747-a86b-46dc4e2a1444", "KnockBackImmunityTime",
            EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, EntityAttributeModifier.Operation.ADDITION
    );

    protected final double DODGE_CHANCE_PER_LEVEL = 0.125;
    protected final double DAMAGE_AMOUNT_FACTOR = 0.5;
    protected final int IMMUNITY_TIME = 3 * 20;

    public DodgeEnchantment() {
        super("dodge", Rarity.RARE, EnchantmentTarget.ARMOR_LEGS, EquipmentSlot.LEGS);

        setMaximumEnchantmentLevel(2);
        setDifferenceBetweenMinimumAndMaximum(20);

        LivingEntityHurtCallback.EVENT.register(this::onHurt);

        LivingEntityUpdateCallback.EVENT.register(user -> {
            updateImmunity(user, user.loriath$getKbImmunityCounter() - 1);
        });
    }

    private boolean onHurt(LivingEntity user, DamageSource source, float amount) {
        ItemStack pants = user.getEquippedStack(EquipmentSlot.LEGS);
        int dodgeLevel = getEnchantmentLevel(pants);

        if (dodgeLevel <= 0 || !(user.world instanceof ServerWorld))
            return true;

        if (!(user.world.getRandom().nextDouble() <= dodgeLevel * DODGE_CHANCE_PER_LEVEL))
            return true;

        updateImmunity(user, IMMUNITY_TIME);
        spawnParticlesAndPlaySounds(user);
        pants.damage(Math.max((int) (amount * DAMAGE_AMOUNT_FACTOR), 1), user,
                owner -> owner.sendEquipmentBreakStatus(EquipmentSlot.LEGS)
        );
        return false;
    }

    /**
     * Updates current knockback immunity depending on dodge tag.
     */
    protected static void updateImmunity(LivingEntity entity, int duration) {
        entity.loriath$setKbImmunityCounter(duration);

        ATTRIBUTE_HANDLER.setValueAndApply(entity, entity.loriath$getKbImmunityCounter() > 0 ? 1.0 : 0.0);
    }

    protected static void spawnParticlesAndPlaySounds(LivingEntity entity) {
        ServerWorld world = (ServerWorld) entity.world;
        for (double d = 0.0; d < 3.0; d++) {
            Vec3d emitterPosition = new Vec3d(0.0, entity.getHeight() * 0.25 * (d + 1.0), 0.0).add(entity.getPos());
            for (int i = 0; i < 2; i++)
                world.spawnParticles(i == 0 ? ParticleTypes.CAMPFIRE_COSY_SMOKE : ParticleTypes.LARGE_SMOKE, emitterPosition.x, emitterPosition.y,
                        emitterPosition.z, 8 * (i + 1), 0.125, 0.0, 0.125, (i == 0 ? 0.1 : 0.4) * 0.075
                );
        }
        world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE,
                SoundCategory.AMBIENT, 1.0f, 1.0f
        );
    }
}
