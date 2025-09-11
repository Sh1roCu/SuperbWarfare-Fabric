package com.atsuishio.superbwarfare.perk.functional;

import com.atsuishio.superbwarfare.data.gun.GunData;
import com.atsuishio.superbwarfare.init.ModPerks;
import com.atsuishio.superbwarfare.item.gun.GunItem;
import com.atsuishio.superbwarfare.perk.Perk;
import com.atsuishio.superbwarfare.tools.DamageTypeTool;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.Collection;

public class PowerfulAttraction extends Perk {

    public PowerfulAttraction() {
        super("powerful_attraction", Type.FUNCTIONAL);
    }

    public static boolean onLivingDrops(LivingEntity target, DamageSource source, Collection<ItemEntity> drops, int lootingLevel, boolean recentlyHit) {
        if (source == null) return false;
        Entity sourceEntity = source.getEntity();
        if (!(sourceEntity instanceof LivingEntity living)) return false;
        ItemStack stack = living.getMainHandItem();
        if (!(stack.getItem() instanceof GunItem)) return false;

        int level = GunData.from(stack).perk.getLevel(ModPerks.POWERFUL_ATTRACTION);
        if (level > 0 && (DamageTypeTool.isGunDamage(source) || DamageTypeTool.isExplosionDamage(source))) {
            drops.forEach(itemEntity -> {
                ItemStack item = itemEntity.getItem();
                living.sw$getItemHandler(null).ifPresent(
                        cap -> {
                            for (int i = 0; i < cap.getSlots(); i++) {
                                int inserted;
                                for (inserted = item.getCount(); inserted > 0; inserted--) {
                                    var insertedStack = cap.insertItem(i, item.copyWithCount(inserted), true);
                                    if (insertedStack.getCount() != inserted || !ItemStack.isSameItemSameTags(insertedStack, item)) {
                                        break;
                                    }
                                }

                                if (inserted > 0) {
                                    cap.insertItem(i, item.copyWithCount(inserted), false);
                                    item.shrink(inserted);

                                    if (!item.isEmpty()) {
                                        var entity = new ItemEntity(living.level(), living.getX(), living.getY(), living.getZ(), item);
                                        entity.setPickUpDelay(10);
                                        living.level().addFreshEntity(entity);
                                    }
                                } else {
                                    var entity = new ItemEntity(living.level(), living.getX(), living.getY(), living.getZ(), item);
                                    entity.setPickUpDelay(10);
                                    living.level().addFreshEntity(entity);
                                }
                            }
                        }
                );
            });
            return true;
        }
        return false;
    }

    public static int onLivingExperienceDrop(int exp, Player player, LivingEntity entity) {
        if (player == null) return exp;

        ItemStack stack = player.getMainHandItem();
        if (!(stack.getItem() instanceof GunItem)) return exp;

        int level = GunData.from(stack).perk.getLevel(ModPerks.POWERFUL_ATTRACTION);
        if (level > 0) {
            player.giveExperiencePoints((int) (exp * (0.8f + 0.2f * level)));
            return 0;
        }
        return exp;
    }

    public static int onLootingLevel(DamageSource source, LivingEntity target, int currentLevel, boolean recentlyHit) {
        if (source == null) return currentLevel;
        Entity sourceEntity = source.getEntity();
        if (!(sourceEntity instanceof LivingEntity living)) return currentLevel;
        ItemStack stack = living.getMainHandItem();
        if (!(stack.getItem() instanceof GunItem)) return currentLevel;

        int level = GunData.from(stack).perk.getLevel(ModPerks.POWERFUL_ATTRACTION);
        if (level > 0 && (DamageTypeTool.isGunDamage(source) || DamageTypeTool.isExplosionDamage(source))) {
            return level / 4;
        }
        return currentLevel;
    }
}
