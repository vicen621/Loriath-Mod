package io.github.vicen621.loriath.common.item.trinkets;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.Trinket;
import dev.emi.trinkets.api.TrinketsApi;
import io.github.vicen621.loriath.common.init.ModItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class TrinketItem extends Item implements Trinket {

    public TrinketItem() {
        this(new FabricItemSettings().group(ModItems.ITEM_GROUP).maxCount(1).rarity(Rarity.RARE));
    }

    public TrinketItem(Settings settings) {
        super(settings);
        TrinketsApi.registerTrinket(this, this);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (dev.emi.trinkets.api.TrinketItem.equipItem(user, stack)) {
            // Play right click equip sound
            TrinketItem.SoundInfo sound = this.getEquipSoundInfo();
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

    public void tick(ItemStack stack, LivingEntity entity) {
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
        public SoundInfo {
        }

        public SoundInfo(SoundEvent soundEvent) {
            this(soundEvent, 1f, 1f);
        }
    }
}
