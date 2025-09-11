package com.atsuishio.superbwarfare.network.message.send;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.entity.vehicle.DroneEntity;
import com.atsuishio.superbwarfare.entity.vehicle.base.VehicleEntity;
import com.atsuishio.superbwarfare.init.ModItems;
import com.atsuishio.superbwarfare.tools.EntityFindUtil;
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
import net.minecraft.world.item.ItemStack;

public class MouseMoveMessage {

    public static final ResourceLocation ID = Mod.loc("mouse_move");

    private final double speedX;
    private final double speedY;

    public MouseMoveMessage(double speedX, double speedY) {
        this.speedX = speedX;
        this.speedY = speedY;
    }

    @Environment(EnvType.CLIENT)
    public void send() {
        FriendlyByteBuf byteBuf = PacketByteBufs.create();
        byteBuf.writeDouble(this.speedX);
        byteBuf.writeDouble(this.speedY);
        ClientPlayNetworking.send(ID, byteBuf);
    }

    public static MouseMoveMessage decode(FriendlyByteBuf byteBuf) {
        return new MouseMoveMessage(byteBuf.readDouble(), byteBuf.readDouble());
    }

    public static void handle(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
        var message = decode(buf);
        server.execute(() -> {
            if (player == null) {
                return;
            }

            var entity = player.getVehicle();

            if (entity instanceof VehicleEntity vehicle) {
                vehicle.mouseInput(message.speedX, message.speedY);
            }

            ItemStack stack = player.getMainHandItem();

            if (stack.is(ModItems.MONITOR) && stack.getOrCreateTag().getBoolean("Using") && stack.getOrCreateTag().getBoolean("Linked")) {
                DroneEntity drone = EntityFindUtil.findDrone(player.level(), stack.getOrCreateTag().getString("LinkedDrone"));
                if (drone != null) {
                    drone.mouseInput(message.speedX, message.speedY);
                }
            }
        });
    }
}
