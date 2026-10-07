package eu.buildsouthwest.bSWEPlugin.listeners;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.inventory.ItemStack;

public class EntityToggleGlideListener implements Listener {
    private final Component DONO = Component.text("You must be a ", NamedTextColor.RED)
            .append(Component.text("Donator", NamedTextColor.RED).decorate(TextDecoration.BOLD))
            .append(Component.text(" to use an elytra.", NamedTextColor.RED));

    @EventHandler (priority = EventPriority.NORMAL)
    public void OnPlayerStartGlide(EntityToggleGlideEvent ev){
        if(ev.getEntity() instanceof Player pl && ev.isGliding()){
            if(!pl.hasPermission("server.elytra.use")) {
                ev.setCancelled(true);
                pl.setGliding(false);
                pl.sendMessage(DONO);

                if(pl.getInventory().getChestplate().getType() == Material.ELYTRA) pl.getInventory().setChestplate(new ItemStack(Material.AIR));
            }
        }
    }
}
