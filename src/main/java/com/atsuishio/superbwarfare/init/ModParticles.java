package com.atsuishio.superbwarfare.init;

import com.atsuishio.superbwarfare.client.particle.BulletDecalParticle;
import com.atsuishio.superbwarfare.client.particle.CustomCloudParticle;
import com.atsuishio.superbwarfare.client.particle.CustomSmokeParticle;
import com.atsuishio.superbwarfare.client.particle.FireStarParticle;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

@Environment(EnvType.CLIENT)
public class ModParticles {

    public static void registerParticles() {
        ParticleFactoryRegistry instance = ParticleFactoryRegistry.getInstance();
        instance.register(ModParticleTypes.FIRE_STAR, FireStarParticle::provider);
        instance.register(ModParticleTypes.BULLET_DECAL, new BulletDecalParticle.Provider());
        instance.register(ModParticleTypes.CUSTOM_CLOUD, CustomCloudParticle.Provider::new);
        instance.register(ModParticleTypes.CUSTOM_SMOKE, CustomSmokeParticle.Provider::new);
    }
}

