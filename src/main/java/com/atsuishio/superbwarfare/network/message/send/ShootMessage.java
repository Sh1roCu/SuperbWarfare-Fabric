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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

public class ShootMessage {

    public static final ResourceLocation ID = Mod.loc("shoot");

    private final double spread;
    private final boolean zoom;
    private final @Nullable UUID uuid;

    public ShootMessage(double spread, boolean zoom, @Nullable UUID uuid) {
        this.spread = spread;
        this.zoom = zoom;
        this.uuid = uuid;
    }

    public static ShootMessage decode(FriendlyByteBuf buffer) {
        return new ShootMessage(buffer.readDouble(), buffer.readBoolean(), buffer.readOptional(FriendlyByteBuf::readUUID).orElse(null));
    }

    @Environment(EnvType.CLIENT)
    public void send() {
        FriendlyByteBuf buffer = PacketByteBufs.create();
        buffer.writeDouble(this.spread);
        buffer.writeBoolean(this.zoom);
        buffer.writeOptional(Optional.ofNullable(this.uuid), FriendlyByteBuf::writeUUID);
        ClientPlayNetworking.send(ID, buffer);
    }

    public static void handle(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
        var message = decode(buf);
        server.execute(() -> {
            if (player != null) {
                pressAction(player, message.spread, message.zoom, message.uuid);
            }
        });
    }

    public static void pressAction(Player player, double spread, boolean zoom, @Nullable UUID uuid) {
        var stack = player.getMainHandItem();
        if (!(stack.getItem() instanceof GunItem)) return;

        GunData.from(stack).shoot(player, spread, zoom, uuid);
    }
}
