package eu.buildsouthwest.bSWEPlugin;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import eu.buildsouthwest.bSWEPlugin.listeners.PlayerTeleportListener;
import eu.buildsouthwest.bSWEPlugin.scripts.Commands;
import eu.buildsouthwest.bSWEPlugin.scripts.CustomWand;
import eu.buildsouthwest.bSWEPlugin.scripts.RegionService;
import eu.buildsouthwest.bSWEPlugin.scripts.Welcome;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class BSWEPlugin extends JavaPlugin {
    private RegionService regionService;
    private Welcome welcome;
    private CustomWand cusWand;
    private Commands commands;

    public static StateFlag TRIAL_FLAG;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        this.welcome = new Welcome(this);
        this.regionService = new RegionService(this, TRIAL_FLAG);
        this.cusWand = new CustomWand(this);
        this.commands = new Commands(this);

        welcome.init();
        regionService.init();
        cusWand.init();
        commands.init();

        Bukkit.getPluginManager().registerEvents(new PlayerTeleportListener(this, new RegionService(this, TRIAL_FLAG)), this);
    }

    @Override
    public void onLoad(){
        FlagRegistry reg = WorldGuard.getInstance().getFlagRegistry();

        try{
            StateFlag trialFlag = new StateFlag("TrialRegion", false);
            reg.register(trialFlag);
            TRIAL_FLAG = trialFlag;
        }
        catch(FlagConflictException fce){
            Flag<?> existingFlag = reg.get("TrialRegion");
            if(existingFlag instanceof StateFlag){
                TRIAL_FLAG = (StateFlag) existingFlag;
            }
            else {
                this.getLogger().warning("Warning! Another plugin has already claimed the custom flag 'TrialRegion'");
            }
        }
    }

    @Override
    public void onDisable() {
        welcome.shutdown();
        regionService.shutdown();
    }
}
