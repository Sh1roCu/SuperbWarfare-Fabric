package com.atsuishio.superbwarfare.perk.functional;

import cn.sh1rocu.superbwarfare.util.fabric.ItemEnergyStorageHelper;
import com.atsuishio.superbwarfare.data.gun.GunData;
import com.atsuishio.superbwarfare.perk.Perk;
import com.atsuishio.superbwarfare.perk.PerkInstance;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class Regeneration extends Perk {

    public Regeneration() {
        super("regeneration", Type.FUNCTIONAL);
    }

    @Override
    public void tick(GunData data, PerkInstance instance, @Nullable Entity entity) {
        ItemStack stack = data.stack;
        ItemEnergyStorageHelper.fromStack(stack).ifPresent(storage -> {
            try (Transaction transaction = Transaction.openOuter()) {
                storage.insert((long) (instance.level() * storage.getCapacity() / 2000d), transaction);
                transaction.commit();
            }
        });
    }
}