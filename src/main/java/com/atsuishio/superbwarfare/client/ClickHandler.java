package com.atsuishio.superbwarfare.client;

import cn.sh1rocu.superbwarfare.api.event.InputEvent;
import com.atsuishio.superbwarfare.client.screens.WeaponEditScreen;
import com.atsuishio.superbwarfare.compat.CompatHolder;
import com.atsuishio.superbwarfare.compat.clothconfig.ClothConfigHelper;
import com.atsuishio.superbwarfare.config.client.ReloadConfig;
import com.atsuishio.superbwarfare.data.gun.FireMode;
import com.atsuishio.superbwarfare.data.gun.GunData;
import com.atsuishio.superbwarfare.data.gun.GunProp;
import com.atsuishio.superbwarfare.entity.vehicle.DroneEntity;
import com.atsuishio.superbwarfare.entity.vehicle.MortarEntity;
import com.atsuishio.superbwarfare.entity.vehicle.base.ArmedVehicleEntity;
import com.atsuishio.superbwarfare.entity.vehicle.base.CannonEntity;
import com.atsuishio.superbwarfare.entity.vehicle.base.VehicleEntity;
import com.atsuishio.superbwarfare.entity.vehicle.base.WeaponVehicleEntity;
import com.atsuishio.superbwarfare.event.ClientEventHandler;
import com.atsuishio.superbwarfare.event.ClientMouseHandler;
import com.atsuishio.superbwarfare.init.*;
import com.atsuishio.superbwarfare.item.ItemScreenProvider;
import com.atsuishio.superbwarfare.item.gun.GunItem;
import com.atsuishio.superbwarfare.network.message.send.*;
import com.atsuishio.superbwarfare.tools.EntityFindUtil;
import com.atsuishio.superbwarfare.tools.SeekTool;
import com.atsuishio.superbwarfare.tools.TraceTool;
import com.mojang.blaze3d.platform.InputConstants;
import committee.nova.mkb.api.IKeyBinding;
import committee.nova.mkb.keybinding.KeyConflictContext;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.lwjgl.glfw.GLFW;

import static com.atsuishio.superbwarfare.event.ClientEventHandler.*;

public class ClickHandler {
    public static boolean switchZoom = false;

