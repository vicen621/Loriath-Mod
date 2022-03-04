package io.github.vicen621.loriath.item;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotAttributes;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import io.github.vicen621.loriath.LoriathMod;
import io.github.vicen621.loriath.accessories.LavaCharm;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTables;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;

import java.util.UUID;

public class CustomItems {

    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(
            new Identifier(LoriathMod.MOD_ID, "loriath"))
            .icon(() -> new ItemStack(CustomItems.MARICOIN))
            .build();

    public static final Item MARICOIN = registerItem("maricoin", new Item(new FabricItemSettings().group(ITEM_GROUP).maxCount(16)));
    public static final Item FROG_LEG = registerItem("frog_leg", new TrinketItem(new FabricItemSettings().group(ITEM_GROUP).maxCount(1)) {
        @Override
        public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, Integer.MAX_VALUE, 1));
        }

        @Override
        public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
            entity.removeStatusEffect(StatusEffects.JUMP_BOOST);
        }
    });
    public static final Item DIVING_GEAR = registerItem("diving_gear", new TrinketItem(new FabricItemSettings().group(ITEM_GROUP).maxCount(1)) {
        int cooldown = 0;
        @Override
        public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
            if (entity.getEntityWorld().isClient) return;
            if (entity.getAir() < 100 && entity.isInsideWaterOrBubbleColumn()) {
                if (cooldown <= 0) {
                    entity.setAir(entity.getAir() + 30);
                    cooldown = 60; // 3 seconds
                } else
                    cooldown--;
            }
        }
    });
    public static final Item FROG_FLIPPER = registerItem("frog_flipper", new TrinketItem(new FabricItemSettings().group(ITEM_GROUP).maxCount(1)) {
        int cooldown = 0;
        @Override
        public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
            if (entity.getEntityWorld().isClient) return;
            if (entity.getAir() > 100 && entity.isInsideWaterOrBubbleColumn()) {
                if (cooldown <= 0) {
                    entity.setAir(entity.getAir() + 30);
                    cooldown = 60;
                } else
                    cooldown--;
            }
        }

        @Override
        public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, Integer.MAX_VALUE, 1));
        }

        @Override
        public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
            entity.removeStatusEffect(StatusEffects.JUMP_BOOST);
        }
    });
    public static final Item HERMES_BOOTS = registerItem("hermes_boots", new TrinketItem(new FabricItemSettings().group(ITEM_GROUP).maxCount(1)) {
        @Override
        public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack,
                                                                               SlotReference slot, LivingEntity entity, UUID uuid) {
            var modifiers = super.getModifiers(stack, slot, entity, uuid);
            modifiers.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(uuid,
                    "loriath:movement_speed", 0.07, EntityAttributeModifier.Operation.ADDITION));
            return modifiers;
        }
    });
    public static final Item BEZOAR = registerItem("bezoar", new TrinketItem(new FabricItemSettings().group(ITEM_GROUP).maxCount(1)) {
        @Override
        public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
            if (entity.getEntityWorld().isClient) return;
            if (entity.hasStatusEffect(StatusEffects.POISON) && entity.getStatusEffect(StatusEffects.POISON).getAmplifier() == 0) {
                entity.removeStatusEffect(StatusEffects.POISON);
            }
        }
    });
    public static final Item ADHESIVE_BANDAGE = registerItem("adhesive_bandage", new TrinketItem(new FabricItemSettings().group(ITEM_GROUP).maxCount(1)) {
        @Override
        public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
            if (entity.getEntityWorld().isClient) return;
            if (entity.hasStatusEffect(StatusEffects.WITHER) && entity.getStatusEffect(StatusEffects.WITHER).getAmplifier() == 0) {
                entity.removeStatusEffect(StatusEffects.WITHER);
            }
        }
    });
    public static final Item MEDICATED_BANDAGE = registerItem("medicated_bandage", new TrinketItem(new FabricItemSettings().group(ITEM_GROUP).maxCount(1)) {
        @Override
        public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
            if (entity.getEntityWorld().isClient) return;
            if (entity.hasStatusEffect(StatusEffects.WITHER) && entity.getStatusEffect(StatusEffects.WITHER).getAmplifier() == 0) {
                entity.removeStatusEffect(StatusEffects.WITHER);
            }
            if (entity.hasStatusEffect(StatusEffects.POISON) && entity.getStatusEffect(StatusEffects.POISON).getAmplifier() == 0) {
                entity.removeStatusEffect(StatusEffects.POISON);
            }
        }
    });
    public static final Item FAST_CLOCK = registerItem("fast_clock", new TrinketItem(new FabricItemSettings().group(ITEM_GROUP).maxCount(1)) {
        @Override
        public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
            if (entity.getEntityWorld().isClient) return;
            if (entity.hasStatusEffect(StatusEffects.SLOWNESS) && entity.getStatusEffect(StatusEffects.SLOWNESS).getAmplifier() == 0) {
                entity.removeStatusEffect(StatusEffects.SLOWNESS);
            }
        }
    });
    //TODO
    public static final Item SHINY_STONE = registerItem("shiny_stone", new TrinketItem(new FabricItemSettings().group(ITEM_GROUP).maxCount(1)) {
        int timer = 0;
        @Override
        public void tick(ItemStack stack, SlotReference reference, LivingEntity entity) {
            if (entity.getEntityWorld().isClient) return;
            Vec3d velocity = entity.getVelocity();
            if (velocity.getY() <= 0 && velocity.getX() == 0 && velocity.getY() == 0) { //Fixme Ver como fijarse si el jugador esta quieto
                LoriathMod.LOGGER.info("isStill");
                timer++;
                if (timer >= 3*20) //3 secs
                    entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 20*4, 2));
                if (timer >= 6*20) //6 secs
                    entity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 25, 1));
            } else if (timer != 0) {
                LoriathMod.LOGGER.info("timer == 0");
                timer = 0;
            }
        }
    });
    public static final Item LAVA_CHARM = registerItem("lava_charm", new LavaCharm());
    public static final Item TITAN_GLOVE = registerItem("titan_glove", new TrinketItem(new FabricItemSettings().group(ITEM_GROUP).maxCount(1)) {
        @Override
        public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack,
                                                                               SlotReference slot, LivingEntity entity, UUID uuid) {
            var modifiers = super.getModifiers(stack, slot, entity, uuid);
            modifiers.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(uuid,
                    "loriath:attack_sped", 1, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
            modifiers.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(uuid,
                    "loriath:attack_damage", 0.1, EntityAttributeModifier.Operation.ADDITION));
            return modifiers;
        }
    });
    public static final Item SHACKLE = registerItem("shackle", new TrinketItem(new FabricItemSettings().group(ITEM_GROUP).maxCount(1)) {
        @Override
        public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack,
                                                                               SlotReference slot, LivingEntity entity, UUID uuid) {
            var modifiers = super.getModifiers(stack, slot, entity, uuid);
            modifiers.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(uuid,
                    "loriath:armor", 2, EntityAttributeModifier.Operation.ADDITION));
            return modifiers;
        }
    });
    //TODO
    public static final Item DESTROYER_EMBLEM = registerItem("destroyer_emblem", new TrinketItem(new FabricItemSettings().group(ITEM_GROUP).maxCount(1)));
    //TODO
    public static final Item PANIC_NECKLACE = registerItem("panic_necklace", new TrinketItem(new FabricItemSettings().group(ITEM_GROUP).maxCount(1)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(LoriathMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        LoriathMod.LOGGER.info("Registering Mod Items for " + LoriathMod.MOD_ID);
    }
}
