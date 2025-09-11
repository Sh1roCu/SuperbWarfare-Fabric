package com.atsuishio.superbwarfare.network.message.receive;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.data.vehicle.DefaultVehicleData;
import com.atsuishio.superbwarfare.data.vehicle.VehicleDataTool;
import com.atsuishio.superbwarfare.tools.BufferSerializer;
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

public record VehiclesDataMessage(List<DefaultVehicleData> data) {

    public static final ResourceLocation ID = Mod.loc("vehicles_data");

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

    public static VehiclesDataMessage decode(FriendlyByteBuf buffer) {
        var size = buffer.readVarInt();
        var list = new ArrayList<DefaultVehicleData>();
        for (var i = 0; i < size; i++) {
            list.add(BufferSerializer.deserialize(buffer, new DefaultVehicleData()));
        }
        return new VehiclesDataMessage(list);
    }

    public static VehiclesDataMessage create() {
        return new VehiclesDataMessage(VehicleDataTool.vehicleData.values().stream().toList());
    }

    @Environment(EnvType.CLIENT)
    public static void handle(Minecraft client, ClientPacketListener handler, FriendlyByteBuf buf, PacketSender responseSender) {
        VehicleDataTool.vehicleData.clear();

        var message = decode(buf);
        for (var entry : message.data) {
            if (VehicleDataTool.vehicleData.containsKey(entry.id)) continue;
            VehicleDataTool.vehicleData.put(entry.id, entry);
        }
    }
}
