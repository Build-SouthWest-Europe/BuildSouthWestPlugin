package eu.buildsouthwest.bSWEPlugin.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HelpCommand extends Command implements CommandExecutor, TabCompleter {
    private static final List<String> TOPICS = List.of("rules", "warps", "teleport", "worldedit", "axiom", "claims");
    private static final Component HELP = Component.text("Welcome to BuildTheEarth SouthWest Europe!", NamedTextColor.BLUE)
            .appendNewline()
            .append(Component.text("You can get more info about the server with the following options: ", NamedTextColor.GOLD))
            .appendNewline()
            .append(Component.text("/help rules", NamedTextColor.DARK_GREEN))
            .append(Component.text(" to learn about the server building rules.", NamedTextColor.GOLD))
            .appendNewline();
    private static final Component RULES = Component.text("Building rules of BTE SouthWest Europe:")
            .appendNewline()
            .append(Component.text("§1 ")).decorate(TextDecoration.BOLD)
            .append(Component.text(" Building interiors is...")).decorate(TextDecoration.UNDERLINED)
            .appendNewline()
            .append(Component.text("§a ")).decorate(TextDecoration.BOLD)
            .append(Component.text("Forbidden if buildings are privately owned or the area is inaccessible."))
            .appendNewline()
            .append(Component.text("§b ")).decorate(TextDecoration.BOLD)
            .append(Component.text("Permitted in publicly accessible buildings."))
            .appendNewline()
            .append(Component.text("§c ")).decorate(TextDecoration.BOLD)
            .append(Component.text("Desired in publicly accessible landmarks."))
            .appendNewline().appendNewline()
            .append(Component.text("§2 ")).decorate(TextDecoration.BOLD)
            .append(Component.text("Building vehicles is ")).decorate(TextDecoration.UNDERLINED)
            .append(Component.text("not permitted")).decorate(TextDecoration.BOLD).decorate(TextDecoration.UNDERLINED)
            .append(Component.text(", unless:")).decorate(TextDecoration.UNDERLINED)
            .appendNewline();
    private final static Component WARPS = Component.text("Warps are our landmark system, with them you can...");
    private final static Component TELEPORT = Component.text("To move around in thw SouthWest Europe server you have various option with different level of permissions...");
    private final static Component WORLDEDIT = Component.text("We have included WorldEdit in the server to help you in your construction and application proccess...");
    private final static Component AXIOM = Component.text("Axiom is also included for our higher level builders to speed up even more construction...");
    private final static Component CLAIMS = Component.text("In BTE we use a claim system to mark our builds and show our progress, you can check at...");

    public HelpCommand(){
        super("help");
        this.setDescription("Shows server info");
        this.setUsage("/help [keyword]");
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cm, String label, String[] args){
        if (!(cs instanceof Player pl)) {
            cs.sendMessage("This command can only be run by a player.");
            return true;
        }

        if(args.length == 0){
            pl.sendMessage(HELP);
            return true;
        }

        switch (args[0]){
            case "rules" -> pl.sendMessage(RULES);
            case "warps" -> pl.sendMessage(WARPS);
            case "teleport" -> pl.sendMessage(TELEPORT);
            case "worldedit" -> pl.sendMessage(WORLDEDIT);
            case "axiom" -> pl.sendMessage(AXIOM);
            case "claims" -> pl.sendMessage(CLAIMS);
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            String partial = args[0].toLowerCase();
            return TOPICS.stream()
                    .filter(topic -> topic.startsWith(partial))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] args) {
        return false;
    }
}
