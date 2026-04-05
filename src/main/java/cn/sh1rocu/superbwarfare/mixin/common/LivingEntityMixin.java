package cn.sh1rocu.superbwarfare.mixin.common;

import com.atsuishio.superbwarfare.init.ModAttributes;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @ModifyReturnValue(method = "createLivingAttributes", at = @At("RETURN"))
    private static AttributeSupplier.Builder sw$createLivingAttributes(AttributeSupplier.Builder original) {
        return original.add(ModAttributes.BULLET_RESISTANCE);
    }
}
