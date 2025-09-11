package com.atsuishio.superbwarfare.network.message.send;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.init.ModDamageTypes;
import com.atsuishio.superbwarfare.init.ModSounds;
import com.atsuishio.superbwarfare.network.message.receive.ClientIndicatorMessage;
import com.atsuishio.superbwarfare.tools.DamageHandler;
import com.atsuishio.superbwarfare.tools.EntityFindUtil;
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
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

import java.util.UUID;

public class LaserShootMessage {

    public static final ResourceLocation ID = Mod.loc("laser_shoot");

    private final double damage;
    private final UUID uuid;
    private final boolean headshot;

    public LaserShootMessage(double damage, UUID uuid, boolean headshot) {
        this.damage = damage;
        this.uuid = uuid;
        this.headshot = headshot;
    }

    public static LaserShootMessage decode(FriendlyByteBuf buffer) {
        return new LaserShootMessage(buffer.readDouble(), buffer.readUUID(), buffer.readBoolean());
    }

    @Environment(EnvType.CLIENT)
    public void send() {
        FriendlyByteBuf buffer = PacketByteBufs.create();
        buffer.writeDouble(this.damage);
        buffer.writeUUID(this.uuid);
        buffer.writeBoolean(this.headshot);
        ClientPlayNetworking.send(ID, buffer);
    }

    public static void handle(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
        var message = decode(buf);
        server.execute(() -> {
            if (player != null) {
                pressAction(player, message.damage, message.uuid, message.headshot);
            }
        });
    }

    public static void pressAction(ServerPlayer player, double damage, UUID uuid, boolean headshot) {
        Level level = player.level();

        Entity entity = EntityFindUtil.findEntity(level, String.valueOf(uuid));

        if (entity != null) {
            if (headshot) {
                DamageHandler.doDamage(entity, ModDamageTypes.causeLaserHeadshotDamage(level.registryAccess(), player, player), (float) (2 * damage));
                player.level().playSound(null, player.blockPosition(), ModSounds.HEADSHOT, SoundSource.VOICE, 0.1f, 1);
                new ClientIndicatorMessage(1, 5).send(player);
            } else {
                DamageHandler.doDamage(entity, ModDamageTypes.causeLaserDamage(level.registryAccess(), player, player), (float) damage);
                player.level().playSound(null, player.blockPosition(), ModSounds.INDICATION, SoundSource.VOICE, 0.1f, 1);
                new ClientIndicatorMessage(0, 5).send(player);
            }
            entity.invulnerableTime = 0;
        }
    }
}
