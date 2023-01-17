package adhdmc.simpledye.command;

import adhdmc.simpledye.SimpleDye;
import com.destroystokyo.paper.MaterialSetTag;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RGBDyeCommand extends SubCommand {

    private static final Pattern pattern = Pattern.compile("#?([a-fA-F0-9]{6})");
    private static final MaterialSetTag RGB_COLORABLE = new MaterialSetTag(new NamespacedKey(SimpleDye.getInstance(), "rgb_colorable"))
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
            // TODO: Not a player error.
            return;
        }

        // No Permissions Check
        if (!sender.hasPermission("simpledye.rgb")) {
            // TODO: No permission error.
            return;
        }

        // No Arguments Check
        if (args.length == 0) {
            // TODO: No arguments error.
            return;
        }

        Player player = (Player) sender;
        ItemStack mainHandItem = player.getInventory().getItemInMainHand();

        // Invalid Item Check
        if (!RGB_COLORABLE.isTagged(mainHandItem)) {
            // TODO: Cannot be colored error.
            return;
        }

        Matcher matcher = pattern.matcher(args[0]);

        // Invalid Hex Code Check
        if (!matcher.find()) {
            // TODO: Invalid hex error.
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
        // TODO: Output colored text to player chat.

    }

    @Override
    public List<String> getSubcommandArguments(CommandSender sender, String[] args) {
        return null;
    }
}
