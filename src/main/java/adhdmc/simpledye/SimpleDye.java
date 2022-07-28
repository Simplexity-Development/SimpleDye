package adhdmc.simpledye;

import adhdmc.simpledye.command.CommandHandler;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleDye extends JavaPlugin {

    private static Plugin plugin;
    public static double version = 1.1;

    @Override
    public void onEnable() {
        plugin = this;
        this.getCommand("sd").setExecutor(new CommandHandler());
    }

    @Override
    public void onDisable() { }

    public static Plugin getPlugin() { return plugin; }
}
