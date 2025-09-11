package com.atsuishio.superbwarfare.capability;

import cn.sh1rocu.superbwarfare.api.event.PlayerEvent;
import com.atsuishio.superbwarfare.capability.player.PlayerVariable;
import com.atsuishio.superbwarfare.network.message.receive.PlayerVariablesSyncMessage;
import com.atsuishio.superbwarfare.tools.Ammo;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class CapabilityHandler {

    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        new PlayerVariablesSyncMessage(player.getId(), PlayerVariable.getOrDefault(player).compareAndUpdate()).send(player);
    }

    public static void onPlayerRespawn(ServerPlayer oldPlayer, ServerPlayer newPlayer, boolean alive) {
        new PlayerVariablesSyncMessage(newPlayer.getId(), PlayerVariable.getOrDefault(newPlayer).compareAndUpdate()).send(newPlayer);
    }

    public static void onPlayerChangeDimension(ServerPlayer player, ServerLevel origin, ServerLevel destination) {
        new PlayerVariablesSyncMessage(player.getId(), PlayerVariable.getOrDefault(player).forceUpdate()).send(player);
    }

    public static void clonePlayer(ServerPlayer oldPlayer, ServerPlayer newPlayer, boolean alive) {
        var original = PlayerVariable.getOrDefault(oldPlayer);
        var clone = ModCapabilities.PLAYER_VARIABLE.maybeGet(newPlayer).orElse(new PlayerVariable());

        for (var type : Ammo.values()) {
            type.set(clone, type.get(original));
        }

        clone.tacticalSprint = original.tacticalSprint;

        if (newPlayer.level().isClientSide()) return;

        ModCapabilities.PLAYER_VARIABLE.maybeGet(newPlayer).orElse(new PlayerVariable()).sync(newPlayer);
    }
}
