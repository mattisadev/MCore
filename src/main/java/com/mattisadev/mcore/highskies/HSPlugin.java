package com.mattisadev.mcore.highskies;

import com.mattisadev.mcore.configuration.ConfigManager;
import com.mattisadev.mcore.inventory.InventoryHandler;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;

import javax.annotation.Nonnull;
import java.util.Set;

public abstract class HSPlugin extends JavaPlugin {
    protected final ConfigManager CONFIG_MANAGER;

    public HSPlugin() {
        this.CONFIG_MANAGER = new ConfigManager(this);
    }

    @Override
    public final void onEnable() {
        loadConfigs(getConfigFileNames());

        if (isUsingInventories()) {
            Bukkit.getPluginManager().registerEvents(new InventoryHandler(), this);
        }

        enable();
    }

    @Override
    public final void onDisable() {
        disable();
    }

    public final void onReload() {
        this.CONFIG_MANAGER.reload("config.yml");
        this.CONFIG_MANAGER.reload("messages.yml");

        reload();
    }

    public abstract void enable();

    public abstract void disable();

    public abstract void reload();
    protected abstract boolean isUsingInventories();
    protected abstract @Nonnull Set<String> getConfigFileNames();

    private void loadConfigs(@Nonnull Set<String> fileNames) {
        fileNames.add("config.yml");
        fileNames.add("messages.yml");

        for (String file : fileNames) {
            getConfigManager().load(file);
            getConfigManager().save(file);
        }
    }

    @Override
    @NonNull
    public final FileConfiguration getConfig() {
        return ConfigManager.get("config.yml");
    }

    public final FileConfiguration getMessages() {
        return ConfigManager.get("messages.yml");
    }

    public final ConfigManager getConfigManager() {
        return this.CONFIG_MANAGER;
    }
}
