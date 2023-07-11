package tld.sima.ropemod;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import tld.sima.ropemod.commands.ToolCommandManager;
import tld.sima.ropemod.events.EventClass;
import tld.sima.ropemod.utils.Tool;

import java.util.Objects;

public class Main extends JavaPlugin {

    public final ItemStack defaultTool = Tool.getDefaultTool();
    public final ItemStack deleteTool = Tool.getDeleteTool();

    @Override
    public void onEnable(){
        // Setup Events
        getServer().getPluginManager().registerEvents(new EventClass(), this);

        // Setup Commands
        ToolCommandManager tcm = new ToolCommandManager();
        Objects.requireNonNull(this.getCommand(tcm.ropeTool)).setExecutor(tcm);
        Objects.requireNonNull(this.getCommand(tcm.delRope)).setExecutor(tcm);
        Objects.requireNonNull(this.getCommand(tcm.cancelRope)).setExecutor(tcm);

        this.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Rope mod Enabled.");
    }

    @Override
    public void onDisable() {
        this.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Rope mod Enabled.");
    }
}
