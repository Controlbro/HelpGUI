package com.helpgui;

import org.bukkit.inventory.ItemStack;

public record GuiItem(ItemStack itemStack, String command, boolean closeOnClick) {
}
