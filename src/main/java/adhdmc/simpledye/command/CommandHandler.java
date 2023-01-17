package adhdmc.simpledye.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.*;

public class CommandHandler implements CommandExecutor, TabExecutor {

    private static HashMap<String, SubCommand> subCommands;

    public CommandHandler() {
        subCommands = new HashMap<>();
        subCommands.put("rgb", new RGBDyeCommand());
        subCommands.put("basic", new SimpleDyeCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args == null || args.length == 0) {
            // TODO: Implement default response.
            return true;
        }

        String subcommand = args[0].toLowerCase();
        if (subCommands.containsKey(subcommand)) {
            subCommands.get(subcommand).execute(sender, Arrays.copyOfRange(args, 1, args.length));
        } else {
            // TODO: Invalid Command Message
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
