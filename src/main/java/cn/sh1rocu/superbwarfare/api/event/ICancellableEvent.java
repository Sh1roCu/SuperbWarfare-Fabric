package cn.sh1rocu.superbwarfare.api.event;

public interface ICancellableEvent {
    default void setCanceled(boolean canceled) {
        ((BaseEvent) this).isCanceled = canceled;
    }

    default boolean isCanceled() {
        return ((BaseEvent) this).isCanceled;
    }
}