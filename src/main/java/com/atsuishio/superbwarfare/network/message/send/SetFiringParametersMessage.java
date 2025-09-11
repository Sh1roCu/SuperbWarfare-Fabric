package com.atsuishio.superbwarfare.network.message.send;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.init.ModItems;
import com.atsuishio.superbwarfare.init.ModSounds;
import com.atsuishio.superbwarfare.item.ArtilleryIndicator;
import com.atsuishio.superbwarfare.tools.SoundTool;
import com.atsuishio.superbwarfare.tools.TraceTool;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class SetFiringParametersMessage {
    public static final FriendlyByteBuf INSTANCE = PacketByteBufs.empty();
    public static final ResourceLocation ID = Mod.loc("set_firing_parameters");

    public static void handle(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
        server.execute(() -> {
            if (player == null) return;

            ItemStack offStack = player.getOffhandItem();
            ItemStack mainStack = player.getMainHandItem();
            boolean lookAtEntity = false;
            Entity lookingEntity = TraceTool.findLookingEntity(player, 520);

            BlockHitResult result = player.level().clip(new ClipContext(player.getEyePosition(), player.getEyePosition().add(player.getViewVector(1).scale(512)),
                    ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player));
            Vec3 hitPos = result.getLocation();

            if (lookingEntity != null && !player.isShiftKeyDown()) {
                lookAtEntity = true;
            }

            if (offStack.is(ModItems.FIRING_PARAMETERS)) {
                if (lookAtEntity) {
                    offStack.getOrCreateTag().putDouble("TargetX", lookingEntity.getX());
                    offStack.getOrCreateTag().putDouble("TargetY", lookingEntity.getY());
                    offStack.getOrCreateTag().putDouble("TargetZ", lookingEntity.getZ());
                } else {
                    offStack.getOrCreateTag().putDouble("TargetX", hitPos.x());
                    offStack.getOrCreateTag().putDouble("TargetY", hitPos.y());
                    offStack.getOrCreateTag().putDouble("TargetZ", hitPos.z());
                }
                player.displayClientMessage(Component.translatable("tips.superbwarfare.mortar.target_pos").withStyle(ChatFormatting.GRAY)
                        .append(Component.literal("[" + offStack.getOrCreateTag().getInt("TargetX")
                                + "," + offStack.getOrCreateTag().getInt("TargetY")
                                + "," + offStack.getOrCreateTag().getInt("TargetZ") + "]")), true);
            }

            if (mainStack.getItem() instanceof ArtilleryIndicator indicator) {
                if (lookAtEntity) {
                    mainStack.getOrCreateTag().putDouble("TargetX", lookingEntity.getX());
                    mainStack.getOrCreateTag().putDouble("TargetY", lookingEntity.getY());
                    mainStack.getOrCreateTag().putDouble("TargetZ", lookingEntity.getZ());
                } else {
                    mainStack.getOrCreateTag().putDouble("TargetX", hitPos.x());
                    mainStack.getOrCreateTag().putDouble("TargetY", hitPos.y());
                    mainStack.getOrCreateTag().putDouble("TargetZ", hitPos.z());
                }
                player.displayClientMessage(Component.translatable("tips.superbwarfare.mortar.target_pos").withStyle(ChatFormatting.GRAY)
                        .append(Component.literal("[" + mainStack.getOrCreateTag().getInt("TargetX")
                                + "," + mainStack.getOrCreateTag().getInt("TargetY")
                                + "," + mainStack.getOrCreateTag().getInt("TargetZ") + "]")), true);

                SoundTool.playLocalSound(player, ModSounds.CANNON_ZOOM_IN, 2, 1);

                indicator.setTarget(mainStack, player);
            }
        });
    }
}
