package com.atsuishio.superbwarfare.event;

import cn.sh1rocu.superbwarfare.api.event.PlayerEvent;
import com.atsuishio.superbwarfare.tools.HitboxHelper;
import net.minecraft.world.entity.player.Player;

public class HitboxHelperEventHandler {

    public static void onPlayerTick(Player player) {
        HitboxHelper.onPlayerTick(player);
    }

    public static void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        HitboxHelper.onPlayerLoggedOut(event.getEntity());
    }
}