    private static boolean notInGame() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return true;
        if (mc.getOverlay() != null) return true;
        if (mc.screen != null) return true;
        if (!mc.mouseHandler.isMouseGrabbed()) return true;
        return !mc.isWindowActive();
    }

    public static void onButtonReleased(InputEvent.MouseButton.Pre event) {
        if (notInGame()) return;
        if (event.getAction() != InputConstants.RELEASE) return;

        Player player = Minecraft.getInstance().player;
        if (player == null) return;

        if (player.hasEffect(ModMobEffects.SHOCK)) {
            return;
        }

        int button = event.getButton();
        if (button == ModKeyMappings.FIRE.key.getValue()) {
            handleWeaponFireRelease();
        }
        if (button == ModKeyMappings.HOLD_ZOOM.key.getValue()) {
            handleWeaponZoomRelease();
            return;
        }

        if (button == ModKeyMappings.SWITCH_ZOOM.key.getValue() && !switchZoom) {
            handleWeaponZoomRelease();
        }
    }

    private static boolean cancelFireKey(Player player, ItemStack stack) {
        return stack.getItem() instanceof GunItem || stack.is(ModItems.MONITOR) || stack.is(ModItems.LUNGE_MINE) || stack.is(ModItems.ARTILLERY_INDICATOR) || player.hasEffect(ModMobEffects.SHOCK)
                || (player.getVehicle() instanceof ArmedVehicleEntity iArmedVehicle && iArmedVehicle.banHand(player));
    }

    private static boolean cancelZoomKey(Player player, ItemStack stack) {
        return stack.getItem() instanceof GunItem
                || (player.getVehicle() instanceof ArmedVehicleEntity iArmedVehicle && iArmedVehicle.isDriver(player) && !stack.getItem().isEdible());
    }

    public static void onButtonPressed(InputEvent.MouseButton.Pre event) {
        if (notInGame()) return;
        if (event.getAction() != InputConstants.PRESS) return;

        var mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null) return;
        if (player.isSpectator()) return;

        ItemStack stack = player.getMainHandItem();

        int button = event.getButton();

        var fireKey = ModKeyMappings.FIRE.key;
        if (fireKey.getType() == InputConstants.Type.MOUSE
                && fireKey.getValue() == button
                && cancelFireKey(player, stack)
        ) {
            event.setCanceled(true);
        }

        if (player.hasEffect(ModMobEffects.SHOCK)) {
            return;
        }

        var zoomKey = ModKeyMappings.HOLD_ZOOM.key;
        if (zoomKey.getType() == InputConstants.Type.MOUSE
                && zoomKey.getValue() == button
                && cancelZoomKey(player, stack)
        ) {
            event.setCanceled(true);
        }

        if (button == GLFW.GLFW_MOUSE_BUTTON_MIDDLE) {
            if (player.hasEffect(ModMobEffects.SHOCK)) {
                event.setCanceled(true);
                return;
            }
            if (stack.is(ModItems.ARTILLERY_INDICATOR)) {
                event.setCanceled(true);
            }
            if (stack.is(ModItems.MONITOR) && player.getOffhandItem().is(ModItems.ARTILLERY_INDICATOR)) {
                event.setCanceled(true);
            }
        }

        if (button == ModKeyMappings.MARK.key.getValue()) {
            if (stack.is(ModItems.ARTILLERY_INDICATOR)) {
                ClientPlayNetworking.send(SetFiringParametersMessage.ID, SetFiringParametersMessage.INSTANCE);
            }
            if (stack.is(ModItems.MONITOR) && player.getOffhandItem().is(ModItems.ARTILLERY_INDICATOR)) {
                droneLeftClick(stack, player);
            }
        }

        if (stack.getItem() instanceof GunItem
                || stack.is(ModItems.MONITOR)
                || stack.is(ModItems.LUNGE_MINE)
                || (player.getVehicle() instanceof ArmedVehicleEntity)
                || (stack.is(Items.SPYGLASS) && player.isScoping() && player.getOffhandItem().is(ModItems.FIRING_PARAMETERS))
                || (stack.is(ModItems.ARTILLERY_INDICATOR))
        ) {
            if (button == ModKeyMappings.FIRE.key.getValue()) {
                handleWeaponFirePress(player, stack);
            }

            if (button == ModKeyMappings.HOLD_ZOOM.key.getValue()) {
                handleWeaponZoomPress(player, stack);
                switchZoom = false;
                return;
            }

            if (button == ModKeyMappings.SWITCH_ZOOM.key.getValue()) {
                handleWeaponZoomPress(player, stack);
                switchZoom = !switchZoom;
            }
        }
    }

    // 枪械交互时禁止挥舞手臂
    public static void stopSwing(InputEvent.InteractionKeyMappingTriggered event) {
        var player = Minecraft.getInstance().player;
        if (player != null && player.getItemInHand(event.getHand()).getItem() instanceof GunItem) {
            event.setSwingHand(false);
        }
    }

    public static void onMouseScrolling(InputEvent.MouseScrollingEvent event) {
        Player player = Minecraft.getInstance().player;

        if (notInGame()) return;
        if (player == null) return;

        ItemStack stack = player.getMainHandItem();

        if (player.hasEffect(ModMobEffects.SHOCK)) {
            return;
        }

        double scroll = event.getScrollDelta();

        // 按下自由视角键时，为载具调整相机距离
        if (player.getVehicle() instanceof VehicleEntity vehicle && player == vehicle.getFirstPassenger() && ModKeyMappings.FREE_CAMERA.isDown()) {
            ClientMouseHandler.custom3pDistance = Mth.clamp(ClientMouseHandler.custom3pDistance - event.getScrollDelta(), -3, 8);
            event.setCanceled(true);
            return;
        }

        // 未按下shift时，为有武器的载具切换武器
        if (!Screen.hasShiftDown()
                && player.getVehicle() instanceof VehicleEntity vehicle
                && vehicle instanceof WeaponVehicleEntity weaponVehicle
                && weaponVehicle.hasWeapon(vehicle.getSeatIndex(player))
                && weaponVehicle.banHand(player)
        ) {
            int index = vehicle.getSeatIndex(player);
            new SwitchVehicleWeaponMessage(index, -scroll, true).send();
            event.setCanceled(true);
        }

        if (stack.getItem() instanceof GunItem && ClientEventHandler.zoom) {
            var data = GunData.from(stack);
            if (data.canSwitchScope()) {
                new SwitchScopeMessage(scroll).send();
            } else if (data.canAdjustZoom() || stack.is(ModItems.MINIGUN)) {
                new AdjustZoomFovMessage(scroll).send();
            }
            event.setCanceled(true);
        }

        if (stack.is(ModItems.MONITOR) && stack.getOrCreateTag().getBoolean("Using") && stack.getOrCreateTag().getBoolean("Linked")) {
            ClientEventHandler.droneFov = Mth.clamp(ClientEventHandler.droneFov + 0.4 * scroll, 1, 6);
            event.setCanceled(true);
        }

        if (player.isUsingItem() && player.getUseItem().is(ModItems.ARTILLERY_INDICATOR)) {
            artilleryIndicatorCustomZoom = Mth.clamp(artilleryIndicatorCustomZoom + 0.4 * scroll, -2, 6);
            event.setCanceled(true);
        }

        Entity looking = TraceTool.findLookingEntity(player, 6);
        if (looking instanceof MortarEntity && player.isShiftKeyDown()) {
            new AdjustMortarAngleMessage(scroll).send();
            event.setCanceled(true);
        }
    }

    public static void onKeyPressed(InputEvent.Key event) {
        if (notInGame()) return;

        var mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null) return;
        if (player.isSpectator()) return;

        ItemStack stack = player.getMainHandItem();

        int key = event.getKey();
        if (event.getAction() == GLFW.GLFW_PRESS) {
            if (player.hasEffect(ModMobEffects.SHOCK)) {
                return;
            }

            if (key == Minecraft.getInstance().options.keyJump.key.getValue()) {
                handleDoubleJump(player);
                handleParachute();
            }

            if (key == ModKeyMappings.CONFIG.key.getValue() && ((IKeyBinding) ModKeyMappings.CONFIG).getKeyModifier().isActive(KeyConflictContext.IN_GAME)) {
                handleConfigScreen(player);
            }
            if (key == ModKeyMappings.RELOAD.key.getValue()) {
                ClientEventHandler.burstFireAmount = 0;
                isEditing = false;
                ClientPlayNetworking.send(ReloadMessage.ID, ReloadMessage.INSTANCE);
            }
            if (key == ModKeyMappings.FIRE_MODE.key.getValue()) {
                ClientPlayNetworking.send(FireModeMessage.ID, FireModeMessage.INSTANCE);
            }
            if (key == ModKeyMappings.INTERACT.key.getValue()) {
                if (stack.getItem() instanceof GunItem) {
                    KeyMapping.click(mc.options.keyUse.key);
                } else if (stack.is(ModItems.MONITOR)) {
                    ClientPlayNetworking.send(InteractMessage.ID, InteractMessage.INSTANCE);
                }
            }

            if (stack.getItem() instanceof GunItem) {
                var data = GunData.from(stack);
                if (key == ModKeyMappings.UNLOAD.key.getValue()) {
                    if (data.useBackpackAmmo() || data.ammo.get() + data.virtualAmmo.get() <= 0) return;
                    ClientPlayNetworking.send(UnloadMessage.ID, UnloadMessage.INSTANCE);
                }
                if (data.ammoConsumers.size() > 1) {
                    if (key == ModKeyMappings.CHANGE_AMMO_FORWARD.key.getValue()) {
                        new EditMessage(5, false).send();
                    }
                    if (key == ModKeyMappings.CHANGE_AMMO_BACKWARD.key.getValue()) {
                        new EditMessage(5, true).send();
                    }
                }
            }

            if (key == ModKeyMappings.DISMOUNT.key.getValue()) {
                handleDismountPress(player);
            }
            if (key == ModKeyMappings.EDIT_MODE.key.getValue()) {
                if (stack.getItem() instanceof ItemScreenProvider provider) {
                    var screen = provider.getItemScreen(stack, player, InteractionHand.MAIN_HAND);
                    if (screen != null) {
                        Minecraft.getInstance().setScreen(screen);
                        if (screen instanceof WeaponEditScreen) {
                            ClientEventHandler.onOpenEditScreen();
                        }
                        return;
                    }
                }
                ItemStack offHand = player.getOffhandItem();
                if (offHand.getItem() instanceof ItemScreenProvider provider) {
                    var screen = provider.getItemScreen(offHand, player, InteractionHand.OFF_HAND);
                    if (screen != null) {
                        Minecraft.getInstance().setScreen(screen);
                        return;
                    }
                }
            }

            if (key == ModKeyMappings.BREATH.key.getValue() && !exhaustion && zoom) {
                breath = true;
            }
            if (key == ModKeyMappings.SENSITIVITY_INCREASE.key.getValue()) {
                new SensitivityMessage(true).send();
            }
            if (key == ModKeyMappings.SENSITIVITY_REDUCE.key.getValue()) {
                new SensitivityMessage(false).send();
            }

            if (stack.getItem() instanceof GunItem
                    || stack.is(ModItems.MONITOR)
                    || (player.getVehicle() instanceof ArmedVehicleEntity iVehicle && iVehicle.isDriver(player))
                    || (stack.is(Items.SPYGLASS) && player.isScoping() && player.getOffhandItem().is(ModItems.FIRING_PARAMETERS))
                    || (stack.is(ModItems.ARTILLERY_INDICATOR))
            ) {
                if (key == ModKeyMappings.FIRE.key.getValue()) {
                    handleWeaponFirePress(player, stack);
                }

                if (key == ModKeyMappings.HOLD_ZOOM.key.getValue()) {
                    handleWeaponZoomPress(player, stack);
                    switchZoom = false;
                    return;
                }

                if (key == ModKeyMappings.SWITCH_ZOOM.key.getValue()) {
                    handleWeaponZoomPress(player, stack);
                    switchZoom = !switchZoom;
                }
            }

            if (key == ModKeyMappings.MARK.key.getValue()) {
                if (stack.is(ModItems.ARTILLERY_INDICATOR)) {
                    ClientPlayNetworking.send(SetFiringParametersMessage.ID, SetFiringParametersMessage.INSTANCE);
                }
                if (stack.is(ModItems.MONITOR) && player.getOffhandItem().is(ModItems.ARTILLERY_INDICATOR)) {
                    droneLeftClick(stack, player);
                }
            }

        } else {
            if (player.hasEffect(ModMobEffects.SHOCK)) {
                return;
            }

            if (key == ModKeyMappings.FIRE.key.getValue()) {
                handleWeaponFireRelease();
            }

            if (key == ModKeyMappings.HOLD_ZOOM.key.getValue()) {
                handleWeaponZoomRelease();
                return;
            }

            if (key == ModKeyMappings.SWITCH_ZOOM.key.getValue() && !switchZoom) {
                handleWeaponZoomRelease();
            }

            if (event.getAction() == GLFW.GLFW_RELEASE) {
                if (key == ModKeyMappings.BREATH.key.getValue()) {
                    breath = false;
                }
            }
        }
    }

    public static void handleWeaponFirePress(Player player, ItemStack stack) {
        isEditing = false;

        if (player.hasEffect(ModMobEffects.SHOCK)) return;

        if (player.getVehicle() instanceof WeaponVehicleEntity iVehicle && iVehicle.banHand(player)) {
            if (player.getVehicle() instanceof VehicleEntity pVehicle && iVehicle.hasWeapon(pVehicle.getSeatIndex(player))) {
                ClientEventHandler.holdFireVehicle = true;
            }
            return;
        }

        if (stack.is(ModItems.ARTILLERY_INDICATOR)) {
            ClientEventHandler.holdFire = true;
        }

        if (stack.is(Items.SPYGLASS) && player.isScoping() && player.getOffhandItem().is(ModItems.FIRING_PARAMETERS)) {
            ClientPlayNetworking.send(SetFiringParametersMessage.ID, SetFiringParametersMessage.INSTANCE);
        }

        if (stack.is(ModItems.MONITOR)) {
            if (player.getOffhandItem().is(ModItems.ARTILLERY_INDICATOR)) {
                ClientEventHandler.holdFire = true;
            } else {
                droneLeftClick(stack, player);
            }
        }

        if (stack.is(ModItems.LUNGE_MINE)) {
            ClientEventHandler.holdFire = true;
        }

        if (stack.getItem() instanceof GunItem && !(player.getVehicle() != null
                && player.getVehicle() instanceof CannonEntity)
                && clientTimer.getProgress() == 0
                && !notInGame()
        ) {
            var data = GunData.from(stack);

            if (!(stack.is(ModItems.BOCEK) || stack.is(ModItems.AURELIA_SCEPTRE))) {
                if (!data.meleeOnly()) {
                    player.playSound(ModSounds.TRIGGER_CLICK, 1, 1);
                }
            } else {
                bowPower = 0;
                holdFire = true;
                player.setSprinting(false);
                if (data.hasEnoughAmmoToShoot(player)) {
                    return;
                }
            }

            if (!data.useBackpackAmmo() && !data.meleeOnly() && !data.hasEnoughAmmoToShoot(player) && data.reload.time() == 0) {
                if (ReloadConfig.LEFT_CLICK_RELOAD.get()) {
                    ClientPlayNetworking.send(ReloadMessage.ID, ReloadMessage.INSTANCE);
                    ClientEventHandler.burstFireAmount = 0;
                }
            } else {
                new FireKeyMessage(0, bowPower, zoom).send();
                if ((!data.reloading()
                        && !data.charging()
                        && !data.bolt.needed.get())
                        && drawTime < 0.01
                ) {
                    if (data.fireMode.get() == FireMode.BURST) {
                        if (ClientEventHandler.burstFireAmount == 0) {
                            cantSprint = 8;
                            player.setSprinting(false);
                            ClientEventHandler.burstFireAmount = data.get(GunProp.BURST_AMOUNT);
                        }
                    } else {
                        ClientEventHandler.holdFire = true;
                        player.setSprinting(false);
                    }
                }
            }
        }
    }

    public static void handleWeaponFireRelease() {
        new FireKeyMessage(1, bowPower, zoom).send();
        bowPull = false;
        holdFire = false;
        holdFireVehicle = false;
        isEditing = false;
        customRpm = 0;

        Player player = Minecraft.getInstance().player;
        if (player == null) return;
        if (player.isSpectator()) return;

        ItemStack stack = player.getMainHandItem();

        if (stack.is(ModItems.BOCEK)) {
            ClientPlayNetworking.send(ReloadMessage.ID, ReloadMessage.INSTANCE);
        }
    }

    public static void handleWeaponZoomPress(Player player, ItemStack stack) {
        new ZoomMessage(0).send();

        isEditing = false;

        if (player.getVehicle() instanceof VehicleEntity pVehicle && player.getVehicle() instanceof WeaponVehicleEntity iVehicle && iVehicle.hasWeapon(pVehicle.getSeatIndex(player)) && iVehicle.banHand(player)) {
            ClientEventHandler.zoomVehicle = true;
            return;
        }

        if (!(stack.getItem() instanceof GunItem)) return;

        ClientEventHandler.zoom = true;
        int level = GunData.from(stack).perk.getLevel(ModPerks.INTELLIGENT_CHIP);
        if (level > 0) {
            if (ClientEventHandler.entity == null) {
                if (GunData.from(stack).perk.has(ModPerks.PHASE_PENETRATING_BULLET) || GunData.from(stack).perk.has(ModPerks.BEAST_BULLET)) {
                    ClientEventHandler.entity = SeekTool.seekEntityThroughWall(player, player.level(), 32 + 8 * (level - 1), 20);
                } else {
                    ClientEventHandler.entity = SeekTool.seekLivingEntity(player, player.level(), 32 + 8 * (level - 1), 20);
                }
            }
        }
    }

    public static void handleWeaponZoomRelease() {
        new ZoomMessage(1).send();
        ClientEventHandler.zoom = false;
        ClientEventHandler.zoomVehicle = false;
        ClientEventHandler.entity = null;
        breath = false;
    }

    private static void handleDoubleJump(Player player) {
        Level level = player.level();
        double x = player.getX();
        double y = player.getY();
        double z = player.getZ();

        if (!level.isLoaded(player.blockPosition())) {
            return;
        }

        if (canDoubleJump) {
            player.setDeltaMovement(new Vec3(player.getLookAngle().x, 0.8, player.getLookAngle().z));
            level.playLocalSound(x, y, z, ModSounds.DOUBLE_JUMP, SoundSource.BLOCKS, 1, 1, false);
            ClientPlayNetworking.send(DoubleJumpMessage.ID, DoubleJumpMessage.INSTANCE);
            canDoubleJump = false;
        }
    }

    private static void handleParachute() {
        ClientPlayNetworking.send(ParachuteMessage.ID, ParachuteMessage.INSTANCE);
    }

    private static void handleConfigScreen(Player player) {
        if (FabricLoader.getInstance().isModLoaded(CompatHolder.CLOTH_CONFIG)) {
            CompatHolder.hasMod(CompatHolder.CLOTH_CONFIG, () -> Minecraft.getInstance().setScreen(ClothConfigHelper.getConfigScreen(null)));
        } else {
            player.displayClientMessage(Component.translatable("tips.superbwarfare.no_cloth_config").withStyle(ChatFormatting.RED), true);
        }
    }

    private static void handleDismountPress(Player player) {
        if (player.getVehicle() instanceof VehicleEntity vehicle) {
            if ((!vehicle.onGround() || vehicle.getDeltaMovement().length() >= 0.1) && ClientEventHandler.dismountCountdown <= 0) {
                if (vehicle.allowEjection()) {
                    player.displayClientMessage(Component.translatable("tips.superbwarfare.mount.onboard", ModKeyMappings.DISMOUNT.getTranslatedKeyMessage()), true);
                } else {
                    player.displayClientMessage(Component.translatable("mount.onboard", ModKeyMappings.DISMOUNT.getTranslatedKeyMessage()), true);
                }

                ClientEventHandler.dismountCountdown = 20;
                return;
            }
            new PlayerStopRidingMessage(false).send();
        }

    }

    public static void droneLeftClick(ItemStack stack, Player player) {
        if (stack.is(ModItems.MONITOR) && stack.getOrCreateTag().getBoolean("Using") && stack.getOrCreateTag().getBoolean("Linked")) {
            DroneEntity drone = EntityFindUtil.findDrone(player.level(), stack.getOrCreateTag().getString("LinkedDrone"));
            if (drone != null) {
                boolean lookAtEntity = false;

                Entity lookingEntity = SeekTool.seekLivingEntity(drone, drone.level(), 512, 2 / droneFovLerp);

                BlockHitResult result = player.level().clip(new ClipContext(drone.getEyePosition(), drone.getEyePosition().add(drone.getLookAngle().scale(512)),
                        ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, drone));
                Vec3 pos = result.getLocation();

                if (lookingEntity != null && !player.isShiftKeyDown()) {
                    lookAtEntity = true;
                }

                if (lookAtEntity) {
                    pos = lookingEntity.position();
                }

                new DroneFireMessage(pos.toVector3f()).send();
            }
        }
    }
}