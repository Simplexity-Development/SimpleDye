package simplexity.simpledye.command;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Color;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import simplexity.simpledye.SimpleDye;
import simplexity.simpledye.config.ConfigHandler;
import simplexity.simpledye.config.LocaleHandler;
import simplexity.simpledye.util.SDPerm;

import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RGBDyeCommand extends SubCommand {
    MiniMessage miniMessage = SimpleDye.getMiniMessage();

    private static final Pattern pattern = Pattern.compile("#?([a-fA-F0-9]{6})");

    public RGBDyeCommand() {
        super("rgb", "Allows dying with RGB Colors represented in Hex.", "/sd rgb <hex>");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        String pluginPrefix = LocaleHandler.getInstance().getPluginPrefix();
        // Not a Player Check
        if (!(sender instanceof Player player)) {
            sender.sendMessage(miniMessage.deserialize(pluginPrefix + LocaleHandler.getInstance().getNotAPlayer()));
            return;
        }

        // No Permissions Check
        if (!sender.hasPermission(SDPerm.DYE_RGB_PERM.getPerm())) {
            sender.sendMessage(pluginPrefix + LocaleHandler.getInstance().getNoPermission());
            return;
        }

        // No Arguments Check
        if (args.length == 0) {
            sender.sendMessage(pluginPrefix + LocaleHandler.getInstance().getNoArguments());
            return;
        }

        ItemStack mainHandItem = player.getInventory().getItemInMainHand();

        // Invalid Item Check
        if (!ConfigHandler.getInstance().getHexDyables().contains(mainHandItem.getType())) {
            sender.sendMessage(miniMessage.deserialize(pluginPrefix + LocaleHandler.getInstance().getItemNotHexDyable(),
                    Placeholder.parsed("item", mainHandItem.getType().toString().toLowerCase(Locale.ROOT))));
            return;
        }

        Matcher matcher = pattern.matcher(args[0]);

        // Invalid Hex Code Check
        if (!matcher.find()) {
            sender.sendMessage(miniMessage.deserialize(pluginPrefix + LocaleHandler.getInstance().getInvalidHexCode(),
                    Placeholder.parsed("input", args[0])));
            return;
        }

        // Execute Request
        String hex = matcher.group(0);
        String hexTag;
        if (hex.startsWith("#")) {
            hexTag ="<" + hex + ">";
            int length = hex.length();
            hex = hex.substring(1, length);
        } else {
            hexTag = "<#" + hex + ">";
        }
        int red = Integer.parseInt(hex.substring(0,2), 16);
        int green = Integer.parseInt(hex.substring(2,4), 16);
        int blue = Integer.parseInt(hex.substring(4,6), 16);
        LeatherArmorMeta meta = (LeatherArmorMeta) mainHandItem.getItemMeta();
        meta.setColor(Color.fromRGB(red, green, blue));
        mainHandItem.setItemMeta(meta);
        sender.sendMessage(miniMessage.deserialize(pluginPrefix + LocaleHandler.getInstance().getOutputDyeSuccess(),
                Placeholder.parsed("color", hexTag)));
    }

    @Override
    public List<String> getSubcommandArguments(CommandSender sender, String[] args) {
        return null;
    }
}
