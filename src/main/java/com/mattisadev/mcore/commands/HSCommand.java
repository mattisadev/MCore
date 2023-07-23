package com.mattisadev.mcore.commands;

import com.mattisadev.mcore.core.HSPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;

public abstract class HSCommand implements CommandExecutor {
    protected final HSPlugin MAIN;

    public HSCommand(HSPlugin main) {
        this.MAIN = main;
    }

    protected abstract String getPermissionToReload();

    protected final boolean reload(CommandSender sender) {
        if (!hasPermission(sender, getPermissionToReload()))
            return false;

        this.MAIN.onReload();

        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                this.MAIN.getMessages().getString("commands.reload.success", "&6&l[!] &eYou have successfully " +
                        "reloaded " + this.MAIN.getName())));

        return true;
    }

    protected final boolean hasPermission(@Nonnull CommandSender sender, @Nonnull String permission) {
        if (sender.hasPermission(permission))
            return true;

        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                this.MAIN.getMessages().getString("no-permission", "&4&l[!] &cYou do not have permission to do " +
                        "that!")));

        return false;
    }
}
