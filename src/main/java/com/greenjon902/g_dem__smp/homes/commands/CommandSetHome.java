package com.greenjon902.g_dem__smp.homes.commands;

import com.greenjon902.g_dem__smp.G_Dem__SMP;
import com.greenjon902.g_dem__smp.homes.Homes;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public class CommandSetHome implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Logger logger = Bukkit.getLogger();

        if (sender instanceof ConsoleCommandSender) {
            ConsoleCommandSender console = (ConsoleCommandSender) sender;
            console.sendMessage("Console cannot set homes");

        } else if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {
                ((Homes) G_Dem__SMP.getComponent("Homes")).storage.setPlayerHome(player.getUniqueId(), "home", player.getLocation(), sender);
            } else if (args.length == 1) {
                ((Homes) G_Dem__SMP.getComponent("Homes")).storage.setPlayerHome(player.getUniqueId(), args[0], player.getLocation(), sender);
            } else if (args.length == 2)  {
                //noinspection ConstantConditions
                if (sender.hasPermission("G_Dem__SMP.homes.sethome.other") || Bukkit.getOfflinePlayer(Bukkit.getOfflinePlayer(args[0]).getUniqueId()).getName().equals(sender.getName())) {
                    ((Homes) G_Dem__SMP.getComponent("Homes")).storage.setPlayerHome(Bukkit.getOfflinePlayer(args[0]).getUniqueId(), args[1], player.getLocation(), sender);
                }
            } else {
                player.sendMessage("/setHome can only have a maximum of 2 arguments");
            }
        }

        return true;
    }
}
