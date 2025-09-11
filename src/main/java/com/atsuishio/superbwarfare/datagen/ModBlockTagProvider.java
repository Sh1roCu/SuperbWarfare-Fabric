package com.atsuishio.superbwarfare.datagen;

import com.atsuishio.superbwarfare.init.ModBlocks;
import com.atsuishio.superbwarfare.init.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBlockTags;
import net.fabricmc.fabric.impl.tag.convention.TagRegistration;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider<Block> {

    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.BLOCK, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        this.getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.GALENA_ORE, ModBlocks.SCHEELITE_ORE,
                ModBlocks.DEEPSLATE_GALENA_ORE, ModBlocks.DEEPSLATE_SCHEELITE_ORE, ModBlocks.DRAGON_TEETH,
                ModBlocks.SILVER_ORE, ModBlocks.DEEPSLATE_SILVER_ORE);

        this.getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE).add(ModBlocks.BARBED_WIRE);
        this.getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.GALENA_ORE, ModBlocks.SCHEELITE_ORE,
                ModBlocks.DEEPSLATE_GALENA_ORE, ModBlocks.DEEPSLATE_SCHEELITE_ORE, ModBlocks.DRAGON_TEETH,
                ModBlocks.REFORGING_TABLE, ModBlocks.LEAD_BLOCK, ModBlocks.STEEL_BLOCK, ModBlocks.TUNGSTEN_BLOCK,
                ModBlocks.CEMENTED_CARBIDE_BLOCK, ModBlocks.SILVER_ORE, ModBlocks.DEEPSLATE_SILVER_ORE,
                ModBlocks.SILVER_BLOCK, ModBlocks.JUMP_PAD, ModBlocks.CONTAINER, ModBlocks.CHARGING_STATION,
                ModBlocks.FUMO_25, ModBlocks.SMALL_CONTAINER, ModBlocks.VEHICLE_DEPLOYER, ModBlocks.AIRCRAFT_CATAPULT,
                ModBlocks.SUPERB_ITEM_INTERFACE, ModBlocks.CREATIVE_SUPERB_ITEM_INTERFACE, ModBlocks.LUCKY_CONTAINER,
                ModBlocks.VEHICLE_ASSEMBLING_TABLE);
        this.getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_SHOVEL).add(ModBlocks.SANDBAG);

        this.getOrCreateTagBuilder(ModTags.Blocks.SOFT_COLLISION)
                .forceAddTag(BlockTags.LEAVES)
                .add(Blocks.LILY_PAD, Blocks.COBWEB, Blocks.CACTUS);
        this.getOrCreateTagBuilder(ModTags.Blocks.NORMAL_COLLISION)
                .forceAddTag(BlockTags.FENCES).forceAddTag(BlockTags.FENCE_GATES).forceAddTag(BlockTags.DOORS)
                .forceAddTag(BlockTags.TRAPDOORS).forceAddTag(BlockTags.WALLS).forceAddTag(BlockTags.WOOL)
                .forceAddTag(BlockTags.STAIRS).forceAddTag(BlockTags.SLABS).forceAddTag(ConventionalBlockTags.GLASS_PANES)
                .add(Blocks.BAMBOO, Blocks.MELON, Blocks.PUMPKIN, Blocks.HAY_BLOCK, Blocks.BELL, Blocks.CHAIN, Blocks.SNOW_BLOCK,
                        Blocks.MUSHROOM_STEM, Blocks.BROWN_MUSHROOM_BLOCK, Blocks.RED_MUSHROOM_BLOCK);
        this.getOrCreateTagBuilder(ModTags.Blocks.HARD_COLLISION)
                .forceAddTag(BlockTags.LOGS).forceAddTag(BlockTags.PLANKS).forceAddTag(ConventionalBlockTags.GLASS_BLOCKS)
                .add(Blocks.ICE, Blocks.FROSTED_ICE, Blocks.PACKED_ICE, Blocks.BLUE_ICE);
        this.getOrCreateTagBuilder(ModTags.Blocks.BULLET_IGNORE)
                .forceAddTag(BlockTags.FENCES).forceAddTag(BlockTags.FENCE_GATES).forceAddTag(BlockTags.DOORS)
                .forceAddTag(BlockTags.TRAPDOORS).forceAddTag(BlockTags.WALLS).forceAddTag(BlockTags.LEAVES).forceAddTag(ConventionalBlockTags.GLASS_PANES)
                .add(Blocks.IRON_BARS, ModBlocks.BARBED_WIRE);
        this.getOrCreateTagBuilder(ModTags.Blocks.BULLET_CAN_DESTROY)
                .forceAddTag(ConventionalBlockTags.GLASS_PANES).forceAddTag(ConventionalBlockTags.GLASS_BLOCKS);
        this.getOrCreateTagBuilder(ModTags.Blocks.CANNON_SHOT_CAN_DESTROY)
                .forceAddTag(ModTags.Blocks.BULLET_CAN_DESTROY).forceAddTag(BlockTags.LEAVES).forceAddTag(BlockTags.BAMBOO_BLOCKS)
                .forceAddTag(BlockTags.WOOL).forceAddTag(BlockTags.SIGNS).forceAddTag(BlockTags.LOGS).forceAddTag(BlockTags.PLANKS).forceAddTag(BlockTags.SAPLINGS)
                .add(Blocks.LANTERN, Blocks.SOUL_LANTERN, Blocks.CHAIN);
        this.getOrCreateTagBuilder(ModTags.Blocks.AUTO_LANDING)
                .add(ModBlocks.CHARGING_STATION, ModBlocks.CREATIVE_CHARGING_STATION);

        this.getOrCreateTagBuilder(ConventionalBlockTags.ORES).forceAddTag(cTag("ores/lead")).forceAddTag(cTag("ores/tungsten")).forceAddTag(cTag("ores/silver"));
        this.getOrCreateTagBuilder(cTag("ores/lead")).add(ModBlocks.GALENA_ORE, ModBlocks.DEEPSLATE_GALENA_ORE);
        this.getOrCreateTagBuilder(cTag("ores/tungsten")).add(ModBlocks.SCHEELITE_ORE, ModBlocks.DEEPSLATE_SCHEELITE_ORE);
        this.getOrCreateTagBuilder(cTag("ores/silver")).add(ModBlocks.SILVER_ORE, ModBlocks.DEEPSLATE_SILVER_ORE);

        this.getOrCreateTagBuilder(BlockTags.STONE_ORE_REPLACEABLES).add(ModBlocks.GALENA_ORE, ModBlocks.SCHEELITE_ORE, ModBlocks.SILVER_ORE);
        this.getOrCreateTagBuilder(BlockTags.DEEPSLATE_ORE_REPLACEABLES).add(ModBlocks.DEEPSLATE_GALENA_ORE, ModBlocks.DEEPSLATE_SCHEELITE_ORE, ModBlocks.DEEPSLATE_SILVER_ORE);
    }

    public static TagKey<Block> cTag(String name) {
        return TagRegistration.BLOCK_TAG_REGISTRATION.registerCommon(name);
    }
}
