package eu.buildsouthwest.bSWEPlugin.commands;

import de.btegermany.terraplusminus.gen.RealWorldGenerator;
import eu.buildsouthwest.bSWEPlugin.scripts.RegionService;
import net.buildtheearth.terraminusminus.generator.EarthGeneratorSettings;
import net.buildtheearth.terraminusminus.projection.GeographicProjection;
import net.buildtheearth.terraminusminus.projection.OutOfProjectionBoundsException;
import net.buildtheearth.terraminusminus.util.geo.CoordinateParseUtils;
import net.buildtheearth.terraminusminus.util.geo.LatLng;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class TpllCommand implements CommandExecutor {
    private final Component NONPLAYER = Component.text("This command can only be run by a player.", NamedTextColor.RED);
    private final Component USAGE = Component.text("Usage: /tpll <latitude> <longitude>", NamedTextColor.WHITE);
    private final Component OUTSIDE = Component.text("The location is outside of the trial zones, you currently only have permission to use /tpll for your builder application.", NamedTextColor.RED);

    private JavaPlugin plugin;

    public TpllCommand(JavaPlugin plug){ this.plugin = plug; }

    @Override
    public boolean onCommand(CommandSender cs, Command cm, String label, String[] args) {
        plugin.getLogger().warning("Command tpll intercepted.");
        if (!(cs instanceof Player pl)) {
            cs.sendMessage(NONPLAYER);
            return true;
        }

        plugin.getLogger().warning("Command is from a player");
        if(pl.hasPermission("t+-.tpll")) forwardCommand(pl, args);

        plugin.getLogger().warning("Player is not a builder");
        if(!pl.hasPermission("tpll.trial")){
            pl.sendMessage(USAGE);
            return true;
        }
        plugin.getLogger().warning("Player is allowed to tp");

        // Adapted from TerraPlusMinus (https://github.com/BTE-Germany/TerraPlusMinus/)
        // Copyright (c) 2026 BTE Germany
        // Licensed under the MIT License
        World tpWorld = pl.getWorld();
        ChunkGenerator generator = tpWorld.getGenerator();
        RealWorldGenerator terraGenerator = null;

        if ((generator instanceof RealWorldGenerator tg)) {
            terraGenerator = tg;
        }
        if (terraGenerator == null) {
            cs.sendMessage("§cThis is not a Terraplusminus world.");
            return false;
        }

        plugin.getLogger().warning("Correct world to TP");
        EarthGeneratorSettings generatorSettings = terraGenerator.getSettings();
        GeographicProjection projection = generatorSettings.projection();
        LatLng parsedCoords;
        if(args.length == 1){
            parsedCoords = CoordinateParseUtils.parseVerbatimCoordinates(args[0]);
        }
        else if(args.length >= 2){
            parsedCoords = CoordinateParseUtils.parseVerbatimCoordinates(args[0] + " " + args[1]);
        }
        else return false;

        double x;
        double z;
        try{
            double[] mcCoords = projection.fromGeo(parsedCoords.getLng(), parsedCoords.getLat());
            x = mcCoords[0];
            z = mcCoords[1];
            plugin.getLogger().warning("Parsed coords are " + x + " " + z);
        }
        catch(OutOfProjectionBoundsException oopbe){
            cs.sendMessage("§cLocation is not within projection bounds.");
            return false;
        }

        plugin.getLogger().warning("Parsing of coords correct");
        Location destinationLoc = new Location(pl.getWorld(), x, 0, z);
        if(RegionService.isTrialRegion(destinationLoc)) forwardCommand(pl, args);
        else pl.sendMessage(OUTSIDE);
        plugin.getLogger().warning("TP action decided");

        return true;
    }

    private void forwardCommand(Player pl , String[] args){
        if(args.length == 1){
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tpll -p" + pl.getName() + " " + args[0]);
        }
        else if(args.length == 2){
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tpll -p" + pl.getName() + " " + args[0] + " " + args[1]);
        }
        else if(args.length == 3){
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tpll -p" + pl.getName() + " " + args[0] + " " + args[1] + " " + args[2]);
        }
        else{
            pl.performCommand("tpll");
        }
    }
}
