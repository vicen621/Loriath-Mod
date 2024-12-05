package io.github.vicen621.loriath.mixin.client.renderer;

import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import io.github.vicen621.loriath.render.accessory.renderer.GloveAccessoryRenderer;
import io.github.vicen621.loriath.utils.TrinketsHelper;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin {

    @Unique
    private static void renderArm(MatrixStack matrixStack, VertexConsumerProvider buffer, int light, AbstractClientPlayerEntity player, Arm handSide) {
        List<ItemStack> allEquippedGloves = TrinketsHelper.getAllEquippedForSlot(player, "hand", "accessory");

        for (ItemStack stack : allEquippedGloves) {
            TrinketRendererRegistry.getRenderer(stack.getItem()).ifPresent(renderer -> {
                if (renderer instanceof GloveAccessoryRenderer gloveRenderer) {
                    gloveRenderer.renderFirstPersonArm(matrixStack, buffer, light, player, handSide, stack.hasGlint());
                }
            });
        }
    }

    @Inject(method = "renderLeftArm", at = @At("TAIL"))
    private void renderLeftGlove(MatrixStack matrixStack, VertexConsumerProvider buffer, int light, AbstractClientPlayerEntity player, CallbackInfo callbackInfo) {
        renderArm(matrixStack, buffer, light, player, Arm.LEFT);
    }

    @Inject(method = "renderRightArm", at = @At("TAIL"))
    private void renderRightGlove(MatrixStack matrixStack, VertexConsumerProvider buffer, int light, AbstractClientPlayerEntity player, CallbackInfo callbackInfo) {
        renderArm(matrixStack, buffer, light, player, Arm.RIGHT);
    }
}