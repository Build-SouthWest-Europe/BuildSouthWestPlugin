package eu.buildsouthwest.bSWEPlugin.listeners;

import eu.buildsouthwest.bSWEPlugin.scripts.RegionService;
import eu.buildsouthwest.bSWEPlugin.scripts.Welcome;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerJoinListener implements Listener {
    private final JavaPlugin plugin;
    private final RegionService regServ;

    public PlayerJoinListener(JavaPlugin plugin, RegionService regServ) {
        this.plugin = plugin;
        this.regServ = regServ;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoin(PlayerJoinEvent ev){
        Player pl = ev.getPlayer();

        if(pl.hasPlayedBefore()){
            ev.joinMessage(Component.text(pl.getName(), NamedTextColor.YELLOW).decorate(TextDecoration.BOLD)
                .append(Component.text(" joined the server!", NamedTextColor.YELLOW)));
            if(pl.hasPermission("custom.noclear")){
                Welcome.builderPlayer(pl);
            }
            else{
                Welcome.oldPlayer(pl);
            }
        }
        else{
            ev.joinMessage(Component.text(pl.getName(), NamedTextColor.YELLOW).decorate(TextDecoration.BOLD)
                .append(Component.text(" joined the server for the first time!", NamedTextColor.YELLOW)));
            Welcome.newPlayer(pl);
        }

//        if(!pl.hasPermission("essentials.nick") || !pl.getName().equals(
//                PlainTextComponentSerializer.plainText()
//                .serialize(pl.displayName()))){
//            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "nick " + pl.getName() + " off");
//        }

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
        }, 1L);
    }
}
