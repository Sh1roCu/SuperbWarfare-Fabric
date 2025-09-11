package com.atsuishio.superbwarfare.datagen;

import com.atsuishio.superbwarfare.init.ModDamageTypes;
import io.github.fabricators_of_create.porting_lib.data.ExistingFileHelper;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;

public class DataGenerators implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();
        ExistingFileHelper helper = ExistingFileHelper.withResourcesFromArg();

        pack.addProvider(ModBlockLootProvider::new);
        pack.addProvider(ModCustomLootProvider::new);
        pack.addProvider(ModRecipeProvider::new);
        pack.addProvider((DataProvider.Factory<DataProvider>) packOutput -> new ModBlockStateProvider(packOutput, helper));
        pack.addProvider((DataProvider.Factory<DataProvider>) packOutput -> new ModItemModelProvider(packOutput, helper));
        pack.addProvider(ModBlockTagProvider::new);
        pack.addProvider(ModItemTagProvider::new);
        pack.addProvider(ModEntityTypeTagProvider::new);
        pack.addProvider(ModDamageTypeTagProvider::new);
        pack.addProvider(ModAdvancementProvider::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.DAMAGE_TYPE, ModDamageTypes::bootstrap);
    }
}
