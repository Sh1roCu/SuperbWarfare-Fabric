package cn.sh1rocu.superbwarfare.mixin.accessor;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Optional;

@Mixin(ItemStack.class)
public interface ItemStackAccessor {
    @Invoker("<init>")
    static ItemStack sw$create(ItemLike item, int count, Optional<CompoundTag> optionalTag) {
        throw new AssertionError();
    }
}
