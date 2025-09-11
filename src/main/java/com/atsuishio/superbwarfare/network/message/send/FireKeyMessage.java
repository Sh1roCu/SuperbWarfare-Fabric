package com.atsuishio.superbwarfare.network.message.send;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.data.gun.GunData;
import com.atsuishio.superbwarfare.data.gun.GunProp;
import com.atsuishio.superbwarfare.event.GunEventHandler;
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
import net.minecraft.world.item.ItemStack;

/**
 * 开火按键按下/松开时的处理
 */
public class FireKeyMessage {

    public static final ResourceLocation ID = Mod.loc("fire_key");

    private final int type;
    private final double power;
    private final boolean zoom;

    public FireKeyMessage(int type, double power, boolean zoom) {
        this.type = type;
        this.power = power;
        this.zoom = zoom;
    }

    public static FireKeyMessage decode(FriendlyByteBuf buffer) {
        return new FireKeyMessage(buffer.readInt(), buffer.readDouble(), buffer.readBoolean());
    }

    @Environment(EnvType.CLIENT)
    public void send() {
        FriendlyByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(this.type);
        buffer.writeDouble(this.power);
        buffer.writeBoolean(this.zoom);
        ClientPlayNetworking.send(ID, buffer);
    }

    public static void handle(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
        var message = decode(buf);
        server.execute(() -> {
            if (player != null) {
                pressAction(player, message.type, message.power, message.zoom);
            }
        });
    }

    public static void pressAction(Player player, int type, double power, boolean zoom) {
        if (player.isSpectator()) return;
        ItemStack stack = player.getMainHandItem();
        if (!(stack.getItem() instanceof GunItem)) return;
        var data = GunData.from(stack);

        handleGunBolt(player, stack);

        if (type == 0) {
            // 按下开火
            data.item.onFireKeyPress(data, player, zoom);
        } else if (type == 1) {
            // 松开开火
            data.item.onFireKeyRelease(data, player, power, zoom);
        }
    }

    private static void handleGunBolt(Player player, ItemStack stack) {
        if (!(stack.getItem() instanceof GunItem)) return;
        var data = GunData.from(stack);

        if (data.get(GunProp.BOLT_ACTION_TIME) > 0
                && data.hasEnoughAmmoToShoot(player)
                && data.bolt.actionTimer.get() == 0
                && !data.reloading()
                && !data.charging()
        ) {
            if (!player.getCooldowns().isOnCooldown(stack.getItem()) && data.bolt.needed.get()) {
                data.startBolt();
                GunEventHandler.playGunBoltSounds(player, data);
            }
        }
    }
}
