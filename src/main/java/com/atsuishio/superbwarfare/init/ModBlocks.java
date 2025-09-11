package com.atsuishio.superbwarfare.init;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.block.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

public class ModBlocks {

    public static void init() {

    }

    public static final Block SANDBAG = register("sandbag",
            new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.SNARE).sound(SoundType.SAND).strength(10f, 20f)));
    public static final Block BARBED_WIRE = register("barbed_wire", new BarbedWireBlock());
    public static final Block JUMP_PAD = register("jump_pad", new JumpPadBlock());
    public static final Block GALENA_ORE = register("galena_ore",
            new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE).strength(3f, 5f).requiresCorrectToolForDrops()));
    public static final Block DEEPSLATE_GALENA_ORE = register("deepslate_galena_ore",
            new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE).strength(3f, 8f).requiresCorrectToolForDrops()));
    public static final Block SCHEELITE_ORE = register("scheelite_ore",
            new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE).strength(3f, 5f).requiresCorrectToolForDrops()));
    public static final Block DEEPSLATE_SCHEELITE_ORE = register("deepslate_scheelite_ore",
            new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE).strength(3f, 8f).requiresCorrectToolForDrops()));
    public static final Block SILVER_ORE = register("silver_ore",
            new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE).strength(3f, 5f).requiresCorrectToolForDrops()));
    public static final Block DEEPSLATE_SILVER_ORE = register("deepslate_silver_ore",
            new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE).strength(3f, 8f).requiresCorrectToolForDrops()));
    public static final Block DRAGON_TEETH = register("dragon_teeth", new DragonTeethBlock());
    public static final Block REFORGING_TABLE = register("reforging_table", new ReforgingTableBlock());
    public static final Block LEAD_BLOCK = register("lead_block",
            new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.METAL).strength(5f, 6f).requiresCorrectToolForDrops()));
    public static final Block STEEL_BLOCK = register("steel_block",
            new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.METAL).strength(5f, 6f).requiresCorrectToolForDrops()));
    public static final Block TUNGSTEN_BLOCK = register("tungsten_block",
            new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.METAL).strength(5f, 6f).requiresCorrectToolForDrops()));
    public static final Block SILVER_BLOCK = register("silver_block",
            new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.METAL).strength(5f, 6f).requiresCorrectToolForDrops()));
    public static final Block CEMENTED_CARBIDE_BLOCK = register("cemented_carbide_block",
            new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.METAL).strength(5f, 6f).requiresCorrectToolForDrops()));
    public static final Block CONTAINER = register("container", new ContainerBlock());
    public static final Block CHARGING_STATION = register("charging_station", new ChargingStationBlock());
    public static final Block CREATIVE_CHARGING_STATION = register("creative_charging_station", new CreativeChargingStationBlock());
    public static final Block FUMO_25 = register("fumo_25", new FuMO25Block());
    public static final Block SMALL_CONTAINER = register("small_container", new SmallContainerBlock());
    public static final Block VEHICLE_DEPLOYER = register("vehicle_deployer", new VehicleDeployerBlock());
    public static final Block AIRCRAFT_CATAPULT = register("aircraft_catapult", new AircraftCatapultBlock());
    public static final Block SUPERB_ITEM_INTERFACE = register("superb_item_interface", new SuperbItemInterfaceBlock());
    public static final Block CREATIVE_SUPERB_ITEM_INTERFACE = register("creative_superb_item_interface", new CreativeSuperbItemInterfaceBlock());
    public static final Block LUCKY_CONTAINER = register("lucky_container", new LuckyContainerBlock());
    public static final Block VEHICLE_ASSEMBLING_TABLE = register("vehicle_assembling_table", new VehicleAssemblingTableBlock());

    private static Block register(String name, Block block) {
        return Registry.register(BuiltInRegistries.BLOCK, Mod.loc(name), block);
    }
}
