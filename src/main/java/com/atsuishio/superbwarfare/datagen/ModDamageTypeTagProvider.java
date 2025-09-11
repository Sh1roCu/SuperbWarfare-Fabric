package com.atsuishio.superbwarfare.datagen;

import com.atsuishio.superbwarfare.init.ModDamageTypes;
import com.atsuishio.superbwarfare.init.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;

import java.util.concurrent.CompletableFuture;

public class ModDamageTypeTagProvider extends FabricTagProvider<DamageType> {

    public ModDamageTypeTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.DAMAGE_TYPE, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.getOrCreateTagBuilder(ModTags.DamageTypes.PROJECTILE).add(ModDamageTypes.GUN_FIRE, ModDamageTypes.GUN_FIRE_HEADSHOT,
                        DamageTypes.ARROW, DamageTypes.TRIDENT, DamageTypes.THROWN)
                .addOptional(new ResourceLocation("tacz", "bullet"))
                .addOptional(new ResourceLocation("tacz", "bullet_void"))
                .addOptional(new ResourceLocation("virtuarealcraft", "rain_crystal"))
                .addOptional(new ResourceLocation("virtuarealcraft", "rain_shower_butterfly"))
                .addOptional(new ResourceLocation("virtuarealcraft", "sparkle_butterfly"))
                .addOptional(new ResourceLocation("dreamaticvoyage", "blood_crystal"))
                .addOptional(new ResourceLocation("dreamaticvoyage", "leviy_beam"));
        this.getOrCreateTagBuilder(ModTags.DamageTypes.PROJECTILE_ABSOLUTE).add(ModDamageTypes.GUN_FIRE_ABSOLUTE, ModDamageTypes.GUN_FIRE_HEADSHOT_ABSOLUTE)
                .addOptional(new ResourceLocation("tacz", "bullet_ignore_armor"))
                .addOptional(new ResourceLocation("tacz", "bullet_void_ignore_armor"))
                .addOptional(new ResourceLocation("dreamaticvoyage", "leviy_beam_absolute"));
        this.getOrCreateTagBuilder(ModTags.DamageTypes.VEHICLE_IGNORE)
                .addOptional(new ResourceLocation("sona", "injury"));
        this.getOrCreateTagBuilder(ModTags.DamageTypes.VEHICLE_NOT_ABSORB)
                .add(DamageTypes.EXPLOSION, DamageTypes.PLAYER_EXPLOSION, ModDamageTypes.CUSTOM_EXPLOSION, ModDamageTypes.MINE, ModDamageTypes.PROJECTILE_EXPLOSION);
        this.getOrCreateTagBuilder(ModTags.DamageTypes.GUN_DAMAGE).add(
                ModDamageTypes.GUN_FIRE,
                ModDamageTypes.GUN_FIRE_HEADSHOT,
                ModDamageTypes.GUN_FIRE_ABSOLUTE,
                ModDamageTypes.GUN_FIRE_HEADSHOT_ABSOLUTE,
                ModDamageTypes.LASER,
                ModDamageTypes.LASER_HEADSHOT,
                ModDamageTypes.SHOCK,
                ModDamageTypes.BURN,
                ModDamageTypes.PROJECTILE_HIT,
                ModDamageTypes.PROJECTILE_EXPLOSION
        );

        this.getOrCreateTagBuilder(DamageTypeTags.ALWAYS_HURTS_ENDER_DRAGONS).add(ModDamageTypes.PROJECTILE_EXPLOSION, ModDamageTypes.CUSTOM_EXPLOSION,
                ModDamageTypes.PROJECTILE_HIT, ModDamageTypes.GRAPESHOT_HIT, ModDamageTypes.LASER, ModDamageTypes.LASER_HEADSHOT, ModDamageTypes.LASER_STATIC);
        this.getOrCreateTagBuilder(DamageTypeTags.BYPASSES_ARMOR).add(ModDamageTypes.GUN_FIRE_ABSOLUTE, ModDamageTypes.GUN_FIRE_HEADSHOT_ABSOLUTE,
                ModDamageTypes.SHOCK, ModDamageTypes.PROJECTILE_HIT, ModDamageTypes.GRAPESHOT_HIT, ModDamageTypes.LASER, ModDamageTypes.LASER_HEADSHOT, ModDamageTypes.LASER_STATIC,
                ModDamageTypes.VEHICLE_STRIKE, ModDamageTypes.VEHICLE_EXPLOSION, ModDamageTypes.AIR_CRASH);
        this.getOrCreateTagBuilder(DamageTypeTags.BYPASSES_EFFECTS).add(ModDamageTypes.SHOCK);
        this.getOrCreateTagBuilder(DamageTypeTags.BYPASSES_ENCHANTMENTS).add(ModDamageTypes.GUN_FIRE_ABSOLUTE, ModDamageTypes.GUN_FIRE_HEADSHOT_ABSOLUTE,
                ModDamageTypes.SHOCK, ModDamageTypes.PROJECTILE_HIT, ModDamageTypes.GRAPESHOT_HIT, ModDamageTypes.LASER, ModDamageTypes.LASER_HEADSHOT, ModDamageTypes.LASER_STATIC,
                ModDamageTypes.VEHICLE_STRIKE, ModDamageTypes.VEHICLE_EXPLOSION, ModDamageTypes.AIR_CRASH);
        this.getOrCreateTagBuilder(DamageTypeTags.IS_EXPLOSION).add(ModDamageTypes.PROJECTILE_EXPLOSION, ModDamageTypes.CUSTOM_EXPLOSION, ModDamageTypes.LUNGE_MINE);
        this.getOrCreateTagBuilder(DamageTypeTags.IS_FIRE).add(ModDamageTypes.BURN);

        this.getOrCreateTagBuilder(otherModTag("cataclysm", "bypasses_hurt_time")).add(
                ModDamageTypes.GUN_FIRE_ABSOLUTE,
                ModDamageTypes.GUN_FIRE_HEADSHOT_ABSOLUTE,
                ModDamageTypes.AIR_CRASH,
                ModDamageTypes.BURN,
                ModDamageTypes.PROJECTILE_HIT,
                ModDamageTypes.GRAPESHOT_HIT,
                ModDamageTypes.CUSTOM_EXPLOSION,
                ModDamageTypes.DRONE_HIT,
                ModDamageTypes.LASER,
                ModDamageTypes.LASER_HEADSHOT,
                ModDamageTypes.LASER_STATIC,
                ModDamageTypes.LUNGE_MINE,
                ModDamageTypes.MINE,
                ModDamageTypes.PROJECTILE_EXPLOSION,
                ModDamageTypes.SHOCK,
                ModDamageTypes.VEHICLE_EXPLOSION,
                ModDamageTypes.VEHICLE_STRIKE
        );
    }

    public static TagKey<DamageType> otherModTag(String modId, String name) {
        return TagKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(modId, name));
    }
}
