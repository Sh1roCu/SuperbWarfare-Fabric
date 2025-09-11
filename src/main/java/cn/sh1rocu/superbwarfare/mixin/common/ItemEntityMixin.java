package cn.sh1rocu.superbwarfare.mixin.common;

import cn.sh1rocu.superbwarfare.util.forge.EventHooks;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {
    public ItemEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Shadow
    public abstract ItemStack getItem();

    @Shadow
    private int pickupDelay;

    @Shadow
    @Nullable
    private UUID target;

    @Inject(
            method = "playerTouch",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getCount()I", shift = At.Shift.AFTER),
            cancellable = true)
    private void sw$itemPickup(Player player, CallbackInfo ci) {
        ItemEntity itemEntity = (ItemEntity) (Object) this;
        ItemStack itemstack = this.getItem();
        Item item = itemstack.getItem();
        int i = itemstack.getCount();
        int hook = EventHooks.onItemPickup(itemEntity, player);
        if (hook < 0)
            ci.cancel();
        ItemStack copy = this.getItem().copy();
        if (this.pickupDelay == 0 && (this.target == null || this.target.equals(player.getUUID())) && (hook == 1 || i <= 0 || player.getInventory().add(itemstack))) {
            i = copy.getCount() - itemstack.getCount();
            copy.setCount(i);
            EventHooks.firePlayerItemPickupEvent(player, itemEntity, copy);
            player.take(this, i);
            if (itemstack.isEmpty()) {
                this.discard();
                itemstack.setCount(i);
            }

            player.awardStat(Stats.ITEM_PICKED_UP.get(item), i);
            player.onItemPickup(itemEntity);
        }
        ci.cancel();
    }
}
