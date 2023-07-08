package tld.sima.ropemod;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable(){
        this.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Rope mod Enabled.");
    }

    @Override
    public void onDisable() {
        this.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Rope mod Enabled.");
    }
}
