package io.github.vicen621.loriath.common.enchantment.types;

import io.github.vicen621.loriath.common.enchantment.ExtendedEnchantment;
import io.github.vicen621.loriath.common.events.LivingEntityHurtCallback;
import io.github.vicen621.loriath.common.init.ModEnchantments;
import io.github.vicen621.loriath.utils.AttributeHandler;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;

import java.util.Random;

//TODO: Terminar
public class DodgeEnchantment extends ExtendedEnchantment {
    private static final AttributeHandler ATTRIBUTE_HANDLER = new AttributeHandler( "ad3e064e-e9f6-4747-a86b-46dc4e2a1444", "KnockBackImmunityTime",
            EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, EntityAttributeModifier.Operation.ADDITION
    );
    private static final String DODGE_TAG = "KnockbackImmunityCounter";

    public DodgeEnchantment() {
        super("dodge", Rarity.RARE, EnchantmentTarget.ARMOR_LEGS, EquipmentSlot.LEGS);

        LivingEntityHurtCallback.EVENT.register((user, source, amount) -> {
            ItemStack pants = user.getEquippedStack( EquipmentSlot.LEGS );
            CompoundTag data = user.getPersistentData();
            DodgeEnchantment dodge = ModEnchantments.DODGE;
            int dodgeLevel = getEnchantmentLevel(pants); //EnchantmentHelper.getItemEnchantmentLevel( dodge, pants );

            if( dodgeLevel <= 0 || !( user.world instanceof ServerWorld) )
                return;

            if( !Random.tryChance( dodgeLevel * dodge.dodgeChancePerLevel.get() ) )
                return;

            updateImmunity( entity, dodge.immunityTime.getDuration() );
            spawnParticlesAndPlaySounds( entity );
            if( dodge.damageAmountFactor.get() > 0.0 )
                pants.hurtAndBreak( Math.max( ( int )( event.getAmount() * dodge.damageAmountFactor.get() ), 1 ), entity,
                        owner->owner.broadcastBreakEvent( EquipmentSlot.LEGS )
                );
            event.setCanceled( true );
        });
    }


}
