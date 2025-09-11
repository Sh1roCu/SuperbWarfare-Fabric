package cn.sh1rocu.superbwarfare.util.forge.itemhandler;

import cn.sh1rocu.superbwarfare.util.forge.itemhandler.IItemHandler;
import net.minecraft.world.item.ItemStack;

public interface IItemHandlerModifiable extends IItemHandler {
    void setStackInSlot(int slot, ItemStack stack);
}