package com.atsuishio.superbwarfare.init;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.api.event.RegisterContainersEvent;
import com.atsuishio.superbwarfare.item.ArmorPlate;
import com.atsuishio.superbwarfare.item.BatteryItem;
import com.atsuishio.superbwarfare.item.C4BombItem;
import com.atsuishio.superbwarfare.item.ElectricBaton;
import com.atsuishio.superbwarfare.item.common.container.LuckyContainerBlockItem;
import com.atsuishio.superbwarfare.item.common.container.SmallContainerBlockItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import team.reborn.energy.api.base.SimpleEnergyItem;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class ModTabs {

    public static void init() {
        ItemGroupEvents.MODIFY_ENTRIES_ALL.register(ModTabs::buildTabContentsVanilla);
    }

    public static final CreativeModeTab GUN_TAB = register("guns",
            FabricItemGroup.builder()
                    .title(Component.translatable("item_group.superbwarfare.guns"))
                    .icon(() -> new ItemStack(ModItems.TASER))
                    .displayItems((param, output) -> ModItems.GUN_ITEMS.forEach(gun -> {
                        output.accept(gun);
                        if (gun instanceof SimpleEnergyItem energyGun) {
                            var stack = new ItemStack(gun);
                            energyGun.setStoredEnergy(stack, energyGun.getEnergyCapacity(stack));
                            output.accept(stack);
                        }
                    }))
                    .build());

    public static final CreativeModeTab PERK_TAB = register("perk",
            FabricItemGroup.builder()
                    .title(Component.translatable("item_group.superbwarfare.perk"))
                    .icon(() -> new ItemStack(ModItems.AP_BULLET))
                    .displayItems((param, output) -> ModItems.PERK_ITEMS.values().forEach(output::accept))
                    .build());

    public static final CreativeModeTab AMMO_TAB = register("ammo",
            FabricItemGroup.builder()
                    .title(Component.translatable("item_group.superbwarfare.ammo"))
                    .icon(() -> new ItemStack(ModItems.SHOTGUN_AMMO_BOX))
                    .displayItems((param, output) -> {
                        ModItems.AMMO_ITEMS.forEach(ammo -> {
                            if (ammo != ModItems.POTION_MORTAR_SHELL) {
                                output.accept(ammo);

                                if (ammo == ModItems.C4_BOMB) {
                                    output.accept(C4BombItem.makeInstance());
                                }
                            }
                        });

                        param.holders().lookup(Registries.POTION)
                                .ifPresent(potion -> generatePotionEffectTypes(output, potion, ModItems.POTION_MORTAR_SHELL));
                    })
                    .build());

    public static final CreativeModeTab ITEM_TAB = register("item",
            FabricItemGroup.builder()
                    .title(Component.translatable("item_group.superbwarfare.item"))
                    .icon(() -> new ItemStack(ModItems.TARGET_DEPLOYER))
                    .displayItems((param, output) -> ModItems.ITEMS.forEach(item -> {
                        output.accept(item);
                        if (item == ModItems.ARMOR_PLATE) {
                            output.accept(ArmorPlate.getInfiniteInstance());
                        }
                        if (item instanceof BatteryItem batteryItem) {
                            output.accept(batteryItem.makeFullEnergyStack());
                        }
                        if (item instanceof ElectricBaton electricBaton) {
                            output.accept(electricBaton.makeFullEnergyStack());
                        }
                    }))
                    .build());

    public static final CreativeModeTab BLOCK_TAB = register("block",
            FabricItemGroup.builder()
                    .title(Component.translatable("item_group.superbwarfare.block"))
                    .icon(() -> new ItemStack(ModItems.SANDBAG))
                    .displayItems((param, output) -> ModItems.BLOCK_ITEMS.forEach(output::accept))
                    .build());

    public static final CreativeModeTab VEHICLE_TAB = register("vehicle",
            FabricItemGroup.builder()
                    .title(Component.translatable("item_group.superbwarfare.vehicle"))
                    .icon(() -> new ItemStack(ModItems.CONTAINER))
                    .displayItems((param, output) -> {
                        output.accept(ModItems.CROWBAR);
                        output.accept(ModItems.VEHICLE_ASSEMBLING_TABLE);

                        RegisterContainersEvent.CONTAINERS.forEach(output::accept);

                        output.accept(ModItems.LUCKY_CONTAINER);
                        LuckyContainerBlockItem.LUCKY_CONTAINERS.stream().map(Supplier::get).forEach(output::accept);

                        output.accept(ModItems.SMALL_CONTAINER);
                        SmallContainerBlockItem.SMALL_CONTAINERS.stream().map(Supplier::get).forEach(output::accept);
                    })
                    .build());

    public static void buildTabContentsVanilla(CreativeModeTab group, FabricItemGroupEntries entries) {
        if (BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.SPAWN_EGGS) == group) {
            entries.accept(ModItems.SENPAI_SPAWN_EGG);
        }
    }

    private static void generatePotionEffectTypes(CreativeModeTab.Output output, HolderLookup<Potion> potions, Item potionItem) {
        potions.listElements().filter(potion -> !potion.is(Potions.EMPTY_ID))
                .map(potion -> PotionUtils.setPotion(new ItemStack(potionItem), potion.value()))
                .forEach(output::accept);
    }

    private static CreativeModeTab register(String name, CreativeModeTab tab) {
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, Mod.loc(name), tab);
    }
}
