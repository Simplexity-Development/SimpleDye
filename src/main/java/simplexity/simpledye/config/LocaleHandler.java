package simplexity.simpledye.config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import simplexity.simpledye.SimpleDye;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class LocaleHandler {
    private static LocaleHandler instance;
    private final String fileName = "locale.yml";
    private final File localeFile = new File(SimpleDye.getInstance().getDataFolder(), fileName);
    private final FileConfiguration localeConfig = new YamlConfiguration();
    private final Logger logger = SimpleDye.getInstance().getLogger();
    private String pluginPrefix, notAPlayer, noPermission, invalidCommand, noArguments, itemNotDyable, itemNotHexDyable,
    invalidHexCode, invalidDyeColor, outputDyeSuccess, outputDefault, configReloaded;

    private LocaleHandler() {
        if (!localeFile.exists()) {
            SimpleDye.getInstance().saveResource(fileName, false);
        }
    }

    public static LocaleHandler getInstance() {
        if (instance == null) instance = new LocaleHandler();
        return instance;
    }

    public FileConfiguration getLocaleConfig() {
        return localeConfig;
    }

    public void loadLocale() {
        try {
            localeConfig.load(localeFile);
        } catch (IOException | InvalidConfigurationException e) {
            logger.severe("Issue loading locale.yml");
            e.printStackTrace();
        }
        pluginPrefix = localeConfig.getString("plugin-prefix", "<white>[<#ff6969>S<#ffb169>i<#fff569>m<#c3ff69>p<#82ff69>l<#69ff98>e<#69f5ff>D<#6987ff>y<#7869ff>e<reset><white>]</white> <gray>»</gray>");
        notAPlayer = localeConfig.getString("error.not-a-player", "<red>Sorry, only a player can run this command!</red>");
        noPermission = localeConfig.getString("error.no-permission", "<red>Sorry, you do not have permission to use this command</red>");
        invalidCommand = localeConfig.getString("error.invalid-command", "<red>Sorry, the command or arguments that you provided were invalid. Please check your spelling and/or usage and try again</red>");
        noArguments = localeConfig.getString("error.no-arguments", "<red>Sorry, you must provide an argument. Please check your usage and try again</red>");
        itemNotDyable = localeConfig.getString("error.item-not-basic-dyable", "<red>Sorry, <item> is not dyable. Please hold a dyable item and try again</red>");
        itemNotHexDyable = localeConfig.getString("error.item-not-hex-dyable", "<red>Sorry, <item> is not dyable with a hex code. Please hold a dyable item and try again</red>");
        invalidHexCode = localeConfig.getString("error.invalid-hex-code", "<red>Sorry, <input> is not a valid hex code, please use the <yellow>#ABC123</yellow> format for hex codes</red>");
        invalidDyeColor = localeConfig.getString("error.invalid-dye-color", "<red>Sorry, <input> is not one of the named dye colors. Please check your spelling and try again</red>");
        outputDyeSuccess = localeConfig.getString("command-output.dye-success", "<color>⬛⬛⬛ <green>dye successfully applied");
        outputDefault = localeConfig.getString("command-output.default", "<gray>Please do <aqua>/dye</aqua> <<blue>basic</blue>|<blue>rgb</blue>> <<yellow>color</yellow>|<yellow>#abc123</yellow>></gray>");
        configReloaded = localeConfig.getString("command-output.config-reloaded", "<gold>Simple Dye Config reloaded!</gold>");
    }

    public String getPluginPrefix() {
        return pluginPrefix;
    }

    public String getNotAPlayer() {
        return notAPlayer;
    }

    public String getNoPermission() {
        return noPermission;
    }

    public String getInvalidCommand() {
        return invalidCommand;
    }

    public String getNoArguments() {
        return noArguments;
    }

    public String getItemNotDyable() {
        return itemNotDyable;
    }

    public String getItemNotHexDyable() {
        return itemNotHexDyable;
    }

    public String getInvalidHexCode() {
        return invalidHexCode;
    }

    public String getInvalidDyeColor() {
        return invalidDyeColor;
    }

    public String getOutputDyeSuccess() {
        return outputDyeSuccess;
    }

    public String getOutputDefault() {
        return outputDefault;
    }

    public String getConfigReloaded() {
        return configReloaded;
    }
}
