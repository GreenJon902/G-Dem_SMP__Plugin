package com.greenjon902.g_dem__smp.homes.commands;

import com.greenjon902.g_dem__smp.homes.Homes;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CommandHome implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            ConsoleCommandSender console = (ConsoleCommandSender) sender;
            console.sendMessage("Console cannot teleport to homes");
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

            //noinspection ConstantConditions
            if (sender.hasPermission("G_Dem__SMP.homes.home.other") || Bukkit.getOfflinePlayer(uniqueId).getName().equals(sender.getName())) {
                Location homeLocation = Homes.storage.getHomeLocation(name, uniqueId);
                player.teleport(homeLocation);
                //noinspection ConstantConditions
                sender.sendMessage("Teleported to home called " + name + " at " + homeLocation.getBlock().getX() + " " +
                        homeLocation.getBlock().getY() + " " + homeLocation.getBlock().getZ() + " in " +
                        homeLocation.getWorld().getName());
            }
        }
        return true;
    }
}
