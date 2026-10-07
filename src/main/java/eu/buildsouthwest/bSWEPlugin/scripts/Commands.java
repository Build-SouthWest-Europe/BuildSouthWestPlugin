package eu.buildsouthwest.bSWEPlugin.scripts;

import eu.buildsouthwest.bSWEPlugin.BSWEPlugin;
import eu.buildsouthwest.bSWEPlugin.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;

public class Commands {
    private final BSWEPlugin plugin;

    public Commands(BSWEPlugin plugin){ this.plugin = plugin; }

    public void init(){
        plugin.getCommand("leave").setExecutor(new LeaveCommand());
        plugin.getCommand("join").setExecutor(new JoinCommand());
        plugin.getCommand("welcomemessage").setExecutor(new WelcomeMessageCommand());

        plugin.getCommand("time").setExecutor(new AliasCommands());
        plugin.getCommand("weather").setExecutor(new AliasCommands());
        plugin.getCommand("builder").setExecutor(new AliasCommands());
        plugin.getCommand("c").setExecutor(new AliasCommands());
        plugin.getCommand("bn").setExecutor(new AliasCommands());
        plugin.getCommand("plugins").setExecutor(new AliasCommands());
        plugin.getCommand("link").setExecutor(new AliasCommands());
        plugin.getCommand("discordlink").setExecutor(new AliasCommands());
        plugin.getCommand("iblink").setExecutor(new AliasCommands());
        plugin.getCommand("unlink").setExecutor(new AliasCommands());
        plugin.getCommand("discordunlink").setExecutor(new AliasCommands());
        plugin.getCommand("ibunlink").setExecutor(new AliasCommands());
        plugin.getCommand("debugstick").setExecutor(new AliasCommands());
        plugin.getCommand("dstick").setExecutor(new AliasCommands());
        plugin.getCommand("blockwand").setExecutor(new AliasCommands());
        plugin.getCommand("hide").setExecutor(new AliasCommands());
        plugin.getCommand("show").setExecutor(new AliasCommands());

        plugin.getCommand("tpll").setExecutor(new TpllCommand(plugin));

        CommandMap cmdMap = Bukkit.getServer().getCommandMap();
        HelpCommand helpCmd = new HelpCommand();

        Command exists = cmdMap.getCommand("help");
        if (exists != null) {
            exists.unregister(cmdMap);
        }
        cmdMap.register("bsweplugin", helpCmd);

        plugin.getCommand("increment").setExecutor(new IncrementCommand(plugin, new CustomWand(plugin)));
    }

    public void shutdown(){}
}