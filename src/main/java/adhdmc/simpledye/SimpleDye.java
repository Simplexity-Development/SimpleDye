package adhdmc.simpledye;

import adhdmc.simpledye.command.CommandHandler;
import adhdmc.simpledye.command.ReloadCommand;
import adhdmc.simpledye.util.Defaults;
import adhdmc.simpledye.util.SDMessage;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleDye extends JavaPlugin {

    private static SimpleDye instance;
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    @Override
    public void onEnable() {
        instance = this;
        this.getCommand("sd").setExecutor(new CommandHandler());
        this.getCommand("sdreload").setExecutor(new ReloadCommand());
        Defaults.setConfigDefaults();
        saveDefaultConfig();
        SDMessage.reloadMessages();
    }

    @Override
    public void onDisable() { }

    public static Plugin getInstance() { return instance; }

    public static MiniMessage getMiniMessage() {
        return miniMessage;
    }
}
