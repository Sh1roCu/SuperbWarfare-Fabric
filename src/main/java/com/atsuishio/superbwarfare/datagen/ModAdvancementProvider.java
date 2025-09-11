package com.atsuishio.superbwarfare.datagen;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.advancement.criteria.OttoSprintTrigger;
import com.atsuishio.superbwarfare.advancement.criteria.RPGMeleeExplosionTrigger;
import com.atsuishio.superbwarfare.init.ModItems;
import com.atsuishio.superbwarfare.init.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

/**
 * Codes Based on @Create
 */
public class ModAdvancementProvider extends FabricAdvancementProvider {

    public static final List<ModAdvancement> ADVANCEMENTS = new ArrayList<>();

    @SuppressWarnings("unused")
    public static ModAdvancement START = null,
    /**
     * Main
     */
    MAIN_ROOT = advancement("root", builder -> builder.icon(ModItems.TASER)
            .type(ModAdvancement.Type.SILENT)
            .awardedForFree()
            .rewardLootTable(Mod.loc("grant_manual"))),

    BEST_FRIEND = advancement("best_friend", builder -> builder.icon(ModItems.CLAYMORE_MINE)
            .whenIconCollected()
            .type(ModAdvancement.Type.SECRET)
            .parent(MAIN_ROOT)),

    BANZAI = advancement("banzai", builder -> builder.icon(ModItems.LUNGE_MINE)
            .whenIconCollected()
            .parent(MAIN_ROOT)),

    HAMMER = advancement("hammer", builder -> builder.icon(ModItems.HAMMER)
            .whenItemCollected(ModTags.Items.HAMMER)
            .parent(MAIN_ROOT)),

    PHYSICS_EXCALIBUR = advancement("physics_excalibur", builder -> builder.icon(ModItems.CROWBAR)
            .whenIconCollected()
            .parent(MAIN_ROOT)),

    CLEAN_ENERGY = advancement("clean_energy", builder -> builder.icon(ModItems.CHARGING_STATION)
            .whenIconCollected()
            .parent(PHYSICS_EXCALIBUR)),

    SUPER_CONTAINER = advancement("super_container", builder -> builder.icon(ModItems.CONTAINER)
            .whenIconCollected()
            .parent(CLEAN_ENERGY)),

    // 蓝图
    BLUEPRINT = advancement("blueprint", builder -> builder.icon(ModItems.TRACHELIUM_BLUEPRINT)
            .whenItemCollected(ModTags.Items.BLUEPRINT)
            .parent(MAIN_ROOT)),

    COMMON_BLUEPRINT = advancement("common_blueprint", builder -> builder.icon(ModItems.TRACHELIUM_BLUEPRINT)
            .whenItemCollected(ModTags.Items.COMMON_BLUEPRINT)
            .parent(BLUEPRINT)),

    RARE_BLUEPRINT = advancement("rare_blueprint", builder -> builder.icon(ModItems.TRACHELIUM_BLUEPRINT)
            .whenItemCollected(ModTags.Items.RARE_BLUEPRINT)
            .parent(COMMON_BLUEPRINT)),

    EPIC_BLUEPRINT = advancement("epic_blueprint", builder -> builder.icon(ModItems.TRACHELIUM_BLUEPRINT)
            .whenItemCollected(ModTags.Items.EPIC_BLUEPRINT)
            .parent(RARE_BLUEPRINT)),

    LEGENDARY_BLUEPRINT = advancement("legendary_blueprint", builder -> builder.icon(ModItems.TRACHELIUM_BLUEPRINT)
            .whenItemCollected(ModTags.Items.LEGENDARY_BLUEPRINT)
            .parent(EPIC_BLUEPRINT)),

    CANNON_BLUEPRINT = advancement("cannon_blueprint", builder -> builder.icon(ModItems.MK_42_BLUEPRINT)
            .whenItemCollected(ModTags.Items.CANNON_BLUEPRINT)
            .parent(BLUEPRINT)),

    // 古代芯片
    ANCIENT_TECHNOLOGY = advancement("ancient_technology", builder -> builder.icon(ModItems.ANCIENT_CPU)
            .whenIconCollected()
            .type(ModAdvancement.Type.GOAL)
            .parent(MAIN_ROOT)),

    ENCLAVE = advancement("enclave", builder -> builder.icon(ModItems.REFORGING_TABLE)
            .whenIconCollected()
            .type(ModAdvancement.Type.GOAL)
            .parent(ANCIENT_TECHNOLOGY)),

    HANDSOME_FRAME = advancement("handsome_frame", builder -> builder.icon(ModItems.INTELLIGENT_CHIP)
            .whenIconCollected()
            .type(ModAdvancement.Type.GOAL)
            .parent(ENCLAVE)),

    // 哑弹棒（？）
    BOOMSTICK_MELEE = advancement("boomstick_melee", builder -> builder.icon(ModItems.RPG_ROCKET_TBG)
            .externalTrigger(RPGMeleeExplosionTrigger.TriggerInstance.get())
            .type(ModAdvancement.Type.SECRET_CHALLENGE)
            .parent(MAIN_ROOT)),

    RUSH_RUSH_RUN = advancement("rush_rush_run", builder -> builder.icon(ModItems.ELECTRIC_BATON)
            .externalTrigger(OttoSprintTrigger.TriggerInstance.get())
            .type(ModAdvancement.Type.SECRET_CHALLENGE)
            .parent(MAIN_ROOT)),

    END = null;

    protected ModAdvancementProvider(FabricDataOutput output) {
        super(output);
    }


    private static ModAdvancement advancement(String id, UnaryOperator<ModAdvancement.Builder> b) {
        return new ModAdvancement(id, b);
    }

    @Override
    public void generateAdvancement(Consumer<Advancement> consumer) {
        for (ModAdvancement advancement : ADVANCEMENTS) {
            advancement.save(consumer);
        }
    }

    @Override
    public @NotNull String getName() {
        return "Superb Warfare Advancements";
    }
}
