package com.helpgui;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public final class HelpGuiConfig {
    private final String title;
    private final int rows;
    private final Map<Integer, GuiItem> items;

    public HelpGuiConfig(FileConfiguration config) {
        this.title = colorize(config.getString("gui.title", "&cHelp GUI"));
        this.rows = Math.max(1, Math.min(6, config.getInt("gui.rows", 3)));
        this.items = loadItems(config);
    }

    public String getTitle() {
        return title;
    }

    public int getSize() {
        return rows * 9;
    }

    public Map<Integer, GuiItem> getItems() {
        return Collections.unmodifiableMap(items);
    }

    private Map<Integer, GuiItem> loadItems(FileConfiguration config) {
        ConfigurationSection section = config.getConfigurationSection("gui.items");
        if (section == null) {
            return Collections.emptyMap();
        }
        Map<Integer, GuiItem> loaded = new LinkedHashMap<>();
        for (String key : section.getKeys(false)) {
            ConfigurationSection itemSection = section.getConfigurationSection(key);
            if (itemSection == null) {
                continue;
            }
            int slot = itemSection.getInt("slot", -1);
            if (slot < 0) {
                continue;
            }
            String materialName = itemSection.getString("material", "STONE");
            Material material = Material.matchMaterial(materialName);
            if (material == null) {
                material = Material.STONE;
            }
            int amount = Math.max(1, Math.min(64, itemSection.getInt("amount", 1)));
            ItemStack itemStack = new ItemStack(material, amount);
            ItemMeta meta = itemStack.getItemMeta();
            if (meta != null) {
                meta.setDisplayName(colorize(itemSection.getString("name", "")));
                List<String> lore = itemSection.getStringList("lore");
                if (!lore.isEmpty()) {
                    meta.setLore(lore.stream().map(HelpGuiConfig::colorize).toList());
                }
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
                itemStack.setItemMeta(meta);
            }
            String command = itemSection.getString("command", "");
            boolean close = itemSection.getBoolean("close", true);
            loaded.put(slot, new GuiItem(itemStack, command, close));
        }
        return loaded;
    }

    private static String colorize(String input) {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNullElse(input, ""));
    }
}
