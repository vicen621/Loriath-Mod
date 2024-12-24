package io.github.vicen621.loriath.mixin.nbt;

import io.github.vicen621.loriath.extensions.LivingEntityExtensions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityNbtMixin extends Entity implements LivingEntityExtensions {

    @Unique
    private int loriath$EGFLinkedEntityID;
    @Unique
    private int loriath$EGFCounter;
    @Unique
    private int loriath$KBImmunityCounter;
    @Unique
    private boolean loriath$canUseEnchants;

    public LivingEntityNbtMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("RETURN"))
    public void writeCustomDataToNbt(NbtCompound tag, CallbackInfo ci) {
        tag.putInt("loriath$EGFLinkedEntityID", this.loriath$EGFLinkedEntityID);
        tag.putInt("loriath$EGFCounter", this.loriath$EGFCounter);
        tag.putInt("loriath$KBImmunityCounter", this.loriath$KBImmunityCounter);
        tag.putBoolean("loriath$canUseEnchants", this.loriath$canUseEnchants);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("RETURN"))
    public void readCustomDataFromNbt(NbtCompound tag, CallbackInfo ci) {
        this.loriath$EGFLinkedEntityID = tag.getInt("loriath$EGFLinkedEntityID");
        this.loriath$EGFCounter = tag.getInt("loriath$EGFCounter");
        this.loriath$KBImmunityCounter = tag.getInt("loriath$KBImmunityCounter");
        this.loriath$canUseEnchants = tag.getBoolean("loriath$canUseEnchants");
    }

    @Unique
    @Override
    public int loriath$getEGFLinkedEntityID() {
        return this.loriath$EGFLinkedEntityID;
    }

    @Unique
    @Override
    public void loriath$setEGFLinkedEntityID(int id) {
        this.loriath$EGFLinkedEntityID = id;
    }

    @Unique
    @Override
    public int loriath$getEGFCounter() {
        return this.loriath$EGFCounter;
    }

    @Unique
    @Override
    public void loriath$setEGFCounter(int counter) {
        this.loriath$EGFCounter = counter;
    }

    @Unique
    @Override
    public int loriath$getKbImmunityCounter() {
        return this.loriath$KBImmunityCounter;
    }

    @Unique
    @Override
    public void loriath$setKbImmunityCounter(int counter) {
        this.loriath$KBImmunityCounter = counter;
    }

    @Unique
    @Override
    public boolean loriath$canUseEnchants() {
        return loriath$canUseEnchants;
    }

    @Unique
    @Override
    public void loriath$setCanUseEnchants(boolean value) {
        this.loriath$canUseEnchants = value;
    }
}
