package com.atsuishio.superbwarfare.api.event;

import cn.sh1rocu.superbwarfare.api.event.BaseEvent;
import com.atsuishio.superbwarfare.item.common.container.ContainerBlockItem;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Register Entities as a container
 */
@ApiStatus.AvailableSince("0.8.0")
public class RegisterContainersEvent extends BaseEvent {

    public static final List<ItemStack> CONTAINERS = new ArrayList<>();
    public static final Event<Callback> CALLBACK = EventFactory.createWithPhases(Callback.class, callbacks -> event -> {
        for (Callback callback : callbacks) {
            callback.post(event);
        }
    }, HIGH, Event.DEFAULT_PHASE);

    public <T extends Entity> void add(EntityType<T> type) {
        ItemStack stack = ContainerBlockItem.createInstance(type);
        CONTAINERS.add(stack);
    }

    public void add(Entity entity) {
        ItemStack stack = ContainerBlockItem.createInstance(entity);
        CONTAINERS.add(stack);
    }

    public interface Callback {
        void post(RegisterContainersEvent event);
    }
}
