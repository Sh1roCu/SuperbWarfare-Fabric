package com.atsuishio.superbwarfare.datagen;

import com.atsuishio.superbwarfare.init.ModItems;
import com.atsuishio.superbwarfare.init.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

import static com.atsuishio.superbwarfare.init.ModTags.commonItemTag;

public class ModItemTagProvider extends FabricTagProvider<Item> {

    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.ITEM, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        this.getOrCreateTagBuilder(ConventionalItemTags.DUSTS).forceAddTag(commonItemTag("dusts/coal_coke")).forceAddTag(commonItemTag("dusts/tungsten"));
        this.getOrCreateTagBuilder(commonItemTag("dusts/coal_coke")).add(ModItems.COAL_POWDER);
        this.getOrCreateTagBuilder(commonItemTag("dusts/iron")).add(ModItems.IRON_POWDER);
        this.getOrCreateTagBuilder(commonItemTag("dusts/tungsten")).add(ModItems.TUNGSTEN_POWDER);

        this.getOrCreateTagBuilder(ConventionalItemTags.INGOTS).forceAddTag(commonItemTag("ingots/lead")).forceAddTag(commonItemTag("ingots/steel"))
                .forceAddTag(commonItemTag("ingots/tungsten")).forceAddTag(commonItemTag("ingots/silver"));
        this.getOrCreateTagBuilder(commonItemTag("ingots/lead")).add(ModItems.LEAD_INGOT);
        this.getOrCreateTagBuilder(commonItemTag("ingots/steel")).add(ModItems.STEEL_INGOT);
        this.getOrCreateTagBuilder(commonItemTag("ingots/tungsten")).add(ModItems.TUNGSTEN_INGOT);
        this.getOrCreateTagBuilder(commonItemTag("ingots/silver")).add(ModItems.SILVER_INGOT);

        this.getOrCreateTagBuilder(ModTags.Items.INGOTS_STEEL).forceAddTag(commonItemTag("ingots/steel"))
                .addOptional(new ResourceLocation("dreamaticvoyage", "fukamizu_bread_ingot"));
        this.getOrCreateTagBuilder(ModTags.Items.INGOTS_CEMENTED_CARBIDE).add(ModItems.CEMENTED_CARBIDE_INGOT)
                .addOptional(new ResourceLocation("dreamaticvoyage", "hqss_bread_ingot"));

//        this.getOrCreateTagBuilder().addTags(commonItemTag("storage_blocks/lead"), commonItemTag("storage_blocks/steel"), commonItemTag("storage_blocks/tungsten"), commonItemTag("storage_blocks/silver"));
//        this.getOrCreateTagBuilder(commonItemTag("storage_blocks/lead")).add(ModItems.LEAD_BLOCK);
//        this.getOrCreateTagBuilder(commonItemTag("storage_blocks/steel")).add(ModItems.STEEL_BLOCK);
//        this.getOrCreateTagBuilder(commonItemTag("storage_blocks/tungsten")).add(ModItems.TUNGSTEN_BLOCK);
//        this.getOrCreateTagBuilder(commonItemTag("storage_blocks/silver")).add(ModItems.SILVER_BLOCK);

        this.getOrCreateTagBuilder(ModTags.Items.STORAGE_BLOCK_STEEL).forceAddTag(commonItemTag("storage_blocks/steel"))
                .addOptional(new ResourceLocation("dreamaticvoyage", "fukamizu_bread_bricks"));
        this.getOrCreateTagBuilder(ModTags.Items.STORAGE_BLOCK_CEMENTED_CARBIDE).add(ModItems.CEMENTED_CARBIDE_BLOCK)
                .addOptional(new ResourceLocation("dreamaticvoyage", "hqss_bread_bricks"));

        this.getOrCreateTagBuilder(ConventionalItemTags.ORES).forceAddTag(commonItemTag("ores/lead")).forceAddTag(commonItemTag("ores/tungsten")).forceAddTag(commonItemTag("ores/silver"));
        this.getOrCreateTagBuilder(commonItemTag("ores/lead")).add(ModItems.GALENA_ORE, ModItems.DEEPSLATE_GALENA_ORE);
        this.getOrCreateTagBuilder(commonItemTag("ores/tungsten")).add(ModItems.SCHEELITE_ORE, ModItems.DEEPSLATE_SCHEELITE_ORE);
        this.getOrCreateTagBuilder(commonItemTag("ores/silver")).add(ModItems.SILVER_ORE, ModItems.DEEPSLATE_SILVER_ORE);

        this.getOrCreateTagBuilder(ConventionalItemTags.RAW_ORES).forceAddTag(commonItemTag("raw_materials/lead")).forceAddTag(commonItemTag("raw_materials/tungsten")).forceAddTag(commonItemTag("raw_materials/silver"));
        this.getOrCreateTagBuilder(commonItemTag("raw_materials/lead")).add(ModItems.GALENA);
        this.getOrCreateTagBuilder(commonItemTag("raw_materials/tungsten")).add(ModItems.SCHEELITE);
        this.getOrCreateTagBuilder(commonItemTag("raw_materials/silver")).add(ModItems.RAW_SILVER);

