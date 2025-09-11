package com.atsuishio.superbwarfare.capability.player;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.capability.ModCapabilities;
import com.atsuishio.superbwarfare.network.message.receive.PlayerVariablesSyncMessage;
import com.atsuishio.superbwarfare.tools.Ammo;
import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.entity.PlayerComponent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class PlayerVariable implements AutoSyncedComponent, PlayerComponent<Component> {
    public static final ResourceLocation ID = Mod.loc("player_variables");
    private PlayerVariable old = null;

    public Map<Ammo, Integer> ammo = new HashMap<>();
    public boolean tacticalSprint = false;

    public PlayerVariable() {

    }

    public PlayerVariable(Player player) {

    }

    public void sync(Entity entity) {
        if (ModCapabilities.PLAYER_VARIABLE.maybeGet(entity).isEmpty()) return;

        var newVariable = getOrDefault(entity);
        if (old != null && old.equals(newVariable)) return;

        if (entity instanceof ServerPlayer player) {
            new PlayerVariablesSyncMessage(entity.getId(), compareAndUpdate()).send(player);
        }
    }

    public static PlayerVariable getOrDefault(Entity entity) {
        return ModCapabilities.PLAYER_VARIABLE.maybeGet(entity).orElse(new PlayerVariable());
    }

    /**
     * 编辑并自动同步玩家变量
     */
    public static void modify(Entity entity, Consumer<PlayerVariable> consumer) {
        if (entity.level().isClientSide) return;
        var cap = ModCapabilities.PLAYER_VARIABLE.maybeGet(entity).orElse(new PlayerVariable());

        cap.watch();
        consumer.accept(cap);
        cap.sync(entity);
    }

    public PlayerVariable watch() {
        this.old = this.copy();
        return this;
    }

    public Map<Byte, Integer> forceUpdate() {
        var map = new HashMap<Byte, Integer>();

        for (var type : Ammo.values()) {
            map.put((byte) type.ordinal(), type.get(this));
        }

        map.put((byte) -1, this.tacticalSprint ? 1 : 0);

        return map;
    }

    public Map<Byte, Integer> compareAndUpdate() {
        var map = new HashMap<Byte, Integer>();
        var old = this.old == null ? new PlayerVariable() : this.old;

        for (var type : Ammo.values()) {
            var oldCount = old.ammo.getOrDefault(type, 0);
            var newCount = type.get(this);

            if (oldCount != newCount) {
                map.put((byte) type.ordinal(), newCount);
            }
        }

        if (old.tacticalSprint != this.tacticalSprint) {
            map.put((byte) -1, this.tacticalSprint ? 1 : 0);
        }

        return map;
    }

    public void writeToNBT(CompoundTag nbt) {
        for (var type : Ammo.values()) {
            type.set(nbt, type.get(this));
        }

        nbt.putBoolean("TacticalSprint", tacticalSprint);
    }

    public void readFromNBT(CompoundTag tag) {
        for (var type : Ammo.values()) {
            type.set(this, type.get(tag));
        }

        tacticalSprint = tag.getBoolean("TacticalSprint");
    }

    public PlayerVariable copy() {
        var clone = new PlayerVariable();

        for (var type : Ammo.values()) {
            type.set(clone, type.get(this));
        }

        clone.tacticalSprint = this.tacticalSprint;

        return clone;
    }

    @Override
    public void readFromNbt(@NotNull CompoundTag tag) {
        readFromNBT(tag);
    }

    @Override
    public void writeToNbt(@NotNull CompoundTag tag) {
        writeToNBT(tag);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PlayerVariable other)) return false;

        for (var type : Ammo.values()) {
            if (type.get(this) != type.get(other)) return false;
        }

        return tacticalSprint == other.tacticalSprint;
    }

}
