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
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

import java.util.Collection;
import java.util.Collections;

public record ClientSetMotionMessage(Vec3 motion) {

    public static final ResourceLocation ID = Mod.loc("client_set_motion");

    public void send(ServerPlayer player) {
        send(Collections.singleton(player));
    }

    public void send(Collection<ServerPlayer> players) {
        FriendlyByteBuf buffer = PacketByteBufs.create();
        buffer.writeVector3f(this.motion.toVector3f());
        players.forEach(player -> ServerPlayNetworking.send(player, ID, buffer));
    }

    public static ClientSetMotionMessage decode(FriendlyByteBuf buffer) {
        Vector3f v = buffer.readVector3f();
        return new ClientSetMotionMessage(new Vec3(v));
    }

    @Environment(EnvType.CLIENT)
    public static void handle(Minecraft client, ClientPacketListener handler, FriendlyByteBuf buf, PacketSender responseSender) {
        var message = decode(buf);
        client.execute(() -> ClientPacketHandler.handleClientSetMotion(message));
    }
}
