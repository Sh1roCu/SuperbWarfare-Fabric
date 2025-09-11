package com.atsuishio.superbwarfare.init;

import com.atsuishio.superbwarfare.client.screens.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.MenuScreens;

@Environment(EnvType.CLIENT)
public class ModScreens {

    public static void registerScreens() {
        MenuScreens.register(ModMenuTypes.REFORGING_TABLE_MENU, ReforgingTableScreen::new);
        MenuScreens.register(ModMenuTypes.CHARGING_STATION_MENU, ChargingStationScreen::new);
        MenuScreens.register(ModMenuTypes.VEHICLE_MENU, VehicleScreen::new);
        MenuScreens.register(ModMenuTypes.SUPERB_ITEM_INTERFACE_MENU, SuperbItemInterfaceScreen::new);
        MenuScreens.register(ModMenuTypes.FUMO_25_MENU, FuMO25Screen::new);
        MenuScreens.register(ModMenuTypes.VEHICLE_ASSEMBLING_MENU, VehicleAssemblingScreen::new);
    }
}
