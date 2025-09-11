package com.atsuishio.superbwarfare.datagen;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.init.ModBlocks;
import io.github.fabricators_of_create.porting_lib.data.ExistingFileHelper;
import io.github.fabricators_of_create.porting_lib.models.generators.ModelFile;
import io.github.fabricators_of_create.porting_lib.models.generators.block.BlockStateProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

@SuppressWarnings({"ConstantConditions", "SameParameterValue"})
public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Mod.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        horizontalBlock(ModBlocks.BARBED_WIRE, new ModelFile.UncheckedModelFile(modLoc("block/barbed_wire")));
        horizontalBlock(ModBlocks.JUMP_PAD, new ModelFile.UncheckedModelFile(modLoc("block/jump_pad")));
        horizontalBlock(ModBlocks.REFORGING_TABLE, new ModelFile.UncheckedModelFile(modLoc("block/reforging_table")));
        horizontalBlock(ModBlocks.CONTAINER, new ModelFile.UncheckedModelFile(modLoc("block/container")));
        horizontalBlock(ModBlocks.SMALL_CONTAINER, new ModelFile.UncheckedModelFile(modLoc("block/small_container")));
        horizontalBlock(ModBlocks.LUCKY_CONTAINER, new ModelFile.UncheckedModelFile(modLoc("block/container")));
        horizontalBlock(ModBlocks.CHARGING_STATION, new ModelFile.UncheckedModelFile(modLoc("block/charging_station")));
        horizontalBlock(ModBlocks.CREATIVE_CHARGING_STATION, new ModelFile.UncheckedModelFile(modLoc("block/creative_charging_station")));
        horizontalBlock(ModBlocks.VEHICLE_DEPLOYER, models().cubeBottomTop("vehicle_deployer", Mod.loc("block/vehicle_deployer_side"),
                        Mod.loc("block/vehicle_deployer_bottom"), Mod.loc("block/vehicle_deployer_top"))
                .texture("particle", Mod.loc("block/vehicle_deployer_bottom")));
        horizontalBlock(ModBlocks.VEHICLE_ASSEMBLING_TABLE, new ModelFile.UncheckedModelFile(modLoc("block/vehicle_assembling_table")));

        horizontalBlock(ModBlocks.AIRCRAFT_CATAPULT, models().cube("aircraft_catapult",
                        Mod.loc("block/vehicle_deployer_bottom"),
                        Mod.loc("block/aircraft_catapult_top"),
                        Mod.loc("block/aircraft_catapult_side"),
                        Mod.loc("block/aircraft_catapult_side"),
                        Mod.loc("block/aircraft_catapult_side2"),
                        Mod.loc("block/aircraft_catapult_side2"))
                .texture("particle", Mod.loc("block/aircraft_catapult_top")));

        directionalBlock(ModBlocks.SUPERB_ITEM_INTERFACE, models().cubeBottomTop("superb_item_interface",
                        Mod.loc("block/superb_item_interface_side"),
                        Mod.loc("block/superb_item_interface_bottom"),
                        Mod.loc("block/superb_item_interface_top"))
                .texture("particle", Mod.loc("block/superb_item_interface_bottom"))
        );

        directionalBlock(ModBlocks.CREATIVE_SUPERB_ITEM_INTERFACE, models().cubeBottomTop("creative_superb_item_interface",
                        Mod.loc("block/creative_superb_item_interface_side"),
                        Mod.loc("block/creative_superb_item_interface_bottom"),
                        Mod.loc("block/creative_superb_item_interface_top"))
                .texture("particle", Mod.loc("block/creative_superb_item_interface_bottom"))
        );

        blockWithItem(ModBlocks.GALENA_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_GALENA_ORE);
        blockWithItem(ModBlocks.SCHEELITE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_SCHEELITE_ORE);
        blockWithItem(ModBlocks.LEAD_BLOCK);
        blockWithItem(ModBlocks.STEEL_BLOCK);
        blockWithItem(ModBlocks.TUNGSTEN_BLOCK);
        blockWithItem(ModBlocks.CEMENTED_CARBIDE_BLOCK);
        blockWithItem(ModBlocks.SILVER_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_SILVER_ORE);
        blockWithItem(ModBlocks.SILVER_BLOCK);

        simpleBlock(ModBlocks.FUMO_25, new ModelFile.UncheckedModelFile(modLoc("block/fumo_25")));
    }

    private String name(Block block) {
        return key(block).getPath();
    }

    private ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    private void blockItem(Block block) {
        simpleBlockItem(block, new ModelFile.UncheckedModelFile(Mod.MODID +
                ":block/" + name(block)));
    }

    private void blockWithItem(Block block) {
        simpleBlockWithItem(block, cubeAll(block));
    }
}