package com.atsuishio.superbwarfare.init;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.tools.ItemNBTTool;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.item.ItemProperties;

@Environment(EnvType.CLIENT)
public class ModProperties {
    public static void propertyOverrideRegistry() {
        ItemProperties.register(ModItems.MONITOR, Mod.loc("monitor_linked"),
                (itemStack, clientWorld, livingEntity, seed) -> ItemNBTTool.getBoolean(itemStack, "Linked", false) ? 1.0F : 0.0F);
        ItemProperties.register(ModItems.ARMOR_PLATE, Mod.loc("armor_plate_infinite"),
                (itemStack, clientWorld, livingEntity, seed) -> ItemNBTTool.getBoolean(itemStack, "Infinite", false) ? 1.0F : 0.0F);
    }
}