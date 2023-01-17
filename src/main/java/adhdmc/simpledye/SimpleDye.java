package adhdmc.simpledye;

import adhdmc.simpledye.command.CommandHandler;
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
    }

    @Override
    public void onDisable() { }

    public static Plugin getInstance() { return instance; }

    public static MiniMessage getMiniMessage() {
        return miniMessage;
    }
}
