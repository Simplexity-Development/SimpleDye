package simplexity.simpledye.command;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import simplexity.simpledye.SimpleDye;
import simplexity.simpledye.config.ConfigHandler;
import simplexity.simpledye.config.LocaleHandler;
import simplexity.simpledye.util.SDPerm;

import java.util.List;

public class ReloadCommand implements TabExecutor {
    MiniMessage miniMessage = SimpleDye.getMiniMessage();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        String pluginPrefix = LocaleHandler.getInstance().getPluginPrefix();
        if (!commandSender.hasPermission(SDPerm.RELOAD_PERM.getPerm())) {
            commandSender.sendMessage(miniMessage.deserialize(pluginPrefix + LocaleHandler.getInstance().getNoPermission()));
            return false;
        }
        ConfigHandler.getInstance().reloadConfig();
        commandSender.sendMessage(miniMessage.deserialize( pluginPrefix + LocaleHandler.getInstance().getConfigReloaded()));
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}
