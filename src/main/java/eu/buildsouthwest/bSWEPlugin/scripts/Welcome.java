package eu.buildsouthwest.bSWEPlugin.scripts;

import eu.buildsouthwest.bSWEPlugin.BSWEPlugin;
import eu.buildsouthwest.bSWEPlugin.listeners.PlayerJoinListener;
import eu.buildsouthwest.bSWEPlugin.listeners.PlayerQuitListener;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;

public class Welcome {
    private final BSWEPlugin plugin;

    private static final Component WARPS = Component.text("/warps", NamedTextColor.RED).decorate(TextDecoration.BOLD)
            .hoverEvent(HoverEvent.showText(Component.text("Click me!", NamedTextColor.RED)))
            .clickEvent(ClickEvent.runCommand("/warps"));
    private static final Component WARPS_BUILDER = Component.text("Warp Menu", NamedTextColor.GREEN).decorate(TextDecoration.BOLD)
            .hoverEvent(HoverEvent.showText(Component.text("Click me!", NamedTextColor.GREEN)))
            .clickEvent(ClickEvent.runCommand("/warps"));
    private static final Component DISCORD = Component.text("Discord Server", NamedTextColor.AQUA).decorate(TextDecoration.BOLD)
            .hoverEvent(HoverEvent.showText(Component.text("Click me!", NamedTextColor.AQUA)))
            .clickEvent(ClickEvent.openUrl("https://discord.com/invite/4wPxWXX5bs"));
    private static final Component MAP = Component.text("SouthWest's Bluemap", NamedTextColor.DARK_PURPLE)
            .hoverEvent(HoverEvent.showText(Component.text("Click me!", NamedTextColor.DARK_PURPLE)))
            .clickEvent(ClickEvent.openUrl("https://map.buildsw.eu"));
    private static final Component TPA = Component.text("/tpa", NamedTextColor.GREEN).decorate(TextDecoration.BOLD)
            .hoverEvent(HoverEvent.showText(Component.text("Click me!", NamedTextColor.GREEN)))
            .clickEvent(ClickEvent.suggestCommand("/tpa"));
    private static final Component TPLL = Component.text("/tpll [coordinates]", NamedTextColor.RED).decorate(TextDecoration.BOLD)
            .hoverEvent(HoverEvent.showText(Component.text("Click me!", NamedTextColor.RED)))
            .clickEvent(ClickEvent.suggestCommand("/tpll"));
    private static final Component WAND = Component.text("Worldedit Wand", NamedTextColor.AQUA).decorate(TextDecoration.BOLD)
            .hoverEvent(HoverEvent.showText(Component.text("Click me!", NamedTextColor.AQUA)))
            .clickEvent(ClickEvent.runCommand("//wand"));

    public Welcome(BSWEPlugin plugin){
        this.plugin = plugin;
    }

