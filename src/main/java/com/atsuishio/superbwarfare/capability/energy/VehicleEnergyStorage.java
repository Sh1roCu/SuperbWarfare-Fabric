package com.atsuishio.superbwarfare.capability.energy;

import com.atsuishio.superbwarfare.data.vehicle.VehicleData;
import com.atsuishio.superbwarfare.data.vehicle.VehicleProp;
import com.atsuishio.superbwarfare.entity.vehicle.base.VehicleEntity;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;

public class VehicleEnergyStorage extends SyncedEntityEnergyStorage {

    protected VehicleEntity vehicle;

    public VehicleEnergyStorage(VehicleEntity vehicle) {
        super(Long.MAX_VALUE, vehicle.getEntityData(), vehicle.getEnergyDataAccessor());

        this.vehicle = vehicle;
    }

    @Override
    public long extract(long maxExtract, TransactionContext transaction) {
        if (VehicleData.getDefault(vehicle).isDefaultData) return 0;

        this.capacity = getCapacity();
        this.maxExtract = getCapacity();
        return super.extract(maxExtract, transaction);
    }

    @Override
    public long insert(long maxReceive, TransactionContext transaction) {
        if (VehicleData.getDefault(vehicle).isDefaultData) return 0;

        this.capacity = getCapacity();
        this.maxInsert = getCapacity();

        return super.insert(maxReceive, transaction);
    }

    @Override
    public boolean supportsInsertion() {
        return !VehicleData.getDefault(vehicle).isDefaultData && super.supportsInsertion();
    }

    @Override
    public boolean supportsExtraction() {
        return !VehicleData.getDefault(vehicle).isDefaultData && super.supportsExtraction();
    }

    @Override
    public long getCapacity() {
        return VehicleData.from(vehicle).get(VehicleProp.MAX_ENERGY);
    }
}
