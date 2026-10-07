package eu.buildsouthwest.bSWEPlugin.commands;

import eu.buildsouthwest.bSWEPlugin.BSWEPlugin;
import eu.buildsouthwest.bSWEPlugin.scripts.CustomWand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

public class IncrementCommand implements CommandExecutor {
    private final BSWEPlugin plugin;
    private final CustomWand cusWand;

    private final NamespacedKey enableKey;
    private final NamespacedKey amountKey;

    private final Component nonPlayer = Component.text("This command can only be run by a player.", NamedTextColor.RED);
    private final Component checkStart = Component.text("Your current increment is ", NamedTextColor.GOLD);
    private final Component checkMiddle = Component.text(" and fastwand is ", NamedTextColor.GOLD);
    private final Component checkEnd = Component.text(".", NamedTextColor.GOLD);
    private final Component none = Component.text("Please provide a number value", NamedTextColor.RED);
    private final Component negative = Component.text("Please provide a number greater than 1", NamedTextColor.RED);
    private final Component newInc = Component.text("Your increment has been set to ", NamedTextColor.GOLD);
    private final Component notNumber = Component.text("Please provide a correct number", NamedTextColor.RED);

    public IncrementCommand(BSWEPlugin pl, CustomWand cw){
        this.plugin = pl;
        this.cusWand = cw;

        enableKey = new NamespacedKey(plugin, "incrementEnable");
        amountKey = new NamespacedKey(plugin, "increment");
    }
    @Override
    public boolean onCommand(CommandSender cs, Command cm, String label, String[] args){
        if (!(cs instanceof Player pl)) {
            cs.sendMessage(nonPlayer);
            return true;
        }

        if(args.length == 0) {
            checkWand(pl);
            return true;
        }

        switch(args[0]){
            case "check" -> checkWand(pl);
            case "on" -> switchIncrement(pl, true);
            case "off" -> switchIncrement(pl, false);
            case "set" -> setIncrement(pl, args);
            default -> setIncrement(pl, args);
        }
        return true;
    }

    private void checkWand(Player pl){
        Component state;

        if(cusWand.isCustomWandEnabled(pl)){
            state = Component.text("enabled", NamedTextColor.GREEN);
        }
        else{
            state = Component.text("disabled", NamedTextColor.RED);
        }

        pl.sendMessage(checkStart
                .append(Component.text(cusWand.getCustomWantIncrement(pl), NamedTextColor.GREEN).decorate(TextDecoration.BOLD))
                .append(checkMiddle)
                .append(state)
                .append(checkEnd)
        );
    }

    private void switchIncrement(Player pl, boolean option){
        pl.getPersistentDataContainer().set(enableKey, PersistentDataType.BOOLEAN, option);
    }

    private void setIncrement(Player pl, String[] args){
        String amountString;

        if(args[0] == null){
            pl.sendMessage(none);
            return;
        }
        else if(args.length == 1){
            amountString = args[0];
        }
        else{
            amountString = args[1];
        }

        try{
            int amount = Integer.parseInt(amountString);

            if(amount < 1) pl.sendMessage(negative);
            else if(amount == 1) pl.getPersistentDataContainer().remove(amountKey);
            else{
                pl.getPersistentDataContainer().set(amountKey, PersistentDataType.INTEGER, amount);
                pl.sendMessage(newInc.append(Component.text(amount, NamedTextColor.GREEN)).decorate(TextDecoration.BOLD));
            }
        }
        catch(NumberFormatException nfe){
            pl.sendMessage(notNumber);
        }
    }
}
