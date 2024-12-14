package io.github.vicen621.loriath.enchantment.types;

import io.github.vicen621.loriath.enchantment.ExtendedEnchantment;
import io.github.vicen621.loriath.events.LivingEvent;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

public class ImmortalityEnchantment extends ExtendedEnchantment {
    protected static final int DAMAGE_ON_USE = 9001;

    public ImmortalityEnchantment() {
        super("immortality", Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});

        setMaximumEnchantmentLevel(1);
        setDifferenceBetweenMinimumAndMaximum(30);
        setMinimumEnchantabilityCalculator(level -> 20);

        LivingEvent.LivingEntityDamageCallback.EVENT.register(this::onDamage);
    }

    protected float onDamage(LivingEntity entity, DamageSource source, float amount) {
        if ((entity.getHealth() - amount) < 1.0f) {
            if (tryCheatDeath(entity, EquipmentSlot.MAINHAND))
                return 0.0f;
            else if (tryCheatDeath(entity, EquipmentSlot.OFFHAND))
                return 0.0f;
        }
        return amount;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof ShieldItem;
    }

    /**
     * Cheats death when player holds shield and it has this enchantment.
     *
     * @return Returns whether player successfully cheated death.
     */
    protected boolean tryCheatDeath(LivingEntity target, EquipmentSlot equipmentSlot) {
        ItemStack itemStack = target.getEquippedStack(equipmentSlot);
        if (itemStack.getItem() instanceof ShieldItem && this.hasEnchantment(itemStack)) {
            target.setHealth(target.getMaxHealth());

            spawnParticlesAndPlaySounds(target);
            itemStack.damage(DAMAGE_ON_USE, target, (entity) -> entity.sendEquipmentBreakStatus(equipmentSlot));
            return true;
        }

        return false;
    }

    /**
     * Spawns particles and plays sound when cheating death.
     */
    protected void spawnParticlesAndPlaySounds(LivingEntity livingEntity) {
        ServerWorld world = (ServerWorld) livingEntity.getWorld();
        world.spawnParticles(ParticleTypes.TOTEM_OF_UNDYING, livingEntity.getX(), livingEntity.getBodyY(0.75), livingEntity.getZ(), 64,
                0.25, 0.5, 0.25, 0.5
        );
        world.playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), SoundEvents.ITEM_TOTEM_USE,
                SoundCategory.AMBIENT, 1.0f, 1.0f
        );
    }
}
