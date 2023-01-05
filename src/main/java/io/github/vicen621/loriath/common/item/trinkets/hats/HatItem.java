package io.github.vicen621.loriath.common.item.trinkets.hats;

import com.mojang.blaze3d.platform.GlStateManager;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.client.TrinketRenderer;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import io.github.vicen621.loriath.common.init.ModHats;
import io.github.vicen621.loriath.common.init.ModItems;
import io.github.vicen621.loriath.common.item.trinkets.TrinketItem;
import io.github.vicen621.loriath.utils.TrinketsHelper;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;

public class HatItem extends TrinketItem {

    public HatItem() {
        super(new FabricItemSettings().group(ModHats.HATS_ITEM_GROUP).maxCount(1).rarity(Rarity.RARE));
    }
}
