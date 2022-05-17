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

    private int EGFLinkedEntityID;
    private int EGFCounter;
    private int KBImmunityCounter;

    public LivingEntityNbtMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("RETURN"))
    public void writeCustomDataToNbt(NbtCompound tag, CallbackInfo ci) {
        tag.putInt("EGFLinkedEntityID", this.EGFLinkedEntityID);
        tag.putInt("EGFCounter", this.EGFCounter);
        tag.putInt("KBImmunityCounter", this.KBImmunityCounter);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("RETURN"))
    public void readCustomDataFromNbt(NbtCompound tag, CallbackInfo ci) {
        this.EGFLinkedEntityID = tag.getInt("EGFLinkedEntityID");
        this.EGFCounter = tag.getInt("EGFCounter");
        this.KBImmunityCounter = tag.getInt("KBImmunityCounter");
    }

    @Unique
    @Override
    public int loriath$getEGFLinkedEntityID() {
        return this.EGFLinkedEntityID;
    }

    @Unique
    @Override
    public void loriath$setEGFLinkedEntityID(int id) {
        this.EGFLinkedEntityID = id;
    }

    @Unique
    @Override
    public int loriath$getEGFCounter() {
        return this.EGFCounter;
    }

    @Unique
    @Override
    public void loriath$setEGFCounter(int counter) {
        this.EGFCounter = counter;
    }

    @Unique
    @Override
    public int loriath$getKbImmunityCounter() {
        return this.KBImmunityCounter;
    }

    @Unique
    @Override
    public void loriath$setKbImmunityCounter(int counter) {
        this.KBImmunityCounter = counter;
    }
}
