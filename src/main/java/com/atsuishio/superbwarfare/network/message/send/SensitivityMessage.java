package com.atsuishio.superbwarfare.network.message.send;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.data.gun.GunData;
import com.atsuishio.superbwarfare.item.gun.GunItem;
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
import net.minecraft.world.item.ItemStack;

public class SensitivityMessage {

    public static final ResourceLocation ID = Mod.loc("sensitivity");

    private final boolean add;

    public SensitivityMessage(boolean add) {
        this.add = add;
    }

    @Environment(EnvType.CLIENT)
    public void send() {
        FriendlyByteBuf byteBuf = PacketByteBufs.create();
        byteBuf.writeBoolean(this.add);
        ClientPlayNetworking.send(ID, byteBuf);
    }

    public static SensitivityMessage decode(FriendlyByteBuf byteBuf) {
        return new SensitivityMessage(byteBuf.readBoolean());
    }

    public static void handle(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
        var message = decode(buf);
        server.execute(() -> {
            if (player == null) {
                return;
            }

            ItemStack stack = player.getMainHandItem();
            if (!(stack.getItem() instanceof GunItem)) return;

            var data = GunData.from(stack);
            if (message.add) {
                data.sensitivity.set(Math.min(10, data.sensitivity.get() + 1));
            } else {
                data.sensitivity.set(Math.max(-10, data.sensitivity.get() - 1));
            }
            player.displayClientMessage(Component.translatable("tips.superbwarfare.sensitivity", data.sensitivity.get()), true);
        });
    }
}
