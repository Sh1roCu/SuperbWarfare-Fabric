package com.atsuishio.superbwarfare.network.message.send;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.capability.player.PlayerVariable;
import com.atsuishio.superbwarfare.config.server.MiscConfig;
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

public record TacticalSprintMessage(boolean sprint) {

    public static final ResourceLocation ID = Mod.loc("tactical_sprint");

    @Environment(EnvType.CLIENT)
    public void send() {
        FriendlyByteBuf buffer = PacketByteBufs.create();
        buffer.writeBoolean(this.sprint);
        ClientPlayNetworking.send(ID, buffer);
    }

    public static TacticalSprintMessage decode(FriendlyByteBuf buffer) {
        return new TacticalSprintMessage(buffer.readBoolean());
    }

    public static void handle(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
        var message = decode(buf);
        server.execute(() -> {
            if (player == null) return;
            PlayerVariable.modify(player, capability -> capability.tacticalSprint = MiscConfig.ALLOW_TACTICAL_SPRINT.get() && message.sprint);
        });
    }
}
