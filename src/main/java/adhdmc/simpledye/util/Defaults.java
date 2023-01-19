package adhdmc.simpledye.util;

import adhdmc.simpledye.SimpleDye;
import org.bukkit.configuration.file.FileConfiguration;

public class Defaults {

    public static void setConfigDefaults(){
        FileConfiguration config = SimpleDye.getInstance().getConfig();
        config.addDefault("plugin-prefix", "<white>[<#ff6969>S<#ffb169>i<#fff569>m<#c3ff69>p<#82ff69>l<#69ff98>e<#69f5ff>D<#6987ff>y<#7869ff>e<reset><white>]</white> <gray>»</gray>");
        config.addDefault("error-not-a-player", "<plugin_prefix> <red>Sorry, only a player can run this command!");
        config.addDefault("error-no-permission", "<plugin_prefix> <red>Sorry, you do not have permission to use this command");
        config.addDefault("error-invalid-command", "<plugin_prefix> <red>Sorry, the command or arguments that you provided were invalid. Please check your spelling and/or usage and try again");
        config.addDefault("error-no-arguments", "<plugin_prefix> <red>Sorry, you must provide an argument. Please check your usage and try again");
        config.addDefault("error-item-not-basic-dyable", "<plugin_prefix> <red>Sorry, <item> is not dyable. Please hold a dyable item and try again");
        config.addDefault("error-item-not-hex-dyable", "<plugin_prefix> <red>Sorry, <item> is not dyable with a hex code. Please hold a dyable item and try again");
        config.addDefault("error-invalid-hex-code", "<plugin_prefix> <red>Sorry, <input> is not a valid hex code, please use the <yellow>#ABC123</yellow> format for hex codes");
        config.addDefault("error-invalid-dye-color", "<plugin_prefix> <red>Sorry, <input> is not one of the named dye colors. Please check your spelling and try again");
        config.addDefault("command-output-dye-success", "<plugin_prefix> <color>⬛⬛⬛ <green>dye successfully applied");
        config.addDefault("command-output-default", "<plugin_prefix> <gray>Please do <aqua>/dye</aqua> <<blue>basic</blue>|<blue>rgb</blue>> <<yellow>color</yellow>|<yellow>#abc123</yellow>>");
        config.addDefault("config-reloaded", "<plugin_prefix> <gold>Simple Dye Config reloaded!");
    }
}
