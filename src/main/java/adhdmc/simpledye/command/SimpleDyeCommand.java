package adhdmc.simpledye.command;

import adhdmc.simpledye.SimpleDyeMessages;
import adhdmc.simpledye.SimpleDye;
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
            sender.sendMessage(MiniMessage.miniMessage().deserialize(SimpleDyeMessages.notAPlayer,
                    Placeholder.unparsed("command", String.valueOf(args))));
            return;
        }

        // No Permissions Check
        if (!sender.hasPermission("simpledye.simple")) {
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
            sender.sendMessage(MiniMessage.miniMessage().deserialize(SimpleDyeMessages.cannotColor,
                    Placeholder.unparsed("helditem",heldItemString)));
            return;
        }

        String colorArg = args[0].toLowerCase(Locale.ROOT);

        // Invalid Color Check
        if (!colorStrings.contains(colorArg)) {
            sender.sendMessage(MiniMessage.miniMessage().deserialize(SimpleDyeMessages.invalidColor,
                    Placeholder.unparsed("arg", colorArg)));
            return;
        }

        // Execute Request
        LeatherArmorMeta meta = (LeatherArmorMeta) mainHandItem.getItemMeta();
        meta.setColor(DyeColor.valueOf(colorArg.toUpperCase(Locale.ROOT)).getColor());
        mainHandItem.setItemMeta(meta);
        sender.sendMessage(MiniMessage.miniMessage().deserialize(SimpleDyeMessages.dyeSuccessSimple,
                Placeholder.unparsed("helditem", heldItemString),
                Placeholder.parsed("colortype", "<"+colorArg+">"),
                Placeholder.unparsed("color", colorArg)));
    }

    @Override
    public List<String> getSubcommandArguments(CommandSender sender, String[] args) {
        return colorStrings;
    }
}
