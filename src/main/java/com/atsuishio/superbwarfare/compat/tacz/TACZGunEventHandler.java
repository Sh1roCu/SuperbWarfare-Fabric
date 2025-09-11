package com.atsuishio.superbwarfare.compat.tacz;

import com.atsuishio.superbwarfare.entity.vehicle.base.VehicleEntity;
import com.tacz.guns.api.TimelessAPI;
import com.tacz.guns.api.event.common.EntityHurtByGunEvent;
import com.tacz.guns.api.item.IGun;
import com.tacz.guns.client.resource.GunDisplayInstance;
import com.tacz.guns.client.resource.index.ClientGunIndex;
import com.tacz.guns.resource.pojo.data.gun.GunData;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Optional;

public class TACZGunEventHandler {

    private static final List<String> VERSIONS = List.of("1.1.4", "1.1.5", "1.1.6");

    public static void entityHurtByTACZGun(EntityHurtByGunEvent.Pre event) {
        if (event.getHurtEntity() instanceof VehicleEntity) {
            event.setHeadshot(false);
        }
    }

    public static boolean hasMod() {
        return FabricLoader.getInstance().isModLoaded("tacz");
    }

    public static boolean compatCondition() {
        Optional<ModContainer> tacz = FabricLoader.getInstance().getModContainer("tacz");
        if (hasMod() && tacz.isPresent()) {
            boolean[] flag = {false};
            VERSIONS.forEach(version -> {
                if (tacz.get().getMetadata().getVersion().getFriendlyString().contains("forge" + version)) {
                    flag[0] = true;
                }
            });
            return flag[0];
        }
        return false;
    }

    public static ResourceLocation getTaczCompatIcon(ItemStack stack) {
        if (stack.getItem() instanceof IGun iGun) {
            ResourceLocation gunId = iGun.getGunId(stack);
            GunData gunData = TimelessAPI.getClientGunIndex(gunId).map(ClientGunIndex::getGunData).orElse(null);
            GunDisplayInstance display = TimelessAPI.getGunDisplay(stack).orElse(null);
            if (gunData != null && display != null) {
                return display.getHUDTexture();
            }
        }
        return null;
    }
}
