package com.atsuishio.superbwarfare.init;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.menu.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

public class ModMenuTypes {

    public static void init() {

    }

    public static final MenuType<ReforgingTableMenu> REFORGING_TABLE_MENU = register("reforging_table_menu",
            new MenuType<>(ReforgingTableMenu::new, FeatureFlags.VANILLA_SET));
    public static final MenuType<ChargingStationMenu> CHARGING_STATION_MENU = register("charging_station_menu",
            new MenuType<>(ChargingStationMenu::new, FeatureFlags.VANILLA_SET));
    public static final MenuType<VehicleMenu> VEHICLE_MENU = register("vehicle_menu",
            new MenuType<>(VehicleMenu::new, FeatureFlags.VANILLA_SET));
    public static final MenuType<SuperbItemInterfaceMenu> SUPERB_ITEM_INTERFACE_MENU = register("superb_item_interface_menu",
            new MenuType<>(SuperbItemInterfaceMenu::new, FeatureFlags.VANILLA_SET));
    public static final MenuType<FuMO25Menu> FUMO_25_MENU = register("fumo_25_menu",
            new MenuType<>(FuMO25Menu::new, FeatureFlags.VANILLA_SET));
    public static final MenuType<VehicleAssemblingMenu> VEHICLE_ASSEMBLING_MENU = register("vehicle_assembling_menu",
            new MenuType<>(VehicleAssemblingMenu::new, FeatureFlags.VANILLA_SET));

    private static <T extends AbstractContainerMenu> MenuType<T> register(String name, MenuType<T> menu) {
        return Registry.register(BuiltInRegistries.MENU, Mod.loc(name), menu);
    }
}
