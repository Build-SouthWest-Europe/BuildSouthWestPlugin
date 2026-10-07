package eu.buildsouthwest.bSWEPlugin.commands;

import eu.buildsouthwest.bSWEPlugin.scripts.Welcome;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WelcomeMessageCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sd, Command cm, String label, String[] args){
        if (!(sd instanceof Player pl)) {
            sd.sendMessage("This command can only be run by a player.");
            return true;
        }

        if(pl.hasPermission("custom.fakeleave")) {
            pl.sendMessage(Component.text("-").append(Component.newline()));
            Welcome.newPlayer(pl);
            pl.sendMessage(Component.text("-").append(Component.newline()));
            Welcome.oldPlayer(pl);
            pl.sendMessage(Component.text("-").append(Component.newline()));
            Welcome.builderPlayer(pl);
            return true;
        }
        else {
            pl.sendMessage(Component.text("You do not have permission to use this command", NamedTextColor.RED));
            return true;
        }
    }
}
