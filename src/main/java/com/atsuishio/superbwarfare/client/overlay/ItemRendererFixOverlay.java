package com.atsuishio.superbwarfare.client.overlay;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.item.gun.GunItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

/**
 * 这个类的作用是在看不见的地方渲染一个第三人称的武器模型，别管为啥这么干
 * 反正删了这个绝对会出事
 */
@Environment(EnvType.CLIENT)
public class ItemRendererFixOverlay {

    public static final String ID = Mod.MODID + "_item_renderer_fix";

    public static void render(GuiGraphics guiGraphics, float partialTick) {
        Player player = Minecraft.getInstance().player;
        if (player == null) return;
        ItemStack stack = player.getMainHandItem();
        if (!(stack.getItem() instanceof GunItem)) return;

        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(-1145.0D, 0.0D, 0.0D);
        Minecraft.getInstance().gameRenderer.itemInHandRenderer.renderItem(player, stack,
                ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, false, guiGraphics.pose(), guiGraphics.bufferSource(), 0);
        guiGraphics.pose().popPose();
    }
}
