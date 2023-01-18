package adhdmc.simpledye.command;

import adhdmc.simpledye.SimpleDye;
import adhdmc.simpledye.util.SDMessage;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.*;

public class CommandHandler implements CommandExecutor, TabExecutor {
    MiniMessage miniMessage = SimpleDye.getMiniMessage();

    private static HashMap<String, SubCommand> subCommands;

    public CommandHandler() {
        subCommands = new HashMap<>();
        subCommands.put("rgb", new RGBDyeCommand());
        subCommands.put("basic", new BasicDyeCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args == null || args.length == 0) {
            sender.sendMessage(miniMessage.deserialize(SDMessage.COMMAND_OUTPUT_DEFAULT.getMessage(),
                    Placeholder.parsed("plugin_prefix", SDMessage.PLUGIN_PREFIX.getMessage())));
            return true;
        }

        String subcommand = args[0].toLowerCase();
        if (subCommands.containsKey(subcommand)) {
            subCommands.get(subcommand).execute(sender, Arrays.copyOfRange(args, 1, args.length));
        } else {
            sender.sendMessage(miniMessage.deserialize(SDMessage.ERROR_INVALID_COMMAND.getMessage(),
                    Placeholder.parsed("plugin_prefix", SDMessage.PLUGIN_PREFIX.getMessage())));
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length <= 1) {
            return new ArrayList<>(subCommands.keySet());
        }
        if (subCommands.containsKey(args[0].toLowerCase(Locale.ROOT))) {
            return subCommands.get(args[0].toLowerCase(Locale.ROOT)).getSubcommandArguments(sender, args);
        }
        return null;
    }
}
