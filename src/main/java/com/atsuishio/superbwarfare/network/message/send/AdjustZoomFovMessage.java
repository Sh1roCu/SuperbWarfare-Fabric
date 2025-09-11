package com.atsuishio.superbwarfare.network.message.send;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.data.gun.GunData;
import com.atsuishio.superbwarfare.init.ModItems;
import com.atsuishio.superbwarfare.init.ModSounds;
import com.atsuishio.superbwarfare.item.gun.GunItem;
import com.atsuishio.superbwarfare.tools.FormatTool;
import com.atsuishio.superbwarfare.tools.SoundTool;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;

public class AdjustZoomFovMessage {

    public static final ResourceLocation ID = Mod.loc("adjust_zoom_fov");

    private final double scroll;

    public AdjustZoomFovMessage(double scroll) {
        this.scroll = scroll;
    }

    @Environment(EnvType.CLIENT)
    public void send() {
        FriendlyByteBuf byteBuf = PacketByteBufs.create();
        byteBuf.writeDouble(this.scroll);
        ClientPlayNetworking.send(ID, byteBuf);
    }

    public static AdjustZoomFovMessage decode(FriendlyByteBuf byteBuf) {
        return new AdjustZoomFovMessage(byteBuf.readDouble());
    }

    public static void handle(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
        var message = decode(buf);
        server.execute(() -> {
            if (player == null) return;

            ItemStack stack = player.getMainHandItem();
            if (!(stack.getItem() instanceof GunItem)) return;
            var gun = GunData.from(stack);
            var data = gun.data();

            if (stack.is(ModItems.MINIGUN)) {
                double minRpm = 300 - 1200;
                double maxRpm = 2400 - 1200;

                var customRPM = data.getInt("CustomRPM");
                var targetCustomRPM = (int) Mth.clamp(customRPM + 50 * message.scroll, minRpm, maxRpm);

                if (targetCustomRPM == 1150 - 1200) {
                    targetCustomRPM = 1145 - 1200;
                } else {
                    targetCustomRPM = Math.toIntExact(Math.round(targetCustomRPM / 50.0) * 50);
                }

                data.putInt("CustomRPM", targetCustomRPM);

                player.displayClientMessage(Component.literal("RPM: " + FormatTool.format0D(customRPM + 1200)), true);
                if (customRPM > minRpm && customRPM < maxRpm) {
                    SoundTool.playLocalSound(player, ModSounds.ADJUST_FOV, 1f, 0.7f);
                }
            } else {
                double minZoom = gun.minZoom() - 1.25;
                double maxZoom = gun.maxZoom() - 1.25;
                double customZoom = data.getDouble("CustomZoom");
                data.putDouble("CustomZoom", Mth.clamp(customZoom + 0.5 * message.scroll, minZoom, maxZoom));

                if (customZoom > minZoom && customZoom < maxZoom) {
                    SoundTool.playLocalSound(player, ModSounds.ADJUST_FOV, 1f, 0.7f);
                }
            }
        });
    }
}
