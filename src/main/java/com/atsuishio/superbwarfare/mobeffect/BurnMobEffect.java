package com.atsuishio.superbwarfare.mobeffect;

import cn.sh1rocu.superbwarfare.api.extension.IEntityPersistentData;
import com.atsuishio.superbwarfare.init.ModDamageTypes;
import com.atsuishio.superbwarfare.init.ModMobEffects;
import com.atsuishio.superbwarfare.init.ModSounds;
import com.atsuishio.superbwarfare.network.message.receive.ClientIndicatorMessage;
import com.atsuishio.superbwarfare.tools.DamageHandler;
import io.github.fabricators_of_create.porting_lib.entity.events.LivingEntityEvents;
import io.github.fabricators_of_create.porting_lib.entity.events.living.MobEffectEvent;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class BurnMobEffect extends MobEffect {

    public BurnMobEffect() {
        super(MobEffectCategory.HARMFUL, -12708330);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        Entity attacker;
        if (!((IEntityPersistentData) entity).sw$getPersistentData().contains("BurnAttacker")) {
            attacker = null;
        } else {
            attacker = entity.level().getEntity(((IEntityPersistentData) entity).sw$getPersistentData().getInt("BurnAttacker"));
        }

        DamageHandler.doDamage(entity, ModDamageTypes.causeBurnDamage(entity.level().registryAccess(), attacker), 0.6f + (0.3f * amplifier));
        entity.invulnerableTime = 0;

        if (attacker instanceof ServerPlayer player) {
            player.level().playSound(null, player.blockPosition(), ModSounds.INDICATION, SoundSource.VOICE, 1, 1);
            new ClientIndicatorMessage(0, 5).send(player);
        }

    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 20 == 0;
    }

    public static void onEffectAdded(MobEffectEvent.Added event) {
        LivingEntity living = event.getEntity();

        MobEffectInstance instance = event.getEffectInstance();
        if (!instance.getEffect().equals(ModMobEffects.BURN)) {
            return;
        }

        DamageHandler.doDamage(living, new DamageSource(living.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.IN_FIRE), event.getEffectSource()), 0.6f + (0.3f * instance.getAmplifier()));
        living.invulnerableTime = 0;

        if (event.getEffectSource() instanceof LivingEntity source) {
            ((IEntityPersistentData) living).sw$getPersistentData().putInt("BurnAttacker", source.getId());
        }
    }

    public static void onEffectExpired(MobEffectEvent.Expired event) {
        LivingEntity living = event.getEntity();

        MobEffectInstance instance = event.getEffectInstance();
        if (instance == null) {
            return;
        }

        if (instance.getEffect().equals(ModMobEffects.BURN)) {
            ((IEntityPersistentData) living).sw$getPersistentData().remove("BurnAttacker");
        }
    }

    public static void onEffectRemoved(MobEffectEvent.Remove event) {
        LivingEntity living = event.getEntity();

        MobEffectInstance instance = event.getEffectInstance();
        if (instance == null) {
            return;
        }

        if (instance.getEffect().equals(ModMobEffects.BURN)) {
            ((IEntityPersistentData) living).sw$getPersistentData().remove("BurnAttacker");
        }
    }

    public static void onLivingTick(LivingEntityEvents.LivingTickEvent event) {
        LivingEntity living = event.getEntity();

        if (living.hasEffect(ModMobEffects.BURN)) {
            living.setRemainingFireTicks(2);
        }

        if (living.isInWater()) {
            living.removeEffect(ModMobEffects.BURN);
        }
    }
}
