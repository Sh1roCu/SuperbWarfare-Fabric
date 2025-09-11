package com.atsuishio.superbwarfare.network.message.send;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.init.ModItems;
import com.atsuishio.superbwarfare.item.ArtilleryIndicator;
import io.netty.buffer.Unpooled;
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

public class FiringParametersEditMessage {

    public static final ResourceLocation ID = Mod.loc("firing_parameters_edit");

    private final int posX;
    private final int posY;
    private final int posZ;
    private final int radius;
    private final boolean isDepressed;
    private final boolean mainHand;

    public FiringParametersEditMessage(int posX, int posY, int posZ, int radius, boolean isDepressed, boolean mainHand) {
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.radius = radius;
        this.isDepressed = isDepressed;
        this.mainHand = mainHand;
    }

    @Environment(EnvType.CLIENT)
    public void send() {
        FriendlyByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(this.posX);
        buffer.writeInt(this.posY);
        buffer.writeInt(this.posZ);
        buffer.writeInt(this.radius);
        buffer.writeBoolean(this.isDepressed);
        buffer.writeBoolean(this.mainHand);
        ClientPlayNetworking.send(ID, buffer);
    }

    public static FiringParametersEditMessage decode(FriendlyByteBuf buffer) {
        return new FiringParametersEditMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readBoolean(), buffer.readBoolean());
    }

    public static void handle(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
        var message = decode(buf);
        server.execute(() -> {
            if (player == null) return;

            ItemStack stack = message.mainHand ? player.getMainHandItem() : player.getOffhandItem();
            if (!stack.is(ModItems.FIRING_PARAMETERS) && !stack.is(ModItems.ARTILLERY_INDICATOR)) return;

            stack.getOrCreateTag().putInt("TargetX", message.posX);
            stack.getOrCreateTag().putInt("TargetY", message.posY);
            stack.getOrCreateTag().putInt("TargetZ", message.posZ);
            stack.getOrCreateTag().putInt("Radius", message.radius);
            stack.getOrCreateTag().putBoolean("IsDepressed", message.isDepressed);

            if (stack.getItem() instanceof ArtilleryIndicator indicator) {
                indicator.setTarget(stack, player);
            }
        });
    }
}
