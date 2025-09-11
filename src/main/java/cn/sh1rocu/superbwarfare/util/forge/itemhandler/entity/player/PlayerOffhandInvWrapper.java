package cn.sh1rocu.superbwarfare.util.forge.itemhandler.entity.player;

import cn.sh1rocu.superbwarfare.util.forge.itemhandler.InvWrapper;
import cn.sh1rocu.superbwarfare.util.forge.itemhandler.RangedWrapper;
import net.minecraft.world.entity.player.Inventory;

public class PlayerOffhandInvWrapper extends RangedWrapper {
    public PlayerOffhandInvWrapper(Inventory inv) {
        super(new InvWrapper(inv), inv.items.size() + inv.armor.size(), inv.items.size() + inv.armor.size() + inv.offhand.size());
    }
}