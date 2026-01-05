package com.helpgui;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public final class HelpGuiReloadCommand implements CommandExecutor {
    private final HelpGuiPlugin plugin;

    public HelpGuiReloadCommand(HelpGuiPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
                             @NotNull String[] args) {
        if (args.length == 0 || !args[0].equalsIgnoreCase("reload")) {
            sender.sendMessage("Usage: /helpgui reload");
            return true;
        }
        if (!sender.hasPermission("helpgui.reload")) {
            sender.sendMessage("You do not have permission to do that.");
            return true;
        }
        plugin.loadGuiConfig();
        sender.sendMessage("HelpGUI configuration reloaded.");
        return true;
    }
}
