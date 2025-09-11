package com.atsuishio.superbwarfare.network.message.receive;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.network.ClientPacketHandler;
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

public class ClientIndicatorMessage {

    public static final ResourceLocation ID = Mod.loc("client_indicator");

    public final int type;
    public final int value;

    public ClientIndicatorMessage(int type, int value) {
        this.type = type;
        this.value = value;
    }

    public void send(ServerPlayer player) {
        send(Collections.singleton(player));
    }

    public void send(Collection<ServerPlayer> players) {
        FriendlyByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(this.type);
        buffer.writeInt(this.value);
        players.forEach(player -> ServerPlayNetworking.send(player, ID, buffer));
    }

    public static ClientIndicatorMessage decode(FriendlyByteBuf buffer) {
        int type = buffer.readInt();
        int value = buffer.readInt();
        return new ClientIndicatorMessage(type, value);
    }

    @Environment(EnvType.CLIENT)
    public static void handle(Minecraft client, ClientPacketListener handler, FriendlyByteBuf buf, PacketSender responseSender) {
        var message = decode(buf);
        client.execute(() -> ClientPacketHandler.handleClientIndicatorMessage(message));
    }
}
