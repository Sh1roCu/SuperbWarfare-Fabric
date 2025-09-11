package com.atsuishio.superbwarfare.network.message.send;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.data.gun.FireMode;
import com.atsuishio.superbwarfare.data.gun.GunData;
import com.atsuishio.superbwarfare.data.gun.GunProp;
import com.atsuishio.superbwarfare.init.ModItems;
import com.atsuishio.superbwarfare.init.ModSounds;
import com.atsuishio.superbwarfare.item.gun.GunItem;
import com.atsuishio.superbwarfare.tools.SoundTool;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import team.reborn.energy.api.EnergyStorage;

public class FireModeMessage {
    public static final FriendlyByteBuf INSTANCE = PacketByteBufs.empty();
    public static final ResourceLocation ID = Mod.loc("fire_mode");

    public static void handle(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
        server.execute(() -> {
            if (player == null) return;

            changeFireMode(player);
        });
    }

    public static void changeFireMode(Player player) {
        ItemStack stack = player.getMainHandItem();
        if (stack.getItem() instanceof GunItem) {
            var data = GunData.from(stack);
            var tag = data.tag();
            var fireMode = data.fireMode.get();

            var mode = data.get(GunProp.AVAILABLE_FIRE_MODES);

            if (fireMode == FireMode.SEMI) {
                if (mode.contains(FireMode.BURST)) {
                    data.fireMode.set(FireMode.BURST);
                    playChangeModeSound(player);
                    return;
                }
                if (mode.contains(FireMode.AUTO)) {
                    data.fireMode.set(FireMode.AUTO);
                    playChangeModeSound(player);
                    return;
                }
            }

            if (fireMode == FireMode.BURST) {
                if (mode.contains(FireMode.AUTO)) {
                    data.fireMode.set(FireMode.AUTO);
                    playChangeModeSound(player);
                    return;
                }
                if (mode.contains(FireMode.SEMI)) {
                    data.fireMode.set(FireMode.SEMI);
                    playChangeModeSound(player);
                    return;
                }
            }

            if (fireMode == FireMode.AUTO) {
                if (mode.contains(FireMode.SEMI)) {
                    data.fireMode.set(FireMode.SEMI);
                    playChangeModeSound(player);
                    return;
                }
                if (mode.contains(FireMode.BURST)) {
                    data.fireMode.set(FireMode.BURST);
                    playChangeModeSound(player);
                    return;
                }
            }

            if (stack.getItem() == ModItems.SENTINEL
                    && !player.isSpectator()
                    && !(player.getCooldowns().isOnCooldown(stack.getItem()))
                    && GunData.from(stack).reload.time() == 0
                    && !GunData.from(stack).charging()) {

                for (var cell : player.getInventory().items) {
                    if (cell.is(ModItems.CELL)) {
                        boolean[] flag = {false};
                        EnergyStorage storage = EnergyStorage.ITEM.find(cell, ContainerItemContext.withConstant(cell));
                        if (storage != null) {
                            flag[0] = storage.getAmount() >= 0;
                        }

                        if (flag[0]) {
                            data.charge.starter.markStart();
                        }
                    }
                }
            }

            if (stack.getItem() == ModItems.JAVELIN) {
                tag.putBoolean("TopMode", !tag.getBoolean("TopMode"));
                if (player instanceof ServerPlayer serverPlayer) {
                    SoundTool.playLocalSound(serverPlayer, ModSounds.CANNON_ZOOM_OUT);
                }
            }
        }
    }

    private static void playChangeModeSound(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            SoundTool.playLocalSound(serverPlayer, ModSounds.FIRE_RATE);
        }
    }
}
