package com.atsuishio.superbwarfare.network;

import cn.sh1rocu.superbwarfare.api.extension.IEntityAdditionalSpawnData;
import com.atsuishio.superbwarfare.network.message.receive.*;
import com.atsuishio.superbwarfare.network.message.send.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

import java.util.Objects;

public class NetworkRegistry {

    @Environment(EnvType.CLIENT)
    public static void registerS2CPackets() {
        playToClient(IEntityAdditionalSpawnData.EXTRA_DATA_PACKET, (client, handler, buf, responseSender) -> {
            int entityId = buf.readVarInt();
            buf.retain();
            client.execute(() -> {
                Entity entity = Objects.requireNonNull(client.level).getEntity(entityId);
                if (entity instanceof IEntityAdditionalSpawnData extra) {
                    extra.readSpawnData(buf);
                }
                buf.release();
            });
        });

        playToClient(PlayerVariablesSyncMessage.ID, PlayerVariablesSyncMessage::handle);
        playToClient(ShakeClientMessage.ID, ShakeClientMessage::handle);
        playToClient(ClientMotionSyncMessage.ID, ClientMotionSyncMessage::handle);
        playToClient(ClientIndicatorMessage.ID, ClientIndicatorMessage::handle);
        playToClient(LivingGunKillMessage.ID, LivingGunKillMessage::handle);
        playToClient(GunsDataMessage.ID, GunsDataMessage::handle);
        playToClient(ContainerDataMessage.ID, ContainerDataMessage::handle);
        playToClient(ShootClientMessage.ID, ShootClientMessage::handle);
        playToClient(DrawClientMessage.ID, DrawClientMessage::handle);
        playToClient(ResetCameraTypeMessage.ID, ResetCameraTypeMessage::handle);
        playToClient(RadarMenuOpenMessage.ID, RadarMenuOpenMessage::handle);
        playToClient(RadarMenuCloseMessage.ID, RadarMenuCloseMessage::handle);
        playToClient(ClientTacticalSprintSyncMessage.ID, ClientTacticalSprintSyncMessage::handle);
        playToClient(VehiclesDataMessage.ID, VehiclesDataMessage::handle);
        playToClient(ClientSetMotionMessage.ID, ClientSetMotionMessage::handle);
        playToClient(FinishAssemblingVehicleMessage.ID, FinishAssemblingVehicleMessage::handle);
    }

    public static void registerC2SPackets() {
        playToServer(LaserShootMessage.ID, LaserShootMessage::handle);
        playToServer(ShootMessage.ID, ShootMessage::handle);
        playToServer(DoubleJumpMessage.ID, DoubleJumpMessage::handle);
        playToServer(ParachuteMessage.ID, ParachuteMessage::handle);
        playToServer(VehicleMovementMessage.ID, VehicleMovementMessage::handle);
        playToServer(MeleeAttackMessage.ID, MeleeAttackMessage::handle);
        playToServer(LungeMineAttackMessage.ID, LungeMineAttackMessage::handle);
        playToServer(VehicleFireMessage.ID, VehicleFireMessage::handle);
        playToServer(AimVillagerMessage.ID, AimVillagerMessage::handle);
        playToServer(RadarChangeModeMessage.ID, RadarChangeModeMessage::handle);
        playToServer(RadarSetParametersMessage.ID, RadarSetParametersMessage::handle);
        playToServer(RadarSetPosMessage.ID, RadarSetPosMessage::handle);
        playToServer(RadarSetTargetMessage.ID, RadarSetTargetMessage::handle);
        playToServer(GunReforgeMessage.ID, GunReforgeMessage::handle);
        playToServer(SetPerkLevelMessage.ID, SetPerkLevelMessage::handle);
        playToServer(SwitchVehicleWeaponMessage.ID, SwitchVehicleWeaponMessage::handle);
        playToServer(AdjustZoomFovMessage.ID, AdjustZoomFovMessage::handle);
        playToServer(SwitchScopeMessage.ID, SwitchScopeMessage::handle);
        playToServer(FireKeyMessage.ID, FireKeyMessage::handle);
        playToServer(ReloadMessage.ID, ReloadMessage::handle);
        playToServer(FireModeMessage.ID, FireModeMessage::handle);
        playToServer(PlayerStopRidingMessage.ID, PlayerStopRidingMessage::handle);
        playToServer(ZoomMessage.ID, ZoomMessage::handle);
        playToServer(DroneFireMessage.ID, DroneFireMessage::handle);
        playToServer(SetFiringParametersMessage.ID, SetFiringParametersMessage::handle);
        playToServer(ArtilleryIndicatorFireMessage.ID, ArtilleryIndicatorFireMessage::handle);
        playToServer(SensitivityMessage.ID, SensitivityMessage::handle);
        playToServer(EditMessage.ID, EditMessage::handle);
        playToServer(InteractMessage.ID, InteractMessage::handle);
        playToServer(AdjustMortarAngleMessage.ID, AdjustMortarAngleMessage::handle);
        playToServer(ChangeVehicleSeatMessage.ID, ChangeVehicleSeatMessage::handle);
        playToServer(ShowChargingRangeMessage.ID, ShowChargingRangeMessage::handle);
        playToServer(TacticalSprintMessage.ID, TacticalSprintMessage::handle);
        playToServer(DogTagFinishEditMessage.ID, DogTagFinishEditMessage::handle);
        playToServer(MouseMoveMessage.ID, MouseMoveMessage::handle);
        playToServer(FiringParametersEditMessage.ID, FiringParametersEditMessage::handle);
        playToServer(UnloadMessage.ID, UnloadMessage::handle);
        playToServer(AssembleVehicleMessage.ID, AssembleVehicleMessage::handle);
    }

    @Environment(EnvType.CLIENT)
    public static void playToClient(ResourceLocation channelName, ClientPlayNetworking.PlayChannelHandler handler) {
        ClientPlayNetworking.registerGlobalReceiver(channelName, handler);
    }

    public static void playToServer(ResourceLocation channelName, ServerPlayNetworking.PlayChannelHandler handler) {
        ServerPlayNetworking.registerGlobalReceiver(channelName, handler);
    }
}
