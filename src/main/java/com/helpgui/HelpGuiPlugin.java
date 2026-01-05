package com.helpgui;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class HelpGuiPlugin extends JavaPlugin {
    private HelpGuiConfig guiConfig;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadGuiConfig();

        PluginCommand helpCommand = getCommand("help");
        if (helpCommand != null) {
            helpCommand.setExecutor(new HelpGuiCommand(this));
        }
        PluginCommand reloadCommand = getCommand("helpgui");
        if (reloadCommand != null) {
            reloadCommand.setExecutor(new HelpGuiReloadCommand(this));
        }

        Bukkit.getPluginManager().registerEvents(new HelpGuiListener(this), this);
    }

    public void loadGuiConfig() {
        reloadConfig();
        this.guiConfig = new HelpGuiConfig(getConfig());
    }

    public HelpGuiConfig getGuiConfig() {
        return guiConfig;
    }
}
