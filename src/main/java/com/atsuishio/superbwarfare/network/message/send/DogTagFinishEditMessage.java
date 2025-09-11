package com.atsuishio.superbwarfare.network.message.send;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.init.ModItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.item.ItemStack;

public class DogTagFinishEditMessage {

    public static final ResourceLocation ID = Mod.loc("dogtag_finish_edit");

    private final short[][] colors;
    private final String name;
    private final boolean mainHand;

    public DogTagFinishEditMessage(short[][] colors, String name, boolean mainHand) {
        this.colors = colors;
        this.name = name;
        this.mainHand = mainHand;
    }

    @Environment(EnvType.CLIENT)
    public void send() {
        FriendlyByteBuf buffer = PacketByteBufs.create();
        buffer.writeVarInt(this.colors.length);
        for (short[] color : this.colors) {
            buffer.writeVarInt(color.length);
            for (short c : color) {
                buffer.writeShort(c);
            }
        }
        buffer.writeUtf(this.name);
        buffer.writeBoolean(this.mainHand);
        ClientPlayNetworking.send(ID, buffer);
    }

    public static DogTagFinishEditMessage decode(FriendlyByteBuf buffer) {
        short[][] colors = new short[buffer.readVarInt()][];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = new short[buffer.readVarInt()];
            for (int j = 0; j < colors[i].length; j++) {
                colors[i][j] = buffer.readShort();
            }
        }
        String name = buffer.readUtf();
        boolean mainHand = buffer.readBoolean();
        return new DogTagFinishEditMessage(colors, name, mainHand);
    }

    public static void handle(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
        var message = decode(buf);
        server.execute(() -> {
            if (player == null) return;

            ItemStack stack = message.mainHand ? player.getMainHandItem() : player.getOffhandItem();
            if (!stack.is(ModItems.DOG_TAG)) return;

            CompoundTag colorsTag = new CompoundTag();
            for (int i = 0; i < message.colors.length; i++) {
                int[] color = new int[message.colors[i].length];
                for (int j = 0; j < message.colors[i].length; j++) {
                    color[j] = message.colors[i][j];
                }
                colorsTag.putIntArray("Color" + i, color);
            }
            stack.getOrCreateTag().put("Colors", colorsTag);

            if (!message.name.isEmpty()) {
                stack.setHoverName(Component.literal(message.name));
            }
        });
    }
}
