package io.github.vicen621.loriath.enchantment.types;

import io.github.vicen621.loriath.enchantment.ExtendedEnchantment;
import io.github.vicen621.loriath.events.LivingEvent;
import io.github.vicen621.loriath.init.ModEnchantments;
import io.github.vicen621.loriath.init.ModParticles;
import io.github.vicen621.loriath.utils.AttributeHandler;
import io.github.vicen621.loriath.utils.StringUtils;
import io.github.vicen621.loriath.utils.TimeHelper;
import net.minecraft.enchantment.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;

import java.util.List;
import java.util.function.Predicate;

public class PhoenixDiveEnchantment extends ExtendedEnchantment {

    protected final double DAMAGE_DISTANCE = 5.0;

    protected final int SECONDS_ON_FIRE = 3;

    public PhoenixDiveEnchantment() {
        super("phoenix_dive", Rarity.RARE, EnchantmentTarget.ARMOR_FEET, EquipmentSlot.FEET);

        setMaximumEnchantmentLevel(3);
        setDifferenceBetweenMinimumAndMaximum(30);
        setMinimumEnchantabilityCalculator(level -> (10 * (level + 1)));

        LivingEvent.LivingEntityFallCallback.EVENT.register((attacker, distance, damageMultiplier) -> {
            if (distance <= 3.0)
                return true;

            int enchantmentLevel = getPhoenixDiveLevel(attacker);

            if (enchantmentLevel <= 0 || !(attacker.getWorld() instanceof ServerWorld world))
                return true;

            for (Entity entity : getEntitiesInRange(attacker, world)) {
                StringUtils.debug(entity.getType() + "");
                StringUtils.debug((entity instanceof LivingEntity) + "");
                if (entity instanceof LivingEntity target) {
                    target.setOnFireFor(SECONDS_ON_FIRE * enchantmentLevel);
                    target.damage(world.getDamageSources().explosion(target, attacker), 0);
                    target.damage(world.getDamageSources().onFire(), (float) Math.sqrt(enchantmentLevel * distance));
                }
            }

            spawnFallParticles(attacker.getPos(), world);

            return true;
        });

        LivingEvent.LivingEntityUpdateCallback.EVENT.register(player -> {
            if (getPhoenixDiveLevel(player) <= 0 || !(player.getWorld() instanceof ServerWorld world) || (player instanceof ServerPlayerEntity p && p.isSpectator()))
                return;

            if (TimeHelper.hasServerTicksPassed(3))
                spawnFootParticle(player, world, TimeHelper.hasServerTicksPassed(6));
        });
    }

    /**
     * Returning Phoenix Dive enchantment level.
     *
     * @param entity Entity to check level.
     */
    protected static int getPhoenixDiveLevel(LivingEntity entity) {
        return EnchantmentHelper.getLevel(ModEnchantments.PHOENIX_DIVE, entity.getEquippedStack(EquipmentSlot.FEET));
    }

    /**
     * Returns predicate to check whether given entity is valid target.
     */
    protected static Predicate<Entity> getEntitiesPredicate(LivingEntity livingEntity) {
        return entity -> {
            boolean canAttack = entity instanceof LivingEntity && AttributeHandler.hasAttribute((LivingEntity) entity, EntityAttributes.GENERIC_ATTACK_DAMAGE);
            boolean isTamedByEntity = entity instanceof TameableEntity && ((TameableEntity) entity).isOwner(livingEntity);
            boolean isTargetedByEntity = livingEntity.getAttacker() != null && livingEntity.getAttacker()
                    .isPartOf(entity);
            boolean wasAttackedByEntity = livingEntity.getAttacker() != null && livingEntity.getAttacker()
                    .isPartOf(entity);

            return canAttack && !isTamedByEntity || isTargetedByEntity || wasAttackedByEntity;
        };
    }

    /**
     * Spawning particles on fall.
     *
     * @param position Position where the entity landed.
     * @param world    Level where particles should be spawned.
     */
    protected static void spawnFallParticles(Vec3d position, ServerWorld world) {
        double x = position.x, y = position.y, z = position.z;
        for (double d = 0.0; d < 3.0; d++)
            world.spawnParticles(ModParticles.PHOENIX, x, y, z, (int) Math.pow(5.0, d + 1.0), 0.0625, 0.125, 0.0625,
                    (0.125 + 0.0625) * (d + 1.0)
            );

        world.playSound(null, x, y, z, SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.AMBIENT, 0.5f, 0.9f);
    }

    /**
     * Spawning particles at foot height.
     *
     * @param entity    Entity where the particles will be spawned.
     * @param world     Level where particles should be spawned.
     * @param isLeftLeg Flag that informs to spawn particle at left leg position.
     */
    protected static void spawnFootParticle(LivingEntity entity, ServerWorld world, boolean isLeftLeg) {
        if (entity.isFallFlying())
            return;

        double leftLegRotation = (isLeftLeg ? 180.0 : 0.0);
        double angleInRadians = Math.toRadians(entity.bodyYaw + 90.0 + leftLegRotation);
        world.spawnParticles(ParticleTypes.FLAME, entity.getX() + 0.1875 * Math.sin(-angleInRadians), entity.getY() + 0.1,
                entity.getZ() + 0.1875 * Math.cos(-angleInRadians), 1, 0.0, 0.125 * Math.cos(angleInRadians), 0.00, 0.0
        );
    }

    @Override
    public boolean canAccept(Enchantment other) {
        return other != Enchantments.FROST_WALKER && super.canAccept(other);
    }

    /**
     * Getting entities in certain range.
     *
     * @param livingEntity Entity as a start position.
     * @param world        Current entity world.
     * @return Returns list with entities that were in range.
     */
    protected List<Entity> getEntitiesInRange(LivingEntity livingEntity, ServerWorld world) {
        List<Entity> entities = world.getOtherEntities(livingEntity, livingEntity.getBoundingBox()
                .expand(DAMAGE_DISTANCE, livingEntity.getHeight(), DAMAGE_DISTANCE));

        return entities.stream()
                .filter(getEntitiesPredicate(livingEntity))
                .toList();
    }
}
