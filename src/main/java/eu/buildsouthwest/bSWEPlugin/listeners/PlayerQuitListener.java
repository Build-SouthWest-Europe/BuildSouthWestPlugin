package eu.buildsouthwest.bSWEPlugin.listeners;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerQuit(PlayerQuitEvent ev){
        ev.quitMessage(
                Component.text(ev.getPlayer().getName(), NamedTextColor.YELLOW).decorate(TextDecoration.BOLD)
                .append(Component.text(" left the server!", NamedTextColor.YELLOW))
        );
    }
}
