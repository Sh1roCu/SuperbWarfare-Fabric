package com.atsuishio.superbwarfare.network.message.send;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.entity.vehicle.Hpj11Entity;
import com.atsuishio.superbwarfare.entity.vehicle.LaserTowerEntity;
import com.atsuishio.superbwarfare.entity.vehicle.WaveforceTowerEntity;
import com.atsuishio.superbwarfare.entity.vehicle.base.AutoAimable;
import com.atsuishio.superbwarfare.entity.vehicle.base.VehicleEntity;
import com.atsuishio.superbwarfare.menu.FuMO25Menu;
import com.atsuishio.superbwarfare.tools.EntityFindUtil;
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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.inventory.AbstractContainerMenu;

import java.util.UUID;
import java.util.stream.StreamSupport;

public class RadarSetTargetMessage {

    public static final ResourceLocation ID = Mod.loc("radar_set_target");

    private final UUID targetUUID;

    public RadarSetTargetMessage(UUID targetUUID) {
        this.targetUUID = targetUUID;
    }

    @Environment(EnvType.CLIENT)
    public void send() {
        FriendlyByteBuf buffer = PacketByteBufs.create();
        buffer.writeUUID(this.targetUUID);
        ClientPlayNetworking.send(ID, buffer);
    }

    public static RadarSetTargetMessage decode(FriendlyByteBuf buffer) {
        return new RadarSetTargetMessage(buffer.readUUID());
    }

    public static void handle(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
        var message = decode(buf);
        server.execute(() -> {
            if (player == null) return;

            AbstractContainerMenu menu = player.containerMenu;
            if (menu instanceof FuMO25Menu fuMO25Menu) {
                if (!player.containerMenu.stillValid(player)) {
                    return;
                }
                fuMO25Menu.getSelfPos().ifPresent(pos -> {
                    var entities = StreamSupport.stream(EntityFindUtil.getEntities(player.level()).getAll().spliterator(), false)
                            .filter(e -> (e instanceof AutoAimable && e instanceof OwnableEntity ownableEntity && ownableEntity.getOwner() == player && ownableEntity instanceof VehicleEntity vehicle && vehicle.distanceTo(player) <= 24))
                            .toList();
                    entities.forEach(e -> setTarget(e, message.targetUUID.toString()));
                });
            }
        });
    }

    public static void setTarget(Entity e, String uuid) {
        if (e instanceof LaserTowerEntity laserTower) {
            laserTower.getEntityData().set(LaserTowerEntity.TARGET_UUID, uuid);
        } else if (e instanceof Hpj11Entity hpj11Entity) {
            hpj11Entity.getEntityData().set(Hpj11Entity.TARGET_UUID, uuid);
        } else if (e instanceof WaveforceTowerEntity waveforceTowerEntity) {
            waveforceTowerEntity.getEntityData().set(WaveforceTowerEntity.TARGET_UUID, uuid);
        }
    }
}
