package cn.sh1rocu.superbwarfare.api.event;

import com.atsuishio.superbwarfare.Mod;
import net.minecraft.resources.ResourceLocation;

public class BaseEvent {

    public static final ResourceLocation HIGHEST = Mod.loc("highest_priority");
    public static final ResourceLocation HIGH = Mod.loc("high_priority");
    public static final ResourceLocation LOW = Mod.loc("low_priority");
    public static final ResourceLocation LOWEST = Mod.loc("lowest_priority");

    protected boolean isCanceled = false;

}
