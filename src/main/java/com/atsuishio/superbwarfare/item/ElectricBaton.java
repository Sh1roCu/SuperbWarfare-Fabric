package com.atsuishio.superbwarfare.item;

import cn.sh1rocu.superbwarfare.util.fabric.ItemEnergyStorageHelper;
import com.atsuishio.superbwarfare.client.tooltip.component.CellImageComponent;
import com.atsuishio.superbwarfare.init.ModMobEffects;
import com.atsuishio.superbwarfare.init.ModSounds;
import com.atsuishio.superbwarfare.tiers.ModItemTier;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.base.SimpleEnergyItem;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class ElectricBaton extends SwordItem implements SimpleEnergyItem {

    public static final long MAX_ENERGY = 30000;
    public static final long ENERGY_COST = 2000;
    public static final String TAG_OPEN = "Open";
    private final Supplier<Long> energyCapacity;

    public ElectricBaton() {
        super(ModItemTier.STEEL, 2, -2.5f, new Properties().durability(1114));
        this.energyCapacity = () -> MAX_ENERGY;
        EnergyStorage.ITEM.registerForItems((stack, context) -> SimpleEnergyItem.createStorage(context, this.getEnergyCapacity(stack), this.getEnergyMaxInput(stack), this.getEnergyMaxOutput(stack)), this);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("des.superbwarfare.electric_baton").withStyle(ChatFormatting.AQUA));
        if (pStack.getTag() != null && pStack.getTag().getBoolean(TAG_OPEN)) {
            pTooltipComponents.add(Component.translatable("des.superbwarfare.electric_baton.open").withStyle(ChatFormatting.GRAY));
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if (pPlayer.isShiftKeyDown()) {
            stack.getOrCreateTag().putBoolean(TAG_OPEN, !stack.getOrCreateTag().getBoolean(TAG_OPEN));
            pPlayer.displayClientMessage(Component.translatable("des.superbwarfare.electric_baton." + (stack.getOrCreateTag().getBoolean(TAG_OPEN) ? "open" : "close")), true);
        }
        return InteractionResultHolder.fail(stack);
    }

    @Override
    public boolean isBarVisible(ItemStack pStack) {
        return pStack.getOrCreateTag().getBoolean(TAG_OPEN) || super.isBarVisible(pStack);
    }

    @Override
    public int getBarWidth(ItemStack pStack) {
        if (pStack.getOrCreateTag().getBoolean(TAG_OPEN)) {
            var storage = EnergyStorage.ITEM.find(pStack, ContainerItemContext.withConstant(pStack));
            var energy = storage == null ? 0 : storage.getAmount();

            return Math.round((float) energy * 13.0F / MAX_ENERGY);
        } else {
            return super.getBarWidth(pStack);
        }
    }

    @Override
    public int getBarColor(@NotNull ItemStack pStack) {
        return pStack.getOrCreateTag().getBoolean(TAG_OPEN) ? 0xFFFF00 : super.getBarColor(pStack);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pAttacker.level().playSound(null, pTarget.getOnPos(), ModSounds.MELEE_HIT, SoundSource.PLAYERS, 1, (float) ((2 * org.joml.Math.random() - 1) * 0.1f + 1.0f));
        if (pStack.getOrCreateTag().getBoolean(TAG_OPEN)) {
            ItemEnergyStorageHelper.fromStack(pStack).ifPresent(storage -> {
                var energy = storage.getAmount();
                if (energy >= ENERGY_COST) {
                    try (Transaction transaction = Transaction.openOuter()) {
                        storage.extract(ENERGY_COST, transaction);
                        transaction.commit();
                    }
                    if (!pTarget.level().isClientSide) {
                        pTarget.addEffect(new MobEffectInstance(ModMobEffects.SHOCK, 30, 2), pAttacker);
                    }
                }
            });
        }
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }

    @Override
    public @NotNull Optional<TooltipComponent> getTooltipImage(@NotNull ItemStack pStack) {
        return Optional.of(new CellImageComponent(pStack));
    }

    public ItemStack makeFullEnergyStack() {
        ItemStack stack = new ItemStack(this);
        this.setStoredEnergy(stack, MAX_ENERGY);
        stack.getOrCreateTag().putBoolean(TAG_OPEN, true);
        return stack;
    }

    @Override
    public long getEnergyCapacity(ItemStack stack) {
        return MAX_ENERGY;
    }

    @Override
    public long getEnergyMaxInput(ItemStack stack) {
        return MAX_ENERGY;
    }

    @Override
    public long getEnergyMaxOutput(ItemStack stack) {
        return MAX_ENERGY;
    }
}
