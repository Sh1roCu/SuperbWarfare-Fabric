package com.atsuishio.superbwarfare.event;

import cn.sh1rocu.superbwarfare.mixin.accessor.MobAccessor;
import com.atsuishio.superbwarfare.config.server.SpawnConfig;
import com.atsuishio.superbwarfare.data.mob_guns.MobGunData;
import com.atsuishio.superbwarfare.entity.goal.GunShootGoal;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;

public class EntityUseGunEventHandler {

    public static boolean entityJoin(Entity entity, Level world, boolean loadedFromDisk) {
        if (loadedFromDisk || !SpawnConfig.SPAWN_MOB_WITH_GUNS.get()) return true;

        if (!(entity instanceof Mob mob)) return true;

        var data = MobGunData.from(mob);

        if (data == null || data.probability() <= 0 || data.probability() < entity.level().random.nextDouble()) {
            return true;
        }

        var gunData = data.getGunData();
        if (gunData == null) {
            return true;
        }

        // TODO 正确处理权重
        ((MobAccessor) mob).sw$goalSelector().addGoal(data.goalWeight(), new GunShootGoal<>(mob, data));

        if (data.backupAmmoCount() > 0) {
            gunData.virtualAmmo.set(data.backupAmmoCount());
        }

        if (data.spawnWithLoadedAmmo()) {
            gunData.reloadAmmo(mob);
        }

        mob.setItemInHand(InteractionHand.MAIN_HAND, gunData.stack);

        return true;
    }
}
