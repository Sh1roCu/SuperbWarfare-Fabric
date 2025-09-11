package cn.sh1rocu.superbwarfare.api.extension.mixin;

import cn.sh1rocu.superbwarfare.util.forge.LazyOptional;
import cn.sh1rocu.superbwarfare.util.forge.itemhandler.IItemHandler;
import net.minecraft.core.Direction;
import org.jetbrains.annotations.Nullable;

public interface ItemHandlerCapability {

    default LazyOptional<IItemHandler> sw$getItemHandler(@Nullable Direction facing) {
        return LazyOptional.empty();
    }

    default void sw$invalidateItemHandler() {
    }

    default void sw$reviveItemHandler() {
    }
}