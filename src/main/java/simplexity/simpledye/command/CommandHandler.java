package simplexity.simpledye.command;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import simplexity.simpledye.SimpleDye;
import simplexity.simpledye.config.LocaleHandler;

import java.util.*;

public class CommandHandler implements TabExecutor {
    MiniMessage miniMessage = SimpleDye.getMiniMessage();

    private static HashMap<String, SubCommand> subCommands;

    public CommandHandler() {
        subCommands = new HashMap<>();
        subCommands.put("rgb", new RGBDyeCommand());
        subCommands.put("basic", new BasicDyeCommand());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        String pluginPrefix = LocaleHandler.getInstance().getPluginPrefix();
        if (args == null || args.length == 0) {
            sender.sendMessage(miniMessage.deserialize(pluginPrefix + LocaleHandler.getInstance().getOutputDefault()));
            return true;
        }
        String subcommand = args[0].toLowerCase();
        if (subCommands.containsKey(subcommand)) {
            subCommands.get(subcommand).execute(sender, Arrays.copyOfRange(args, 1, args.length));
        } else {
            sender.sendMessage(miniMessage.deserialize(pluginPrefix + LocaleHandler.getInstance().getInvalidCommand()));
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length <= 1) {
            return new ArrayList<>(subCommands.keySet());
        }
        if (subCommands.containsKey(args[0].toLowerCase(Locale.ROOT))) {
            return subCommands.get(args[0].toLowerCase(Locale.ROOT)).getSubcommandArguments(sender, args);
        }
        return null;
    }
}
