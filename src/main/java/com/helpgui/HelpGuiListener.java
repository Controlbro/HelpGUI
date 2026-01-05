package com.helpgui;

import java.util.Map;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;

public final class HelpGuiListener implements Listener {
    private final HelpGuiPlugin plugin;

    public HelpGuiListener(HelpGuiPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        InventoryView view = event.getView();
        HelpGuiConfig config = plugin.getGuiConfig();
        if (!view.getTitle().equals(config.getTitle())) {
            return;
        }
        event.setCancelled(true);

        if (!(event.getWhoClicked() instanceof Player player)) {
            return;
        }
        if (event.getClickedInventory() == null || event.getClickedInventory() != view.getTopInventory()) {
            return;
        }
        Map<Integer, GuiItem> items = config.getItems();
        GuiItem guiItem = items.get(event.getSlot());
        if (guiItem == null) {
            return;
        }
        String command = guiItem.command();
        if (command != null && !command.isBlank()) {
            player.performCommand(command);
        }
        if (guiItem.closeOnClick()) {
            player.closeInventory();
        }
    }
}
