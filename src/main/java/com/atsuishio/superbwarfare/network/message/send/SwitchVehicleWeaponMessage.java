package com.atsuishio.superbwarfare.network.message.send;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.entity.vehicle.base.VehicleEntity;
import com.atsuishio.superbwarfare.entity.vehicle.base.WeaponVehicleEntity;
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
import net.minecraft.util.Mth;

public class SwitchVehicleWeaponMessage {

    public static final ResourceLocation ID = Mod.loc("switch_vehicle_weapon");

    private final int index;
    private final double value;
    private final boolean isScroll;

    public SwitchVehicleWeaponMessage(int index, double value, boolean isScroll) {
        this.index = index;
        this.value = value;
        this.isScroll = isScroll;
    }

    @Environment(EnvType.CLIENT)
    public void send() {
        FriendlyByteBuf byteBuf = PacketByteBufs.create();
        byteBuf.writeInt(this.index);
        byteBuf.writeDouble(this.value);
        byteBuf.writeBoolean(this.isScroll);
        ClientPlayNetworking.send(ID, byteBuf);
    }

    public static SwitchVehicleWeaponMessage decode(FriendlyByteBuf byteBuf) {
        return new SwitchVehicleWeaponMessage(byteBuf.readInt(), byteBuf.readDouble(), byteBuf.readBoolean());
    }

    public static void handle(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
        var message = decode(buf);
        server.execute(() -> {
            if (player == null) {
                return;
            }

            if (player.getVehicle() instanceof VehicleEntity vehicle && vehicle instanceof WeaponVehicleEntity weaponVehicle && weaponVehicle.hasWeapon(vehicle.getSeatIndex(player))) {
                var value = message.isScroll ? (Mth.clamp(message.value > 0 ? Mth.ceil(message.value) : Mth.floor(message.value), -1, 1)) : message.value;
                weaponVehicle.changeWeapon(message.index, (int) value, message.isScroll);
            }
        });
    }
}
