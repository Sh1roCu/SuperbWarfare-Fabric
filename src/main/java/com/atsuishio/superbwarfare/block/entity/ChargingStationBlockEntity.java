package com.atsuishio.superbwarfare.block.entity;

import cn.sh1rocu.superbwarfare.util.fabric.ItemEnergyStorageHelper;
import com.atsuishio.superbwarfare.block.ChargingStationBlock;
import com.atsuishio.superbwarfare.config.server.MiscConfig;
import com.atsuishio.superbwarfare.entity.vehicle.base.VehicleEntity;
import com.atsuishio.superbwarfare.init.ModBlockEntities;
import com.atsuishio.superbwarfare.menu.ChargingStationMenu;
import com.atsuishio.superbwarfare.network.dataslot.ContainerEnergyData;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.EnergyStorageUtil;
import team.reborn.energy.api.base.SimpleEnergyStorage;

import java.util.List;

/**
 * Energy Data Slot Code based on @GoryMoon's Chargers
 */
public class ChargingStationBlockEntity extends BlockEntity implements WorldlyContainer, MenuProvider {

    protected static final int SLOT_FUEL = 0;
    protected static final int SLOT_CHARGE = 1;
    public static final int MAX_DATA_COUNT = 4;
    protected NonNullList<ItemStack> items = NonNullList.withSize(2, ItemStack.EMPTY);

    public static final long MAX_ENERGY = MiscConfig.CHARGING_STATION_MAX_ENERGY.get();
    public static final int DEFAULT_FUEL_TIME = MiscConfig.CHARGING_STATION_DEFAULT_FUEL_TIME.get();
    public static final int CHARGE_SPEED = MiscConfig.CHARGING_STATION_GENERATE_SPEED.get();
    public static final int CHARGE_OTHER_SPEED = MiscConfig.CHARGING_STATION_TRANSFER_SPEED.get();
    public static final int CHARGE_RADIUS = MiscConfig.CHARGING_STATION_CHARGE_RADIUS.get();

    public final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(ChargingStationBlockEntity.MAX_ENERGY, ChargingStationBlockEntity.MAX_ENERGY, ChargingStationBlockEntity.MAX_ENERGY);

    public int fuelTick = 0;
    public int maxFuelTick = DEFAULT_FUEL_TIME;
    public boolean showRange = false;

    protected final ContainerEnergyData dataAccess = new ContainerEnergyData() {
        public long get(int pIndex) {
            return switch (pIndex) {
                case 0 -> ChargingStationBlockEntity.this.fuelTick;
                case 1 -> ChargingStationBlockEntity.this.maxFuelTick;
                case 2 -> ChargingStationBlockEntity.this.energyStorage.getAmount();
                case 3 -> ChargingStationBlockEntity.this.showRange ? 1 : 0;
                default -> 0;
            };
        }

        public void set(int pIndex, long pValue) {
            switch (pIndex) {
                case 0:
                    ChargingStationBlockEntity.this.fuelTick = (int) pValue;
                    break;
                case 1:
                    ChargingStationBlockEntity.this.maxFuelTick = (int) pValue;
                    break;
                case 2:
                    ChargingStationBlockEntity.this.energyStorage.amount = pValue;
                    break;
                case 3:
                    ChargingStationBlockEntity.this.showRange = pValue == 1;
                    break;
            }
        }

        public int getCount() {
            return MAX_DATA_COUNT;
        }
    };

