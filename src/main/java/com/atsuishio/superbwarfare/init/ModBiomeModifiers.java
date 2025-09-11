package com.atsuishio.superbwarfare.init;

import com.atsuishio.superbwarfare.Mod;
import com.google.common.collect.Sets;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.levelgen.GenerationStep;

import java.util.Set;

public class ModBiomeModifiers {
    private static final Set<String> SENPAI_BIOMES = Sets.newHashSet(
            "badlands",
            "bamboo_jungle",
            "birch_forest",
            "cherry_grove",
            "dark_forest",
            "desert",
            "dripstone_caves",
            "eroded_badlands",
            "flower_forest",
            "forest",
            "frozen_peaks",
            "grove",
            "ice_spikes",
            "jagged_peaks",
            "jungle",
            "lush_caves",
            "mangrove_swamp",
            "meadow",
            "old_growth_birch_forest",
            "old_growth_pine_taiga",
            "old_growth_spruce_taiga",
            "plains",
            "savanna",
            "savanna_plateau",
            "snowy_slopes",
            "snowy_beach",
            "snowy_plains",
            "snowy_taiga",
            "stony_peaks",
            "stony_shore",
            "sunflower_plains",
            "swamp",
            "taiga",
            "windswept_forest",
            "windswept_gravelly_hills",
            "windswept_hills",
            "windswept_savanna",
            "wooded_badlands"
    );

    public static void init() {
        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.IS_OVERWORLD),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ResourceKey.create(Registries.PLACED_FEATURE, Mod.loc("deepslate_galena_ore"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.IS_OVERWORLD),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ResourceKey.create(Registries.PLACED_FEATURE, Mod.loc("deepslate_scheelite_ore"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.IS_OVERWORLD),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ResourceKey.create(Registries.PLACED_FEATURE, Mod.loc("deepslate_silver_ore"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.IS_OVERWORLD),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ResourceKey.create(Registries.PLACED_FEATURE, Mod.loc("galena_ore"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.IS_OVERWORLD),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ResourceKey.create(Registries.PLACED_FEATURE, Mod.loc("scheelite_ore"))
        );
        BiomeModifications.addSpawn(
                biomeSelector -> {
                    ResourceLocation loc = biomeSelector.getBiomeKey().location();
                    if (loc.getNamespace().equals("minecraft"))
                        return SENPAI_BIOMES.contains(loc.getPath());
                    return SENPAI_BIOMES.contains(loc.toString());
                },
                MobCategory.MONSTER,
                ModEntities.SENPAI,
                20, 4, 4
        );
        BiomeModifications.addFeature(
                biomeSelector -> BiomeSelectors.tag(BiomeTags.IS_OVERWORLD).test(biomeSelector),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ResourceKey.create(Registries.PLACED_FEATURE, Mod.loc("silver_ore"))
        );
    }
}
