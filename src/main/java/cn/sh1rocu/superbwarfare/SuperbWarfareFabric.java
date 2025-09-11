package cn.sh1rocu.superbwarfare;

import cn.sh1rocu.superbwarfare.api.event.*;
import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.advancement.CriteriaRegister;
import com.atsuishio.superbwarfare.api.event.PreKillEvent;
import com.atsuishio.superbwarfare.api.event.ProjectileHitEvent;
import com.atsuishio.superbwarfare.api.event.ReloadEvent;
import com.atsuishio.superbwarfare.capability.CapabilityHandler;
import com.atsuishio.superbwarfare.command.CommandRegister;
import com.atsuishio.superbwarfare.compat.CompatHolder;
import com.atsuishio.superbwarfare.data.DataLoader;
import com.atsuishio.superbwarfare.data.container.ContainerDataManager;
import com.atsuishio.superbwarfare.data.vehicle.VehicleDataTool;
import com.atsuishio.superbwarfare.entity.DPSGeneratorEntity;
import com.atsuishio.superbwarfare.entity.SenpaiEntity;
import com.atsuishio.superbwarfare.entity.TargetEntity;
import com.atsuishio.superbwarfare.event.*;
import com.atsuishio.superbwarfare.init.ModBlockEntities;
import com.atsuishio.superbwarfare.init.ModBlocks;
import com.atsuishio.superbwarfare.init.ModItems;
import com.atsuishio.superbwarfare.item.Hammer;
import com.atsuishio.superbwarfare.item.common.ammo.PotionMortarShell;
import com.atsuishio.superbwarfare.menu.EnergyMenu;
import com.atsuishio.superbwarfare.menu.FuMO25Menu;
import com.atsuishio.superbwarfare.mobeffect.BurnMobEffect;
import com.atsuishio.superbwarfare.mobeffect.ShockMobEffect;
import com.atsuishio.superbwarfare.perk.functional.PowerfulAttraction;
import com.atsuishio.superbwarfare.tools.GunsTool;
import io.github.fabricators_of_create.porting_lib.entity.events.EntityEvents;
import io.github.fabricators_of_create.porting_lib.entity.events.LivingAttackEvent;
import io.github.fabricators_of_create.porting_lib.entity.events.LivingEntityEvents;
import io.github.fabricators_of_create.porting_lib.entity.events.PlayerTickEvents;
import io.github.fabricators_of_create.porting_lib.entity.events.living.LivingHurtEvent;
import io.github.fabricators_of_create.porting_lib.entity.events.living.MobEffectEvent;
import io.github.fabricators_of_create.porting_lib.entity.events.player.AttackEntityEvent;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityWorldChangeEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.registry.LandPathNodeTypesRegistry;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import org.jetbrains.annotations.NotNull;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.base.InfiniteEnergyStorage;