    public ChargingStationBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CHARGING_STATION, pos, state);
    }

    public static void serverTick(Level pLevel, BlockPos pPos, BlockState pState, ChargingStationBlockEntity blockEntity) {
        if (blockEntity.showRange != pState.getValue(ChargingStationBlock.SHOW_RANGE)) {
            pLevel.setBlockAndUpdate(pPos, pState.setValue(ChargingStationBlock.SHOW_RANGE, blockEntity.showRange));
            setChanged(pLevel, pPos, pState);
        }

        SimpleEnergyStorage storage = blockEntity.energyStorage;
        if (storage.getAmount() > 0) {
            blockEntity.chargeEntity(storage);
        }
        if (storage.getAmount() > 0) {
            blockEntity.chargeItemStack(storage);
        }
        if (storage.getAmount() > 0) {
            blockEntity.chargeBlock(storage);
        }
        if (blockEntity.fuelTick > 0) {
            blockEntity.fuelTick--;
            long energy = storage.getAmount();
            if (energy < storage.getCapacity()) {
                try (Transaction transaction = Transaction.openOuter()) {
                    storage.insert(CHARGE_SPEED, transaction);
                    transaction.commit();
                }
            }
        } else if (!blockEntity.getItem(SLOT_FUEL).isEmpty()) {
            boolean[] flag = {false};
            if (storage.getAmount() >= storage.getCapacity()) {
                flag[0] = true;
            }
            if (flag[0]) return;

            ItemStack fuel = blockEntity.getItem(SLOT_FUEL);
            Integer i = FuelRegistry.INSTANCE.get(fuel.getItem());
            int burnTime = i == null ? 0 : i;

            var cap = ItemEnergyStorageHelper.fromStack(fuel);
            if (cap.isPresent()) {
                cap.ifPresent(fuelStorage -> {
                    // 优先当作电池处理
                    var energyToExtract = Math.min(CHARGE_OTHER_SPEED, storage.getCapacity() - storage.getAmount());
                    if (fuelStorage.supportsExtraction() && storage.supportsInsertion()) {
                        EnergyStorageUtil.move(fuelStorage, storage, energyToExtract, null);
                    }
                    blockEntity.setChanged();
                });
            } else if (burnTime > 0) {
                // 其次尝试作为燃料处理
                blockEntity.fuelTick = burnTime;
                blockEntity.maxFuelTick = burnTime;

                if (!fuel.getRecipeRemainder().isEmpty()) {
                    if (fuel.getCount() <= 1) {
                        blockEntity.setItem(SLOT_FUEL, fuel.getRecipeRemainder());
                    } else {
                        ItemStack copy = fuel.getRecipeRemainder().copy();
                        copy.setCount(1);

                        ItemEntity itemEntity = new ItemEntity(pLevel,
                                pPos.getX() + 0.5,
                                pPos.getY() + 0.2,
                                pPos.getZ() + 0.5,
                                copy);
                        pLevel.addFreshEntity(itemEntity);

                        fuel.shrink(1);
                    }
                } else {
                    fuel.shrink(1);
                }

                blockEntity.setChanged();
            } else if (fuel.getItem().isEdible()) {
                // 最后作为食物处理

                var properties = fuel.getItem().getFoodProperties();
                if (properties == null) return;

                int nutrition = properties.getNutrition();
                float saturation = properties.getSaturationModifier() * 2.0f * nutrition;
                int tick = nutrition * 80 + (int) (saturation * 200);

                if (!fuel.getRecipeRemainder().isEmpty()) {
                    tick += 400;
                }

                fuel.shrink(1);

                blockEntity.fuelTick = tick;
                blockEntity.maxFuelTick = tick;
                blockEntity.setChanged();
            }
        }
    }

    private void chargeEntity(EnergyStorage handler) {
        if (this.level == null) return;
        if (this.level.getGameTime() % 20 != 0) return;

        List<Entity> entities = this.level.getEntitiesOfClass(Entity.class, new AABB(this.getBlockPos()).inflate(CHARGE_RADIUS));
        entities.forEach(entity -> {
            if (entity instanceof VehicleEntity vehicle && vehicle.hasEnergyStorage()) {
                EnergyStorage storage = vehicle.getEnergyStorage();
                EnergyStorageUtil.move(handler, storage, Math.min(handler.getAmount(), CHARGE_OTHER_SPEED * 20), null);
            }
        });
        this.setChanged();
    }

    private void chargeItemStack(EnergyStorage handler) {
        ItemStack stack = this.getItem(SLOT_CHARGE);
        if (stack.isEmpty()) return;

        ItemEnergyStorageHelper.fromStack(stack).ifPresent(storage -> {
            if (storage.getAmount() < storage.getCapacity()) {
                EnergyStorageUtil.move(handler, storage, Math.min(CHARGE_OTHER_SPEED, handler.getAmount()), null);
                this.setChanged();
            }
        });
    }

    private void chargeBlock(EnergyStorage handler) {
        if (this.level == null) return;

        for (Direction direction : Direction.values()) {
            var blockEntity = this.level.getBlockEntity(this.getBlockPos().relative(direction));
            if (blockEntity == null) continue;
            EnergyStorage storage = EnergyStorage.SIDED.find(blockEntity.getLevel(), blockEntity.getBlockPos(), blockEntity.getBlockState(), blockEntity, direction);
            if (storage == null || blockEntity instanceof ChargingStationBlockEntity) {
                continue;
            }

            if (storage.supportsInsertion() && storage.getAmount() < storage.getCapacity()) {
                try (Transaction transaction = Transaction.openOuter()) {
                    long receiveEnergy = storage.insert(Math.min(handler.getAmount(), CHARGE_OTHER_SPEED), transaction);
                    handler.extract(receiveEnergy, transaction);
                    transaction.commit();
                }
                blockEntity.setChanged();
                this.setChanged();
            }
        }
    }

    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);

        this.energyStorage.amount = pTag.getLong("Energy");
        this.fuelTick = pTag.getInt("FuelTick");
        this.maxFuelTick = pTag.getInt("MaxFuelTick");
        this.showRange = pTag.getBoolean("ShowRange");
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(pTag, this.items);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);

        pTag.putLong("Energy", this.energyStorage.amount);
        pTag.putInt("FuelTick", this.fuelTick);
        pTag.putInt("MaxFuelTick", this.maxFuelTick);
        pTag.putBoolean("ShowRange", this.showRange);
        ContainerHelper.saveAllItems(pTag, this.items);
    }

    @Override
    public int[] getSlotsForFace(Direction pSide) {
        return new int[]{SLOT_FUEL};
    }

    @Override
    public boolean canPlaceItemThroughFace(int pIndex, ItemStack pItemStack, @Nullable Direction pDirection) {
        return pIndex == SLOT_FUEL;
    }

    @Override
    public boolean canTakeItemThroughFace(int pIndex, ItemStack pStack, Direction pDirection) {
        return false;
    }

    @Override
    public int getContainerSize() {
        return this.items.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.items) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public ItemStack getItem(int pSlot) {
        return this.items.get(pSlot);
    }

    @Override
    public ItemStack removeItem(int pSlot, int pAmount) {
        return ContainerHelper.removeItem(this.items, pSlot, pAmount);
    }

    @Override
    public ItemStack removeItemNoUpdate(int pSlot) {
        return ContainerHelper.takeItem(this.items, pSlot);
    }

    @Override
    public void setItem(int pSlot, ItemStack pStack) {
        ItemStack itemstack = this.items.get(pSlot);
        boolean flag = !pStack.isEmpty() && net.minecraft.world.item.ItemStack.isSameItemSameTags(itemstack, pStack);
        this.items.set(pSlot, pStack);
        if (pStack.getCount() > this.getMaxStackSize()) {
            pStack.setCount(this.getMaxStackSize());
        }

        if (pSlot == 0 && !flag) {
            this.setChanged();
        }
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return Container.stillValidBlockEntity(this, pPlayer);
    }

    @Override
    public void clearContent() {
        this.items.clear();
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.superbwarfare.charging_station");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new ChargingStationMenu(pContainerId, pPlayerInventory, this, this.dataAccess);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag compoundtag = new CompoundTag();
        ContainerHelper.saveAllItems(compoundtag, this.items, true);
        compoundtag.putBoolean("ShowRange", this.showRange);
        return compoundtag;
    }

    @Override
    public void saveToItem(ItemStack pStack) {
        CompoundTag tag = new CompoundTag();
        tag.putLong("Energy", this.energyStorage.amount);
        BlockItem.setBlockEntityData(pStack, this.getType(), tag);
    }
}
