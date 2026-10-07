package eu.buildsouthwest.bSWEPlugin.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LeaveCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sd, Command cm, String label, String[] args){
        if (!(sd instanceof Player pl)) {
            sd.sendMessage("This command can only be run by a player.");
            return true;
        }

        if(pl.hasPermission("custom.fakeleave")) {
            Bukkit.broadcast(Component.text(pl.getName(), NamedTextColor.YELLOW).decorate(TextDecoration.BOLD)
                    .append(Component.text(" left the server!", NamedTextColor.YELLOW)));
            pl.performCommand("vanish enable");
            return true;
        }
        else {
            pl.sendMessage(Component.text("You do not have permission to use this command", NamedTextColor.RED));
            return true;
        }
    }
}
