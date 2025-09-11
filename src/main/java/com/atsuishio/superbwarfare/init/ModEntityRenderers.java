package com.atsuishio.superbwarfare.init;

import com.atsuishio.superbwarfare.client.renderer.entity.*;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class ModEntityRenderers {

    public static void registerEntityRenderers() {
        EntityRendererRegistry.register(ModEntities.MORTAR, MortarRenderer::new);
        EntityRendererRegistry.register(ModEntities.SENPAI, SenpaiRenderer::new);
        EntityRendererRegistry.register(ModEntities.CLAYMORE, ClaymoreRenderer::new);
        EntityRendererRegistry.register(ModEntities.C_4, C4Renderer::new);
        EntityRendererRegistry.register(ModEntities.TASER_BULLET, TaserBulletProjectileRenderer::new);
        EntityRendererRegistry.register(ModEntities.GUN_GRENADE, GunGrenadeRenderer::new);
        EntityRendererRegistry.register(ModEntities.TARGET, TargetRenderer::new);
        EntityRendererRegistry.register(ModEntities.DPS_GENERATOR, DPSGeneratorRenderer::new);
        EntityRendererRegistry.register(ModEntities.RPG_ROCKET, RpgRocketRenderer::new);
        EntityRendererRegistry.register(ModEntities.SMALL_ROCKET, SmallRocketRenderer::new);
        EntityRendererRegistry.register(ModEntities.MEDIUM_ROCKET, MediumRocketRenderer::new);
        EntityRendererRegistry.register(ModEntities.MORTAR_SHELL, MortarShellRenderer::new);
        EntityRendererRegistry.register(ModEntities.CANNON_SHELL, CannonShellRenderer::new);
        EntityRendererRegistry.register(ModEntities.PROJECTILE, ProjectileEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.MK_42, Mk42Renderer::new);
        EntityRendererRegistry.register(ModEntities.DRONE, DroneRenderer::new);
        EntityRendererRegistry.register(ModEntities.HAND_GRENADE, HandGrenadeRenderer::new);
        EntityRendererRegistry.register(ModEntities.RGO_GRENADE, RgoGrenadeRenderer::new);
        EntityRendererRegistry.register(ModEntities.M18_SMOKE_GRENADE, M18SmokeGrenadeRenderer::new);
        EntityRendererRegistry.register(ModEntities.MLE_1934, Mle1934Renderer::new);
        EntityRendererRegistry.register(ModEntities.JAVELIN_MISSILE, JavelinMissileRenderer::new);
        EntityRendererRegistry.register(ModEntities.LASER, LaserEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.ANNIHILATOR, AnnihilatorRenderer::new);
        EntityRendererRegistry.register(ModEntities.SPEEDBOAT, SpeedboatRenderer::new);
        EntityRendererRegistry.register(ModEntities.WHEEL_CHAIR, WheelChairRenderer::new);
        EntityRendererRegistry.register(ModEntities.AH_6, Ah6Renderer::new);
        EntityRendererRegistry.register(ModEntities.FLARE_DECOY, FlareDecoyEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.SMOKE_DECOY, SmokeDecoyEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.LAV_150, Lav150Renderer::new);
        EntityRendererRegistry.register(ModEntities.SMALL_CANNON_SHELL, SmallCannonShellRenderer::new);
        EntityRendererRegistry.register(ModEntities.TOM_6, Tom6Renderer::new);
        EntityRendererRegistry.register(ModEntities.MELON_BOMB, MelonBombEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.BMP_2, Bmp2Renderer::new);
        EntityRendererRegistry.register(ModEntities.WG_MISSILE, WgMissileRenderer::new);
        EntityRendererRegistry.register(ModEntities.LASER_TOWER, LaserTowerRenderer::new);
        EntityRendererRegistry.register(ModEntities.YX_100, Yx100Renderer::new);
        EntityRendererRegistry.register(ModEntities.WATER_MASK, WaterMaskEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.PRISM_TANK, PrismTankRenderer::new);
        EntityRendererRegistry.register(ModEntities.SWARM_DRONE, SwarmDroneRenderer::new);
        EntityRendererRegistry.register(ModEntities.HPJ_11, Hpj11Renderer::new);
        EntityRendererRegistry.register(ModEntities.A_10A, A10Renderer::new);
        EntityRendererRegistry.register(ModEntities.MK_82, Mk82Renderer::new);
        EntityRendererRegistry.register(ModEntities.AGM_65, Agm65Renderer::new);
        EntityRendererRegistry.register(ModEntities.BLU_43, Blu43Renderer::new);
        EntityRendererRegistry.register(ModEntities.TM_62, Tm62Renderer::new);
        EntityRendererRegistry.register(ModEntities.PTKM_1R, Ptkm1rRenderer::new);
        EntityRendererRegistry.register(ModEntities.PTKM_PROJECTILE, PtkmProjectileRenderer::new);
        EntityRendererRegistry.register(ModEntities.TYPE_63, Type63Renderer::new);
        EntityRendererRegistry.register(ModEntities.MEDICAL_KIT, MedicalKitEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.BL_132, Bl132Renderer::new);
        EntityRendererRegistry.register(ModEntities.GRAPESHOT, GrapeshotRenderer::new);
        EntityRendererRegistry.register(ModEntities.VEHICLE_ASSEMBLING_TABLE, VehicleAssemblingTableVehicleRenderer::new);
        EntityRendererRegistry.register(ModEntities.WAVEFORCE_TOWER, WaveforceTowerRenderer::new);
    }
}
