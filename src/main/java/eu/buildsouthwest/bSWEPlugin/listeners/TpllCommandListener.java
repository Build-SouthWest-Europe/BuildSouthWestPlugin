package eu.buildsouthwest.bSWEPlugin.listeners;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class TpllCommandListener implements Listener {
    private final Component TPLL = Component.text("This command is ", NamedTextColor.DARK_RED)
            .append(Component.text("builder only", NamedTextColor.DARK_RED)).decorate(TextDecoration.BOLD)
            .append(Component.text(", please use ", NamedTextColor.DARK_RED))
            .append(Component.text("/warp", NamedTextColor.GOLD)).decorate(TextDecoration.BOLD)
            .append(Component.text("!", NamedTextColor.DARK_RED));
    private final Component DENY = Component.text("You are not in the ", NamedTextColor.DARK_RED)
            .append(Component.text("same trial zone", NamedTextColor.DARK_RED).decorate(TextDecoration.BOLD))
            .append(Component.text(" as this location, please use ", NamedTextColor.DARK_RED))
            .append(Component.text("/trialzones", NamedTextColor.GOLD).decorate(TextDecoration.BOLD));
    private final Component ERROR = Component.text("Could not resolve the coordinates at the moment", NamedTextColor.RED);
    private final Component USAGE = Component.text("");

    @EventHandler(priority = EventPriority.NORMAL)
    public void onTpllCommand(PlayerCommandPreprocessEvent ev){
        if(ev.getPlayer().hasPermission("t+-.tpll")) return;

        if(ev.getPlayer().hasPermission("tpll.trial")){
            String[] args = ev.getMessage().substring(1).split(" ");

            if(args[1] == null || args[2] == null){
                ev.getPlayer().sendMessage(USAGE);
                return;
            }

            String lat = args[1].replaceAll(",$", "");
        }
        else{
            ev.getPlayer().sendMessage(TPLL);
            ev.setCancelled(true);
        }
    }
}
