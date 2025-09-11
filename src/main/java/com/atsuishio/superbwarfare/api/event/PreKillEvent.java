package com.atsuishio.superbwarfare.api.event;

import cn.sh1rocu.superbwarfare.api.event.BaseEvent;
import cn.sh1rocu.superbwarfare.api.event.ICancellableEvent;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.ApiStatus;

/**
 * 玩家击杀生物后，用于判断是否发送击杀播报/显示击杀指示
 */
@ApiStatus.Internal
@ApiStatus.AvailableSince("0.8.0")
public class PreKillEvent extends BaseEvent implements ICancellableEvent {

    private final LivingEntity entity;
    private final DamageSource source;
    private final LivingEntity target;

    public static final Event<SendKillMessageCallback> SEND_KILL_MESSAGE = EventFactory.createArrayBacked(SendKillMessageCallback.class, callbacks -> event -> {
        for (SendKillMessageCallback callback : callbacks) {
            callback.post(event);
        }
    });
    public static final Event<IndicatorCallback> INDICATOR = EventFactory.createArrayBacked(IndicatorCallback.class, callbacks -> event -> {
        for (IndicatorCallback callback : callbacks) {
            callback.post(event);
        }
    });

    private PreKillEvent(LivingEntity entity, DamageSource source, LivingEntity target) {
        this.entity = entity;
        this.source = source;
        this.target = target;
    }

    public static class SendKillMessage extends PreKillEvent {

        public SendKillMessage(LivingEntity player, DamageSource source, LivingEntity target) {
            super(player, source, target);
        }
    }

    public static class Indicator extends PreKillEvent {

        public Indicator(LivingEntity player, DamageSource source, LivingEntity target) {
            super(player, source, target);
        }
    }

    public LivingEntity getEntity() {
        return entity;
    }

    public DamageSource getSource() {
        return source;
    }

    public LivingEntity getTarget() {
        return target;
    }

    public interface SendKillMessageCallback {
        void post(SendKillMessage event);
    }

    public interface IndicatorCallback {
        void post(Indicator event);
    }
}
