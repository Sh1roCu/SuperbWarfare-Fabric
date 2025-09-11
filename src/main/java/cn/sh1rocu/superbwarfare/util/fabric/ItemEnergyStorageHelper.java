package cn.sh1rocu.superbwarfare.util.fabric;

import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.item.base.SingleStackStorage;
import net.minecraft.world.item.ItemStack;
import team.reborn.energy.api.EnergyStorage;

import java.util.Optional;

public class ItemEnergyStorageHelper {
    public static Optional<EnergyStorage> fromStack(ItemStack stack) {
        ContainerItemContext context = ContainerItemContext.ofSingleSlot(new SingleStackStorage() {
            private ItemStack itemStack = stack;

            @Override
            protected ItemStack getStack() {
                return itemStack;
            }

            @Override
            protected void setStack(final ItemStack stack) {
                itemStack = stack;
            }

            @Override
            protected void onFinalCommit() {
                stack.setTag(itemStack.getTag());
            }
        });

        return Optional.ofNullable(EnergyStorage.ITEM.find(stack, context));
    }
}
