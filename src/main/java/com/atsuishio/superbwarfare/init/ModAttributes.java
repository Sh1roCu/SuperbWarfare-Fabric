package com.atsuishio.superbwarfare.init;

import com.atsuishio.superbwarfare.Mod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;

public class ModAttributes {

    public static void init(){

    }

    public static final Attribute BULLET_RESISTANCE = register("bullet_resistance", new RangedAttribute("attribute." + Mod.MODID + ".bullet_resistance", 0, 0, 1)).setSyncable(true);

    private static Attribute register(String name, Attribute attribute) {
        return Registry.register(BuiltInRegistries.ATTRIBUTE, Mod.loc(name), attribute);
    }
}
