package com.atsuishio.superbwarfare.api.event;

import cn.sh1rocu.superbwarfare.api.event.BaseEvent;
import com.atsuishio.superbwarfare.data.gun.GunData;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

@ApiStatus.Internal
@ApiStatus.AvailableSince("0.8.0")
public class ReloadEvent extends BaseEvent {

    public final Entity shooter;
    public final GunData data;
    public final ItemStack stack;

    public static final Event<PreCallback> PRE = EventFactory.createArrayBacked(PreCallback.class, callbacks -> event -> {
        for (PreCallback callback : callbacks) {
            callback.post(event);
        }
    });
    public static final Event<PostCallback> POST = EventFactory.createArrayBacked(PostCallback.class, callbacks -> event -> {
        for (PostCallback callback : callbacks) {
            callback.post(event);
        }
    });

    private ReloadEvent(Entity shooter, GunData data) {
        this.shooter = shooter;
        this.data = data;
        this.stack = data.stack;
    }

    public static class Pre extends ReloadEvent {
        public Pre(@Nullable Entity shooter, GunData data) {
            super(shooter, data);
        }
    }

    public static class Post extends ReloadEvent {
        public Post(@Nullable Entity shooter, GunData data) {
            super(shooter, data);
        }
    }

    public @Nullable Entity getEntity() {
        return shooter;
    }

    public ItemStack getStack() {
        return stack;
    }

    public interface PreCallback {
        void post(Pre event);
    }

    public interface PostCallback {
        void post(Post event);
    }
}
