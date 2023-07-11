package tld.sima.ropemod.utils;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Bat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.UUID;

public class TrackRope {
    private static final HashMap<UUID, Location> batMap = new HashMap<>();
    public static final Vector firstOffset = new Vector(0, -0.31, 0);
    public static final Vector secondOffset = new Vector(0, -0.45, -0.2);

    public static void HandleBatPlacement(Player player, Location loc)
    {
        if(batMap.containsKey(player.getUniqueId()))
        {
            // Location magic numbers
            assert(loc.getWorld() != null);
            Location firstLoc = loc.add(firstOffset);
            assert(firstLoc.getWorld() != null);
            if(!loc.getWorld().getUID().equals(firstLoc.getWorld().getUID())){
                player.sendMessage(ChatColor.GRAY + "You must be in the same world as the original location of your rope. If you want to create a rope here, type " + ChatColor.WHITE + "/cancelRope" + ChatColor.GRAY + " to cancel the original rope and start a new one here.");
                return;
            }

            Location secondLoc = batMap.get(player.getUniqueId()).add(secondOffset);

            // Summon
            Bat firstBat = SummonBat(firstLoc);
            Bat secondBat = SummonBat(secondLoc);

            // Set bat settings
            firstBat.setCustomName("rope 1 " + secondBat.getUniqueId());
            secondBat.setCustomName("rope 2 " + firstBat.getUniqueId());

            secondBat.setLeashHolder(firstBat);
            batMap.remove(player.getUniqueId());
            player.sendMessage("Second position has been marked. A rope should be in place before you! :)");
        }else{
            batMap.put(player.getUniqueId(), loc);
            player.sendMessage("First position has been marked. Right click another block to place the second positon");
        }
    }

    public static void RemovePlayer(UUID uuid){
        batMap.remove(uuid);
    }

    private static Bat SummonBat(Location loc)
    {
        World world = loc.getWorld();
        assert world != null;
        Bat entity = (Bat) world.spawnEntity(loc, EntityType.BAT);
        entity.setAI(false);
        entity.setAwake(true);
        entity.setCollidable(false);
        entity.setInvulnerable(true);
        entity.setRemoveWhenFarAway(false);
        entity.setInvisible(true);
        entity.setSilent(true);
        entity.setPersistent(true);
        entity.setCustomNameVisible(false);
        return entity;
    }
}
