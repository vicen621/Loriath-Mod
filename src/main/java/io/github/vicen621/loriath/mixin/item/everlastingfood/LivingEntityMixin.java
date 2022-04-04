package io.github.vicen621.loriath.mixin.item.everlastingfood;

import io.github.vicen621.loriath.common.item.EverlastingFoodItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

	@ModifyArgs(method = "eatFood", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;decrement(I)V"))
	private void cancelDecrement(Args args, World level, ItemStack stack) {
		if (stack.getItem() instanceof EverlastingFoodItem) {
			args.set(0, 0);
		}
	}
}
