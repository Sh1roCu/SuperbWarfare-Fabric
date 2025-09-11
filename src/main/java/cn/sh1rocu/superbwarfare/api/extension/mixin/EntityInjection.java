package cn.sh1rocu.superbwarfare.api.extension.mixin;

import net.minecraft.nbt.CompoundTag;

public interface EntityInjection {

    void sw$deserializeNBT(CompoundTag nbt);

    CompoundTag sw$serializeNBT();
}
