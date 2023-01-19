package adhdmc.simpledye.util;

import adhdmc.simpledye.SimpleDye;
import org.bukkit.configuration.file.FileConfiguration;

public enum SDMessage {
    PLUGIN_PREFIX("<white>[<#ff6969>S<#ffb169>i<#fff569>m<#c3ff69>p<#82ff69>l<#69ff98>e<#69f5ff>D<#6987ff>y<#7869ff>e<reset><white>]</white> <gray>»</gray>"),
    ERROR_NOT_A_PLAYER("<plugin_prefix> <red>Sorry, only a player can run this command!"),
    ERROR_NO_PERMISSION("<plugin_prefix> <red>Sorry, you do not have permission to use this command"),
    ERROR_INVALID_COMMAND("<plugin_prefix> <red>Sorry, the command or arguments that you provided were invalid. Please check your spelling and/or usage and try again"),
    ERROR_NO_ARGUMENTS("<plugin_prefix> <red>Sorry, you must provide an argument. Please check your usage and try again"),
    ERROR_ITEM_NOT_BASIC_DYABLE("<plugin_prefix> <red>Sorry, <item> is not dyable. Please hold a dyable item and try again"),
    ERROR_ITEM_NOT_HEX_DYABLE("<plugin_prefix> <red>Sorry, <item> is not dyable with a hex code. Please hold a dyable item and try again"),
    ERROR_INVALID_HEX_CODE("<plugin_prefix> <red>Sorry, <input> is not a valid hex code, please use the <yellow>#ABC123</yellow> format for hex codes"),
    ERROR_INVALID_DYE_COLOR("<plugin_prefix> <red>Sorry, <input> is not one of the named dye colors. Please check your spelling and try again"),
    COMMAND_OUTPUT_DYE_SUCCESS("<plugin_prefix> <color>⬛⬛⬛ <green>dye successfully applied"),
    COMMAND_OUTPUT_DEFAULT("<plugin_prefix> <gray>Please do <aqua>/dye</aqua> <<blue>basic</blue>|<blue>rgb</blue>> <<yellow>color</yellow>|<yellow>#abc123</yellow>>"),
    CONFIG_RELOADED("<plugin_prefix> <gold>Simple Dye Config reloaded!");
    String message;
    SDMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static void reloadMessages(){
        FileConfiguration config = SimpleDye.getInstance().getConfig();
        PLUGIN_PREFIX.setMessage(config.getString("plugin-prefix"));
        ERROR_NOT_A_PLAYER.setMessage(config.getString("error-not-a-player"));
        ERROR_NO_PERMISSION.setMessage(config.getString("error-no-permission"));
        ERROR_INVALID_COMMAND.setMessage(config.getString("error-invalid-command"));
        ERROR_NO_ARGUMENTS.setMessage(config.getString("error-no-arguments"));
        ERROR_ITEM_NOT_BASIC_DYABLE.setMessage(config.getString("error-item-not-basic-dyable"));
        ERROR_ITEM_NOT_HEX_DYABLE.setMessage(config.getString("error-item-not-hex-dyable"));
        ERROR_INVALID_HEX_CODE.setMessage(config.getString("error-invalid-hex-code"));
        ERROR_INVALID_DYE_COLOR.setMessage(config.getString("error-invalid-dye-color"));
        COMMAND_OUTPUT_DYE_SUCCESS.setMessage(config.getString("command-output-dye-success"));
        COMMAND_OUTPUT_DEFAULT.setMessage(config.getString("command-output-default"));
        CONFIG_RELOADED.setMessage(config.getString("config-reloaded"));
    }
}
