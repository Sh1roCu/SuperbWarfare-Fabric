package com.atsuishio.superbwarfare.network.message.send;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.menu.ReforgingTableMenu;
import com.atsuishio.superbwarfare.perk.Perk;
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
import net.minecraft.world.inventory.AbstractContainerMenu;

public class SetPerkLevelMessage {

    public static final ResourceLocation ID = Mod.loc("set_perk_level");

    int type;
    boolean add;

    public SetPerkLevelMessage(int type, boolean add) {
        this.type = type;
        this.add = add;
    }

    @Environment(EnvType.CLIENT)
    public void send() {
        FriendlyByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(this.type);
        buffer.writeBoolean(this.add);
        ClientPlayNetworking.send(ID, buffer);
    }

    public static SetPerkLevelMessage decode(FriendlyByteBuf buffer) {
        return new SetPerkLevelMessage(buffer.readInt(), buffer.readBoolean());
    }

    public static void handle(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
        var message = decode(buf);
        server.execute(() -> {
            if (player == null) {
                return;
            }

            AbstractContainerMenu abstractcontainermenu = player.containerMenu;
            if (abstractcontainermenu instanceof ReforgingTableMenu menu) {
                if (!menu.stillValid(player)) {
                    return;
                }

                menu.setPerkLevel(Perk.Type.values()[message.type], message.add, player.getAbilities().instabuild);
            }
        });
    }
}
