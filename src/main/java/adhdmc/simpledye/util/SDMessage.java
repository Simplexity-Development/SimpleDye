package adhdmc.simpledye.util;

public enum SDMessage {
    PLUGIN_PREFIX("<white>[<#ff6969>S<#ffb169>i<#fff569>m<#c3ff69>p<#82ff69>l<#69ff98>e<#69f5ff>D<#6987ff>y<#7869ff>e<reset><white>]</white> <light_gray>»</light_gray>"),
    ERROR_NOT_A_PLAYER("<plugin_prefix> <red>Sorry, only a player can run this command!"),
    ERROR_NO_PERMISSION("<plugin_prefix> <red>Sorry, you do not have permission to use this command"),
    ERROR_INVALID_COMMAND("<plugin_prefix> <red>Sorry, the command or arguments that you provided were invalid. Please check your spelling and/or usage and try again"),
    ERROR_NO_ARGUMENTS("<plugin_prefix> <red>Sorry, you must provide an argument. Please check your usage and try again"),
    ERROR_ITEM_NOT_BASIC_DYABLE("<plugin_prefix> <red>Sorry, <item> is not dyable. Please hold a dyable item and try again"),
    ERROR_ITEM_NOT_HEX_DYABLE("<plugin_prefix> <red>Sorry, <item> is not dyable with a hex code. Please hold a dyable item and try again"),
    ERROR_INVALID_HEX_CODE("<plugin_prefix> <red>Sorry, <input> is not a valid hex code, please use the <yellow>#ABC123</yellow> format for hex codes"),
    ERROR_INVALID_DYE_COLOR("<plugin_prefix> <red>Sorry, <input> is not one of the named dye colors. Please check your spelling and try again"),
    COMMAND_OUTPUT_DYE_SUCCESS("<plugin_prefix> <green><item> has been dyed <color><color_name>")

    ;
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
}
