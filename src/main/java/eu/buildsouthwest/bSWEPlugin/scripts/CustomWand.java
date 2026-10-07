package eu.buildsouthwest.bSWEPlugin.scripts;

import eu.buildsouthwest.bSWEPlugin.BSWEPlugin;
import eu.buildsouthwest.bSWEPlugin.listeners.PlayerLeftClickListener;
import eu.buildsouthwest.bSWEPlugin.listeners.PlayerRightClickListener;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

public class CustomWand {
    private final BSWEPlugin plugin;

    public CustomWand(BSWEPlugin plugin){
        this.plugin = plugin;
    }

    public void init(){
        Bukkit.getPluginManager().registerEvents(new PlayerLeftClickListener(new CustomWand(plugin), plugin), plugin);
        Bukkit.getPluginManager().registerEvents(new PlayerRightClickListener(new CustomWand(plugin), plugin), plugin);
    }

    public int getCustomWantIncrement(Player pl) {
        return pl.getPersistentDataContainer().getOrDefault(new NamespacedKey(plugin, "increment"), PersistentDataType.INTEGER, 1);
    }

    public boolean isCustomWandEnabled(Player pl){
        return pl.getPersistentDataContainer().has(new NamespacedKey(plugin, "incrementEnable"), PersistentDataType.BOOLEAN);
    }
}