    public void init(){
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(plugin, new RegionService(plugin)), plugin);
    }

    public static void cleanInv(Player pl){
        PlayerInventory inv = pl.getInventory();

        ItemStack ns = new ItemStack(Material.NETHER_STAR);
        ns.getItemMeta().customName(Component.text("Teleport", NamedTextColor.GREEN).decorate(TextDecoration.BOLD));
        List<Component> nsList = List.of(Component.text("Use me to visit any location in Iberia.", NamedTextColor.GRAY));
        ns.lore(nsList);

        ItemStack cy = new ItemStack(Material.CLAY_BALL);
        ns.getItemMeta().customName(Component.text("Teleport", NamedTextColor.GREEN).decorate(TextDecoration.BOLD));
        List<Component> cyList = List.of(Component.text("Find out more about the project", NamedTextColor.GRAY));
        cy.lore(cyList);

        inv.clear();
        inv.setItem(0, ns);
        inv.setItem(1, cy);
        inv.setHeldItemSlot(0);
    }

    public static void oldPlayer(Player pl){
        Component message = Component.text(" ")
                .appendNewline()
                .append(Component.text("Welcome back ", NamedTextColor.GREEN))
                .append(Component.text(pl.getName(), NamedTextColor.DARK_GREEN).decorate(TextDecoration.BOLD))
                .append(Component.text("!", NamedTextColor.GREEN))
                .appendNewline()
                .append(Component.text("==========================================", NamedTextColor.GOLD).decorate(TextDecoration.BOLD).decorate(TextDecoration.STRIKETHROUGH))
                .appendNewline()
                .append(Component.text("Use ", NamedTextColor.GOLD))
                .append(WARPS)
                .hoverEvent(HoverEvent.showText(Component.text("")))
                .append(Component.text(" to go wherever you want!", NamedTextColor.GOLD))
                .appendNewline()
                .append(Component.text("Join our ", NamedTextColor.GOLD))
                .append(DISCORD)
                .append(Component.text("!", NamedTextColor.GOLD))
                .appendNewline()
                .append(Component.text("Use ", NamedTextColor.GOLD))
                .append(TPA)
                .append(Component.text(" to teleport to other players!", NamedTextColor.GOLD))
                .appendNewline()
                .append(Component.text("==========================================", NamedTextColor.GOLD).decorate(TextDecoration.BOLD).decorate(TextDecoration.STRIKETHROUGH))
                .appendNewline()
                .append(Component.text("--NEW--", NamedTextColor.DARK_GREEN).decorate(TextDecoration.BOLD))
                .appendNewline()
                .append(Component.text("Our web map is back! Check it out at ", NamedTextColor.DARK_GREEN).decorate(TextDecoration.BOLD))
                .append(MAP)
                .append(Component.text("!", NamedTextColor.DARK_GREEN))
                .appendNewline()
                .append(Component.text("==========================================", NamedTextColor.GOLD).decorate(TextDecoration.BOLD).decorate(TextDecoration.STRIKETHROUGH))
                .appendNewline()
                .append(Component.text("--IMPORTANT--", NamedTextColor.RED).decorate(TextDecoration.BOLD))
                .appendNewline()
                .append(Component.text("This server runs best on Minecraft 26.3!", NamedTextColor.RED))
                .appendNewline()
                .append(Component.text("Versions older than 1.17 cause loading errors", NamedTextColor.RED))
                .appendNewline()
                .append(Component.text("Minecraft Bedrock is incompatible with most's BTE servers and won't load the world", NamedTextColor.RED));

        pl.sendMessage(message);
        cleanInv(pl);
    }

    public static void newPlayer(Player pl){
        Component message = Component.text(" ")
                .appendNewline()
                .append(Component.text("Welcome ", NamedTextColor.GREEN))
                .append(Component.text(pl.getName(), NamedTextColor.DARK_GREEN).decorate(TextDecoration.BOLD))
                .append(Component.text(" to Build The Earth Southwest Europe!", NamedTextColor.GREEN))
                .appendNewline()
                .append(Component.text("==========================================", NamedTextColor.GOLD).decorate(TextDecoration.BOLD).decorate(TextDecoration.STRIKETHROUGH))
                .appendNewline()
                //.append(Component.text("Our tools to visit our remarkable sites is currently unavailable, please ask for assistance in our Discord.", NamedTextColor.GOLD))
                .append(Component.text("Use ", NamedTextColor.GOLD))
                .append(WARPS)
                .append(Component.text(" to go wherever you want!", NamedTextColor.GOLD))
                .appendNewline()
                .append(Component.text("Join our ", NamedTextColor.GOLD))
                .append(DISCORD)
                .append(Component.text("!", NamedTextColor.GOLD))
                .appendNewline()
                .append(Component.text("Use ", NamedTextColor.GOLD))
                .append(TPA)
                .append(Component.text(" to teleport to other players!", NamedTextColor.GOLD))
                .appendNewline()
                .append(Component.text("==========================================", NamedTextColor.GOLD).decorate(TextDecoration.BOLD).decorate(TextDecoration.STRIKETHROUGH))
                .appendNewline()
                .append(Component.text("--NEW--", NamedTextColor.GOLD).decorate(TextDecoration.BOLD))
                .appendNewline()
                .append(Component.text("See our progress on ", NamedTextColor.GREEN))
                .append(MAP)
                .append(Component.text("!", NamedTextColor.GREEN))
                .appendNewline()
                .append(Component.text(" "))
                .appendNewline()
                .append(Component.text("==========================================", NamedTextColor.GOLD).decorate(TextDecoration.BOLD).decorate(TextDecoration.STRIKETHROUGH))
                .appendNewline()
                .append(Component.text("--IMPORTANT--", NamedTextColor.RED).decorate(TextDecoration.BOLD))
                .appendNewline()
                .append(Component.text("This server runs best on Minecraft 26.3!", NamedTextColor.RED))
                .appendNewline()
                .append(Component.text("Versions older than 1.17 cause loading errors", NamedTextColor.RED))
                .appendNewline()
                .append(Component.text("Minecraft Bedrock is incompatible with most's BTE servers", NamedTextColor.RED));;

        pl.sendMessage(message);
        cleanInv(pl);
    }

    public static void builderPlayer(Player pl){
        Component message = Component.text(" ")
                .appendNewline()
                .append(Component.text("Welcome back ", NamedTextColor.GREEN))
                .append(Component.text(pl.getName(), NamedTextColor.DARK_GREEN).decorate(TextDecoration.BOLD))
                .append(Component.text("!", NamedTextColor.GREEN))
                .appendNewline()
                .append(Component.text("==========================================", NamedTextColor.GOLD).decorate(TextDecoration.BOLD).decorate(TextDecoration.STRIKETHROUGH))
                .appendNewline()
                .append(Component.text("Use ", NamedTextColor.GOLD))
                .append(TPLL)
                .append(Component.text(" to go wherever you want!", NamedTextColor.GOLD))
                .appendNewline()
                .append(Component.text("Get the ", NamedTextColor.GOLD))
                .append(WAND)
                .append(Component.text("!", NamedTextColor.GOLD))
                .appendNewline()
                .append(Component.text("Go to our ", NamedTextColor.GOLD))
                .append(WARPS_BUILDER)
                .append(Component.text("!", NamedTextColor.GOLD))
                .appendNewline()
                .append(Component.text("==========================================", NamedTextColor.GOLD).decorate(TextDecoration.BOLD).decorate(TextDecoration.STRIKETHROUGH))
                .appendNewline()
                .append(Component.text("--NEW--", NamedTextColor.GOLD).decorate(TextDecoration.BOLD))
                .appendNewline()
                .append(Component.text("Our web map is back! Check it out at ", NamedTextColor.GREEN))
                .append(MAP)
                .append(Component.text("!", NamedTextColor.GREEN))
                .appendNewline()
                .append(Component.text(" "));
        pl.sendMessage(message);
    }

    public void shutdown(){

    }
}
