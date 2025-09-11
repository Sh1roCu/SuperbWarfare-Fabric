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
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import java.util.Collection;
import java.util.Collections;

public class RadarMenuOpenMessage {

    public static final ResourceLocation ID = Mod.loc("radar_menu_open");

    public BlockPos pos;

    public RadarMenuOpenMessage(BlockPos pos) {
        this.pos = pos;
    }

    public void send(ServerPlayer player) {
        send(Collections.singleton(player));
    }

    public void send(Collection<ServerPlayer> players) {
        FriendlyByteBuf buffer = PacketByteBufs.create();
        buffer.writeBlockPos(this.pos);
        players.forEach(player -> ServerPlayNetworking.send(player, ID, buffer));
    }

    public static RadarMenuOpenMessage decode(FriendlyByteBuf buffer) {
        return new RadarMenuOpenMessage(buffer.readBlockPos());
    }

    @Environment(EnvType.CLIENT)
    public static void handle(Minecraft client, ClientPacketListener handler, FriendlyByteBuf buf, PacketSender responseSender) {
        var message = decode(buf);
        client.execute(() -> ClientPacketHandler.handleRadarMenuOpen(message));
    }
}
