package com.atsuishio.superbwarfare.network.message.send;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.item.gun.GunItem;
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
import net.minecraft.world.item.ItemStack;

public class SwitchScopeMessage {

    public static final ResourceLocation ID = Mod.loc("switch_scope");

    private final double scroll;

    public SwitchScopeMessage(double scroll) {
        this.scroll = scroll;
    }

    @Environment(EnvType.CLIENT)
    public void send() {
        FriendlyByteBuf byteBuf = PacketByteBufs.create();
        byteBuf.writeDouble(this.scroll);
        ClientPlayNetworking.send(ID, byteBuf);
    }

    public static SwitchScopeMessage decode(FriendlyByteBuf byteBuf) {
        return new SwitchScopeMessage(byteBuf.readDouble());
    }

    public static void handle(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
        server.execute(() -> {
            if (player == null) {
                return;
            }

            ItemStack stack = player.getMainHandItem();
            if (!(stack.getItem() instanceof GunItem)) return;

            var tag = stack.getOrCreateTag();
            tag.putBoolean("ScopeAlt", !tag.getBoolean("ScopeAlt"));
        });
    }

}
