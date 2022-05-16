package io.github.vicen621.loriath.common.enchantment;

import com.github.crimsondawn45.fabricshieldlib.lib.enchantment.ShieldTarget;
import io.github.vicen621.loriath.common.enchantment.targets.MeleeWeaponTarget;
import io.github.vicen621.loriath.common.events.LivingEntityHurtCallback;
import io.github.vicen621.loriath.common.init.ModEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.math.MathHelper;


/**
 * TODO: Add melee_weapon target, fijarse en
 * @see com.github.crimsondawn45.fabricshieldlib.lib.enchantment.ShieldTarget
 * @see com.github.crimsondawn45.fabricshieldlib.lib.enchantment.FabricShieldLibTarget
 */
public class DeathWishEnchantment extends Enchantment {

    public DeathWishEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});

        LivingEntityHurtCallback.EVENT.register((user, source, amount) -> {
            Enchantment deathWish = ModEnchantments.DEATH_WISH;

            LivingEntity attacker = source.getAttacker() instanceof LivingEntity ? (LivingEntity) source.getAttacker() : null;
            if (attacker != null && EnchantmentHelper.getEquipmentLevel(deathWish, attacker) > 0)
                user.damage(DamageSource.mob(attacker), (amount * getDamageMultiplier(attacker)) - amount);

            if (EnchantmentHelper.getEquipmentLevel(deathWish, user) > 0)
                user.damage( DamageSource.MAGIC, (amount * getVulnerabilityMultiplier()) - amount);
        });
    }

    /**
     * @return Damage multiplier depending on missing health.
     */
    private float getDamageMultiplier(LivingEntity entity) {
        return (float) (1.0f + (1.0 - MathHelper.clamp(entity.getHealth() / entity.getMaxHealth(), 0.0, 1.0)));
    }

    /**
     * @return Vulnerability damage multiplier.
     */
    private float getVulnerabilityMultiplier() {
        return 1.25f;
    }
}
