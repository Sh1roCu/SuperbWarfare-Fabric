package com.atsuishio.superbwarfare.network.message.send;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.init.ModSounds;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class DoubleJumpMessage {
    public static final ResourceLocation ID = Mod.loc("double_jump");
    public static final FriendlyByteBuf INSTANCE = PacketByteBufs.empty();

    public static void handle(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
        server.execute(() -> {
            if (player != null) {
                Level level = player.level();
                double x = player.getX();
                double y = player.getY();
                double z = player.getZ();
                level.playSound(null, BlockPos.containing(x, y, z), ModSounds.DOUBLE_JUMP, SoundSource.BLOCKS, 1, 1);

                Entity vehicle = player.getRootVehicle();
                if (vehicle != player) {
                    vehicle.setDeltaMovement(new Vec3(vehicle.getLookAngle().x, 0.8, vehicle.getLookAngle().z));
                }
            }
        });
    }
}
