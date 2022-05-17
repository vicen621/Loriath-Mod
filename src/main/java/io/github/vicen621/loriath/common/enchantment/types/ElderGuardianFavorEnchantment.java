package io.github.vicen621.loriath.common.enchantment.types;

import io.github.vicen621.loriath.common.enchantment.ExtendedEnchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;

/**
 * Enchantment which after successful hit attacks enemy with laser beam that cannot be dodged or blocked.
 */

//TODO: Terminar
public class ElderGuardianFavorEnchantment extends ExtendedEnchantment {
    private static final String LINK_TAG = "ElderGuardianFavorLinkedEntityID";
    private static final String LINK_COUNTER_TAG = "ElderGuardianFavorCounter";
    protected final double beamCooldown = 4.0;
    protected final double beamDamage = 6.0;
    protected final double waterMultiplier = 1.5;

    public ElderGuardianFavorEnchantment() {
        super("elder_guardian_favor", Rarity.RARE, EnchantmentTarget.TRIDENT, EquipmentSlot.MAINHAND);
    }

    /**
     * Event that links entities together on hit.
     */
    @SubscribeEvent
    public static void onHit(LivingHurtEvent event) {
        DamageSource damageSource = event.getSource();

        if (!(damageSource.getEntity() instanceof LivingEntity) || !(damageSource.getDirectEntity() instanceof LivingEntity))
            return;

        LivingEntity attacker = (LivingEntity) damageSource.getEntity();
        LivingEntity target = event.getEntityLiving();
        int enchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(Instances.ELDER_GAURDIAN_FAVOR, attacker.getMainHandItem());

        connectEntities(attacker, target, enchantmentLevel);
    }

    /**
     * Event that updates link between entities and damage target after some time.
     */
    @SubscribeEvent
    public static void onUpdate(LivingEvent.LivingUpdateEvent event) {
        LivingEntity attacker = event.getEntityLiving();
        CompoundTag data = attacker.getPersistentData();
        int counter = data.getInt(LINK_COUNTER_TAG) - 1;

        if (counter < 0 || !(attacker.level instanceof ServerLevel))
            return;

        data.putInt(LINK_COUNTER_TAG, counter);

        ElderGuardianFavorEnchantment enchantment = Instances.ELDER_GAURDIAN_FAVOR;
        int targetID = data.getInt(LINK_TAG);
        ServerLevel world = (ServerLevel) attacker.level;
        Entity targetEntity = world.getEntity(targetID);
        if (!(targetEntity instanceof LivingEntity))
            return;

        LivingEntity target = (LivingEntity) targetEntity;
        if (counter > 0) {
            spawnParticles(attacker, target, world);
        } else {
            boolean areEntitiesInWater = (target.isInWater() || LevelHelper.isEntityOutsideWhenItIsRaining(target)
            ) && (attacker.isInWater() || LevelHelper.isEntityOutsideWhenItIsRaining(attacker));

            world.playSound(null, target.getX(), target.getEyeY(), target.getZ(), SoundEvents.GLASS_BREAK, SoundSource.AMBIENT,
                    0.5f, 1.8f
            );
            target.hurt(DamageSource.MAGIC,
                    (float) ((areEntitiesInWater ? enchantment.waterMultiplier.get() : 1.0) * enchantment.beamDamage.get())
            );
        }
    }

    /**
     * Linking entities together.
     *
     * @param attacker         Entity that attacks target.
     * @param target           Entity that was damaged and will be damaged second time later.
     * @param enchantmentLevel Attacker's level of 'Favor of Elder Guardian'.
     */
    protected static void connectEntities(LivingEntity attacker, LivingEntity target, int enchantmentLevel) {
        CompoundTag data = attacker.getPersistentData();

        if (data.getInt(LINK_COUNTER_TAG) > 0 || enchantmentLevel == 0)
            return;

        data.putInt(LINK_TAG, target.getId());
        data.putInt(LINK_COUNTER_TAG, Instances.ELDER_GAURDIAN_FAVOR.beamCooldown.getDuration());
    }

    /**
     * Spawning particles between entities when they are linked.
     *
     * @param attacker Attacker.
     * @param target   Target.
     * @param world    Level at which particles will be spawned.
     */
    protected static void spawnParticles(LivingEntity attacker, LivingEntity target, ServerLevel world) {
        Vec3 difference = new Vec3(attacker.getX() - target.getX(), attacker.getY(0.5) - target.getY(0.5),
                attacker.getZ() - target.getZ()
        );
        Vec3 normalized = difference.normalize();
        double factor = 0.0;

        while (factor < difference.length()) {
            double x = attacker.getX() - normalized.x * factor;
            double y = attacker.getY(0.5) - normalized.y * factor;
            double z = attacker.getZ() - normalized.z * factor;
            world.sendParticles(ParticleTypes.BUBBLE, x, y, z, 1, 0.0, 0.0, 0.0, 0.0);
            world.sendParticles(ParticleTypes.BUBBLE_POP, x, y, z, 1, 0.0, 0.0, 0.0, 0.0);

            factor += 1.8 - 0.8 + MajruszLibrary.RANDOM.nextDouble() * (1.7 - 0.8);
        }
    }
}
