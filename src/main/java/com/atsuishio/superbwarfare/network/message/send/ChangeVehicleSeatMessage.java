package com.atsuishio.superbwarfare.network.message.send;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.entity.vehicle.base.VehicleEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;

public class ChangeVehicleSeatMessage {

    public static final ResourceLocation ID = Mod.loc("change_vehicle_seat");

    private final int index;

    public ChangeVehicleSeatMessage(int index) {
        this.index = index;
    }

    @Environment(EnvType.CLIENT)
    public void send() {
        FriendlyByteBuf byteBuf = PacketByteBufs.create();
        byteBuf.writeInt(this.index);
        ClientPlayNetworking.send(ID, byteBuf);
    }

    public static ChangeVehicleSeatMessage decode(FriendlyByteBuf byteBuf) {
        return new ChangeVehicleSeatMessage(byteBuf.readInt());
    }

    public static void handle(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
        var message = decode(buf);
        server.execute(() -> {
            if (player == null || !(player.getVehicle() instanceof VehicleEntity vehicle)) {
                return;
            }

            vehicle.changeSeat(player, message.index);
        });
    }
}
