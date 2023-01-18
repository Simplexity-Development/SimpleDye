package adhdmc.simpledye.command;

import adhdmc.simpledye.SimpleDye;
import adhdmc.simpledye.util.SDMessage;
import adhdmc.simpledye.util.SDPerm;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ReloadCommand implements CommandExecutor, TabCompleter {
    MiniMessage miniMessage = SimpleDye.getMiniMessage();
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!commandSender.hasPermission(SDPerm.RELOAD_PERM.getPerm())) {
            commandSender.sendMessage(miniMessage.deserialize(SDMessage.ERROR_NO_PERMISSION.getMessage(),
                    Placeholder.parsed("plugin_prefix", SDMessage.PLUGIN_PREFIX.getMessage())));
            return false;
        }
        SimpleDye.getInstance().reloadConfig();
        SDMessage.reloadMessages();
        commandSender.sendMessage(miniMessage.deserialize(SDMessage.CONFIG_RELOADED.getMessage(),
                Placeholder.parsed("plugin_prefix", SDMessage.PLUGIN_PREFIX.getMessage())));
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}
