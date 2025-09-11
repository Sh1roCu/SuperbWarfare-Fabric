package com.atsuishio.superbwarfare.init;

import com.atsuishio.superbwarfare.Mod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;

@SuppressWarnings("unused")
public class ModSounds {

    public static void init() {

    }

    public static final SoundEvent TASER_FIRE_1P = register("taser_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("taser_fire_1p")));
    public static final SoundEvent TASER_FIRE_3P = register("taser_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("taser_fire_3p")));
    public static final SoundEvent TASER_RELOAD_EMPTY = register("taser_reload_empty", SoundEvent.createVariableRangeEvent(Mod.loc("taser_reload_empty")));

    public static final SoundEvent SHOCK = register("shock", SoundEvent.createVariableRangeEvent(Mod.loc("shock")));
    public static final SoundEvent ELECTRIC = register("electric", SoundEvent.createVariableRangeEvent(Mod.loc("electric")));
    public static final SoundEvent MELEE_HIT = register("melee_hit", SoundEvent.createVariableRangeEvent(Mod.loc("melee_hit")));

    public static final SoundEvent TRACHELIUM_FIRE_1P = register("trachelium_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("trachelium_fire_1p")));
    public static final SoundEvent TRACHELIUM_FIRE_3P = register("trachelium_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("trachelium_fire_3p")));
    public static final SoundEvent TRACHELIUM_FAR = register("trachelium_far", SoundEvent.createVariableRangeEvent(Mod.loc("trachelium_far")));
    public static final SoundEvent TRACHELIUM_VERYFAR = register("trachelium_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("trachelium_veryfar")));

    public static final SoundEvent TRACHELIUM_FIRE_1P_S = register("trachelium_fire_1p_s", SoundEvent.createVariableRangeEvent(Mod.loc("trachelium_fire_1p_s")));
    public static final SoundEvent TRACHELIUM_FIRE_3P_S = register("trachelium_fire_3p_s", SoundEvent.createVariableRangeEvent(Mod.loc("trachelium_fire_3p_s")));
    public static final SoundEvent TRACHELIUM_FAR_S = register("trachelium_far_s", SoundEvent.createVariableRangeEvent(Mod.loc("trachelium_far_s")));
    public static final SoundEvent TRACHELIUM_RELOAD_EMPTY = register("trachelium_reload_empty", SoundEvent.createVariableRangeEvent(Mod.loc("trachelium_reload_empty")));
    public static final SoundEvent TRACHELIUM_BOLT = register("trachelium_bolt", SoundEvent.createVariableRangeEvent(Mod.loc("trachelium_bolt")));

    public static final SoundEvent TRIGGER_CLICK = register("triggerclick", SoundEvent.createVariableRangeEvent(Mod.loc("triggerclick")));
    public static final SoundEvent HIT = register("hit", SoundEvent.createVariableRangeEvent(Mod.loc("hit")));
    public static final SoundEvent TARGET_DOWN = register("targetdown", SoundEvent.createVariableRangeEvent(Mod.loc("targetdown")));
    public static final SoundEvent INDICATION = register("indication", SoundEvent.createVariableRangeEvent(Mod.loc("indication")));
    public static final SoundEvent INDICATION_VEHICLE = register("indication_vehicle", SoundEvent.createVariableRangeEvent(Mod.loc("indication_vehicle")));
    public static final SoundEvent JUMP = register("jump", SoundEvent.createVariableRangeEvent(Mod.loc("jump")));
    public static final SoundEvent DOUBLE_JUMP = register("doublejump", SoundEvent.createVariableRangeEvent(Mod.loc("doublejump")));

    public static final SoundEvent EXPLOSION_CLOSE = register("explosion_close", SoundEvent.createVariableRangeEvent(Mod.loc("explosion_close")));
    public static final SoundEvent EXPLOSION_FAR = register("explosion_far", SoundEvent.createVariableRangeEvent(Mod.loc("explosion_far")));
    public static final SoundEvent EXPLOSION_VERY_FAR = register("explosion_very_far", SoundEvent.createVariableRangeEvent(Mod.loc("explosion_very_far")));
    public static final SoundEvent HUGE_EXPLOSION_CLOSE = register("huge_explosion_close", SoundEvent.createVariableRangeEvent(Mod.loc("huge_explosion_close")));
    public static final SoundEvent HUGE_EXPLOSION_FAR = register("huge_explosion_far", SoundEvent.createVariableRangeEvent(Mod.loc("huge_explosion_far")));
    public static final SoundEvent HUGE_EXPLOSION_VERY_FAR = register("huge_explosion_very_far", SoundEvent.createVariableRangeEvent(Mod.loc("huge_explosion_very_far")));
    public static final SoundEvent EXPLOSION_WATER = register("explosion_water", SoundEvent.createVariableRangeEvent(Mod.loc("explosion_water")));
    public static final SoundEvent EXPLOSION_AIR = register("explosion_air", SoundEvent.createVariableRangeEvent(Mod.loc("explosion_air")));

    public static final SoundEvent HUNTING_RIFLE_FIRE_1P = register("hunting_rifle_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("hunting_rifle_fire_1p")));
    public static final SoundEvent HUNTING_RIFLE_FIRE_3P = register("hunting_rifle_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("hunting_rifle_fire_3p")));
    public static final SoundEvent HUNTING_RIFLE_FAR = register("hunting_rifle_far", SoundEvent.createVariableRangeEvent(Mod.loc("hunting_rifle_far")));
    public static final SoundEvent HUNTING_RIFLE_VERYFAR = register("hunting_rifle_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("hunting_rifle_veryfar")));
    public static final SoundEvent HUNTING_RIFLE_RELOAD_EMPTY = register("hunting_rifle_reload_empty", SoundEvent.createVariableRangeEvent(Mod.loc("hunting_rifle_reload_empty")));

    public static final SoundEvent OUCH = register("ouch", SoundEvent.createVariableRangeEvent(Mod.loc("ouch")));
    public static final SoundEvent STEP = register("step", SoundEvent.createVariableRangeEvent(Mod.loc("step")));
    public static final SoundEvent GROWL = register("growl", SoundEvent.createVariableRangeEvent(Mod.loc("growl")));
    public static final SoundEvent IDLE = register("idle", SoundEvent.createVariableRangeEvent(Mod.loc("idle")));
    public static final SoundEvent HENG = register("heng", SoundEvent.createVariableRangeEvent(Mod.loc("heng")));

    public static final SoundEvent M_79_FIRE_1P = register("m_79_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("m_79_fire_1p")));
    public static final SoundEvent M_79_FIRE_3P = register("m_79_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("m_79_fire_3p")));
    public static final SoundEvent M_79_FAR = register("m_79_far", SoundEvent.createVariableRangeEvent(Mod.loc("m_79_far")));
    public static final SoundEvent M_79_VERYFAR = register("m_79_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("m_79_veryfar")));
    public static final SoundEvent M_79_RELOAD_EMPTY = register("m_79_reload_empty", SoundEvent.createVariableRangeEvent(Mod.loc("m_79_reload_empty")));

    public static final SoundEvent SKS_FIRE_1P = register("sks_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("sks_fire_1p")));
    public static final SoundEvent SKS_FIRE_3P = register("sks_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("sks_fire_3p")));
    public static final SoundEvent SKS_RELOAD_NORMAL = register("sks_reload_normal", SoundEvent.createVariableRangeEvent(Mod.loc("sks_reload_normal")));
    public static final SoundEvent SKS_RELOAD_EMPTY = register("sks_reload_empty", SoundEvent.createVariableRangeEvent(Mod.loc("sks_reload_empty")));
    public static final SoundEvent SKS_FAR = register("sks_far", SoundEvent.createVariableRangeEvent(Mod.loc("sks_far")));
    public static final SoundEvent SKS_VERYFAR = register("sks_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("sks_veryfar")));

    public static final SoundEvent HOMEMADE_SHOTGUN_FIRE_1P = register("homemade_shotgun_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("homemade_shotgun_fire_1p")));
    public static final SoundEvent HOMEMADE_SHOTGUN_FIRE_3P = register("homemade_shotgun_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("homemade_shotgun_fire_3p")));
    public static final SoundEvent HOMEMADE_SHOTGUN_FAR = register("homemade_shotgun_far", SoundEvent.createVariableRangeEvent(Mod.loc("homemade_shotgun_far")));
    public static final SoundEvent HOMEMADE_SHOTGUN_VERYFAR = register("homemade_shotgun_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("homemade_shotgun_veryfar")));
    public static final SoundEvent HOMEMADE_SHOTGUN_NORMAL = register("homemade_shotgun_reload_normal", SoundEvent.createVariableRangeEvent(Mod.loc("homemade_shotgun_reload_normal")));
    public static final SoundEvent HOMEMADE_SHOTGUN_RELOAD_EMPTY = register("homemade_shotgun_reload_empty", SoundEvent.createVariableRangeEvent(Mod.loc("homemade_shotgun_reload_empty")));

    public static final SoundEvent AK_47_FIRE_1P = register("ak_47_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("ak_47_fire_1p")));
    public static final SoundEvent AK_47_FIRE_3P = register("ak_47_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("ak_47_fire_3p")));
    public static final SoundEvent AK_47_FIRE_1P_S = register("ak_47_fire_1p_s", SoundEvent.createVariableRangeEvent(Mod.loc("ak_47_fire_1p_s")));
    public static final SoundEvent AK_47_FIRE_3P_S = register("ak_47_fire_3p_s", SoundEvent.createVariableRangeEvent(Mod.loc("ak_47_fire_3p_s")));
    public static final SoundEvent AK_47_FAR = register("ak_47_far", SoundEvent.createVariableRangeEvent(Mod.loc("ak_47_far")));
    public static final SoundEvent AK_47_VERYFAR = register("ak_47_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("ak_47_veryfar")));
    public static final SoundEvent AK_47_FAR_S = register("ak_47_far_s", SoundEvent.createVariableRangeEvent(Mod.loc("ak_12_far_s")));
    public static final SoundEvent AK_47_VERYFAR_S = register("ak_47_veryfar_s", SoundEvent.createVariableRangeEvent(Mod.loc("ak_12_veryfar_s")));
    public static final SoundEvent AK_47_RELOAD_NORMAL = register("ak_47_reload_normal", SoundEvent.createVariableRangeEvent(Mod.loc("ak_47_reload_normal")));
    public static final SoundEvent AK_47_RELOAD_EMPTY = register("ak_47_reload_empty", SoundEvent.createVariableRangeEvent(Mod.loc("ak_47_reload_empty")));

    public static final SoundEvent AK_12_FIRE_1P = register("ak_12_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("ak_12_fire_1p")));
    public static final SoundEvent AK_12_FIRE_3P = register("ak_12_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("ak_12_fire_3p")));
    public static final SoundEvent AK_12_FIRE_1P_S = register("ak_12_fire_1p_s", SoundEvent.createVariableRangeEvent(Mod.loc("ak_12_fire_1p_s")));
    public static final SoundEvent AK_12_FIRE_3P_S = register("ak_12_fire_3p_s", SoundEvent.createVariableRangeEvent(Mod.loc("ak_12_fire_3p_s")));
    public static final SoundEvent AK_12_FAR = register("ak_12_far", SoundEvent.createVariableRangeEvent(Mod.loc("ak_12_far")));
    public static final SoundEvent AK_12_VERYFAR = register("ak_12_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("ak_12_veryfar")));
    public static final SoundEvent AK_12_FAR_S = register("ak_12_far_s", SoundEvent.createVariableRangeEvent(Mod.loc("ak_12_far_s")));
    public static final SoundEvent AK_12_VERYFAR_S = register("ak_12_veryfar_s", SoundEvent.createVariableRangeEvent(Mod.loc("ak_12_veryfar_s")));
    public static final SoundEvent AK_12_RELOAD_NORMAL = register("ak_12_reload_normal", SoundEvent.createVariableRangeEvent(Mod.loc("ak_12_reload_normal")));
    public static final SoundEvent AK_12_RELOAD_EMPTY = register("ak_12_reload_empty", SoundEvent.createVariableRangeEvent(Mod.loc("ak_12_reload_empty")));

    public static final SoundEvent LAND = register("land", SoundEvent.createVariableRangeEvent(Mod.loc("land")));
    public static final SoundEvent HIT_WATER = register("hit_water", SoundEvent.createVariableRangeEvent(Mod.loc("hit_water")));
    public static final SoundEvent HEADSHOT = register("headshot", SoundEvent.createVariableRangeEvent(Mod.loc("headshot")));

    public static final SoundEvent DEVOTION_FIRE_1P = register("devotion_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("devotion_fire_1p")));
    public static final SoundEvent DEVOTION_FIRE_3P = register("devotion_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("devotion_fire_3p")));
    public static final SoundEvent DEVOTION_FAR = register("devotion_far", SoundEvent.createVariableRangeEvent(Mod.loc("devotion_far")));
    public static final SoundEvent DEVOTION_VERYFAR = register("devotion_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("devotion_veryfar")));
    public static final SoundEvent DEVOTION_RELOAD_NORMAL = register("devotion_reload_normal", SoundEvent.createVariableRangeEvent(Mod.loc("devotion_reload_normal")));
    public static final SoundEvent DEVOTION_RELOAD_EMPTY = register("devotion_reload_empty", SoundEvent.createVariableRangeEvent(Mod.loc("devotion_reload_empty")));

    public static final SoundEvent RPG_FIRE_1P = register("rpg_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("rpg_fire_1p")));
    public static final SoundEvent RPG_FIRE_3P = register("rpg_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("rpg_fire_3p")));
    public static final SoundEvent RPG_FAR = register("rpg_far", SoundEvent.createVariableRangeEvent(Mod.loc("rpg_far")));
    public static final SoundEvent RPG_VERYFAR = register("rpg_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("rpg_veryfar")));
    public static final SoundEvent RPG_RELOAD_EMPTY = register("rpg_reload_empty", SoundEvent.createVariableRangeEvent(Mod.loc("rpg_reload_empty")));

    public static final SoundEvent MORTAR_FIRE = register("mortar_fire", SoundEvent.createVariableRangeEvent(Mod.loc("mortar_fire")));
    public static final SoundEvent MORTAR_LOAD = register("mortar_load", SoundEvent.createVariableRangeEvent(Mod.loc("mortar_load")));
    public static final SoundEvent MORTAR_DISTANT = register("mortar_distant", SoundEvent.createVariableRangeEvent(Mod.loc("mortar_distant")));

    public static final SoundEvent FIRE_RATE = register("firerate", SoundEvent.createVariableRangeEvent(Mod.loc("firerate")));

    public static final SoundEvent M_4_FIRE_1P = register("m_4_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("m_4_fire_1p")));
    public static final SoundEvent M_4_FIRE_3P = register("m_4_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("m_4_fire_3p")));
    public static final SoundEvent M_4_FIRE_1P_S = register("m_4_fire_1p_s", SoundEvent.createVariableRangeEvent(Mod.loc("m_4_fire_1p_s")));
    public static final SoundEvent M_4_FIRE_3P_S = register("m_4_fire_3p_s", SoundEvent.createVariableRangeEvent(Mod.loc("m_4_fire_3p_s")));
    public static final SoundEvent M_4_FAR = register("m_4_far", SoundEvent.createVariableRangeEvent(Mod.loc("m_4_far")));
    public static final SoundEvent M_4_VERYFAR = register("m_4_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("m_4_veryfar")));
    public static final SoundEvent M_4_RELOAD_NORMAL = register("m_4_reload_normal", SoundEvent.createVariableRangeEvent(Mod.loc("m_4_reload_normal")));
    public static final SoundEvent M_4_RELOAD_EMPTY = register("m_4_reload_empty", SoundEvent.createVariableRangeEvent(Mod.loc("m_4_reload_empty")));
    public static final SoundEvent M_4_FAR_S = register("m_4_far_s", SoundEvent.createVariableRangeEvent(Mod.loc("ak_12_far_s")));
    public static final SoundEvent M_4_VERYFAR_S = register("m_4_veryfar_s", SoundEvent.createVariableRangeEvent(Mod.loc("ak_12_veryfar_s")));

    public static final SoundEvent AA_12_FIRE_1P = register("aa_12_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("aa_12_fire_1p")));
    public static final SoundEvent AA_12_FIRE_3P = register("aa_12_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("aa_12_fire_3p")));
    public static final SoundEvent AA_12_FAR = register("aa_12_far", SoundEvent.createVariableRangeEvent(Mod.loc("aa_12_far")));
    public static final SoundEvent AA_12_VERYFAR = register("aa_12_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("aa_12_veryfar")));
    public static final SoundEvent AA_12_RELOAD_NORMAL = register("aa_12_reload_normal", SoundEvent.createVariableRangeEvent(Mod.loc("aa_12_reload_normal")));
    public static final SoundEvent AA_12_RELOAD_EMPTY = register("aa_12_reload_empty", SoundEvent.createVariableRangeEvent(Mod.loc("aa_12_reload_empty")));

    public static final SoundEvent BOCEK_ZOOM_FIRE_1P = register("bocek_zoom_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("bocek_zoom_fire_1p")));
    public static final SoundEvent BOCEK_ZOOM_FIRE_3P = register("bocek_zoom_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("bocek_zoom_fire_3p")));
    public static final SoundEvent BOCEK_SHATTER_CAP_FIRE_1P = register("bocek_shatter_cap_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("bocek_shatter_cap_fire_1p")));
    public static final SoundEvent BOCEK_SHATTER_CAP_FIRE_3P = register("bocek_shatter_cap_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("bocek_shatter_cap_fire_3p")));
    public static final SoundEvent BOCEK_PULL_1P = register("bocek_pull_1p", SoundEvent.createVariableRangeEvent(Mod.loc("bocek_pull_1p")));
    public static final SoundEvent BOCEK_PULL_3P = register("bocek_pull_3p", SoundEvent.createVariableRangeEvent(Mod.loc("bocek_pull_3p")));

    public static final SoundEvent HK_416_FIRE_1P = register("hk_416_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("hk_416_fire_1p")));
    public static final SoundEvent HK_416_FIRE_3P = register("hk_416_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("hk_416_fire_3p")));
    public static final SoundEvent HK_416_FIRE_1P_S = register("hk_416_fire_1p_s", SoundEvent.createVariableRangeEvent(Mod.loc("hk_416_fire_1p_s")));
    public static final SoundEvent HK_416_FIRE_3P_S = register("hk_416_fire_3p_s", SoundEvent.createVariableRangeEvent(Mod.loc("hk_416_fire_3p_s")));
    public static final SoundEvent HK_416_FAR = register("hk_416_far", SoundEvent.createVariableRangeEvent(Mod.loc("hk_416_far")));
    public static final SoundEvent HK_416_VERYFAR = register("hk_416_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("hk_416_veryfar")));
    public static final SoundEvent HK_416_RELOAD_NORMAL = register("hk_416_reload_normal", SoundEvent.createVariableRangeEvent(Mod.loc("hk_416_reload_normal")));
    public static final SoundEvent HK_416_RELOAD_EMPTY = register("hk_416_reload_empty", SoundEvent.createVariableRangeEvent(Mod.loc("hk_416_reload_empty")));
    public static final SoundEvent HK_416_FAR_S = register("hk_416_far_s", SoundEvent.createVariableRangeEvent(Mod.loc("ak_12_far_s")));
    public static final SoundEvent HK_416_VERYFAR_S = register("hk_416_veryfar_s", SoundEvent.createVariableRangeEvent(Mod.loc("ak_12_veryfar_s")));

    public static final SoundEvent RPK_FIRE_1P = register("rpk_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("rpk_fire_1p")));
    public static final SoundEvent RPK_FIRE_3P = register("rpk_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("rpk_fire_3p")));
    public static final SoundEvent RPK_FIRE_1P_S = register("rpk_fire_1p_s", SoundEvent.createVariableRangeEvent(Mod.loc("rpk_fire_1p_s")));
    public static final SoundEvent RPK_FIRE_3P_S = register("rpk_fire_3p_s", SoundEvent.createVariableRangeEvent(Mod.loc("rpk_fire_3p_s")));
    public static final SoundEvent RPK_FAR = register("rpk_far", SoundEvent.createVariableRangeEvent(Mod.loc("rpk_far")));
    public static final SoundEvent RPK_VERYFAR = register("rpk_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("rpk_veryfar")));
    public static final SoundEvent RPK_RELOAD_NORMAL = register("rpk_reload_normal", SoundEvent.createVariableRangeEvent(Mod.loc("rpk_reload_normal")));
    public static final SoundEvent RPK_RELOAD_EMPTY = register("rpk_reload_empty", SoundEvent.createVariableRangeEvent(Mod.loc("rpk_reload_empty")));

    public static final SoundEvent NTW_20_FIRE_1P = register("ntw_20_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("ntw_20_fire_1p")));
    public static final SoundEvent NTW_20_FIRE_3P = register("ntw_20_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("ntw_20_fire_3p")));
    public static final SoundEvent NTW_20_FAR = register("ntw_20_far", SoundEvent.createVariableRangeEvent(Mod.loc("ntw_20_far")));
    public static final SoundEvent NTW_20_VERYFAR = register("ntw_20_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("ntw_20_veryfar")));
    public static final SoundEvent NTW_20_RELOAD_NORMAL = register("ntw_20_reload_normal", SoundEvent.createVariableRangeEvent(Mod.loc("ntw_20_reload_normal")));
    public static final SoundEvent NTW_20_RELOAD_EMPTY = register("ntw_20_reload_empty", SoundEvent.createVariableRangeEvent(Mod.loc("ntw_20_reload_empty")));
    public static final SoundEvent NTW_20_BOLT = register("ntw_20_bolt", SoundEvent.createVariableRangeEvent(Mod.loc("ntw_20_bolt")));

    public static final SoundEvent VECTOR_FIRE_1P = register("vector_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("vector_fire_1p")));
    public static final SoundEvent VECTOR_FIRE_3P = register("vector_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("vector_fire_3p")));
    public static final SoundEvent VECTOR_FAR = register("vector_far", SoundEvent.createVariableRangeEvent(Mod.loc("vector_far")));
    public static final SoundEvent VECTOR_VERYFAR = register("vector_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("vector_veryfar")));
    public static final SoundEvent VECTOR_FIRE_1P_S = register("vector_fire_1p_s", SoundEvent.createVariableRangeEvent(Mod.loc("vector_fire_1p_s")));
    public static final SoundEvent VECTOR_FIRE_3P_S = register("vector_fire_3p_s", SoundEvent.createVariableRangeEvent(Mod.loc("vector_fire_3p_s")));
    public static final SoundEvent VECTOR_FAR_S = register("vector_far_s", SoundEvent.createVariableRangeEvent(Mod.loc("vector_far_s")));
    public static final SoundEvent VECTOR_RELOAD_NORMAL = register("vector_reload_normal", SoundEvent.createVariableRangeEvent(Mod.loc("vector_reload_normal")));
    public static final SoundEvent VECTOR_RELOAD_EMPTY = register("vector_reload_empty", SoundEvent.createVariableRangeEvent(Mod.loc("vector_reload_empty")));

    public static final SoundEvent MINIGUN_FIRE_1P = register("minigun_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("minigun_fire_1p")));
    public static final SoundEvent MINIGUN_FIRE_3P = register("minigun_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("minigun_fire_3p")));
    public static final SoundEvent MINIGUN_FAR = register("minigun_far", SoundEvent.createVariableRangeEvent(Mod.loc("minigun_far")));
    public static final SoundEvent MINIGUN_VERYFAR = register("minigun_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("minigun_veryfar")));
    public static final SoundEvent MINIGUN_ROT = register("minigun_rot", SoundEvent.createVariableRangeEvent(Mod.loc("minigun_rot")));
    public static final SoundEvent MINIGUN_OVERHEAT = register("minigun_overheat", SoundEvent.createVariableRangeEvent(Mod.loc("minigun_overheat")));

    public static final SoundEvent MK_14_FIRE_1P = register("mk_14_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("mk_14_fire_1p")));
    public static final SoundEvent MK_14_FIRE_3P = register("mk_14_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("mk_14_fire_3p")));
    public static final SoundEvent MK_14_FAR = register("mk_14_far", SoundEvent.createVariableRangeEvent(Mod.loc("mk_14_far")));
    public static final SoundEvent MK_14_VERYFAR = register("mk_14_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("mk_14_veryfar")));
    public static final SoundEvent MK_14_FIRE_1P_S = register("mk_14_fire_1p_s", SoundEvent.createVariableRangeEvent(Mod.loc("mk_14_fire_1p_s")));
    public static final SoundEvent MK_14_FIRE_3P_S = register("mk_14_fire_3p_s", SoundEvent.createVariableRangeEvent(Mod.loc("mk_14_fire_3p_s")));
    public static final SoundEvent MK_14_FAR_S = register("mk_14_far_s", SoundEvent.createVariableRangeEvent(Mod.loc("mk_14_far_s")));
    public static final SoundEvent MK_14_RELOAD_NORMAL = register("mk_14_reload_normal", SoundEvent.createVariableRangeEvent(Mod.loc("mk_14_reload_normal")));
    public static final SoundEvent MK_14_RELOAD_EMPTY = register("mk_14_reload_empty", SoundEvent.createVariableRangeEvent(Mod.loc("mk_14_reload_empty")));

    public static final SoundEvent SENTINEL_FIRE_1P = register("sentinel_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("sentinel_fire_1p")));
    public static final SoundEvent SENTINEL_FIRE_3P = register("sentinel_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("sentinel_fire_3p")));
    public static final SoundEvent SENTINEL_CHARGE_FIRE_1P = register("sentinel_charge_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("sentinel_charge_fire_1p")));
    public static final SoundEvent SENTINEL_CHARGE_FIRE_3P = register("sentinel_charge_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("sentinel_charge_fire_3p")));
    public static final SoundEvent SENTINEL_FAR = register("sentinel_far", SoundEvent.createVariableRangeEvent(Mod.loc("sentinel_far")));
    public static final SoundEvent SENTINEL_VERYFAR = register("sentinel_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("sentinel_veryfar")));
    public static final SoundEvent SENTINEL_CHARGE_FAR = register("sentinel_charge_far", SoundEvent.createVariableRangeEvent(Mod.loc("sentinel_charge_far")));
    public static final SoundEvent SENTINEL_CHARGE_VERYFAR = register("sentinel_charge_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("sentinel_charge_veryfar")));
    public static final SoundEvent SENTINEL_RELOAD_NORMAL = register("sentinel_reload_normal", SoundEvent.createVariableRangeEvent(Mod.loc("sentinel_reload_normal")));
    public static final SoundEvent SENTINEL_RELOAD_EMPTY = register("sentinel_reload_empty", SoundEvent.createVariableRangeEvent(Mod.loc("sentinel_reload_empty")));
    public static final SoundEvent SENTINEL_CHARGE = register("sentinel_charge", SoundEvent.createVariableRangeEvent(Mod.loc("sentinel_charge")));
    public static final SoundEvent SENTINEL_BOLT = register("sentinel_bolt", SoundEvent.createVariableRangeEvent(Mod.loc("sentinel_bolt")));

    public static final SoundEvent M_60_FIRE_1P = register("m_60_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("m_60_fire_1p")));
    public static final SoundEvent M_60_FIRE_3P = register("m_60_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("m_60_fire_3p")));
    public static final SoundEvent M_60_FAR = register("m_60_far", SoundEvent.createVariableRangeEvent(Mod.loc("m_60_far")));
    public static final SoundEvent M_60_VERYFAR = register("m_60_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("m_60_veryfar")));
    public static final SoundEvent M_60_RELOAD_NORMAL = register("m_60_reload_normal", SoundEvent.createVariableRangeEvent(Mod.loc("m_60_reload_normal")));
    public static final SoundEvent M_60_RELOAD_EMPTY = register("m_60_reload_empty", SoundEvent.createVariableRangeEvent(Mod.loc("m_60_reload_empty")));

    public static final SoundEvent SVD_FIRE_1P = register("svd_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("svd_fire_1p")));
    public static final SoundEvent SVD_FIRE_3P = register("svd_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("svd_fire_3p")));
    public static final SoundEvent SVD_FAR = register("svd_far", SoundEvent.createVariableRangeEvent(Mod.loc("svd_far")));
    public static final SoundEvent SVD_VERYFAR = register("svd_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("svd_veryfar")));
    public static final SoundEvent SVD_FIRE_1P_S = register("svd_fire_1p_s", SoundEvent.createVariableRangeEvent(Mod.loc("svd_fire_1p_s")));
    public static final SoundEvent SVD_FIRE_3P_S = register("svd_fire_3p_s", SoundEvent.createVariableRangeEvent(Mod.loc("svd_fire_3p_s")));
    public static final SoundEvent SVD_FAR_S = register("svd_far_s", SoundEvent.createVariableRangeEvent(Mod.loc("svd_far_s")));
    public static final SoundEvent SVD_RELOAD_NORMAL = register("svd_reload_normal", SoundEvent.createVariableRangeEvent(Mod.loc("svd_reload_normal")));
    public static final SoundEvent SVD_RELOAD_EMPTY = register("svd_reload_empty", SoundEvent.createVariableRangeEvent(Mod.loc("svd_reload_empty")));

    public static final SoundEvent AWM_FIRE_1P = register("awm_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("awm_fire_1p")));
    public static final SoundEvent AWM_FIRE_3P = register("awm_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("awm_fire_3p")));
    public static final SoundEvent AWM_FIRE_1P_S = register("awm_fire_1p_s", SoundEvent.createVariableRangeEvent(Mod.loc("awm_fire_1p_s")));
    public static final SoundEvent AWM_FIRE_3P_S = register("awm_fire_3p_s", SoundEvent.createVariableRangeEvent(Mod.loc("awm_fire_3p_s")));
    public static final SoundEvent AWM_FAR = register("awm_far", SoundEvent.createVariableRangeEvent(Mod.loc("awm_far")));
    public static final SoundEvent AWM_VERYFAR = register("awm_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("awm_veryfar")));
    public static final SoundEvent AWM_RELOAD_NORMAL = register("awm_reload_normal", SoundEvent.createVariableRangeEvent(Mod.loc("awm_reload_normal")));
    public static final SoundEvent AWM_RELOAD_EMPTY = register("awm_reload_empty", SoundEvent.createVariableRangeEvent(Mod.loc("awm_reload_empty")));
    public static final SoundEvent AWM_BOLT = register("awm_bolt", SoundEvent.createVariableRangeEvent(Mod.loc("awm_bolt")));

    public static final SoundEvent M_98B_FIRE_1P = register("m_98b_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("m_98b_fire_1p")));
    public static final SoundEvent M_98B_FIRE_3P = register("m_98b_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("m_98b_fire_3p")));
    public static final SoundEvent M_98B_FIRE_1P_S = register("m_98b_fire_1p_s", SoundEvent.createVariableRangeEvent(Mod.loc("m_98b_fire_1p_s")));
    public static final SoundEvent M_98B_FIRE_3P_S = register("m_98b_fire_3p_s", SoundEvent.createVariableRangeEvent(Mod.loc("m_98b_fire_3p_s")));
    public static final SoundEvent M_98B_FAR = register("m_98b_far", SoundEvent.createVariableRangeEvent(Mod.loc("m_98b_far")));
    public static final SoundEvent M_98B_VERYFAR = register("m_98b_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("m_98b_veryfar")));
    public static final SoundEvent M_98B_RELOAD_NORMAL = register("m_98b_reload_normal", SoundEvent.createVariableRangeEvent(Mod.loc("m_98b_reload_normal")));
    public static final SoundEvent M_98B_RELOAD_EMPTY = register("m_98b_reload_empty", SoundEvent.createVariableRangeEvent(Mod.loc("m_98b_reload_empty")));
    public static final SoundEvent M_98B_BOLT = register("m_98b_bolt", SoundEvent.createVariableRangeEvent(Mod.loc("m_98b_bolt")));

    public static final SoundEvent MARLIN_FIRE_1P = register("marlin_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("marlin_fire_1p")));
    public static final SoundEvent MARLIN_FIRE_3P = register("marlin_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("marlin_fire_3p")));
    public static final SoundEvent MARLIN_FAR = register("marlin_far", SoundEvent.createVariableRangeEvent(Mod.loc("marlin_far")));
    public static final SoundEvent MARLIN_VERYFAR = register("marlin_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("marlin_veryfar")));
    public static final SoundEvent MARLIN_PREPARE = register("marlin_prepare", SoundEvent.createVariableRangeEvent(Mod.loc("marlin_prepare")));
    public static final SoundEvent MARLIN_LOOP = register("marlin_loop", SoundEvent.createVariableRangeEvent(Mod.loc("marlin_loop")));
    public static final SoundEvent MARLIN_END = register("marlin_end", SoundEvent.createVariableRangeEvent(Mod.loc("marlin_end")));
    public static final SoundEvent MARLIN_BOLT = register("marlin_bolt", SoundEvent.createVariableRangeEvent(Mod.loc("marlin_bolt")));

    public static final SoundEvent M_870_FIRE_1P = register("m_870_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("m_870_fire_1p")));
    public static final SoundEvent M_870_FIRE_3P = register("m_870_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("m_870_fire_3p")));
    public static final SoundEvent M_870_FAR = register("m_870_far", SoundEvent.createVariableRangeEvent(Mod.loc("m_870_far")));
    public static final SoundEvent M_870_VERYFAR = register("m_870_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("m_870_veryfar")));
    public static final SoundEvent M_870_PREPARE_LOAD = register("m_870_prepare_load", SoundEvent.createVariableRangeEvent(Mod.loc("m_870_prepare_load")));
    public static final SoundEvent M_870_LOOP = register("m_870_loop", SoundEvent.createVariableRangeEvent(Mod.loc("m_870_loop")));
    public static final SoundEvent M_870_BOLT = register("m_870_bolt", SoundEvent.createVariableRangeEvent(Mod.loc("m_870_bolt")));

    public static final SoundEvent GLOCK_17_FIRE_1P = register("glock_17_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("glock_17_fire_1p")));
    public static final SoundEvent GLOCK_17_FIRE_3P = register("glock_17_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("glock_17_fire_3p")));
    public static final SoundEvent GLOCK_17_FAR = register("glock_17_far", SoundEvent.createVariableRangeEvent(Mod.loc("glock_17_far")));
    public static final SoundEvent GLOCK_17_VERYFAR = register("glock_17_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("glock_17_veryfar")));
    public static final SoundEvent GLOCK_17_RELOAD_NORMAL = register("glock_17_reload_normal", SoundEvent.createVariableRangeEvent(Mod.loc("glock_17_reload_normal")));
    public static final SoundEvent GLOCK_17_RELOAD_EMPTY = register("glock_17_reload_empty", SoundEvent.createVariableRangeEvent(Mod.loc("glock_17_reload_empty")));

    public static final SoundEvent GLOCK_18_FIRE_1P = register("glock_18_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("glock_17_fire_1p")));
    public static final SoundEvent GLOCK_18_FIRE_3P = register("glock_18_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("glock_17_fire_3p")));
    public static final SoundEvent GLOCK_18_FAR = register("glock_18_far", SoundEvent.createVariableRangeEvent(Mod.loc("glock_17_far")));
    public static final SoundEvent GLOCK_18_VERYFAR = register("glock_18_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("glock_17_veryfar")));
    public static final SoundEvent GLOCK_18_RELOAD_NORMAL = register("glock_18_reload_normal", SoundEvent.createVariableRangeEvent(Mod.loc("glock_17_reload_normal")));
    public static final SoundEvent GLOCK_18_RELOAD_EMPTY = register("glock_18_reload_empty", SoundEvent.createVariableRangeEvent(Mod.loc("glock_17_reload_empty")));

    public static final SoundEvent MP_443_FIRE_1P = register("mp_443_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("mp_443_fire_1p")));
    public static final SoundEvent MP_443_FIRE_3P = register("mp_443_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("mp_443_fire_3p")));
    public static final SoundEvent MP_443_FAR = register("mp_443_far", SoundEvent.createVariableRangeEvent(Mod.loc("glock_17_far")));
    public static final SoundEvent MP_443_VERYFAR = register("mp_443_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("glock_17_veryfar")));
    public static final SoundEvent MP_443_RELOAD_NORMAL = register("mp_443_reload_normal", SoundEvent.createVariableRangeEvent(Mod.loc("glock_17_reload_normal")));
    public static final SoundEvent MP_443_RELOAD_EMPTY = register("mp_443_reload_empty", SoundEvent.createVariableRangeEvent(Mod.loc("glock_17_reload_empty")));

    public static final SoundEvent M_1911_FIRE_1P = register("m_1911_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("m_1911_fire_1p")));
    public static final SoundEvent M_1911_FIRE_3P = register("m_1911_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("m_1911_fire_3p")));
    public static final SoundEvent M_1911_FAR = register("m_1911_far", SoundEvent.createVariableRangeEvent(Mod.loc("m_1911_far")));
    public static final SoundEvent M_1911_VERYFAR = register("m_1911_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("m_1911_veryfar")));
    public static final SoundEvent M_1911_RELOAD_NORMAL = register("m_1911_reload_normal", SoundEvent.createVariableRangeEvent(Mod.loc("glock_17_reload_normal")));
    public static final SoundEvent M_1911_RELOAD_EMPTY = register("m_1911_reload_empty", SoundEvent.createVariableRangeEvent(Mod.loc("glock_17_reload_empty")));

    public static final SoundEvent QBZ_95_FIRE_1P = register("qbz_95_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("qbz_95_fire_1p")));
    public static final SoundEvent QBZ_95_FIRE_3P = register("qbz_95_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("qbz_95_fire_3p")));
    public static final SoundEvent QBZ_95_FAR = register("qbz_95_far", SoundEvent.createVariableRangeEvent(Mod.loc("qbz_95_far")));
    public static final SoundEvent QBZ_95_VERYFAR = register("qbz_95_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("qbz_95_veryfar")));
    public static final SoundEvent QBZ_95_RELOAD_NORMAL = register("qbz_95_reload_normal", SoundEvent.createVariableRangeEvent(Mod.loc("qbz_95_reload_normal")));
    public static final SoundEvent QBZ_95_RELOAD_EMPTY = register("qbz_95_reload_empty", SoundEvent.createVariableRangeEvent(Mod.loc("qbz_95_reload_empty")));
    public static final SoundEvent QBZ_95_FIRE_1P_S = register("qbz_95_fire_1p_s", SoundEvent.createVariableRangeEvent(Mod.loc("qbz_95_fire_1p_s")));
    public static final SoundEvent QBZ_95_FIRE_3P_S = register("qbz_95_fire_3p_s", SoundEvent.createVariableRangeEvent(Mod.loc("qbz_95_fire_3p_s")));
    public static final SoundEvent QBZ_95_FAR_S = register("qbz_95_far_s", SoundEvent.createVariableRangeEvent(Mod.loc("ak_12_far_s")));
    public static final SoundEvent QBZ_95_VERYFAR_S = register("qbz_95_veryfar_s", SoundEvent.createVariableRangeEvent(Mod.loc("ak_12_veryfar_s")));

    public static final SoundEvent K_98_FIRE_1P = register("k_98_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("k_98_fire_1p")));
    public static final SoundEvent K_98_FIRE_3P = register("k_98_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("k_98_fire_3p")));
    public static final SoundEvent K_98_FAR = register("k_98_far", SoundEvent.createVariableRangeEvent(Mod.loc("k_98_far")));
    public static final SoundEvent K_98_VERYFAR = register("k_98_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("k_98_veryfar")));
    public static final SoundEvent K_98_RELOAD_EMPTY = register("k_98_reload_empty", SoundEvent.createVariableRangeEvent(Mod.loc("k_98_reload_empty")));
    public static final SoundEvent K_98_BOLT = register("k_98_bolt", SoundEvent.createVariableRangeEvent(Mod.loc("k_98_bolt")));
    public static final SoundEvent K_98_PREPARE = register("k_98_prepare", SoundEvent.createVariableRangeEvent(Mod.loc("k_98_prepare")));
    public static final SoundEvent K_98_LOOP = register("k_98_loop", SoundEvent.createVariableRangeEvent(Mod.loc("k_98_loop")));
    public static final SoundEvent K_98_END = register("k_98_end", SoundEvent.createVariableRangeEvent(Mod.loc("k_98_end")));

    public static final SoundEvent MOSIN_NAGANT_FIRE_1P = register("mosin_nagant_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("mosin_nagant_fire_1p")));
    public static final SoundEvent MOSIN_NAGANT_FIRE_3P = register("mosin_nagant_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("mosin_nagant_fire_3p")));
    public static final SoundEvent MOSIN_NAGANT_FAR = register("mosin_nagant_far", SoundEvent.createVariableRangeEvent(Mod.loc("mosin_nagant_far")));
    public static final SoundEvent MOSIN_NAGANT_VERYFAR = register("mosin_nagant_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("mosin_nagant_veryfar")));
    public static final SoundEvent MOSIN_NAGANT_BOLT = register("mosin_nagant_bolt", SoundEvent.createVariableRangeEvent(Mod.loc("mosin_nagant_bolt")));
    public static final SoundEvent MOSIN_NAGANT_PREPARE = register("mosin_nagant_prepare", SoundEvent.createVariableRangeEvent(Mod.loc("mosin_nagant_prepare")));
    public static final SoundEvent MOSIN_NAGANT_PREPARE_EMPTY = register("mosin_nagant_prepare_empty", SoundEvent.createVariableRangeEvent(Mod.loc("mosin_nagant_prepare_empty")));
    public static final SoundEvent MOSIN_NAGANT_LOOP = register("mosin_nagant_loop", SoundEvent.createVariableRangeEvent(Mod.loc("mosin_nagant_loop")));
    public static final SoundEvent MOSIN_NAGANT_END = register("mosin_nagant_end", SoundEvent.createVariableRangeEvent(Mod.loc("mosin_nagant_end")));

    public static final SoundEvent JAVELIN_FIRE_1P = register("javelin_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("javelin_fire_1p")));
    public static final SoundEvent JAVELIN_FIRE_3P = register("javelin_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("javelin_fire_3p")));
    public static final SoundEvent JAVELIN_FAR = register("javelin_far", SoundEvent.createVariableRangeEvent(Mod.loc("javelin_far")));
    public static final SoundEvent JAVELIN_RELOAD_EMPTY = register("javelin_reload_empty", SoundEvent.createVariableRangeEvent(Mod.loc("javelin_reload_empty")));

    public static final SoundEvent JAVELIN_LOCK = register("javelin_lock", SoundEvent.createVariableRangeEvent(Mod.loc("javelin_lock")));
    public static final SoundEvent JAVELIN_LOCKON = register("javelin_lockon", SoundEvent.createVariableRangeEvent(Mod.loc("javelin_lockon")));

    public static final SoundEvent SECONDARY_CATACLYSM_FIRE_1P = register("secondary_cataclysm_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("secondary_cataclysm_fire_1p")));
    public static final SoundEvent SECONDARY_CATACLYSM_FIRE_3P = register("secondary_cataclysm_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("secondary_cataclysm_fire_3p")));
    public static final SoundEvent SECONDARY_CATACLYSM_FAR = register("secondary_cataclysm_far", SoundEvent.createVariableRangeEvent(Mod.loc("secondary_cataclysm_far")));
    public static final SoundEvent SECONDARY_CATACLYSM_VERYFAR = register("secondary_cataclysm_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("secondary_cataclysm_veryfar")));
    public static final SoundEvent SECONDARY_CATACLYSM_FIRE_1P_CHARGE = register("secondary_cataclysm_fire_1p_charge", SoundEvent.createVariableRangeEvent(Mod.loc("secondary_cataclysm_fire_1p_charge")));
    public static final SoundEvent SECONDARY_CATACLYSM_FIRE_3P_CHARGE = register("secondary_cataclysm_fire_3p_charge", SoundEvent.createVariableRangeEvent(Mod.loc("secondary_cataclysm_fire_3p_charge")));
    public static final SoundEvent SECONDARY_CATACLYSM_FAR_CHARGE = register("secondary_cataclysm_far_charge", SoundEvent.createVariableRangeEvent(Mod.loc("secondary_cataclysm_far_charge")));
    public static final SoundEvent SECONDARY_CATACLYSM_VERYFAR_CHARGE = register("secondary_cataclysm_veryfar_charge", SoundEvent.createVariableRangeEvent(Mod.loc("secondary_cataclysm_veryfar_charge")));
    public static final SoundEvent SECONDARY_CATACLYSM_PREPARE_LOAD = register("secondary_cataclysm_prepare_load", SoundEvent.createVariableRangeEvent(Mod.loc("secondary_cataclysm_prepare_load")));
    public static final SoundEvent SECONDARY_CATACLYSM_LOOP = register("secondary_cataclysm_loop", SoundEvent.createVariableRangeEvent(Mod.loc("secondary_cataclysm_loop")));
    public static final SoundEvent SECONDARY_CATACLYSM_END = register("secondary_cataclysm_end", SoundEvent.createVariableRangeEvent(Mod.loc("secondary_cataclysm_end")));

    public static final SoundEvent MP_5_FIRE_1P = register("mp_5_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("mp_5_fire_1p")));
    public static final SoundEvent MP_5_FIRE_3P = register("mp_5_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("mp_5_fire_3p")));
    public static final SoundEvent MP_5_FAR = register("mp_5_far", SoundEvent.createVariableRangeEvent(Mod.loc("mp_5_far")));
    public static final SoundEvent MP_5_VERYFAR = register("mp_5_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("mp_5_veryfar")));
    public static final SoundEvent MP_5_RELOAD_NORMAL = register("mp_5_reload_normal", SoundEvent.createVariableRangeEvent(Mod.loc("mp_5_reload_normal")));
    public static final SoundEvent MP_5_RELOAD_EMPTY = register("mp_5_reload_empty", SoundEvent.createVariableRangeEvent(Mod.loc("mp_5_reload_empty")));
    public static final SoundEvent MP_5_FIRE_1P_S = register("mp_5_fire_1p_s", SoundEvent.createVariableRangeEvent(Mod.loc("mp_5_fire_1p_s")));
    public static final SoundEvent MP_5_FIRE_3P_S = register("mp_5_fire_3p_s", SoundEvent.createVariableRangeEvent(Mod.loc("mp_5_fire_3p_s")));

    public static final SoundEvent M_2_HB_FIRE_1P = register("m_2_hb_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("m_2_hb_fire_1p")));
    public static final SoundEvent M_2_HB_FIRE_3P = register("m_2_hb_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("m_2_hb_fire_3p")));
    public static final SoundEvent M_2_HB_FAR = register("m_2_hb_far", SoundEvent.createVariableRangeEvent(Mod.loc("m_2_hb_far")));
    public static final SoundEvent M_2_HB_VERYFAR = register("m_2_hb_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("m_2_hb_veryfar")));
    public static final SoundEvent M_2_HB_RELOAD_NORMAL = register("m_2_hb_reload_normal", SoundEvent.createVariableRangeEvent(Mod.loc("m_2_hb_reload_normal")));
    public static final SoundEvent M_2_HB_RELOAD_EMPTY = register("m_2_hb_reload_empty", SoundEvent.createVariableRangeEvent(Mod.loc("m_2_hb_reload_empty")));

    public static final SoundEvent QBZ_191_FIRE_1P = register("qbz_191_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("qbz_191_fire_1p")));
    public static final SoundEvent QBZ_191_FIRE_3P = register("qbz_191_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("qbz_191_fire_3p")));
    public static final SoundEvent QBZ_191_FIRE_1P_S = register("qbz_191_fire_1p_s", SoundEvent.createVariableRangeEvent(Mod.loc("qbz_191_fire_1p_s")));
    public static final SoundEvent QBZ_191_FIRE_3P_S = register("qbz_191_fire_3p_s", SoundEvent.createVariableRangeEvent(Mod.loc("qbz_191_fire_3p_s")));
    public static final SoundEvent QBZ_191_FAR = register("qbz_191_far", SoundEvent.createVariableRangeEvent(Mod.loc("qbz_191_far")));
    public static final SoundEvent QBZ_191_VERYFAR = register("qbz_191_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("qbz_191_veryfar")));
    public static final SoundEvent QBZ_191_RELOAD_NORMAL = register("qbz_191_reload_normal", SoundEvent.createVariableRangeEvent(Mod.loc("qbz_191_reload_normal")));
    public static final SoundEvent QBZ_191_RELOAD_EMPTY = register("qbz_191_reload_empty", SoundEvent.createVariableRangeEvent(Mod.loc("qbz_191_reload_empty")));
    public static final SoundEvent QBZ_191_FAR_S = register("qbz_191_far_s", SoundEvent.createVariableRangeEvent(Mod.loc("ak_12_far_s")));
    public static final SoundEvent QBZ_191_VERYFAR_S = register("qbz_191_veryfar_s", SoundEvent.createVariableRangeEvent(Mod.loc("ak_12_veryfar_s")));

    public static final SoundEvent MK_42_FIRE_1P = register("mk_42_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("mk_42_fire_1p")));
    public static final SoundEvent MK_42_FIRE_3P = register("mk_42_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("mk_42_fire_3p")));
    public static final SoundEvent MK_42_FAR = register("mk_42_far", SoundEvent.createVariableRangeEvent(Mod.loc("mk_42_far")));
    public static final SoundEvent MK_42_VERYFAR = register("mk_42_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("mk_42_veryfar")));
    public static final SoundEvent CANNON_RELOAD = register("cannon_reload", SoundEvent.createVariableRangeEvent(Mod.loc("cannon_reload")));
    public static final SoundEvent CANNON_ZOOM_IN = register("cannon_zoom_in", SoundEvent.createVariableRangeEvent(Mod.loc("cannon_zoom_in")));
    public static final SoundEvent CANNON_ZOOM_OUT = register("cannon_zoom_out", SoundEvent.createVariableRangeEvent(Mod.loc("cannon_zoom_out")));

    public static final SoundEvent BULLET_SUPPLY = register("bullet_supply", SoundEvent.createVariableRangeEvent(Mod.loc("bullet_supply")));
    public static final SoundEvent ADJUST_FOV = register("adjust_fov", SoundEvent.createVariableRangeEvent(Mod.loc("adjust_fov")));
    public static final SoundEvent DRONE_SOUND = register("drone_sound", SoundEvent.createVariableRangeEvent(Mod.loc("drone_sound")));
    public static final SoundEvent GRENADE_PULL = register("grenade_pull", SoundEvent.createVariableRangeEvent(Mod.loc("grenade_pull")));
    public static final SoundEvent GRENADE_THROW = register("grenade_throw", SoundEvent.createVariableRangeEvent(Mod.loc("grenade_throw")));

    public static final SoundEvent EDIT_MODE = register("edit_mode", SoundEvent.createVariableRangeEvent(Mod.loc("edit_mode")));
    public static final SoundEvent EDIT = register("edit", SoundEvent.createVariableRangeEvent(Mod.loc("edit")));
    public static final SoundEvent SHELL_CASING_NORMAL = register("shell_casing_normal", SoundEvent.createVariableRangeEvent(Mod.loc("shell_casing_normal")));
    public static final SoundEvent SHELL_CASING_SHOTGUN = register("shell_casing_shotgun", SoundEvent.createVariableRangeEvent(Mod.loc("shell_casing_shotgun")));
    public static final SoundEvent SHELL_CASING_50CAL = register("shell_casing_50cal", SoundEvent.createVariableRangeEvent(Mod.loc("shell_casing_50cal")));

    public static final SoundEvent OPEN = register("open", SoundEvent.createVariableRangeEvent(Mod.loc("open")));

    public static final SoundEvent CHARGE_RIFLE_FIRE_1P = register("charge_rifle_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("charge_rifle_fire_1p")));
    public static final SoundEvent CHARGE_RIFLE_FIRE_3P = register("charge_rifle_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("charge_rifle_fire_3p")));
    public static final SoundEvent CHARGE_RIFLE_FIRE_BOOM_1P = register("charge_rifle_fire_boom_1p", SoundEvent.createVariableRangeEvent(Mod.loc("charge_rifle_fire_boom_1p")));
    public static final SoundEvent CHARGE_RIFLE_FIRE_BOOM_3P = register("charge_rifle_fire_boom_3p", SoundEvent.createVariableRangeEvent(Mod.loc("charge_rifle_fire_boom_3p")));

    public static final SoundEvent ANNIHILATOR_FIRE_1P = register("annihilator_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("annihilator_fire_1p")));
    public static final SoundEvent ANNIHILATOR_FIRE_3P = register("annihilator_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("annihilator_fire_3p")));
    public static final SoundEvent ANNIHILATOR_FAR = register("annihilator_far", SoundEvent.createVariableRangeEvent(Mod.loc("annihilator_far")));
    public static final SoundEvent ANNIHILATOR_VERYFAR = register("annihilator_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("annihilator_veryfar")));
    public static final SoundEvent ANNIHILATOR_RELOAD = register("annihilator_reload", SoundEvent.createVariableRangeEvent(Mod.loc("annihilator_reload")));

    public static final SoundEvent BOAT_ENGINE = register("boat_engine", SoundEvent.createVariableRangeEvent(Mod.loc("boat_engine")));
    public static final SoundEvent VEHICLE_STRIKE = register("vehicle_strike", SoundEvent.createVariableRangeEvent(Mod.loc("vehicle_strike")));
    public static final SoundEvent WHEEL_CHAIR_ENGINE = register("wheel_chair_engine", SoundEvent.createVariableRangeEvent(Mod.loc("wheel_chair_engine")));
    public static final SoundEvent WHEEL_CHAIR_JUMP = register("wheel_chair_jump", SoundEvent.createVariableRangeEvent(Mod.loc("wheel_chair_jump")));

    public static final SoundEvent RADAR_SEARCH_START = register("radar_search_start", SoundEvent.createVariableRangeEvent(Mod.loc("radar_search_start")));
    public static final SoundEvent RADAR_SEARCH_IDLE = register("radar_search_idle", SoundEvent.createVariableRangeEvent(Mod.loc("radar_search_idle")));
    public static final SoundEvent RADAR_SEARCH_END = register("radar_search_end", SoundEvent.createVariableRangeEvent(Mod.loc("radar_search_end")));

    public static final SoundEvent HELICOPTER_ENGINE_START = register("helicopter_engine_start", SoundEvent.createVariableRangeEvent(Mod.loc("helicopter_engine_start")));
    public static final SoundEvent HELICOPTER_ENGINE = register("helicopter_engine", SoundEvent.createVariableRangeEvent(Mod.loc("helicopter_engine")));
    public static final SoundEvent HELICOPTER_CANNON_FIRE_1P = register("heli_cannon_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("heli_cannon_fire_1p")));
    public static final SoundEvent HELICOPTER_CANNON_FIRE_3P = register("heli_cannon_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("heli_cannon_fire_3p")));
    public static final SoundEvent HELICOPTER_CANNON_FAR = register("heli_cannon_far", SoundEvent.createVariableRangeEvent(Mod.loc("heli_cannon_far")));
    public static final SoundEvent HELICOPTER_CANNON_VERYFAR = register("heli_cannon_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("heli_cannon_veryfar")));
    public static final SoundEvent SMALL_ROCKET_FIRE_1P = register("small_rocket_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("small_rocket_fire_1p")));
    public static final SoundEvent SMALL_ROCKET_FIRE_3P = register("small_rocket_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("small_rocket_fire_3p")));

    public static final SoundEvent INTO_CANNON = register("into_cannon", SoundEvent.createVariableRangeEvent(Mod.loc("into_cannon")));
    public static final SoundEvent INTO_MISSILE = register("into_missile", SoundEvent.createVariableRangeEvent(Mod.loc("into_missile")));
    public static final SoundEvent MISSILE_RELOAD = register("missile_reload", SoundEvent.createVariableRangeEvent(Mod.loc("missile_reload")));

    public static final SoundEvent LOW_HEALTH = register("low_health", SoundEvent.createVariableRangeEvent(Mod.loc("low_health")));
    public static final SoundEvent NO_HEALTH = register("no_health", SoundEvent.createVariableRangeEvent(Mod.loc("no_health")));

    public static final SoundEvent LOCKING_WARNING = register("locking_warning", SoundEvent.createVariableRangeEvent(Mod.loc("locking_warning")));
    public static final SoundEvent LOCKED_WARNING = register("locked_warning", SoundEvent.createVariableRangeEvent(Mod.loc("locked_warning")));
    public static final SoundEvent MISSILE_WARNING = register("missile_warning", SoundEvent.createVariableRangeEvent(Mod.loc("missile_warning")));

    public static final SoundEvent DECOY_FIRE = register("decoy_fire", SoundEvent.createVariableRangeEvent(Mod.loc("decoy_fire")));
    public static final SoundEvent DECOY_RELOAD = register("decoy_reload", SoundEvent.createVariableRangeEvent(Mod.loc("decoy_reload")));
    public static final SoundEvent LUNGE_MINE_GROWL = register("lunge_mine_growl", SoundEvent.createVariableRangeEvent(Mod.loc("lunge_mine_growl")));
    public static final SoundEvent LAV_CANNON_FIRE_1P = register("lav_cannon_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("lav_fire_1p")));
    public static final SoundEvent LAV_CANNON_FIRE_3P = register("lav_cannon_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("lav_fire_3p")));
    public static final SoundEvent LAV_CANNON_FAR = register("lav_cannon_far", SoundEvent.createVariableRangeEvent(Mod.loc("lav_far")));
    public static final SoundEvent LAV_CANNON_VERYFAR = register("lav_cannon_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("lav_veryfar")));
    public static final SoundEvent LAV_ENGINE = register("lav_engine", SoundEvent.createVariableRangeEvent(Mod.loc("lav_engine")));
    public static final SoundEvent COAX_FIRE_1P = register("coax_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("coax_fire_1p")));

    public static final SoundEvent BMP_CANNON_FIRE_1P = register("bmp_cannon_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("bmp_fire_1p")));
    public static final SoundEvent BMP_CANNON_FIRE_3P = register("bmp_cannon_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("bmp_fire_3p")));
    public static final SoundEvent BMP_ENGINE = register("bmp_engine", SoundEvent.createVariableRangeEvent(Mod.loc("bmp_engine")));
    public static final SoundEvent BMP_MISSILE_FIRE_1P = register("bmp_missile_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("bmp_missile_fire_1p")));
    public static final SoundEvent BMP_MISSILE_FIRE_3P = register("bmp_missile_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("bmp_missile_fire_3p")));
    public static final SoundEvent BMP_MISSILE_RELOAD = register("bmp_missile_reload", SoundEvent.createVariableRangeEvent(Mod.loc("bmp_missile_reload")));

    public static final SoundEvent WHEEL_STEP = register("wheel_step", SoundEvent.createVariableRangeEvent(Mod.loc("wheel_step")));
    public static final SoundEvent LASER_TOWER_SHOOT = register("laser_tower_shoot", SoundEvent.createVariableRangeEvent(Mod.loc("laser_tower_shoot")));

    public static final SoundEvent YX_100_RELOAD = register("yx_100_reload", SoundEvent.createVariableRangeEvent(Mod.loc("yx_100_reload")));
    public static final SoundEvent YX_100_FIRE_1P = register("yx_100_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("yx_100_fire_1p")));
    public static final SoundEvent YX_100_FIRE_3P = register("yx_100_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("yx_100_fire_3p")));
    public static final SoundEvent YX_100_FAR = register("yx_100_far", SoundEvent.createVariableRangeEvent(Mod.loc("yx_100_far")));
    public static final SoundEvent YX_100_VERYFAR = register("yx_100_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("yx_100_veryfar")));
    public static final SoundEvent YX_100_ENGINE = register("yx_100_engine", SoundEvent.createVariableRangeEvent(Mod.loc("yx_100_engine")));

    public static final SoundEvent TURRET_TURN = register("turret_turn", SoundEvent.createVariableRangeEvent(Mod.loc("turret_turn")));
    public static final SoundEvent C4_BEEP = register("c4_beep", SoundEvent.createVariableRangeEvent(Mod.loc("c4_beep")));
    public static final SoundEvent C4_FINAL = register("c4_final", SoundEvent.createVariableRangeEvent(Mod.loc("c4_final")));
    public static final SoundEvent C4_THROW = register("c4_throw", SoundEvent.createVariableRangeEvent(Mod.loc("c4_throw")));
    public static final SoundEvent C4_DETONATOR_CLICK = register("c4_detonator_click", SoundEvent.createVariableRangeEvent(Mod.loc("c4_detonator_click")));

    public static final SoundEvent PRISM_FIRE_1P = register("prism_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("prism_fire_1p")));
    public static final SoundEvent PRISM_FIRE_3P = register("prism_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("prism_fire_3p")));
    public static final SoundEvent PRISM_FIRE_1P_2 = register("prism_fire_1p_2", SoundEvent.createVariableRangeEvent(Mod.loc("prism_fire_1p_2")));
    public static final SoundEvent PRISM_FIRE_3P_2 = register("prism_fire_3p_2", SoundEvent.createVariableRangeEvent(Mod.loc("prism_fire_3p_2")));
    public static final SoundEvent PRISM_ENGINE = register("prism_engine", SoundEvent.createVariableRangeEvent(Mod.loc("prism_engine")));

    public static final SoundEvent INSIDIOUS_FIRE_1P = register("insidious_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("insidious_fire_1p")));
    public static final SoundEvent INSIDIOUS_FIRE_3P = register("insidious_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("insidious_fire_3p")));
    public static final SoundEvent INSIDIOUS_FAR = register("insidious_far", SoundEvent.createVariableRangeEvent(Mod.loc("insidious_far")));
    public static final SoundEvent INSIDIOUS_VERYFAR = register("insidious_veryfar", SoundEvent.createVariableRangeEvent(Mod.loc("insidious_veryfar")));
    public static final SoundEvent INSIDIOUS_RELOAD_EMPTY = register("insidious_reload_empty", SoundEvent.createVariableRangeEvent(Mod.loc("insidious_reload_empty")));

    public static final SoundEvent SMOKE_FIRE = register("smoke_fire", SoundEvent.createVariableRangeEvent(Mod.loc("smoke_fire")));
    public static final SoundEvent HPJ_11_FIRE_3P = register("hpj_11_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("hpj_11_fire_3p")));
    public static final SoundEvent TRACK_MOVE = register("track_move", SoundEvent.createVariableRangeEvent(Mod.loc("track_move")));
    public static final SoundEvent ROCKET_FLY = register("rocket_fly", SoundEvent.createVariableRangeEvent(Mod.loc("rocket_fly")));
    public static final SoundEvent SHELL_FLY = register("shell_fly", SoundEvent.createVariableRangeEvent(Mod.loc("shell_fly")));
    public static final SoundEvent ROCKET_ENGINE = register("rocket_engine", SoundEvent.createVariableRangeEvent(Mod.loc("rocket_engine")));
    public static final SoundEvent VEHICLE_SWIM = register("vehicle_swim", SoundEvent.createVariableRangeEvent(Mod.loc("vehicle_swim")));
    public static final SoundEvent A_10_ENGINE = register("a10_engine", SoundEvent.createVariableRangeEvent(Mod.loc("a10_engine")));
    public static final SoundEvent A_10_FIRE = register("a10_fire", SoundEvent.createVariableRangeEvent(Mod.loc("a10_fire")));
    public static final SoundEvent BOMB_RELEASE = register("bomb_release", SoundEvent.createVariableRangeEvent(Mod.loc("bomb_release")));
    public static final SoundEvent BOMB_RELOAD = register("bomb_reload", SoundEvent.createVariableRangeEvent(Mod.loc("bomb_reload")));
    public static final SoundEvent MISSILE_START = register("missile_start", SoundEvent.createVariableRangeEvent(Mod.loc("missile_start")));
    public static final SoundEvent JET_LOCK = register("jet_lock", SoundEvent.createVariableRangeEvent(Mod.loc("jet_lock")));
    public static final SoundEvent JET_LOCKON = register("jet_lockon", SoundEvent.createVariableRangeEvent(Mod.loc("jet_lockon")));
    public static final SoundEvent FLY_LOOP = register("fly_loop", SoundEvent.createVariableRangeEvent(Mod.loc("fly_loop")));

    public static final SoundEvent AURELIA_SCEPTRE_FIRE_1P = register("aurelia_sceptre_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("aurelia_sceptre_fire_1p")));
    public static final SoundEvent AURELIA_SCEPTRE_FIRE_3P = register("aurelia_sceptre_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("aurelia_sceptre_fire_3p")));

    public static final SoundEvent DPS_GENERATOR_EVOLVE = register("dps_generator_evolve", SoundEvent.createVariableRangeEvent(Mod.loc("dps_generator_evolve")));
    public static final SoundEvent STEEL_PIPE_HIT = register("steel_pipe_hit", SoundEvent.createVariableRangeEvent(Mod.loc("steel_pipe_hit")));
    public static final SoundEvent STEEL_PIPE_DROP = register("steel_pipe_drop", SoundEvent.createVariableRangeEvent(Mod.loc("steel_pipe_drop")));
    public static final SoundEvent SM0KE_GRENADE_RELEASE = register("smoke_grenade_release", SoundEvent.createVariableRangeEvent(Mod.loc("smoke_grenade_release")));

    public static final SoundEvent HAND_WHEEL_ROT = register("hand_wheel_rot", SoundEvent.createVariableRangeEvent(Mod.loc("hand_wheel_rot")));
    public static final SoundEvent MEDIUM_ROCKET_FIRE = register("medium_rocket_fire", SoundEvent.createVariableRangeEvent(Mod.loc("medium_rocket_fire")));
    public static final SoundEvent TYPE_63_RELOAD = register("ty63_reload", SoundEvent.createVariableRangeEvent(Mod.loc("ty63_reload")));

    public static final SoundEvent PARACHUTE_OPEN = register("parachute_open", SoundEvent.createVariableRangeEvent(Mod.loc("parachute_open")));
    public static final SoundEvent PARACHUTE_CLOSE = register("parachute_close", SoundEvent.createVariableRangeEvent(Mod.loc("parachute_close")));

    public static final SoundEvent BL_132_FIRE_1P = register("bl_132_fire_1p", SoundEvent.createVariableRangeEvent(Mod.loc("bl_132_fire_1p")));
    public static final SoundEvent BL_132_FIRE_3P = register("bl_132_fire_3p", SoundEvent.createVariableRangeEvent(Mod.loc("bl_132_fire_3p")));
    public static final SoundEvent BL_132_RELOAD = register("bl_132_reload", SoundEvent.createVariableRangeEvent(Mod.loc("bl_132_reload")));

    public static final SoundEvent PTKM_1R_DEPLOY = register("ptkm_1r_deploy", SoundEvent.createVariableRangeEvent(Mod.loc("ptkm_1r_deploy")));

    public static final SoundEvent WAVEFORCE_TOWER_FIRE = register("waveforce_tower_fire", SoundEvent.createVariableRangeEvent(Mod.loc("waveforce_tower_fire")));

    private static SoundEvent register(String name, SoundEvent sound) {
        return Registry.register(BuiltInRegistries.SOUND_EVENT, Mod.loc(name), sound);
    }
}

