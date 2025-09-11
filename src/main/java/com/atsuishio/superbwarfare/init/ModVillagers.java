package com.atsuishio.superbwarfare.init;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.item.common.container.SmallContainerBlockItem;
import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class ModVillagers {

    public static void init() {
        addCustomTrades();
        addWandererTrade();
    }

    public static final PoiType ARMORY_POI = registerPoi("armory",
            ImmutableSet.copyOf(ModBlocks.REFORGING_TABLE.getStateDefinition().getPossibleStates()), 1, 1);

    public static final VillagerProfession ARMORY = registerPro("armory",
            new VillagerProfession("armory", holder -> holder.value() == ARMORY_POI, holder -> holder.value() == ARMORY_POI,
                    ImmutableSet.of(), ImmutableSet.of(), null));

    private static PoiType registerPoi(String name, Set<BlockState> states, int ticketCount, int searchDistance) {
        return PointOfInterestHelper.register(Mod.loc(name), ticketCount, searchDistance, states);
    }

    private static VillagerProfession registerPro(String name, VillagerProfession pro) {
        return Registry.register(BuiltInRegistries.VILLAGER_PROFESSION, Mod.loc(name), pro);
    }

    public static void addCustomTrades() {
        // ARMORY
        // 等级 1 交易
        TradeOfferHelper.registerVillagerOffers(ModVillagers.ARMORY, 1, trades -> {
            trades.add(new BasicItemListing(new ItemStack(ModItems.TASER_BLUEPRINT),
                    new ItemStack(Items.EMERALD, 2), 16, 5, 0.05f));

            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 1),
                    new ItemStack(ModItems.HANDGUN_AMMO, 20), 16, 1, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 1),
                    new ItemStack(ModItems.RIFLE_AMMO, 15), 16, 1, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 1),
                    new ItemStack(ModItems.SNIPER_AMMO, 8), 16, 1, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 1),
                    new ItemStack(ModItems.SHOTGUN_AMMO, 8), 16, 1, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 1),
                    new ItemStack(ModItems.HEAVY_AMMO, 6), 32, 1, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 1),
                    new ItemStack(ModItems.SMALL_SHELL, 4), 32, 1, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 1),
                    new ItemStack(ModItems.BLU_43_MINE, 4), 32, 1, 0.05f));

            trades.add(new BasicItemListing(new ItemStack(ModItems.HANDGUN_AMMO, 40),
                    new ItemStack(Items.EMERALD, 1), 32, 2, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(ModItems.RIFLE_AMMO, 30),
                    new ItemStack(Items.EMERALD, 1), 32, 2, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(ModItems.SNIPER_AMMO, 16),
                    new ItemStack(Items.EMERALD, 1), 32, 2, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(ModItems.SHOTGUN_AMMO, 16),
                    new ItemStack(Items.EMERALD, 1), 32, 2, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(ModItems.HEAVY_AMMO, 12),
                    new ItemStack(Items.EMERALD, 1), 64, 2, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(ModItems.SMALL_SHELL, 8),
                    new ItemStack(Items.EMERALD, 1), 64, 2, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(ModItems.BLU_43_MINE, 8),
                    new ItemStack(Items.EMERALD, 1), 64, 2, 0.05f));
        });
        // 等级 2 交易
        TradeOfferHelper.registerVillagerOffers(ModVillagers.ARMORY, 2, trades -> {
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 10),
                    new ItemStack(ModItems.STEEL_MATERIALS.action()), 12, 5, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 8),
                    new ItemStack(ModItems.STEEL_MATERIALS.barrel()), 12, 5, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 6),
                    new ItemStack(ModItems.STEEL_MATERIALS.trigger()), 12, 5, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 8),
                    new ItemStack(ModItems.STEEL_MATERIALS.spring()), 12, 5, 0.05f));

            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 16),
                    new ItemStack(ModItems.MARLIN_BLUEPRINT), 8, 25, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 16),
                    new ItemStack(ModItems.GLOCK_17_BLUEPRINT), 8, 15, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 16),
                    new ItemStack(ModItems.M_1911_BLUEPRINT), 8, 15, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 16),
                    new ItemStack(ModItems.MP_443_BLUEPRINT), 8, 15, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 16),
                    new ItemStack(ModItems.TASER_BLUEPRINT), 8, 15, 0.05f));
        });
        // 等级 3 交易
        TradeOfferHelper.registerVillagerOffers(ModVillagers.ARMORY, 3, trades -> {
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 3),
                    new ItemStack(ModItems.HANDGUN_AMMO_BOX, 2), 8, 5, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 2),
                    new ItemStack(ModItems.RIFLE_AMMO_BOX, 1), 8, 5, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 3),
                    new ItemStack(ModItems.SNIPER_AMMO_BOX, 1), 8, 5, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 3),
                    new ItemStack(ModItems.SHOTGUN_AMMO_BOX, 1), 8, 5, 0.05f));

            trades.add(new BasicItemListing(new ItemStack(ModItems.HANDGUN_AMMO_BOX, 4),
                    new ItemStack(Items.EMERALD, 3), 16, 5, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(ModItems.RIFLE_AMMO_BOX, 1),
                    new ItemStack(Items.EMERALD, 1), 16, 5, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(ModItems.SNIPER_AMMO_BOX, 2),
                    new ItemStack(Items.EMERALD, 3), 16, 5, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(ModItems.SHOTGUN_AMMO_BOX, 2),
                    new ItemStack(Items.EMERALD, 3), 16, 5, 0.05f));

            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 16),
                    new ItemStack(ModItems.CEMENTED_CARBIDE_MATERIALS.barrel()), 12, 10, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 20),
                    new ItemStack(ModItems.CEMENTED_CARBIDE_MATERIALS.action()), 10, 10, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 16),
                    new ItemStack(ModItems.CEMENTED_CARBIDE_MATERIALS.spring()), 10, 10, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 12),
                    new ItemStack(ModItems.CEMENTED_CARBIDE_MATERIALS.trigger()), 10, 10, 0.05f));

            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 32),
                    new ItemStack(ModItems.M_4_BLUEPRINT), 10, 25, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 32),
                    new ItemStack(ModItems.M_79_BLUEPRINT), 10, 25, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 32),
                    new ItemStack(ModItems.AK_47_BLUEPRINT), 10, 25, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 32),
                    new ItemStack(ModItems.GLOCK_18_BLUEPRINT), 10, 25, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 32),
                    new ItemStack(ModItems.SKS_BLUEPRINT), 10, 25, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 32),
                    new ItemStack(ModItems.M_870_BLUEPRINT), 10, 25, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 32),
                    new ItemStack(ModItems.K_98_BLUEPRINT), 10, 25, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 32),
                    new ItemStack(ModItems.MOSIN_NAGANT_BLUEPRINT), 10, 25, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 32),
                    new ItemStack(ModItems.RPG_BLUEPRINT), 10, 25, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 32),
                    new ItemStack(ModItems.HK_416_BLUEPRINT), 10, 25, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 32),
                    new ItemStack(ModItems.QBZ_95_BLUEPRINT), 10, 25, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 32),
                    new ItemStack(ModItems.AK_12_BLUEPRINT), 10, 25, 0.05f));
        });
        // 等级 4 交易
        TradeOfferHelper.registerVillagerOffers(ModVillagers.ARMORY, 4, trades -> {
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 2),
                    new ItemStack(ModItems.GRENADE_40MM, 1), 16, 5, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 2),
                    new ItemStack(ModItems.HAND_GRENADE, 1), 16, 5, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 2),
                    new ItemStack(ModItems.RGO_GRENADE, 1), 16, 5, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 3),
                    new ItemStack(ModItems.MORTAR_SHELL, 1), 16, 5, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 4),
                    new ItemStack(ModItems.CLAYMORE_MINE, 1), 16, 5, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 4),
                    new ItemStack(ModItems.C4_BOMB, 1), 16, 5, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 4),
                    new ItemStack(ModItems.RPG_ROCKET_TBG, 1), 16, 5, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 4),
                    new ItemStack(ModItems.TM_62, 1), 16, 5, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 3),
                    new ItemStack(ModItems.SMALL_ROCKET, 1), 16, 5, 0.05f));


            trades.add(new BasicItemListing(new ItemStack(ModItems.GRENADE_40MM, 1),
                    new ItemStack(Items.EMERALD, 1), 32, 5, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(ModItems.HAND_GRENADE, 1),
                    new ItemStack(Items.EMERALD, 1), 32, 5, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(ModItems.RGO_GRENADE, 1),
                    new ItemStack(Items.EMERALD, 1), 32, 5, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(ModItems.MORTAR_SHELL, 3),
                    new ItemStack(Items.EMERALD, 2), 32, 5, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(ModItems.CLAYMORE_MINE, 1),
                    new ItemStack(Items.EMERALD, 2), 32, 5, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(ModItems.C4_BOMB, 1),
                    new ItemStack(Items.EMERALD, 2), 32, 5, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(ModItems.RPG_ROCKET_TBG, 1),
                    new ItemStack(Items.EMERALD, 2), 32, 5, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(ModItems.TM_62, 1),
                    new ItemStack(Items.EMERALD, 2), 32, 5, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(ModItems.SMALL_ROCKET, 3),
                    new ItemStack(Items.EMERALD, 2), 32, 5, 0.05f));

            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 22),
                    new ItemStack(getItemHolder("poisonous_bullet"), 1), 4, 10, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 24),
                    new ItemStack(getItemHolder("subsistence"), 1), 4, 10, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 25),
                    new ItemStack(getItemHolder("kill_clip"), 1), 4, 10, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 26),
                    new ItemStack(getItemHolder("gutshot_straight"), 1), 4, 10, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 22),
                    new ItemStack(getItemHolder("head_seeker"), 1), 4, 10, 0.05f));
        });
        // 等级 5 交易
        TradeOfferHelper.registerVillagerOffers(ModVillagers.ARMORY, 5, trades -> {
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 34),
                    new ItemStack(getItemHolder("silver_bullet"), 1), 4, 15, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 30),
                    new ItemStack(getItemHolder("field_doctor"), 1), 4, 15, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 34),
                    new ItemStack(getItemHolder("heal_clip"), 1), 4, 15, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 30),
                    new ItemStack(getItemHolder("killing_tally"), 1), 4, 15, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 34),
                    new ItemStack(getItemHolder("fourth_times_charm"), 1), 4, 15, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 48),
                    new ItemStack(getItemHolder("monster_hunter"), 1), 4, 25, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 40),
                    new ItemStack(getItemHolder("vorpal_weapon"), 1), 4, 25, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 42),
                    new ItemStack(getItemHolder("magnificent_howl"), 1), 4, 25, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 64),
                    new ItemStack(getItemHolder("fair_means"), 1), 4, 25, 0.05f));

            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 64),
                    new ItemStack(ModItems.HUNTING_RIFLE_BLUEPRINT), 10, 30, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 64),
                    new ItemStack(ModItems.RPK_BLUEPRINT), 10, 30, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 64),
                    new ItemStack(ModItems.VECTOR_BLUEPRINT), 10, 30, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 64),
                    new ItemStack(ModItems.MK_14_BLUEPRINT), 10, 30, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 64),
                    new ItemStack(ModItems.M_60_BLUEPRINT), 10, 30, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 64),
                    new ItemStack(ModItems.SVD_BLUEPRINT), 10, 30, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 64),
                    new ItemStack(ModItems.M_98B_BLUEPRINT), 10, 30, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 64),
                    new ItemStack(ModItems.AWM_BLUEPRINT), 10, 30, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 64),
                    new ItemStack(ModItems.DEVOTION_BLUEPRINT), 10, 30, 0.05f));

            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 8),
                    new ItemStack(ModItems.HE_5_INCHES, 1), 8, 10, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 8),
                    new ItemStack(ModItems.AP_5_INCHES, 1), 8, 10, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 8),
                    new ItemStack(ModItems.CM_5_INCHES, 1), 8, 10, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 8),
                    new ItemStack(ModItems.MEDIUM_ROCKET_HE, 1), 8, 10, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 8),
                    new ItemStack(ModItems.MEDIUM_ROCKET_AP, 1), 8, 10, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 8),
                    new ItemStack(ModItems.MEDIUM_ROCKET_CM, 1), 8, 10, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 12),
                    new ItemStack(ModItems.JAVELIN_MISSILE, 1), 8, 10, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 12),
                    new ItemStack(ModItems.WIRE_GUIDE_MISSILE, 1), 8, 10, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 16),
                    new ItemStack(ModItems.AGM, 1), 8, 10, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 16),
                    new ItemStack(ModItems.MEDIUM_AERIAL_BOMB, 1), 8, 10, 0.05f));

            trades.add(new BasicItemListing(new ItemStack(ModItems.HE_5_INCHES, 1),
                    new ItemStack(Items.EMERALD, 4), 32, 4, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(ModItems.AP_5_INCHES, 1),
                    new ItemStack(Items.EMERALD, 4), 32, 4, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(ModItems.CM_5_INCHES, 1),
                    new ItemStack(Items.EMERALD, 4), 32, 4, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(ModItems.MEDIUM_ROCKET_HE, 1),
                    new ItemStack(Items.EMERALD, 4), 32, 4, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(ModItems.MEDIUM_ROCKET_AP, 1),
                    new ItemStack(Items.EMERALD, 4), 32, 4, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(ModItems.MEDIUM_ROCKET_CM, 1),
                    new ItemStack(Items.EMERALD, 4), 32, 4, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(ModItems.JAVELIN_MISSILE, 1),
                    new ItemStack(Items.EMERALD, 6), 32, 4, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(ModItems.WIRE_GUIDE_MISSILE, 1),
                    new ItemStack(Items.EMERALD, 6), 32, 4, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(ModItems.AGM, 1),
                    new ItemStack(Items.EMERALD, 8), 32, 4, 0.05f));
            trades.add(new BasicItemListing(new ItemStack(ModItems.MEDIUM_AERIAL_BOMB, 1),
                    new ItemStack(Items.EMERALD, 8), 32, 4, 0.05f));
        });
    }

    private static Holder<Item> getItemHolder(String name) {
        return BuiltInRegistries.ITEM.wrapAsHolder(BuiltInRegistries.ITEM.get(Mod.loc(name)));
    }

    public static void addWandererTrade() {
        TradeOfferHelper.registerWanderingTraderOffers(1, rareTrades -> {
            rareTrades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 16),
                    SmallContainerBlockItem.createInstance(Mod.loc("containers/blueprints")), 10, 0, 0.05f));
            rareTrades.add(new BasicItemListing(new ItemStack(Items.EMERALD, 10),
                    SmallContainerBlockItem.createInstance(Mod.loc("containers/common")), 10, 0, 0.05f));
        });
    }

    public static class BasicItemListing implements VillagerTrades.ItemListing {

        protected final ItemStack price;
        protected final ItemStack price2;
        protected final ItemStack forSale;
        protected final int maxTrades;
        protected final int xp;
        protected final float priceMult;

        public BasicItemListing(ItemStack price, ItemStack price2, ItemStack forSale, int maxTrades, int xp, float priceMult) {
            this.price = price;
            this.price2 = price2;
            this.forSale = forSale;
            this.maxTrades = maxTrades;
            this.xp = xp;
            this.priceMult = priceMult;
        }

        public BasicItemListing(ItemStack price, ItemStack forSale, int maxTrades, int xp, float priceMult) {
            this(price, ItemStack.EMPTY, forSale, maxTrades, xp, priceMult);
        }

        public BasicItemListing(int emeralds, ItemStack forSale, int maxTrades, int xp, float mult) {
            this(new ItemStack(Items.EMERALD, emeralds), forSale, maxTrades, xp, mult);
        }

        public BasicItemListing(int emeralds, ItemStack forSale, int maxTrades, int xp) {
            this(new ItemStack(Items.EMERALD, emeralds), forSale, maxTrades, xp, 1);
        }

        @Nullable
        @Override
        public MerchantOffer getOffer(Entity pTrader, RandomSource pRandom) {
            return new MerchantOffer(price, price2, forSale, maxTrades, xp, priceMult);
        }
    }
}
