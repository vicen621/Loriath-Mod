package io.github.vicen621.loriath.common.item.accessories;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.Trinket;
import dev.emi.trinkets.api.TrinketItem;
import dev.emi.trinkets.api.TrinketsApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.DispenserBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class AccessoryItem extends AbstractAccessoryItem implements Trinket {

    public AccessoryItem() {
        TrinketsApi.registerTrinket(this, this);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (TrinketItem.equipItem(user, stack)) {
            // Play right click equip sound
            SoundInfo sound = this.getEquipSoundInfo();
            user.playSound(sound.soundEvent(), sound.volume(), sound.pitch());

            return TypedActionResult.success(stack, world.isClient);
        }

        return super.use(world, user, hand);
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity.getEntityWorld().isClient) return;
        this.tick(stack, entity);
    }

    public void tick(ItemStack stack, LivingEntity entity) {}

    /**
     * Used to give a Trinket a permanent status effect while wearing it.
     * The StatusEffectInstance is applied every 15 ticks so a duration greater than that is required.
     *
     * @return The {@link StatusEffectInstance} to be applied while wearing this artifact
     */
    public StatusEffectInstance getPermanentEffect() {
        return null;
    }

    /**
     * @return The {@link SoundInfo} to play when the artifact is right-click equipped
     */
    protected SoundInfo getEquipSoundInfo() {
        return new SoundInfo(SoundEvents.ITEM_ARMOR_EQUIP_GENERIC);
    }

    protected record SoundInfo(SoundEvent soundEvent, float volume, float pitch) {

        // Changes access modifier to public
        @SuppressWarnings({"RedundantRecordConstructor", "RedundantSuppression"})
        public SoundInfo {}

        public SoundInfo(SoundEvent soundEvent) {
            this(soundEvent, 1f, 1f);
        }
    }
}
