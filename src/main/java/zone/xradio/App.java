package zone.xradio;

import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import github.scarsz.discordsrv.DiscordSRV;

public class App extends JavaPlugin implements Listener {

    FileConfiguration config = this.getConfig();
    private DiscordListener discordListener = new DiscordListener(config);

    @Override
    public void onEnable() {
        getLogger().info("Enabling XR Plugin");

        // Default config
        config.addDefault("url", "http://xradio.zone:9420/stats");
        config.options().copyDefaults(true);
        saveConfig();

        // Subscript to DiscordSRV API for !np
        DiscordSRV.api.subscribe(discordListener);

        // Register events
        Bukkit.getServer().getPluginManager().registerEvents(this, this);

        // Enable bStats
        new Metrics(this, 16059);
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabling XR Plugin");
    }

    @EventHandler
    public void AsyncChatEvent(AsyncPlayerChatEvent e) {
        if (e.getMessage().equalsIgnoreCase("!np")) {

            getLogger().warning(config.getString("url"));

            String song = FetchSong.getSongString(config.getString("url"));
            if (song == null) {
                return;
            }
            e.getPlayer().sendMessage(ChatColor.BOLD + "Now Playing: " + song);
        }

    }
}