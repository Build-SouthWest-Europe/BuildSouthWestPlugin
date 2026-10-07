package eu.buildsouthwest.bSWEPlugin.listeners;

import eu.buildsouthwest.bSWEPlugin.scripts.RegionService;
import eu.buildsouthwest.bSWEPlugin.scripts.Welcome;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerTeleportListener implements Listener {
    private final JavaPlugin plugin;
    private final RegionService regServ;

    public PlayerTeleportListener(JavaPlugin plugin, RegionService regServ) {
        this.plugin = plugin;
        this.regServ = regServ;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerTP(PlayerTeleportEvent ev){
        Player pl = ev.getPlayer();
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            Location loc = pl.getLocation();
            if(regServ.isInRegion(loc, "lobby")){
                if(!pl.hasPermission("creative.in.spawn") && !pl.hasPermission("saveInventory.in.spawn")){
                    Welcome.cleanInv(pl);
                }

                if(!pl.getGameMode().equals(GameMode.ADVENTURE)){
                    pl.setGameMode(GameMode.ADVENTURE);
                }
            }
            else if(pl.getGameMode().equals(GameMode.ADVENTURE)){
                pl.setGameMode(GameMode.CREATIVE);
            }
        }, 2L);
    }
}
