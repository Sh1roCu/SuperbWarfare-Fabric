package com.atsuishio.superbwarfare.capability;

import com.atsuishio.superbwarfare.capability.player.PlayerVariable;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import org.jetbrains.annotations.NotNull;

public class ModCapabilities implements EntityComponentInitializer {

    public static final ComponentKey<LaserCapability.ILaserCapability> LASER_CAPABILITY = ComponentRegistry.getOrCreate(LaserCapability.ID, LaserCapability.ILaserCapability.class);
    public static final ComponentKey<PlayerVariable> PLAYER_VARIABLE = ComponentRegistry.getOrCreate(PlayerVariable.ID, PlayerVariable.class);

    @Override
    public void registerEntityComponentFactories(@NotNull EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(LASER_CAPABILITY, LaserCapability.LaserCapabilityImpl::new);
        registry.registerForPlayers(PLAYER_VARIABLE, PlayerVariable::new);
    }
}
