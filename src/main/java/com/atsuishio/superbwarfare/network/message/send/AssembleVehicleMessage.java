package com.atsuishio.superbwarfare.network.message.send;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.menu.VehicleAssemblingMenu;
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

public class AssembleVehicleMessage {

    public static final ResourceLocation ID = Mod.loc("assemble_vehicle");

    private final ResourceLocation id;
    private final int containerId;

    public AssembleVehicleMessage(ResourceLocation id, int containerId) {
        this.id = id;
        this.containerId = containerId;
    }

    @Environment(EnvType.CLIENT)
    public void send() {
        FriendlyByteBuf byteBuf = PacketByteBufs.create();
        byteBuf.writeResourceLocation(this.id);
        byteBuf.writeVarInt(this.containerId);
        ClientPlayNetworking.send(ID, byteBuf);
    }

    public static AssembleVehicleMessage decode(FriendlyByteBuf byteBuf) {
        return new AssembleVehicleMessage(byteBuf.readResourceLocation(), byteBuf.readVarInt());
    }

    public static void handle(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
        var message = decode(buf);
        server.execute(() -> {
            if (player == null) return;
            if (player.containerMenu.containerId != message.containerId) return;
            if (player.containerMenu instanceof VehicleAssemblingMenu menu) {
                menu.assembleVehicle(message.id, player);
            }
        });
    }
}
