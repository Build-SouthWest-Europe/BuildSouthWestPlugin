package eu.buildsouthwest.bSWEPlugin.listeners;

import eu.buildsouthwest.bSWEPlugin.scripts.CustomWand;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

public class PlayerLeftClickListener implements Listener {
    private final CustomWand cusWand;
    private final Plugin plugin;

    public PlayerLeftClickListener(CustomWand cusWand, Plugin plug){
        this.plugin = plug;
        this.cusWand = cusWand;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onWandLeftClick(PlayerInteractEvent ev){
        if(ev.getAction().isRightClick()) return;
        if(ev.getItem() == null || ev.getItem().getType() != Material.WOODEN_AXE) return;

        if(!cusWand.isCustomWandEnabled(ev.getPlayer())) return;
        if(ev.getClickedBlock() != null) return;

        if(ev.getPlayer().isSneaking()){
            ev.getPlayer().performCommand("/shift " + cusWand.getCustomWantIncrement(ev.getPlayer()));
        }
        else{
            ev.getPlayer().performCommand("/expand " + cusWand.getCustomWantIncrement(ev.getPlayer()));
        }
    }
}
