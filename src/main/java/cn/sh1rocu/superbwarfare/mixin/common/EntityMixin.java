package cn.sh1rocu.superbwarfare.mixin.common;

import cn.sh1rocu.superbwarfare.api.extension.IEntity;
import cn.sh1rocu.superbwarfare.api.extension.IEntityPersistentData;
import cn.sh1rocu.superbwarfare.api.extension.mixin.EntityInjection;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin implements EntityInjection, IEntityPersistentData {
    @Shadow
    public abstract void load(CompoundTag compound);

    @Shadow
    @Nullable
    protected abstract String getEncodeId();

    @Shadow
    public abstract CompoundTag saveWithoutId(CompoundTag compound);

    @Override
    public void sw$deserializeNBT(CompoundTag nbt) {
        this.load(nbt);
    }

    @Override
    public CompoundTag sw$serializeNBT() {
        CompoundTag ret = new CompoundTag();
        String id = this.getEncodeId();
        if (id != null) {
            ret.putString("id", this.getEncodeId());
        }
        return this.saveWithoutId(ret);
    }

    @Unique
    private CompoundTag sw$persistentData;

    @Unique
    @Override
    public CompoundTag sw$getPersistentData() {
        if (this.sw$persistentData == null) {
            this.sw$persistentData = new CompoundTag();
        }
        return sw$persistentData;
    }

    @Inject(method = "saveWithoutId", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;addAdditionalSaveData(Lnet/minecraft/nbt/CompoundTag;)V"))
    private void sw$savePersistentData(CompoundTag nbt, CallbackInfoReturnable<CompoundTag> cir) {
        if (this.sw$persistentData != null) {
            nbt.put("ForgeData", this.sw$persistentData.copy());
        }
    }

    @Inject(method = "load", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;readAdditionalSaveData(Lnet/minecraft/nbt/CompoundTag;)V"))
    private void sw$loadPersistentData(CompoundTag nbt, CallbackInfo ci) {
        if (nbt.contains("ForgeData", 10)) {
            sw$persistentData = nbt.getCompound("ForgeData");
        }
    }

    @Inject(method = "setPosRaw", at = @At("TAIL"))
    private void sw$setPosRaw(double x, double y, double z, CallbackInfo ci) {
        Entity self = (Entity) (Object) this;
        if (self instanceof IEntity entity && entity.isAddedToWorld() && !self.level().isClientSide && !self.isRemoved())
            //强加载区块
            self.level().getChunk((int) Math.floor(x) >> 4, (int) Math.floor(z) >> 4);
    }
}
