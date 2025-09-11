package cn.sh1rocu.superbwarfare.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;

public class EntityItemPickupEvent extends PlayerEvent implements ICancellableEvent {
    public static Event<EntityItemPickup> ENTITY_ITEM_PICKUP = EventFactory.createArrayBacked(EntityItemPickup.class, callbacks -> ((event) -> {
        for (EntityItemPickup e : callbacks) {
            e.post(event);
        }
    }));

    public interface EntityItemPickup {
        void post(EntityItemPickupEvent event);
    }

    private final ItemEntity item;
    private Result result = Result.DEFAULT;

    public EntityItemPickupEvent(Player player, ItemEntity item) {
        super(player);
        this.item = item;
    }

    public ItemEntity getItem() {
        return item;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result value) {
        result = value;
    }

    public enum Result {
        DENY,
        DEFAULT,
        ALLOW;
    }
}