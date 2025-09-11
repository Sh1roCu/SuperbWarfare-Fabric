package com.atsuishio.superbwarfare.item.common.container;

import cn.sh1rocu.superbwarfare.api.extension.IClientItemExtensions;
import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.client.renderer.item.LuckyContainerBlockItemRenderer;
import com.atsuishio.superbwarfare.init.ModBlockEntities;
import com.atsuishio.superbwarfare.init.ModBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class LuckyContainerBlockItem extends BlockItem implements GeoItem, IClientItemExtensions {
    private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);

    public static final List<Supplier<ItemStack>> LUCKY_CONTAINERS = List.of(
            () -> LuckyContainerBlockItem.createInstance(Mod.loc("mobile_vehicles"), Mod.loc("textures/gui/vehicle/type/civilian.png")),
            () -> LuckyContainerBlockItem.createInstance(Mod.loc("land_vehicles"), Mod.loc("textures/gui/vehicle/type/land.png")),
            () -> LuckyContainerBlockItem.createInstance(Mod.loc("aircraft"), Mod.loc("textures/gui/vehicle/type/aircraft.png")),
            () -> LuckyContainerBlockItem.createInstance(Mod.loc("controllable_turrets"), Mod.loc("textures/gui/vehicle/type/defense.png"))
    );

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public LuckyContainerBlockItem() {
        super(ModBlocks.LUCKY_CONTAINER, new Properties().stacksTo(1).rarity(Rarity.EPIC));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        return InteractionResult.PASS;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        BlockHitResult playerPOVHitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.WATER);
        if (playerPOVHitResult.getType() == HitResult.Type.MISS) {
            return super.use(level, player, hand);
        }
        BlockHitResult blockHitResult = playerPOVHitResult.withPosition(playerPOVHitResult.getBlockPos().above());
        InteractionResult interactionresult = super.useOn(new UseOnContext(player, hand, blockHitResult));
        return new InteractionResultHolder<>(interactionresult, player.getItemInHand(hand));
    }

    private PlayState predicate(AnimationState<LuckyContainerBlockItem> event) {
        return PlayState.CONTINUE;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
        return new LuckyContainerBlockItemRenderer();
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public static ItemStack createInstance(ResourceLocation location, @Nullable ResourceLocation icon) {
        ItemStack stack = new ItemStack(ModBlocks.LUCKY_CONTAINER);
        CompoundTag tag = new CompoundTag();
        tag.putString("Location", location.toString());
        if (icon != null) {
            tag.putString("Icon", icon.toString());
        }
        BlockItem.setBlockEntityData(stack, ModBlockEntities.LUCKY_CONTAINER, tag);
        return stack;
    }

    public static ItemStack createInstance(ResourceLocation location) {
        return createInstance(location, null);
    }

    @Override
    public void createRenderer(Consumer<Object> consumer) {
        consumer.accept(new RenderProvider() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return LuckyContainerBlockItem.this.getCustomRenderer();
            }
        });
    }

    @Override
    public Supplier<Object> getRenderProvider() {
        return renderProvider;
    }
}
