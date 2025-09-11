package com.atsuishio.superbwarfare.item.gun.machinegun;

import cn.sh1rocu.superbwarfare.api.extension.IRarity;
import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.client.renderer.gun.MinigunItemRenderer;
import com.atsuishio.superbwarfare.data.gun.GunData;
import com.atsuishio.superbwarfare.item.gun.GunItem;
import com.atsuishio.superbwarfare.tools.RarityTool;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.renderer.GeoItemRenderer;

import java.util.function.Supplier;

public class MinigunItem extends GunItem implements IRarity {

    private ChatFormatting rarityColor;

    public MinigunItem() {
        super(new Properties());
    }

    public MinigunItem(ChatFormatting color) {
        this();
        this.rarityColor = color;
    }

    @Override
    public ChatFormatting getRarityColor() {
        return rarityColor;
    }

    @Override
    public int getCustomRPM(GunData data) {
        return data.data().getInt("CustomRPM");
    }

    @Override
    public Supplier<? extends GeoItemRenderer<? extends Item>> getRenderer() {
        return MinigunItemRenderer::new;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
    }

    @Override
    public ResourceLocation getGunIcon(GunData data) {
        return Mod.loc("textures/gun_icon/minigun_icon.png");
    }

    @Environment(EnvType.CLIENT)
    @Override
    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
        return this.getRenderer().get();
    }

    @Environment(EnvType.CLIENT)
    @Override
    public HumanoidModel.ArmPose getArmPose(LivingEntity entityLiving, InteractionHand hand, ItemStack stack) {
        return super.getArmPose(entityLiving, hand, stack);
        // TODO
//        final HumanoidModel.ArmPose POSE = HumanoidModel.ArmPose.create("Minigun", false, (model, entity, arm) -> {
//            if (arm != HumanoidArm.LEFT) {
//                model.rightArm.xRot = 22.5f * Mth.DEG_TO_RAD + model.head.xRot;
//                model.rightArm.yRot = model.head.yRot;
//                model.leftArm.xRot = Mth.clamp(-45f * Mth.DEG_TO_RAD + model.head.xRot, -67.5f * Mth.DEG_TO_RAD, 0f * Mth.DEG_TO_RAD);
//                model.leftArm.yRot = Mth.clamp(45f * Mth.DEG_TO_RAD + model.head.yRot, 45f * Mth.DEG_TO_RAD, 80f * Mth.DEG_TO_RAD);
//            }
//        });
//        if (!stack.isEmpty()) {
//            if (entityLiving.getUsedItemHand() == hand) {
//                return POSE;
//            }
//        }
//        return HumanoidModel.ArmPose.EMPTY;
    }
}