        this.getOrCreateTagBuilder(ConventionalItemTags.ORES).add(ModItems.GALENA_ORE, ModItems.DEEPSLATE_GALENA_ORE,
                ModItems.SCHEELITE_ORE, ModItems.DEEPSLATE_SCHEELITE_ORE,
                ModItems.SILVER_ORE, ModItems.DEEPSLATE_SILVER_ORE);


        this.getOrCreateTagBuilder(commonItemTag("plates")).forceAddTag(commonItemTag("plates/copper"));
        this.getOrCreateTagBuilder(commonItemTag("plates/copper")).add(ModItems.COPPER_PLATE);

        this.getOrCreateTagBuilder(commonItemTag("tools/crowbar")).add(ModItems.CROWBAR);

        this.getOrCreateTagBuilder(ModTags.Items.HAMMER).add(ModItems.HAMMER, ModItems.GOLDEN_HAMMER, ModItems.STEEL_HAMMER, ModItems.DIAMOND_HAMMER,
                ModItems.CEMENTED_CARBIDE_HAMMER, ModItems.NETHERITE_HAMMER);
        this.getOrCreateTagBuilder(ModTags.Items.TOOLS_HAMMER).forceAddTag(ModTags.Items.HAMMER);

        // 专门给其他模组添加动画用的枪械武器分类 tag
        this.getOrCreateTagBuilder(ModTags.Items.ANIMATED_PISTOL).add(
                ModItems.TASER,
                ModItems.GLOCK_17,
                ModItems.GLOCK_18,
                ModItems.MP_443,
                ModItems.M_1911,
                ModItems.TRACHELIUM);

        this.getOrCreateTagBuilder(ModTags.Items.ANIMATED_SNIPER).add(
                ModItems.MOSIN_NAGANT,
                ModItems.SVD,
                ModItems.AWM,
                ModItems.NTW_20);

        this.getOrCreateTagBuilder(ModTags.Items.ANIMATED_RIFLE).add(
                ModItems.AK_47,
                ModItems.AK_12,
                ModItems.SKS,
                ModItems.M_4,
                ModItems.HK_416,
                ModItems.QBZ_95,
                ModItems.QBZ_191,
                ModItems.INSIDIOUS,
                ModItems.MK_14,
                ModItems.MARLIN,
                ModItems.K_98,
                ModItems.M_98B,
                ModItems.SENTINEL,
                ModItems.HUNTING_RIFLE);

        this.getOrCreateTagBuilder(ModTags.Items.ANIMATED_SHOTGUN).add(
                ModItems.HOMEMADE_SHOTGUN,
                ModItems.M_870,
                ModItems.AA_12,
                ModItems.M_79,
                ModItems.SECONDARY_CATACLYSM);

        this.getOrCreateTagBuilder(ModTags.Items.ANIMATED_SMG).add(
                ModItems.MP_5,
                ModItems.VECTOR);

        this.getOrCreateTagBuilder(ModTags.Items.ANIMATED_RPG).add(
                ModItems.RPG,
                ModItems.JAVELIN);

        this.getOrCreateTagBuilder(ModTags.Items.ANIMATED_MG).add(
                ModItems.DEVOTION,
                ModItems.RPK,
                ModItems.M_60,
                ModItems.M_2_HB);

        this.getOrCreateTagBuilder(ModTags.Items.ANIMATED_MINIGUN).add(
                ModItems.MINIGUN);

        // TODO 清理枪械Tag
        ModItems.GUN_ITEMS.forEach(item -> this.getOrCreateTagBuilder(ModTags.Items.GUN).add(item));

        this.getOrCreateTagBuilder(ModTags.Items.SMG).add(ModItems.VECTOR);

        this.getOrCreateTagBuilder(ModTags.Items.RIFLE).add(ModItems.M_4, ModItems.HK_416, ModItems.SKS,
                ModItems.MK_14, ModItems.MARLIN, ModItems.AK_47, ModItems.AK_12, ModItems.QBZ_95, ModItems.QBZ_191);

        this.getOrCreateTagBuilder(ModTags.Items.SNIPER_RIFLE).add(ModItems.HUNTING_RIFLE, ModItems.SENTINEL,
                ModItems.SVD, ModItems.M_98B, ModItems.K_98, ModItems.MOSIN_NAGANT, ModItems.AWM);

        this.getOrCreateTagBuilder(ModTags.Items.HEAVY_WEAPON).add(ModItems.NTW_20, ModItems.M_2_HB);

        this.getOrCreateTagBuilder(ModTags.Items.SHOTGUN).add(ModItems.HOMEMADE_SHOTGUN, ModItems.M_870, ModItems.AA_12);

