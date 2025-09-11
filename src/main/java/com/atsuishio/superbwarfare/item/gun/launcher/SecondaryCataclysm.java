package com.atsuishio.superbwarfare.item.gun.launcher;

import cn.sh1rocu.superbwarfare.api.extension.IRarity;
import cn.sh1rocu.superbwarfare.util.fabric.ItemEnergyStorageHelper;
import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.client.GunRendererBuilder;
import com.atsuishio.superbwarfare.client.TooltipTool;
import com.atsuishio.superbwarfare.client.model.item.SecondaryCataclysmModel;
import com.atsuishio.superbwarfare.client.tooltip.component.EnergyImageComponent;
import com.atsuishio.superbwarfare.data.gun.GunData;
import com.atsuishio.superbwarfare.data.gun.GunProp;
import com.atsuishio.superbwarfare.event.ClientEventHandler;
import com.atsuishio.superbwarfare.init.ModItems;
import com.atsuishio.superbwarfare.init.ModSounds;
import com.atsuishio.superbwarfare.item.gun.GunItem;
import com.atsuishio.superbwarfare.tools.ParticleTool;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
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
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

public class SecondaryCataclysm extends GunItem implements SimpleEnergyItem, IRarity {

    private final Supplier<Long> energyCapacity = () -> 24000L;
    private ChatFormatting rarityColor;

    public SecondaryCataclysm() {
        super(new Properties().fireResistant());
        EnergyStorage.ITEM.registerForItems((stack, context) -> SimpleEnergyItem.createStorage(context, this.getEnergyCapacity(stack), this.getEnergyMaxInput(stack), this.getEnergyMaxOutput(stack)), this);
    }

    public SecondaryCataclysm(ChatFormatting color) {
        this();
        this.rarityColor = color;
    }

    @Override
    public ChatFormatting getRarityColor() {
        return rarityColor;
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
    @ParametersAreNonnullByDefault
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
        list.add(Component.empty());
        list.add(Component.translatable("des.superbwarfare.secondary_cataclysm_1").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));

