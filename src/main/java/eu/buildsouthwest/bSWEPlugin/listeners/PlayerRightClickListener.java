package eu.buildsouthwest.bSWEPlugin.listeners;

import eu.buildsouthwest.bSWEPlugin.scripts.CustomWand;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class PlayerRightClickListener implements Listener {
    private final CustomWand cusWand;
    private final Plugin plugin;

    public PlayerRightClickListener(CustomWand cusWand, Plugin plug){
        this.plugin = plug;
        this.cusWand = cusWand;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onItemRightClick(PlayerInteractEvent ev){
        if(ev.getAction().isLeftClick()) return;
        ItemStack it = ev.getItem();

        if(it == null) return;
        if(it.getType() != Material.NETHER_STAR || it.getType() != Material.CLAY_BALL) return;
        ItemMeta itMeta = it.getItemMeta();

        if(itMeta == null || !itMeta.hasDisplayName()) return;
        String itName = PlainTextComponentSerializer.plainText().serialize(itMeta.displayName());

        if(it.getType() == Material.NETHER_STAR && itName.equals("Teleport")){
            ev.setCancelled(true);
            ev.getPlayer().performCommand("warps");
        }
        else if(it.getType() == Material.CLAY_BALL && itName.equals("Join the Discord")){
            ev.setCancelled(true);
            ev.getPlayer().performCommand("discord");
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onWandRightClick(PlayerInteractEvent ev){
        if(ev.getAction().isLeftClick()) return;
        if(ev.getItem() == null || ev.getItem().getType() != Material.WOODEN_AXE) return;

        if(!cusWand.isCustomWandEnabled(ev.getPlayer())) return;
        if(ev.getClickedBlock() != null) return;

        int inc = cusWand.getCustomWantIncrement(ev.getPlayer());
        ev.getPlayer().performCommand("/contract " + inc);
    }
}
