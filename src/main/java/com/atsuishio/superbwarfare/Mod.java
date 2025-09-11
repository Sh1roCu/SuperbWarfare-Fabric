package com.atsuishio.superbwarfare;

import com.atsuishio.superbwarfare.api.event.RegisterContainersEvent;
import com.atsuishio.superbwarfare.block.entity.FuMO25BlockEntity;
import com.atsuishio.superbwarfare.client.MouseMovementHandler;
import com.atsuishio.superbwarfare.client.molang.MolangVariable;
import com.atsuishio.superbwarfare.client.sound.ModSoundInstances;
import com.atsuishio.superbwarfare.compat.coldsweat.ColdSweatCompatHandler;
import com.atsuishio.superbwarfare.compat.tacz.TACZGunEventHandler;
import com.atsuishio.superbwarfare.config.ClientConfig;
import com.atsuishio.superbwarfare.config.CommonConfig;
import com.atsuishio.superbwarfare.config.ServerConfig;
import com.atsuishio.superbwarfare.data.CustomData;
import com.atsuishio.superbwarfare.init.*;
import com.atsuishio.superbwarfare.item.common.container.ContainerBlockItem;
import com.atsuishio.superbwarfare.network.NetworkRegistry;
import com.atsuishio.superbwarfare.recipe.ModPotionRecipes;
import com.tacz.guns.api.event.common.EntityHurtByGunEvent;
import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib.network.SerializableDataTicket;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Mod {

    public static final String MODID = "superbwarfare";
    public static final String ATTRIBUTE_MODIFIER = "superbwarfare_attribute_modifier";

    public static final Logger LOGGER = LogManager.getLogger(Mod.class);

    public static void setup() {
        ForgeConfigRegistry.INSTANCE.register(MODID, ModConfig.Type.CLIENT, ClientConfig.init());
        ForgeConfigRegistry.INSTANCE.register(MODID, ModConfig.Type.COMMON, CommonConfig.init());
        ForgeConfigRegistry.INSTANCE.register(MODID, ModConfig.Type.SERVER, ServerConfig.init());

        ModPerks.init();
        ModSerializers.init();
        ModSounds.init();
        ModAttributes.init();
        ModBlocks.init();
        ModBlockEntities.init();
        ModItems.init();

        RegisterContainersEvent.CALLBACK.register(ContainerBlockItem::registerContainers);

        ModEntities.init();

        var registerContainerEvent = new RegisterContainersEvent();
        RegisterContainersEvent.CALLBACK.invoker().post(registerContainerEvent);

        ModTabs.init();
        ModMobEffects.init();
        ModParticleTypes.init();
        ModPotions.init();
        ModPotionRecipes.register();
        ModMenuTypes.init();
        ModVillagers.init();
        ModRecipes.init();
        ModLootModifier.init();
        ModBiomeModifiers.init();
        ModCommandArguments.init();

        onCommonSetup();
        ModItems.registerDispenserBehavior();

        registerDataTickets();

        if (TACZGunEventHandler.compatCondition()) {
            EntityHurtByGunEvent.PRE.register(TACZGunEventHandler::entityHurtByTACZGun);
        }
        if (ColdSweatCompatHandler.hasMod()) {
            // MinecraftForge.EVENT_BUS.addListener(ColdSweatCompatHandler::onPlayerInVehicle);
        }

        CustomData.load();
    }

    public static ResourceLocation loc(String path) {
        return new ResourceLocation(MODID, path);
    }

    private static final Collection<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();
    private static final Collection<AbstractMap.SimpleEntry<Runnable, Integer>> workQueueC = new ConcurrentLinkedQueue<>();

    public static void queueServerWork(int tick, Runnable action) {
        workQueue.add(new AbstractMap.SimpleEntry<>(action, tick));
    }

    public static void queueClientWork(int tick, Runnable action) {
        workQueueC.add(new AbstractMap.SimpleEntry<>(action, tick));
    }

    public static void tick(MinecraftServer server) {
        List<AbstractMap.SimpleEntry<Runnable, Integer>> actions = new ArrayList<>();
        workQueue.forEach(work -> {
            work.setValue(work.getValue() - 1);
            if (work.getValue() == 0)
                actions.add(work);
        });
        actions.forEach(e -> e.getKey().run());
        workQueue.removeAll(actions);
    }

    @Environment(EnvType.CLIENT)
    public static void tick(Minecraft client) {
        List<AbstractMap.SimpleEntry<Runnable, Integer>> actions = new ArrayList<>();
        workQueueC.forEach(work -> {
            work.setValue(work.getValue() - 1);
            if (work.getValue() == 0)
                actions.add(work);
        });
        actions.forEach(e -> e.getKey().run());
        workQueueC.removeAll(actions);
    }

    public static void onCommonSetup() {
        NetworkRegistry.registerC2SPackets();
    }

    @Environment(EnvType.CLIENT)
    public static void onClientSetup() {
        MouseMovementHandler.init();
        MolangVariable.register();
        ModSoundInstances.init();
    }

    private static void registerDataTickets() {
        FuMO25BlockEntity.FUMO25_TICK = GeckoLibUtil.addDataTicket(SerializableDataTicket.ofInt(loc("fumo25_tick")));
    }
}
