package io.github.vicen621.loriath.common.enchantment.types;

import io.github.vicen621.loriath.LoriathMod;
import io.github.vicen621.loriath.common.enchantment.ExtendedEnchantment;
import io.github.vicen621.loriath.common.events.LivingEntityHurtCallback;
import io.github.vicen621.loriath.common.init.ModEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.*;
import net.minecraft.util.math.MathHelper;

//TODO: check if works
public class DeathWishEnchantment extends ExtendedEnchantment {

    public DeathWishEnchantment() {
        super("death_wish", Rarity.RARE, EnchantmentTarget.WEAPON, EquipmentSlot.MAINHAND);

        setDifferenceBetweenMinimumAndMaximum(40);

        LivingEntityHurtCallback.EVENT.register((user, source, amount) -> {
            LivingEntity attacker = source.getAttacker() instanceof LivingEntity ? (LivingEntity) source.getAttacker() : null;
            if (attacker != null && hasEnchantment(attacker)) {
                float dmg = (amount * getDamageMultiplier(attacker)) - amount;
                user.damage(DamageSource.mob(attacker), dmg);
                LoriathMod.LOGGER.info("Damage Deal: " + dmg);
            }

            if (hasEnchantment(user))
                user.damage(DamageSource.MAGIC, (amount * getVulnerabilityMultiplier()) - amount);

            return true;
        });
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        Item item = stack.getItem();
        return item instanceof SwordItem || item instanceof AxeItem || item instanceof TridentItem;
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
