package com.atsuishio.superbwarfare.recipe;

import com.atsuishio.superbwarfare.init.ModPotions;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistry;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;

public class ModPotionRecipes {

    public static void register() {
//        ItemStack water = potion(Potions.WATER);
//        ItemStack shock = potion(ModPotions.SHOCK);
//        ItemStack strongShock = potion(ModPotions.STRONG_SHOCK);
//        ItemStack longShock = potion(ModPotions.LONG_SHOCK);
        FabricBrewingRecipeRegistry.registerPotionRecipe(Potions.WATER, Ingredient.of(Items.LIGHTNING_ROD), ModPotions.SHOCK);
        FabricBrewingRecipeRegistry.registerPotionRecipe(ModPotions.SHOCK, Ingredient.of(Items.GLOWSTONE_DUST), ModPotions.STRONG_SHOCK);
        FabricBrewingRecipeRegistry.registerPotionRecipe(ModPotions.SHOCK, Ingredient.of(Items.REDSTONE), ModPotions.LONG_SHOCK);
    }

    private static ItemStack potion(Potion potion) {
        return PotionUtils.setPotion(Items.POTION.getDefaultInstance(), potion);
    }
}
