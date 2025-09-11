package com.atsuishio.superbwarfare.network.message.receive;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.network.ClientPacketHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.player.Player;

import java.util.Collection;
import java.util.Collections;

public record LivingGunKillMessage(int attackerId, int targetId, boolean headshot, ResourceKey<DamageType> damageType) {

    public static final ResourceLocation ID = Mod.loc("living_gunkill");

    public void send(ServerPlayer player) {
        send(Collections.singleton(player));
    }

    public void send(Collection<ServerPlayer> players) {
        FriendlyByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(this.attackerId);
        buffer.writeInt(this.targetId);
        buffer.writeBoolean(this.headshot);
        buffer.writeResourceKey(this.damageType);
        players.forEach(player -> ServerPlayNetworking.send(player, ID, buffer));
    }

    public static LivingGunKillMessage decode(FriendlyByteBuf buffer) {
        int attackerId = buffer.readInt();
        int targetId = buffer.readInt();
        boolean headshot = buffer.readBoolean();
        ResourceKey<DamageType> damageType = buffer.readResourceKey(Registries.DAMAGE_TYPE);
        return new LivingGunKillMessage(attackerId, targetId, headshot, damageType);
    }

    @Environment(EnvType.CLIENT)
    public static void handle(Minecraft client, ClientPacketListener handler, FriendlyByteBuf buf, PacketSender responseSender) {
        var message = decode(buf);
        client.execute(() -> {
            ClientLevel level = Minecraft.getInstance().level;
            if (level != null) {
                var entity = level.getEntity(message.attackerId);
                LivingEntity attacker;
                if (entity instanceof LivingEntity living) {
                    if (living instanceof Player player) {
                        attacker = player;
                    } else if (living instanceof OwnableEntity ownableEntity && ownableEntity.getOwner() instanceof Player) {
                        attacker = living;
                    } else {
                        attacker = null;
                    }
                } else {
                    attacker = null;
                }
                Entity target = level.getEntity(message.targetId);

                if (attacker != null && target != null) {
                    ClientPacketHandler.handleLivingKillMessage(attacker, target, message.headshot, message.damageType);
                }
            }
        });
    }
}
