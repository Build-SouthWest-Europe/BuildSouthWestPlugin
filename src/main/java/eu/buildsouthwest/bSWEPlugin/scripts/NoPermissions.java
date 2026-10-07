package eu.buildsouthwest.bSWEPlugin.scripts;

import eu.buildsouthwest.bSWEPlugin.BSWEPlugin;
import eu.buildsouthwest.bSWEPlugin.listeners.EntityToggleGlideListener;
import eu.buildsouthwest.bSWEPlugin.listeners.PlayerCommandsListener;
import org.bukkit.Bukkit;

public class NoPermissions {
    private final BSWEPlugin plugin;

    public NoPermissions(BSWEPlugin plugin){
        this.plugin = plugin;
    }

    public void init(){
        Bukkit.getPluginManager().registerEvents(new PlayerCommandsListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new EntityToggleGlideListener(), plugin);
    }

    public void shutdown(){}
}
