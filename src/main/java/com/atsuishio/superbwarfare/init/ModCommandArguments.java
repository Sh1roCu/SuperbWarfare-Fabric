package com.atsuishio.superbwarfare.init;

import com.atsuishio.superbwarfare.Mod;
import com.atsuishio.superbwarfare.command.LowerCamelCaseEnumArgument;
import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry;

public class ModCommandArguments {

    public static void init() {
        ArgumentTypeRegistry.registerArgumentType(Mod.loc("lower_camel_case_enum"), LowerCamelCaseEnumArgument.class, new LowerCamelCaseEnumArgument.Info());
    }

}
