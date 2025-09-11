package cn.sh1rocu.superbwarfare.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class PlayerEvent extends LivingEvent {
    private final Player player;

    public static final Event<PlayerLoggedInCallback> LOGGED_IN = EventFactory.createArrayBacked(PlayerLoggedInCallback.class, callbacks -> event -> {
        for (PlayerLoggedInCallback callback : callbacks) callback.post(event);
    });
    public static final Event<PlayerLoggedOutCallback> LOGGED_OUT = EventFactory.createArrayBacked(PlayerLoggedOutCallback.class, callbacks -> event -> {
        for (PlayerLoggedOutCallback callback : callbacks) callback.post(event);
    });
    public static final Event<ItemCraftedCallback> ITEM_CRAFTED = EventFactory.createArrayBacked(ItemCraftedCallback.class, callbacks -> event -> {
        for (ItemCraftedCallback callback : callbacks)
            callback.post(event);
    });

    public interface PlayerLoggedInCallback {
        void post(PlayerLoggedInEvent event);
    }

    public interface PlayerLoggedOutCallback {
        void post(PlayerLoggedOutEvent event);
    }

    public interface ItemCraftedCallback {
        void post(ItemCraftedEvent event);
    }

    public PlayerEvent(Player player) {
        super(player);
        this.player = player;
    }

    @Override
    public Player getEntity() {
        return player;
    }

    public static class PlayerLoggedInEvent extends PlayerEvent {
        public PlayerLoggedInEvent(Player player) {
            super(player);
        }
    }

    public static class PlayerLoggedOutEvent extends PlayerEvent {
        public PlayerLoggedOutEvent(Player player) {
            super(player);
        }
    }

    public static class ItemCraftedEvent extends PlayerEvent {
        private final ItemStack crafting;
        private final Container craftMatrix;

        public ItemCraftedEvent(Player player, ItemStack crafting, Container craftMatrix) {
            super(player);
            this.crafting = crafting;
            this.craftMatrix = craftMatrix;
        }

        public ItemStack getCrafting() {
            return this.crafting;
        }

        public Container getInventory() {
            return this.craftMatrix;
        }
    }
}