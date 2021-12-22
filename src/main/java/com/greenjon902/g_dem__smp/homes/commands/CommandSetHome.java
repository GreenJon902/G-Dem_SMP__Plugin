package com.greenjon902.g_dem__smp.homes.commands;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.chat.ChatAPI;
import com.greenjon902.g_dem__smp.homes.HomeAlreadyExistsException;
import com.greenjon902.g_dem__smp.homes.Homes;
import com.greenjon902.tabCompleterHelper.TabCompleterHelper;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class CommandSetHome implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Logger logger = Bukkit.getLogger();

        if (sender instanceof ConsoleCommandSender) {
            ConsoleCommandSender console = (ConsoleCommandSender) sender;
            ChatAPI.sendMessage("homes.commands.setHome.errors.consoleCantSetHomes", "Homes", console);

        } else if (sender instanceof Player) {
            Player player = (Player) sender;


            Location location = player.getLocation();

            if (args.length == 0) {
                try {
                    ((Homes) G_Dem__SMP.getComponent("Homes")).storage.setPlayerHome(player.getUniqueId(), "home", location);
                    ChatAPI.sendMessage("homes.commands.setHome", new HashMap<String, String>() {{
                        //noinspection ConstantConditions
                        put("world", location.getWorld().getName());
                        put("x", String.valueOf(location.getBlock().getX()));
                        put("y", String.valueOf(location.getBlock().getY()));
                        put("z", String.valueOf(location.getBlock().getZ()));
                    }}, "Homes", player);

                } catch (HomeAlreadyExistsException e) {
                    ChatAPI.sendMessage("homes.commands.setHome.errors.homeAlreadyExists", "Homes", player);
                }


            } else if (args.length == 1) {
                try {
                    ((Homes) G_Dem__SMP.getComponent("Homes")).storage.setPlayerHome(player.getUniqueId(), args[0], player.getLocation());
                    ChatAPI.sendMessage("homes.commands.setHome.setHome.withName", new HashMap<String, String>() {{
                        put("homeName", args[0]);
                        //noinspection ConstantConditions
                        put("world", location.getWorld().getName());
                        put("x", String.valueOf(location.getBlock().getX()));
                        put("y", String.valueOf(location.getBlock().getY()));
                        put("z", String.valueOf(location.getBlock().getZ()));
                    }}, "Homes", player);

                } catch (HomeAlreadyExistsException e) {
                    ChatAPI.sendMessage("homes.commands.setHome.errors.homeAlreadyExists.withName", new HashMap<String, String>() {{
                        put("homeName", args[0]);
                    }}, "Homes", player);
                }


            } else if (args.length == 2)  {
                //noinspection ConstantConditions
                if (sender.hasPermission("g_dem__smp.homes.sethome.other") || Bukkit.getOfflinePlayer(Bukkit.getOfflinePlayer(args[0]).getUniqueId()).getName().equals(sender.getName())) {
                    try {
                        ((Homes) G_Dem__SMP.getComponent("Homes")).storage.setPlayerHome(Bukkit.getOfflinePlayer(args[0]).getUniqueId(), args[1], player.getLocation());
                        ChatAPI.sendMessage("homes.commands.setHome.setHome.other.withName", new HashMap<String, String>() {{
                            put("userName", args[0]);
                            put("homeName", args[1]);
                            //noinspection ConstantConditions
                            put("world", location.getWorld().getName());
                            put("x", String.valueOf(location.getBlock().getX()));
                            put("y", String.valueOf(location.getBlock().getY()));
                            put("z", String.valueOf(location.getBlock().getZ()));
                        }}, "Homes", player);

                    } catch (HomeAlreadyExistsException e) {
                        ChatAPI.sendMessage("homes.commands.setHome.errors.homeAlreadyExists.other.withName", new HashMap<String, String>() {{
                            put("userName", args[0]);
                            put("homeName", args[1]);
                        }}, "Homes", player);
                    }
                }
            } else {
                player.sendMessage("/setHome can only have a maximum of 2 arguments");
            }
        }

        return true;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (sender.hasPermission("G_Dem__SMP.homes.sethome.other")) {
            if (args.length == 1) {
                return TabCompleterHelper.filterWithFunction(Bukkit.getOfflinePlayers(), (player) -> ((OfflinePlayer) player).getName(), args[0]);
            }
        }
        return TabCompleterHelper.noSolutions;
    }
}
