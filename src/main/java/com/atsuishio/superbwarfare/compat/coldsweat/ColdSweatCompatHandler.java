package com.atsuishio.superbwarfare.compat.coldsweat;

import com.atsuishio.superbwarfare.compat.CompatHolder;
import net.fabricmc.loader.api.FabricLoader;

public class ColdSweatCompatHandler {

    public static void onPlayerInVehicle(/*TickEvent.PlayerTickEvent event*/) {
/*        var player = event.player;
        if (player == null) return;
        if (player.getVehicle() instanceof VehicleEntity vehicle
                && vehicle.hasEnergyStorage()
                && vehicle.isEnclosed(vehicle.getSeatIndex(player))
                && vehicle.getEnergy() > 0
        ) {
            Temperature.set(player, Temperature.Trait.CORE, 1);
        }*/
    }

    public static boolean hasMod() {
        return FabricLoader.getInstance().isModLoaded(CompatHolder.COLD_SWEAT);
    }
}
