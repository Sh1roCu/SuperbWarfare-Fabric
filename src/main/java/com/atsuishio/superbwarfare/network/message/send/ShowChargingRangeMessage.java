package com.atsuishio.superbwarfare.network.message.send;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.menu.ChargingStationMenu;
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

public class ShowChargingRangeMessage {

    public static final ResourceLocation ID = Mod.loc("show_charging_range");

    private final boolean operation;

    public ShowChargingRangeMessage(boolean operation) {
        this.operation = operation;
    }

    public static ShowChargingRangeMessage decode(FriendlyByteBuf buffer) {
        return new ShowChargingRangeMessage(buffer.readBoolean());
    }

    @Environment(EnvType.CLIENT)
    public void send() {
        FriendlyByteBuf buffer = PacketByteBufs.create();
        buffer.writeBoolean(this.operation);
        ClientPlayNetworking.send(ID, buffer);
    }

    public static void handle(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
        var message = decode(buf);
        server.execute(() -> {
            if (player == null) return;

            var menu = player.containerMenu;
            if (menu instanceof ChargingStationMenu chargingStationMenu) {
                if (!chargingStationMenu.stillValid(player)) return;

                chargingStationMenu.setShowRange(message.operation);
            }
        });
    }
}
