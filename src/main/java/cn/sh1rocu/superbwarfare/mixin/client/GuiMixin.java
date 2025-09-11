package cn.sh1rocu.superbwarfare.mixin.client;

import com.atsuishio.superbwarfare.event.ClientEventHandler;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.atomic.AtomicBoolean;

@Mixin(Gui.class)
public class GuiMixin {
    @Inject(method = "renderHotbar", at = @At("HEAD"), cancellable = true)
    private void sw$onRenderSlot(float partialTick, GuiGraphics guiGraphics, CallbackInfo ci) {
        AtomicBoolean cancelled = new AtomicBoolean(false);
        ClientEventHandler.handleAvoidRenderingHotbar(cancelled);
        if (cancelled.get()) {
            ci.cancel();
        }
    }

    @Inject(method = "renderCrosshair", at = @At("HEAD"), cancellable = true)
    private void sw$renderCrosshair(GuiGraphics context, CallbackInfo ci) {
        AtomicBoolean cancelled = new AtomicBoolean(false);
        ClientEventHandler.handleRenderCrossHair(cancelled);
        if (cancelled.get()) {
            RenderSystem.defaultBlendFunc();
            ci.cancel();
        }
    }
}
