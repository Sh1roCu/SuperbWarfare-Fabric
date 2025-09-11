package com.atsuishio.superbwarfare.menu;

import com.atsuishio.superbwarfare.network.dataslot.ContainerEnergyData;
import com.atsuishio.superbwarfare.network.dataslot.ContainerEnergyDataSlot;
import com.atsuishio.superbwarfare.network.message.receive.ContainerDataMessage;
import com.google.common.collect.Lists;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class EnergyMenu extends AbstractContainerMenu {

    private final List<ContainerEnergyDataSlot> containerEnergyDataSlots = Lists.newArrayList();
    private final List<ServerPlayer> usingPlayers = new ArrayList<>();

    public EnergyMenu(@Nullable MenuType<?> pMenuType, int pContainerId) {
        super(pMenuType, pContainerId);
    }

    public EnergyMenu(@Nullable MenuType<?> pMenuType, int id, ContainerEnergyData containerData) {
        super(pMenuType, id);

        for (int i = 0; i < containerData.getCount(); ++i) {
            this.containerEnergyDataSlots.add(ContainerEnergyDataSlot.forContainer(containerData, i));
        }
    }

    @Override
    public void broadcastChanges() {
        List<ContainerDataMessage.Pair> pairs = new ArrayList<>();
        for (int i = 0; i < this.containerEnergyDataSlots.size(); ++i) {
            ContainerEnergyDataSlot dataSlot = this.containerEnergyDataSlots.get(i);
            if (dataSlot.checkAndClearUpdateFlag())
                pairs.add(new ContainerDataMessage.Pair(i, dataSlot.get()));
        }

        if (!pairs.isEmpty()) {
            new ContainerDataMessage(this.containerId, pairs).send(this.usingPlayers);
        }

        super.broadcastChanges();
    }

    public void setData(int id, int data) {
        this.containerEnergyDataSlots.get(id).set(data);
    }

    public void setData(int id, long data) {
        this.containerEnergyDataSlots.get(id).set(data);
    }

    public static void onContainerOpened(ServerPlayer player, AbstractContainerMenu container) {
        if (container instanceof EnergyMenu menu) {
            menu.usingPlayers.add(player);

            List<ContainerDataMessage.Pair> toSync = new ArrayList<>();
            for (int i = 0; i < menu.containerEnergyDataSlots.size(); ++i) {
                toSync.add(new ContainerDataMessage.Pair(i, menu.containerEnergyDataSlots.get(i).get()));
            }
            new ContainerDataMessage(menu.containerId, toSync).send(player);
        }
    }

    public static void onContainerClosed(ServerPlayer player, AbstractContainerMenu container) {
        if (container instanceof EnergyMenu menu) {
            menu.usingPlayers.remove(player);
        }
    }
}
