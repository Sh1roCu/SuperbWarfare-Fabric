package cn.sh1rocu.superbwarfare.api.extension;

import net.minecraft.nbt.CompoundTag;

public interface IEntityPersistentData {
    default CompoundTag sw$getPersistentData() {
        throw new RuntimeException();
    }
}