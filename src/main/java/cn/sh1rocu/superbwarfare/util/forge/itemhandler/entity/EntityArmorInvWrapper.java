package cn.sh1rocu.superbwarfare.util.forge.itemhandler.entity;

import cn.sh1rocu.superbwarfare.util.forge.itemhandler.entity.EntityEquipmentInvWrapper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

public class EntityArmorInvWrapper extends EntityEquipmentInvWrapper {
    public EntityArmorInvWrapper(final LivingEntity entity) {
        super(entity, EquipmentSlot.Type.ARMOR);
    }
}