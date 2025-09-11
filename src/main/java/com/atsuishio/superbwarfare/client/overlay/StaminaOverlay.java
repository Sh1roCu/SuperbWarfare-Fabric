package com.atsuishio.superbwarfare.client.overlay;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.client.RenderHelper;
import com.atsuishio.superbwarfare.config.client.DisplayConfig;
import com.atsuishio.superbwarfare.entity.vehicle.base.ArmedVehicleEntity;
import com.atsuishio.superbwarfare.event.ClientEventHandler;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;

@Environment(EnvType.CLIENT)
public class StaminaOverlay {

    public static final String ID = Mod.MODID + "_stamina";

    public static void render(GuiGraphics guiGraphics, float partialTick) {
        Player player = Minecraft.getInstance().player;

        if (player != null && ClientEventHandler.isEditing)
            return;
        if (player != null && player.getVehicle() instanceof ArmedVehicleEntity iArmedVehicle && iArmedVehicle.banHand(player))
            return;
        if (!shouldRender(player)) return;

        guiGraphics.pose().pushPose();

        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);

        if (ClientEventHandler.exhaustion) {
            RenderSystem.setShaderColor(1, 0, 0, (float) Mth.clamp(ClientEventHandler.switchTime, 0, 1));
        } else {
            RenderSystem.setShaderColor(1, 1, 1, (float) Mth.clamp(ClientEventHandler.switchTime, 0, 1));
        }

        RenderHelper.fill(guiGraphics, RenderType.guiOverlay(), (float) guiGraphics.guiWidth() / 2 - 90, guiGraphics.guiHeight() - 23, (float) guiGraphics.guiWidth() / 2 + 90, guiGraphics.guiHeight() - 24, -90, -16777216);
        RenderHelper.fill(guiGraphics, RenderType.guiOverlay(), (float) guiGraphics.guiWidth() / 2 - 90, (float) (guiGraphics.guiHeight() - 23), (float) (guiGraphics.guiWidth() / 2 + 90 - 1.8 * ClientEventHandler.stamina), guiGraphics.guiHeight() - 24, -90, -1);

        RenderSystem.setShaderColor(1, 1, 1, 1);

        guiGraphics.pose().popPose();
    }

    private static boolean shouldRender(Player player) {
        if (!DisplayConfig.STAMINA_HUD.get()) return false;
        if (player == null) return false;
        return ClientEventHandler.switchTime > 0;
    }
}
