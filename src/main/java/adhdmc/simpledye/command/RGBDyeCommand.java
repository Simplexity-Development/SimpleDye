package adhdmc.simpledye.command;

import adhdmc.simpledye.SimpleDye;
import adhdmc.simpledye.SimpleDyeMessages;
import com.destroystokyo.paper.MaterialSetTag;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RGBDyeCommand extends SubCommand {

    private static final Pattern pattern = Pattern.compile("#?([a-fA-F0-9]{6})");
    private static final MaterialSetTag RGB_COLORABLE = new MaterialSetTag(new NamespacedKey(SimpleDye.getPlugin(), "rgb_colorable"))
            .add(Material.LEATHER_BOOTS)
            .add(Material.LEATHER_LEGGINGS)
            .add(Material.LEATHER_CHESTPLATE)
            .add(Material.LEATHER_HELMET)
            .add(Material.LEATHER_HORSE_ARMOR);

    public RGBDyeCommand() {
        super("rgb", "Allows dying with RGB Colors represented in Hex.", "/sd rgb <hex>");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        // Not a Player Check
        if (!(sender instanceof Player)) {
            sender.sendMessage(MiniMessage.miniMessage().deserialize(SimpleDyeMessages.notAPlayer,
                    Placeholder.unparsed("command", String.valueOf(args))));
            return;
        }

        // No Permissions Check
        if (!sender.hasPermission("simpledye.rgb")) {
            sender.sendMessage(MiniMessage.miniMessage().deserialize(SimpleDyeMessages.permissionDenied));
            return;
        }

        // No Arguments Check
        if (args.length == 0) {
            sender.sendMessage(MiniMessage.miniMessage().deserialize(SimpleDyeMessages.noArguments));
            return;
        }

        Player player = (Player) sender;
        ItemStack mainHandItem = player.getInventory().getItemInMainHand();
        String heldItemString = mainHandItem.getType().name().toLowerCase(Locale.ROOT);

        // Invalid Item Check
        if (!RGB_COLORABLE.isTagged(mainHandItem)) {
            sender.sendMessage(MiniMessage.miniMessage().deserialize(SimpleDyeMessages.cannotRGBColor,
                    Placeholder.unparsed("helditem", heldItemString)));
            return;
        }

        Matcher matcher = pattern.matcher(args[0]);

        // Invalid Hex Code Check
        if (!matcher.find()) {
            sender.sendMessage(MiniMessage.miniMessage().deserialize(SimpleDyeMessages.invalidRGBColor,
                    Placeholder.unparsed("arg", args[0])));
            return;
        }

        // Execute Request
        String hex = matcher.group(0);
        int red = Integer.parseInt(hex.substring(1,3), 16);
        int green = Integer.parseInt(hex.substring(3,5), 16);
        int blue = Integer.parseInt(hex.substring(5,7), 16);
        LeatherArmorMeta meta = (LeatherArmorMeta) mainHandItem.getItemMeta();
        meta.setColor(Color.fromRGB(red, green, blue));
        mainHandItem.setItemMeta(meta);
        sender.sendMessage(MiniMessage.miniMessage().deserialize(SimpleDyeMessages.dyeSuccessSimple,
                Placeholder.unparsed("helditem", heldItemString),
                Placeholder.parsed("colortype", "<"+args[0]+">"),
                Placeholder.unparsed("color", args[0])));

    }

    @Override
    public List<String> getSubcommandArguments(CommandSender sender, String[] args) {
        return null;
    }
}
