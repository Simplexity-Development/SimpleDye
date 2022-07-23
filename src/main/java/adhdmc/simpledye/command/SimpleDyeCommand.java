package adhdmc.simpledye.command;

import adhdmc.simpledye.SimpleDye;
import com.destroystokyo.paper.MaterialSetTag;
import com.destroystokyo.paper.MaterialTags;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;

public class SimpleDyeCommand extends SubCommand {

    private static List<String> colorStrings;
    private static final MaterialSetTag RGB_COLORABLE = new MaterialSetTag(new NamespacedKey(SimpleDye.getPlugin(), "rgb_colorable"))
            .add(Material.LEATHER_BOOTS)
            .add(Material.LEATHER_LEGGINGS)
            .add(Material.LEATHER_CHESTPLATE)
            .add(Material.LEATHER_HELMET)
            .add(Material.LEATHER_HORSE_ARMOR);

    public SimpleDyeCommand() {
        super("simple", "Allows dying with Simple Colors represented by names.", "/sd simple <color>");
        List<String> colorStrings = new ArrayList<>();
        for (DyeColor d : EnumSet.allOf(DyeColor.class)) {
            colorStrings.add(d.toString().toLowerCase(Locale.ROOT));
        }
        SimpleDyeCommand.colorStrings = colorStrings;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        // Not a Player Check
        if (!(sender instanceof Player)) {
            // TODO: Not a player error.
            return;
        }

        // No Permissions Check
        if (!sender.hasPermission("simpledye.simple")) {
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

        String colorArg = args[0].toLowerCase(Locale.ROOT);

        // Invalid Color Check
        if (!colorStrings.contains(colorArg)) {
            // TODO: Color invalid error.
            return;
        }

        // Execute Request
        LeatherArmorMeta meta = (LeatherArmorMeta) mainHandItem.getItemMeta();
        meta.setColor(DyeColor.valueOf(colorArg.toUpperCase(Locale.ROOT)).getColor());
        mainHandItem.setItemMeta(meta);
        // TODO: Output colored text to player chat.
    }

    @Override
    public List<String> getSubcommandArguments(CommandSender sender, String[] args) {
        return colorStrings;
    }
}
