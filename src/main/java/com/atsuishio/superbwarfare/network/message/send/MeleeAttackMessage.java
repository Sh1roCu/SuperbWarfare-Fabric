package com.atsuishio.superbwarfare.network.message.send;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.init.ModSounds;
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

import java.util.UUID;

public class MeleeAttackMessage {

    public static final ResourceLocation ID = Mod.loc("melee_attack");

    private final UUID uuid;

    public MeleeAttackMessage(UUID uuid) {
        this.uuid = uuid;
    }

    public static MeleeAttackMessage decode(FriendlyByteBuf buffer) {
        return new MeleeAttackMessage(buffer.readUUID());
    }

    @Environment(EnvType.CLIENT)
    public void send() {
        FriendlyByteBuf buffer = PacketByteBufs.create();
        buffer.writeUUID(this.uuid);
        ClientPlayNetworking.send(ID, buffer);
    }

    public static void handle(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
        var message = decode(buf);
        server.execute(() -> {
            if (player != null) {
                Entity lookingEntity = EntityFindUtil.findEntity(player.level(), String.valueOf(message.uuid));
                if (lookingEntity != null) {
                    player.level().playSound(null, lookingEntity.getOnPos(), ModSounds.MELEE_HIT, SoundSource.PLAYERS, 1, (float) ((2 * org.joml.Math.random() - 1) * 0.1f + 1.0f));
                    player.attack(lookingEntity);
                }
            }
        });
    }
}
