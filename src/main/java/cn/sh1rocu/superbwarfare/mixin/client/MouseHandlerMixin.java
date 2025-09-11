package cn.sh1rocu.superbwarfare.mixin.client;

import cn.sh1rocu.superbwarfare.api.event.InputEvent;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MouseHandler.class)
public abstract class MouseHandlerMixin {
    @Shadow
    @Final
    private Minecraft minecraft;

    @Shadow
    public abstract boolean isLeftPressed();

    @Shadow
    public abstract boolean isMiddlePressed();

    @Shadow
    public abstract boolean isRightPressed();

    @Shadow
    public abstract double xpos();

    @Shadow
    public abstract double ypos();

    @Inject(method = "onPress", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;getOverlay()Lnet/minecraft/client/gui/screens/Overlay;"), cancellable = true)
    private void sw$onMouseButtonPre(long windowPointer, int button, int action, int modifiers, CallbackInfo ci) {
        InputEvent.MouseButton.Pre event = new InputEvent.MouseButton.Pre(button, action, modifiers);
        InputEvent.MouseButton.Pre.EVENT.invoker().onMousePre(event);

        if (event.isCanceled()) {
            ci.cancel();
        }
    }

    @Inject(method = "onPress", at = @At("TAIL"))
    private void sw$onMouseButtonPost(long windowPointer, int button, int action, int modifiers, CallbackInfo ci) {
        if (windowPointer == this.minecraft.getWindow().getWindow()) {
            InputEvent.MouseButton.Post event = new InputEvent.MouseButton.Post(button, action, modifiers);
            InputEvent.MouseButton.Post.EVENT.invoker().onMousePost(event);
        }
    }

    // 1.21
//    @Inject(method = "onScroll", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isSpectator()Z", ordinal = 0), cancellable = true)
//    private void sw$onMouseScroll(long windowPointer, double xOffset, double yOffset, CallbackInfo ci, @Local(ordinal = 3) double scrollDeltaX, @Local(ordinal = 4) double scrollDeltaY) {
//        InputEvent.MouseScrollingEvent event = new InputEvent.MouseScrollingEvent(scrollDeltaX, scrollDeltaY, this.isLeftPressed(), this.isMiddlePressed(), this.isRightPressed(), this.xpos(), this.ypos());
//        InputEvent.MouseScrollingEvent.EVENT.invoker().onMouseScroll(event);
//
//        if (event.isCanceled()) {
//            ci.cancel();
//        }
//    }

    // 1.20
    @Inject(method = "onScroll", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isSpectator()Z", ordinal = 0), cancellable = true)
    private void sw$onMouseScroll(long windowPointer, double xOffset, double yOffset, CallbackInfo ci, @Local(ordinal = 2) double scrollDelta) {
        InputEvent.MouseScrollingEvent event = new InputEvent.MouseScrollingEvent(scrollDelta, this.isLeftPressed(), this.isMiddlePressed(), this.isRightPressed(), this.xpos(), this.ypos());
        InputEvent.MouseScrollingEvent.EVENT.invoker().onMouseScroll(event);

        if (event.isCanceled()) {
            ci.cancel();
        }
    }
}