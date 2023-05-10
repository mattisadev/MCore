package com.mattisadev.mcore.inventory;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public interface GUI extends InventoryHolder {

    void onInventoryClick(InventoryClickEvent paramInventoryClickEvent);

    void onInventoryClose(InventoryCloseEvent paramInventoryCloseEvent);

    void addContent(Inventory paramInventory);
}
