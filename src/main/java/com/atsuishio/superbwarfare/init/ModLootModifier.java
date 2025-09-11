package com.atsuishio.superbwarfare.init;

import com.atsuishio.superbwarfare.datagen.ModCustomLootProvider;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;

public class ModLootModifier {

    public static void init() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (id.toString().startsWith("minecraft:chests")) {
                ModCustomLootProvider.LOOTS.forEach(loot ->
                        tableBuilder.withPool(LootPool.lootPool().add(LootTableReference.lootTableReference(loot)))
                );
            }
        });
    }
}
