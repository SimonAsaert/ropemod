package tld.sima.ropemod.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import tld.sima.ropemod.Main;
import tld.sima.ropemod.utils.TrackRope;

import java.util.HashMap;
import java.util.UUID;

public class EventClass implements Listener {
    private final Main plugin = Main.getPlugin(Main.class);
    private final HashMap<UUID, UUID> playerSet = new HashMap<>();

    @EventHandler
    public void onClickBat(PlayerInteractEntityEvent event){
        String batName = event.getRightClicked().getCustomName();
        if(event.getHand().equals(EquipmentSlot.HAND) && event.getRightClicked().getCustomName() != null
                && batName != null && batName.contains("rope")){
            if( event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
                playerSet.put(event.getPlayer().getUniqueId(), event.getRightClicked().getUniqueId());
                event.getPlayer().sendMessage("Right click with the Rope tool to set the location of this node!");
            }else if (event.getPlayer().getInventory().getItemInMainHand().isSimilar(plugin.deleteTool)){
                String[] delims = batName.split(" ");
                Entity entity = Bukkit.getEntity(UUID.fromString(delims[2]));
                assert entity != null;
                if(delims[1].equals("1")){
                    event.getRightClicked().remove();
                    entity.remove();
                }else{
                    entity.remove();
                    event.getRightClicked().remove();
                }
                event.getPlayer().sendMessage(ChatColor.RED + "Bat successfully deleted!");
            }
        }
    }

    @EventHandler
    public void onLeftClickBat(EntityDamageByEntityEvent event){
        String batName = event.getEntity().getCustomName();
        if(event.getDamager() instanceof Player p && p.getInventory().getItemInMainHand().equals(plugin.deleteTool) &&
                batName != null && batName.contains("rope")){
            String[] delims = batName.split(" ");
            Entity entity = Bukkit.getEntity(UUID.fromString(delims[2]));
            assert entity != null;
            if(delims[1].equals("1")){
                event.getEntity().remove();
                entity.remove();
            }else{
                entity.remove();
                event.getEntity().remove();
            }
            p.sendMessage(ChatColor.RED + "Bat successfully deleted!");
        }
    }
    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        if (event.getHand() != null && event.getHand().equals(EquipmentSlot.HAND) && event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getItem() != null && event.getItem().isSimilar(plugin.defaultTool)){
            Location loc = event.getClickedBlock().getLocation().add(event.getClickedPosition());
            if(playerSet.containsKey(event.getPlayer().getUniqueId())){
                Entity entity = Bukkit.getEntity(playerSet.get(event.getPlayer().getUniqueId()));
                if(entity != null && entity.getCustomName() != null) {
                    if (entity.getCustomName().split(" ")[1].equals("1")) {
                        entity.teleport(loc.add(TrackRope.firstOffset));
                    } else if (entity.getCustomName().split(" ")[1].equals("2")) {
                        entity.teleport(loc.add(TrackRope.secondOffset));
                    }
                    event.getPlayer().sendMessage("Rope node moved!");
                }else{
                    event.getPlayer().sendMessage("Rope node has been distrupted. Cancelling action!!");
                }
                playerSet.remove(event.getPlayer().getUniqueId());
            }else
                TrackRope.HandleBatPlacement(event.getPlayer(), loc);
        }
    }
}
