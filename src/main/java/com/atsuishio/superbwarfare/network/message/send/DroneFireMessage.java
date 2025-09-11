package com.atsuishio.superbwarfare.network.message.send;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.entity.vehicle.DroneEntity;
import com.atsuishio.superbwarfare.init.ModItems;
import com.atsuishio.superbwarfare.init.ModSounds;
import com.atsuishio.superbwarfare.item.ArtilleryIndicator;
import com.atsuishio.superbwarfare.tools.EntityFindUtil;
import com.atsuishio.superbwarfare.tools.SoundTool;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector3f;

public class DroneFireMessage {

    public static final ResourceLocation ID = Mod.loc("drone_fire");

    private final Vector3f pos;

    public DroneFireMessage(Vector3f pos) {
        this.pos = pos;
    }

    public static DroneFireMessage decode(FriendlyByteBuf buffer) {
        return new DroneFireMessage(buffer.readVector3f());
    }

    @Environment(EnvType.CLIENT)
    public void send() {
        FriendlyByteBuf buffer = PacketByteBufs.create();
        buffer.writeVector3f(this.pos);
        ClientPlayNetworking.send(ID, buffer);
    }

    public static void handle(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
        var message = decode(buf);
        server.execute(() -> {
            if (player == null) return;

            ItemStack stack = player.getMainHandItem();

            if (stack.is(ModItems.MONITOR) && stack.getOrCreateTag().getBoolean("Using") && stack.getOrCreateTag().getBoolean("Linked")) {
                DroneEntity drone = EntityFindUtil.findDrone(player.level(), stack.getOrCreateTag().getString("LinkedDrone"));
                if (drone != null) {
                    if (player.getOffhandItem().is(ModItems.FIRING_PARAMETERS) || player.getOffhandItem().is(ModItems.ARTILLERY_INDICATOR)) {
                        ItemStack offStack = player.getOffhandItem();

                        offStack.getOrCreateTag().putDouble("TargetX", message.pos.x());
                        offStack.getOrCreateTag().putDouble("TargetY", message.pos.y());
                        offStack.getOrCreateTag().putDouble("TargetZ", message.pos.z());

                        player.displayClientMessage(Component.translatable("tips.superbwarfare.mortar.target_pos").withStyle(ChatFormatting.GRAY)
                                .append(Component.literal("[" + offStack.getOrCreateTag().getInt("TargetX")
                                        + "," + offStack.getOrCreateTag().getInt("TargetY")
                                        + "," + offStack.getOrCreateTag().getInt("TargetZ") + "]")), true);

                        SoundTool.playLocalSound(player, ModSounds.CANNON_ZOOM_IN, 2, 1);

                        if (offStack.getItem() instanceof ArtilleryIndicator indicator) {
                            indicator.setTarget(offStack, player);
                        }
                    } else {
                        drone.fire = true;
                    }
                }
            }
        });
    }
}
