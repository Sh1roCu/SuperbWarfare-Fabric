package com.atsuishio.superbwarfare.compat;

import com.atsuishio.superbwarfare.compat.clothconfig.ClothConfigHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;

public class CompatHolder {

    public static final String DMV = "dreamaticvoyage";
    public static final String VRC = "virtuarealcraft";
    public static final String CLOTH_CONFIG = "cloth-config";
    public static final String COLD_SWEAT = "cold_sweat";

    public static final MobEffect DMV_BLEEDING = BuiltInRegistries.MOB_EFFECT.get(new ResourceLocation(DMV + ":bleeding"));

    public static final MobEffect VRC_CURSE_FLAME = BuiltInRegistries.MOB_EFFECT.get(new ResourceLocation(VRC + ":curse_flame"));

    public static final EntityType<?> VRC_RAIN_SHOWER_BUTTERFLY = BuiltInRegistries.ENTITY_TYPE.get(new ResourceLocation(VRC + ":rain_shower_butterfly"));

    public static void onInterModEnqueue() {
//        hasMod(CLOTH_CONFIG, () -> {
//            if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT)
//                ClothConfigHelper.registerScreen();
//        });
    }

    public static void hasMod(String modid, Runnable runnable) {
        if (FabricLoader.getInstance().isModLoaded(modid)) {
            runnable.run();
        }
    }
}
