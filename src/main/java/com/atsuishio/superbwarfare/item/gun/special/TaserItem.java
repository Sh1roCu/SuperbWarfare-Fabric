package com.atsuishio.superbwarfare.item.gun.special;

import cn.sh1rocu.superbwarfare.util.fabric.ItemEnergyStorageHelper;
import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.client.renderer.gun.TaserItemRenderer;
import com.atsuishio.superbwarfare.client.tooltip.component.EnergyImageComponent;
import com.atsuishio.superbwarfare.data.gun.GunData;
import com.atsuishio.superbwarfare.init.ModItems;
import com.atsuishio.superbwarfare.init.ModPerks;
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
import net.minecraft.world.entity.player.Player;
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
import team.reborn.energy.api.EnergyStorageUtil;
import team.reborn.energy.api.base.SimpleEnergyItem;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Supplier;

public class TaserItem extends GunItem implements SimpleEnergyItem {

    public static final long MAX_ENERGY = 6000;

    private final Supplier<Long> energyCapacity;

    public TaserItem() {
        super(new Properties().rarity(Rarity.COMMON));
        this.energyCapacity = () -> MAX_ENERGY;
        EnergyStorage.ITEM.registerForItems((stack, context) ->
                SimpleEnergyItem.createStorage(context, this.getEnergyCapacity(stack), this.getEnergyMaxInput(stack), this.getEnergyMaxOutput(stack)), this);
    }

    @Override
    public boolean isBarVisible(@NotNull ItemStack pStack) {
        EnergyStorage storage = EnergyStorage.ITEM.find(pStack, ContainerItemContext.withConstant(pStack));
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
        EnergyStorage storage = EnergyStorage.ITEM.find(pStack, ContainerItemContext.withConstant(pStack));
        energy[0] = storage.getAmount();

        return Math.round((float) energy[0] * 13.0F / MAX_ENERGY);
    }

    @Override
    public int getBarColor(@NotNull ItemStack pStack) {
        return 0xFFFF00;
    }

    @Override
    public Set<SoundEvent> getReloadSound() {
        return Set.of(ModSounds.TASER_RELOAD_EMPTY);
    }

    @Override
    public Supplier<? extends GeoItemRenderer<? extends Item>> getRenderer() {
        return TaserItemRenderer::new;
    }

    private PlayState idlePredicate(AnimationState<TaserItem> event) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return PlayState.STOP;
        ItemStack stack = player.getMainHandItem();
        if (!(stack.getItem() instanceof GunItem)) return PlayState.STOP;
        if (event.getData(DataTickets.ITEM_RENDER_PERSPECTIVE) != ItemDisplayContext.FIRST_PERSON_RIGHT_HAND)
            return event.setAndContinue(RawAnimation.begin().thenLoop("animation.taser.idle"));

        var data = GunData.from(stack);
        if (data.reload.empty()) {
            return event.setAndContinue(RawAnimation.begin().thenPlay("animation.taser.reload"));
        }

        return event.setAndContinue(RawAnimation.begin().thenLoop("animation.taser.idle"));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        AnimationController<TaserItem> idleController = new AnimationController<>(this, "idleController", 3, this::idlePredicate);
        data.add(idleController);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);

        if (entity instanceof Player player) {
            for (var cell : player.getInventory().items) {
                if (cell.is(ModItems.CELL)) {
                    var stackStorage = ItemEnergyStorageHelper.fromStack(stack);
                    assert stackStorage.isPresent();
                    long stackMaxEnergy = stackStorage.get().getCapacity();
                    long stackEnergy = stackStorage.get().getAmount();

                    var cellStorage = ItemEnergyStorageHelper.fromStack(cell);
                    assert cellStorage.isPresent();
                    long cellEnergy = cellStorage.get().getAmount();

                    long stackEnergyNeed = Math.min(cellEnergy, stackMaxEnergy - stackEnergy);

                    if (cellEnergy > 0) {
                        EnergyStorageUtil.move(cellStorage.get(), stackStorage.get(), stackEnergyNeed, null);
                    }
                }
            }
        }
    }

    @Override
    public ResourceLocation getGunIcon(GunData data) {
        return Mod.loc("textures/gun_icon/taser_icon.png");
    }

    @Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(@NotNull ItemStack pStack) {
        return Optional.of(new EnergyImageComponent(pStack));
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

        var stack = data.stack;
        int perkLevel = data.perk.getLevel(ModPerks.VOLT_OVERLOAD);
        ItemEnergyStorageHelper.fromStack(stack).ifPresent(storage -> {
            try (Transaction transaction = Transaction.openOuter()) {
                storage.extract(400 + 100 * perkLevel, transaction);
                transaction.commit();
            }
        });
    }

    @Override
    public boolean canShoot(GunData data, @Nullable Entity shooter) {
        var stack = data.stack;

        int perkLevel = data.perk.getLevel(ModPerks.VOLT_OVERLOAD);
        EnergyStorage storage = EnergyStorage.ITEM.find(stack, ContainerItemContext.withConstant(stack));
        boolean hasEnoughEnergy = storage != null && storage.getAmount() >= 400 + 100 * perkLevel;

        if (!hasEnoughEnergy) return false;

        return super.canShoot(data, shooter);
    }

    @Override
    public long getEnergyCapacity(ItemStack itemStack) {
        return energyCapacity.get();
    }

    @Override
    public long getEnergyMaxInput(ItemStack itemStack) {
        return getEnergyCapacity(itemStack);
    }

    @Override
    public long getEnergyMaxOutput(ItemStack itemStack) {
        return getEnergyCapacity(itemStack);
    }
}
