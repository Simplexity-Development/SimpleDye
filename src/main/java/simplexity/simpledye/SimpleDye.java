package simplexity.simpledye;

import simplexity.simpledye.command.CommandHandler;
import simplexity.simpledye.command.ReloadCommand;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import simplexity.simpledye.config.ConfigHandler;

public final class SimpleDye extends JavaPlugin {

    private static SimpleDye instance;
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    @Override
    public void onEnable() {
        instance = this;
        this.getCommand("sd").setExecutor(new CommandHandler());
        this.getCommand("sdreload").setExecutor(new ReloadCommand());
        saveDefaultConfig();
        ConfigHandler.getInstance().reloadConfig();
    }

    @Override
    public void onDisable() { }

    public static Plugin getInstance() { return instance; }

    public static MiniMessage getMiniMessage() {
        return miniMessage;
    }
}
