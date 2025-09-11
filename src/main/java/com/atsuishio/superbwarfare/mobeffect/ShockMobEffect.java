package com.atsuishio.superbwarfare.mobeffect;

import cn.sh1rocu.superbwarfare.api.extension.IEntityPersistentData;
import com.atsuishio.superbwarfare.init.ModDamageTypes;
import com.atsuishio.superbwarfare.init.ModMobEffects;
import com.atsuishio.superbwarfare.init.ModSounds;
import com.atsuishio.superbwarfare.network.message.receive.ClientIndicatorMessage;
import com.atsuishio.superbwarfare.tools.DamageHandler;
import io.github.fabricators_of_create.porting_lib.entity.events.LivingAttackEvent;
import io.github.fabricators_of_create.porting_lib.entity.events.LivingEntityEvents;
import io.github.fabricators_of_create.porting_lib.entity.events.living.MobEffectEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

public class ShockMobEffect extends MobEffect {

    public ShockMobEffect() {
        super(MobEffectCategory.HARMFUL, -256);
        addAttributeModifier(Attributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", -10.0F, AttributeModifier.Operation.ADDITION);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        Entity attacker;
        if (!((IEntityPersistentData) entity).sw$getPersistentData().contains("TargetShockAttacker")) {
            attacker = null;
        } else {
            attacker = entity.level().getEntity(((IEntityPersistentData) entity).sw$getPersistentData().getInt("TargetShockAttacker"));
        }

        DamageHandler.doDamage(entity, ModDamageTypes.causeShockDamage(entity.level().registryAccess(), attacker), 2 + (1.25f * amplifier));
        entity.level().playSound(null, entity.getOnPos(), ModSounds.ELECTRIC, SoundSource.PLAYERS, 1, 1);

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
        if (!instance.getEffect().equals(ModMobEffects.SHOCK)) {
            return;
        }

        if (living instanceof Player) {
            if (!living.level().isClientSide()) {
                living.level().playSound(null, BlockPos.containing(living.getX(), living.getY(), living.getZ()), ModSounds.SHOCK, SoundSource.HOSTILE, 1, 1);
            } else {
                living.level().playLocalSound(living.getX(), living.getY(), living.getZ(), ModSounds.SHOCK, SoundSource.HOSTILE, 1, 1, false);
            }
        }

        DamageHandler.doDamage(living, ModDamageTypes.causeShockDamage(living.level().registryAccess(),
                event.getEffectSource()), 2 + (1.25f * instance.getAmplifier()));

        if (event.getEffectSource() instanceof LivingEntity source) {
            ((IEntityPersistentData) living).sw$getPersistentData().putInt("TargetShockAttacker", source.getId());
        }
    }

    public static void onEffectExpired(MobEffectEvent.Expired event) {
        LivingEntity living = event.getEntity();

        MobEffectInstance instance = event.getEffectInstance();
        if (instance == null) {
            return;
        }

        if (instance.getEffect().equals(ModMobEffects.SHOCK)) {
            ((IEntityPersistentData) living).sw$getPersistentData().remove("TargetShockAttacker");
        }
    }

    public static void onEffectRemoved(MobEffectEvent.Remove event) {
        LivingEntity living = event.getEntity();

        MobEffectInstance instance = event.getEffectInstance();
        if (instance == null) {
            return;
        }

        if (instance.getEffect().equals(ModMobEffects.SHOCK)) {
            ((IEntityPersistentData) living).sw$getPersistentData().remove("TargetShockAttacker");
        }
    }

    public static void onLivingTick(LivingEntityEvents.LivingTickEvent event) {
        LivingEntity living = event.getEntity();

        if (living.hasEffect(ModMobEffects.SHOCK)) {
            living.setXRot((float) Mth.nextDouble(RandomSource.create(), -23, -36));
            living.xRotO = living.getXRot();
        }
    }

    public static void onEntityAttacked(LivingAttackEvent event) {
        if (event == null || event.getEntity() == null) {
            return;
        }
        DamageSource source = event.getSource();
        Entity entity = source.getDirectEntity();
        if (entity == null) {
            return;
        }
        if (entity instanceof LivingEntity living && living.hasEffect(ModMobEffects.SHOCK)) {
            event.setCanceled(true);
        }
    }
}
