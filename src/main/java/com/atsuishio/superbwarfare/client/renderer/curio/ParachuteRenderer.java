package com.atsuishio.superbwarfare.client.renderer.curio;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.client.model.curio.ParachuteModel;
import com.atsuishio.superbwarfare.item.curio.ParachuteItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.client.TrinketRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderBuffers;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class ParachuteRenderer implements TrinketRenderer {

    private static ParachuteModel firstPersonModel;
    private static final ResourceLocation TEXTURE = Mod.loc("textures/trinkets/parachute.png");

    private ParachuteModel model;


    @Override
    public void render(ItemStack stack, SlotReference slotContext, EntityModel<? extends LivingEntity> contextModel,
                       PoseStack matrixStack, MultiBufferSource renderTypeBuffer, int light, LivingEntity entity,
                       float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw,
                       float headPitch) {
        if (model == null) {
            model = new ParachuteModel(Minecraft.getInstance().getEntityModels().bakeLayer(ParachuteModel.LAYER_LOCATION));
        }

        matrixStack.pushPose();

        matrixStack.scale(0.5f, 0.5f, 0.5f);
        matrixStack.translate(0, 1.25, 0);

        if (stack.getOrCreateTag().getBoolean(ParachuteItem.TAG_OPEN)) {
            this.model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
            this.model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

            VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(renderTypeBuffer, RenderType.armorCutoutNoCull(TEXTURE), false, stack.hasFoil());

            model.renderToBuffer(matrixStack, vertexconsumer, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        }

        matrixStack.popPose();
    }

    public static void onRenderLevelStage(WorldRenderContext context) {
        RenderBuffers buffers = Minecraft.getInstance().renderBuffers();
        var player = Minecraft.getInstance().player;
        if (player == null) return;
        if (!ParachuteItem.isParachuteOpen(player)) return;
        if (!ParachuteItem.isParachuteVisible(player)) return;
        PoseStack stack = context.matrixStack();

        if (Minecraft.getInstance().options.getCameraType() == CameraType.FIRST_PERSON) {
            stack.pushPose();

            if (firstPersonModel == null) {
                firstPersonModel = new ParachuteModel(Minecraft.getInstance().getEntityModels().bakeLayer(ParachuteModel.LAYER_LOCATION));
            }

            stack.mulPose(Axis.XP.rotationDegrees(180));
            stack.mulPose(Axis.YP.rotationDegrees(player.getViewYRot(1f)));
            stack.translate(0, 1.5, 0);

            firstPersonModel.prepareMobModel(player, 0, 0, context.tickDelta());
            firstPersonModel.setupAnim(player, 0, 0, player.tickCount, 0, 0);
            firstPersonModel.renderToBuffer(stack, buffers.bufferSource().getBuffer(RenderType.armorCutoutNoCull(TEXTURE)), 0xFFFFFF, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

            stack.popPose();
        }
    }
}
