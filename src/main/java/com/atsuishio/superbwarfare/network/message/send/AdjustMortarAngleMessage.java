package com.atsuishio.superbwarfare.network.message.send;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.entity.vehicle.MortarEntity;
import com.atsuishio.superbwarfare.init.ModSounds;
import com.atsuishio.superbwarfare.tools.SoundTool;
import com.atsuishio.superbwarfare.tools.TraceTool;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

import static com.atsuishio.superbwarfare.entity.vehicle.MortarEntity.PITCH;

public class AdjustMortarAngleMessage {

    public static final ResourceLocation ID = Mod.loc("adjust_mortar_angle");

    private final double scroll;

    public AdjustMortarAngleMessage(double scroll) {
        this.scroll = scroll;
    }

    @Environment(EnvType.CLIENT)
    public void send() {
        FriendlyByteBuf byteBuf = PacketByteBufs.create();
        byteBuf.writeDouble(this.scroll);
        ClientPlayNetworking.send(ID, byteBuf);
    }

    public static AdjustMortarAngleMessage decode(FriendlyByteBuf byteBuf) {
        return new AdjustMortarAngleMessage(byteBuf.readDouble());
    }

    public static void handle(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
        var message = decode(buf);
        server.execute(() -> {
            if (player == null) {
                return;
            }

            Entity looking = TraceTool.findLookingEntity(player, 6);
            if (looking == null) return;

            if (looking instanceof MortarEntity mortar) {
                mortar.getEntityData().set(PITCH, (float) Mth.clamp(mortar.getEntityData().get(PITCH) + 0.5 * message.scroll, -89, -20));
            }

            SoundTool.playLocalSound(player, ModSounds.ADJUST_FOV, 1f, 0.7f);
        });
    }
}
