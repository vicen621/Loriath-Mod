package io.github.vicen621.loriath.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;

public abstract class ExtendedEnchantment extends Enchantment {

    private final String registerName;
    private int differenceBetweenMinimumAndMaximum;
    private int maximumEnchantmentLevel;
    private MinimumEnchantabilityCalculator minimumEnchantabilityCalculator;

    protected ExtendedEnchantment(String registerName, Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
        this.registerName = registerName;
        this.differenceBetweenMinimumAndMaximum = 10;
        this.maximumEnchantmentLevel = 1;
        this.minimumEnchantabilityCalculator = (level) -> level;
    }

    protected ExtendedEnchantment(String registerName, Rarity rarity, EnchantmentTarget enchantmentCategory, EquipmentSlot equipmentSlot) {
        this(registerName, rarity, enchantmentCategory, new EquipmentSlot[]{equipmentSlot});
    }

    @Override
    public int getMaxLevel() {
        return this.maximumEnchantmentLevel;
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + this.differenceBetweenMinimumAndMaximum;
    }

    public int getEnchantmentLevel(ItemStack itemStack) {
        return EnchantmentHelper.getLevel(this, itemStack);
    }

    public int getEnchantmentLevel(LivingEntity entity) {
        return EnchantmentHelper.getEquipmentLevel(this, entity);
    }

    public boolean hasEnchantment(ItemStack itemStack) {
        return this.getEnchantmentLevel(itemStack) > 0;
    }

    public boolean hasEnchantment(LivingEntity entity) {
        return this.getEnchantmentLevel(entity) > 0;
    }

    /**
     * Increases enchantment level by 1 if possible or adds enchantment if there is not any.
     *
     * @return Returns whether level was increased.
     */
    public boolean increaseEnchantmentLevel(ItemStack itemStack) {
        int enchantmentLevel = getEnchantmentLevel(itemStack);
        if (enchantmentLevel >= getMaxLevel())
            return false;

        if (enchantmentLevel == 0) {
            itemStack.addEnchantment(this, 1);
        } else {
            NbtList nbt = itemStack.getEnchantments();

            for (int i = 0; i < nbt.size(); ++i) {
                NbtCompound enchantmentData = nbt.getCompound(i);
                String enchantmentID = enchantmentData.getString("id");

                if (enchantmentID.contains(this.registerName)) {
                    enchantmentData.putInt("lvl", enchantmentLevel + 1);
                    break;
                }
            }

            itemStack.setSubNbt("Enchantments", nbt);
        }
        return true;
    }


    /**
     * Setting up difference between minimum and maximum enchantability.
     * For example when this value is set to 20 and minimum level is equal 10
     * then player can receive this enchantment only when enchanting with level between 10 and 30.
     */
    protected void setDifferenceBetweenMinimumAndMaximum(int differenceInLevels) {
        this.differenceBetweenMinimumAndMaximum = Math.max(1, differenceInLevels);
    }

    /**
     * Setting up maximum enchantment level this enchantment can have.
     */
    protected void setMaximumEnchantmentLevel(int enchantmentLevel) {
        this.maximumEnchantmentLevel = Math.max(1, enchantmentLevel);
    }

    protected void setMinimumEnchantabilityCalculator(MinimumEnchantabilityCalculator minimumEnchantabilityCalculator) {
        this.minimumEnchantabilityCalculator = minimumEnchantabilityCalculator;
    }

    public String getRegisterName() {
        return this.registerName;
    }

    @FunctionalInterface
    protected interface MinimumEnchantabilityCalculator {
        int getMinimumLevel(int var1);
    }
}
