package com.atsuishio.superbwarfare.init;

import com.atsuishio.superbwarfare.Mod;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nullable;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class ModDamageTypes {

    // Gun Damage Types
    public static final ResourceKey<DamageType> GUN_FIRE = ResourceKey.create(Registries.DAMAGE_TYPE, Mod.loc("gunfire"));
    public static final ResourceKey<DamageType> GUN_FIRE_ABSOLUTE = ResourceKey.create(Registries.DAMAGE_TYPE, Mod.loc("gunfire_absolute"));
    public static final ResourceKey<DamageType> GUN_FIRE_HEADSHOT = ResourceKey.create(Registries.DAMAGE_TYPE, Mod.loc("gunfire_headshot"));
    public static final ResourceKey<DamageType> GUN_FIRE_HEADSHOT_ABSOLUTE = ResourceKey.create(Registries.DAMAGE_TYPE, Mod.loc("gunfire_headshot_absolute"));
    public static final ResourceKey<DamageType> LASER = ResourceKey.create(Registries.DAMAGE_TYPE, Mod.loc("laser"));
    public static final ResourceKey<DamageType> LASER_HEADSHOT = ResourceKey.create(Registries.DAMAGE_TYPE, Mod.loc("laser_headshot"));
    public static final ResourceKey<DamageType> BURN = ResourceKey.create(Registries.DAMAGE_TYPE, Mod.loc("burn"));
    public static final ResourceKey<DamageType> SHOCK = ResourceKey.create(Registries.DAMAGE_TYPE, Mod.loc("shock"));
    public static final ResourceKey<DamageType> PROJECTILE_HIT = ResourceKey.create(Registries.DAMAGE_TYPE, Mod.loc("projectile_hit"));
    public static final ResourceKey<DamageType> PROJECTILE_EXPLOSION = ResourceKey.create(Registries.DAMAGE_TYPE, Mod.loc("projectile_explosion"));

    // Other Damage Types
    public static final ResourceKey<DamageType> MINE = ResourceKey.create(Registries.DAMAGE_TYPE, Mod.loc("mine"));
    public static final ResourceKey<DamageType> BEAST = ResourceKey.create(Registries.DAMAGE_TYPE, Mod.loc("beast"));
    public static final ResourceKey<DamageType> CUSTOM_EXPLOSION = ResourceKey.create(Registries.DAMAGE_TYPE, Mod.loc("custom_explosion"));
    public static final ResourceKey<DamageType> DRONE_HIT = ResourceKey.create(Registries.DAMAGE_TYPE, Mod.loc("drone_hit"));
    public static final ResourceKey<DamageType> LASER_STATIC = ResourceKey.create(Registries.DAMAGE_TYPE, Mod.loc("laser_static"));
    public static final ResourceKey<DamageType> VEHICLE_STRIKE = ResourceKey.create(Registries.DAMAGE_TYPE, Mod.loc("vehicle_strike"));
    public static final ResourceKey<DamageType> AIR_CRASH = ResourceKey.create(Registries.DAMAGE_TYPE, Mod.loc("air_crash"));
    public static final ResourceKey<DamageType> LUNGE_MINE = ResourceKey.create(Registries.DAMAGE_TYPE, Mod.loc("lunge_mine"));
    public static final ResourceKey<DamageType> VEHICLE_EXPLOSION = ResourceKey.create(Registries.DAMAGE_TYPE, Mod.loc("vehicle_explosion"));
    public static final ResourceKey<DamageType> GRAPESHOT_HIT = ResourceKey.create(Registries.DAMAGE_TYPE, Mod.loc("grapeshot_hit"));


    public static void bootstrap(BootstapContext<DamageType> ctx) {
        ctx.register(GUN_FIRE, new DamageType("gunfire", DamageScaling.NEVER, 0));
        ctx.register(GUN_FIRE_ABSOLUTE, new DamageType("gunfire_absolute", DamageScaling.NEVER, 0));
        ctx.register(GUN_FIRE_HEADSHOT, new DamageType("gunfire_headshot", DamageScaling.NEVER, 0));
        ctx.register(GUN_FIRE_HEADSHOT_ABSOLUTE, new DamageType("gunfire_headshot_absolute", DamageScaling.NEVER, 0));
        ctx.register(LASER, new DamageType("laser", DamageScaling.NEVER, 0));
        ctx.register(LASER_HEADSHOT, new DamageType("laser_headshot", DamageScaling.NEVER, 0));
        ctx.register(BURN, new DamageType("burn", DamageScaling.NEVER, 0));
        ctx.register(SHOCK, new DamageType("shock", DamageScaling.NEVER, 0));
        ctx.register(PROJECTILE_HIT, new DamageType("projectile_hit", DamageScaling.NEVER, 0));
        ctx.register(PROJECTILE_EXPLOSION, new DamageType("projectile_explosion", DamageScaling.NEVER, 0.1f));

        ctx.register(MINE, new DamageType("mine", DamageScaling.NEVER, 0));
        ctx.register(BEAST, new DamageType("beast", DamageScaling.NEVER, 0));
        ctx.register(CUSTOM_EXPLOSION, new DamageType("custom_explosion", DamageScaling.NEVER, 0.1f));
        ctx.register(DRONE_HIT, new DamageType("drone_hit", DamageScaling.NEVER, 0));
        ctx.register(LASER_STATIC, new DamageType("laser", DamageScaling.NEVER, 0));
        ctx.register(VEHICLE_STRIKE, new DamageType("vehicle_strike", DamageScaling.NEVER, 0));
        ctx.register(AIR_CRASH, new DamageType("air_crash", DamageScaling.NEVER, 0));
        ctx.register(LUNGE_MINE, new DamageType("lunge_mine", DamageScaling.NEVER, 0));
        ctx.register(VEHICLE_EXPLOSION, new DamageType("vehicle_explosion", DamageScaling.NEVER, 0));
        ctx.register(GRAPESHOT_HIT, new DamageType("grapeshot_hit", DamageScaling.NEVER, 0));
    }

    public static DamageSource causeGunFireDamage(RegistryAccess registryAccess, @Nullable Entity directEntity, @Nullable Entity attacker) {
        return new DamageMessages(registryAccess.registry(Registries.DAMAGE_TYPE).get().getHolderOrThrow(GUN_FIRE), directEntity, attacker);
    }

    public static DamageSource causeGunFireHeadshotDamage(RegistryAccess registryAccess, @Nullable Entity directEntity, @Nullable Entity attacker) {
        return new DamageMessages(registryAccess.registry(Registries.DAMAGE_TYPE).get().getHolderOrThrow(GUN_FIRE_HEADSHOT), directEntity, attacker);
    }

    public static DamageSource causeMineDamage(RegistryAccess registryAccess, @Nullable Entity entity) {
        return new DamageMessages(registryAccess.registry(Registries.DAMAGE_TYPE).get().getHolderOrThrow(MINE), entity);
    }

    public static DamageSource causeShockDamage(RegistryAccess registryAccess, @Nullable Entity attacker) {
        return new DamageMessages(registryAccess.registry(Registries.DAMAGE_TYPE).get().getHolderOrThrow(SHOCK), attacker);
    }

    public static DamageSource causeBurnDamage(RegistryAccess registryAccess, @Nullable Entity attacker) {
        return new DamageMessages(registryAccess.registry(Registries.DAMAGE_TYPE).get().getHolderOrThrow(BURN), attacker);
    }

    /**
     * 仅用于枪械可以发射的投射物造成的爆炸伤害
     */
    public static DamageSource causeProjectileExplosionDamage(RegistryAccess registryAccess, @Nullable Entity directEntity, @Nullable Entity attacker) {
        return new DamageMessages(registryAccess.registry(Registries.DAMAGE_TYPE).get().getHolderOrThrow(PROJECTILE_EXPLOSION), directEntity, attacker);
    }

    /**
     * 仅用于枪械可以发射的投射物造成的直击伤害
     */
    public static DamageSource causeProjectileHitDamage(RegistryAccess registryAccess, @Nullable Entity directEntity, @Nullable Entity attacker) {
        return new DamageMessages(registryAccess.registry(Registries.DAMAGE_TYPE).get().getHolderOrThrow(PROJECTILE_HIT), directEntity, attacker);
    }

    public static DamageSource causeGunFireAbsoluteDamage(RegistryAccess registryAccess, @Nullable Entity directEntity, @Nullable Entity attacker) {
        return new DamageMessages(registryAccess.registry(Registries.DAMAGE_TYPE).get().getHolderOrThrow(GUN_FIRE_ABSOLUTE), directEntity, attacker);
    }

    public static DamageSource causeGunFireHeadshotAbsoluteDamage(RegistryAccess registryAccess, @Nullable Entity directEntity, @Nullable Entity attacker) {
        return new DamageMessages(registryAccess.registry(Registries.DAMAGE_TYPE).get().getHolderOrThrow(GUN_FIRE_HEADSHOT_ABSOLUTE), directEntity, attacker);
    }

    public static DamageSource causeGrapeShotHitDamage(RegistryAccess registryAccess, @Nullable Entity directEntity, @Nullable Entity attacker) {
        return new DamageMessages(registryAccess.registry(Registries.DAMAGE_TYPE).get().getHolderOrThrow(GRAPESHOT_HIT), directEntity, attacker);
    }

    /**
     * 用于其他实体造成的爆炸伤害
     */
    public static DamageSource causeCustomExplosionDamage(RegistryAccess registryAccess, @Nullable Entity directEntity, @Nullable Entity attacker) {
        return new DamageMessages(registryAccess.registry(Registries.DAMAGE_TYPE).get().getHolderOrThrow(CUSTOM_EXPLOSION), directEntity, attacker);
    }

    public static DamageSource causeDroneHitDamage(RegistryAccess registryAccess, @Nullable Entity directEntity, @Nullable Entity attacker) {
        return new DamageMessages(registryAccess.registry(Registries.DAMAGE_TYPE).get().getHolderOrThrow(DRONE_HIT), directEntity, attacker);
    }

    public static DamageSource causeLaserDamage(RegistryAccess registryAccess, @Nullable Entity directEntity, @Nullable Entity attacker) {
        return new DamageMessages(registryAccess.registry(Registries.DAMAGE_TYPE).get().getHolderOrThrow(LASER), directEntity, attacker);
    }

    public static DamageSource causeLaserStaticDamage(RegistryAccess registryAccess, @Nullable Entity directEntity, @Nullable Entity attacker) {
        return new DamageMessages(registryAccess.registry(Registries.DAMAGE_TYPE).get().getHolderOrThrow(LASER_STATIC), directEntity, attacker);
    }

    public static DamageSource causeLaserHeadshotDamage(RegistryAccess registryAccess, @Nullable Entity directEntity, @Nullable Entity attacker) {
        return new DamageMessages(registryAccess.registry(Registries.DAMAGE_TYPE).get().getHolderOrThrow(LASER_HEADSHOT), directEntity, attacker);
    }

    public static DamageSource causeVehicleStrikeDamage(RegistryAccess registryAccess, @Nullable Entity directEntity, @Nullable Entity attacker) {
        return new DamageMessages(registryAccess.registry(Registries.DAMAGE_TYPE).get().getHolderOrThrow(VEHICLE_STRIKE), directEntity, attacker);
    }

    public static DamageSource causeAirCrashDamage(RegistryAccess registryAccess, @Nullable Entity directEntity, @Nullable Entity attacker) {
        return new DamageMessages(registryAccess.registry(Registries.DAMAGE_TYPE).get().getHolderOrThrow(AIR_CRASH), directEntity, attacker);
    }

    public static DamageSource causeLungeMineDamage(RegistryAccess registryAccess, @Nullable Entity directEntity, @Nullable Entity attacker) {
        return new DamageMessages(registryAccess.registry(Registries.DAMAGE_TYPE).get().getHolderOrThrow(LUNGE_MINE), directEntity, attacker);
    }

    public static DamageSource causeVehicleExplosionDamage(RegistryAccess registryAccess, @Nullable Entity directEntity, @Nullable Entity attacker) {
        return new DamageMessages(registryAccess.registry(Registries.DAMAGE_TYPE).get().getHolderOrThrow(VEHICLE_EXPLOSION), directEntity, attacker);
    }

    public static DamageSource causeBeastDamage(RegistryAccess registryAccess, @Nullable Entity directEntity, @Nullable Entity attacker) {
        return new DamageMessages(registryAccess.registry(Registries.DAMAGE_TYPE).get().getHolderOrThrow(BEAST), directEntity, attacker);
    }

    private static class DamageMessages extends DamageSource {

        public DamageMessages(Holder.Reference<DamageType> typeReference) {
            super(typeReference);
        }

        public DamageMessages(Holder.Reference<DamageType> typeReference, Entity entity) {
            super(typeReference, entity);
        }

        public DamageMessages(Holder.Reference<DamageType> typeReference, Entity directEntity, Entity attacker) {
            super(typeReference, directEntity, attacker);
        }

        @Override
        public Component getLocalizedDeathMessage(LivingEntity pLivingEntity) {
            Entity entity = this.getEntity() == null ? this.getDirectEntity() : this.getEntity();
            if (entity == null) {
                return Component.translatable("death.attack." + this.getMsgId(), pLivingEntity.getDisplayName());
            } else if (entity instanceof LivingEntity living && living.getMainHandItem().hasCustomHoverName()) {
                return Component.translatable("death.attack." + this.getMsgId() + ".item", pLivingEntity.getDisplayName(), entity.getDisplayName(), living.getMainHandItem().getDisplayName());
            } else {
                return Component.translatable("death.attack." + this.getMsgId() + ".entity", pLivingEntity.getDisplayName(), entity.getDisplayName());
            }
        }
    }
}
