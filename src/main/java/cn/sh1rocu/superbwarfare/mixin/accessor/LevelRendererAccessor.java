package cn.sh1rocu.superbwarfare.mixin.accessor;

import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.culling.Frustum;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LevelRenderer.class)
public interface LevelRendererAccessor {
    @Accessor("capturedFrustum")
    Frustum sw$capturedFrustum();

    @Accessor("cullingFrustum")
    Frustum sw$cullingFrustum();
}
