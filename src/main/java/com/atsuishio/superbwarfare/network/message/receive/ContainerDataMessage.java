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
import java.util.List;

/**
 * Code based on @GoryMoon's Chargers
 */
public class ContainerDataMessage {

    public static final ResourceLocation ID = Mod.loc("container_data");

    private final int containerId;
    private final List<Pair> data;

    public ContainerDataMessage(int containerId, List<Pair> data) {
        this.containerId = containerId;
        this.data = data;
    }

    public static ContainerDataMessage decode(FriendlyByteBuf buf) {
        return new ContainerDataMessage(buf.readUnsignedByte(), buf.readList(byteBuf -> new Pair(byteBuf.readShort(), byteBuf.readLong())));
    }

    public void send(ServerPlayer player) {
        send(Collections.singleton(player));
    }

    public void send(Collection<ServerPlayer> players) {
        FriendlyByteBuf buf = PacketByteBufs.create();
        buf.writeByte(this.containerId);
        buf.writeCollection(this.data, (byteBuf, p) -> p.write(byteBuf));
        players.forEach(player -> ServerPlayNetworking.send(player, ID, buf));
    }

    @Environment(EnvType.CLIENT)
    public static void handle(Minecraft client, ClientPacketListener handler, FriendlyByteBuf buf, PacketSender responseSender) {
        var message = decode(buf);
        client.execute(() -> ClientPacketHandler.handleContainerDataMessage(message.containerId, message.data));
    }

    public static class Pair {

        public int id;
        public long data;

        public Pair(int id, long data) {
            this.id = id;
            this.data = data;
        }

        public void write(FriendlyByteBuf buf) {
            buf.writeShort(id);
            buf.writeLong(data);
        }
    }

}
