package cn.sh1rocu.superbwarfare.util.forge.itemhandler.entity.player;

import cn.sh1rocu.superbwarfare.util.forge.itemhandler.CombinedInvWrapper;
import cn.sh1rocu.superbwarfare.util.forge.itemhandler.entity.player.PlayerArmorInvWrapper;
import cn.sh1rocu.superbwarfare.util.forge.itemhandler.entity.player.PlayerMainInvWrapper;
import cn.sh1rocu.superbwarfare.util.forge.itemhandler.entity.player.PlayerOffhandInvWrapper;
import net.minecraft.world.entity.player.Inventory;

public class PlayerInvWrapper extends CombinedInvWrapper {
    public PlayerInvWrapper(Inventory inv) {
        super(new PlayerMainInvWrapper(inv), new PlayerArmorInvWrapper(inv), new PlayerOffhandInvWrapper(inv));
    }
}