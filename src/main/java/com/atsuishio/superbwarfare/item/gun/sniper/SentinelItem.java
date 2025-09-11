package com.atsuishio.superbwarfare.item.gun.sniper;

import cn.sh1rocu.superbwarfare.util.fabric.ItemEnergyStorageHelper;
import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.client.renderer.gun.SentinelItemRenderer;
import com.atsuishio.superbwarfare.client.tooltip.component.SentinelImageComponent;
import com.atsuishio.superbwarfare.data.gun.GunData;
import com.atsuishio.superbwarfare.data.gun.GunProp;
import com.atsuishio.superbwarfare.init.ModSounds;
import com.atsuishio.superbwarfare.item.gun.GunItem;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.base.SimpleEnergyItem;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Supplier;

public class SentinelItem extends GunItem implements SimpleEnergyItem {

    private final Supplier<Integer> energyCapacity;

    public SentinelItem() {
        super(new Properties().rarity(Rarity.EPIC));

        this.energyCapacity = () -> 24000;
    }

    @Override
    public boolean isBarVisible(@NotNull ItemStack pStack) {
        var storage = EnergyStorage.ITEM.find(pStack, ContainerItemContext.withConstant(pStack));
        if (storage == null) {
            return false;
        }

        long[] energy = {0};
        energy[0] = storage.getAmount();
        return energy[0] != 0;
    }

    @Override
    public int getBarWidth(@NotNull ItemStack pStack) {
        long[] energy = {0};
        var storage = EnergyStorage.ITEM.find(pStack, ContainerItemContext.withConstant(pStack));
        if (storage != null) {
            energy[0] = storage.getAmount();
        }

        return Math.round((float) energy[0] * 13.0F / 24000F);
    }

    @Override
    public int getBarColor(@NotNull ItemStack pStack) {
        return 0x95E9FF;
    }

    @Override
    public Supplier<? extends GeoItemRenderer<? extends Item>> getRenderer() {
        return SentinelItemRenderer::new;
    }

    private PlayState fireAnimPredicate(AnimationState<SentinelItem> event) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return PlayState.STOP;
        ItemStack stack = player.getMainHandItem();
        if (!(stack.getItem() instanceof GunItem)) return PlayState.STOP;
        if (event.getData(DataTickets.ITEM_RENDER_PERSPECTIVE) != ItemDisplayContext.FIRST_PERSON_RIGHT_HAND)
            return event.setAndContinue(RawAnimation.begin().thenLoop("animation.sentinel.idle"));

        if (GunData.from(stack).bolt.actionTimer.get() > 0) {
            return event.setAndContinue(RawAnimation.begin().thenPlay("animation.sentinel.shift"));
        }

        if (GunData.from(stack).reload.empty()) {
            return event.setAndContinue(RawAnimation.begin().thenPlay("animation.sentinel.reload_empty"));
        }

        if (GunData.from(stack).reload.normal()) {
            return event.setAndContinue(RawAnimation.begin().thenPlay("animation.sentinel.reload_normal"));
        }

        if (GunData.from(stack).charging()) {
            return event.setAndContinue(RawAnimation.begin().thenPlay("animation.sentinel.charge"));
        }

        return event.setAndContinue(RawAnimation.begin().thenLoop("animation.sentinel.idle"));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        var fireAnimController = new AnimationController<>(this, "fireAnimController", 1, this::fireAnimPredicate);
        data.add(fireAnimController);
    }

    @Override
    public double getCustomDamage(GunData data) {
        var stack = data.stack;
        var storage = EnergyStorage.ITEM.find(stack, ContainerItemContext.withConstant(stack));
        return storage == null ? 0 : storage.getAmount() > 0 ? 0.2857142857142857 * data.getDefault().damage : 0;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, level, entity, slot, selected);

        ItemEnergyStorageHelper.fromStack(stack).ifPresent(storage -> {
            long energyStored = storage.getAmount();
            if (energyStored > 0) {
                try (Transaction transaction = Transaction.openOuter()) {
                    storage.extract(1, transaction);
                    transaction.commit();
                }
            }
        });
    }

    @Override
    public Set<SoundEvent> getReloadSound() {
        return Set.of(
                ModSounds.SENTINEL_RELOAD_EMPTY,
                ModSounds.SENTINEL_RELOAD_NORMAL,
                ModSounds.SENTINEL_CHARGE,
                ModSounds.SENTINEL_BOLT
        );
    }

    @Override
    public ResourceLocation getGunIcon(GunData data) {
        return Mod.loc("textures/gun_icon/sentinel_icon.png");
    }

    @Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(@NotNull ItemStack pStack) {
        return Optional.of(new SentinelImageComponent(pStack));
    }

    @Override
    public boolean isOpenBolt(GunData data) {
        return true;
    }

    @Override
    public boolean hasBulletInBarrel(GunData data) {
        return true;
    }

    @Override
    public void afterShoot(
            @Nullable Entity shooter,
            @NotNull ServerLevel level,
            @NotNull Vec3 shootPosition,
            @NotNull Vec3 shootDirection,
            @NotNull GunData data,
            double spread,
            boolean zoom,
            @Nullable UUID uuid
    ) {
        super.afterShoot(shooter, level, shootPosition, shootDirection, data, spread, zoom, uuid);

        ItemEnergyStorageHelper.fromStack(data.stack).ifPresent(storage -> {
            try (Transaction transaction = Transaction.openOuter()) {
                storage.extract(3000, transaction);
                transaction.commit();
            }
        });
    }

    @Override
    public void playFireSounds(GunData data, Entity shooter, boolean zoom) {
        var storage = EnergyStorage.ITEM.find(data.stack, ContainerItemContext.withConstant(data.stack));

        if (storage != null && storage.getAmount() > 0) {
            float soundRadius = data.get(GunProp.SOUND_RADIUS).floatValue();

            shooter.playSound(ModSounds.SENTINEL_CHARGE_FAR, soundRadius * 0.7f, 1f);
            shooter.playSound(ModSounds.SENTINEL_CHARGE_FIRE_3P, soundRadius * 0.4f, 1f);
            shooter.playSound(ModSounds.SENTINEL_CHARGE_VERYFAR, soundRadius, 1f);
        } else {
            super.playFireSounds(data, shooter, zoom);
        }
    }

    @Override
    public long getEnergyCapacity(ItemStack stack) {
        return energyCapacity.get();
    }

    @Override
    public long getEnergyMaxInput(ItemStack stack) {
        return energyCapacity.get();
    }

    @Override
    public long getEnergyMaxOutput(ItemStack stack) {
        return energyCapacity.get();
    }
}