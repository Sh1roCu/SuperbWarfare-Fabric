package com.atsuishio.superbwarfare.init;

import cn.sh1rocu.superbwarfare.api.extension.CustomRatityItem;
import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.entity.projectile.MediumRocketEntity;
import com.atsuishio.superbwarfare.item.*;
import com.atsuishio.superbwarfare.item.armor.*;
import com.atsuishio.superbwarfare.item.common.BlueprintItem;
import com.atsuishio.superbwarfare.item.common.MaterialPack;
import com.atsuishio.superbwarfare.item.common.MedicalKitItem;
import com.atsuishio.superbwarfare.item.common.ammo.*;
import com.atsuishio.superbwarfare.item.common.container.ContainerBlockItem;
import com.atsuishio.superbwarfare.item.common.container.LuckyContainerBlockItem;
import com.atsuishio.superbwarfare.item.common.container.SmallContainerBlockItem;
import com.atsuishio.superbwarfare.item.curio.DogTagItem;
import com.atsuishio.superbwarfare.item.curio.IffItem;
import com.atsuishio.superbwarfare.item.curio.ParachuteItem;
import com.atsuishio.superbwarfare.item.gun.handgun.*;
import com.atsuishio.superbwarfare.item.gun.heavy.Ntw20Item;
import com.atsuishio.superbwarfare.item.gun.launcher.JavelinItem;
import com.atsuishio.superbwarfare.item.gun.launcher.M79Item;
import com.atsuishio.superbwarfare.item.gun.launcher.RpgItem;
import com.atsuishio.superbwarfare.item.gun.launcher.SecondaryCataclysm;
import com.atsuishio.superbwarfare.item.gun.machinegun.*;
import com.atsuishio.superbwarfare.item.gun.rifle.*;
import com.atsuishio.superbwarfare.item.gun.shotgun.Aa12Item;
import com.atsuishio.superbwarfare.item.gun.shotgun.HomemadeShotgunItem;
import com.atsuishio.superbwarfare.item.gun.shotgun.M870Item;
import com.atsuishio.superbwarfare.item.gun.smg.Mp5Item;
import com.atsuishio.superbwarfare.item.gun.smg.VectorItem;
import com.atsuishio.superbwarfare.item.gun.sniper.*;
import com.atsuishio.superbwarfare.item.gun.special.BocekItem;
import com.atsuishio.superbwarfare.item.gun.special.TaserItem;
import com.atsuishio.superbwarfare.perk.Perk;
import com.atsuishio.superbwarfare.tiers.ModItemTier;
import com.atsuishio.superbwarfare.tools.Ammo;
import com.atsuishio.superbwarfare.tools.RarityTool;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DispenserBlock;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class ModItems {

    public static void init() {
        registerPerkItems();
    }

    /**
     * guns
     */
    public static final Item TASER = registerGun("taser", new TaserItem());
    public static final Item GLOCK_17 = registerGun("glock_17", new Glock17Item());
    public static final Item GLOCK_18 = registerGun("glock_18", new Glock18Item());
    public static final Item MP_443 = registerGun("mp_443", new Mp443Item());
    public static final Item M_1911 = registerGun("m_1911", new M1911Item());
    public static final Item HOMEMADE_SHOTGUN = registerGun("homemade_shotgun", new HomemadeShotgunItem());
    public static final Item TRACHELIUM = registerGun("trachelium", new Trachelium());
    public static final Item MP_5 = registerGun("mp_5", new Mp5Item());
    public static final Item VECTOR = registerGun("vector", new VectorItem());
    public static final Item AK_47 = registerGun("ak_47", new AK47Item());
    public static final Item AK_12 = registerGun("ak_12", new AK12Item());
    public static final Item SKS = registerGun("sks", new SksItem());
    public static final Item M_4 = registerGun("m_4", new M4Item());
    public static final Item HK_416 = registerGun("hk_416", new Hk416Item());
    public static final Item QBZ_95 = registerGun("qbz_95", new Qbz95Item());
    public static final Item QBZ_191 = registerGun("qbz_191", new Qbz191Item());
    public static final Item INSIDIOUS = registerGun("insidious", new InsidiousItem());
    public static final Item MK_14 = registerGun("mk_14", new Mk14Item());
    public static final Item MARLIN = registerGun("marlin", new MarlinItem());
    public static final Item K_98 = registerGun("k_98", new K98Item());
    public static final Item MOSIN_NAGANT = registerGun("mosin_nagant", new MosinNagantItem());
    public static final Item SVD = registerGun("svd", new SvdItem());

    public static final Item AWM = registerGun("awm", new AwmItem());
    public static final Item M_98B = registerGun("m_98b", new M98bItem());
    public static final Item SENTINEL = registerGun("sentinel", new SentinelItem());
    public static final Item HUNTING_RIFLE = registerGun("hunting_rifle", new HuntingRifleItem());
    public static final Item NTW_20 = registerGun("ntw_20", new Ntw20Item(RarityTool.LEGENDARY));
    public static final Item M_870 = registerGun("m_870", new M870Item());
    public static final Item AA_12 = registerGun("aa_12", new Aa12Item(RarityTool.LEGENDARY));
    public static final Item DEVOTION = registerGun("devotion", new DevotionItem());
    public static final Item RPK = registerGun("rpk", new RpkItem());
    public static final Item M_60 = registerGun("m_60", new M60Item());
    public static final Item M_2_HB = registerGun("m_2_hb", new M2HBItem());
    public static final Item MINIGUN = registerGun("minigun", new MinigunItem(RarityTool.LEGENDARY));
    public static final Item M_79 = registerGun("m_79", new M79Item());
    public static final Item SECONDARY_CATACLYSM = registerGun("secondary_cataclysm", new SecondaryCataclysm(RarityTool.LEGENDARY));
    public static final Item RPG = registerGun("rpg", new RpgItem());
    public static final Item JAVELIN = registerGun("javelin", new JavelinItem(RarityTool.LEGENDARY));
    public static final Item AURELIA_SCEPTRE = registerGun("aurelia_sceptre", new AureliaSceptre(RarityTool.LEGENDARY));
    public static final Item BOCEK = registerGun("bocek", new BocekItem());

    /**
     * Ammo
     */
    public static final Item HANDGUN_AMMO = registerAmmo("handgun_ammo", new AmmoSupplierItem(Ammo.HANDGUN, 1, new Item.Properties()));
    public static final Item RIFLE_AMMO = registerAmmo("rifle_ammo", new AmmoSupplierItem(Ammo.RIFLE, 1, new Item.Properties()));
    public static final Item SNIPER_AMMO = registerAmmo("sniper_ammo", new AmmoSupplierItem(Ammo.SNIPER, 1, new Item.Properties()));
    public static final Item SHOTGUN_AMMO = registerAmmo("shotgun_ammo", new AmmoSupplierItem(Ammo.SHOTGUN, 1, new Item.Properties()));
    public static final Item HEAVY_AMMO = registerAmmo("heavy_ammo", new AmmoSupplierItem(Ammo.HEAVY, 1, new Item.Properties()));
    public static final Item HANDGUN_AMMO_BOX = registerAmmo("handgun_ammo_box", new HandgunAmmoBox());
    public static final Item RIFLE_AMMO_BOX = registerAmmo("rifle_ammo_box", new RifleAmmoBox());
    public static final Item SNIPER_AMMO_BOX = registerAmmo("sniper_ammo_box", new SniperAmmoBox());
    public static final Item SHOTGUN_AMMO_BOX = registerAmmo("shotgun_ammo_box", new ShotgunAmmoBox());
    public static final Item CREATIVE_AMMO_BOX = registerAmmo("creative_ammo_box", new CreativeAmmoBox());
    public static final Item AMMO_BOX = registerAmmo("ammo_box", new AmmoBox());
    public static final Item TASER_ELECTRODE = registerAmmo("taser_electrode", new Item(new Item.Properties()));
    public static final Item GRENADE_40MM = registerAmmo("grenade_40mm", new Item(new Item.Properties()));
    public static final Item JAVELIN_MISSILE = registerAmmo("javelin_missile", new Item(new Item.Properties()));
    public static final Item MORTAR_SHELL = registerAmmo("mortar_shell", new MortarShell());
    public static final Item POTION_MORTAR_SHELL = registerAmmo("potion_mortar_shell", new PotionMortarShell());
    public static final Item RPG_ROCKET_STANDARD = registerAmmo("rpg_rocket_standard", new RpgRocketStandard());
    public static final Item RPG_ROCKET_TBG = registerAmmo("rpg_rocket_tbg", new RpgRocketTBG());
    public static final Item LUNGE_MINE = registerAmmo("lunge_mine", new LungeMine());
    public static final Item HE_5_INCHES = registerAmmo("he_5_inches", new CannonShellItem(new Item.Properties().rarity(Rarity.RARE)));
    public static final Item AP_5_INCHES = registerAmmo("ap_5_inches", new CannonShellItem(new Item.Properties().rarity(Rarity.RARE)));
    public static final Item CM_5_INCHES = registerAmmo("cm_5_inches", new CannonShellItem(new Item.Properties().rarity(Rarity.RARE)));
    public static final Item GS_5_INCHES = registerAmmo("gs_5_inches", new CannonShellItem(new Item.Properties().rarity(Rarity.RARE)));
    public static final Item HAND_GRENADE = registerAmmo("hand_grenade", new HandGrenade());
    public static final Item RGO_GRENADE = registerAmmo("rgo_grenade", new RgoGrenade());
    public static final Item M18_SMOKE_GRENADE = registerAmmo("m18_smoke_grenade", new M18SmokeGrenade());
    public static final Item CLAYMORE_MINE = registerAmmo("claymore_mine", new ClaymoreMine());
    public static final Item TM_62 = registerAmmo("tm_62", new Tm62Item());
    public static final Item PTKM_1R = registerAmmo("ptkm_1r", new Ptkm1rItem());
    public static final Item C4_BOMB = registerAmmo("c4_bomb", new C4BombItem());
    public static final Item BLU_43_MINE = registerAmmo("blu_43_mine", new Blu43MineItem());
    public static final Item SMALL_SHELL = registerAmmo("small_shell", new SmallShellItem());
    public static final Item SMALL_ROCKET = registerAmmo("small_rocket", new SmallRocketItem());
    public static final Item MEDIUM_ROCKET_AP = registerAmmo("medium_rocket_ap", new MediumRocketItem(500, 6, 100, 0, 0, MediumRocketEntity.Type.AP, 0));
    public static final Item MEDIUM_ROCKET_HE = registerAmmo("medium_rocket_he", new MediumRocketItem(200, 12, 200, 0.2f, 40, MediumRocketEntity.Type.HE, 0));
    public static final Item MEDIUM_ROCKET_CM = registerAmmo("medium_rocket_cm", new MediumRocketItem(300, 12, 300, 0, 0, MediumRocketEntity.Type.CM, 20));
    public static final Item WIRE_GUIDE_MISSILE = registerAmmo("wire_guide_missile", new WireGuideMissileItem());
    public static final Item AGM = registerAmmo("agm", new AgmItem());
    public static final Item SWARM_DRONE = registerAmmo("swarm_drone", new SwarmDroneItem());
    public static final Item MEDIUM_AERIAL_BOMB = registerAmmo("medium_aerial_bomb", new MediumAerialBombItem());
    public static final Item BEAM_TEST = registerAmmo("beam_test", new BeamTest());

    /**
     * items
     */
    public static final Item SENPAI_SPAWN_EGG = registerItem("senpai_spawn_egg", new SpawnEggItem(ModEntities.SENPAI, -11584987, -14014413, new Item.Properties()));
    public static final Item ANCIENT_CPU = registerItem("ancient_cpu", new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final Item PROPELLER = registerItem("propeller", new Item(new Item.Properties()));
    public static final Item LARGE_PROPELLER = registerItem("large_propeller", new Item(new Item.Properties()));
    public static final Item MOTOR = registerItem("motor", new Item(new Item.Properties()));
    public static final Item LARGE_MOTOR = registerItem("large_motor", new Item(new Item.Properties()));
    public static final Item WHEEL = registerItem("wheel", new Item(new Item.Properties()));
    public static final Item TRACK = registerItem("track", new Item(new Item.Properties()));
    public static final Item DRONE = registerItem("drone", new Drone());

    public static final Item MONITOR = registerItem("monitor", new Monitor());
    public static final Item ARTILLERY_INDICATOR = registerItem("artillery_indicator", new ArtilleryIndicator());

    public static final Item DETONATOR = registerItem("detonator", new Detonator());
    public static final Item TARGET_DEPLOYER = registerItem("target_deployer", new TargetDeployer());
    public static final Item DPS_GENERATOR_DEPLOYER = registerItem("dps_generator_deployer", new DPSGeneratorDeployer());
    public static final Item KNIFE = registerItem("knife", new SwordItem(ModItemTier.STEEL, 0, -1.8f, new Item.Properties().durability(1200)));
    public static final Item HAMMER = registerItem("hammer", new Hammer(Tiers.IRON, 11, -3.2f, new Item.Properties().durability(400)));
    public static final Item GOLDEN_HAMMER = registerItem("golden_hammer", new Hammer(Tiers.GOLD, 11, -3.2f, new Item.Properties().durability(150)));
    public static final Item STEEL_HAMMER = registerItem("steel_hammer", new Hammer(ModItemTier.STEEL, 9, -3.2f, new Item.Properties().durability(600)));
    public static final Item DIAMOND_HAMMER = registerItem("diamond_hammer", new Hammer(Tiers.DIAMOND, 12, -3.2f, new Item.Properties().durability(1500)));
    public static final Item CEMENTED_CARBIDE_HAMMER = registerItem("cemented_carbide_hammer", new Hammer(ModItemTier.CEMENTED_CARBIDE, 8, -3.2f, new Item.Properties().durability(2000)));
    public static final Item NETHERITE_HAMMER = registerItem("netherite_hammer", new NetheriteHammer());
    public static final Item T_BATON = registerItem("t_baton", new TBaton());
    public static final Item ELECTRIC_BATON = registerItem("electric_baton", new ElectricBaton());
    public static final Item STEEL_PIPE = registerItem("steel_pipe", new SteelPipe());
    public static final Item CROWBAR = registerItem("crowbar", new Crowbar());
    public static final Item DEFUSER = registerItem("defuser", new Defuser());
    public static final Item ARMOR_PLATE = registerItem("armor_plate", new ArmorPlate());

    public static final Item RU_HELMET_6B47 = registerItem("ru_helmet_6b47", new RuHelmet6b47());
    public static final Item RU_CHEST_6B43 = registerItem("ru_chest_6b43", new RuChest6b43());
    public static final Item US_HELMET_PASTG = registerItem("us_helmet_pastg", new UsHelmetPastg());
    public static final Item US_CHEST_IOTV = registerItem("us_chest_iotv", new UsChestIotv());
    public static final Item GE_HELMET_M_35 = registerItem("ge_helmet_m_35", new GeHelmetM35());
    public static final Item PARACHUTE = registerItem("parachute", new ParachuteItem());
    public static final Item MORTAR_DEPLOYER = registerItem("mortar_deployer", new MortarDeployer());
    public static final Item MORTAR_BARREL = registerItem("mortar_barrel", new Item(new Item.Properties()));
    public static final Item MORTAR_BASE_PLATE = registerItem("mortar_base_plate", new Item(new Item.Properties()));
    public static final Item MORTAR_BIPOD = registerItem("mortar_bipod", new Item(new Item.Properties()));
    public static final Item SEEKER = registerItem("seeker", new Item(new Item.Properties()));
    public static final Item MISSILE_ENGINE = registerItem("missile_engine", new Item(new Item.Properties()));
    public static final Item FUSEE = registerItem("fusee", new Item(new Item.Properties()));
    public static final Item PRIMER = registerItem("primer", new Item(new Item.Properties()));
    public static final Item AP_HEAD = registerItem("ap_head", new Item(new Item.Properties()));
    public static final Item HE_HEAD = registerItem("he_head", new Item(new Item.Properties()));
    public static final Item CM_HEAD = registerItem("cm_head", new Item(new Item.Properties()));
    public static final Item GS_HEAD = registerItem("gs_head", new Item(new Item.Properties()));
    public static final Item CANNON_CORE = registerItem("cannon_core", new Item(new Item.Properties()));
    public static final Item COPPER_PLATE = registerItem("copper_plate", new Item(new Item.Properties()));
    public static final Item STEEL_INGOT = registerItem("steel_ingot", new Item(new Item.Properties()));
    public static final Item LEAD_INGOT = registerItem("lead_ingot", new Item(new Item.Properties()));
    public static final Item SILVER_INGOT = registerItem("silver_ingot", new Item(new Item.Properties()));
    public static final Item TUNGSTEN_INGOT = registerItem("tungsten_ingot", new Item(new Item.Properties()));
    public static final Item CEMENTED_CARBIDE_INGOT = registerItem("cemented_carbide_ingot", new Item(new Item.Properties()));
    public static final Item HIGH_ENERGY_EXPLOSIVES = registerItem("high_energy_explosives", new Item(new Item.Properties()));
    public static final Item GRAIN = registerItem("grain", new Item(new Item.Properties()));
    public static final Item IRON_POWDER = registerItem("iron_powder", new Item(new Item.Properties()));
    public static final Item TUNGSTEN_POWDER = registerItem("tungsten_powder", new Item(new Item.Properties()));
    public static final Item COAL_POWDER = registerItem("coal_powder", new Item(new Item.Properties()));
    public static final Item COAL_IRON_POWDER = registerItem("coal_iron_powder", new Item(new Item.Properties()));
    public static final Item RAW_CEMENTED_CARBIDE_POWDER = registerItem("raw_cemented_carbide_powder", new Item(new Item.Properties()));
    public static final Item GALENA = registerItem("galena", new Item(new Item.Properties()));
    public static final Item SCHEELITE = registerItem("scheelite", new Item(new Item.Properties()));
    public static final Item RAW_SILVER = registerItem("raw_silver", new Item(new Item.Properties()));
    public static final Item DOG_TAG = registerItem("dog_tag", new DogTagItem());
    public static final Item IFF = registerItem("iff", new IffItem());
    public static final Item CELL = registerItem("cell", new BatteryItem(24000, new Item.Properties()));
    public static final Item BATTERY = registerItem("battery", new BatteryItem(100000, new Item.Properties()));
    public static final Item SMALL_BATTERY_PACK = registerItem("small_battery_pack", new BatteryItem(500000, new Item.Properties()));
    public static final Item MEDIUM_BATTERY_PACK = registerItem("medium_battery_pack", new BatteryItem(5000000, new Item.Properties()));
    public static final Item LARGE_BATTERY_PACK = registerItem("large_battery_pack", new BatteryItem(20000000, new Item.Properties()));
    public static final Item BEAST = registerItem("beast", new Beast(RarityTool.LEGENDARY));
    public static final Item TRANSCRIPT = registerItem("transcript", new Transcript());
    public static final Item FIRING_PARAMETERS = registerItem("firing_parameters", new FiringParameters());
    public static final Item MEDICAL_KIT = registerItem("medical_kit", new MedicalKitItem());
    public static final Item VEHICLE_DAMAGE_ANALYZER = registerItem("vehicle_damage_analyzer", new VehicleDamageAnalyzer());

    public static final Item TUNGSTEN_ROD = registerItem("tungsten_rod", new Item(new Item.Properties()));

    public static final Materials IRON_MATERIALS = registerMaterials("iron");
    public static final Materials STEEL_MATERIALS = registerMaterials("steel");
    public static final Materials CEMENTED_CARBIDE_MATERIALS = registerMaterials("cemented_carbide");
    public static final Materials NETHERITE_MATERIALS = registerMaterials("netherite");

    public static final Item COMMON_MATERIAL_PACK = registerItem("common_material_pack", new MaterialPack(Rarity.COMMON));
    public static final Item RARE_MATERIAL_PACK = registerItem("rare_material_pack", new MaterialPack(Rarity.RARE));
    public static final Item EPIC_MATERIAL_PACK = registerItem("epic_material_pack", new MaterialPack(Rarity.EPIC));
    public static final Item LEGENDARY_MATERIAL_PACK = registerItem("legendary_material_pack", new MaterialPack(RarityTool.LEGENDARY));

    public static final Item LIGHT_ARMAMENT_MODULE = registerItem("light_armament_module", new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final Item MEDIUM_ARMAMENT_MODULE = registerItem("medium_armament_module", new Item(new Item.Properties().rarity(Rarity.EPIC)));
    public static final Item HEAVY_ARMAMENT_MODULE = registerItem("heavy_armament_module", new CustomRatityItem(RarityTool.LEGENDARY));

    public static final Item TRACHELIUM_BLUEPRINT = registerItem("trachelium_blueprint", new BlueprintItem(Rarity.EPIC));
    public static final Item GLOCK_17_BLUEPRINT = registerItem("glock_17_blueprint", new BlueprintItem(Rarity.COMMON));
    public static final Item MP_443_BLUEPRINT = registerItem("mp_443_blueprint", new BlueprintItem(Rarity.COMMON));
    public static final Item GLOCK_18_BLUEPRINT = registerItem("glock_18_blueprint", new BlueprintItem(Rarity.RARE));
    public static final Item HUNTING_RIFLE_BLUEPRINT = registerItem("hunting_rifle_blueprint", new BlueprintItem(Rarity.RARE));
    public static final Item M_79_BLUEPRINT = registerItem("m_79_blueprint", new BlueprintItem(Rarity.RARE));
    public static final Item RPG_BLUEPRINT = registerItem("rpg_blueprint", new BlueprintItem(Rarity.RARE));
    public static final Item BOCEK_BLUEPRINT = registerItem("bocek_blueprint", new BlueprintItem(Rarity.EPIC));
    public static final Item M_4_BLUEPRINT = registerItem("m_4_blueprint", new BlueprintItem(Rarity.RARE));
    public static final Item AA_12_BLUEPRINT = registerItem("aa_12_blueprint", new BlueprintItem(RarityTool.LEGENDARY));
    public static final Item HK_416_BLUEPRINT = registerItem("hk_416_blueprint", new BlueprintItem(Rarity.RARE));
    public static final Item RPK_BLUEPRINT = registerItem("rpk_blueprint", new BlueprintItem(Rarity.EPIC));
    public static final Item SKS_BLUEPRINT = registerItem("sks_blueprint", new BlueprintItem(Rarity.RARE));
    public static final Item NTW_20_BLUEPRINT = registerItem("ntw_20_blueprint", new BlueprintItem(RarityTool.LEGENDARY));
    public static final Item MP_5_BLUEPRINT = registerItem("mp_5_blueprint", new BlueprintItem(Rarity.RARE));
    public static final Item VECTOR_BLUEPRINT = registerItem("vector_blueprint", new BlueprintItem(Rarity.EPIC));
    public static final Item MINIGUN_BLUEPRINT = registerItem("minigun_blueprint", new BlueprintItem(RarityTool.LEGENDARY));
    public static final Item MK_14_BLUEPRINT = registerItem("mk_14_blueprint", new BlueprintItem(Rarity.EPIC));
    public static final Item SENTINEL_BLUEPRINT = registerItem("sentinel_blueprint", new BlueprintItem(Rarity.EPIC));
    public static final Item M_60_BLUEPRINT = registerItem("m_60_blueprint", new BlueprintItem(Rarity.EPIC));
    public static final Item SVD_BLUEPRINT = registerItem("svd_blueprint", new BlueprintItem(Rarity.EPIC));
    public static final Item MARLIN_BLUEPRINT = registerItem("marlin_blueprint", new BlueprintItem(Rarity.COMMON));
    public static final Item M_870_BLUEPRINT = registerItem("m_870_blueprint", new BlueprintItem(Rarity.RARE));
    public static final Item AWM_BLUEPRINT = registerItem("awm_blueprint", new BlueprintItem(Rarity.EPIC));
    public static final Item M_98B_BLUEPRINT = registerItem("m_98b_blueprint", new BlueprintItem(Rarity.EPIC));
    public static final Item AK_47_BLUEPRINT = registerItem("ak_47_blueprint", new BlueprintItem(Rarity.RARE));
    public static final Item AK_12_BLUEPRINT = registerItem("ak_12_blueprint", new BlueprintItem(Rarity.RARE));
    public static final Item DEVOTION_BLUEPRINT = registerItem("devotion_blueprint", new BlueprintItem(Rarity.EPIC));
    public static final Item TASER_BLUEPRINT = registerItem("taser_blueprint", new BlueprintItem(Rarity.COMMON));
    public static final Item M_1911_BLUEPRINT = registerItem("m_1911_blueprint", new BlueprintItem(Rarity.COMMON));
    public static final Item QBZ_95_BLUEPRINT = registerItem("qbz_95_blueprint", new BlueprintItem(Rarity.RARE));
    public static final Item QBZ_191_BLUEPRINT = registerItem("qbz_191_blueprint", new BlueprintItem(Rarity.EPIC));
    public static final Item K_98_BLUEPRINT = registerItem("k_98_blueprint", new BlueprintItem(Rarity.RARE));
    public static final Item MOSIN_NAGANT_BLUEPRINT = registerItem("mosin_nagant_blueprint", new BlueprintItem(Rarity.RARE));
    public static final Item JAVELIN_BLUEPRINT = registerItem("javelin_blueprint", new BlueprintItem(RarityTool.LEGENDARY));
    public static final Item M_2_HB_BLUEPRINT = registerItem("m_2_hb_blueprint", new BlueprintItem(Rarity.RARE));
    public static final Item SECONDARY_CATACLYSM_BLUEPRINT = registerItem("secondary_cataclysm_blueprint", new BlueprintItem(RarityTool.LEGENDARY));
    public static final Item INSIDIOUS_BLUEPRINT = registerItem("insidious_blueprint", new BlueprintItem(Rarity.EPIC));
    public static final Item AURELIA_SCEPTRE_BLUEPRINT = registerItem("aurelia_sceptre_blueprint", new BlueprintItem(RarityTool.LEGENDARY));
    public static final Item MK_42_BLUEPRINT = registerItem("mk_42_blueprint", new BlueprintItem(RarityTool.LEGENDARY));
    public static final Item MLE_1934_BLUEPRINT = registerItem("mle_1934_blueprint", new BlueprintItem(RarityTool.LEGENDARY));
    public static final Item BL_132_BLUEPRINT = registerItem("bl_132_blueprint", new BlueprintItem(RarityTool.LEGENDARY));
    public static final Item HPJ_11_BLUEPRINT = registerItem("hpj_11_blueprint", new BlueprintItem(RarityTool.LEGENDARY));
    public static final Item ANNIHILATOR_BLUEPRINT = registerItem("annihilator_blueprint", new BlueprintItem(RarityTool.LEGENDARY));

    /**
     * Block
     */
    public static final Item GALENA_ORE = block(ModBlocks.GALENA_ORE);
    public static final Item DEEPSLATE_GALENA_ORE = block(ModBlocks.DEEPSLATE_GALENA_ORE);
    public static final Item SCHEELITE_ORE = block(ModBlocks.SCHEELITE_ORE);
    public static final Item DEEPSLATE_SCHEELITE_ORE = block(ModBlocks.DEEPSLATE_SCHEELITE_ORE);
    public static final Item SILVER_ORE = block(ModBlocks.SILVER_ORE);
    public static final Item DEEPSLATE_SILVER_ORE = block(ModBlocks.DEEPSLATE_SILVER_ORE);
    public static final Item JUMP_PAD = block(ModBlocks.JUMP_PAD);
    public static final Item SANDBAG = block(ModBlocks.SANDBAG);
    public static final Item BARBED_WIRE = block(ModBlocks.BARBED_WIRE);
    public static final Item DRAGON_TEETH = block(ModBlocks.DRAGON_TEETH);
    public static final Item REFORGING_TABLE = block(ModBlocks.REFORGING_TABLE);
    public static final Item CHARGING_STATION = block("charging_station", new ChargingStationBlockItem());
    public static final Item CREATIVE_CHARGING_STATION = block("creative_charging_station", new CreativeChargingStationBlockItem());
    public static final Item LEAD_BLOCK = block(ModBlocks.LEAD_BLOCK);
    public static final Item STEEL_BLOCK = block(ModBlocks.STEEL_BLOCK);
    public static final Item TUNGSTEN_BLOCK = block(ModBlocks.TUNGSTEN_BLOCK);
    public static final Item SILVER_BLOCK = block(ModBlocks.SILVER_BLOCK);
    public static final Item CEMENTED_CARBIDE_BLOCK = block(ModBlocks.CEMENTED_CARBIDE_BLOCK);
    public static final Item FUMO_25 = block(ModBlocks.FUMO_25);
    public static final Item VEHICLE_DEPLOYER = block("vehicle_deployer", new VehicleDeployerBlockItem());
    public static final Item AIRCRAFT_CATAPULT = block(ModBlocks.AIRCRAFT_CATAPULT);
    public static final Item SUPERB_ITEM_INTERFACE = block(ModBlocks.SUPERB_ITEM_INTERFACE);
    public static final Item CREATIVE_SUPERB_ITEM_INTERFACE = block(ModBlocks.CREATIVE_SUPERB_ITEM_INTERFACE, Rarity.EPIC);
    public static final Item VEHICLE_ASSEMBLING_TABLE = block("vehicle_assembling_table", new VehicleAssemblingTableBlockItem());

    /**
     * Vehicle
     */
    public static final Item CONTAINER = register("container", new ContainerBlockItem());
    public static final Item SMALL_CONTAINER = register("small_container", new SmallContainerBlockItem());
    public static final Item LUCKY_CONTAINER = register("lucky_container", new LuckyContainerBlockItem());

    public static List<Item> BLOCK_ITEMS;

    private static Item block(Block block) {
        if (BLOCK_ITEMS == null)
            BLOCK_ITEMS = new ArrayList<>();
        BlockItem blockItem = new BlockItem(block, new Item.Properties());
        BLOCK_ITEMS.add(blockItem);
        return register(BuiltInRegistries.BLOCK.getKey(block).getPath(), blockItem);
    }

    private static Item block(Block block, Rarity rarity) {
        BlockItem blockItem = new BlockItem(block, new Item.Properties().rarity(rarity));
        BLOCK_ITEMS.add(blockItem);
        return register(BuiltInRegistries.BLOCK.getKey(block).getPath(), blockItem);
    }

    private static Item block(String name, BlockItem blockItem) {
        BLOCK_ITEMS.add(blockItem);
        return register(name, blockItem);
    }

    public record Materials(
            String name,
            Item barrel,
            Item action,
            Item spring,
            Item trigger
    ) {
    }

    public static Materials registerMaterials(String name) {
        return new Materials(
                name,
                register(name + "_barrel", new Item(new Item.Properties())),
                register(name + "_action", new Item(new Item.Properties())),
                register(name + "_spring", new Item(new Item.Properties())),
                register(name + "_trigger", new Item(new Item.Properties()))
        );
    }

    /**
     * Perk Items
     */
    public static final Map<Perk, Item> PERK_ITEMS = new HashMap<>();

    /**
     * 单独注册，用于Tab图标，不要删
     */
    public static Item AP_BULLET;
    public static Item INTELLIGENT_CHIP;

    public static void registerPerkItems() {
        ModPerks.PERKS.forEach(ModItems::registerSinglePerkItem);

        AP_BULLET = PERK_ITEMS.get(ModPerks.AP_BULLET);
        INTELLIGENT_CHIP = PERK_ITEMS.get(ModPerks.INTELLIGENT_CHIP);
    }

    private static void registerSinglePerkItem(Perk perk) {
        PERK_ITEMS.put(perk, register(ModPerks.PERKS.getKey(perk).getPath(), new PerkItem(perk)));
    }

    public static final Item SHORTCUT_PACK = register("shortcut_pack", new ShortcutPack());
    public static final Item EMPTY_PERK = register("empty_perk", new Item(new Item.Properties()));


    public static void registerDispenserBehavior() {
        List<Item> list = new ArrayList<>();
        list.addAll(AMMO_ITEMS);
        list.addAll(ITEMS);

        for (var item : list) {
            if (item instanceof DispenserLaunchable launchable) {
                DispenserBlock.registerBehavior(item, launchable.getLaunchBehavior());
            }
        }
    }

    public static List<Item> GUN_ITEMS;
    public static List<Item> AMMO_ITEMS;
    public static List<Item> ITEMS;

    private static Item registerGun(String name, @NotNull Item item) {
        if (GUN_ITEMS == null)
            GUN_ITEMS = new ArrayList<>();
        GUN_ITEMS.add(item);
        return register(name, item);
    }

    private static Item registerAmmo(String name, Item item) {
        if (AMMO_ITEMS == null)
            AMMO_ITEMS = new ArrayList<>();
        AMMO_ITEMS.add(item);
        return register(name, item);
    }

    private static Item registerItem(String name, Item item) {
        if (ITEMS == null)
            ITEMS = new ArrayList<>();
        ITEMS.add(item);
        return register(name, item);
    }

    private static Item register(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, Mod.loc(name), item);
    }
}
