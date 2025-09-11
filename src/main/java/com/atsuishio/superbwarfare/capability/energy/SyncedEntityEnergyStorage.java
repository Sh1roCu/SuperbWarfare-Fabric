package com.atsuishio.superbwarfare.capability.energy;

import net.fabricmc.fabric.api.transfer.v1.storage.StoragePreconditions;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.fabricmc.fabric.api.transfer.v1.transaction.base.SnapshotParticipant;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import team.reborn.energy.api.EnergyStorage;

/**
 * 自动同步的实体能量存储能力，会和客户端自动同步实体的当前能量值
 */
public class SyncedEntityEnergyStorage extends SnapshotParticipant<Long> implements EnergyStorage {

    private long amount = 0;
    protected long capacity;
    protected long maxInsert, maxExtract;

    protected SynchedEntityData entityData;
    protected EntityDataAccessor<Long> energyDataAccessor;

    /**
     * 自动同步的实体能量存储能力
     *
     * @param capacity           能量上限
     * @param data               实体的entityData
     * @param energyDataAccessor 能量的EntityDataAccessor
     */
    public SyncedEntityEnergyStorage(long capacity, SynchedEntityData data, EntityDataAccessor<Long> energyDataAccessor) {
        this(capacity, capacity, capacity, data, energyDataAccessor);
    }

    public SyncedEntityEnergyStorage(long capacity, long maxInsert, long maxExtract, SynchedEntityData data, EntityDataAccessor<Long> energyDataAccessor) {
        this.capacity = capacity;
        this.maxInsert = maxInsert;
        this.maxExtract = maxExtract;
        this.entityData = data;
        this.energyDataAccessor = energyDataAccessor;
        this.amount = entityData.get(this.energyDataAccessor);
    }

    @Override
    public long insert(long maxAmount, TransactionContext transaction) {
        long received = insertInternal(maxAmount, transaction);

        entityData.set(energyDataAccessor, this.amount);

        return received;
    }

    @Override
    public long extract(long maxAmount, TransactionContext transaction) {
        var extracted = extractInternal(maxAmount, transaction);

        entityData.set(energyDataAccessor, this.amount);

        return extracted;
    }

    private long insertInternal(long maxAmount, TransactionContext transaction) {
        StoragePreconditions.notNegative(maxAmount);

        long inserted = Math.min(maxInsert, Math.min(maxAmount, capacity - amount));

        if (inserted > 0) {
            updateSnapshots(transaction);
            amount += inserted;
            return inserted;
        }

        return 0;
    }

    private long extractInternal(long maxAmount, TransactionContext transaction) {
        StoragePreconditions.notNegative(maxAmount);

        long extracted = Math.min(maxExtract, Math.min(maxAmount, amount));

        if (extracted > 0) {
            updateSnapshots(transaction);
            amount -= extracted;
            return extracted;
        }

        return 0;
    }

    @Override
    public long getAmount() {
        // 获取同步数据，保证客户端能正确获得能量值
        return entityData.get(energyDataAccessor);
    }

    @Override
    public long getCapacity() {
        return capacity;
    }

    public void setAmount(long amount) {
        setEnergy(amount);
    }

    public void setEnergy(long energy) {
        this.amount = energy;
        entityData.set(energyDataAccessor, energy);
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setMaxExtract(int maxExtract) {
        this.maxExtract = maxExtract;
    }

    public void setMaxReceive(int maxReceive) {
        this.maxInsert = maxReceive;
    }

    @Override
    protected Long createSnapshot() {
        return getAmount();
    }

    @Override
    protected void readSnapshot(Long snapshot) {
        setEnergy(snapshot);
    }
}
