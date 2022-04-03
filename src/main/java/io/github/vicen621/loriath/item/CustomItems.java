package io.github.vicen621.loriath.item;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import io.github.vicen621.loriath.LoriathMod;
import io.github.vicen621.loriath.accessories.AccessoryItem;
import io.github.vicen621.loriath.accessories.LavaCharm;
import io.github.vicen621.loriath.accessories.ShinyStone;
import io.github.vicen621.loriath.item.misteryBoxes.MisteryBoxItem;
import io.github.vicen621.loriath.item.misteryBoxes.MisteryBoxRarity;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.UUID;

public class CustomItems {

    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(
            new Identifier(LoriathMod.MOD_ID, "loriath"))
            .icon(() -> new ItemStack(CustomItems.MARICOIN))
            .build();

    public static final Item MARICOIN = registerItem("maricoin", new Item(new FabricItemSettings().group(ITEM_GROUP).maxCount(16)));
    public static final Item COMMON_MISTERY_BOX = new MisteryBoxItem(MisteryBoxRarity.COMMON, new FabricItemSettings().group(ITEM_GROUP).maxCount(1));
    public static final Item UNCOMMON_MISTERY_BOX = registerItem("uncommon_mistery_box", new Item(new FabricItemSettings().group(ITEM_GROUP).maxCount(1)));
    public static final Item RARE_MISTERY_BOX = registerItem("rare_mistery_box", new Item(new FabricItemSettings().group(ITEM_GROUP).maxCount(1)));

    //public static final Item DASH_SHIELD = registerItem("dash_shield", new FabricShieldItem(new FabricItemSettings().group(ITEM_GROUP).maxDamage(1200), 5, 13, Items.OAK_PLANKS));
    public static final Item FROG_LEG = registerItem("frog_leg", new AccessoryItem() {
        @Override
        public StatusEffectInstance getPermanentEffect() {
            return new StatusEffectInstance(StatusEffects.JUMP_BOOST, 20, 1, true, false);
        }
    });
    public static final Item DIVING_GEAR = registerItem("diving_gear", new AccessoryItem() {

        @Override
        public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
            stack.getOrCreateNbt().putInt("divingGearCooldown", 0);
        }

        @Override
        public void tick(ItemStack stack, LivingEntity entity) {
            if (entity.getAir() < 100 && entity.isInsideWaterOrBubbleColumn()) {
                NbtCompound nbt = stack.getOrCreateNbt();
                int cooldown = nbt.getInt("divingGearCooldown");
                if (cooldown <= 0) {
                    entity.setAir(entity.getAir() + 30);
                    nbt.putInt("divingGearCooldown", 60); // 3 seconds
                } else {
                    cooldown--;
                    nbt.putInt("divingGearCooldown", cooldown);
                }
            }
        }
    });
    public static final Item FROG_FLIPPER = registerItem("frog_flipper", new AccessoryItem() {

        @Override
        public void tick(ItemStack stack, LivingEntity entity) {
            if (entity.getAir() < 100 && entity.isInsideWaterOrBubbleColumn()) {
                NbtCompound nbt = stack.getOrCreateNbt();
                int cooldown = nbt.getInt("frogFlipperCooldown");
                if (cooldown <= 0) {
                    entity.setAir(entity.getAir() + 30);
                    nbt.putInt("frogFlipperCooldown", 60); // 3 seconds
                } else {
                    cooldown--;
                    nbt.putInt("frogFlipperCooldown", cooldown);
                }
            }
        }

        @Override
        public StatusEffectInstance getPermanentEffect() {
            return new StatusEffectInstance(StatusEffects.JUMP_BOOST, 20, 1, true, false);
        }

        @Override
        public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
            stack.getOrCreateNbt().putInt("frogFlipperCooldown", 0);
        }
    });
    public static final Item HERMES_BOOTS = registerItem("hermes_boots", new AccessoryItem() {
        @Override
        public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack,
                                                                               SlotReference slot, LivingEntity entity, UUID uuid) {
            var modifiers = super.getModifiers(stack, slot, entity, uuid);
            modifiers.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(uuid,
                    "loriath:movement_speed", 0.04, EntityAttributeModifier.Operation.ADDITION));
            return modifiers;
        }
    });
    public static final Item BEZOAR = registerItem("bezoar", new AccessoryItem() {
        @Override
        public void tick(ItemStack stack, LivingEntity entity) {
            if (entity.hasStatusEffect(StatusEffects.POISON) && entity.getStatusEffect(StatusEffects.POISON).getAmplifier() == 0) {
                entity.removeStatusEffect(StatusEffects.POISON);
            }
        }
    });
    public static final Item ADHESIVE_BANDAGE = registerItem("adhesive_bandage", new AccessoryItem() {
        @Override
        public void tick(ItemStack stack, LivingEntity entity) {
            if (entity.hasStatusEffect(StatusEffects.WITHER) && entity.getStatusEffect(StatusEffects.WITHER).getAmplifier() == 0) {
                entity.removeStatusEffect(StatusEffects.WITHER);
            }
        }
    });
    public static final Item MEDICATED_BANDAGE = registerItem("medicated_bandage", new AccessoryItem() {
        @Override
        public void tick(ItemStack stack, LivingEntity entity) {
            if (entity.hasStatusEffect(StatusEffects.WITHER) && entity.getStatusEffect(StatusEffects.WITHER).getAmplifier() == 0) {
                entity.removeStatusEffect(StatusEffects.WITHER);
            }
            if (entity.hasStatusEffect(StatusEffects.POISON) && entity.getStatusEffect(StatusEffects.POISON).getAmplifier() == 0) {
                entity.removeStatusEffect(StatusEffects.POISON);
            }
        }
    });
    public static final Item FAST_CLOCK = registerItem("fast_clock", new AccessoryItem() {
        @Override
        public void tick(ItemStack stack, LivingEntity entity) {
            if (entity.hasStatusEffect(StatusEffects.SLOWNESS) && entity.getStatusEffect(StatusEffects.SLOWNESS).getAmplifier() == 0) {
                entity.removeStatusEffect(StatusEffects.SLOWNESS);
            }
        }
    });
    public static final Item SHINY_STONE = registerItem("shiny_stone", new ShinyStone());
    public static final Item LAVA_CHARM = registerItem("lava_charm", new LavaCharm());
    public static final Item TITAN_GLOVE = registerItem("titan_glove", new AccessoryItem() {
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
    public static final Item SHACKLE = registerItem("shackle", new AccessoryItem() {
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
    public static final Item DESTROYER_EMBLEM = registerItem("destroyer_emblem", new AccessoryItem());
    //TODO public static final Item PANIC_NECKLACE = registerItem("panic_necklace", new AccessoryItem());

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(LoriathMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        LoriathMod.LOGGER.info("Registering Mod Items for " + LoriathMod.MOD_ID);
    }
}
