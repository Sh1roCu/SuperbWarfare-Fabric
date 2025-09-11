package com.atsuishio.superbwarfare.datagen;

import com.atsuishio.superbwarfare.block.VehicleAssemblingTableBlock;
import com.atsuishio.superbwarfare.block.property.BlockPart;
import com.atsuishio.superbwarfare.init.ModBlocks;
import com.atsuishio.superbwarfare.init.ModItems;
import com.mojang.datafixers.util.Pair;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyNbtFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.List;

public class ModBlockLootProvider extends FabricBlockLootTableProvider {

    protected ModBlockLootProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        this.dropSelf(ModBlocks.SANDBAG);
        this.dropSelf(ModBlocks.BARBED_WIRE);
        this.dropSelf(ModBlocks.JUMP_PAD);
        this.dropSelf(ModBlocks.DRAGON_TEETH);
        this.dropSelf(ModBlocks.REFORGING_TABLE);
        this.dropSelf(ModBlocks.LEAD_BLOCK);
        this.dropSelf(ModBlocks.STEEL_BLOCK);
        this.dropSelf(ModBlocks.TUNGSTEN_BLOCK);
        this.dropSelf(ModBlocks.CEMENTED_CARBIDE_BLOCK);
        this.dropSelf(ModBlocks.SILVER_BLOCK);
        this.dropSelf(ModBlocks.CREATIVE_CHARGING_STATION);
        this.dropSelf(ModBlocks.FUMO_25);
        this.dropSelf(ModBlocks.VEHICLE_DEPLOYER);
        this.dropSelf(ModBlocks.AIRCRAFT_CATAPULT);
        this.dropSelf(ModBlocks.SUPERB_ITEM_INTERFACE);
        this.dropSelf(ModBlocks.CREATIVE_SUPERB_ITEM_INTERFACE);
        this.add(ModBlocks.VEHICLE_ASSEMBLING_TABLE,
                this.applyExplosionDecay(ModBlocks.VEHICLE_ASSEMBLING_TABLE, LootTable.lootTable().withPool(LootPool.lootPool().add(
                        LootItem.lootTableItem(ModBlocks.VEHICLE_ASSEMBLING_TABLE).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.VEHICLE_ASSEMBLING_TABLE)
                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(VehicleAssemblingTableBlock.BLOCK_PART, BlockPart.FLB))).otherwise(LootItem.lootTableItem(Blocks.AIR)))
                ))
        );

        this.add(ModBlocks.CHARGING_STATION, createCopyNBTDrops(ModBlocks.CHARGING_STATION,
                List.of(Pair.of("Energy", "BlockEntityTag.Energy"),
                        Pair.of("id", "BlockEntityTag.id"))));

        this.add(ModBlocks.GALENA_ORE, this.createOreDrop(ModBlocks.GALENA_ORE, ModItems.GALENA));
        this.add(ModBlocks.SCHEELITE_ORE, this.createOreDrop(ModBlocks.SCHEELITE_ORE, ModItems.SCHEELITE));
        this.add(ModBlocks.SILVER_ORE, this.createOreDrop(ModBlocks.SILVER_ORE, ModItems.RAW_SILVER));
        this.add(ModBlocks.DEEPSLATE_GALENA_ORE, this.createOreDrop(ModBlocks.DEEPSLATE_GALENA_ORE, ModItems.GALENA));
        this.add(ModBlocks.DEEPSLATE_SCHEELITE_ORE, this.createOreDrop(ModBlocks.DEEPSLATE_SCHEELITE_ORE, ModItems.SCHEELITE));
        this.add(ModBlocks.DEEPSLATE_SILVER_ORE, this.createOreDrop(ModBlocks.DEEPSLATE_SILVER_ORE, ModItems.RAW_SILVER));

        this.add(ModBlocks.CONTAINER, LootTable.lootTable().withPool(this.applyExplosionCondition(ModBlocks.CONTAINER,
                LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(ModBlocks.CONTAINER))
                        .apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY).copy("Entity", "BlockEntityTag.Entity")
                                .copy("EntityType", "BlockEntityTag.EntityType")))));
        this.add(ModBlocks.SMALL_CONTAINER, LootTable.lootTable().withPool(this.applyExplosionCondition(ModBlocks.SMALL_CONTAINER,
                LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(ModBlocks.SMALL_CONTAINER))
                        .apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY).copy("LootTable", "BlockEntityTag.LootTable")
                                .copy("LootTableSeed", "BlockEntityTag.LootTableSeed")))));
        this.add(ModBlocks.LUCKY_CONTAINER, LootTable.lootTable().withPool(this.applyExplosionCondition(ModBlocks.LUCKY_CONTAINER,
                LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(ModBlocks.LUCKY_CONTAINER))
                        .apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY).copy("Location", "BlockEntityTag.Location")
                                .copy("Icon", "BlockEntityTag.Icon")))));
    }

    public LootTable.Builder createCopyNBTDrops(Block pBlock, List<Pair<String, String>> paths) {
        var pool = LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(pBlock));
        if (!paths.isEmpty()) {
            var copy = CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY);
            for (var path : paths) {
                copy.copy(path.getFirst(), path.getSecond());
            }
            pool.apply(copy);
        }
        return LootTable.lootTable().withPool(this.applyExplosionCondition(pBlock, pool));
    }
}
