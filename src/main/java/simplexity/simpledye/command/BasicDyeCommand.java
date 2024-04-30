package simplexity.simpledye.command;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.DyeColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import simplexity.simpledye.SimpleDye;
import simplexity.simpledye.config.ConfigHandler;
import simplexity.simpledye.config.LocaleHandler;
import simplexity.simpledye.util.SDColor;
import simplexity.simpledye.util.SDPerm;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BasicDyeCommand extends SubCommand {
    MiniMessage miniMessage = SimpleDye.getMiniMessage();

    private static List<String> colorStrings;

    public BasicDyeCommand() {
        super("basic", "Allows dying with Simple Colors represented by names.", "/sd simple <color>");
        List<String> colorStrings = new ArrayList<>();
        for (SDColor color : SDColor.values()) {
            colorStrings.add(color.getColor());
        }
        BasicDyeCommand.colorStrings = colorStrings;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        String pluginPrefix = LocaleHandler.getInstance().getPluginPrefix();
        // Not a Player Check
        if (!(sender instanceof Player)) {
            sender.sendMessage(miniMessage.deserialize(pluginPrefix + LocaleHandler.getInstance().getNotAPlayer()));
            return;
        }

        // No Permissions Check
        if (!sender.hasPermission(SDPerm.DYE_BASIC_PERM.getPerm())) {
            sender.sendMessage(pluginPrefix + LocaleHandler.getInstance().getNoPermission());
            return;
        }

        // No Arguments Check
        if (args.length == 0) {
            sender.sendMessage(pluginPrefix + LocaleHandler.getInstance().getNoArguments());
            return;
        }

        Player player = (Player) sender;
        ItemStack mainHandItem = player.getInventory().getItemInMainHand();

        // Invalid Item Check
        if (!ConfigHandler.getInstance().getBasicDyables().contains(mainHandItem.getType())) {
            player.sendMessage(miniMessage.deserialize(pluginPrefix + LocaleHandler.getInstance().getItemNotDyable(),
                    Placeholder.parsed("item", mainHandItem.getType().toString().toLowerCase(Locale.ROOT))));
            return;
        }

        String colorArg = args[0].toLowerCase(Locale.ROOT);


        // Invalid Color Check
        if (!colorStrings.contains(colorArg)) {
            player.sendMessage(miniMessage.deserialize(pluginPrefix + LocaleHandler.getInstance().getInvalidDyeColor(),
                    Placeholder.parsed("input", args[0])));
            return;
        }
        String colorArgCaps = colorArg.toUpperCase(Locale.ENGLISH);
        String hexColor = SDColor.valueOf(colorArgCaps).getMsgColor();

        // Execute Request
        LeatherArmorMeta meta = (LeatherArmorMeta) mainHandItem.getItemMeta();
        meta.setColor(DyeColor.valueOf(colorArg.toUpperCase(Locale.ROOT)).getColor());
        mainHandItem.setItemMeta(meta);
        player.sendMessage(miniMessage.deserialize(pluginPrefix + LocaleHandler.getInstance().getOutputDyeSuccess(),
                Placeholder.parsed("color", hexColor)));
    }

    @Override
    public List<String> getSubcommandArguments(CommandSender sender, String[] args) {
        return colorStrings;
    }
}
