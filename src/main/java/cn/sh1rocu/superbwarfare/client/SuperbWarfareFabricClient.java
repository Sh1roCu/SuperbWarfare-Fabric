package cn.sh1rocu.superbwarfare.client;

import cn.sh1rocu.superbwarfare.api.event.*;
import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.client.ClickHandler;
import com.atsuishio.superbwarfare.client.ClientRenderHandler;
import com.atsuishio.superbwarfare.client.language.ClientLanguageGetter;
import com.atsuishio.superbwarfare.client.renderer.curio.ParachuteRenderer;
import com.atsuishio.superbwarfare.client.renderer.special.ContainerBlockPreview;
import com.atsuishio.superbwarfare.client.screens.FuMO25ScreenHelper;
import com.atsuishio.superbwarfare.client.screens.modsell.ModSellWarningScreen;
import com.atsuishio.superbwarfare.event.ClientEventHandler;
import com.atsuishio.superbwarfare.event.ClientMouseHandler;
import com.atsuishio.superbwarfare.event.KillMessageHandler;
import com.atsuishio.superbwarfare.init.*;
import com.atsuishio.superbwarfare.item.common.ammo.PotionMortarShell;
import com.atsuishio.superbwarfare.network.NetworkRegistry;
import com.atsuishio.superbwarfare.tools.VectorUtil;
import io.github.fabricators_of_create.porting_lib.event.client.RenderPlayerEvents;
import io.github.fabricators_of_create.porting_lib.item.api.client.callbacks.ItemDecorationsCallback;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;

public class SuperbWarfareFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        NetworkRegistry.registerS2CPackets();
        ClientLifecycleEvents.CLIENT_STARTED.register(client -> Mod.onClientSetup());

        // 由GeckoLib接管渲染，不使用FabricAPI进行注册
//        BuiltInRegistries.ITEM.stream().filter(item -> item instanceof IClientItemExtensions).forEach(clientEx ->
//                BuiltinItemRendererRegistry.INSTANCE.register(clientEx,
//                        (stack, mode, matrices, vertexConsumers, light, overlay) ->
//                                ((IClientItemExtensions) clientEx).getCustomRenderer().renderByItem(stack, mode, matrices, vertexConsumers, light, overlay)));


        ClientLanguageGetter.onResourcePackReload();
        ClientRenderHandler.registerGuiOverlays();
        ClientRenderHandler.registerTooltips();
        ClientRenderHandler.registerRenderers();
        ClientRenderHandler.onClientSetup();
        ClientRenderHandler.registerLayers();
        PotionMortarShell.onRegisterColorHandlers();
        ModEntityRenderers.registerEntityRenderers();
        ModKeyMappings.registerKeyMappings();
        ModParticles.registerParticles();
        ModProperties.propertyOverrideRegistry();
        ModScreens.registerScreens();

        subscribeEvents();
    }

    private static void subscribeEvents() {
        ClientTickEvents.END_CLIENT_TICK.register(Mod::tick);
        ItemDecorationsCallback.EVENT.register(ClientRenderHandler::registerItemDecorations);
        WorldRenderEvents.AFTER_TRANSLUCENT.register(ContainerBlockPreview::render);
        WorldRenderEvents.AFTER_TRANSLUCENT.register(ParachuteRenderer::onRenderLevelStage);
        ScreenEvents.AFTER_INIT.register(ModSellWarningScreen::onGuiOpen);
        ClientTickEvents.END_CLIENT_TICK.register(FuMO25ScreenHelper::onClientTick);
        InputEvent.MouseButton.Pre.EVENT.register(ClickHandler::onButtonReleased);
        InputEvent.MouseButton.Pre.EVENT.register(ClickHandler::onButtonPressed);
        InputEvent.InteractionKeyMappingTriggered.EVENT.register(ClickHandler::stopSwing);
        InputEvent.MouseScrollingEvent.EVENT.register(ClickHandler::onMouseScrolling);
        InputEvent.Key.EVENT.register(ClickHandler::onKeyPressed);
        RenderHandEvent.CALLBACK.register(ClientEventHandler::handleWeaponTurn);
        ClientTickEvents.END_CLIENT_TICK.register(ClientEventHandler::handleClientTick);
        RenderTickEvent.CALLBACK.register(ClientEventHandler::handleWeaponFire);
        RenderTickEvent.CALLBACK.register(ClientEventHandler::handleVehicleFire);
        RenderTickEvent.CALLBACK.register(ClientEventHandler::handleWeaponBreathSway);
        ViewportEvent.CAMERA.register(ClientEventHandler::computeCameraAngles);
        RenderHandEvent.CALLBACK.register(BaseEvent.HIGHEST, ClientEventHandler::onRenderHand);
        ViewportEvent.FOV.register(ClientEventHandler::onFovUpdate);
        RenderPlayerEvents.PRE.register(ClientEventHandler::setPlayerInvisible);
        ClientTickEvents.END_CLIENT_TICK.register(ClientMouseHandler::handleClientTick);
        ViewportEvent.CAMERA.register(ClientMouseHandler::handleClientTick);
        ClientTickEvents.END_CLIENT_TICK.register(KillMessageHandler::onClientTick);
        ViewportEvent.FOV.register(BaseEvent.LOWEST, VectorUtil::captureFov);
    }
}
