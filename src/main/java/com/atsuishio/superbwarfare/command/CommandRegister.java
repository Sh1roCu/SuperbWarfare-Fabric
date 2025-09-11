package com.atsuishio.superbwarfare.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class CommandRegister {
    public static void registerCommand(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext registryAccess, Commands.CommandSelection environment) {
        var command = Commands.literal("sbw");
        command.then(AmmoCommand.get());
        command.then(ConfigCommand.get());

        var result = dispatcher.register(command);
        dispatcher.register(Commands.literal("superbwarfare").redirect(result));
    }
}
