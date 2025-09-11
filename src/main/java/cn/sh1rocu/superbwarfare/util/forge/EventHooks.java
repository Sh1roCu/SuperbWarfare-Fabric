package cn.sh1rocu.superbwarfare.util.forge;

import cn.sh1rocu.superbwarfare.api.event.EntityItemPickupEvent;
import cn.sh1rocu.superbwarfare.api.event.ItemPickupEvent;
import cn.sh1rocu.superbwarfare.api.event.PlayerEvent;
import net.minecraft.world.Container;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class EventHooks {
    public static void firePlayerLoggedIn(Player player) {
        PlayerEvent.LOGGED_IN.invoker().post(new PlayerEvent.PlayerLoggedInEvent(player));
    }

    public static void firePlayerLoggedOut(Player player) {
        PlayerEvent.LOGGED_OUT.invoker().post(new PlayerEvent.PlayerLoggedOutEvent(player));
    }

    public static int onItemPickup(ItemEntity entityItem, Player player) {
        var event = new EntityItemPickupEvent(player, entityItem);
        EntityItemPickupEvent.ENTITY_ITEM_PICKUP.invoker().post(event);
        if (event.isCanceled()) return -1;
        return event.getResult() == EntityItemPickupEvent.Result.ALLOW ? 1 : 0;
    }

    public static void firePlayerItemPickupEvent(Player player, ItemEntity item, ItemStack clone) {
        ItemPickupEvent.PICKUP.invoker().post(new ItemPickupEvent(player, item, clone));
    }

    public static void firePlayerCraftingEvent(Player player, ItemStack crafted, Container craftMatrix) {
        PlayerEvent.ITEM_CRAFTED.invoker().post(new PlayerEvent.ItemCraftedEvent(player, crafted, craftMatrix));
    }
}
