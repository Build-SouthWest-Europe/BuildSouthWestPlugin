package eu.buildsouthwest.bSWEPlugin.scripts;

import eu.buildsouthwest.bSWEPlugin.BSWEPlugin;
import eu.buildsouthwest.bSWEPlugin.listeners.PlayerJoinListener;
import eu.buildsouthwest.bSWEPlugin.listeners.PlayerQuitListener;
import net.kyori.adventure.text.Component;
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

    public Welcome(BSWEPlugin plugin){
        this.plugin = plugin;
    }

    public void init(){
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(plugin, new RegionService(plugin)), plugin);
    }

    public static void cleanInv(Player pl){
//        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "/clear " + pl.getName());
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
                .append(Component.text(pl.getName(), NamedTextColor.DARK_GREEN))
                .append(Component.text("!", NamedTextColor.GREEN))
                .appendNewline()
                .append(Component.text("==========================================", NamedTextColor.GOLD).decorate(TextDecoration.BOLD).decorate(TextDecoration.STRIKETHROUGH))
                .appendNewline()
                .append(Component.text("Use <tooltip:&5Click me!><run command:/warp>", NamedTextColor.GOLD))
                .append(Component.text("/warps<reset><reset>", NamedTextColor.RED).decorate(TextDecoration.BOLD))
                .append(Component.text(" to go where you want!", NamedTextColor.GOLD))
                .appendNewline()
                .append(Component.text("Join our <tooltip:&5Click me!><link:https://discord.com/invite/4wPxWXX5bs>", NamedTextColor.GOLD))
                .append(Component.text("Discord Server<reset><reset>", NamedTextColor.AQUA).decorate(TextDecoration.BOLD))
                .append(Component.text("!", NamedTextColor.GOLD))
                .appendNewline()
                .append(Component.text("Use <tooltip:&5Click me!><suggest command:/tpa>", NamedTextColor.GOLD))
                .append(Component.text("/tpa<reset><reset>", NamedTextColor.GREEN).decorate(TextDecoration.BOLD))
                .append(Component.text(" to teleport to other players!", NamedTextColor.GOLD))
                .appendNewline()
                .append(Component.text("==========================================", NamedTextColor.GOLD).decorate(TextDecoration.BOLD).decorate(TextDecoration.STRIKETHROUGH))
                .appendNewline()
                .append(Component.text("NEW!", NamedTextColor.DARK_GREEN).decorate(TextDecoration.BOLD))
                .appendNewline()
                .append(Component.text("Our global map is back! Check it out at <tooltip:&5Open><link:https://map.buildsw.eu>", NamedTextColor.DARK_GREEN).decorate(TextDecoration.BOLD))
                .append(Component.text("Iberia Dynmap<reset><reset>", NamedTextColor.DARK_PURPLE))
                .append(Component.text("!", NamedTextColor.DARK_GREEN))
                .appendNewline()
                .append(Component.text("==========================================", NamedTextColor.GOLD).decorate(TextDecoration.BOLD).decorate(TextDecoration.STRIKETHROUGH))
                .appendNewline()
                .append(Component.text("IMPORTANT!", NamedTextColor.RED).decorate(TextDecoration.BOLD))
                .appendNewline()
                .append(Component.text("This server runs best on Minecraft 26.2!", NamedTextColor.RED))
                .appendNewline()
                .append(Component.text("Older versions than 1.17 cause loading errors", NamedTextColor.RED))
                .appendNewline()
                .append(Component.text("Minecraft Bedrock is incompatible with most's BTE servers", NamedTextColor.RED));

        pl.sendMessage(message);
        cleanInv(pl);
    }

    public static void newPlayer(Player pl){
        Component message = Component.text(" ")
                .appendNewline()
                .append(Component.text("Welcome " + pl.getName() + " ", NamedTextColor.GREEN))
                .append(Component.text(pl.getName(), NamedTextColor.DARK_GREEN))
                .append(Component.text(" to Build The Earth Southwest Europe!", NamedTextColor.GREEN))
                .appendNewline()
                .append(Component.text("==========================================", NamedTextColor.GOLD).decorate(TextDecoration.BOLD).decorate(TextDecoration.STRIKETHROUGH))
                .appendNewline()
                //.append(Component.text("Our tools to visit our remarkable sites is currently unavailable, please ask for assistance in our Discord.", NamedTextColor.GOLD))
                .append(Component.text("Use <tooltip:&5Click me!><run command:/warp>", NamedTextColor.GOLD))
                .append(Component.text("/warps<reset><reset>", NamedTextColor.RED))
                .append(Component.text(" to go where you want!", NamedTextColor.GOLD))
                .appendNewline()
                .append(Component.text("Join our <tooltip:&5Click me!><link:https://discord.com/invite/4wPxWXX5bs>", NamedTextColor.GOLD))
                .append(Component.text("Discord Server<reset><reset>", NamedTextColor.AQUA).decorate(TextDecoration.BOLD))
                .append(Component.text("!", NamedTextColor.GOLD))
                .appendNewline()
                .append(Component.text("Use <tooltip:&5Click me!><suggest command:/tpa>", NamedTextColor.GOLD))
                .append(Component.text("/tpa<reset><reset>", NamedTextColor.GREEN).decorate(TextDecoration.BOLD))
                .append(Component.text(" to teleport to other players!", NamedTextColor.GOLD))
                .appendNewline()
                .append(Component.text("==========================================", NamedTextColor.GOLD).decorate(TextDecoration.BOLD).decorate(TextDecoration.STRIKETHROUGH))
                .appendNewline()
                .append(Component.text("NEW!", NamedTextColor.GOLD).decorate(TextDecoration.BOLD))
                .appendNewline()
                .append(Component.text("See our progress on the <tooltip:&5Open><link:https://map.buildsw.eu>", NamedTextColor.GREEN))
                .append(Component.text("Iberia Dynmap<reset><reset>", NamedTextColor.DARK_GREEN).decorate(TextDecoration.BOLD))
                .append(Component.text("!", NamedTextColor.GREEN))
                .appendNewline()
                .append(Component.text(" "));

        pl.sendMessage(message);
        cleanInv(pl);
    }

    public static void builderPlayer(Player pl){
        Component message = Component.text(" ")
                .appendNewline()
                .append(Component.text("Welcome back ", NamedTextColor.GREEN))
                .append(Component.text(pl.getName(), NamedTextColor.DARK_GREEN))
                .append(Component.text("!", NamedTextColor.GREEN))
                .appendNewline()
                .append(Component.text("==========================================", NamedTextColor.GOLD).decorate(TextDecoration.BOLD).decorate(TextDecoration.STRIKETHROUGH))
                .appendNewline()
                .append(Component.text("Use <tooltip:&5Click me!><run command:/warp>", NamedTextColor.GOLD))
                .append(Component.text("/warps<reset><reset>", NamedTextColor.RED).decorate(TextDecoration.BOLD))
                .append(Component.text(" to go where you want!", NamedTextColor.GOLD))
                .appendNewline()
                .append(Component.text("Join our <tooltip:&5Click me!><link:https://discord.com/invite/4wPxWXX5bs>"))
                .append(Component.text("Discord Server<reset><reset>", NamedTextColor.AQUA).decorate(TextDecoration.BOLD))
                .append(Component.text("!", NamedTextColor.GOLD))
                .appendNewline()
                .append(Component.text("==========================================", NamedTextColor.GOLD))
                .appendNewline()
                .append(Component.text("NEW!", NamedTextColor.GOLD).decorate(TextDecoration.BOLD))
                .appendNewline()
                .append(Component.text("See our progress on the <tooltip:&5Open><link:https://map.buildsw.eu>", NamedTextColor.GREEN))
                .append(Component.text("Iberia Dynmap<reset><reset>", NamedTextColor.DARK_GREEN))
                .append(Component.text("!", NamedTextColor.GREEN))
                .appendNewline()
                .append(Component.text(" "));
        pl.sendMessage(message);
    }

    public void shutdown(){

    }
}
