package simplexity.simpledye.config;

import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.configuration.file.FileConfiguration;
import simplexity.simpledye.SimpleDye;

import java.util.ArrayList;
import java.util.List;

public class ConfigHandler {
    private static ConfigHandler instance;
    private ConfigHandler() {}

    public static ConfigHandler getInstance() {
        if (instance == null) instance = new ConfigHandler();
        return instance;
    }
    private final List<Material> basicDyables = new ArrayList<>();
    private final List<Material> hexDyables = new ArrayList<>();

    public void reloadConfig() {
        SimpleDye.getInstance().reloadConfig();
        checkBasicDyables();
        checkHexDyables();
        LocaleHandler.getInstance().loadLocale();
    }

    private void checkBasicDyables() {
        FileConfiguration config = SimpleDye.getInstance().getConfig();
        List<String> basicDyableConfig = config.getStringList("basic");
        basicDyables.clear();
        if (basicDyableConfig.isEmpty()) return;
        for (String dyable : basicDyableConfig) {
            Material dyableMaterial = Material.matchMaterial(dyable);
            if (dyableMaterial == null) {
                SimpleDye.getInstance().getLogger().warning("Invalid material in basic list: " + dyable
                        + " - Material will not be added to the list");
                continue;
            }
            if (!Tag.ITEMS_DYEABLE.isTagged(dyableMaterial)) {
                SimpleDye.getInstance().getLogger().warning("Material " + dyableMaterial + " is not dyeable - Material will not be added to the list");
                continue;
            }
            basicDyables.add(dyableMaterial);
        }
    }

    private void checkHexDyables() {
        FileConfiguration config = SimpleDye.getInstance().getConfig();
        List<String> hexConfig = config.getStringList("hex-dye");
        hexDyables.clear();
        if (hexConfig.isEmpty()) return;
        for (String dyable : hexConfig) {
            Material dyableMaterial = Material.matchMaterial(dyable);
            if (dyableMaterial == null) {
                SimpleDye.getInstance().getLogger().warning("Invalid material in hex-dye list: " + dyable
                        + " - Material will not be added to the list");
                continue;
            }
            if (!Tag.ITEMS_DYEABLE.isTagged(dyableMaterial)) {
                SimpleDye.getInstance().getLogger().warning("Material " + dyableMaterial + " is not dyeable - Material will not be added to the list");
                continue;
            }
            hexDyables.add(dyableMaterial);
        }
    }

    public List<Material> getBasicDyables() {
        return basicDyables;
    }

    public List<Material> getHexDyables() {
        return hexDyables;
    }
}
