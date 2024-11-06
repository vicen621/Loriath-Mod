package io.github.vicen621.loriath.common.enchantment.types;

import io.github.vicen621.loriath.common.enchantment.ExtendedEnchantment;
import io.github.vicen621.loriath.common.events.LivingEvent;
import io.github.vicen621.loriath.utils.WorldUtils;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;

/**
 * Enchantment which after successful hit attacks enemy with laser beam that cannot be dodged or blocked.
 */

public class ElderGuardianFavorEnchantment extends ExtendedEnchantment {
    protected final int BEAM_COOLDOWN = 80; //4 secs
    protected final double BEAM_DAMAGE = 6.0;
    protected final double WATER_MULTIPLIER = 1.5;

    public ElderGuardianFavorEnchantment() {
        super("elder_guardian_favor", Rarity.RARE, EnchantmentTarget.TRIDENT, EquipmentSlot.MAINHAND);

        setDifferenceBetweenMinimumAndMaximum(20);
        setMinimumEnchantabilityCalculator(level -> (14 * level));

        // Event that links entities together on hit.
        LivingEvent.LivingEntityHurtCallback.EVENT.register(this::onDamage);

        //Event that updates link between entities and damage target after some time.
        LivingEvent.LivingEntityUpdateCallback.EVENT.register(this::entityUpdate);
    }

    /**
     * Spawning particles between entities when they are linked.
     *
     * @param attacker Attacker.
     * @param target   Target.
     * @param world    Level at which particles will be spawned.
     */
    protected static void spawnParticles(LivingEntity attacker, LivingEntity target, ServerWorld world) {
        Vec3d difference = new Vec3d(attacker.getX() - target.getX(), attacker.getBodyY(0.5) - target.getBodyY(0.5),
                attacker.getZ() - target.getZ()
        );
        Vec3d normalized = difference.normalize();
        double factor = 0.0;

        while (factor < difference.length()) {
            double x = attacker.getX() - normalized.x * factor;
            double y = attacker.getBodyY(0.5) - normalized.y * factor;
            double z = attacker.getZ() - normalized.z * factor;
            world.spawnParticles(ParticleTypes.BUBBLE, x, y, z, 1, 0.0, 0.0, 0.0, 0.0);
            world.spawnParticles(ParticleTypes.BUBBLE_POP, x, y, z, 1, 0.0, 0.0, 0.0, 0.0);

            factor += 1.8 - 0.8 + world.getRandom().nextDouble() * (1.7 - 0.8);
        }
    }

    protected void entityUpdate(LivingEntity entity) {
        int counter = entity.loriath$getEGFCounter() - 1;

        if (counter < 0 || !(entity.getWorld() instanceof ServerWorld world))
            return;

        entity.loriath$setEGFCounter(counter);

        int targetID = entity.loriath$getEGFLinkedEntityID();
        Entity targetEntity = world.getEntityById(targetID);
        if (!(targetEntity instanceof LivingEntity target))
            return;

        if (counter > 0) {
            spawnParticles(entity, target, world);
        } else {
            boolean areEntitiesInWater = (target.isTouchingWater() || WorldUtils.isEntityOutsideWhenItIsRaining(target)
            ) && (entity.isTouchingWater() || WorldUtils.isEntityOutsideWhenItIsRaining(entity));

            world.playSound(null, target.getX(), target.getEyeY(), target.getZ(), SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.AMBIENT,
                    0.5f, 1.8f
            );
            target.damage(world.getDamageSources().magic(),
                    (float) ((areEntitiesInWater ? this.WATER_MULTIPLIER : 1.0) * this.BEAM_DAMAGE)
            );
            entity.loriath$setEGFLinkedEntityID(0);
        }
    }

    protected float onDamage(LivingEntity user, DamageSource source, float amount) {
        if (source.getAttacker() instanceof PlayerEntity || source.getSource() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) source.getAttacker();
            int enchantmentLevel = this.getEnchantmentLevel(player.getMainHandStack());
            connectEntities(player, user, enchantmentLevel);
            return amount;
        }

        if (!(source.getAttacker() instanceof LivingEntity attacker) || !(source.getSource() instanceof LivingEntity))
            return amount;

        int enchantmentLevel = this.getEnchantmentLevel(attacker.getMainHandStack());
        connectEntities(attacker, user, enchantmentLevel);
        return amount;
    }

    /**
     * Linking entities together.
     *
     * @param attacker         Entity that attacks target.
     * @param target           Entity that was damaged and will be damaged second time later.
     * @param enchantmentLevel Attacker's level of 'Favor of Elder Guardian'.
     */
    protected void connectEntities(LivingEntity attacker, LivingEntity target, int enchantmentLevel) {

        if (attacker.loriath$getEGFLinkedEntityID() > 0 && attacker.getWorld().getEntityById(attacker.loriath$getEGFLinkedEntityID()) == null)
            attacker.loriath$setEGFLinkedEntityID(0);

        if (attacker.loriath$getEGFLinkedEntityID() > 0 || enchantmentLevel == 0)
            return;

        attacker.loriath$setEGFLinkedEntityID(target.getId());
        attacker.loriath$setEGFCounter(BEAM_COOLDOWN);
    }
}
