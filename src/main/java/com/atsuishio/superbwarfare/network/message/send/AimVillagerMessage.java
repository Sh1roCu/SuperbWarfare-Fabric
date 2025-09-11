package com.atsuishio.superbwarfare.network.message.send;

import com.atsuishio.superbwarfare.Mod;
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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.gossip.GossipType;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.schedule.Activity;

public class AimVillagerMessage {

    public static final ResourceLocation ID = Mod.loc("aim_villager");

    private final int villagerId;

    public AimVillagerMessage(int villagerId) {
        this.villagerId = villagerId;
    }

    @Environment(EnvType.CLIENT)
    public void send() {
        FriendlyByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(this.villagerId);
        ClientPlayNetworking.send(ID, buffer);
    }

    public static AimVillagerMessage decode(FriendlyByteBuf buffer) {
        return new AimVillagerMessage(buffer.readInt());
    }

    public static void handle(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
        var message = decode(buf);
        server.execute(() -> {
            if (player == null) return;

            Entity entity = player.level().getEntity(message.villagerId);
            if (entity instanceof AbstractVillager abstractVillager) {
                if (entity instanceof Villager villager) {
                    villager.getGossips().add(player.getUUID(), GossipType.MINOR_NEGATIVE, 10);
                }
                abstractVillager.getBrain().setActiveActivityIfPossible(Activity.PANIC);
            }

        });
    }
}
