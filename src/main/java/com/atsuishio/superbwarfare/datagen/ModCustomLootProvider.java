package com.atsuishio.superbwarfare.datagen;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.init.ModItems;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;

public class ModCustomLootProvider extends SimpleFabricLootTableProvider {

    public ModCustomLootProvider(FabricDataOutput output) {
        super(output, LootContextParamSets.CHEST);
    }

    public static final Set<ResourceLocation> LOOTS = Sets.newHashSet();

    public static ResourceLocation containers(String name) {
        ResourceLocation loc = Mod.loc("containers/" + name);
        LOOTS.add(loc);
        return loc;
    }

    public static ResourceLocation chests(String name) {
        ResourceLocation loc = Mod.loc("chests/" + name);
        LOOTS.add(loc);
        return loc;
    }

    public static ResourceLocation special(String name) {
        ResourceLocation loc = Mod.loc("special/" + name);
        LOOTS.add(loc);
        return loc;
    }

    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> pOutput) {
        pOutput.accept(chests("ancient_cpu"),
                LootTable.lootTable()
                        .withPool(singleItem(ModItems.ANCIENT_CPU, 1, 1, 1, 0)
                                .when(() -> LootItemRandomChanceCondition.randomChance(0.4f).build()))
        );
        pOutput.accept(chests("blue_print_common"),
                LootTable.lootTable()
                        .withPool(multiItems(1, 0,
                                new ItemEntry(ModItems.TASER_BLUEPRINT, 50),
                                new ItemEntry(ModItems.GLOCK_17_BLUEPRINT, 50),
                                new ItemEntry(ModItems.MP_443_BLUEPRINT, 50),
                                new ItemEntry(ModItems.M_1911_BLUEPRINT, 50),
                                new ItemEntry(ModItems.MARLIN_BLUEPRINT, 50),

                                new ItemEntry(ModItems.GLOCK_18_BLUEPRINT, 15),
                                new ItemEntry(ModItems.M_79_BLUEPRINT, 15),
                                new ItemEntry(ModItems.M_4_BLUEPRINT, 15),
                                new ItemEntry(ModItems.SKS_BLUEPRINT, 15),
                                new ItemEntry(ModItems.K_98_BLUEPRINT, 15),
                                new ItemEntry(ModItems.MOSIN_NAGANT_BLUEPRINT, 15),
                                new ItemEntry(ModItems.AK_47_BLUEPRINT, 15),
                                new ItemEntry(ModItems.M_870_BLUEPRINT, 15),
                                new ItemEntry(ModItems.HK_416_BLUEPRINT, 15),
                                new ItemEntry(ModItems.AK_12_BLUEPRINT, 15),
                                new ItemEntry(ModItems.QBZ_95_BLUEPRINT, 15),
                                new ItemEntry(ModItems.RPG_BLUEPRINT, 15),
                                new ItemEntry(ModItems.M_2_HB_BLUEPRINT, 15),
                                new ItemEntry(ModItems.MP_5_BLUEPRINT, 15),
                                new ItemEntry(ModItems.HUNTING_RIFLE_BLUEPRINT, 15),

                                new ItemEntry(ModItems.TRACHELIUM_BLUEPRINT, 1),
                                new ItemEntry(ModItems.SENTINEL_BLUEPRINT, 1),
                                new ItemEntry(ModItems.BOCEK_BLUEPRINT, 1),
                                new ItemEntry(ModItems.RPK_BLUEPRINT, 1),
                                new ItemEntry(ModItems.VECTOR_BLUEPRINT, 1),
                                new ItemEntry(ModItems.MK_14_BLUEPRINT, 1),
                                new ItemEntry(ModItems.M_60_BLUEPRINT, 1),
                                new ItemEntry(ModItems.SVD_BLUEPRINT, 1),
                                new ItemEntry(ModItems.M_98B_BLUEPRINT, 1),
                                new ItemEntry(ModItems.AWM_BLUEPRINT, 1),
                                new ItemEntry(ModItems.DEVOTION_BLUEPRINT, 1),
                                new ItemEntry(ModItems.INSIDIOUS_BLUEPRINT, 1),
                                new ItemEntry(ModItems.QBZ_191_BLUEPRINT, 1)
                        ))
                        .withPool(multiItems(2, 0,
                                new ItemEntry(ModItems.HANDGUN_AMMO_BOX, 12)
                                        .setCountBetween(1, 2),
                                new ItemEntry(ModItems.RIFLE_AMMO_BOX, 20)
                                        .setCountBetween(1, 2),
                                new ItemEntry(ModItems.SNIPER_AMMO_BOX, 10)
                                        .setCountBetween(1, 2),
                                new ItemEntry(ModItems.SHOTGUN_AMMO_BOX, 17)
                                        .setCountBetween(1, 2),
                                new ItemEntry(ModItems.GRENADE_40MM, 6)
                                        .setCountBetween(1, 3),
                                new ItemEntry(ModItems.RPG_ROCKET_TBG, 2)
                                        .setCountBetween(1, 2),
                                new ItemEntry(ModItems.RPG_ROCKET_STANDARD, 2)
                                        .setCountBetween(1, 2),
                                new ItemEntry(ModItems.MORTAR_SHELL, 6)
                                        .setCountBetween(1, 4),
                                new ItemEntry(ModItems.CLAYMORE_MINE, 3)
                                        .setCountBetween(1, 3),
                                new ItemEntry(ModItems.C4_BOMB, 1)
                        ))
        );
        pOutput.accept(chests("blue_print_rare"),
                LootTable.lootTable()
                        .withPool(multiItems(1, 0,
                                new ItemEntry(ModItems.TASER_BLUEPRINT, 10),
                                new ItemEntry(ModItems.GLOCK_17_BLUEPRINT, 10),
                                new ItemEntry(ModItems.MP_443_BLUEPRINT, 10),
                                new ItemEntry(ModItems.M_1911_BLUEPRINT, 10),
                                new ItemEntry(ModItems.MARLIN_BLUEPRINT, 10),

                                new ItemEntry(ModItems.GLOCK_18_BLUEPRINT, 30),
                                new ItemEntry(ModItems.M_79_BLUEPRINT, 30),
                                new ItemEntry(ModItems.M_4_BLUEPRINT, 30),
                                new ItemEntry(ModItems.SKS_BLUEPRINT, 30),
                                new ItemEntry(ModItems.K_98_BLUEPRINT, 30),
                                new ItemEntry(ModItems.MOSIN_NAGANT_BLUEPRINT, 30),
                                new ItemEntry(ModItems.AK_47_BLUEPRINT, 30),
                                new ItemEntry(ModItems.M_870_BLUEPRINT, 30),
                                new ItemEntry(ModItems.HK_416_BLUEPRINT, 30),
                                new ItemEntry(ModItems.AK_12_BLUEPRINT, 30),
                                new ItemEntry(ModItems.QBZ_95_BLUEPRINT, 30),
                                new ItemEntry(ModItems.RPG_BLUEPRINT, 30),
                                new ItemEntry(ModItems.M_2_HB_BLUEPRINT, 30),
                                new ItemEntry(ModItems.HUNTING_RIFLE_BLUEPRINT, 30),

                                new ItemEntry(ModItems.TRACHELIUM_BLUEPRINT, 10),
                                new ItemEntry(ModItems.SENTINEL_BLUEPRINT, 10),
                                new ItemEntry(ModItems.BOCEK_BLUEPRINT, 10),
                                new ItemEntry(ModItems.RPK_BLUEPRINT, 10),
                                new ItemEntry(ModItems.VECTOR_BLUEPRINT, 10),
                                new ItemEntry(ModItems.MK_14_BLUEPRINT, 10),
                                new ItemEntry(ModItems.M_60_BLUEPRINT, 10),
                                new ItemEntry(ModItems.SVD_BLUEPRINT, 10),
                                new ItemEntry(ModItems.M_98B_BLUEPRINT, 10),
                                new ItemEntry(ModItems.AWM_BLUEPRINT, 10),
                                new ItemEntry(ModItems.DEVOTION_BLUEPRINT, 10),
                                new ItemEntry(ModItems.INSIDIOUS_BLUEPRINT, 10),
                                new ItemEntry(ModItems.QBZ_191_BLUEPRINT, 10),

                                new ItemEntry(ModItems.AA_12_BLUEPRINT, 3),
                                new ItemEntry(ModItems.NTW_20_BLUEPRINT, 3),
                                new ItemEntry(ModItems.MINIGUN_BLUEPRINT, 3),
                                new ItemEntry(ModItems.JAVELIN_BLUEPRINT, 3),
                                new ItemEntry(ModItems.SECONDARY_CATACLYSM_BLUEPRINT, 3),
                                new ItemEntry(ModItems.AURELIA_SCEPTRE_BLUEPRINT, 2),
                                new ItemEntry(ModItems.MK_42_BLUEPRINT, 3),
                                new ItemEntry(ModItems.MLE_1934_BLUEPRINT, 2),
                                new ItemEntry(ModItems.HPJ_11_BLUEPRINT, 2),
                                new ItemEntry(ModItems.BL_132_BLUEPRINT, 2),
                                new ItemEntry(ModItems.ANNIHILATOR_BLUEPRINT, 1)
                        ))
                        .withPool(multiItems(2, 0,
                                new ItemEntry(ModItems.HANDGUN_AMMO_BOX, 12)
                                        .setCountBetween(1, 3),
                                new ItemEntry(ModItems.RIFLE_AMMO_BOX, 20)
                                        .setCountBetween(1, 3),
                                new ItemEntry(ModItems.SNIPER_AMMO_BOX, 10)
                                        .setCountBetween(1, 3),
                                new ItemEntry(ModItems.SHOTGUN_AMMO_BOX, 17)
                                        .setCountBetween(1, 3),
                                new ItemEntry(ModItems.GRENADE_40MM, 6)
                                        .setCountBetween(2, 6),
                                new ItemEntry(ModItems.RPG_ROCKET_TBG, 2)
                                        .setCountBetween(2, 4),
                                new ItemEntry(ModItems.RPG_ROCKET_STANDARD, 2)
                                        .setCountBetween(2, 4),
                                new ItemEntry(ModItems.MORTAR_SHELL, 6)
                                        .setCountBetween(2, 8),
                                new ItemEntry(ModItems.CLAYMORE_MINE, 3)
                                        .setCountBetween(2, 6),
                                new ItemEntry(ModItems.C4_BOMB, 1)
                                        .setCountBetween(1, 2)
                        ))
        );
        pOutput.accept(chests("blue_print_epic"),
                LootTable.lootTable()
                        .withPool(multiItems(1, 0,
                                new ItemEntry(ModItems.TRACHELIUM_BLUEPRINT, 10),
                                new ItemEntry(ModItems.SENTINEL_BLUEPRINT, 10),
                                new ItemEntry(ModItems.BOCEK_BLUEPRINT, 10),
                                new ItemEntry(ModItems.RPK_BLUEPRINT, 10),
                                new ItemEntry(ModItems.VECTOR_BLUEPRINT, 10),
                                new ItemEntry(ModItems.MK_14_BLUEPRINT, 10),
                                new ItemEntry(ModItems.M_60_BLUEPRINT, 10),
                                new ItemEntry(ModItems.SVD_BLUEPRINT, 10),
                                new ItemEntry(ModItems.M_98B_BLUEPRINT, 10),
                                new ItemEntry(ModItems.AWM_BLUEPRINT, 10),
                                new ItemEntry(ModItems.DEVOTION_BLUEPRINT, 10),
                                new ItemEntry(ModItems.INSIDIOUS_BLUEPRINT, 10),
                                new ItemEntry(ModItems.QBZ_191_BLUEPRINT, 10),

                                new ItemEntry(ModItems.AA_12_BLUEPRINT, 20),
                                new ItemEntry(ModItems.NTW_20_BLUEPRINT, 20),
                                new ItemEntry(ModItems.MINIGUN_BLUEPRINT, 20),
                                new ItemEntry(ModItems.JAVELIN_BLUEPRINT, 15),
                                new ItemEntry(ModItems.SECONDARY_CATACLYSM_BLUEPRINT, 15),
                                new ItemEntry(ModItems.AURELIA_SCEPTRE_BLUEPRINT, 10),
                                new ItemEntry(ModItems.MK_42_BLUEPRINT, 10),
                                new ItemEntry(ModItems.MLE_1934_BLUEPRINT, 10),
                                new ItemEntry(ModItems.BL_132_BLUEPRINT, 7),
                                new ItemEntry(ModItems.HPJ_11_BLUEPRINT, 5),
                                new ItemEntry(ModItems.ANNIHILATOR_BLUEPRINT, 5)
                        ))
                        .withPool(multiItems(2, 0,
                                new ItemEntry(ModItems.HANDGUN_AMMO_BOX, 12)
                                        .setCountBetween(2, 4),
                                new ItemEntry(ModItems.RIFLE_AMMO_BOX, 20)
                                        .setCountBetween(2, 4),
                                new ItemEntry(ModItems.SNIPER_AMMO_BOX, 10)
                                        .setCountBetween(2, 4),
                                new ItemEntry(ModItems.SHOTGUN_AMMO_BOX, 17)
                                        .setCountBetween(2, 4),
                                new ItemEntry(ModItems.HEAVY_AMMO, 10)
                                        .setCountBetween(10, 24),
                                new ItemEntry(ModItems.GRENADE_40MM, 6)
                                        .setCountBetween(4, 12),
                                new ItemEntry(ModItems.RPG_ROCKET_TBG, 2)
                                        .setCountBetween(4, 8),
                                new ItemEntry(ModItems.RPG_ROCKET_STANDARD, 2)
                                        .setCountBetween(4, 8),
                                new ItemEntry(ModItems.MORTAR_SHELL, 6)
                                        .setCountBetween(4, 8),
                                new ItemEntry(ModItems.CLAYMORE_MINE, 3)
                                        .setCountBetween(4, 12),
                                new ItemEntry(ModItems.C4_BOMB, 1)
                                        .setCountBetween(2, 4),
                                new ItemEntry(ModItems.JAVELIN_MISSILE, 1)
                                        .setCountBetween(1, 2)
                        ))
        );

        pOutput.accept(containers("blueprints"),
                LootTable.lootTable()
                        .withPool(multiItems(1, 0,
                                new ItemEntry(ModItems.GLOCK_17_BLUEPRINT, 60),
                                new ItemEntry(ModItems.MP_443_BLUEPRINT, 60),
                                new ItemEntry(ModItems.TASER_BLUEPRINT, 60),
                                new ItemEntry(ModItems.MARLIN_BLUEPRINT, 60),
                                new ItemEntry(ModItems.M_1911_BLUEPRINT, 60),

                                new ItemEntry(ModItems.GLOCK_18_BLUEPRINT, 42),
                                new ItemEntry(ModItems.M_79_BLUEPRINT, 42),
                                new ItemEntry(ModItems.M_4_BLUEPRINT, 42),
                                new ItemEntry(ModItems.SKS_BLUEPRINT, 42),
                                new ItemEntry(ModItems.M_870_BLUEPRINT, 42),
                                new ItemEntry(ModItems.AK_47_BLUEPRINT, 42),
                                new ItemEntry(ModItems.K_98_BLUEPRINT, 42),
                                new ItemEntry(ModItems.MOSIN_NAGANT_BLUEPRINT, 42),
                                new ItemEntry(ModItems.HK_416_BLUEPRINT, 42),
                                new ItemEntry(ModItems.AK_12_BLUEPRINT, 42),
                                new ItemEntry(ModItems.QBZ_95_BLUEPRINT, 42),
                                new ItemEntry(ModItems.RPG_BLUEPRINT, 42),
                                new ItemEntry(ModItems.HUNTING_RIFLE_BLUEPRINT, 42),
                                new ItemEntry(ModItems.M_2_HB_BLUEPRINT, 42),

                                new ItemEntry(ModItems.TRACHELIUM_BLUEPRINT, 15),
                                new ItemEntry(ModItems.SENTINEL_BLUEPRINT, 15),
                                new ItemEntry(ModItems.BOCEK_BLUEPRINT, 15),
                                new ItemEntry(ModItems.RPK_BLUEPRINT, 15),
                                new ItemEntry(ModItems.VECTOR_BLUEPRINT, 15),
                                new ItemEntry(ModItems.MK_14_BLUEPRINT, 15),
                                new ItemEntry(ModItems.M_60_BLUEPRINT, 15),
                                new ItemEntry(ModItems.SVD_BLUEPRINT, 15),
                                new ItemEntry(ModItems.M_98B_BLUEPRINT, 15),
                                new ItemEntry(ModItems.AWM_BLUEPRINT, 15),
                                new ItemEntry(ModItems.DEVOTION_BLUEPRINT, 15),
                                new ItemEntry(ModItems.INSIDIOUS_BLUEPRINT, 15),
                                new ItemEntry(ModItems.QBZ_191_BLUEPRINT, 15),

                                new ItemEntry(ModItems.AA_12_BLUEPRINT, 5),
                                new ItemEntry(ModItems.NTW_20_BLUEPRINT, 5),
                                new ItemEntry(ModItems.MINIGUN_BLUEPRINT, 5),
                                new ItemEntry(ModItems.JAVELIN_BLUEPRINT, 5),
                                new ItemEntry(ModItems.SECONDARY_CATACLYSM_BLUEPRINT, 5),
                                new ItemEntry(ModItems.AURELIA_SCEPTRE_BLUEPRINT, 5)
                        ))
        );
        pOutput.accept(containers("common"),
                LootTable.lootTable()
                        .withPool(multiItems(1, 0,
                                new ItemEntry(ModItems.EPIC_MATERIAL_PACK, 2),
                                new ItemEntry(ModItems.CEMENTED_CARBIDE_BLOCK, 2),
                                new ItemEntry(Items.EXPERIENCE_BOTTLE, 2)
                                        .setCount(4),
                                new ItemEntry(ModItems.RARE_MATERIAL_PACK, 4)
                                        .setCount(2),
                                new ItemEntry(ModItems.COMMON_MATERIAL_PACK, 6)
                                        .setCount(3),
                                new ItemEntry(ModItems.STEEL_BLOCK, 14),
                                new ItemEntry(Items.GOLD_BLOCK, 20),
                                new ItemEntry(ModItems.HANDGUN_AMMO, 6)
                                        .setCount(64),
                                new ItemEntry(ModItems.RIFLE_AMMO, 6)
                                        .setCount(64),
                                new ItemEntry(ModItems.SHOTGUN_AMMO, 6)
                                        .setCount(32),
                                new ItemEntry(ModItems.SNIPER_AMMO, 6)
                                        .setCount(32),
                                new ItemEntry(ModItems.HEAVY_AMMO, 6)
                                        .setCount(16),
                                new ItemEntry(Items.COAL_BLOCK, 30)
                                        .setCount(9))
                                .add(LootTableReference.lootTableReference(special("common/flags")).setWeight(40))
                                .add(LootTableReference.lootTableReference(special("common/blueprints")).setWeight(50))
                        )
        );

        pOutput.accept(special("common/flags"),
                LootTable.lootTable()
                        .withPool(singleItem(Items.RED_BANNER, 1))
                        .withPool(singleItem(Items.ORANGE_BANNER, 1))
                        .withPool(singleItem(Items.YELLOW_BANNER, 1))
                        .withPool(singleItem(Items.GREEN_BANNER, 1))
                        .withPool(singleItem(Items.CYAN_BANNER, 1))
                        .withPool(singleItem(Items.BLUE_BANNER, 1))
                        .withPool(singleItem(Items.PURPLE_BANNER, 1))
                        .withPool(singleItem(Items.PINK_BANNER, 1))
        );
        pOutput.accept(special("common/blueprints"),
                LootTable.lootTable()
                        .withPool(multiItems(1, 0,
                                new ItemEntry(ModItems.GLOCK_17_BLUEPRINT, 4),
                                new ItemEntry(ModItems.MP_443_BLUEPRINT, 4),
                                new ItemEntry(ModItems.M_1911_BLUEPRINT, 4),
                                new ItemEntry(ModItems.MARLIN_BLUEPRINT, 4),
                                new ItemEntry(ModItems.TASER_BLUEPRINT, 4),

                                new ItemEntry(ModItems.GLOCK_18_BLUEPRINT, 2),
                                new ItemEntry(ModItems.AK_47_BLUEPRINT, 2),
                                new ItemEntry(ModItems.QBZ_95_BLUEPRINT, 2),
                                new ItemEntry(ModItems.SKS_BLUEPRINT, 2),
                                new ItemEntry(ModItems.MOSIN_NAGANT_BLUEPRINT, 2),
                                new ItemEntry(ModItems.M_870_BLUEPRINT, 2),
                                new ItemEntry(ModItems.M_79_BLUEPRINT, 2),

                                new ItemEntry(ModItems.BOCEK_BLUEPRINT, 2),
                                new ItemEntry(ModItems.TRACHELIUM_BLUEPRINT, 2),
                                new ItemEntry(ModItems.VECTOR_BLUEPRINT, 2),
                                new ItemEntry(ModItems.DEVOTION_BLUEPRINT, 2),
                                new ItemEntry(ModItems.M_98B_BLUEPRINT, 2),
                                new ItemEntry(ModItems.AWM_BLUEPRINT, 2),

                                new ItemEntry(ModItems.AA_12_BLUEPRINT, 1),
                                new ItemEntry(ModItems.NTW_20_BLUEPRINT, 1),
                                new ItemEntry(ModItems.MINIGUN_BLUEPRINT, 1),
                                new ItemEntry(ModItems.JAVELIN_BLUEPRINT, 1),

                                new ItemEntry(ModItems.MK_42_BLUEPRINT, 1),
                                new ItemEntry(ModItems.MLE_1934_BLUEPRINT, 1)
                        ))
        );
    }

    public LootPool.Builder singleItem(ItemLike item, int weight) {
        return singleItem(item, 1, 0, weight, 0);
    }

    public LootPool.Builder singleItem(ItemLike item, float rolls, float bonus, int weight, int quality) {
        return LootPool.lootPool().setRolls(ConstantValue.exactly(rolls)).setBonusRolls(ConstantValue.exactly(bonus))
                .add(LootItem.lootTableItem(item).setWeight(weight).setQuality(quality));
    }

    public final LootPool.Builder multiItems(float rolls, float bonus, ItemEntry... triplet) {
        var builder = LootPool.lootPool().setRolls(ConstantValue.exactly(rolls)).setBonusRolls(ConstantValue.exactly(bonus));
        for (var t : triplet) {
            var entry = LootItem.lootTableItem(t.item).setWeight(t.weight).setQuality(t.quality);
            for (var c : t.conditions) {
                entry.when(c);
            }
            for (var f : t.functions) {
                entry.apply(f);
            }
            builder.add(entry);
        }
        return builder;
    }

    public static class ItemEntry {

        public ItemLike item;
        public int weight;
        public int quality;
        public List<LootItemCondition.Builder> conditions = Lists.newArrayList();
        public List<LootItemFunction.Builder> functions = Lists.newArrayList();

        public ItemEntry(ItemLike item, int weight) {
            this(item, weight, 0);
        }

        public ItemEntry(ItemLike item, int weight, int quality) {
            this.item = item;
            this.weight = weight;
            this.quality = quality;
        }

        public ItemEntry condition(LootItemCondition.Builder condition) {
            this.conditions.add(condition);
            return this;
        }

        public ItemEntry function(LootItemFunction.Builder function) {
            this.functions.add(function);
            return this;
        }

        public ItemEntry setCountBetween(int min, int max) {
            return this.function(SetItemCountFunction.setCount(UniformGenerator.between(min, max)));
        }

        public ItemEntry setCount(int count) {
            return this.function(SetItemCountFunction.setCount(ConstantValue.exactly(count)));
        }
    }
}
