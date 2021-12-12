package com.greenjon902.g_dem__smp.homes.commands;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.chat.ChatAPI;
import com.greenjon902.g_dem__smp.homes.Homes;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class CommandHome implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            ConsoleCommandSender console = (ConsoleCommandSender) sender;
            ChatAPI.sendMessage("homes.commands.home.errors.consoleCannotTeleportToHomes", "Homes", console);
        } else {
            Player player = (Player) sender;

            String name = "home";
            UUID uniqueId = player.getUniqueId();

            if (args.length > 2) {
                sender.sendMessage("/home cannot have more than two argument");
                return true;

            } else if (args.length == 2) {
                uniqueId = Bukkit.getOfflinePlayer(args[0]).getUniqueId();
                name = args[1];

            } else if (args.length == 1) {
                name = args[0];
            }

            String finalName = name;
            UUID finalUniqueId = uniqueId;
            //noinspection ConstantConditions
            if (sender.hasPermission("G_Dem__SMP.homes.home.other") || Bukkit.getOfflinePlayer(uniqueId).getName().equals(sender.getName())) {
                Location homeLocation = ((Homes) G_Dem__SMP.getComponent("Homes")).storage.getHomeLocation(name, uniqueId);
                player.teleport(homeLocation);

                //noinspection ConstantConditions
                if (Bukkit.getOfflinePlayer(uniqueId).getName().equals(sender.getName())) {
                    if (name.equals("home")) {
                        ChatAPI.sendMessage("homes.commands.home", new HashMap<String, String>() {{
                            //noinspection ConstantConditions
                            put("world", homeLocation.getWorld().getName());
                            put("x", String.valueOf(homeLocation.getBlock().getX()));
                            put("y", String.valueOf(homeLocation.getBlock().getY()));
                            put("z", String.valueOf(homeLocation.getBlock().getZ()));
                        }}, "Homes", player);
                    } else {
                        ChatAPI.sendMessage("homes.commands.home.withName", new HashMap<String, String>() {{
                            put("homeName", finalName);
                            //noinspection ConstantConditions
                            put("world", homeLocation.getWorld().getName());
                            put("x", String.valueOf(homeLocation.getBlock().getX()));
                            put("y", String.valueOf(homeLocation.getBlock().getY()));
                            put("z", String.valueOf(homeLocation.getBlock().getZ()));
                        }}, "Homes", player);
                    }

                } else {
                    ChatAPI.sendMessage("homes.commands.home.other.withName", new HashMap<String, String>() {{
                        //noinspection ConstantConditions
                        put("userName", Bukkit.getServer().getPlayer(finalUniqueId).getName());
                        put("homeName", finalName);
                        //noinspection ConstantConditions
                        put("world", homeLocation.getWorld().getName());
                        put("x", String.valueOf(homeLocation.getBlock().getX()));
                        put("y", String.valueOf(homeLocation.getBlock().getY()));
                        put("z", String.valueOf(homeLocation.getBlock().getZ()));
                    }}, "Homes", player);
                }
            } else {
                ChatAPI.sendMessage("homes.commands.home.errors.noTeleportToOtherPermission.withName", new HashMap<String, String>() {{
                    //noinspection ConstantConditions
                    put("userName", Bukkit.getServer().getPlayer(finalUniqueId).getName());
                    put("homeName", finalName);
                }}, "Homes", player);
            }
        }
        return true;
    }
}
