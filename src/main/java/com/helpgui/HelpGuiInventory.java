package com.helpgui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public final class HelpGuiInventory {
    private HelpGuiInventory() {
    }

    public static void openFor(Player player, HelpGuiConfig config) {
        Inventory inventory = Bukkit.createInventory(player, config.getSize(), config.getTitle());
        config.getItems().forEach((slot, item) -> {
            if (slot >= 0 && slot < inventory.getSize()) {
                inventory.setItem(slot, item.itemStack());
            }
        });
        player.openInventory(inventory);
    }
}
