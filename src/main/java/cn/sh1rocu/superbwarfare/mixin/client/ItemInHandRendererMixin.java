package cn.sh1rocu.superbwarfare.mixin.client;

import cn.sh1rocu.superbwarfare.api.event.RenderHandEvent;
import cn.sh1rocu.superbwarfare.api.extension.IItem;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nonnull;

// PortingLib
@Mixin(ItemInHandRenderer.class)
public abstract class ItemInHandRendererMixin {
    @Shadow
    private ItemStack mainHandItem;
    @Shadow
    private ItemStack offHandItem;

    @Unique
    private static int sb$mainHandSlot = 0;

    @Inject(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/player/LocalPlayer;getAttackStrengthScale(F)F",
                    shift = At.Shift.AFTER
            )
    )
    private void tick(CallbackInfo ci, @Local LocalPlayer clientPlayerEntity, @Local(ordinal = 0) ItemStack itemStack, @Local(ordinal = 1) ItemStack itemStack2) {
        if (!sw$shouldCauseReequipAnimation(mainHandItem, itemStack, clientPlayerEntity.getInventory().selected)) {
            mainHandItem = itemStack;
        }

        if (!sw$shouldCauseReequipAnimation(offHandItem, itemStack2, -1)) {
            offHandItem = itemStack2;
        }
    }

    @Inject(method = "renderArmWithItem", at = @At("HEAD"), cancellable = true)
    private void sw$renderHand(AbstractClientPlayer player, float tickDelta, float pitch, InteractionHand hand, float swingProgress, ItemStack stack, float equipProgress, PoseStack matrices, MultiBufferSource vertexConsumers, int light, CallbackInfo ci) {
        RenderHandEvent event = new RenderHandEvent(player, hand, stack, matrices, vertexConsumers, tickDelta, pitch, swingProgress, equipProgress, light);
        RenderHandEvent.CALLBACK.invoker().post(event);
        if (event.isCanceled()) {
            ci.cancel();
        }
    }

    @Unique
    private static boolean sw$shouldCauseReequipAnimation(@Nonnull ItemStack from, @Nonnull ItemStack to, int slot) {
        if (!from.isEmpty() && !to.isEmpty()) {
            boolean changed = false;
            if (slot != -1) {
                changed = slot != sb$mainHandSlot;
                sb$mainHandSlot = slot;
            }
            if (from.getItem() instanceof IItem handler) {
                return handler.shouldCauseReequipAnimation(from, to, changed);
            }
        }
        return true;
    }
}