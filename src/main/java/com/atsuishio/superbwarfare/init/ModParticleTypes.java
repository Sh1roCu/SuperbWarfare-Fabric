package com.atsuishio.superbwarfare.init;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.client.particle.BulletDecalOption;
import com.atsuishio.superbwarfare.client.particle.CustomCloudOption;
import com.atsuishio.superbwarfare.client.particle.CustomSmokeOption;
import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import org.jetbrains.annotations.NotNull;

public class ModParticleTypes {

    public static void init() {

    }

    public static final SimpleParticleType FIRE_STAR = register("fire_star", FabricParticleTypes.simple(false));
    public static final ParticleType<BulletDecalOption> BULLET_DECAL = register("bullet_decal",
            createOptions(BulletDecalOption.CODEC, BulletDecalOption.DESERIALIZER));
    public static final ParticleType<CustomSmokeOption> CUSTOM_SMOKE = register("custom_smoke",
            createOptions(CustomSmokeOption.CODEC, CustomSmokeOption.DESERIALIZER));

    public static final ParticleType<CustomCloudOption> CUSTOM_CLOUD = register("custom_cloud",
            createOptions(CustomCloudOption.CODEC, CustomCloudOption.DESERIALIZER));

    @SuppressWarnings("deprecation")
    public static <T extends ParticleOptions> ParticleType<T> createOptions(Codec<T> codec, ParticleOptions.Deserializer<T> deserializer) {
        return new ParticleType<>(false, deserializer) {
            public @NotNull Codec<T> codec() {
                return codec;
            }
        };
    }

    private static <T extends ParticleOptions, S extends ParticleType<T>> S register(String name, S type) {
        return Registry.register(BuiltInRegistries.PARTICLE_TYPE, Mod.loc(name), type);
    }
}

