package com.atsuishio.superbwarfare.item;

import com.atsuishio.superbwarfare.client.tooltip.component.CellImageComponent;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.base.SimpleEnergyItem;

import java.util.Optional;
import java.util.function.Supplier;

public class BatteryItem extends Item implements SimpleEnergyItem {

    private final Supplier<Long> energyCapacity;
    public long maxEnergy;

    public BatteryItem(long maxEnergy, Properties properties) {
        super(properties.stacksTo(1));
        this.maxEnergy = maxEnergy;
        this.energyCapacity = () -> maxEnergy;
        EnergyStorage.ITEM.registerForItems((stack, context) -> SimpleEnergyItem.createStorage(context, this.getEnergyCapacity(stack), this.getEnergyMaxInput(stack), this.getEnergyMaxOutput(stack)), this);
    }

    @Override
    public boolean isBarVisible(ItemStack pStack) {
        var storage = EnergyStorage.ITEM.find(pStack, ContainerItemContext.withConstant(pStack));
        return storage != null && storage.getAmount() != maxEnergy;
    }

    @Override
    public int getBarWidth(ItemStack pStack) {
        var storage = EnergyStorage.ITEM.find(pStack, ContainerItemContext.withConstant(pStack));
        var energy = storage == null ? 0 : storage.getAmount();

        return Math.round((float) energy * 13.0F / maxEnergy);
    }

    @Override
    public int getBarColor(@NotNull ItemStack pStack) {
        return 0xFFFF00;
    }

    @Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(@NotNull ItemStack pStack) {
        return Optional.of(new CellImageComponent(pStack));
    }

    public ItemStack makeFullEnergyStack() {
        ItemStack stack = new ItemStack(this);
        this.setStoredEnergy(stack, maxEnergy);
        return stack;
    }

    @Override
    public long getEnergyCapacity(ItemStack stack) {
        return energyCapacity.get();
    }

    @Override
    public long getEnergyMaxInput(ItemStack stack) {
        return energyCapacity.get();
    }

    @Override
    public long getEnergyMaxOutput(ItemStack stack) {
        return energyCapacity.get();
    }
}
