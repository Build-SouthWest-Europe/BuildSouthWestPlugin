package eu.buildsouthwest.bSWEPlugin.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AliasCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender cs, Command cm, String label, String[] args){
        if (!(cs instanceof Player pl)) {
            cs.sendMessage("This command can only be run by a player.");
            return true;
        }

        return switch(cm.getName().toLowerCase()){
            case "time" -> handleTime(pl, args);
            case "weather" -> handleWeather(pl, args);
            case "builder" -> handleButil(pl);
            case "c" -> handleButil(pl);
            case "bn" -> handleBanner(pl);
            case "plugins" -> handlePlugins(pl);
            case "link" -> handleLink(pl);
            case "discordlink" -> handleLink(pl);
            case "iblink" -> handleLink(pl);
            case "unlink" -> handleUnlink(pl);
            case "discordunlink" -> handleUnlink(pl);
            case "ibunlink" -> handleUnlink(pl);
            case "debugstick" -> handleStick(pl);
            case "dstick" -> handleStick(pl);
            case "blockwand" -> handleStick(pl);
            case "hide" -> handleHide(pl);
            case "show" -> handleShow(pl);
            default -> false;
        };
    }

    private boolean handleTime(Player pl, String[] args){
        pl.performCommand("time" + args[0].toString());
        return true;
    }

    private boolean handleWeather(Player pl, String[] args){
        pl.performCommand("weather" + args[0].toString());
        return true;
    }

    private boolean handleButil(Player pl){
        pl.performCommand("butil");
        return true;
    }

    private boolean handleBanner(Player pl){
        pl.performCommand("banner");
        return true;
    }

    private boolean handlePlugins(Player pl){
        Component message = Component.text("Plugins (5): ")
                .append(Component.text("Never", NamedTextColor.GREEN))
                .append(Component.text(", "))
                .append(Component.text("Gonna", NamedTextColor.GREEN))
                .append(Component.text(", "))
                .append(Component.text("Give", NamedTextColor.GREEN))
                .append(Component.text(", "))
                .append(Component.text(" You", NamedTextColor.GREEN))
                .append(Component.text(", "))
                .append(Component.text("Up", NamedTextColor.GREEN));
        return true;
    }

    private boolean handleLink(Player pl){
        pl.performCommand("discord link");
        return true;
    }

    private boolean handleUnlink(Player pl){
        pl.performCommand("discord unlink");
        return true;
    }

    private boolean handleStick(Player pl){
        pl.give(new ItemStack(Material.DEBUG_STICK));
        return true;
    }

    private boolean handleHide(Player pl){
        pl.performCommand("vanish on");
        pl.performCommand("dynmap hide");
        return true;
    }

    private boolean handleShow(Player pl){
        pl.performCommand("vanish off");
        pl.performCommand("dynmap show");
        return true;
    }
}
