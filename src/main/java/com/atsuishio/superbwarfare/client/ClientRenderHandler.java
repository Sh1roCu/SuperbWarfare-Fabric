package com.atsuishio.superbwarfare.client;

import com.atsuishio.superbwarfare.client.decorator.ContainerItemDecorator;
import com.atsuishio.superbwarfare.client.decorator.LuckyContainerItemDecorator;
import com.atsuishio.superbwarfare.client.model.curio.ParachuteModel;
import com.atsuishio.superbwarfare.client.overlay.*;
import com.atsuishio.superbwarfare.client.renderer.block.*;
import com.atsuishio.superbwarfare.client.renderer.curio.ParachuteRenderer;
import com.atsuishio.superbwarfare.client.tooltip.*;
import com.atsuishio.superbwarfare.client.tooltip.component.*;
import com.atsuishio.superbwarfare.init.ModBlockEntities;
import com.atsuishio.superbwarfare.init.ModItems;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import io.github.fabricators_of_create.porting_lib.item.api.client.IItemDecorator;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.item.Item;

import java.util.List;
import java.util.Map;

public class ClientRenderHandler {

    public static void registerTooltips() {
        TooltipComponentCallback.EVENT.register(data -> {
            if (data instanceof GunImageComponent gunImageComponent && !(data instanceof BocekImageComponent)
                    && !(data instanceof EnergyImageComponent) && !(data instanceof SentinelImageComponent) && !(data instanceof ChargingStationImageComponent)) {
                return new ClientGunImageTooltip(gunImageComponent);
            } else if (data instanceof BocekImageComponent bocekImageComponent) {
                return new ClientBocekImageTooltip(bocekImageComponent);
            } else if (data instanceof EnergyImageComponent energyImageComponent) {
                return new ClientEnergyImageTooltip(energyImageComponent);
            } else if (data instanceof CellImageComponent cellImageComponent) {
                return new ClientCellImageTooltip(cellImageComponent);
            } else if (data instanceof SentinelImageComponent sentinelImageComponent) {
                return new ClientSentinelImageTooltip(sentinelImageComponent);
            } else if (data instanceof ChargingStationImageComponent chargingStationImageComponent) {
                return new ClientChargingStationImageTooltip(chargingStationImageComponent);
            } else if (data instanceof DogTagImageComponent dogTagImageComponent) {
                return new ClientDogTagImageTooltip(dogTagImageComponent);
            } else {
                return null;
            }
        });
    }

    public static void registerRenderers() {
        BlockEntityRenderers.register(ModBlockEntities.CONTAINER, context -> new ContainerBlockEntityRenderer());
        BlockEntityRenderers.register(ModBlockEntities.FUMO_25, context -> new FuMO25BlockEntityRenderer());
        BlockEntityRenderers.register(ModBlockEntities.CHARGING_STATION, context -> new ChargingStationBlockEntityRenderer());
        BlockEntityRenderers.register(ModBlockEntities.SMALL_CONTAINER, context -> new SmallContainerBlockEntityRenderer());
        BlockEntityRenderers.register(ModBlockEntities.LUCKY_CONTAINER, context -> new LuckyContainerBlockEntityRenderer());
        BlockEntityRenderers.register(ModBlockEntities.VEHICLE_ASSEMBLING_TABLE, context -> new VehicleAssemblingTableBlockEntityRenderer());
    }

    public static void registerGuiOverlays() {
        HudRenderCallback.EVENT.register(KillMessageOverlay::render);
        HudRenderCallback.EVENT.register(IFFOverlay::render);
        HudRenderCallback.EVENT.register(VehicleTeamOverlay::render);
        HudRenderCallback.EVENT.register(JavelinHudOverlay::render);
        HudRenderCallback.EVENT.register(ArmorPlateOverlay::render);
        HudRenderCallback.EVENT.register(VehicleHudOverlay::render);
        HudRenderCallback.EVENT.register(VehicleMgHudOverlay::render);
        HudRenderCallback.EVENT.register(StaminaOverlay::render);
        HudRenderCallback.EVENT.register(Yx100SwarmDroneHudOverlay::render);
        HudRenderCallback.EVENT.register(AmmoBarOverlay::render);
        HudRenderCallback.EVENT.register(AmmoCountOverlay::render);
        HudRenderCallback.EVENT.register(ItemRendererFixOverlay::render);
        HudRenderCallback.EVENT.register(CannonHudOverlay::render);
        HudRenderCallback.EVENT.register(CrossHairOverlay::render);
        HudRenderCallback.EVENT.register(HeatBarOverlay::render);
        HudRenderCallback.EVENT.register(DroneHudOverlay::render);
        HudRenderCallback.EVENT.register(GrenadeLauncherOverlay::render);
        HudRenderCallback.EVENT.register(RedTriangleOverlay::render);
        HudRenderCallback.EVENT.register(HandsomeFrameOverlay::render);
        HudRenderCallback.EVENT.register(SpyglassRangeOverlay::render);
        HudRenderCallback.EVENT.register(HelicopterHudOverlay::render);
        HudRenderCallback.EVENT.register(AircraftOverlay::render);
        HudRenderCallback.EVENT.register(MortarInfoOverlay::render);
        HudRenderCallback.EVENT.register(Type63InfoOverlay::render);
    }

    public static void registerItemDecorations(Map<Item, List<IItemDecorator>> decorators) {
        decorators.put(ModItems.CONTAINER, List.of(new ContainerItemDecorator()));
        decorators.put(ModItems.LUCKY_CONTAINER, List.of(new LuckyContainerItemDecorator()));
    }

    public static void onClientSetup() {
        TrinketRendererRegistry.registerRenderer(ModItems.PARACHUTE, new ParachuteRenderer());
    }

    public static void registerLayers() {
        EntityModelLayerRegistry.registerModelLayer(ParachuteModel.LAYER_LOCATION, ParachuteModel::createBodyLayer);
    }
}
