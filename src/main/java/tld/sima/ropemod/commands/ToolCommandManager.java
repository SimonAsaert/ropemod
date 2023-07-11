package tld.sima.ropemod.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import tld.sima.ropemod.Main;
import tld.sima.ropemod.utils.TrackRope;

public class ToolCommandManager implements CommandExecutor {
    private final Main plugin = Main.getPlugin(Main.class);

    public final String ropeTool = "ropeTool";
    public final String delRope = "delRope";
    public final String cancelRope = "cancelRope";
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {
        if(commandSender instanceof Player p) {
            if (command.getName().equalsIgnoreCase(ropeTool)) {
                if(!p.getInventory().containsAtLeast(plugin.defaultTool, 1))
                    p.getInventory().addItem(plugin.defaultTool);
                commandSender.sendMessage("Right click on two locations to create a rope.");
                return true;
            } else if (command.getName().equalsIgnoreCase(delRope)) {
                if(!p.getInventory().containsAtLeast(plugin.deleteTool, 1))
                    p.getInventory().addItem(plugin.deleteTool);
                commandSender.sendMessage("Left click on the end of the rope to delete the rope." +
                        ChatColor.DARK_RED + " BE CAREFUL! This is Irreversible!");
                return true;
            } else if (command.getName().equalsIgnoreCase(cancelRope)){
                TrackRope.RemovePlayer(p.getUniqueId());
                p.sendMessage("You are no longer creating a rope.");
                return true;
            }
        }else{
            commandSender.sendMessage(ChatColor.RED + "You must be a Player to use this command!");
            return true;
        }
        return false;
    }
}
