package com.atsuishio.superbwarfare.init;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.compat.CompatHolder;
import com.atsuishio.superbwarfare.perk.AmmoPerk;
import com.atsuishio.superbwarfare.perk.Perk;
import com.atsuishio.superbwarfare.perk.ammo.*;
import com.atsuishio.superbwarfare.perk.damage.*;
import com.atsuishio.superbwarfare.perk.functional.*;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffects;

@SuppressWarnings("unused")
public class ModPerks {

    public static final ResourceKey<Registry<Perk>> PERK_KEY = ResourceKey.createRegistryKey(Mod.loc("perk"));

    public static final Registry<Perk> PERKS = FabricRegistryBuilder.createSimple(PERK_KEY).attribute(RegistryAttribute.SYNCED).buildAndRegister();

    /**
     * Ammo Perks
     */
    public static final Perk AP_BULLET = register("ap_bullet", new APBullet());
    public static final Perk JHP_BULLET = register("jhp_bullet", new JHPBullet());
    public static final Perk HE_BULLET = register("he_bullet", new HEBullet());
    public static final Perk SILVER_BULLET = register("silver_bullet", new SilverBullet());
    public static final Perk POISONOUS_BULLET = register("poisonous_bullet",
            new AmmoPerk(new AmmoPerk.Builder("poisonous_bullet", Perk.Type.AMMO).bypassArmorRate(0.0f).damageRate(1.0f).speedRate(1.0f).rgb(48, 131, 6)
                    .mobEffect(MobEffects.POISON)));
    public static final Perk BEAST_BULLET = register("beast_bullet", new BeastBullet());
    public static final Perk LONGER_WIRE = register("longer_wire", new LongerWire());
    public static final Perk INCENDIARY_BULLET = register("incendiary_bullet", new IncendiaryBullet());
    public static final Perk MICRO_MISSILE = register("micro_missile", new MicroMissile());
    public static final Perk CUPID_ARROW = register("cupid_arrow", new CupidArrow());
    public static final Perk RIOT_BULLET = register("riot_bullet", new RiotBullet());
    public static final Perk PHASE_PENETRATING_BULLET = register("phase_penetrating_bullet", new PhasePenetratingBullet());

    /**
     * Functional Perks
     */
    public static final Perk HEAL_CLIP = register("heal_clip", new HealClip());
    public static final Perk FOURTH_TIMES_CHARM = register("fourth_times_charm", new FourthTimesCharm());
    public static final Perk SUBSISTENCE = register("subsistence", new Subsistence());
    public static final Perk FIELD_DOCTOR = register("field_doctor", new FieldDoctor());
    public static final Perk REGENERATION = register("regeneration", new Regeneration());
    public static final Perk TURBO_CHARGER = register("turbo_charger", new TurboCharger());
    public static final Perk POWERFUL_ATTRACTION = register("powerful_attraction", new PowerfulAttraction());
    public static final Perk INTELLIGENT_CHIP = register("intelligent_chip", new Perk("intelligent_chip", Perk.Type.FUNCTIONAL));

    /**
     * Damage Perks
     */
    public static final Perk KILL_CLIP = register("kill_clip", new KillClip());
    public static final Perk GUTSHOT_STRAIGHT = register("gutshot_straight", new GutshotStraight());
    public static final Perk KILLING_TALLY = register("killing_tally", new KillingTally());
    public static final Perk HEAD_SEEKER = register("head_seeker", new HeadSeeker());
    public static final Perk MONSTER_HUNTER = register("monster_hunter", new MonsterHunter());
    public static final Perk VOLT_OVERLOAD = register("volt_overload", new VoltOverload());
    public static final Perk DESPERADO = register("desperado", new Desperado());
    public static final Perk VORPAL_WEAPON = register("vorpal_weapon", new VorpalWeapon());
    public static final Perk MAGNIFICENT_HOWL = register("magnificent_howl", new MagnificentHowl());
    public static final Perk FIREFLY = register("firefly", new Firefly());
    public static final Perk FAIR_MEANS = register("fair_means", new FairMeans());

    private static Perk register(String name, Perk perk) {
        return Registry.register(PERKS, Mod.loc(name), perk);
    }

    public static void registerCompatPerks() {
        if (FabricLoader.getInstance().isModLoaded(CompatHolder.DMV)) {
            register("blade_bullet", new BladeBullet());
            register("bread_bullet", new BreadBullet());
        }
        if (FabricLoader.getInstance().isModLoaded(CompatHolder.VRC)) {
            register("curse_flame_bullet", new AmmoPerk(new AmmoPerk.Builder("curse_flame_bullet", Perk.Type.AMMO)
                    .bypassArmorRate(0.0f).damageRate(1.2f).speedRate(0.9f).rgb(0xB1, 0xC1, 0xF2).mobEffect(CompatHolder.VRC_CURSE_FLAME)));
            register("butterfly_bullet", new AmmoPerk(new AmmoPerk.Builder("butterfly_bullet", Perk.Type.AMMO)
                    .bypassArmorRate(0.0f)));
        }
    }

    public static void init() {
        registerCompatPerks();
    }
}
