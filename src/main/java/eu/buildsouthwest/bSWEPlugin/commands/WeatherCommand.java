package eu.buildsouthwest.bSWEPlugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WeatherCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sd, Command cm, String label, String[] args) {
        if (!(sd instanceof Player pl)) {
            sd.sendMessage("This command can only be run by a player.");
            return true;
        }

        if(args[0].equals("rain") || args[0].equals("storm")){
            pl.performCommand("pweather storm");
        }
        else{
            pl.performCommand(args[0].toString());
        }

        return true;
    }
}
