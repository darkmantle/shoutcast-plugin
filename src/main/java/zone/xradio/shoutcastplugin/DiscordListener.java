package zone.xradio.shoutcastplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import github.scarsz.discordsrv.api.ListenerPriority;
import github.scarsz.discordsrv.api.Subscribe;
import github.scarsz.discordsrv.api.events.*;

public class DiscordListener {

    FileConfiguration config;

    public DiscordListener(FileConfiguration config) {
        this.config = config;
    }

    @Subscribe(priority = ListenerPriority.MONITOR)
    public void discordMessageReceived(DiscordGuildMessageReceivedEvent event) {
        switch (event.getMessage().getContentRaw()) {
            case "!np":
                String song = FetchSong.getSongString(config.getString("url"));
                
                if (song != null) {
                    Bukkit.broadcastMessage(ChatColor.BOLD + "Now Playing: " + song);
                }
                
                break;
            default:
                break;
        }
    }

}