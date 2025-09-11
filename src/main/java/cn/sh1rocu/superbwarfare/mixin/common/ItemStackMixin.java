package cn.sh1rocu.superbwarfare.mixin.common;

import cn.sh1rocu.superbwarfare.api.extension.IDamageable;
import cn.sh1rocu.superbwarfare.api.extension.IRarity;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow
    public abstract Item getItem();

    @Inject(method = "isDamageableItem", at = @At("HEAD"), cancellable = true)
    private void sw$isDamageable(CallbackInfoReturnable<Boolean> cir) {
        if (this.getItem() instanceof IDamageable damageable)
            cir.setReturnValue(damageable.isDamageable((ItemStack) (Object) this));
    }

    @ModifyExpressionValue(method = "getTooltipLines", at = @At(value = "FIELD", target = "Lnet/minecraft/world/item/Rarity;color:Lnet/minecraft/ChatFormatting;"))
    private ChatFormatting sw$modifyTooltipRarityColor(ChatFormatting original) {
        if (this.getItem() instanceof IRarity rarity) {
            ChatFormatting rarityColor = rarity.getRarityColor();
            if (rarityColor != null)
                return rarityColor;
        }
        return original;
    }

    @ModifyExpressionValue(method = "getDisplayName", at = @At(value = "FIELD", target = "Lnet/minecraft/world/item/Rarity;color:Lnet/minecraft/ChatFormatting;"))
    private ChatFormatting sw$modifyDisplayNameRarityColor(ChatFormatting original) {
        if (this.getItem() instanceof IRarity rarity) {
            ChatFormatting rarityColor = rarity.getRarityColor();
            if (rarityColor != null)
                return rarityColor;
        }
        return original;
    }
}
