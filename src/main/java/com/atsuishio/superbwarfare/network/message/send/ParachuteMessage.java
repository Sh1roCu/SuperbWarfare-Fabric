package com.atsuishio.superbwarfare.network.message.send;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.init.ModItems;
import com.atsuishio.superbwarfare.init.ModSounds;
import com.atsuishio.superbwarfare.item.curio.ParachuteItem;
import dev.emi.trinkets.api.TrinketsApi;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.sounds.SoundSource;

public class ParachuteMessage {
    public static final ResourceLocation ID = Mod.loc("parachute");
    public static final FriendlyByteBuf INSTANCE = PacketByteBufs.empty();

    public static void handle(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
        server.execute(() -> {
            if (player == null) return;

            TrinketsApi.getTrinketComponent(player).flatMap(c -> c.getEquipped(ModItems.PARACHUTE).stream().findFirst()).ifPresent(s -> {
                var stack = s.getB();
                if (!player.getCooldowns().isOnCooldown(stack.getItem())) {
                    if (!stack.getOrCreateTag().getBoolean(ParachuteItem.TAG_OPEN) && player.getDeltaMovement().y < -0.6 && player.fallDistance > 4) {
                        stack.getOrCreateTag().putBoolean(ParachuteItem.TAG_OPEN, true);
                        player.getCooldowns().addCooldown(stack.getItem(), 10);
                        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.PARACHUTE_OPEN, SoundSource.PLAYERS, 1f, 1);
                    } else if (stack.getOrCreateTag().getBoolean(ParachuteItem.TAG_OPEN)) {
                        stack.getOrCreateTag().putBoolean(ParachuteItem.TAG_OPEN, false);
                        player.getCooldowns().addCooldown(stack.getItem(), 10);
                        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.PARACHUTE_CLOSE, SoundSource.PLAYERS, 1f, 1);
                    }
                }
            });
        });
    }
}
