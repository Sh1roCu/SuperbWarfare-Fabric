package com.atsuishio.superbwarfare.item;

import cn.sh1rocu.superbwarfare.api.extension.IDamageable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;

public class NetheriteHammer extends Hammer implements IDamageable {

    public NetheriteHammer() {
        super(Tiers.NETHERITE, 13, -3.2f, new Properties().durability(2800).fireResistant());
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return false;
    }
}
