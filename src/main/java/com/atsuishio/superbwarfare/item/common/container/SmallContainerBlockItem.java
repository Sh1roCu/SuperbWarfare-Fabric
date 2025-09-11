package com.atsuishio.superbwarfare.item.common.container;

import cn.sh1rocu.superbwarfare.api.extension.IClientItemExtensions;
import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.client.renderer.item.SmallContainerBlockItemRenderer;
import com.atsuishio.superbwarfare.init.ModBlockEntities;
import com.atsuishio.superbwarfare.init.ModBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
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

public class SmallContainerBlockItem extends BlockItem implements GeoItem, IClientItemExtensions {
    private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);

    public static final List<Supplier<ItemStack>> SMALL_CONTAINERS = List.of(
            () -> SmallContainerBlockItem.createInstance(Mod.loc("containers/blueprints")),
            () -> SmallContainerBlockItem.createInstance(Mod.loc("containers/common"))
    );

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public SmallContainerBlockItem() {
        super(ModBlocks.SMALL_CONTAINER, new Properties().stacksTo(1));
    }

    private PlayState predicate(AnimationState<SmallContainerBlockItem> event) {
        return PlayState.CONTINUE;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
        return new SmallContainerBlockItemRenderer();
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public static ItemStack createInstance(ResourceLocation lootTable) {
        return createInstance(lootTable, 0L);
    }

    public static ItemStack createInstance(ResourceLocation lootTable, long lootTableSeed) {
        ItemStack stack = new ItemStack(ModBlocks.SMALL_CONTAINER);
        CompoundTag tag = new CompoundTag();
        tag.putString("LootTable", lootTable.toString());
        if (lootTableSeed != 0L) {
            tag.putLong("LootTableSeed", lootTableSeed);
        }
        BlockItem.setBlockEntityData(stack, ModBlockEntities.SMALL_CONTAINER, tag);
        return stack;
    }

    @Override
    public void createRenderer(Consumer<Object> consumer) {
        consumer.accept(new RenderProvider() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return SmallContainerBlockItem.this.getCustomRenderer();
            }
        });
    }

    @Override
    public Supplier<Object> getRenderProvider() {
        return renderProvider;
    }
}
