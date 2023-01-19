package adhdmc.simpledye.command;

import adhdmc.simpledye.SimpleDye;
import adhdmc.simpledye.util.SDColor;
import adhdmc.simpledye.util.SDMessage;
import adhdmc.simpledye.util.SDPerm;
import com.destroystokyo.paper.MaterialSetTag;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BasicDyeCommand extends SubCommand {
    MiniMessage miniMessage = SimpleDye.getMiniMessage();

    private static List<String> colorStrings;
    private static final MaterialSetTag RGB_COLORABLE = new MaterialSetTag(new NamespacedKey(SimpleDye.getInstance(), "rgb_colorable"))
            .add(Material.LEATHER_BOOTS)
            .add(Material.LEATHER_LEGGINGS)
            .add(Material.LEATHER_CHESTPLATE)
            .add(Material.LEATHER_HELMET)
            .add(Material.LEATHER_HORSE_ARMOR);

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
        // Not a Player Check
        if (!(sender instanceof Player)) {
            sender.sendMessage(miniMessage.deserialize(SDMessage.ERROR_NOT_A_PLAYER.getMessage(),
                    Placeholder.parsed("plugin_prefix", SDMessage.PLUGIN_PREFIX.getMessage())));
            return;
        }

        // No Permissions Check
        if (!sender.hasPermission(SDPerm.DYE_BASIC_PERM.getPerm())) {
            sender.sendMessage(miniMessage.deserialize(SDMessage.ERROR_NO_PERMISSION.getMessage(),
                    Placeholder.parsed("plugin_prefix", SDMessage.PLUGIN_PREFIX.getMessage())));
            return;
        }

        // No Arguments Check
        if (args.length == 0) {
            sender.sendMessage(miniMessage.deserialize(SDMessage.ERROR_NO_ARGUMENTS.getMessage(),
                    Placeholder.parsed("plugin_prefix", SDMessage.PLUGIN_PREFIX.getMessage())));
            return;
        }

        Player player = (Player) sender;
        ItemStack mainHandItem = player.getInventory().getItemInMainHand();

        // Invalid Item Check
        if (!RGB_COLORABLE.isTagged(mainHandItem)) {
            player.sendMessage(miniMessage.deserialize(SDMessage.ERROR_ITEM_NOT_BASIC_DYABLE.getMessage(),
                    Placeholder.parsed("plugin_prefix", SDMessage.PLUGIN_PREFIX.getMessage()),
                    Placeholder.parsed("item", mainHandItem.getType().toString().toLowerCase(Locale.ROOT))));
            return;
        }

        String colorArg = args[0].toLowerCase(Locale.ROOT);


        // Invalid Color Check
        if (!colorStrings.contains(colorArg)) {
            player.sendMessage(miniMessage.deserialize(SDMessage.ERROR_INVALID_DYE_COLOR.getMessage(),
                    Placeholder.parsed("plugin_prefix", SDMessage.PLUGIN_PREFIX.getMessage()),
                    Placeholder.parsed("input", args[0])));
            return;
        }
        String colorArgCaps = colorArg.toUpperCase(Locale.ENGLISH);
        String hexColor = SDColor.valueOf(colorArgCaps).getMsgColor();

        // Execute Request
        LeatherArmorMeta meta = (LeatherArmorMeta) mainHandItem.getItemMeta();
        meta.setColor(DyeColor.valueOf(colorArg.toUpperCase(Locale.ROOT)).getColor());
        mainHandItem.setItemMeta(meta);
        player.sendMessage(miniMessage.deserialize(SDMessage.COMMAND_OUTPUT_DYE_SUCCESS.getMessage(),
                Placeholder.parsed("plugin_prefix", SDMessage.PLUGIN_PREFIX.getMessage()),
                Placeholder.parsed("color", hexColor)));
    }

    @Override
    public List<String> getSubcommandArguments(CommandSender sender, String[] args) {
        return colorStrings;
    }
}
