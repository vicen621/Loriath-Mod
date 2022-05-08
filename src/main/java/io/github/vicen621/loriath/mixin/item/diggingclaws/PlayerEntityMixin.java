package io.github.vicen621.loriath.mixin.item.diggingclaws;

import io.github.vicen621.loriath.common.init.ModItems;
import io.github.vicen621.loriath.common.item.accessories.items.hand.DiggingClawsItem;
import io.github.vicen621.loriath.utils.TrinketsHelper;
import net.fabricmc.fabric.api.mininglevel.v1.MiningLevelManager;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "canHarvest", at = @At("HEAD"), cancellable = true)
    private void increaseBaseMiningLevel(BlockState block, CallbackInfoReturnable<Boolean> info) {
        if (TrinketsHelper.isEquipped(ModItems.DIGGING_CLAWS, this)
                && DiggingClawsItem.NEW_BASE_MINING_LEVEL >= MiningLevelManager.getRequiredMiningLevel(block)) {
            info.setReturnValue(true);
        }
    }

    /**
     * This is identical to the forge version but might not be ideal
     * It adds the speed after the vanilla method does all the calculations on the base modifier such as haste and underwater
     */
    // TODO: identical artifacts-forge behaviour but could do this on the speed mutliplier instead of end result
    @Inject(method = "getBlockBreakingSpeed", at = @At("RETURN"), cancellable = true)
    private void increaseMiningSpeed(BlockState block, CallbackInfoReturnable<Float> info) {
        if (TrinketsHelper.isEquipped(ModItems.DIGGING_CLAWS, this)) {
            info.setReturnValue(info.getReturnValueF() + DiggingClawsItem.MINING_SPEED_INCREASE);
        }
    }
}
