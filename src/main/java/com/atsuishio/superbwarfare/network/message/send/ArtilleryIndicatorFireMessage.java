package com.atsuishio.superbwarfare.network.message.send;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.entity.vehicle.base.RemoteControllableTurret;
import com.atsuishio.superbwarfare.init.ModItems;
import com.atsuishio.superbwarfare.item.ArtilleryIndicator;
import com.atsuishio.superbwarfare.tools.EntityFindUtil;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;

import static com.atsuishio.superbwarfare.item.ArtilleryIndicator.TAG_CANNON;

public class ArtilleryIndicatorFireMessage {
    public static final FriendlyByteBuf INSTANCE = PacketByteBufs.empty();
    public static final ResourceLocation ID = Mod.loc("artillery_indicator_fire");

    public static void handle(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
        server.execute(() -> {
            if (player != null) {
                ItemStack stack = player.getMainHandItem();

                if (player.getMainHandItem().is(ModItems.MONITOR) && player.getOffhandItem().is(ModItems.ARTILLERY_INDICATOR)) {
                    stack = player.getOffhandItem();
                }

                if (stack.is(ModItems.ARTILLERY_INDICATOR)) {
                    ListTag tags = stack.getOrCreateTag().getList(TAG_CANNON, Tag.TAG_COMPOUND);
                    if (tags.isEmpty()) {
                        stack.getOrCreateTag().remove(ArtilleryIndicator.TAG_TYPE);
                        return;
                    }

                    for (int i = 0; i < tags.size(); i++) {
                        var tag = tags.getCompound(i);
                        Entity entity = EntityFindUtil.findEntity(player.level(), tag.getString("UUID"));

                        if (entity instanceof RemoteControllableTurret turret && turret.canRemoteFire()) {
                            Mod.queueServerWork(i % 5 + 1, () -> turret.remoteFire(player));
                        }
                    }
                }
            }
        });
    }
}
