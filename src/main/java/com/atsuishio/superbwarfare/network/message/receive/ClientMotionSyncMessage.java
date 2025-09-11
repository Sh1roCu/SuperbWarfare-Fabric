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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

import java.util.Collection;
import java.util.Collections;

public class ClientMotionSyncMessage {

    public static final ResourceLocation ID = Mod.loc("client_motion");

    public final int id;
    public final float x;
    public final float y;
    public final float z;

    public ClientMotionSyncMessage(Entity entity) {
        this(entity.getId(), entity.getDeltaMovement());
    }

    public ClientMotionSyncMessage(int id, Vec3 motion) {
        this.id = id;
        this.x = (float) motion.x;
        this.y = (float) motion.y;
        this.z = (float) motion.z;
    }

    public void send(ServerPlayer player) {
        send(Collections.singleton(player));
    }

    public void send(Collection<ServerPlayer> players) {
        FriendlyByteBuf buffer = PacketByteBufs.create();
        buffer.writeVarInt(this.id);
        buffer.writeFloat(this.x);
        buffer.writeFloat(this.y);
        buffer.writeFloat(this.z);
        players.forEach(player -> ServerPlayNetworking.send(player, ID, buffer));
    }

    public static ClientMotionSyncMessage decode(FriendlyByteBuf buffer) {
        int id = buffer.readVarInt();
        double x = buffer.readFloat();
        double y = buffer.readFloat();
        double z = buffer.readFloat();
        return new ClientMotionSyncMessage(id, new Vec3(x, y, z));
    }

    @Environment(EnvType.CLIENT)
    public static void handle(Minecraft client, ClientPacketListener handler, FriendlyByteBuf buf, PacketSender responseSender) {
        var message = decode(buf);
        client.execute(() -> ClientPacketHandler.handleClientSyncMotion(message));
    }
}
