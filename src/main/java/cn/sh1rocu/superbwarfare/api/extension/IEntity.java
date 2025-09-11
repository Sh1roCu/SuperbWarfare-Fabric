package cn.sh1rocu.superbwarfare.api.extension;

public interface IEntity {
    boolean isAddedToWorld();

    void onAddedToWorld();

    void onRemovedFromWorld();
}