public class SuperbWarfareFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        Mod.setup();

        LandPathNodeTypesRegistry.registerDynamic(ModBlocks.DRAGON_TEETH, new LandPathNodeTypesRegistry.DynamicPathNodeTypeProvider() {
            @Override
            public @NotNull BlockPathTypes getPathNodeType(BlockState state, BlockGetter world, BlockPos pos, boolean neighbor) {
                return BlockPathTypes.LAVA;
            }
        });

        PotionMortarShell.onRegisterColorHandlers();
        CriteriaRegister.setup();
        registerItemStorages();
        registerEnergyStorages();
        CompatHolder.onInterModEnqueue();
        ContainerDataManager.onAddReloadListeners();

        subscribeEvents();
    }

    private void registerItemStorages() {
        ItemStorage.SIDED.registerForBlockEntity(InventoryStorage::of, ModBlockEntities.CHARGING_STATION);
        ItemStorage.SIDED.registerForBlockEntity(InventoryStorage::of, ModBlockEntities.SUPERB_ITEM_INTERFACE);
        ItemStorage.SIDED.registerForBlockEntity(InventoryStorage::of, ModBlockEntities.CREATIVE_SUPERB_ITEM_INTERFACE);
    }

    private static void registerEnergyStorages() {
        EnergyStorage.ITEM.registerForItems((stack, context) -> InfiniteEnergyStorage.INSTANCE, ModItems.CREATIVE_CHARGING_STATION);

        EnergyStorage.SIDED.registerForBlockEntity((blockEntity, direction) ->
                blockEntity.energyStorage, ModBlockEntities.CHARGING_STATION);

        EnergyStorage.SIDED.registerForBlockEntity((blockEntity, direction) ->
                blockEntity.energyStorage, ModBlockEntities.CREATIVE_CHARGING_STATION);

        EnergyStorage.SIDED.registerForBlockEntity((blockEntity, direction) ->
                blockEntity.energyStorage, ModBlockEntities.FUMO_25);
    }

    private static void subscribeEvents() {
        ServerLifecycleEvents.SERVER_STARTED.register(DataLoader::serverStarted);
        ServerLifecycleEvents.END_DATA_PACK_RELOAD.register(DataLoader::onDataPackSync);
        CommandRegistrationCallback.EVENT.register(CommandRegister::registerCommand);
        ServerTickEvents.END_SERVER_TICK.register(Mod::tick);
        ServerLivingEntityEvents.ALLOW_DEATH.register(DPSGeneratorEntity::onTargetDown);
        PlayerEvent.LOGGED_IN.register(CapabilityHandler::onPlayerLoggedIn);
        ServerPlayerEvents.AFTER_RESPAWN.register(CapabilityHandler::onPlayerRespawn);
        ServerEntityWorldChangeEvents.AFTER_PLAYER_CHANGE_WORLD.register(CapabilityHandler::onPlayerChangeDimension);
        ServerPlayerEvents.COPY_FROM.register(CapabilityHandler::clonePlayer);
        PlayerEvent.LOGGED_IN.register(VehicleDataTool::onPlayerLogin);
        ServerLifecycleEvents.SYNC_DATA_PACK_CONTENTS.register(VehicleDataTool::onDataPackSync);
        ServerLivingEntityEvents.ALLOW_DEATH.register(TargetEntity::onTargetDown);
        MobSpawnEvent.FINALIZE_SPAWN.register(SenpaiEntity::onFinalizeSpawn);
        ReloadEvent.PRE.register(CustomEventHandler::onPreReload);
        ReloadEvent.POST.register(CustomEventHandler::onPostReload);
        ProjectileHitEvent.HIT_ENTITY.register(CustomEventHandler::onProjectileHitEntity);
        ProjectileHitEvent.HIT_BLOCK.register(CustomEventHandler::onProjectileHitBlock);
        EntityEvents.ON_JOIN_WORLD.register(EntityUseGunEventHandler::entityJoin);
        PlayerTickEvents.END.register(HitboxHelperEventHandler::onPlayerTick);
        PlayerEvent.LOGGED_OUT.register(HitboxHelperEventHandler::onPlayerLoggedOut);
        LivingEntityEvents.CHANGE_TARGET.register(LivingEventHandler::onLivingChangeTargetEvent);
        LivingAttackEvent.ATTACK.register(LivingEventHandler::onEntityAttacked);
        LivingHurtEvent.HURT.register(LivingEventHandler::onEntityHurt);
        ServerLivingEntityEvents.ALLOW_DEATH.register(LivingEventHandler::onEntityDeath);
        ServerEntityEvents.EQUIPMENT_CHANGE.register(LivingEventHandler::handleChangeSlot);
        EntityItemPickupEvent.ENTITY_ITEM_PICKUP.register(LivingEventHandler::onPickup);
        LivingEntityEvents.DROPS.register(LivingEventHandler::onLivingDrops);
        LivingEntityEvents.EXPERIENCE_DROP.register(LivingEventHandler::onLivingExperienceDrop);
        LivingEntityEvents.KNOCKBACK_STRENGTH.register(LivingEventHandler::onKnockback);
        LivingEntityEvents.FALL.register(LivingEventHandler::onEntityFall);
        PreKillEvent.SEND_KILL_MESSAGE.register(LivingEventHandler::onPreSendKillMessage);
        PreKillEvent.INDICATOR.register(LivingEventHandler::onPreIndicator);
        MobEffectEvent.APPLICABLE.register(LivingEventHandler::onEffectApply);
        PlayerEvent.LOGGED_IN.register(PlayerEventHandler::onPlayerLoggedIn);
        ServerPlayerEvents.AFTER_RESPAWN.register(PlayerEventHandler::onPlayerRespawned);
        PlayerTickEvents.END.register(PlayerEventHandler::onPlayerTick);
        AnvilUpdateEvent.CALLBACK.register(PlayerEventHandler::onAnvilUpdate);
        AttackEntityEvent.ATTACK_ENTITY.register(PlayerEventHandler::onAttackEntity);
        PlayerEvent.ITEM_CRAFTED.register(Hammer::onItemCrafted);
        PlayerContainerEvent.OPEN.register(EnergyMenu::onContainerOpened);
        PlayerContainerEvent.CLOSE.register(EnergyMenu::onContainerClosed);
        PlayerContainerEvent.OPEN.register(FuMO25Menu::onContainerOpened);
        PlayerContainerEvent.CLOSE.register(FuMO25Menu::onContainerClosed);
        MobEffectEvent.ADDED.register(BurnMobEffect::onEffectAdded);
        MobEffectEvent.EXPIRED.register(BurnMobEffect::onEffectExpired);
        MobEffectEvent.REMOVE.register(BurnMobEffect::onEffectRemoved);
        LivingEntityEvents.LivingTickEvent.TICK.register(BurnMobEffect::onLivingTick);
        MobEffectEvent.ADDED.register(ShockMobEffect::onEffectAdded);
        MobEffectEvent.EXPIRED.register(ShockMobEffect::onEffectExpired);
        MobEffectEvent.REMOVE.register(ShockMobEffect::onEffectRemoved);
        LivingEntityEvents.LivingTickEvent.TICK.register(ShockMobEffect::onLivingTick);
        LivingAttackEvent.ATTACK.register(ShockMobEffect::onEntityAttacked);
        LivingEntityEvents.DROPS.register(PowerfulAttraction::onLivingDrops);
        LivingEntityEvents.EXPERIENCE_DROP.register(PowerfulAttraction::onLivingExperienceDrop);
        LivingEntityEvents.LOOTING_LEVEL.register(PowerfulAttraction::onLootingLevel);
        PlayerEvent.LOGGED_IN.register(GunsTool::onPlayerLogin);
        ServerLifecycleEvents.SYNC_DATA_PACK_CONTENTS.register(GunsTool::onDataPackSync);
//        PlayerTickEvents.END.register(BoundingBoxManager::onPlayerTick);
//        PlayerEvent.LOGGED_OUT.register(BoundingBoxManager::onPlayerLoggedOut);
    }
}
