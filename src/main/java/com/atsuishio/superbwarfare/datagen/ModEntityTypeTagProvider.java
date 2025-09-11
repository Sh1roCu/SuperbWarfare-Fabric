package com.atsuishio.superbwarfare.datagen;

import com.atsuishio.superbwarfare.init.ModEntities;
import com.atsuishio.superbwarfare.init.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModEntityTypeTagProvider extends FabricTagProvider<EntityType<?>> {

    public ModEntityTypeTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.ENTITY_TYPE, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        this.getOrCreateTagBuilder(ModTags.EntityTypes.AERIAL_BOMB).add(
                ModEntities.MELON_BOMB,
                ModEntities.MK_82
        );

        this.getOrCreateTagBuilder(ModTags.EntityTypes.DESTROYABLE_PROJECTILE).add(
                ModEntities.AGM_65,
                ModEntities.JAVELIN_MISSILE,
                ModEntities.MELON_BOMB,
                ModEntities.MK_82,
                ModEntities.SWARM_DRONE,
                ModEntities.WG_MISSILE
        );

        this.getOrCreateTagBuilder(ModTags.EntityTypes.DECOY).add(
                ModEntities.SMOKE_DECOY,
                ModEntities.FLARE_DECOY
        );

        this.getOrCreateTagBuilder(ModTags.EntityTypes.NO_EXPERIENCE).add(ModEntities.TARGET, ModEntities.DPS_GENERATOR)
                .addOptional(new ResourceLocation("dummmmmmy", "target_dummy"))
                .addOptional(new ResourceLocation("powerful_dummy", "test_dummy"));
    }

    public static TagKey<EntityType<?>> forgeTag(String name) {
        return TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("forge", name));
    }
}
