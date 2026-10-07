package eu.buildsouthwest.bSWEPlugin.listeners;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommandsListener implements Listener {
    private final Component TP = Component.text("Please use ", NamedTextColor.DARK_RED)
            .append(Component.text("/tpa", NamedTextColor.DARK_RED).decorate(TextDecoration.BOLD))
            .append(Component.text(" to teleport to other players or "))
            .append(Component.text("/warp", NamedTextColor.DARK_RED).decorate(TextDecoration.BOLD))
            .append(Component.text(" to teleport to locations!", NamedTextColor.DARK_RED));
    private final Component FAST = Component.text("Fast mode is ", NamedTextColor.DARK_RED)
            .append(Component.text("admin only", NamedTextColor.DARK_RED).decorate(TextDecoration.BOLD))
            .append(Component.text("!", NamedTextColor.DARK_RED));
    private final Component DEFAULT = Component.text(" ");

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerCommand(PlayerCommandPreprocessEvent ev){
        String[] parts = ev.getMessage().substring(1).split(" ");
        String perm = "";
        Component message;

        switch (parts[0].toLowerCase()){
            case "tp":
                perm = "essentials.tp";
                message = TP;
                break;
            case "fast":
                perm = "worldedit.fast";
                message = FAST;
                break;
            default:
                perm = "";
                message = DEFAULT;
        }

        if (!ev.getPlayer().hasPermission(perm)) {
            ev.setCancelled(true);
            ev.getPlayer().sendMessage(message);
        }
    }
}