        TooltipTool.addHideText(list, Component.empty());
        TooltipTool.addHideText(list, Component.translatable("des.superbwarfare.trachelium_3").withStyle(ChatFormatting.WHITE));
        TooltipTool.addHideText(list, Component.translatable("des.superbwarfare.secondary_cataclysm_2").withStyle(Style.EMPTY.withColor(0x68B9F6)));
    }

    @Override
    public int getBarColor(@NotNull ItemStack pStack) {
        return 0x95E9FF;
    }

    @Override
    public Supplier<? extends GeoItemRenderer<? extends Item>> getRenderer() {
        return GunRendererBuilder.simple(SecondaryCataclysmModel::new, 0, 0, 1.0375, 0.6);
    }

    private PlayState reloadAnimPredicate(AnimationState<SecondaryCataclysm> event) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return PlayState.STOP;
        ItemStack stack = player.getMainHandItem();
        if (!(stack.getItem() instanceof GunItem)) return PlayState.STOP;
        if (event.getData(DataTickets.ITEM_RENDER_PERSPECTIVE) != ItemDisplayContext.FIRST_PERSON_RIGHT_HAND)
            return event.setAndContinue(RawAnimation.begin().thenLoop("animation.secondary_cataclysm.idle"));

        var data = GunData.from(stack);

        if (data.reload.stage() == 1 && data.reload.prepareLoadTimer.get() > 0) {
            return event.setAndContinue(RawAnimation.begin().thenPlay("animation.secondary_cataclysm.prepare"));
        }

        if (data.loadIndex.get() == 0 && data.reload.stage() == 2) {
            return event.setAndContinue(RawAnimation.begin().thenPlay("animation.secondary_cataclysm.iterativeload"));
        }

        if (data.loadIndex.get() == 1 && data.reload.stage() == 2) {
            return event.setAndContinue(RawAnimation.begin().thenPlay("animation.secondary_cataclysm.iterativeload2"));
        }

        if (data.reload.stage() == 3) {
            return event.setAndContinue(RawAnimation.begin().thenPlay("animation.secondary_cataclysm.finish"));
        }

        return event.setAndContinue(RawAnimation.begin().thenLoop("animation.secondary_cataclysm.idle"));
    }

    private PlayState meleePredicate(AnimationState<SecondaryCataclysm> event) {
        if (event.getData(DataTickets.ITEM_RENDER_PERSPECTIVE) != ItemDisplayContext.FIRST_PERSON_RIGHT_HAND)
            return event.setAndContinue(RawAnimation.begin().thenLoop("animation.secondary_cataclysm.idle"));

        if (ClientEventHandler.gunMelee > 0) {
            return event.setAndContinue(RawAnimation.begin().thenPlay("animation.secondary_cataclysm.hit"));
        }

        return event.setAndContinue(RawAnimation.begin().thenLoop("animation.secondary_cataclysm.idle"));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        var reloadAnimController = new AnimationController<>(this, "reloadAnimController", 1, this::reloadAnimPredicate);
        data.add(reloadAnimController);
        var meleeController = new AnimationController<>(this, "meleeController", 0, this::meleePredicate);
        data.add(meleeController);
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
        return Mod.loc("textures/gun_icon/secondary_cataclysm_icon.png");
    }

    @Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(@NotNull ItemStack pStack) {
        return Optional.of(new EnergyImageComponent(pStack));
    }

    @Override
    public boolean shootBullet(
            @Nullable Entity shooter,
            @NotNull ServerLevel level,
            @NotNull Vec3 shootPosition,
            @NotNull Vec3 shootDirection,
            @NotNull GunData data,
            double spread,
            boolean zoom,
            @Nullable UUID uuid
    ) {
        var stack = data.stack;

        var storage = ItemEnergyStorageHelper.fromStack(stack);

        var hasEnoughEnergy = storage.map(energyStorage -> energyStorage.getAmount() >= 3000).orElse(false);

        boolean isChargedFire = zoom && hasEnoughEnergy;

        if (isChargedFire) {
            data.setTempProperty(GunProp.DAMAGE, (pm, d, v) -> v * 1.25);
            data.setTempProperty(GunProp.VELOCITY, (pm, d, v) -> v * 4);
        }

        if (!super.shootBullet(shooter, level, shootPosition, shootDirection, data, spread, zoom, uuid)) return false;

        ParticleTool.sendParticle(level, ParticleTypes.CLOUD, shootPosition.x + 1.8 * shootDirection.x,
                shootPosition.y - 0.35 + 1.8 * shootDirection.y,
                shootPosition.z + 1.8 * shootDirection.z,
                4, 0.1, 0.1, 0.1, 0.002, true);

        if (isChargedFire) {
            try (Transaction tx = Transaction.openOuter()) {
                storage.get().extract(3000, tx);
                tx.commit();
            }
        }

        return true;
    }

    @Override
    public void playFireSounds(GunData data, Entity shooter, boolean zoom) {
        var storage = EnergyStorage.ITEM.find(data.stack, ContainerItemContext.withConstant(data.stack));

        if (storage != null) {
            if (storage.getAmount() > 3000 && zoom) {
                float soundRadius = data.get(GunProp.SOUND_RADIUS).floatValue();

                shooter.playSound(ModSounds.SECONDARY_CATACLYSM_FIRE_3P_CHARGE, soundRadius * 0.4f, 1f);
                shooter.playSound(ModSounds.SECONDARY_CATACLYSM_FAR_CHARGE, soundRadius * 0.7f, 1f);
                shooter.playSound(ModSounds.SECONDARY_CATACLYSM_VERYFAR_CHARGE, soundRadius, 1f);
            } else {
                super.playFireSounds(data, shooter, zoom);
            }
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