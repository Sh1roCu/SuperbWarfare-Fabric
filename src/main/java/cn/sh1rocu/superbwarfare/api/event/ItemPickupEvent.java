package cn.sh1rocu.superbwarfare.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class ItemPickupEvent extends PlayerEvent {
    public static Event<ItemPickupEvent.ItemPickup> PICKUP = EventFactory.createArrayBacked(ItemPickupEvent.ItemPickup.class, callbacks -> ((event) -> {
        for (ItemPickupEvent.ItemPickup e : callbacks) {
            e.post(event);
        }
    }));

    public interface ItemPickup {
        void post(ItemPickupEvent event);
    }

    private final ItemEntity originalEntity;
    private final ItemStack stack;

    public ItemPickupEvent(Player player, ItemEntity entPickedUp, ItemStack stack) {
        super(player);
        this.originalEntity = entPickedUp;
        this.stack = stack;
    }

    public ItemStack getStack() {
        return stack;
    }

    public ItemEntity getOriginalEntity() {
        return originalEntity;
    }

}