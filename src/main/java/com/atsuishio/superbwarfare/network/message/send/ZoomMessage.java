package com.atsuishio.superbwarfare.network.message.send;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.entity.vehicle.base.VehicleEntity;
import com.atsuishio.superbwarfare.entity.vehicle.base.WeaponVehicleEntity;
import com.atsuishio.superbwarfare.init.ModItems;
import com.atsuishio.superbwarfare.init.ModSounds;
import com.atsuishio.superbwarfare.tools.SoundTool;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundStopSoundPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.sounds.SoundSource;

public class ZoomMessage {

    public static final ResourceLocation ID = Mod.loc("zoom");

    private final int type;

    public ZoomMessage(int type) {
        this.type = type;
    }

    public static ZoomMessage decode(FriendlyByteBuf buffer) {
        return new ZoomMessage(buffer.readInt());
    }

    @Environment(EnvType.CLIENT)
    public void send() {
        FriendlyByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(this.type);
        ClientPlayNetworking.send(ID, buffer);
    }

    public static void handle(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
        var message = decode(buf);
        server.execute(() -> {
            if (player == null) return;

            var vehicle = player.getVehicle();
            // 缩放音效播放条件: 载具是武器载具，且该位置有可用武器

            if (message.type == 0) {

                if (player.isPassenger()
                        && vehicle instanceof WeaponVehicleEntity weaponEntity
                        && vehicle instanceof VehicleEntity vehicleEntity
                        && weaponEntity.hasWeapon(vehicleEntity.getSeatIndex(player))
                        && weaponEntity.banHand(player)
                ) {
                    SoundTool.playLocalSound(player, ModSounds.CANNON_ZOOM_IN, 2, 1);
                }

            }

            if (message.type == 1) {
                if (player.isPassenger()
                        && vehicle instanceof WeaponVehicleEntity weaponEntity
                        && vehicle instanceof VehicleEntity vehicleEntity
                        && weaponEntity.hasWeapon(vehicleEntity.getSeatIndex(player))
                        && weaponEntity.banHand(player)
                ) {
                    SoundTool.playLocalSound(player, ModSounds.CANNON_ZOOM_OUT, 2, 1);
                }

                if (player.getMainHandItem().getItem() == ModItems.JAVELIN) {
                    var handItem = player.getMainHandItem();
                    var tag = handItem.getOrCreateTag();
                    tag.putBoolean("Seeking", false);
                    tag.putInt("SeekTime", 0);
                    tag.putString("TargetEntity", "none");
                    var clientboundstopsoundpacket = new ClientboundStopSoundPacket(new ResourceLocation(Mod.MODID, "javelin_lock"), SoundSource.PLAYERS);
                    player.connection.send(clientboundstopsoundpacket);
                }
            }
        });
    }

}
