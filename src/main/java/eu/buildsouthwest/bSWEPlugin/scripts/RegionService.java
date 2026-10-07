package eu.buildsouthwest.bSWEPlugin.scripts;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public class RegionService {
    private final JavaPlugin plugin;
    private static StateFlag TRIAL_FLAG;

    public RegionService(JavaPlugin plugin, StateFlag trial) {
        this.plugin = plugin;
        TRIAL_FLAG = trial;
    }

    public RegionService(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void init() {}

    public boolean isInRegion(Location loc, String regName){
        RegionContainer cont = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery qry = cont.createQuery();
        ApplicableRegionSet regions = qry.getApplicableRegions(BukkitAdapter.adapt(loc));

        for(ProtectedRegion reg : regions){
            if(reg.getId().equalsIgnoreCase(regName)) return true;
        }

        return false;
    }

    public static boolean isTrialRegion(Location loc){
        RegionContainer cont = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery qry = cont.createQuery();
        StateFlag.State state = qry.queryState(BukkitAdapter.adapt(loc), null, TRIAL_FLAG);

        return state == StateFlag.State.ALLOW;
    }

    public void shutdown(){}
}
