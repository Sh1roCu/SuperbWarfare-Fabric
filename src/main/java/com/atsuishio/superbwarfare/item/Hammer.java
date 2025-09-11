package com.atsuishio.superbwarfare.item;

import cn.sh1rocu.superbwarfare.api.event.PlayerEvent;
import com.atsuishio.superbwarfare.client.TooltipTool;
import com.atsuishio.superbwarfare.init.ModSounds;
import com.atsuishio.superbwarfare.init.ModTags;
import io.github.fabricators_of_create.porting_lib.item.api.extensions.RepairableItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Hammer extends SwordItem implements RepairableItem {

    public Hammer(Tier tier, int attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        TooltipTool.addHideText(pTooltipComponents, Component.translatable("des.superbwarfare.hammer", pStack.getOrCreateTag().getInt("CraftCount")).withStyle(ChatFormatting.GRAY));
    }


    @Override
    public ItemStack getRecipeRemainder(ItemStack itemstack) {
        ItemStack stack = itemstack.copy();
        stack.hurt(1, RandomSource.create(), null);
        stack.getOrCreateTag().putInt("CraftCount", stack.getOrCreateTag().getInt("CraftCount") + 1);
        if (stack.isEmpty() || stack.getDamageValue() >= stack.getMaxDamage()) {
            return ItemStack.EMPTY;
        }
        return stack;
    }

    @Override
    public boolean isRepairable(ItemStack itemstack) {
        return true;
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pAttacker.level().playSound(null, pTarget.getOnPos(), ModSounds.MELEE_HIT, SoundSource.PLAYERS, 1, (float) ((2 * org.joml.Math.random() - 1) * 0.1f + 1.0f));
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }

    public static void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
        var item = event.getCrafting();
        var container = event.getInventory();
        var player = event.getEntity();
        if (player == null) return;

        if (player.level().isClientSide) return;

        if (item.is(ModTags.Items.HAMMER)) {
            int count = 0;
            for (int i = 0; i < container.getContainerSize(); i++) {
                if (container.getItem(i).is(ModTags.Items.HAMMER)) count++;
            }
            if (count == 2) {
                container.clearContent();
            }
        }
    }
}
