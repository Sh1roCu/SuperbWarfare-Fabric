package com.atsuishio.superbwarfare.network.message.send;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.entity.vehicle.base.ControllableVehicle;
import com.atsuishio.superbwarfare.entity.vehicle.base.MobileVehicleEntity;
import com.atsuishio.superbwarfare.entity.vehicle.base.VehicleEntity;
import com.atsuishio.superbwarfare.init.ModItems;
import com.atsuishio.superbwarfare.tools.EntityFindUtil;
import com.atsuishio.superbwarfare.tools.ItemNBTTool;
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

public record VehicleMovementMessage(short keys) {

    public static final ResourceLocation ID = Mod.loc("vehicle_movement");

    public static VehicleMovementMessage decode(FriendlyByteBuf buffer) {
        return new VehicleMovementMessage(buffer.readShort());
    }

    @Environment(EnvType.CLIENT)
    public void send() {
        FriendlyByteBuf buffer = PacketByteBufs.create();
        buffer.writeShort(this.keys);
        ClientPlayNetworking.send(ID, buffer);
    }

    public static void handle(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
        var message = decode(buf);
        server.execute(() -> {
            if (player != null) {
                var entity = player.getVehicle();
                ItemStack stack = player.getMainHandItem();

                VehicleEntity vehicle = null;
                if (entity instanceof MobileVehicleEntity mobileVehicleEntity && mobileVehicleEntity.getFirstPassenger() == player) {
                    vehicle = mobileVehicleEntity;
                } else if (stack.is(ModItems.MONITOR)
                        && ItemNBTTool.getBoolean(stack, "Using", false)
                        && ItemNBTTool.getBoolean(stack, "Linked", false)
                ) vehicle = EntityFindUtil.findDrone(player.level(), stack.getOrCreateTag().getString("LinkedDrone"));

                if (!(vehicle instanceof ControllableVehicle controllable)) return;
                controllable.processInput(message.keys);
            }
        });
    }
}
