package com.atsuishio.superbwarfare.network.message.send;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.entity.vehicle.DroneEntity;
import com.atsuishio.superbwarfare.init.ModItems;
import com.atsuishio.superbwarfare.tools.EntityFindUtil;
import com.atsuishio.superbwarfare.tools.TraceTool;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class InteractMessage {
    public static final FriendlyByteBuf INSTANCE = PacketByteBufs.empty();
    public static final ResourceLocation ID = Mod.loc("interact");

    public static void handle(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
        server.execute(() -> {
            if (player != null) {
                handleInteract(player);
            }
        });
    }

    public static void handleInteract(Player player) {
        ItemStack stack = player.getMainHandItem();
        if (stack.is(ModItems.MONITOR) && stack.getOrCreateTag().getBoolean("Using") && stack.getOrCreateTag().getBoolean("Linked") && !player.getCooldowns().isOnCooldown(stack.getItem())) {
            DroneEntity drone = EntityFindUtil.findDrone(player.level(), stack.getOrCreateTag().getString("LinkedDrone"));

            if (drone != null) {
                Vec3 looking = Vec3.atLowerCornerOf(player.level().clip(new ClipContext(drone.getEyePosition(), drone.getEyePosition().add(drone.getLookAngle().scale(2)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player)).getBlockPos());
                BlockPos blockPos = BlockPos.containing(looking.x(), looking.y(), looking.z());
                player.level().getBlockState(blockPos).use(player.level(), player, InteractionHand.MAIN_HAND, BlockHitResult.miss(new Vec3(blockPos.getX(), blockPos.getY(), blockPos.getZ()), Direction.UP, blockPos));

                Entity lookingEntity = TraceTool.findLookingEntity(drone, 2);
                if (lookingEntity == null) return;

                player.attack(lookingEntity);
                player.getCooldowns().addCooldown(stack.getItem(), 13);
            }
        }
    }
}