        this.getOrCreateTagBuilder(ModTags.Items.NORMAL_GUN).add(ModItems.HOMEMADE_SHOTGUN, ModItems.AK_47, ModItems.AK_12, ModItems.SVD, ModItems.M_60, ModItems.MK_14, ModItems.VECTOR,
                ModItems.SKS, ModItems.RPK, ModItems.HK_416, ModItems.AA_12, ModItems.M_4, ModItems.DEVOTION, ModItems.TRACHELIUM, ModItems.M_79,
                ModItems.HUNTING_RIFLE, ModItems.NTW_20, ModItems.M_98B, ModItems.SENTINEL, ModItems.M_870, ModItems.MARLIN, ModItems.GLOCK_17, ModItems.RPG,
                ModItems.GLOCK_18, ModItems.M_1911, ModItems.AURELIA_SCEPTRE, ModItems.QBZ_95, ModItems.K_98, ModItems.MOSIN_NAGANT, ModItems.MP_443, ModItems.INSIDIOUS, ModItems.SECONDARY_CATACLYSM,
                ModItems.TASER, ModItems.MINIGUN, ModItems.MP_5, ModItems.M_2_HB, ModItems.QBZ_191, ModItems.AWM);

        this.getOrCreateTagBuilder(ModTags.Items.LAUNCHER).add(ModItems.RPG, ModItems.JAVELIN)
                .addTag(ModTags.Items.LAUNCHER_GRENADE);
        this.getOrCreateTagBuilder(ModTags.Items.LAUNCHER_GRENADE).add(ModItems.M_79, ModItems.SECONDARY_CATACLYSM);

        this.getOrCreateTagBuilder(ModTags.Items.MILITARY_ARMOR).add(ModItems.RU_CHEST_6B43, ModItems.US_CHEST_IOTV);

        this.getOrCreateTagBuilder(ModTags.Items.BLUEPRINT).forceAddTag(ModTags.Items.COMMON_BLUEPRINT).forceAddTag(ModTags.Items.RARE_BLUEPRINT)
                .forceAddTag(ModTags.Items.EPIC_BLUEPRINT).forceAddTag(ModTags.Items.LEGENDARY_BLUEPRINT).forceAddTag(ModTags.Items.CANNON_BLUEPRINT);

        this.getOrCreateTagBuilder(ModTags.Items.COMMON_BLUEPRINT).add(ModItems.GLOCK_17_BLUEPRINT, ModItems.MP_443_BLUEPRINT, ModItems.MARLIN_BLUEPRINT,
                ModItems.TASER_BLUEPRINT, ModItems.M_1911_BLUEPRINT);

        this.getOrCreateTagBuilder(ModTags.Items.RARE_BLUEPRINT).add(ModItems.GLOCK_18_BLUEPRINT, ModItems.M_79_BLUEPRINT, ModItems.M_4_BLUEPRINT,
                ModItems.SKS_BLUEPRINT, ModItems.M_870_BLUEPRINT, ModItems.AK_47_BLUEPRINT, ModItems.K_98_BLUEPRINT,
                ModItems.MOSIN_NAGANT_BLUEPRINT, ModItems.M_2_HB_BLUEPRINT, ModItems.HK_416_BLUEPRINT, ModItems.AK_12_BLUEPRINT
                , ModItems.QBZ_95_BLUEPRINT, ModItems.RPG_BLUEPRINT);

        this.getOrCreateTagBuilder(ModTags.Items.EPIC_BLUEPRINT).add(ModItems.TRACHELIUM_BLUEPRINT, ModItems.HUNTING_RIFLE_BLUEPRINT, ModItems.BOCEK_BLUEPRINT,
                ModItems.RPK_BLUEPRINT, ModItems.VECTOR_BLUEPRINT, ModItems.MK_14_BLUEPRINT, ModItems.M_60_BLUEPRINT, ModItems.SVD_BLUEPRINT,
                ModItems.M_98B_BLUEPRINT, ModItems.DEVOTION_BLUEPRINT, ModItems.INSIDIOUS_BLUEPRINT, ModItems.QBZ_191_BLUEPRINT, ModItems.AWM_BLUEPRINT);

        this.getOrCreateTagBuilder(ModTags.Items.LEGENDARY_BLUEPRINT).add(ModItems.AA_12_BLUEPRINT, ModItems.NTW_20_BLUEPRINT, ModItems.MINIGUN_BLUEPRINT,
                ModItems.SENTINEL_BLUEPRINT, ModItems.JAVELIN_BLUEPRINT, ModItems.SECONDARY_CATACLYSM_BLUEPRINT, ModItems.MK_42_BLUEPRINT,
                ModItems.MLE_1934_BLUEPRINT, ModItems.ANNIHILATOR_BLUEPRINT, ModItems.HPJ_11_BLUEPRINT, ModItems.AURELIA_SCEPTRE_BLUEPRINT
                , ModItems.BL_132_BLUEPRINT);

        this.getOrCreateTagBuilder(ModTags.Items.CANNON_BLUEPRINT).add(ModItems.MK_42_BLUEPRINT, ModItems.MLE_1934_BLUEPRINT, ModItems.ANNIHILATOR_BLUEPRINT,
                ModItems.HPJ_11_BLUEPRINT, ModItems.BL_132_BLUEPRINT);
    }
}
