package com.atsuishio.superbwarfare.network.message.receive;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.data.gun.DefaultGunData;
import com.atsuishio.superbwarfare.tools.BufferSerializer;
import com.atsuishio.superbwarfare.tools.GunsTool;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GunsDataMessage {

    public static final ResourceLocation ID = Mod.loc("guns_data");

    public final List<DefaultGunData> data;

    private GunsDataMessage(List<DefaultGunData> data) {
        this.data = data;
    }

    public static GunsDataMessage create() {
        return new GunsDataMessage(GunsTool.gunsData.values().stream().toList());
    }

    public void send(ServerPlayer player) {
        send(Collections.singleton(player));
    }

    public void send(Collection<ServerPlayer> players) {
        var obj = this.data;

        FriendlyByteBuf buf = PacketByteBufs.create();
        buf.writeVarInt(obj.size());
        for (var data : obj) {
            buf.writeBytes(BufferSerializer.serialize(data).copy());
        }
        players.forEach(player -> ServerPlayNetworking.send(player, ID, buf));
    }

    public static GunsDataMessage decode(FriendlyByteBuf buffer) {
        var size = buffer.readVarInt();
        var list = new ArrayList<DefaultGunData>();
        for (var i = 0; i < size; i++) {
            list.add(BufferSerializer.deserialize(buffer, new DefaultGunData()));
        }
        return new GunsDataMessage(list);
    }

    @Environment(EnvType.CLIENT)
    public static void handle(Minecraft client, ClientPacketListener handler, FriendlyByteBuf buf, PacketSender responseSender) {
        var message = decode(buf);
        GunsTool.gunsData.clear();

        for (var entry : message.data) {
            if (GunsTool.gunsData.containsKey(entry.id)) continue;
            GunsTool.gunsData.put(entry.id, entry);
        }
    }
}
