package com.atsuishio.superbwarfare.network.message.receive;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.event.ClientEventHandler;
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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.Collection;
import java.util.Collections;

public class ShakeClientMessage {

    public static final ResourceLocation ID = Mod.loc("shake_client");

    public double time;
    public double radius;
    public double amplitude;
    public double x;
    public double y;
    public double z;

    public ShakeClientMessage(double time, double radius, double amplitude, double x, double y, double z) {
        this.time = time;
        this.radius = radius;
        this.amplitude = amplitude;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void send(ServerPlayer player) {
        send(Collections.singleton(player));
    }

    public void send(Collection<ServerPlayer> players) {
        FriendlyByteBuf buffer = PacketByteBufs.create();
        buffer.writeDouble(this.time);
        buffer.writeDouble(this.radius);
        buffer.writeDouble(this.amplitude);
        buffer.writeDouble(this.x);
        buffer.writeDouble(this.y);
        buffer.writeDouble(this.z);
        players.forEach(player -> ServerPlayNetworking.send(player, ID, buffer));
    }

    public static ShakeClientMessage decode(FriendlyByteBuf buffer) {
        return new ShakeClientMessage(buffer.readDouble(), buffer.readDouble(), buffer.readDouble(), buffer.readDouble(), buffer.readDouble(), buffer.readDouble());
    }

    @Environment(EnvType.CLIENT)
    public static void handle(Minecraft client, ClientPacketListener handler, FriendlyByteBuf buf, PacketSender responseSender) {
        var message = decode(buf);
        client.execute(() -> ClientEventHandler.handleShakeClient(message.time, message.radius, message.amplitude, message.x, message.y, message.z));
    }

    public static void sendToNearbyPlayers(Level level, double x, double y, double z, double sendRadius, double time, double radius, double amplitude) {
        var center = new Vec3(x, y, z);

        ShakeClientMessage message = new ShakeClientMessage(time, radius, amplitude, x, y, z);
        message.send(level.getEntitiesOfClass(ServerPlayer.class, new AABB(center, center).inflate(sendRadius), e -> true));
    }

    public static void sendToNearbyPlayers(Entity source, double sendRadius, double time, double radius, double amplitude) {
        sendToNearbyPlayers(source.level(), source.getX(), source.getY(), source.getZ(), sendRadius, time, radius, amplitude);
    }
}
