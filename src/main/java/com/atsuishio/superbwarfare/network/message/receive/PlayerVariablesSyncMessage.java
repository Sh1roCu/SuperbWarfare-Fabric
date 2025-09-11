package com.atsuishio.superbwarfare.network.message.receive;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.capability.ModCapabilities;
import com.atsuishio.superbwarfare.capability.player.PlayerVariable;
import com.atsuishio.superbwarfare.tools.Ammo;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class PlayerVariablesSyncMessage {
    public static final ResourceLocation ID = Mod.loc("player_variables");

    private final int target;
    private final Map<Byte, Integer> data;

    public PlayerVariablesSyncMessage(FriendlyByteBuf buffer) {
        this.target = buffer.readVarInt();
        this.data = buffer.readMap(FriendlyByteBuf::readByte, FriendlyByteBuf::readVarInt);
    }

    public PlayerVariablesSyncMessage(int entityId, Map<Byte, Integer> data) {
        this.data = data;
        this.target = entityId;
    }

    public void send(ServerPlayer player) {
        send(Collections.singleton(player));
    }

    public void send(Collection<ServerPlayer> players) {
        FriendlyByteBuf buffer = PacketByteBufs.create();
        buffer.writeVarInt(this.target);
        buffer.writeMap(this.data, (buf, key) -> buf.writeByte(key), FriendlyByteBuf::writeVarInt);
        players.forEach(player -> ServerPlayNetworking.send(player, ID, buffer));
    }

    @Environment(EnvType.CLIENT)
    public static void handle(Minecraft client, ClientPacketListener handler, FriendlyByteBuf buf, PacketSender responseSender) {
        var message = new PlayerVariablesSyncMessage(buf);
        client.execute(() -> {
            var entity = Minecraft.getInstance().player.level().getEntity(message.target);
            if (entity == null) return;

            PlayerVariable variables = ModCapabilities.PLAYER_VARIABLE.maybeGet(entity).orElse(new PlayerVariable());

            for (var entry : message.data.entrySet()) {
                var type = entry.getKey();
                if (type == -1) {
                    variables.tacticalSprint = entry.getValue() == 1;
                } else {
                    var types = Ammo.values();
                    if (type < types.length) {
                        types[type].set(variables, entry.getValue());
                    }
                }
            }
        });
    }
}
