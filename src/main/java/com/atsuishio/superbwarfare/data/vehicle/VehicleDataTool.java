package com.atsuishio.superbwarfare.data.vehicle;

import cn.sh1rocu.superbwarfare.api.event.PlayerEvent;
import com.atsuishio.superbwarfare.data.CustomData;
import com.atsuishio.superbwarfare.network.message.receive.VehiclesDataMessage;
import net.minecraft.server.level.ServerPlayer;

import java.util.HashMap;

public class VehicleDataTool {

    public static HashMap<String, DefaultVehicleData> vehicleData = CustomData.VEHICLE;

    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            var server = player.getServer();
            if (server != null && server.isSingleplayerOwner(player.getGameProfile())) {
                return;
            }

            VehiclesDataMessage.create().send(player);
        }
    }

    public static void onDataPackSync(ServerPlayer player, boolean joined) {
        var server = player.getServer();

        var message = VehiclesDataMessage.create();
        if (server == null || server.isSingleplayerOwner(player.getGameProfile()))
            return;
        message.send(player);
    }
}